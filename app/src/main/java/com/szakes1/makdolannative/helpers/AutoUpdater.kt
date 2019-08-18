package com.szakes1.makdolannative.helpers

import android.app.Activity
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.*
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import androidx.core.content.FileProvider
import com.szakes1.makdolannative.BuildConfig
import com.szakes1.makdolannative.R
import org.json.JSONObject
import java.io.*
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class AutoUpdater : FileProvider() {

    var act: Activity? = null
    lateinit var ctx: Context

    //  Checks if app has permission for saving files to external storage
    fun checkForPermission() {
        ctx = act!!.applicationContext
        if (Build.VERSION.SDK_INT>=23) {
            if(act!!.shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) { }
            else {
                if (ctx.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    act!!.requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
                } else showDialog()
            }
        }else showDialog()
    }

    // Displays dialog if update is available
    fun showDialog() {
        val f = File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "MakdolanNative.apk")
        if(f.exists()) f.delete()

        if(CheckVersionClass().execute().get()) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(act)
            builder.setTitle(R.string.update_title)
                .setCancelable(true)
                .setPositiveButton(R.string.update_button) { _, _ -> if (DownloadFileInBackground().execute(ctx).get()) ctx.registerReceiver(openApk, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) }
                .setNeutralButton(R.string.cancel_button) {_, _ -> }
            builder.create().show()
        }
    }

    // Checks for updates asynchronously
    private class CheckVersionClass : AsyncTask<String, String, Boolean>() {
        override fun doInBackground(vararg p0: String?): Boolean {
            val http: HttpsURLConnection = URL("https://api.github.com/repos/szakes1/makdolannative/releases/latest").openConnection() as HttpsURLConnection
            http.requestMethod = "GET"
            http.connect()
            val br = BufferedReader(InputStreamReader(http.inputStream))
            val lines = br.readLines()
            val version = JSONObject(lines[0])["tag_name"] as String
            return (version.toFloat()>BuildConfig.VERSION_NAME.toFloat())
        }
    }

    // Downloads MakdolanNative.apk from github asynchronously
    private class DownloadFileInBackground: AsyncTask<Context, String, Boolean>() {
        override fun doInBackground(vararg p0: Context?): Boolean {
            val downloadManager = p0[0]!!.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri: Uri = Uri.parse("https://github.com/szakes1/MakdolanNative/releases/latest/download/MakdolanNative.apk")
            val request: DownloadManager.Request = DownloadManager.Request(uri)
            request.setDestinationUri(Uri.fromFile(File(p0[0]!!.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "MakdolanNative.apk")))
            downloadManager.enqueue(request)
            return true
        }
    }

    // Opens an apk
    private val openApk: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.data = getUriForFile(ctx, "com.szakes1.fileprovider", File(p0!!.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "MakdolanNative.apk"))
            }
            else { intent.setDataAndType(Uri.fromFile(File(p0!!.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "MakdolanNative.apk")), "application/vnd.android.package-archive") }
            ctx.startActivity(intent)
            ctx.unregisterReceiver(this)
        }
    }
}