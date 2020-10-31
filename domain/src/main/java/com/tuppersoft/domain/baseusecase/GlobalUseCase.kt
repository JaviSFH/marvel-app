package com.tuppersoft.domain.baseusecase

/**
 * Created by Raúl Rodríguez Concepción on 17/08/2020.
 * Talento Mobile
 * raulrcs@gmail.com
 */
abstract class GlobalUseCase<out Type, in Params> where Type : Any? {

    protected abstract suspend fun run(params: Params): Type

    suspend fun invoke(params: Params): Type {
        return run(params)
    }

    class None
}
