package com.shobeir.toopia.cheknet

sealed class ConnectionState{
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}
