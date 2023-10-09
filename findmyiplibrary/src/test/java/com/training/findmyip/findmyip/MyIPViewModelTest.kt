package com.training.findmyip.findmyip

import com.training.findmyip.data.MyIPRepository
import com.training.findmyip.data.Resource
import com.training.findmyip.model.FindMyIPResponse
import com.training.findmyip.utils.BaseUnitTest
import com.training.findmyip.utils.LiveDataTestUtil.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MyIPViewModelTest :BaseUnitTest(){

    private lateinit var viewModel: MyIPViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repository: MyIPRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MyIPViewModel(repository)
    }

    @Test
    fun test_GetMyIp()= runTest{
        Mockito.`when`(repository.findMyIp()).thenReturn(Resource.Success(FindMyIPResponse()))
        viewModel.findMyIp()
        testDispatcher.scheduler.advanceUntilIdle()
        val value = viewModel.findMyIPResponse.getOrAwaitValue()
        assertEquals(FindMyIPResponse(), value.data)
    }

    @Test
    fun test_GetMyIp_expectedError()= runTest {
        Mockito.`when`(repository.findMyIp()).thenReturn(Resource.Error("Error occurred"))
        viewModel.findMyIp()
        testDispatcher.scheduler.advanceUntilIdle()
        val value = viewModel.findMyIPResponse.getOrAwaitValue()
        assertEquals(true,value is Resource.Error)
        assertEquals("Error occurred", value.message)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }


}