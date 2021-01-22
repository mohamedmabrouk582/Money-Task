package com.example.moneytask.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.domain.models.Budget
import com.example.moneytask.BUDGET_KEY
import com.example.moneytask.R
import com.example.moneytask.databinding.BudgetDetailsLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BudgetDetailsFragment : BottomSheetDialogFragment() {
   lateinit var viewBinding:BudgetDetailsLayoutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.budget_details_layout,container,false)
        arguments?.getParcelable<Budget>(BUDGET_KEY)?.let {
            viewBinding.budget=it
        }
        return viewBinding.root
    }

}