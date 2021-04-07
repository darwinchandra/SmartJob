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
    //Menympan apikey,country, dan kategori yang didapat dalam variabel AppID
    val AppID = "81c1172db5154c208b2e21d76b4dab56"
    val country = "id"
    val kategori = "business"
    //onStartJob akan dijalankan ketika scheduler telah aktif sesuai jadwal yang ditentukan
    override fun onStartJob(params: JobParameters?): Boolean {
        //menampilkan log bahwa job telah dimulai
        Log.w("Job","Mulai")
        //menjalankan fungsi get News untuk mendapatkan hasil
        getNews(params)
        //mengembalikan sebuah nilai Boolean, true menyatakan proses akan berjalan di thread yang berbeda
        return true
    }
    //onStopJo akan dijalankan ketika scheduler anda sedang berjalan tetapi berhenti ketika kondisinya tidak dipenuhi
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.w("Job","Berhenti")
        //Mengembalikan True, ketika service scheduler yang anda jalankan, gagal karena suatu proses sehingga anda perlu menjadwalkan kembali proses yang akan dilakukan
        return true
    }

    private fun getNews(JobParameters: JobParameters?) {
        //var client digunakan untuk membuat objek AsyncHttpClinet(), yang akan memproses penerimaan data dari Internet secara Async melalui AsyncHttpResponseHandler()
        var client = AsyncHttpClient()
        //Var url, merupakan url khusus yang akan ada gunakan ketika meminta data dari sebuah web yang memberikan services data
        var url = "https://newsapi.org/v2/top-headlines?country=$country&category=$kategori&apiKey=$AppID"
        //Val charset, digunakan untuk mengubah ArrayByte menjadi string melalui toString()
        val charset = Charsets.UTF_8
        //List untuk menampung data dari news
        val list=ArrayList<MyNewsData>()
        var handler = object : AsyncHttpResponseHandler(){
            //onSuccess() dijalankan ketika data dari web service berhasil diambil
            override fun onSuccess(
                //berisikan kode yang dikirmkan dari web service
                statusCode: Int,
                //berisikan list aksi pada HTTP request dan response yang biasana bernilai kosong atau beberapa pasangan Key value
                headers: Array<out Header>?,
                //berisikan data yang dikirimkan dari web service
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
                ////Ketika perintah jobFinished() dijalankan untuk memastikan job scheduler telah selesai atau belum
                //false menandakan bahwa job  tidak bermasalah dan job selesai
                jobFinished(JobParameters,false)
            }
            //onFailure() dijalankan ketika data dari web service gagal diambil
            override fun onFailure(
                //berisikan kode yang dikirmkan dari web service
                statusCode: Int,
                //berisikan list aksi pada HTTP request dan response yang biasana bernilai kosong atau beberapa pasangan Key value
                headers: Array<out Header>?,
                //berisikan data yang dikirimkan dari web service
                responseBody: ByteArray?,
                //berisikan Exeption yang mengakibatkan kegagalan permintaan data
                error: Throwable?
            ) {
                //Ketika perintah jobFinished() dijalankan untuk memastikan job scheduler telah selesai atau belum
                //true menandakan bahwa jika job bermasalah maka akan dijalankan ulang
                jobFinished(JobParameters,true)
            }
        }
        //Untuk mengambil data menggunakan AsyncHttpClinet(),menggunakan fungsi get() yang memerlukan 2 parameter, yaitu alamat pengambilan data (url) dan
        //objek AsyncHttpResponseHandler() yang menangani data ketika diterima respon dari web service
        client.get(url,handler)
    }
}