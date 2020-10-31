package com.tuppersoft.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.tuppersoft.data.datasource.MarvelServices
import com.tuppersoft.domain.models.Data
import com.tuppersoft.domain.models.exceptions.Failure
import com.tuppersoft.domain.repository.MarvelRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val api: MarvelServices, @ApplicationContext private val context: Context) : MarvelRepository {

    override suspend fun getCharacters(): Flow<Data> {
        return flow {

            checkInternetConnection()

            try {
                val response = api.getCharacters()

                if (response.isSuccessful) {
                    response.body()
                } else {
                    throw Failure.OthersException("")
                }

                emit(response.body()?.data ?: Data())

            } catch (e: Exception) {
                throw Failure.OthersException(e.message.orEmpty())
            }
        }
    }

    private fun checkInternetConnection() {
        if (!context.isNetworkAvailable()) {
            throw Failure.NoInternetConnectionException()
        }
    }
}

private fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        return nwInfo.isConnected
    }
}
