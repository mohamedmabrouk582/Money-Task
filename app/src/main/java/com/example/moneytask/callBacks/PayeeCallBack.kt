package com.example.moneytask.callBacks

import com.example.domain.models.Budget
import com.example.domain.models.Payee
import com.example.domain.models.Transaction

interface PayeeCallBack : BaseCallBack {
    fun loadSpinners(budgetPayees:HashMap<Budget,ArrayList<Payee>>)
    fun loadTransactions(transactions:ArrayList<Transaction>)
    fun showLoader()
    fun dismiss()
    fun error(error:String)
    fun createClick()
}