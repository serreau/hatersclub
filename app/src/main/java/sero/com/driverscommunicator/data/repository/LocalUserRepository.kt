package sero.com.driverscommunicator.data.repository

import android.content.Context
import sero.com.driverscommunicator.data.db.CustomDatabase
import sero.com.driverscommunicator.data.db.User

object LocalUserRepository {

    lateinit var context : Context

    fun get()  = CustomDatabase.getDatabase(context).userDao().get()
    suspend fun update(user: User) = CustomDatabase.getDatabase(context).userDao().update(user)

}