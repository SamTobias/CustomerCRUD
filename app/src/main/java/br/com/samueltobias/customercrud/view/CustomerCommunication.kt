package br.com.samueltobias.customercrud.view

interface CustomerCommunication {
    companion object {
        const val INTENT_EXTRA_CUSTOMER_SERIALIZED = "customerSerialized"
        const val CUSTOMER_ADD_REQUEST_CODE = "customerAdd"
        const val CUSTOMER_EDIT_REQUEST_CODE = "customerEdit"
    }
}