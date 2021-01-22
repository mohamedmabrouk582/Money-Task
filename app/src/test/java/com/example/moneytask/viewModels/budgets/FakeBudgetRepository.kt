package com.example.moneytask.viewModels.budgets

import com.example.domain.repository.BudgetDefaultRepository
import com.example.domain.response.BudgetResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import com.example.moneytask.UseCasesUtils
import com.ibm.icu.impl.duration.impl.Utils
import kotlinx.coroutines.flow.Flow

class FakeBudgetRepository : BudgetDefaultRepository {
    override suspend fun getUserBudgets(): Flow<Result<MoneyResponse<BudgetResponse>>> {
      return UseCasesUtils.getBudgets()
    }
}