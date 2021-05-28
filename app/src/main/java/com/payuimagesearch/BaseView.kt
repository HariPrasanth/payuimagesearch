package com.payuimagesearch

interface BaseView{
    fun showProgress()

    fun showServerError(errorString : String)

    fun showUserError(errorString : String)

    fun hideProgress()

    fun hideError()

    fun unauthorizedQuitApp()

    fun objectNotFound(message: String)
}