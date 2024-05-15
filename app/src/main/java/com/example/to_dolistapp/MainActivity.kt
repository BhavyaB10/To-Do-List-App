package com.example.to_dolistapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var addButton: FloatingActionButton
    private lateinit var deleteAll: Button
    private lateinit var recyclerView: RecyclerView

    private lateinit var dataBase: DataBase
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        dataBase = Room.databaseBuilder(
            applicationContext, DataBase::class.java, "To_Do"
        ).build()

        recyclerView = findViewById(R.id.recycler_view)
        deleteAll = findViewById(R.id.deleteAllButton)
        addButton = findViewById(R.id.floatingActionButton)

        //Set Recycler View
        recyclerView.adapter = ToDoAdapter(dataObject.getAllData())
        recyclerView.layoutManager = LinearLayoutManager(this)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer)
        val navigationView: NavigationView = findViewById(R.id.nav_view)

        addButton.setOnClickListener {
            val intent = Intent(this, CreateData::class.java)
            startActivity(intent)
        }

        deleteAll.setOnClickListener {
            dataObject.deleteAll()

            GlobalScope.launch {
                dataBase.dao().deleteAll()
            }
            setRecycler()
        }
        setRecycler()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {

            return true

        }

        return super.onOptionsItemSelected(item)
    }

    private fun setRecycler() {

        recyclerView.adapter = ToDoAdapter(dataObject.getAllData())
        recyclerView.layoutManager = LinearLayoutManager(this)

    }


}