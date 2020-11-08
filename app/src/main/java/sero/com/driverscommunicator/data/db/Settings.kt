package sero.com.driverscommunicator.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SETTINGS")
data class Settings(
    @ColumnInfo(name = "INFORMATIONS_ALREADY_SEEN") val informationsAlreadySeen: Boolean,
    @PrimaryKey @ColumnInfo(name = "ID") val id: Int = 0
)