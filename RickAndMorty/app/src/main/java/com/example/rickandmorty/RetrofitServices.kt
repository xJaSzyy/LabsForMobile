import com.example.rickandmorty.DataModel
import retrofit2.Call
import retrofit2.http.*


interface RetrofitServices {
    @GET("api/character/{id}")
    fun getCharacterList(@Path("id") id: Int): Call<DataModel.CharacterResults>

    @GET("api/episode/{id}")
    fun getEpisodeList(@Path("id") id: Int): Call<DataModel.EpisodeResults>
}