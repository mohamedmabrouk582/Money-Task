package com.example.moneytask.di

import com.example.data.repository.AccountRepository
import com.example.data.repository.BudgetRepository
import com.example.data.repository.PayeeRepository
import com.example.data.repository.TransactionRepository
import com.example.domain.repository.AccountDefaultRepository
import com.example.domain.repository.BudgetDefaultRepository
import com.example.domain.repository.PayeeDefaultRepository
import com.example.domain.repository.TransactionDefaultRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun getBudgetRepository(repository: BudgetRepository) : BudgetDefaultRepository

    @Binds
    @Singleton
    abstract fun getAccountRepository(repository: AccountRepository) : AccountDefaultRepository

    @Binds
    @Singleton
    abstract fun getPayeeRepository(repository: PayeeRepository) : PayeeDefaultRepository

    @Binds
    @Singleton
    abstract fun getTransactionRepository(repository: TransactionRepository) : TransactionDefaultRepository
}