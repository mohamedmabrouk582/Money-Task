package com.example.moneytask.viewModels

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.domain.repository.AccountDefaultRepository
import com.example.domain.utils.Result
import com.example.moneytask.callBacks.CreateAccountCallBack
import com.example.moneytask.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreateAccountViewModel<V:CreateAccountCallBack> @ViewModelInject constructor(app:Application,val repository:AccountDefaultRepository) : BaseViewModel<V>(app) {

    fun createNewAccount(budget_id: String,account: AccountRequest){
        viewModelScope.launch {
            repository.createNewAccount(budget_id,account).collect {
                when(it){
                    Result.OnLoading -> {
                        view.showLoader()
                    }
                    is Result.OnSuccess ->{
                        view.dismiss()
                        error.set("success")
                        view.accountCreated(it.data.data)
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

    fun createClick(button:View){
        view.createClick()
    }

}