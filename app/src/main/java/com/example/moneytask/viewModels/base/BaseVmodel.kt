package com.example.moneytask.viewModels.base

import com.example.moneytask.callBacks.BaseCallBack

interface BaseVmodel<V:BaseCallBack>  {
    fun attachView(view:V)
}