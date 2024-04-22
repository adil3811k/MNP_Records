package com.example.mnprecords.DataLayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MNPRecode::class], version = 1, exportSchema = false)
abstract class AppDataBase :RoomDatabase(){
    abstract fun getDao():RecodeDao


    companion object{
        @Volatile
        var instent:AppDataBase? = null

        fun getDataBase(context: Context):AppDataBase{
            return instent ?: synchronized(this){
                Room.databaseBuilder(context,AppDataBase::class.java,"AppDataBase")
                    .build()
                    .also { instent=it }
            }
        }
    }
}