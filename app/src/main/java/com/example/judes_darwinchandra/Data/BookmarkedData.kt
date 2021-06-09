package com.example.judes_darwinchandra.Data

import com.example.judes_darwinchandra.model.MyBookmarkedModel

class BookmarkedData {
    fun createdata(): ArrayList<MyBookmarkedModel> {
    val mybookmarkedList= arrayListOf<MyBookmarkedModel>(
        MyBookmarkedModel("Finance Manager", "PT. Indah Kiat Pulp & Paper", "Kerawang - Jawa Barat", "https://ceowatermandate.org/wp-content/uploads/2021/01/indah-kiat-logo.jpg"),
        MyBookmarkedModel("CEO", "PT. Bukit Asam Tbk", "Setia Budi - Jakarta Selatan", "https://tambang.itm.ac.id/wp-content/uploads/2019/03/logo-pt-bukit-asam.jpg"),
        MyBookmarkedModel("IT Support", "PT. Aneka Tambang Tbk", "Medan - Sumatera Utara", "https://www.dreamcareerbuilder.com/uploads/employers/5754_ECLB.png"))
    return mybookmarkedList
    }
}