package com.example.judes_darwinchandra.presenter

import com.example.judes_darwinchandra.Interface.bookmarkInterface
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkedPresenterTest {
    private var view : bookmarkInterface.DataView= mock(
        bookmarkInterface.DataView::class.java)
    private var presenter=BookmarkedPresenter(view)

    @Test
    fun getData() {
        //membuat var dengan memanfaatkan  fungsi prosesadddata agar var tersebut memiliki nilai dari arraylist
        var listBookmarkedtesting=presenter.prosesadddata()
        //pengambilan size dari array recycler yang telah dibuat
        var size= listBookmarkedtesting.size.toDouble()
        // pengujian banyak data yang ada pada arraylist tersebut
        assertEquals(3.0,size,0.1)

    }
}