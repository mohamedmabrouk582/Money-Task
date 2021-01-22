package com.example.moneytask.callBacks

import com.example.domain.models.Account

interface AccountListener {
    fun addAccount(account:Account)
}