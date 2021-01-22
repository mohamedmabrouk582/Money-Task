package com.example.moneytask.viewModels.payee

import android.app.Application
import com.example.moneytask.UseCasesUtils
import com.example.moneytask.callBacks.PayeeCallBack
import com.example.moneytask.viewModels.BaseViewModelTest
import com.example.moneytask.viewModels.PayeeViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito


@ExperimentalCoroutinesApi
class PayeeViewModelTest : BaseViewModelTest() {
    lateinit var viewModel: PayeeViewModel<PayeeCallBack>

    override fun setUp() {
        super.setUp()
        val app = Mockito.mock(Application::class.java)
        val callBack = Mockito.mock(PayeeCallBack::class.java)
        viewModel = PayeeViewModel(app,FakePayeeRepository())
        viewModel.attachView(callBack)
    }

    @Test
    fun `getUserBudgets()`() = testScope.runBlockingTest {
        viewModel.getBudget()
        assertThat(viewModel.budgets).isEqualTo(UseCasesUtils.budgets)
    }

    @Test
    fun `getBudgetPayees()`()=testScope.runBlockingTest {
        viewModel.getPayees(UseCasesUtils.budgets)
        assertThat(viewModel.loader.get()).isFalse()
    }

    @Test
    fun `getPayeeTransactions()`()=testScope.runBlockingTest {
        viewModel.getPayeesTransactions("1","1")
        assertThat(viewModel.loader.get()).isFalse()
    }



}