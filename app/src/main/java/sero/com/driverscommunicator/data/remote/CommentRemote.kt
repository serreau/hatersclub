package sero.com.driverscommunicator.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sero.com.driverscommunicator.data.remote.response.DefaultResponse
import sero.com.driverscommunicator.data.remote.response.GetCommentResponse
import sero.com.driverscommunicator.data.remote.body.CreateCommentBody


interface CommentRemote {
    @GET("/comment/get/{id}")
    suspend fun get(@Path("id") numberPlate : String) : List<GetCommentResponse>

    @POST("/comment/insert")
    fun insert(@Body job : CreateCommentBody) : Call<DefaultResponse>
}