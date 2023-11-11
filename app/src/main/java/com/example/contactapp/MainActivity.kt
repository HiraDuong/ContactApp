package com.example.contactapp

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.view.ActionMode

import listview.ListAdapter
import listview.UserItem
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var actionMode: ActionMode? = null
    var userItems = arrayListOf<UserItem?>()

    lateinit var listView: ListView
    lateinit var listAdapter: ListAdapter

    lateinit var searchView: SearchView
    lateinit var searchList : ArrayList<UserItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //test with list view ok but don't work with recycler view? ?


        listView = findViewById<ListView>(R.id.list_view)
        listAdapter = ListAdapter(this, userItems)
        listView.adapter = listAdapter
        addUser()
        // Context menu
        registerForContextMenu(listView)
        // item Click Listener


        val intent = Intent(this, UserActivity::class.java)

        listView.setOnItemClickListener{parent, view, position, id ->
            val selectedItem = userItems[position]
            intent.putExtra("id",selectedItem?.id)
            intent.putExtra("name",selectedItem?.name)
            intent.putExtra("avt",selectedItem?.avt)
            intent.putExtra("phone",selectedItem?.phone)
            intent.putExtra("email",selectedItem?.email)

            startActivity(intent)
        }
    }
    private fun addUser(){
        val newUsers = listOf(
            UserItem("User 0", R.drawable.avt,"0000000","hira0@gmail.com","00123456789"),
            UserItem("User 1", R.drawable.avt1,"0000001","hira1@gmail.com","01234567890"),
            UserItem("User 2", R.drawable.avt2,"0000002","hira2@gmail.com","02345678901"),
            UserItem("User 3", R.drawable.avt3,"0000003","hira3@gmail.com","03456789012"),
            UserItem("User 4", R.drawable.avt4,"0000004","hira4@gmail.com","04567890123"),
            UserItem("User 5", R.drawable.avt5,"0000005","hira5@gmail.com","05678901234"),
            UserItem("User 6", R.drawable.avt6,"0000006","hira6@gmail.com","06789012345"),

            UserItem("User 01", R.drawable.avt,"0000007","hira00@gmail.com","10123456789"),
            UserItem("User 11", R.drawable.avt1,"0000008","hira11@gmail.com","11234567890"),
            UserItem("User 22", R.drawable.avt2,"0000009","hira22@gmail.com","22345678901"),
            UserItem("User 33", R.drawable.avt3,"1000001","hira33@gmail.com","33456789012"),
            UserItem("User 44", R.drawable.avt4,"1000002","hira44@gmail.com","44567890123"),
            UserItem("User 55", R.drawable.avt5,"1000003","hira55@gmail.com","55678901234"),
            UserItem("User 66", R.drawable.avt6,"1000004","hira66@gmail.com","66789012345"),

            UserItem("User 000", R.drawable.avt,"2000005","hira000@gmail.com","20123456789"),
            UserItem("User 111", R.drawable.avt1,"2000006","hira111@gmail.com","21234567890"),
            UserItem("User 222", R.drawable.avt2,"2000007","hira222@gmail.com","22345678901"),
            UserItem("User 333", R.drawable.avt3,"2000008","hira333@gmail.com","33456789012"),
            UserItem("User 444", R.drawable.avt4,"2000009","hira444@gmail.com","44567890123"),
            UserItem("User 555", R.drawable.avt5,"3000001","hira555@gmail.com","55678901234"),
            UserItem("User 666", R.drawable.avt6,"3000002","hira666@gmail.com","66789012345"),



        )

        for (user in newUsers) {
            userItems.add(user)
        }

    }


    //context Menu
    override fun onCreateContextMenu(
        menu: ContextMenu, v: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        val seletedItem = userItems[position]
        return when (item.itemId) {
            R.id.menu_send_email -> {

                val intent = Intent(Intent.ACTION_SEND).apply {

                    type = "text/plain"
                    if (seletedItem != null) {
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(seletedItem.email))
                    }
                    putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                    putExtra(Intent.EXTRA_TEXT, "Email message text")
                }

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                    Log.v("TAG", "Send a email ${position.toString()}")
                } else {
                    Log.v("TAG", "No email app found!")
                }

                true
            }

            R.id.menu_sms -> {

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:${seletedItem?.phone}"))
                    intent.putExtra("sms_body", "Bé Dương, I love you chùn chụt")
                    startActivity(intent)

                Log.v("TAG", "Send a SMS  ${position.toString()}")
                true
            }

            R.id.menu_call -> {
                Log.v("TAG", "Call someone  ${position.toString()}")
                val callIntent: Intent = Uri.parse("tel:${seletedItem?.phone}").let { number ->
                    Intent(Intent.ACTION_DIAL, number)
                }
                startActivity(callIntent)
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }


    private val actionModeCallback = object : ActionMode.Callback {
        // Called when the action mode is created. startActionMode() is called.
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            // Inflate a menu resource providing context menu items.
            val inflater: MenuInflater = mode.menuInflater
            inflater.inflate(R.menu.context_menu, menu)
            return true
        }

        // Called each time the action mode is shown. Always called after
        // onCreateActionMode, and might be called multiple times if the mode
        // is invalidated.
        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item.
        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.menu_call -> {
                    Log.v("TAG", "call someone")
                    mode.finish() // Action picked, so close the CAB.
                    true
                }

                R.id.menu_sms -> {
                    Log.v("TAG", "SMS someone")
                    mode.finish() // Action picked, so close the CAB.
                    true
                }

                R.id.menu_send_email -> {
                    Log.v("TAG", "send email someone")
                    mode.finish() // Action picked, so close the CAB.
                    true
                }

                else -> false
            }
        }

        // Called when the user exits the action mode.
        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
        }
    }


    // Option Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        // search
        val search = menu.findItem(R.id.menu_search)
        searchView = search?.actionView as SearchView
        searchList = arrayListOf<UserItem>()

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("TAG","query change")
                listAdapter.filter.filter(newText)
                return true


            }

        })


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_select -> {
                Log.v("TAG","Select Clicked")
                true
            }
            R.id.menu_delete -> {
                Log.v("TAG","Delete Clicked")
                true
            }
            R.id.menu_add -> {
                Log.v("TAG","Add Clicked")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}