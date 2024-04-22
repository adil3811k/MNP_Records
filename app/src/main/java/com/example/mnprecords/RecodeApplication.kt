package com.example.mnprecords

import android.app.Application
import com.example.mnprecords.DataLayer.AppContainer
import com.example.mnprecords.DataLayer.Container

class RecodeApplication:Application() {
    lateinit var container: Container
    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}