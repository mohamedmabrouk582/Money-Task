package com.example.domain.useCases

import com.example.domain.models.Account
import com.example.domain.models.Budget
import com.example.domain.models.Payee
import com.example.domain.models.Transaction
import com.example.domain.response.*
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.flow

object UseCasesUtils {
    var budgets= arrayListOf(Budget("1","budget1"),Budget("2","budget2"),Budget("3","budget3"))
    var accounts= arrayListOf(Account("1","account1"),Account("2","account2"),Account("3","account3"))
    var payees= arrayListOf(Payee("1","payee1"),Payee("2","payee2"),Payee("3","payee3"))
    var transactions= arrayListOf(Transaction("1"),Transaction("2"),Transaction("3"))
    fun getBudgets() = flow {
        emit(Result.OnSuccess(MoneyResponse(BudgetResponse(budgets))))
    }

    fun getAccounts()= flow {
        emit(Result.OnSuccess(MoneyResponse(AccountResponse(accounts))))
    }

    fun getPayees()= flow {
        emit(Result.OnSuccess(MoneyResponse(PayeeResponse(payees))))
    }

    fun getTransactions()= flow{
        emit(Result.OnSuccess(MoneyResponse(TransactionResponse(transactions))))
    }
}