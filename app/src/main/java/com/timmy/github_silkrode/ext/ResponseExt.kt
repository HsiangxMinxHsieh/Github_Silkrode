package com.timmy.github_silkrode.ext

import com.timmy.github_silkrode.api.Errors
import com.timmy.github_silkrode.base.Results
import retrofit2.Response
import util.logi
import java.io.IOException

inline fun <T> processApiResponse(request: () -> Response<T>): Results<T> {
    return try {
        val response = request()
        val responseCode = response.code()
        val responseMessage = response.message()
        logi("login","登入結果是===>$response")
        if (response.isSuccessful) {
            Results.success(response.body()!!)
        } else {
            Results.failure(Errors.NetworkError(responseCode, responseMessage))
        }
    } catch (e: IOException) {
        e.printStackTrace()
        Results.failure(Errors.NetworkError())
    }
}