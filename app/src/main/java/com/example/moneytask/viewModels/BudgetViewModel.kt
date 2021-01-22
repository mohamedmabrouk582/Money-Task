package com.example.moneytask.viewModels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.BudgetDefaultRepository
import com.example.domain.utils.Result
import com.example.moneytask.callBacks.BudgetsCallBack
import com.example.moneytask.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BudgetViewModel<V:BudgetsCallBack> @ViewModelInject constructor(app:Application,val repository: BudgetDefaultRepository) : BaseViewModel<V>(app) {

    override fun retry() {
        getAllBudget()
    }

   fun getAllBudget(){
       viewModelScope.launch {
           repository.getUserBudgets().collect {
               when(it){
                   Result.OnLoading -> {
                       error.set(null)
                       loader.set(true)
                   }
                   is Result.OnSuccess ->{
                       loader.set(false)
                       view.loadBudgets(it.data.data.budgets)
                   }
                   is Result.OnFailure -> {
                       loader.set(false)
                       error.set(it.throwable.message)
                   }
                   is Result.NoInternetConnect -> {
                       loader.set(false)
                       error.set(it.error)
                   }
               }
           }
       }
   }
}