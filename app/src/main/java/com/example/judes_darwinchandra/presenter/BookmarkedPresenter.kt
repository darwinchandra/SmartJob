package com.example.judes_darwinchandra.presenter

import com.example.judes_darwinchandra.Interface.bookmarkInterface
import com.example.judes_darwinchandra.model.MyBookmarkedModel
//implement interface presenter
class BookmarkedPresenter(view: bookmarkInterface.DataView):
    bookmarkInterface.Presenter
{
    //var untuk menampung list
    private var listmodel=ArrayList<MyBookmarkedModel>()
    //var untuk view agar bisa di set data boookmarknya
    private var view: bookmarkInterface.DataView = view

    //fungsi untuk proses pembentukan data bookmarked  yang mereturn arraylist
    fun prosesadddata(): ArrayList<MyBookmarkedModel> {
        listmodel.add(MyBookmarkedModel("Finance Manager", "PT. Indah Kiat Pulp & Paper", "Kerawang - Jawa Barat", "https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"))
        listmodel.add(MyBookmarkedModel("CEO", "PT. Bukit Asam Tbk", "Setia Budi - Jakarta Selatan", "https://tambang.itm.ac.id/wp-content/uploads/2019/03/logo-pt-bukit-asam.jpg"))
        listmodel.add(MyBookmarkedModel("IT Support", "PT. Aneka Tambang Tbk", "Medan - Sumatera Utara", "https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png"))
        return listmodel
    }

    override fun getData() {
        //panggil fungsi proses pembentukan data
        prosesadddata()
        //seetelah terbentuk,  listmodel sudah ada data, sehingga sudah daapt di set pada view
        view.setdataBookmarktoView(listmodel)

    }

}