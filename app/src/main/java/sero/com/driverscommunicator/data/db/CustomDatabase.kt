package sero.com.driverscommunicator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Settings::class, User::class], version = 1)
abstract class CustomDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: CustomDatabase? = null

        fun getDatabase(context: Context): CustomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CustomDatabase::class.java,
                    "haters_club_settings_locale_db")
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE as CustomDatabase
            }
        }
    }
}