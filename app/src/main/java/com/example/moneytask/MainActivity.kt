package com.example.moneytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.domain.models.Account
import com.example.domain.models.Budget
import com.example.moneytask.callBacks.AccountListener
import com.example.moneytask.callBacks.BudgetsCallBack
import com.example.moneytask.databinding.ActivityMainBinding
import com.example.moneytask.databinding.BudgetListLayoutBinding
import com.example.moneytask.viewModels.BudgetViewModel
import com.example.moneytask.views.adapters.BudgetAdapter
import com.example.moneytask.views.fragments.AccountListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , AccountListener {
    lateinit var viewBinding: ActivityMainBinding
   lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        navController=Navigation.findNavController(this,R.id.nav_main)
        viewBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            val currentId=navController.currentDestination?.id
            when(it.itemId){
                R.id.budget_item->{
                    if (currentId!=R.id.budgetListFragment) navController.popBackStack()
                    true
                }
                R.id.payee_item ->{
                    when (currentId) {
                        R.id.budgetListFragment -> {
                            navController.navigate(R.id.action_budgetListFragment_to_payeeTransactionFragment)
                        }
                        R.id.accountListFragment -> {
                            navController.navigate(R.id.action_accountListFragment_to_payeeTransactionFragment)
                        }
                        R.id.payeeTransactionFragment ->{}
                        else -> {
                            navController.navigate(R.id.action_budgetListFragment_to_payeeTransactionFragment)
                        }
                    }
                    true
                }
                else ->false
            }
        }
    }

    override fun addAccount(account: Account) {
        val navHostFragment = supportFragmentManager.fragments.first() as NavHostFragment
        navHostFragment.apply {
       this.childFragmentManager.fragments.forEach {
           if (it is AccountListFragment){
               it.addAccount(account)
           }
       }
        }
    }
}