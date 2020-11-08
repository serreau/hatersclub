package sero.com.driverscommunicator.data.remote.body

import com.google.gson.annotations.SerializedName

class CreateCommentBody (@SerializedName("username") var username : String,
                         @SerializedName("numberplate") var numberplate : String,
                         @SerializedName("comment") var comment : String)