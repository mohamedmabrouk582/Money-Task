package com.example.data.repository

import android.content.Context
import com.example.data.api.MoneyApi
import com.example.data.utils.executeCall
import com.example.domain.repository.PayeeDefaultRepository
import com.example.domain.response.BudgetResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.response.PayeeResponse
import com.example.domain.response.TransactionResponse
import com.example.domain.utils.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PayeeRepository @Inject constructor(@ApplicationContext val context: Context,val api:MoneyApi) : PayeeDefaultRepository {
    override suspend fun getBudgetPayees(budget_id: String): Flow<Result<MoneyResponse<PayeeResponse>>> {
        return executeCall(context){api.getPayees(budget_id)}
    }

    override suspend fun getPayeeTransactions(
        budget_id: String,
        payee_id: String
    ): Flow<Result<MoneyResponse<TransactionResponse>>> {
        return executeCall(context){api.getPayeesTransactions(budget_id, payee_id)}
    }

    override suspend fun getUserBudgets(): Flow<Result<MoneyResponse<BudgetResponse>>> {
        return executeCall(context){api.getBudgets(false)}
    }
}