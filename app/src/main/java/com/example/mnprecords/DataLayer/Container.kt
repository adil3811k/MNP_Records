package com.example.mnprecords.DataLayer

import android.content.Context

interface Container {
    val repository : Repository
}

class AppContainer(private val context: Context):Container{
    override val repository: Repository by lazy {
        AppRepository(AppDataBase.getDataBase(context).getDao())
    }

}