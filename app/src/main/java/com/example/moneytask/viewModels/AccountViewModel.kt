package com.example.moneytask.viewModels

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.data.repository.AccountRepository
import com.example.domain.models.Account
import com.example.domain.repository.AccountDefaultRepository
import com.example.domain.utils.Result
import com.example.moneytask.callBacks.AccountCallBack
import com.example.moneytask.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AccountViewModel<V:AccountCallBack> @ViewModelInject constructor(app:Application,val repository: AccountDefaultRepository) : BaseViewModel<V>(app) {
  lateinit var budget_id: String
  fun getBudgetAccounts(budget_id:String){
      this.budget_id=budget_id
      viewModelScope.launch {
          repository.getBudgetAccounts(budget_id).collect {
              when(it){
                  Result.OnLoading ->{
                      loader.set(true)
                      error.set(null)
                  }
                  is Result.OnSuccess -> {
                      loader.set(false)
                      if (it.data.data.accounts.isEmpty()){
                          error.set("No Accounts")
                      }else{
                          view.loadAccounts(it.data.data.accounts)
                      }

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

    fun createClick(view: View){
        this.view.createAccount(budget_id)
    }

}