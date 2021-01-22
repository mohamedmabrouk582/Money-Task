package com.example.domain.useCases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@ExperimentalCoroutinesApi
open class BaseUseCaseTest {
    private val testDispatcher= TestCoroutineDispatcher()
    protected val testScope= TestCoroutineScope()

    @Before
    open fun setUp(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun tearDown(){
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }
}