package com.umut.soysal.mobile.moviebox.core.base

import dagger.hilt.android.scopes.ViewModelScoped
import android.R
import javax.inject.Inject


@ViewModelScoped
abstract class BaseUseCase {

    private lateinit var mRequestValues: BaseRequest

    protected abstract fun executeUseCase(requestValues: BaseRequest?)
    protected abstract fun cancelUseCase()

    open fun run() {
        executeUseCase(mRequestValues)
    }

    open fun cancel() {
        cancelUseCase()
    }

}