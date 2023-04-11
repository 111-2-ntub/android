package com.example.mid.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface PhoneDao {
    @Query("SELECT * FROM phone")
    fun getAll(): LiveData<List<Phone>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhone(vararg users: Phone)

    @Delete
    fun delete(user: Phone)
}