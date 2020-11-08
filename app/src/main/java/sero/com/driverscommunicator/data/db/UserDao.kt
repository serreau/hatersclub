package sero.com.driverscommunicator.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import sero.com.driverscommunicator.data.db.Settings
import java.time.LocalDateTime

@Dao
interface UserDao {
    @Query("SELECT * FROM USER LIMIT 1")
     fun get(): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

}

