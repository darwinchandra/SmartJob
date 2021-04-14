package com.example.judes_darwinchandra

import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.WindowManager
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.judes_darwinchandra.Data.myContact
import com.example.judes_darwinchandra.adapter.postsAdapterInvite
import kotlinx.android.synthetic.main.activity_invitefriends.*

class invitefriends : AppCompatActivity(),
    //Metode Callback yang mengatur siklus hidup Loader, yang terdiri atas 3 metode utama onCreateLoader(), onLoadFinished(), dan
    //onLoaderReset()
    LoaderManager.LoaderCallbacks<Cursor>
{
    //DISPLAY_NAME dan NUMBER merupakan variable yang akan digunakan untuk
    //menentukan attribute yang perlu diambil pada CursorLoader
    var DisplayName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
    var Number = ContactsContract.CommonDataKinds.Phone.NUMBER

    //myListContact Untuk memasukkan contact kedalam Mutable list
    var myListContact : MutableList<myContact> = ArrayList()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitefriends)

        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)
        topAppBar_invitefriends.setNavigationOnClickListener {
            finish()
        }

        //Untuk menjalankan loader kita mmerlukan intLoader yang terdiri dari id,objek bundle dan callback
        //getInstance(this) menyatakan dari mana context yang menjalankan loader
        LoaderManager.getInstance(this).initLoader(1,null,this)


        // Untuk menghandle perubahan dan submit dari Searchview
        SearchViewfriends.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            // yang dilakukan saat submit
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            // saaat isi text dari searchview berubah
            override fun onQueryTextChange(newText: String?): Boolean {
                // membuat bundle untuk dikirimkan
                // kemudian masukkan data selection dan selectionArgs ke dalam bundle yang akan dipakai nantinya dalam proses onCreateLoader
                // SelctionArgs memakai string yang diinput ketika text change
                val bundle = Bundle()
                bundle.putString("select", "$DisplayName LIKE ?")
                bundle.putStringArray("selectArg",Array(1){"%$newText%"})
                // restartLoader untuk meload ulang loadernya tiap ada perubahan pada searchview .
                // tapi kali ini sudah ada bundle yang dikirim untuk data selection dan SelectionArgs
                // guna untuk filter contact yang akan di tampilkan
                LoaderManager.getInstance(this@invitefriends).restartLoader(1,bundle,this@invitefriends)
                return false
            }

        } )
    }
        //InitLoader akan menjalankan onCreateLoader() yang akan mengembalikan objek CursorLoader()
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        //mentukan reference uri (alamat) pembacaan data, uri yang digunakan adalah uri dari kontak phone kita
        var MyContactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        //Menentukan atribut kolom yang akan di pilih
        var MyProjection = arrayOf(DisplayName,Number)
        //Menentukan kondisi data yang akan diambil
        var selection = args?.getString("select")
        //Nilai argumen yang akan dipilih
        var selectionArgs = args?.getStringArray("selectArg")
        //CursorLoader berfungsi untuk membaca data secara ascyn
        return CursorLoader(this,MyContactUri,MyProjection,selection,selectionArgs,"$DisplayName ASC" )
    }

    //onLoadFinished() dijalankan setelah pembacaan selesai.
    //Hasil pembacaan query akan disimpan pada Cursor pada val loader, yang dapat anda baca menggunakan data
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        //untuk memastikan list kontak kosong
        myListContact.clear()
        //data sebagai pointer dalam membaca data
        if(data != null){
            //data.moveToFirst() digunakan supaya kursor pindah pada posisi pertama
            data.moveToFirst()
            //data.isAfterLast() digunakan untuk mengcek apakah cursor telah berada tepat sebelum baris terakhir
            while (!data.isAfterLast){
                ////Data yang diambil melalui query ini akan dimasukkan sebagai objek list yang akan dijadikan sebagai data untuk recycleView
                myListContact.add(
                    myContact(
                        //Fungsi dari data.getString(data.getColumnIndex()) digunakan untuk mengambil nilai string didalam baris table
                        // hasil query berdasarkan nama attribute yang digunakan
                        data.getString(data.getColumnIndex(DisplayName)),
                        data.getString(data.getColumnIndex(Number))
                    )
                )
                //data.moveToNext() digunakan untuk melanjukan kursor pada baris berikutnya
                data.moveToNext()
            }
            //Membuat adapter untuk contact
            var contactAdapter = postsAdapterInvite(this,myListContact)
            //memasukkan data yang telah didapat pada recycle view dengan adapter
            recyInvFriend.apply {
                //layout manager untuk untuk mengatur layout
                layoutManager = LinearLayoutManager(this@invitefriends)
                //adapter pada contact
                adapter=contactAdapter
            }


        }
    }
    //onLoaderReset(loader: Loader<Cursor>) digunakan ketika Loader yang telah dibentuk, dipanggil ulang
    // akibat penggunaan fungsi (destroyLoader(int) Atau ketika activity atau fragment tempat loader dihancurkan,
    // yang akan menyebabkan data tidak tersedia
    override fun onLoaderReset(loader: Loader<Cursor>) {
        recyInvFriend.adapter?.notifyDataSetChanged()
    }
}