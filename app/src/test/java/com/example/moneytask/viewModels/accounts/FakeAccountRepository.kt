package com.example.moneytask.viewModels.accounts

import com.example.domain.models.AccountRequest
import com.example.domain.repository.AccountDefaultRepository
import com.example.domain.response.AccountResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import com.example.moneytask.UseCasesUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAccountRepository : AccountDefaultRepository {
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