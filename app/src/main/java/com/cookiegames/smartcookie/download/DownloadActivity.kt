/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 * Created by CookieJarApps 10/01/2020 */

package com.cookiegames.smartcookie.download

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.webkit.MimeTypeMap
import android.widget.Filter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.cookiegames.smartcookie.AppTheme
import com.cookiegames.smartcookie.BuildConfig
import com.cookiegames.smartcookie.R
import com.cookiegames.smartcookie.databinding.ActivityDownloadBinding
import com.cookiegames.smartcookie.databinding.DownloadItemBinding
import com.cookiegames.smartcookie.di.injector
import com.cookiegames.smartcookie.preference.UserPreferences
import com.cookiegames.smartcookie.utils.ThemeUtils
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.huxq17.download.Pump
import com.huxq17.download.core.DownloadInfo
import com.huxq17.download.core.DownloadListener
import com.huxq17.download.utils.LogUtil
import java.io.File
import java.util.*
import javax.inject.Inject


class DownloadActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
  @JvmField
  @Inject
  var mUserPreferences: UserPreferences? = null

  private var downloadObserver: DownloadListener = object : DownloadListener() {
    override fun onProgress(progress: Int) {
      val downloadInfo = downloadInfo
      val viewHolder = downloadInfo.extraData as DownloadViewHolder?
      val tag = map[viewHolder]
      if (tag != null && tag.id == downloadInfo.id) {
        viewHolder?.bindData(downloadInfo, status)
      }
    }

    override fun onFailed() {
      super.onFailed()
      LogUtil.e("onFailed code=" + downloadInfo.errorCode)
    }
  }

  private val map = HashMap<DownloadViewHolder, DownloadInfo>()
  private var downloadAdapter: DownloadAdapter? = null
  private lateinit var downloadInfoList: MutableList<DownloadInfo>

  private lateinit var activityDownloadBinding: ActivityDownloadBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    this.injector.inject(this)

    val color: Int
    if (mUserPreferences!!.useTheme === AppTheme.LIGHT) {
      setTheme(R.style.Theme_SettingsTheme)
      color = ThemeUtils.getColorBackground(this)
      window.setBackgroundDrawable(ColorDrawable(color))
    } else if (mUserPreferences!!.useTheme === AppTheme.DARK) {
      setTheme(R.style.Theme_SettingsTheme_Dark)
      color = ThemeUtils.getColorBackground(this)
      window.setBackgroundDrawable(ColorDrawable(color))
    } else {
      setTheme(R.style.Theme_SettingsTheme_Black)
      color = ThemeUtils.getColorBackground(this)
      window.setBackgroundDrawable(ColorDrawable(color))
    }

    super.onCreate(savedInstanceState)
    activityDownloadBinding = ActivityDownloadBinding.inflate(layoutInflater)
    setContentView(activityDownloadBinding.root)
    ButterKnife.bind(this)
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    if (supportActionBar != null) supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    val downloadInfoList = Pump.getAllDownloadList()
    val list = findViewById<RecyclerView>(R.id.downloads)
    val linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

    //Sort download list if need.
    downloadInfoList.sortWith { o1, o2 -> (o1.createTime - o2.createTime).toInt() }
    list.layoutManager = linearLayoutManager
    downloadAdapter = DownloadAdapter(map, downloadInfoList)
    list.adapter = downloadAdapter
    downloadObserver.enable()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      else -> finish()
    }
    return super.onOptionsItemSelected(item)
  }


  override fun onDestroy() {
    super.onDestroy()
    downloadObserver.disable()
  }

  class DownloadAdapter(
    var map: HashMap<DownloadViewHolder, DownloadInfo>,
    var downloadInfoList: MutableList<DownloadInfo>
  ) : androidx.recyclerview.widget.RecyclerView.Adapter<DownloadViewHolder>() {

    lateinit var filtered: MutableList<DownloadInfo>
    lateinit var oldList: MutableList<DownloadInfo>

    fun getFilter(): Filter {
      return object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults? {
          val charString = charSequence.toString()
          if (charString.isEmpty()) {
            filtered = oldList
          } else {
            val filteredList: MutableList<DownloadInfo> = ArrayList()
            for (row in oldList) {
              if (row.name.lowercase(Locale.getDefault())
                  .contains(charString.lowercase(Locale.getDefault()))
              ) {
                filteredList.add(row)
              }
            }
            filtered = filteredList
          }
          val filterResults = FilterResults()
          filterResults.values = filtered
          return filterResults
        }

        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
          if (filterResults.values != null) {
            downloadInfoList = filterResults.values as MutableList<DownloadInfo>

            notifyDataSetChanged()
          }
        }
      }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DownloadViewHolder {
      val downloadItemBinding: DownloadItemBinding =
        DownloadItemBinding.inflate(LayoutInflater.from(viewGroup.context))
      oldList = downloadInfoList
      return DownloadViewHolder(downloadItemBinding, this)
    }

    override fun onBindViewHolder(viewHolder: DownloadViewHolder, i: Int) {
      val downloadInfo = downloadInfoList[i]
      viewHolder.bindData(downloadInfo, downloadInfo.status)

      downloadInfo.extraData = viewHolder
      map[viewHolder] = downloadInfo
    }

    fun delete(viewHolder: DownloadViewHolder) {
      val position = viewHolder.adapterPosition
      downloadInfoList.removeAt(position)
      notifyItemRemoved(position)
      map.remove(viewHolder)
    }

    override fun getItemCount(): Int {
      return downloadInfoList.size
    }
  }

  class DownloadViewHolder(downloadItemBinding: DownloadItemBinding, adapter: DownloadAdapter) :
    RecyclerView.ViewHolder(downloadItemBinding.root), View.OnClickListener, View.OnLongClickListener {
    lateinit var downloadInfo: DownloadInfo
    lateinit var status: DownloadInfo.Status
    private var totalSizeString: String? = null

    private var dl_name: TextView
    private var dl_speed: TextView
    private var dl_download: TextView
    private var dl_status: MaterialButton
    private var dl_progress: ProgressBar
    private var dl_icon: ImageView

    var totalSize: Long = 0
    var dialog: AlertDialog

    init {
      downloadItemBinding.dlStatus.setOnClickListener(this)
      downloadItemBinding.root.setOnLongClickListener(this)
      dialog = MaterialAlertDialogBuilder(downloadItemBinding.root.context)
        .setTitle(downloadItemBinding.root.context.resources.getString(R.string.confirm_delete))
        .setPositiveButton(downloadItemBinding.root.context.resources.getString(R.string.yes)) { _, _ ->
          adapter.delete(this@DownloadViewHolder)
          Pump.deleteById(downloadInfo.id)
        }
        .setNegativeButton(downloadItemBinding.root.context.resources.getString(R.string.no)) { _, _ -> }
        .create()
      dl_name = downloadItemBinding.dlName
      dl_status = downloadItemBinding.dlStatus
      dl_progress = downloadItemBinding.dlProgress
      dl_icon = downloadItemBinding.dlIcon
      dl_speed = downloadItemBinding.dlSpeed
      dl_download = downloadItemBinding.dlDownload
    }

    fun bindData(downloadInfo: DownloadInfo, status: DownloadInfo.Status) {
      this.downloadInfo = downloadInfo
      this.status = status
      dl_name.text = downloadInfo.name
      dl_name.isSelected = true
      var speed = ""
      val progress = downloadInfo.progress
      dl_progress.progress = progress
      when (status) {
        DownloadInfo.Status.STOPPED -> dl_status.text =
          itemView.context.getString(R.string.start_download)

        DownloadInfo.Status.PAUSING -> dl_status.text =
          itemView.context.getString(R.string.pausing_download)

        DownloadInfo.Status.PAUSED -> dl_status.text =
          itemView.context.getString(R.string.continue_download)

        DownloadInfo.Status.WAIT -> dl_status.text =
          itemView.context.getString(R.string.waiting_download)

        DownloadInfo.Status.RUNNING -> {
          dl_status.text = itemView.context.getString(R.string.pause_download)
          speed = downloadInfo.speed
        }

        DownloadInfo.Status.FINISHED -> dl_status.text =
          itemView.context.getString(R.string.action_open)

        else -> dl_status.text = itemView.context.getString(R.string.title_error)
      }
      dl_speed.text = speed
      val completedSize = downloadInfo.completedSize
      if (totalSize == 0L) {
        val totalSize = downloadInfo.contentLength
        totalSizeString = "/" + DownloadUtil.getDataSize(totalSize)
      }
      dl_download.text = DownloadUtil.getDataSize(completedSize) + totalSizeString!!
      when (File(downloadInfo.filePath).extension) {
        "pdf" -> dl_icon.setImageResource(R.drawable.icon_pdf)
        "zip" -> dl_icon.setImageResource(R.drawable.icon_zip)
        "apk" -> dl_icon.setImageResource(R.drawable.icon_apk)
        "txt", "doc", "docx" -> dl_icon.setImageResource(R.drawable.icon_txt)
        "jpg", "jpeg", "gif", "png" -> dl_icon.setImageResource(R.drawable.icon_img)
        "bin" -> dl_icon.setImageResource(R.drawable.icon_bin)
      }

    }

    fun openFile(filePath: String, v: View) {
      val intent = Intent()
      intent.action = Intent.ACTION_VIEW

      val finalUri = FileProvider.getUriForFile(
        v.context,
        BuildConfig.APPLICATION_ID + ".fileprovider",
        File(filePath)
      )
      intent.setDataAndType(
        finalUri,
        MimeTypeMap.getSingleton().getMimeTypeFromExtension(getFileExtension(filePath))
      )
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
      if (intent.resolveActivity(v.context.packageManager) != null) {
        v.context.startActivity(intent)
      } else {
        Toast.makeText(
          v.context,
          v.context.resources.getString(R.string.no_installed_app),
          Toast.LENGTH_LONG
        ).show()
      }
    }

    fun getFileExtension(filename: String?): String? {
      if (filename == null) {
        return null
      }
      val lastUnixPos = filename.lastIndexOf('/')
      val lastWindowsPos = filename.lastIndexOf('\\')
      val indexOfLastSeparator = Math.max(lastUnixPos, lastWindowsPos)
      val extensionPos = filename.lastIndexOf('.')
      val indexOfExtension = if (indexOfLastSeparator > extensionPos) -1 else extensionPos
      return if (indexOfExtension == -1) {
        null
      } else {
        filename.substring(indexOfExtension + 1).lowercase(Locale.getDefault())
      }
    }

    override fun onClick(v: View) {
      if (v === dl_status) {
        when (status) {
          DownloadInfo.Status.STOPPED -> Pump.newRequest(downloadInfo.url, downloadInfo.filePath)
            .setId(downloadInfo.id)
            .submit()

          DownloadInfo.Status.PAUSED -> Pump.resume(downloadInfo.id)
          DownloadInfo.Status.WAIT -> {
          }

          DownloadInfo.Status.RUNNING -> Pump.pause(downloadInfo.id)
          DownloadInfo.Status.FINISHED -> openFile(downloadInfo.filePath, v)
          else -> Pump.resume(downloadInfo.id)
        }
      }

    }

    override fun onLongClick(v: View): Boolean {
      dialog.show()
      return true
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.download, menu)

    val searchItem: MenuItem = menu.findItem(R.id.action_search)
    val searchView: SearchView = searchItem.actionView as SearchView
    searchView.setOnQueryTextListener(this)

    return super.onCreateOptionsMenu(menu)
  }

  override fun onQueryTextChange(query: String?): Boolean {
    downloadAdapter?.getFilter()?.filter(query)
    return false
  }

  override fun onQueryTextSubmit(query: String?): Boolean {
    return false
  }
}