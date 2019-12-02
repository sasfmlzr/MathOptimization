package com.sasfmlzr.mathoptimization.architecture

interface RuntimePermissions {
    fun request(permissions: List<String>)
    fun request(permission: String)

}
