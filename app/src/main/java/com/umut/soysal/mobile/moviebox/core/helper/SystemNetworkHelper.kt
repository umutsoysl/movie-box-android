package com.umut.soysal.mobile.moviebox.core.helper

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object SystemNetworkHelper {

    fun isNetworkConnection(application: Application): Boolean {
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}