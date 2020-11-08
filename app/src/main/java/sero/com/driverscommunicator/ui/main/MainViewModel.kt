package sero.com.driverscommunicator.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sero.com.driverscommunicator.data.db.CustomDatabase
import sero.com.driverscommunicator.data.db.Settings
import sero.com.driverscommunicator.data.db.User
import sero.com.driverscommunicator.data.remote.response.DefaultResponse
import sero.com.driverscommunicator.data.repository.LocalCommentRepository
import sero.com.driverscommunicator.data.repository.LocalSettingsRepository
import sero.com.driverscommunicator.data.repository.LocalUserRepository


class MainViewModel : ViewModel() {

    val numberPlate : MutableLiveData<String> = MutableLiveData()
    val allComments = Transformations.switchMap(numberPlate) { numberPlate: String ->
        liveData(Dispatchers.IO) { emit( LocalCommentRepository.getAll(numberPlate)) }
    }

    val settings = LocalSettingsRepository.get()
    val user = LocalUserRepository.get()

    fun setInformationsAlreadySeen() =
        viewModelScope.launch {
            LocalSettingsRepository.update(Settings(true))
        }

    fun setUserName(username : String) =
        viewModelScope.launch {
            LocalUserRepository.update(User(username))
        }

    fun send(numberPlate: String, comment: String) = viewModelScope.launch {
        LocalCommentRepository.send(user.value?.username ?: "", numberPlate, comment).enqueue(object: Callback<DefaultResponse>{
            override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                if (response.isSuccessful) this@MainViewModel.numberPlate.postValue(numberPlate)
            }
            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) { t.printStackTrace() }
        })
    }


}