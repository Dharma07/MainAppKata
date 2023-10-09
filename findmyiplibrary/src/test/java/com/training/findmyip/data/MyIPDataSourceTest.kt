package com.training.findmyip.data

import com.training.findmyip.api.MyIpApi
import com.training.findmyip.model.FindMyIPResponse
import com.training.findmyip.utils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response


class MyIPDataSourceTest : BaseUnitTest(){

    private val expectedOutPut = FindMyIPResponse("136.226.255.75")

    @Mock
    lateinit var service: MyIpApi

    private lateinit var myIPDataSource: MyIPDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        myIPDataSource= MyIPDataSource(service)
    }

    @Test
    fun test_findMyIp_Value()= runTest{
        Mockito.`when`(service.findMyIP()).thenReturn(Response.success(expectedOutPut))
        val result = myIPDataSource.callFindMyIp()
        Assert.assertEquals("136.226.255.75", result.body()!!.ip)
    }

    @Test
    fun test_findMyIp_Value_mockk() = runTest {
        val myIpApi = mockk<MyIpApi>()
        val ipDataSource = MyIPDataSource(myIpApi)
        coEvery { myIpApi.findMyIP() } returns mockk()
        ipDataSource.callFindMyIp()
        coVerify { myIpApi.findMyIP() }
    }
}