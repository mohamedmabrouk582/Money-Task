package com.example.moneytask.viewModels.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.example.moneytask.callBacks.BaseCallBack
import com.mabrouk.loaderlib.RetryCallBack

open class BaseViewModel<V:BaseCallBack>(application: Application) : AndroidViewModel(application)  , BaseVmodel<V> {
 var loader : ObservableBoolean = ObservableBoolean()
 var error : ObservableField<String> = ObservableField()
    val retryCallBack = object : RetryCallBack{
        override fun onRetry() {
            retry()
        }
    }
 lateinit var view:V
    override fun attachView(view: V) {
        this.view=view
    }

    open fun retry(){}

}