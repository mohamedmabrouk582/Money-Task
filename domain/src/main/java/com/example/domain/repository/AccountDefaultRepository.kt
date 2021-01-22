package com.example.domain.repository

import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.domain.response.AccountResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface AccountDefaultRepository {
    suspend fun getBudgetAccounts(budget_id:String) : Flow<Result<MoneyResponse<AccountResponse>>>
    suspend fun createNewAccount(budget_id:String,account:AccountRequest) : Flow<Result<MoneyResponse<AccountRequest>>>
}