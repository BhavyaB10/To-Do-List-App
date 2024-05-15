package com.example.to_dolistapp

object dataObject {
    var listdata = mutableListOf<CardInfo>()

    fun  setData(title:String,priority:String){
        listdata.add(CardInfo(title,priority))
    }

    fun getAllData():List<CardInfo>{
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(position: Int):CardInfo{
        return listdata[position]
    }

    fun deleteParticularData(position: Int){
        listdata.removeAt(position)
    }

    fun updateData(pos: Int, title:String, priority: String){

        listdata[pos].title=title
        listdata[pos].priority=priority
    }
}
