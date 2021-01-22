package com.example.moneytask.views.fragments

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.models.Account
import com.example.moneytask.ACCOUNT_KEY
import com.example.moneytask.BUDGET_ID
import com.example.moneytask.MainActivity
import com.example.moneytask.R
import com.example.moneytask.callBacks.AccountCallBack
import com.example.moneytask.databinding.AccountsListLayoutBinding
import com.example.moneytask.viewModels.AccountViewModel
import com.example.moneytask.views.adapters.AccountAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

@AndroidEntryPoint
class AccountListFragment : Fragment(R.layout.accounts_list_layout), AccountAdapter.AccountListener, AccountCallBack {
    lateinit var viewBinding:AccountsListLayoutBinding
     val viewModel: AccountViewModel<AccountCallBack> by viewModels()
     val adapter:AccountAdapter by lazy { AccountAdapter(this) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding=DataBindingUtil.bind(view)!!
        viewModel.attachView(this)
        viewBinding.vm=viewModel
        val alphaAdapter = AlphaInAnimationAdapter(adapter)
        viewBinding.accountRcv.adapter= ScaleInAnimationAdapter(alphaAdapter)
        arguments?.getString(BUDGET_ID)?.let {
            viewModel.getBudgetAccounts(it)
        }

    }

    override fun onAccountClick(account: Account) {
       val bundle= Bundle()
        bundle.putParcelable(ACCOUNT_KEY,account)
        (activity as MainActivity).navController.navigate(R.id.action_accountListFragment_to_accountDetailsFragment,bundle)
    }

    fun addAccount(account: Account){
        adapter.addAccount(account)
        viewModel.error.set(null)
    }

    override fun loadAccounts(accounts: ArrayList<Account>) {
        adapter.accounts=accounts
    }

    override fun createAccount(budget_id:String) {
        val bundle = Bundle()
        bundle.putString(BUDGET_ID,budget_id)
        (activity as MainActivity).navController.navigate(R.id.action_accountListFragment_to_createAccountFragment,bundle)
    }
}