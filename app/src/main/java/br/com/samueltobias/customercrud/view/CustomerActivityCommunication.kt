package br.com.samueltobias.customercrud.view

interface CustomerActivityCommunication {
    companion object {
        const val INTENT_EXTRA_CUSTOMER_SERIALIZED = "customerSerialized"
        const val CUSTOMER_ADD_REQUEST_CODE = 101
        const val CUSTOMER_EDIT_REQUEST_CODE = 102
        const val CUSTUMER_ADD_RESULT_CODE = 201
        const val CUSTUMER_EDIT_RESULT_CODE = 202
    }
}