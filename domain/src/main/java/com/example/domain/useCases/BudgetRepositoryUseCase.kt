package com.example.domain.useCases

import com.example.domain.repository.BudgetDefaultRepository
import com.example.domain.response.BudgetResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

class BudgetRepositoryUseCase(val defaultRepository:BudgetDefaultRepository) {
    suspend operator fun invoke() : Flow<Result<MoneyResponse<BudgetResponse>>> =
        defaultRepository.getUserBudgets()
}