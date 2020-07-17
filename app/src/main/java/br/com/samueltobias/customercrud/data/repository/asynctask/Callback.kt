package br.com.samueltobias.customercrud.data.repository.asynctask

interface Callback<T> {
    fun onFinish(success: T)
}