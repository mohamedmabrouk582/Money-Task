package com.example.moneytask.callBacks

import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.domain.models.Transaction

interface CreateTransactionCallBack : BaseCallBack {
    fun showLoader()
    fun dismiss()
    fun error(message: String)
    fun loadAccounts(accounts:ArrayList<Account>)
    fun accountTransaction(transaction: Transaction)
    fun createClick()
    fun datePicker()
}