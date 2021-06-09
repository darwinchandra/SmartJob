package com.example.judes_darwinchandra

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.example.judes_darwinchandra.Interface.bookmarkInterface
import com.example.judes_darwinchandra.model.MyBookmarkedModel
import com.example.judes_darwinchandra.adapter.postsAdapterBookmark
import com.example.judes_darwinchandra.presenter.BookmarkedPresenter
import kotlinx.android.synthetic.main.activity_bookmarked.*

//implementasi interface DataView
class BookmarkedActivity : AppCompatActivity(),
    bookmarkInterface.DataView {

    private var presenter : bookmarkInterface.Presenter? =null
    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarked) // view
        supportActionBar?.hide()
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.gray3)

        topAppBar_bookmarked.setNavigationOnClickListener {
            finish()
        }


        // inisialisasi presenter dengan view BookmarkedActivity
        presenter=BookmarkedPresenter(this)
        // fungsi initview
        initView()


        // init array list untuk tampung data
       /* val list=ArrayList<MyBookmarkedModel>()*/

        // add 3 data kedalam beserta img url
/*
        list.add(
            MyBookmarkedModel(
                "Finance Manager",
                "PT. Indah Kiat Pulp & Paper",
                "Kerawang - Jawa Barat",
                "https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"
            )
        )
        list.add(
            MyBookmarkedModel(
                "CEO",
                "PT. Bukit Asam Tbk",
                "Setia Budi - Jakarta Selatan",
                "https://tambang.itm.ac.id/wp-content/uploads/2019/03/logo-pt-bukit-asam.jpg"
            )
        )
        list.add(
            MyBookmarkedModel(
                "IT Support",
                "PT. Aneka Tambang Tbk",
                "Medan - Sumatera Utara",
                "https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png"
            )
        )
*/



    }

    @SuppressLint("WrongConstant")
    private fun initView() {
        // pemanggilan getdata() sehingga nilai "value" pada setdataBookmarktoView telah berisi data
        // yang diberikan pada presenter
        presenter!!.getData()
        // layout manager untuk recycler
        recyclerView_bookmarked.layoutManager= LinearLayoutManager(recyclerView_bookmarked.context, OrientationHelper.VERTICAL,false)

    }
    // overide yang muncul karena implement Interface ViewData
    override fun setdataBookmarktoView(value: ArrayList<MyBookmarkedModel>) {
        //membuat adapter recyclerview dengan arraylist value
        recyclerView_bookmarked.adapter=
            postsAdapterBookmark(
                value
            )
    }
}