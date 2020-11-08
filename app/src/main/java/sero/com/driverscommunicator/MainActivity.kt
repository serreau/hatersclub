package sero.com.driverscommunicator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import sero.com.driverscommunicator.data.repository.LocalSettingsRepository
import sero.com.driverscommunicator.data.repository.LocalUserRepository
import sero.com.driverscommunicator.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        LocalSettingsRepository.context = this
        LocalUserRepository.context = this

        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}