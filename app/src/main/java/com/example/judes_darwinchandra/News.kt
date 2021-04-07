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
                // membuat jsonObject untuk menampung result
                val obj=JSONObject(result)
                // membuat JSONArray untuk menampung isi dari array articles yang ada di result.
                val jsonArray= obj.getJSONArray("articles")
                //inisialisasi i
                var i = 0
                //perulangan sebanyak jumlah jsonArray kali
                while (i<jsonArray.length()){
                    //get object dari tiap array ke i, untuk mendapatkan data dari arrayke i
                    val jsonObject=jsonArray.getJSONObject(i)
                    // kemudian masukkkan ke arraylist
                    list.add(
                        //proses add sesuai dengan urutan class data MyNewsData
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
                    //increment i
                    i++
                }
                //membuat send broadcast dengan action berupa konstanta ACTION_NEWS
                var intent =  Intent(ACTION_NEWS)
                //untuk mengirimkan extra berupa arraylist<MyNewsData> yang sudah ada datanya ke activity lain menggunakan broadcast
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