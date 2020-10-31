package com.tuppersoft.domain.models.exceptions

sealed class Failure(message: String = "") : Exception(message) {

    class NoInternetConnectionException : Failure("There is no internet connection. Please try later")
    class OthersException(message: String) : Failure(message)
}
