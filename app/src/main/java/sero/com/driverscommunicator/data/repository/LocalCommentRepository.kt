package sero.com.driverscommunicator.data.repository

import sero.com.driverscommunicator.data.remote.CommentRemote
import sero.com.driverscommunicator.utils.retrofit
import sero.com.driverscommunicator.data.remote.body.CreateCommentBody

object LocalCommentRepository {

    private val remote = retrofit.create(CommentRemote::class.java)

    suspend fun getAll(numberPlate : String)  = remote.get(numberPlate)
    fun send(username : String, comment: String, numberPlate: String) = remote.insert(
        CreateCommentBody(username, comment, numberPlate)
    )
}