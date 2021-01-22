package com.example.moneytask.viewModels.accounts

import android.app.Application
import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.moneytask.callBacks.AccountCallBack
import com.example.moneytask.callBacks.CreateAccountCallBack
import com.example.moneytask.viewModels.AccountViewModel
import com.example.moneytask.viewModels.BaseViewModelTest
import com.example.moneytask.viewModels.CreateAccountViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito


@ExperimentalCoroutinesApi
class CreateAccountViewModelTest : BaseViewModelTest() {
    lateinit var viewModel: CreateAccountViewModel<CreateAccountCallBack>

    override fun setUp() {
        super.setUp()
        val app= Mockito.mock(Application::class.java)
        val callBack= Mockito.mock(CreateAccountCallBack::class.java)
        viewModel= CreateAccountViewModel(app,FakeAccountRepository())
        viewModel.attachView(callBack)
    }

    @Test
    fun `createNewAccount()`() = testScope.runBlockingTest {
        viewModel.createNewAccount("1", AccountRequest(Account(id = "4","account4")))
        assertThat(viewModel.error.get()).isEqualTo("success")
    }


}