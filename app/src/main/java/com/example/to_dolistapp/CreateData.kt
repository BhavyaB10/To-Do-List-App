package com.example.to_dolistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateData : AppCompatActivity() {

    private lateinit var dataBase: DataBase
    private lateinit var editTextTitle : EditText
    private lateinit var editTextPriority:EditText
    private lateinit var isChecked : CheckBox
    private lateinit var saveButton:Button
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_data)

        dataBase= Room.databaseBuilder(
            applicationContext,DataBase::class.java,"To_Do"
        ).build()

        editTextTitle=findViewById(R.id.editTexttitle)
        editTextPriority=findViewById(R.id.editTextPriority)

        saveButton=findViewById(R.id.buttonSave)

        saveButton.setOnClickListener {

            if(editTextTitle.text.toString().trim{it <=' '}.isNotEmpty() && editTextPriority.text.toString().trim{it<= ' '}.isNotEmpty()){
                val title=editTextTitle.text.toString()
                val priority=editTextPriority.text.toString()

                dataObject.setData(title,priority)

                GlobalScope.launch{
                    dataBase.dao().insertData(Entity(0,title,priority))
                }
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }

        }

    }
}