package com.example.myapplication.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    fun getEmployees(): Call<MutableList<EmployeeModel>>
}