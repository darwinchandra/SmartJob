package com.example.judes_darwinchandra.presenter

import com.example.judes_darwinchandra.Data.BookmarkedData
import com.example.judes_darwinchandra.`interface`.bookmarkInterface
import com.example.judes_darwinchandra.model.MyBookmarkedModel

class BookmarkedPresenter(view: bookmarkInterface.DataView):bookmarkInterface.Presenter
{


    private var listmodel=ArrayList<MyBookmarkedModel>()
    private var view: bookmarkInterface.DataView = view

    init {
        }
    fun prosesadddata(): ArrayList<MyBookmarkedModel> {
        listmodel.add(MyBookmarkedModel("Finance Manager", "PT. Indah Kiat Pulp & Paper", "Kerawang - Jawa Barat", "https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"))
        listmodel.add(MyBookmarkedModel("CEO", "PT. Bukit Asam Tbk", "Setia Budi - Jakarta Selatan", "http://dewara.com/wp-content/uploads/2018/03/logo-bukit_asam.png"))
        listmodel.add(MyBookmarkedModel("IT Support", "PT. Aneka Tambang Tbk", "Medan - Sumatera Utara", "https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png"))
        return listmodel
    }
    override fun getData() {
        view.setdataBookmarktoView(listmodel)

    }

}