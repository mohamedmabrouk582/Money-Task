package com.example.moneytask.viewModels.accounts

import android.accounts.Account
import android.app.Application
import com.example.moneytask.callBacks.AccountCallBack
import com.example.moneytask.viewModels.AccountViewModel
import com.example.moneytask.viewModels.BaseViewModelTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito


@ExperimentalCoroutinesApi
class AccountViewModelTest : BaseViewModelTest(){
    lateinit var viewModel: AccountViewModel<AccountCallBack>

    override fun setUp() {
        super.setUp()
        val app= Mockito.mock(Application::class.java)
        val callBack= Mockito.mock(AccountCallBack::class.java)
        viewModel= AccountViewModel(app,FakeAccountRepository())
        viewModel.attachView(callBack)
    }

    @Test
    fun `getBudgetAccounts() `()= testScope.runBlockingTest {
        viewModel.getBudgetAccounts("1")
        assertThat(viewModel.loader.get()).isFalse()
    }

}