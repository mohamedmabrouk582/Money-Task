package com.example.data.repository

import android.content.Context
import com.example.data.api.MoneyApi
import com.example.data.utils.executeCall
import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.domain.repository.AccountDefaultRepository
import com.example.domain.response.AccountResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepository @Inject constructor(@ApplicationContext val context: Context,val api:MoneyApi) : AccountDefaultRepository {
    override suspend fun getBudgetAccounts(budget_id: String): Flow<Result<MoneyResponse<AccountResponse>>> {
        return executeCall(context){api.getBudgetAccounts(budget_id)}
    }

    override suspend fun createNewAccount(
        budget_id: String,
        account: AccountRequest
    ): Flow<Result<MoneyResponse<AccountRequest>>> {
        return executeCall(context){api.createNewAccount(budget_id,account)}
    }
}