package com.example.to_dolistapp


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateData : AppCompatActivity() {
    private lateinit var dataBase: DataBase
    private lateinit var editTextTitle:EditText
    private lateinit var editTextPriority:EditText
    private lateinit var deleteButton:Button
    private lateinit var updateButton:Button
    private lateinit var remember: Button

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)

        dataBase= Room.databaseBuilder(
            applicationContext,DataBase::class.java,"To_Do"
        ).build()


        editTextTitle=findViewById(R.id.updateTitle)
        editTextPriority=findViewById(R.id.updatePriority)
        deleteButton=findViewById(R.id.deleteButton)
        updateButton=findViewById(R.id.updateButton)
        remember=findViewById(R.id.taskCompleted)

        val position=intent.getIntExtra("id",-1)

        if(position!=-1){
            val title=dataObject.getData(position).title
            val priority=dataObject.getData(position).priority

            editTextTitle.setText(title)
            editTextPriority.setText(priority)

            deleteButton.setOnClickListener {
                dataObject.deleteParticularData(position)
                GlobalScope.launch {
                    dataBase.dao().deleteData(Entity(position+1,title,priority))
                }
                myIntent()
            }


            updateButton.setOnClickListener {
                dataObject.updateData(position,title,priority)

                GlobalScope.launch {
                    dataBase.dao().updateData(Entity(position+1,editTextTitle.text.toString(),
                        editTextPriority.text.toString()))
                }
                myIntent()
            }

        }

    }

//    override fun onPause() {
//        super.onPause()
//
//        saveData()
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        retrieveData()
//    }



    private fun myIntent(){
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

//    fun saveData(){
//        sharedPreferences=this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
//
//       isChecked=remember.isChecked
//        val editor=sharedPreferences.edit()
//
//        editor.putBoolean("key_checked",isChecked!!)
//        editor.apply()
//        Toast.makeText(this@Update_Data,"Task is completed",Toast.LENGTH_SHORT).show()
//
//    }
//
//    fun retrieveData(){
//        sharedPreferences=this.getSharedPreferences("saveData", Context.MODE_PRIVATE)
//
//        isChecked=sharedPreferences.getBoolean("key_checked",false)
//
//        remember.isChecked=isChecked!!
//    }
}