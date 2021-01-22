package com.example.moneytask.viewModels

import android.app.Application
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.domain.models.TransactionRequest
import com.example.domain.repository.TransactionDefaultRepository
import com.example.domain.utils.Result
import com.example.moneytask.callBacks.CreateTransactionCallBack
import com.example.moneytask.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreateTransactionViewModel<V:CreateTransactionCallBack> @ViewModelInject constructor(app:Application,val repository: TransactionDefaultRepository) : BaseViewModel<V>(app) {


    fun getBudgetAccounts(budget_id:String){
        viewModelScope.launch {
            repository.getBudgetAccounts(budget_id).collect {
                when(it){
                    Result.OnLoading -> {view.showLoader()}
                    is Result.OnSuccess -> {
                        view.dismiss()
                        view.loadAccounts(it.data.data.accounts)
                    }
                    is Result.OnFailure -> {
                        view.dismiss()
                        view.error(it.throwable.message.toString())
                    }
                    is Result.NoInternetConnect -> {
                        view.dismiss()
                        view.error(it.error)
                    }
                }
            }
        }
    }

    fun createTransaction(budget_id: String,transaction:TransactionRequest){
        viewModelScope.launch {
            repository.createTransAction(budget_id, transaction).collect {
                when(it){
                    Result.OnLoading -> {view.showLoader()}
                    is Result.OnSuccess -> {
                        view.dismiss()
                        view.accountTransaction(it.data.data.transaction)
                    }
                    is Result.OnFailure -> {
                        view.dismiss()
                        view.error(it.throwable.message.toString())

                    }
                    is Result.NoInternetConnect -> {
                        view.dismiss()
                        view.error(it.error)
                    }
                }
            }
        }
    }

    fun createClick(button: View){
        view.createClick()
    }

    fun selectDate(button: View){
        view.datePicker()
    }

}