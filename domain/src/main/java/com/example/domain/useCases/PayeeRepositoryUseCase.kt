package com.example.domain.useCases

import com.example.domain.repository.PayeeDefaultRepository
import com.example.domain.response.MoneyResponse
import com.example.domain.response.PayeeResponse
import com.example.domain.response.TransactionResponse
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

class PayeeRepositoryUseCase(val defaultRepository:PayeeDefaultRepository) {
     suspend fun getBudgetPayees(budget_id: String): Flow<Result<MoneyResponse<PayeeResponse>>> =
         defaultRepository.getBudgetPayees(budget_id)

     suspend fun getPayeeTransactions(
        budget_id: String,
        payee_id: String
    ): Flow<Result<MoneyResponse<TransactionResponse>>> = defaultRepository.getPayeeTransactions(budget_id, payee_id)
}