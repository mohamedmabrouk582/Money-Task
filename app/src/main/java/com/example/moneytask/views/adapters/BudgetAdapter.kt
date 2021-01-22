package com.example.moneytask.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Budget
import com.example.moneytask.R
import com.example.moneytask.databinding.BudgetItemViewBinding

class BudgetAdapter (val listener: BudgetListener) : RecyclerView.Adapter<BudgetAdapter.Holder>(){
  var budgets:ArrayList<Budget> = ArrayList()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.budget_item_view,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(budgets[position])
    }

    override fun getItemCount(): Int =budgets.size


    inner class Holder(private val viewBinding: BudgetItemViewBinding) : RecyclerView.ViewHolder(viewBinding.root){
        fun bind(budget: Budget){
            viewBinding.budget=budget
            viewBinding.root.setOnClickListener { listener.onBudgetClick(budget) }
            viewBinding.accountBtn.setOnClickListener { listener.onAccountClick(budget) }
            viewBinding.executePendingBindings()
        }
    }

    interface BudgetListener{
        fun onBudgetClick(budget: Budget)
        fun onAccountClick(budget: Budget)
    }
}