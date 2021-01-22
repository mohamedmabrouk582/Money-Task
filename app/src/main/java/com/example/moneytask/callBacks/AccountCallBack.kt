package com.example.moneytask.callBacks

import com.example.domain.models.Account

interface AccountCallBack : BaseCallBack {
    fun loadAccounts(accounts:ArrayList<Account>)
    fun createAccount(budget_id:String)
}