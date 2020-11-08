package sero.com.driverscommunicator.data.repository

import android.content.Context
import sero.com.driverscommunicator.data.db.CustomDatabase
import sero.com.driverscommunicator.data.db.Settings

object LocalSettingsRepository {

    lateinit var context : Context

    fun get()  = CustomDatabase.getDatabase(context).settingsDao().get()
    suspend fun update(settings: Settings) =
        CustomDatabase.getDatabase(context).settingsDao().update(settings)

}