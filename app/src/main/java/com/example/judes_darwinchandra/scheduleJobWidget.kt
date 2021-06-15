package com.example.judes_darwinchandra

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_pekerjaan.*
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class scheduleJobWidget : AppWidgetProvider() {
    // membuat var yang menampung preferences
    var JobPref : JobIdsPref?=null
    // membuat fungsi update
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // jika jobpref null maka jobpref sama dengan jobidspref context
        if(JobPref==null){
            JobPref = JobIdsPref(context)
        }
        // jobpref dikosongkan
        JobPref?.getJobId()?.clear()
        // membuat var yang menampung jobpref
        var JobId = JobPref?.getJobId()
        // ini untuk multiple widget yang sedang aktif dan akan diupdate semuanya
        for (appWidgetId in appWidgetIds) {
            // appwidget ditambahkan ke jobid
            JobId?.add(appWidgetId.toString())
            // update widget
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        // jika tidak null maka jobpref disetkan langsung dengan jobid
        if(JobId!=null){
            JobPref?.setJobId(JobId)
        }
    }

    // membuat fungsi untuk mengenablekan widget
    override fun onEnabled(context: Context) {
        // membuat alarm intent yang bisa menuju ke interviewmessage
        var alarmIntent = Intent(context, jobInterviewMessage::class.java).let {
            // membuat it menjadi autoupdate
            it.action = ACTION_AUTO_UPDATE
            // pending intent  menerima data broadcast
            PendingIntent.getBroadcast(context,101,it, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        // membuat var yang menampung kalender
        var cal = Calendar.getInstance()
        // calender di tmbh menitnya
        cal.add(Calendar.MINUTE,1)

        // alarm manager bekerja sebagai service sehingga dapat di jalankan walaupun aplikasi dalam keadaan tertutup
        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // alarm akan diulang setiap 1 jam
        alarmManager.setRepeating(AlarmManager.RTC,cal.timeInMillis,60000,alarmIntent)
    }
    // membuat fungsi ketika disabled
    override fun onDisabled(context: Context) {
        // membuat alarm intent yang bisa menuju ke interviewmessage
        var alarmIntent = Intent(context, jobInterviewMessage::class.java).let {
            // membuat it menjadi autoupdate
            it.action = ACTION_AUTO_UPDATE
            // pending intent  menerima data broadcast
            PendingIntent.getBroadcast(context,101,it,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        // membuat var yang menampung kalender
        var cal = Calendar.getInstance()
        // calender di tmbh menitnya
        cal.add(Calendar.MINUTE,1)
// alarm manager bekerja sebagai service sehingga dapat di jalankan walaupun aplikasi dalam keadaan tertutup
        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // alarm akan diulang setiap 1 jam
        alarmManager.cancel(alarmIntent)
    }
    // membuat fungsi menerima
    override fun onReceive(context: Context?, intent: Intent?) {
        // jika intent aksi tidak sama dengan action autoupdate maka jalankan
        if(intent?.action!!.equals(ACTION_AUTO_UPDATE)) {
            // jika jobpref null maka jalankan
            if (JobPref == null) {
                // context tidak null
                JobPref = JobIdsPref(context!!)
            }
            // setiap appwidget didalam jobpref maka update widget
            for (appWidgetId in JobPref?.getJobId()!!) {
                updateAppWidget(context!!, AppWidgetManager.getInstance(context),appWidgetId.toInt())
            }
        }
        super.onReceive(context, intent)
    }

    // membuat fungis onappwidget
    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {

        // membuat RemoteViews object
        val views = RemoteViews(context?.packageName, R.layout.schedule_job_widget)
        // mmebuat batas besar
        var minWidth = newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)

        // membuat var yang menampung pesan
        val widgetText = pesan.getMessage()
        // jika minwidth tidak lbh besar dari 291 maka setview card ke 1 dibuat keluar
        if (minWidth!! >291){
            views.setViewVisibility(R.id.card_interview1, View.VISIBLE)
            views.setViewVisibility(R.id.card_interview2, View.INVISIBLE)
            views.setTextViewText(R.id.time_interview1, "Interview : "+widgetText.jadwal)
        }
        // jika minwidth tidak lbh besar dari 214 maka setview card ke 1 dibuat keluar
        else if(minWidth!! >214){
            views.setViewVisibility(R.id.card_interview1, View.VISIBLE)
            views.setViewVisibility(R.id.card_interview2, View.INVISIBLE)
            views.setTextViewText(R.id.time_interview1, widgetText.jadwal)
        }
        // jika minwidth tidak lbh besar dari lainnya maka setview card ke 2 dibuat keluar
        else{
            views.setViewVisibility(R.id.card_interview1, View.INVISIBLE)
            views.setViewVisibility(R.id.card_interview2, View.VISIBLE)
            views.setTextViewText(R.id.time_interview1, widgetText.jadwal)
        }
        // widget manager untuk mengupdate widget
        appWidgetManager?.updateAppWidget(appWidgetId, views)
    }
    companion object{
        // membuat var pesan mengambil data dari jobinterviewmessage
        var pesan = jobInterviewMessage()
        // membuat var action autoupdate
        var ACTION_AUTO_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE"

        // membuat fungsi update widget
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            // membuat widget yang mengambil data dari pesan
            val widgetText = pesan.getMessage()
            // membuat RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.schedule_job_widget)
            // setview posisiloker di jabatan_pegawai_interview1
            views.setTextViewText(R.id.jabatan_pegawai_interview1, widgetText.posisiLoker)
            // setview namaperusahaan di nama_perusahaan_interview1
            views.setTextViewText(R.id.nama_perusahaan_interview1, widgetText.namaPerusahaan)
            // setview interview image di icon_perusahaan_interview1
            views.setImageViewResource(R.id.icon_perusahaan_interview1, R.drawable.ic_interview);
            // setview jadwal di time_interview1
            views.setTextViewText(R.id.time_interview1, "Interview : "+widgetText.jadwal)
            // setview posisiLoker di jabatan_pegawai_interview2
            views.setTextViewText(R.id.jabatan_pegawai_interview2, widgetText.posisiLoker)
            // setview jadwal di time_interview2
            views.setTextViewText(R.id.time_interview2, widgetText.jadwal)
            // setview click on pending intent pada card 1 mengambil intent activity dari context dengan fungsi
            views.setOnClickPendingIntent(R.id.card_interview1,getPendingIntentActivity(context))
            // setview click on pending intent pada card 2 mengambil intent activity dari context dengan fungis
            views.setOnClickPendingIntent(R.id.card_interview2,getPendingIntentActivity(context))
            // widget manager untuk mengupdate widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
        // membuat fungsi getpending intent
        private fun getPendingIntentActivity(context: Context): PendingIntent? {
            // intent menuju ke mainactivity
            var mIntent=Intent(context,MainActivity::class.java)
            return PendingIntent.getActivity(context,0,mIntent,0)
        }
    }
}

