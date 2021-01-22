package com.example.domain.useCases

import com.example.domain.repository.BudgetDefaultRepository
import com.example.domain.response.BudgetResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class BudgetDefaultRepositoryUseCaseTest : BaseUseCaseTest(){
    val repository = object : BudgetDefaultRepository{
        override suspend fun getUserBudgets(): Flow<Result<MoneyResponse<BudgetResponse>>> {
            return UseCasesUtils.getBudgets()
        }
    }

    @Test
    fun `getAllUserBudgets() return Budgets`()= testScope.runBlockingTest {
        val first = repository.getUserBudgets().first()
        assertThat(first).isEqualTo(Result.OnSuccess(MoneyResponse(BudgetResponse(UseCasesUtils.budgets))))
    }


}