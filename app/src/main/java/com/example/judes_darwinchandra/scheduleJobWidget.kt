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
    var JobPref : JobIdsPref?=null
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        if(JobPref==null){
            JobPref = JobIdsPref(context)
        }
        JobPref?.getJobId()?.clear()
        var JobId = JobPref?.getJobId()
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            JobId?.add(appWidgetId.toString())
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        if(JobId!=null){
            JobPref?.setJobId(JobId)
        }
    }

    override fun onEnabled(context: Context) {
        var alarmIntent = Intent(context, jobInterviewMessage::class.java).let {
            it.action = ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(context,101,it, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE,1)

        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC,cal.timeInMillis,60000,alarmIntent)
    }

    override fun onDisabled(context: Context) {
        var alarmIntent = Intent(context, jobInterviewMessage::class.java).let {
            it.action = ACTION_AUTO_UPDATE
            PendingIntent.getBroadcast(context,101,it,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        var cal = Calendar.getInstance()
        cal.add(Calendar.MINUTE,1)

        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(alarmIntent)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action!!.equals(ACTION_AUTO_UPDATE)) {
            if (JobPref == null) {
                JobPref = JobIdsPref(context!!)
            }
            for (appWidgetId in JobPref?.getJobId()!!) {
                updateAppWidget(context!!, AppWidgetManager.getInstance(context),appWidgetId.toInt())
            }
        }
        super.onReceive(context, intent)
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {

        // Construct the RemoteViews object
        val views = RemoteViews(context?.packageName, R.layout.schedule_job_widget)
        var minWidth = newOptions?.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH)
        //Toast.makeText(context, "minwidth : "+minWidth+" maxHeight : "+maxHeight, Toast.LENGTH_SHORT).show()

        val widgetText = pesan.getMessage()
        if (minWidth!! >291){
            views.setViewVisibility(R.id.card_interview1, View.VISIBLE)
            views.setViewVisibility(R.id.card_interview2, View.INVISIBLE)
            views.setTextViewText(R.id.time_interview1, "Interview : "+widgetText.jadwal)
        }
        else if(minWidth!! >214){
            views.setViewVisibility(R.id.card_interview1, View.VISIBLE)
            views.setViewVisibility(R.id.card_interview2, View.INVISIBLE)
            views.setTextViewText(R.id.time_interview1, widgetText.jadwal)
        }
        else{
            views.setViewVisibility(R.id.card_interview1, View.INVISIBLE)
            views.setViewVisibility(R.id.card_interview2, View.VISIBLE)
            views.setTextViewText(R.id.time_interview1, widgetText.jadwal)
        }
        // Instruct the widget manager to update the widget
        appWidgetManager?.updateAppWidget(appWidgetId, views)
    }
    companion object{
        var pesan = jobInterviewMessage()
        var ACTION_AUTO_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE"
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val widgetText = pesan.getMessage()
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.schedule_job_widget)
            views.setTextViewText(R.id.jabatan_pegawai_interview1, widgetText.posisiLoker)
            views.setTextViewText(R.id.nama_perusahaan_interview1, widgetText.namaPerusahaan)
            views.setImageViewResource(R.id.icon_perusahaan_interview1, R.drawable.ic_interview);
            views.setTextViewText(R.id.time_interview1, "Interview : "+widgetText.jadwal)

            views.setTextViewText(R.id.jabatan_pegawai_interview2, widgetText.posisiLoker)
            views.setTextViewText(R.id.time_interview2, widgetText.jadwal)

            views.setOnClickPendingIntent(R.id.card_interview1,getPendingIntentActivity(context))
            views.setOnClickPendingIntent(R.id.card_interview2,getPendingIntentActivity(context))
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        private fun getPendingIntentActivity(context: Context): PendingIntent? {
            var mIntent=Intent(context,MainActivity::class.java)
            return PendingIntent.getActivity(context,0,mIntent,0)
        }
    }
}

