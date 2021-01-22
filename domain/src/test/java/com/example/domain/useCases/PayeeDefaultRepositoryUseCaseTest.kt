package com.example.domain.useCases

import com.example.domain.repository.PayeeDefaultRepository
import com.example.domain.response.BudgetResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.response.PayeeResponse
import com.example.domain.response.TransactionResponse
import com.example.domain.utils.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PayeeDefaultRepositoryUseCaseTest : BaseUseCaseTest(){
    private val repository = object : PayeeDefaultRepository{
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

    @Test
    fun `getBudgetPayees() list Payees`() = testScope.runBlockingTest {
        val payees=repository.getBudgetPayees("1").first()
        assertThat(payees).isEqualTo(Result.OnSuccess(MoneyResponse(PayeeResponse(UseCasesUtils.payees))))
    }

    @Test
    fun `getPayeeTransactions(budgetId,payeeId) list Transactions`() = testScope.runBlockingTest {
        val trans=repository.getPayeeTransactions("1","1").first()
        assertThat(trans).isEqualTo(Result.OnSuccess(MoneyResponse(TransactionResponse(UseCasesUtils.transactions))))
    }

}