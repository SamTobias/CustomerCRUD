package br.com.samueltobias.customercrud.ui;

public interface CustomerActivityCommunication {

    String INTENT_EXTRA_CUSTOMER_SERIALIZED = "customerSerialized";
    int CUSTOMER_ADD_REQUEST_CODE = 101;
    int CUSTOMER_EDIT_REQUEST_CODE = 102;

    int CUSTUMER_ADD_RESULT_CODE = 201;
    int CUSTUMER_EDIT_RESULT_CODE = 202;
}
