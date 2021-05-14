package huaan.com.mvvmdemo.http

import com.lyl.mvvm.test.Data
import com.lyl.mvvm.test.ResponseData
import retrofit2.http.GET

interface RequestService {
    @GET("wxarticle/chapters/json")
   suspend fun getDatas() : ResponseData<List<Data>>
}