package com.shobeir.toopia


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shobeir.toopia.data.model.ModelTeam
import com.shobeir.toopia.data.model.News
import com.shobeir.toopia.data.model.Store
import com.shobeir.toopia.data.model.User
import com.shobeir.toopia.data.model.UserRegister


class SharedViewModel :ViewModel(){

    var userReg by  mutableStateOf<UserRegister?>(null)
        private set

    fun addUserReg(newUser:UserRegister){
        userReg = newUser
    }

    var store by  mutableStateOf<Store?>(null)
        private set

    fun addStore(newStore:Store){
        store = newStore
    }

    var user by  mutableStateOf<User?>(null)
        private set

    fun addUser(newUser:User){
        user = newUser
    }

    var team by mutableStateOf<ModelTeam?>(null)

    fun addTeam(newTeam: ModelTeam){
        team = newTeam
    }

    var news by  mutableStateOf<News?>(null)
        private set

    fun addNews(newNews:News){
        news = newNews
    }

    var code by  mutableStateOf<String?>(null)
        private set

    fun addCode(newCode:String){
        code = newCode
    }

}