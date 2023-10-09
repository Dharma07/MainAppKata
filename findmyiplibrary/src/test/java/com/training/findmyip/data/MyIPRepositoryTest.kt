package com.training.findmyip.data

import com.training.findmyip.model.FindMyIPResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MyIPRepositoryTest {

    private lateinit var repository: MyIPRepository
    private val ipDataSource = mockk<MyIPDataSource>()

    @Before
    fun setUp() {
        repository = MyIPRepository(ipDataSource)
    }

    @Test
    fun findMyIp_using_Mockk(): Unit = runBlocking {
        val response = Response.success(FindMyIPResponse())
        coEvery { ipDataSource.callFindMyIp() } returns response
        repository.findMyIp()
        coVerify { ipDataSource.callFindMyIp() }
        assertEquals(response.body(), repository.findMyIp().data)
    }

}


