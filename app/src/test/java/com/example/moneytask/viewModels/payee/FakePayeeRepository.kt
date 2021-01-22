package com.example.moneytask.viewModels.payee

import com.example.domain.repository.PayeeDefaultRepository
import com.example.domain.response.BudgetResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.response.PayeeResponse
import com.example.domain.response.TransactionResponse
import com.example.domain.utils.Result
import com.example.moneytask.UseCasesUtils
import kotlinx.coroutines.flow.Flow

class FakePayeeRepository : PayeeDefaultRepository {

    override suspend fun getBudgetPayees(budget_id: String): Flow<Result<MoneyResponse<PayeeResponse>>> {
      return UseCasesUtils.getPayees()
    }

    override suspend fun getPayeeTransactions(
        budget_id: String,
        payee_id: String
    ): Flow<Result<MoneyResponse<TransactionResponse>>> {
        return UseCasesUtils.getTransactions()
    }

    override suspend fun getUserBudgets(): Flow<Result<MoneyResponse<BudgetResponse>>> {
       return UseCasesUtils.getBudgets()
    }
}