package com.example.moneytask.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.models.Budget
import com.example.moneytask.BUDGET_ID
import com.example.moneytask.BUDGET_KEY
import com.example.moneytask.MainActivity
import com.example.moneytask.R
import com.example.moneytask.callBacks.BudgetsCallBack
import com.example.moneytask.databinding.BudgetListLayoutBinding
import com.example.moneytask.viewModels.BudgetViewModel
import com.example.moneytask.views.adapters.BudgetAdapter
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

@AndroidEntryPoint
class BudgetListFragment : Fragment(R.layout.budget_list_layout), BudgetAdapter.BudgetListener, BudgetsCallBack {
    lateinit var viewBinding: BudgetListLayoutBinding
    val adapter : BudgetAdapter by lazy { BudgetAdapter(this) }
    val viewModel : BudgetViewModel<BudgetsCallBack> by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding= DataBindingUtil.bind(view)!!
        viewModel.attachView(this)
        viewBinding.vm=viewModel
        val alphaAdapter = AlphaInAnimationAdapter(adapter)
        viewBinding.budgetRcv.adapter= ScaleInAnimationAdapter(alphaAdapter)
        viewModel.getAllBudget()
    }

    override fun onBudgetClick(budget: Budget) {
        val bundle =Bundle()
        bundle.putParcelable(BUDGET_KEY,budget)
        (activity as MainActivity).navController.navigate(R.id.action_budgetListFragment_to_budgetDetailsFragment,bundle)
    }

    override fun onAccountClick(budget: Budget) {
        val bundle =Bundle()
        bundle.putString(BUDGET_ID,budget.id)
        (activity as MainActivity).navController.navigate(R.id.action_budgetListFragment_to_accountListFragment,bundle)
    }

    override fun loadBudgets(budgets: ArrayList<Budget>) {
        adapter.budgets=budgets
    }

}