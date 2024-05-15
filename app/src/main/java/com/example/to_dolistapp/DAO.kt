package com.example.to_dolistapp

import androidx.room.*

@Dao
interface DAO {

    @Insert
    suspend fun insertData(entity: Entity)

    @Update
    suspend fun updateData(entity: Entity)

    @Delete
    suspend fun deleteData(entity: Entity)

    @Query("Delete from to_do")
    suspend fun deleteAll()

    @Query("Select * from to_do")
    suspend fun getData():List<CardInfo>
}