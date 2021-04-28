package com.example.judes_darwinchandra.`interface`

import com.example.judes_darwinchandra.model.MyBookmarkedModel

interface bookmarkInterface{

        interface DataView {
                fun setdataBookmarktoView(value: ArrayList<MyBookmarkedModel>)
        }
        interface Presenter {
                fun getData()
        }
}