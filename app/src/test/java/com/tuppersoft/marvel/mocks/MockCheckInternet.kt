package com.tuppersoft.marvel.mocks

import com.tuppersoft.data.functions.CheckInternet
import com.tuppersoft.domain.models.exceptions.Failure

class MockCheckInternetTrue : CheckInternet {

    override fun checkInternetConnection() {

        //nothing to do Internet is true
    }
}

class MockCheckInternetFalse : CheckInternet {

    override fun checkInternetConnection() {
        // send exception internet is not available
        throw Failure.NoInternetConnectionException()
    }
}
