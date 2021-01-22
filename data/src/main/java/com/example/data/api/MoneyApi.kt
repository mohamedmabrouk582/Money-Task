package com.example.data.api

import com.example.data.BuildConfig
import com.example.domain.models.Account
import com.example.domain.models.AccountRequest
import com.example.domain.models.TransactionRequest
import com.example.domain.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface MoneyApi {
    @GET("budgets?access_token=${BuildConfig.Access_Token}")
    fun getBudgets(@Query("include_accounts") include_accounts:Boolean=true) : Deferred<MoneyResponse<BudgetResponse>>

    @GET("budgets/{budget_id}/accounts?access_token=${BuildConfig.Access_Token}")
    fun getBudgetAccounts(@Path("budget_id") budget_id:String) : Deferred<MoneyResponse<AccountResponse>>

    @GET("budgets/{budget_id}/payees?access_token=${BuildConfig.Access_Token}")
    fun getPayees(@Path("budget_id") budget_id:String) : Deferred<MoneyResponse<PayeeResponse>>

    @GET("budgets/{budget_id}/payees/{payee_id}/transactions?access_token=${BuildConfig.Access_Token}")
    fun getPayeesTransactions(@Path("budget_id") budget_id:String,@Path("payee_id") payee_id:String) :Deferred<MoneyResponse<TransactionResponse>>

    @Headers("Content-Type: application/json")
    @POST("budgets/{budget_id}/accounts?access_token=${BuildConfig.Access_Token}")
    fun createNewAccount(@Path("budget_id") budget_id:String,@Body account:AccountRequest) : Deferred<MoneyResponse<AccountRequest>>

    @Headers("Content-Type: application/json")
    @POST("budgets/{budget_id}/transactions?access_token=${BuildConfig.Access_Token}")
    fun createTransaction(@Path("budget_id") budget_id:String,@Body transaction:TransactionRequest ) : Deferred<MoneyResponse<TransactionRequest>>
}