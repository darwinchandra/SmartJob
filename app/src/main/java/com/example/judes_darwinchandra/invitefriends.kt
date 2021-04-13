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
    LoaderManager.LoaderCallbacks<Cursor>
{
    var DisplayName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
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
        SearchViewfriends.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val bundle = Bundle()
                bundle.putString("select", "$DisplayName LIKE ?")
                bundle.putStringArray("selectArg",Array(1){"%$newText%"})
                LoaderManager.getInstance(this@invitefriends).restartLoader(1,bundle,this@invitefriends)
                return false
            }

        } )
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        var MyContactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        var MyProjection = arrayOf(DisplayName,Number)
        var selection = args?.getString("select")
        var selectionArgs = args?.getStringArray("selectArg")
        return CursorLoader(this,MyContactUri,MyProjection,selection,selectionArgs,"$DisplayName ASC" )
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