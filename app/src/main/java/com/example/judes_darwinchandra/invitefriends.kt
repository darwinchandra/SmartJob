package com.example.judes_darwinchandra

import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.judes_darwinchandra.Data.myContact
import com.example.judes_darwinchandra.adapter.postsAdapterInvite
import kotlinx.android.synthetic.main.activity_bookmarked.*
import kotlinx.android.synthetic.main.activity_invitefriends.*

class invitefriends : AppCompatActivity(),
    LoaderManager.LoaderCallbacks<Cursor>
{
    var DisplayName = ContactsContract.Contacts.DISPLAY_NAME
    var Number = ContactsContract.CommonDataKinds.Phone.NUMBER
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
        LoaderManager.getInstance(this).initLoader(1,null,this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        var MyContactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        var MyProjection = arrayOf(DisplayName,Number)
        return CursorLoader(this,MyContactUri,MyProjection,null,null,"$DisplayName ASC" )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        myListContact.clear()
        if(data != null){
            data.moveToFirst()
            while (!data.isAfterLast){
                myListContact.add(
                    myContact(
                        data.getString(data.getColumnIndex(DisplayName)),
                        data.getString(data.getColumnIndex(Number))
                    )
                )
                data.moveToNext()
            }
            var contactAdapter = postsAdapterInvite(myListContact)
            recyInvFriend.apply {
                layoutManager = LinearLayoutManager(this@invitefriends)
                adapter=contactAdapter
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        recyInvFriend.adapter?.notifyDataSetChanged()
    }
}