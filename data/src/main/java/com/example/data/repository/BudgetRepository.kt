package com.example.data.repository

import android.content.Context
import com.example.data.api.MoneyApi
import com.example.data.utils.executeCall
import com.example.domain.repository.BudgetDefaultRepository
import com.example.domain.response.BudgetResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BudgetRepository @Inject constructor(@ApplicationContext val context: Context, val api:MoneyApi): BudgetDefaultRepository {

    override suspend fun getUserBudgets(): Flow<Result<MoneyResponse<BudgetResponse>>> {
      return executeCall(context){api.getBudgets(include_accounts = true)}
    }
}