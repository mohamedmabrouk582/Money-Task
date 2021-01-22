package com.example.data.repository

import android.content.Context
import com.example.data.api.MoneyApi
import com.example.data.utils.executeCall
import com.example.domain.models.TransactionRequest
import com.example.domain.repository.TransactionDefaultRepository
import com.example.domain.response.AccountResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(@ApplicationContext val context: Context , val api:MoneyApi) : TransactionDefaultRepository {


    override suspend fun getBudgetAccounts(budget_id: String): Flow<Result<MoneyResponse<AccountResponse>>> {
        return executeCall(context){api.getBudgetAccounts(budget_id)}
    }

    override suspend fun createTransAction(budget_id: String, transaction: TransactionRequest): Flow<Result<MoneyResponse<TransactionRequest>>> {
        return executeCall(context){api.createTransaction(budget_id, transaction)}
    }
}