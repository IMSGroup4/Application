import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import com.example.ulla_app.dataclasses.ObstaclePosition

interface ullaApiService {
    // @GET("api/positions")
   //  fun getPositions(): Call<List<PositionResponse>>

  //   @POST("api/positions")
    // fun postPosition(@Body position: PositionRequest): Call<Void>

    @GET("api/obstacles")
    fun getObstacles(): Call<List<ObstaclePosition>>

   //  @POST("api/obstacles")
  //   fun postObstacle(@Body obstacle: ObstacleRequest): Call<Void>
}

