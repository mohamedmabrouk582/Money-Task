package com.example.moneytask.viewModels.budgets

import android.app.Application
import com.example.moneytask.callBacks.BudgetsCallBack
import com.example.moneytask.viewModels.BaseViewModelTest
import com.example.moneytask.viewModels.BudgetViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class BudgetViewModelTest : BaseViewModelTest(){

    lateinit var viewModel: BudgetViewModel<BudgetsCallBack>

    override fun setUp() {
        super.setUp()
        val app= Mockito.mock(Application::class.java)
        val callBack =  Mockito.mock(BudgetsCallBack::class.java)
        viewModel= BudgetViewModel(app, FakeBudgetRepository())
        viewModel.attachView(callBack)
    }

    override fun tearDown() {
        super.tearDown()

    }

    @Test
    fun `getAllBudget()`() = testScope.runBlockingTest {
        viewModel.getAllBudget()
        assertThat(viewModel.loader.get()).isFalse()
    }


}