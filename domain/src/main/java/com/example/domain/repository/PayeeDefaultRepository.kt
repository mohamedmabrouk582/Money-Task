package com.example.domain.repository

import com.example.domain.response.MoneyResponse
import com.example.domain.response.PayeeResponse
import com.example.domain.response.TransactionResponse
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface PayeeDefaultRepository : BudgetDefaultRepository{
    suspend fun getBudgetPayees(budget_id:String) : Flow<Result<MoneyResponse<PayeeResponse>>>
    suspend fun getPayeeTransactions(budget_id: String,payee_id:String)  : Flow<Result<MoneyResponse<TransactionResponse>>>
}