package com.example.domain.repository

import com.example.domain.models.Transaction
import com.example.domain.models.TransactionRequest
import com.example.domain.response.AccountResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface TransactionDefaultRepository {
    suspend fun getBudgetAccounts(budget_id:String) : Flow<Result<MoneyResponse<AccountResponse>>>
    suspend fun createTransAction(budget_id:String,transaction: TransactionRequest) : Flow<Result<MoneyResponse<TransactionRequest>>>
}