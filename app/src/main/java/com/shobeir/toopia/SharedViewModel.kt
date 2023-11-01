package com.shobeir.toopia


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shobeir.toopia.data.model.ModelTeam
import com.shobeir.toopia.data.model.UserRegister


class SharedViewModel :ViewModel(){

    var user by  mutableStateOf<UserRegister?>(null)
        private set

    fun addUserReg(newUser:UserRegister){
        user = newUser
    }

    var team by mutableStateOf<ModelTeam?>(null)

    fun addTeam(newTeam: ModelTeam){
        team = newTeam
    }

}