package sero.com.driverscommunicator.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import sero.com.driverscommunicator.data.db.Settings
import java.time.LocalDateTime

@Dao
interface SettingsDao {
    @Query("SELECT * FROM SETTINGS LIMIT 1")
     fun get(): LiveData<Settings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(settings: Settings)

}

