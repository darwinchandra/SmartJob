package com.example.judes_darwinchandra.presenter

import com.example.judes_darwinchandra.Data.BookmarkedData
import com.example.judes_darwinchandra.`interface`.bookmarkInterface
import com.example.judes_darwinchandra.model.MyBookmarkedModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkedPresenterTest {
    private var view :bookmarkInterface.DataView= mock(bookmarkInterface.DataView::class.java)
    private var presenter=BookmarkedPresenter(view)

    @Test
    fun getData() {
        var listBookmarkedtesting=presenter.prosesadddata()
        var size= listBookmarkedtesting.size.toDouble()
        assertEquals(3.0,size,00.1)

    }
}