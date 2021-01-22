package com.example.moneytask.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.models.Budget
import com.example.domain.models.BudgetPayees
import com.example.domain.models.Payee
import com.example.domain.models.Transaction
import com.example.moneytask.BUDGETS_LIST
import com.example.moneytask.BUDGET_PAYEES
import com.example.moneytask.MainActivity
import com.example.moneytask.R
import com.example.moneytask.callBacks.PayeeCallBack
import com.example.moneytask.databinding.PayeeTransactionsLayoutBinding
import com.example.moneytask.viewModels.PayeeViewModel
import com.example.moneytask.views.adapters.TransactionAdapter
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter

@AndroidEntryPoint
class PayeeTransactionFragment : Fragment(R.layout.payee_transactions_layout), PayeeCallBack {
    lateinit var viewBinding:PayeeTransactionsLayoutBinding
    var budgetPayees: HashMap<Budget, ArrayList<Payee>> = HashMap()
     val viewModel:PayeeViewModel<PayeeCallBack> by viewModels()
    val adapter : TransactionAdapter by lazy { TransactionAdapter() }
    lateinit var  budgetAdapter : ArrayAdapter<String>
    lateinit var  payeeAdapter: ArrayAdapter<String>
    lateinit var currentBudgetName:Budget
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = DataBindingUtil.bind(view)!!
        val alphaAdapter = AlphaInAnimationAdapter(adapter)
        viewBinding.transactionRcv.adapter=ScaleInAnimationAdapter(alphaAdapter)

        viewModel.attachView(this)
        viewBinding.vm=viewModel
        viewModel.getBudget()
    }

    override fun loadSpinners(budgetPayees:HashMap<Budget,ArrayList<Payee>>) {
        dismiss()
         this.budgetPayees=budgetPayees
        setupBudget(ArrayList(budgetPayees.keys.map { it.name }))
    }

    private fun setupPayee(data: ArrayList<String>){
        payeeAdapter= ArrayAdapter(requireContext(),R.layout.account_type_view, data)
        with(viewBinding.payeeSpinner){
            adapter=payeeAdapter
            onItemSelectedListener= payeeListener
        }
    }

    private fun setupBudget(data:ArrayList<String>){
        budgetAdapter=ArrayAdapter(requireContext(),R.layout.account_type_view, data)
        with(viewBinding.budgetSpinner){
            adapter=budgetAdapter
            onItemSelectedListener=budgetListener
        }
    }



    override fun loadTransactions(transactions: ArrayList<Transaction>) {
       adapter.data=transactions
    }

    override fun showLoader() {
        ProgressFragment.start().show(activity?.supportFragmentManager!!,"ProgressFragment")
    }

    override fun dismiss() {
        ProgressFragment.stop()
    }

    override fun error(error: String) {
        dismiss()
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun createClick() {
     val bundle= Bundle()
        bundle.putParcelable(BUDGET_PAYEES,BudgetPayees(budgetPayees))
        (activity as MainActivity).navController.navigate(R.id.action_payeeTransactionFragment_to_createTransactionFragment,bundle)
    }

    val budgetListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            currentBudgetName= budgetPayees.keys.filter { it.name==budgetAdapter.getItem(position) }[0]
            budgetPayees[currentBudgetName]?.let { setupPayee(ArrayList(it.map{it.name})) }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }

    val payeeListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val filter = budgetPayees[currentBudgetName]?.filter { payeeAdapter.getItem(position)!! == it.name }?.get(0)
            viewModel.getPayeesTransactions(currentBudgetName.id,filter?.id!!)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }
}