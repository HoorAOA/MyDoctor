package com.mydoctor.customer.delegates;

import java.io.Serializable;

public interface CommunicationInterfaceAll extends Serializable {
    void setCommunication(String message, String actionType, String response);
}
