package sero.com.driverscommunicator.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER")
data class User(
    @ColumnInfo(name = "USERNAME") val username: String,
    @PrimaryKey @ColumnInfo(name = "ID") val id: Int = 0
)
