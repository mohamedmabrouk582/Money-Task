package com.example.moneytask.callBacks

import com.example.domain.models.Budget

interface BudgetsCallBack : BaseCallBack {
    fun loadBudgets(budgets:ArrayList<Budget>)
}