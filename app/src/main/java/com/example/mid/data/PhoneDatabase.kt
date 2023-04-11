package com.example.mid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Phone::class], version = 1)
abstract class PhoneDatabase : RoomDatabase() {

    abstract fun phoneDao(): PhoneDao
    companion object {
        @Volatile
        private var INSTANCE: PhoneDatabase? = null

        fun getDatabase(context: Context): PhoneDatabase {
            val tempInstance= INSTANCE
            if (tempInstance!=null) return  tempInstance
            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,PhoneDatabase::class.java,"phone_database"
                ).allowMainThreadQueries().build()
                INSTANCE=instance
                return instance

            }
        }


    }
}