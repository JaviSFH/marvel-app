package com.tuppersoft.marvel.core.platform

import android.content.Context
import com.tuppersoft.data.extension.isNetworkAvailable
import com.tuppersoft.data.functions.CheckInternet
import com.tuppersoft.domain.models.exceptions.Failure
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CheckInternetConnectionImpl @Inject constructor(@ApplicationContext val context: Context) : CheckInternet {

    override fun checkInternetConnection() {
        if (!context.isNetworkAvailable()) {
            throw Failure.NoInternetConnectionException()
        }
    }
}
