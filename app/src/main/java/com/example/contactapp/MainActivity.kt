package com.example.contactapp

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
import androidx.appcompat.view.ActionMode

import listview.ListAdapter
import listview.UserItem

class MainActivity : AppCompatActivity() {
    var actionMode: ActionMode? = null
    private var userItems = arrayListOf<UserItem?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //test with list view ok but don't work with recycler view? ?

        val listView = findViewById<ListView>(R.id.list_view)
        listView.adapter = ListAdapter(this, userItems)
        addUser()
        // Context menu
        registerForContextMenu(listView)


    }
    private fun addUser(){
        val newUsers = listOf(
            UserItem("User", R.drawable.avt),
            UserItem("User 1", R.drawable.avt1),
            UserItem("User 2", R.drawable.avt2),
            UserItem("User 3", R.drawable.avt3),
            UserItem("User 4", R.drawable.avt4),
            UserItem("User 5", R.drawable.avt5),
            UserItem("User 6", R.drawable.avt6),
            UserItem("User", R.drawable.avt),
            UserItem("User 1", R.drawable.avt1),
            UserItem("User 2", R.drawable.avt2),
            UserItem("User 3", R.drawable.avt3),
            UserItem("User 4", R.drawable.avt4),
            UserItem("User 5", R.drawable.avt5),
            UserItem("User 6", R.drawable.avt6),
            UserItem("User", R.drawable.avt),
            UserItem("User 1", R.drawable.avt1),
            UserItem("User 2", R.drawable.avt2),
            UserItem("User 3", R.drawable.avt3),
            UserItem("User 4", R.drawable.avt4),
            UserItem("User 5", R.drawable.avt5),
            UserItem("User 6", R.drawable.avt6),


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
        return when (item.itemId) {
            R.id.menu_send_email -> {
                Log.v("TAG", "Send a email")
                true
            }

            R.id.menu_sms -> {
                Log.v("TAG", "Send a SMS")
                true
            }

            R.id.menu_call -> {
                Log.v("TAG", "Call someone")
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

}