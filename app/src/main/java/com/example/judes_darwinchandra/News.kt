package com.example.judes_darwinchandra

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.judes_darwinchandra.Data.MyNewsData
import com.example.judes_darwinchandra.adapter.postsAdapterNews
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class News: JobService() {
    val AppID = "81c1172db5154c208b2e21d76b4dab56"
    val country = "id"
    val kategori = "business"

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.w("Job","Mulai")
        getNews(params)
        return true
    }
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.w("Job","Berhenti")
        return true
    }

    private fun getNews(JobParameters: JobParameters?) {
        var client = AsyncHttpClient()
        var url = "https://newsapi.org/v2/top-headlines?country=$country&category=$kategori&apiKey=$AppID"
        val charset = Charsets.UTF_8
        val list=ArrayList<MyNewsData>()
        var handler = object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                var result = responseBody?.toString(charset) ?: "Kosong"
                val obj=JSONObject(result)
                val jsonArray= obj.getJSONArray("articles")
                var i = 0
                while (i<jsonArray.length()){
                    val jsonObject=jsonArray.getJSONObject(i)
                    list.add(
                        MyNewsData(
                            jsonObject.getString("author"),
                            jsonObject.getString("title"),
                            jsonObject.getString("description"),
                            jsonObject.getString("url"),
                            jsonObject.getString("urlToImage"),
                            jsonObject.getString("publishedAt"),
                            jsonObject.getString("content")
                        )
                    )
                    i++
                }
                var intent =  Intent(ACTION_NEWS)
                intent.putExtra(EXTRA_NEWS,list)
                sendBroadcast(intent)

                jobFinished(JobParameters,false)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                jobFinished(JobParameters,true)
            }
        }
        client.get(url,handler)
    }
}