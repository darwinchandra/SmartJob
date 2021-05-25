package com.example.judes_darwinchandra

import com.example.judes_darwinchandra.model.MyBookmarkedModel
// membuat interface untuk view data dan juga untuk presenter
interface bookmarkInterface{

        interface DataView {
                //untuk set data berupa arraylist
                fun setdataBookmarktoView(value: ArrayList<MyBookmarkedModel>);
        }
        interface Presenter {
                fun getData();
        }
}