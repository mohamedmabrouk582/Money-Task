package com.example.moneytask.viewModels

import android.app.Application
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Budget
import com.example.domain.models.Payee
import com.example.domain.repository.PayeeDefaultRepository
import com.example.domain.utils.Result
import com.example.moneytask.callBacks.PayeeCallBack
import com.example.moneytask.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PayeeViewModel<V:PayeeCallBack> @ViewModelInject constructor(app:Application , val repository:PayeeDefaultRepository) : BaseViewModel<V>(app) {
   suspend fun getPayees(budgets:ArrayList<Budget>){
        val  budgetPayees : HashMap<Budget,ArrayList<Payee>> = HashMap()
       loader.set(true)
        budgets.forEach {
            budgetPayees[it]= getPayee(it.id)
        }
       loader.set(false)
       view.loadSpinners(budgetPayees)
    }

     lateinit var budgets:ArrayList<Budget>


    fun getBudget(){
       viewModelScope.launch {
           repository.getUserBudgets().collect {
               when(it){
                   Result.OnLoading -> {view.showLoader()}
                   is Result.OnSuccess -> {
                       budgets = it.data.data.budgets
                       getPayees(it.data.data.budgets)
                   }
                   is Result.OnFailure -> {
                       view.error(it.throwable.message.toString())
                   }
                   is Result.NoInternetConnect -> {
                       view.error(it.error)
                   }
               }
           }
       }
    }

    private suspend fun getPayee(budget_id:String) : ArrayList<Payee>{
        var payeesId :ArrayList<Payee> = ArrayList()
            repository.getBudgetPayees(budget_id).collect {
                when(it){
                    Result.OnLoading -> {}
                    is Result.OnSuccess -> {
                        payeesId=ArrayList(it.data.data.payees)
                    }
                    is Result.OnFailure -> {
                        view.error(it.throwable.message.toString())
                    }
                    is Result.NoInternetConnect -> {
                        view.error(it.error)
                    }
                }
            }
        return payeesId
    }

    fun getPayeesTransactions(budgetId:String,payee_id:String){
        viewModelScope.launch {
            repository.getPayeeTransactions(budgetId,payee_id).collect {
                when(it){
                    Result.OnLoading -> {
                        loader.set(true)
                        error.set(null)
                    }
                    is Result.OnSuccess -> {
                        loader.set(false)
                        if (it.data.data.transactions.isEmpty()){
                            error.set("No Transactions")
                        }
                        view.loadTransactions(it.data.data.transactions)
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

    fun createClick(v:View){
        view.createClick()
    }

}