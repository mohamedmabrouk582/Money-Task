package com.example.domain.repository

import com.example.domain.response.BudgetResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface BudgetDefaultRepository {
    suspend fun getUserBudgets() : Flow<Result<MoneyResponse<BudgetResponse>>>
}