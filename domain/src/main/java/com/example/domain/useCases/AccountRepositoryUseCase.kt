package com.example.domain.useCases

import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.domain.repository.AccountDefaultRepository
import com.example.domain.response.AccountResponse
import com.example.domain.response.MoneyResponse
import com.example.domain.utils.Result
import kotlinx.coroutines.flow.Flow

class AccountRepositoryUseCase(val defaultRepository: AccountDefaultRepository) {
    suspend fun getBudgetAccounts(budget_id:String) : Flow<Result<MoneyResponse<AccountResponse>>> = defaultRepository.getBudgetAccounts(budget_id)

    suspend fun createNewAccount(budget_id:String,account: AccountRequest) : Flow<Result<MoneyResponse<AccountRequest>>> =  defaultRepository.createNewAccount(budget_id, account)


}