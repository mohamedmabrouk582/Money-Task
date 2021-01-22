package com.example.domain.useCases

import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.domain.repository.AccountDefaultRepository
import com.example.domain.response.AccountResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class AccountDefaultRepositoryUseCaseTest : BaseUseCaseTest(){
    private val repository= object : AccountDefaultRepository{
        override suspend fun getBudgetAccounts(budget_id: String): Flow<Result<MoneyResponse<AccountResponse>>> {
            return UseCasesUtils.getAccounts()
        }

        override suspend fun createNewAccount(
            budget_id: String,
            account: AccountRequest
        ): Flow<Result<MoneyResponse<AccountRequest>>> {
            UseCasesUtils.accounts.add(account.account)
            return flow { emit(Result.OnSuccess(MoneyResponse(account))) }
        }

    }

    @Test
    fun `getBudgetAccounts(id) list Accounts`() = testScope.runBlockingTest {
        val accounts=repository.getBudgetAccounts("1").first()
        assertThat(accounts).isEqualTo(Result.OnSuccess(MoneyResponse(AccountResponse(UseCasesUtils.accounts))))
    }

    @Test
    fun `createNewAccount() created Success`() = testScope.runBlockingTest {
        val newAccount = repository.createNewAccount("1", AccountRequest(Account("4", "account4"))).first()
        assertThat(newAccount).isEqualTo(Result.OnSuccess(MoneyResponse(Account("4", "account4"))))
        val accounts=repository.getBudgetAccounts("1").first()
        assertThat(accounts).isEqualTo(Result.OnSuccess(MoneyResponse(AccountResponse(UseCasesUtils.accounts))))
    }
}