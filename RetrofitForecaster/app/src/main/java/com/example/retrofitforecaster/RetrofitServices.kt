import com.example.retrofitforecaster.WeatherData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface RetrofitServices {
    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") key: String,
        @Query("units") units: String,
        @Query("lang") language: String
    ): Response<WeatherData>
}