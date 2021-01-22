package com.example.moneytask.callBacks

import com.example.domain.models.Account
import com.example.domain.models.AccountRequest

interface CreateAccountCallBack : BaseCallBack {
    fun showLoader()
    fun dismiss()
    fun error(message: String)
    fun accountCreated(account: AccountRequest)
    fun createClick()
}