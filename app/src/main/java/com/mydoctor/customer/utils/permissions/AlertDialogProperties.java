package com.mydoctor.customer.utils.permissions;

import android.view.View;

public class AlertDialogProperties {

    private String message;
    private String okayButtonText;
    private String cancelButtonText;
    private int okayButtonColor;
    private int cancelButtonColor;
    private View.OnClickListener okayButtonListener;
    private View.OnClickListener cancelButtonListener;
    private int messageTextColor;
    private int buttonTextColor;
    private boolean cancelable;

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public int getCancelButtonColor() {
        return cancelButtonColor;
    }

    public void setCancelButtonColor(int cancelButtonColor) {
        this.cancelButtonColor = cancelButtonColor;
    }

    public int getOkayButtonColor() {

        return okayButtonColor;
    }

    public void setOkayButtonColor(int okayButtonColor) {
        this.okayButtonColor = okayButtonColor;
    }

    public int getButtonTextColor() {
        return buttonTextColor;
    }

    public void setButtonTextColor(int buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
    }

    public int getMessageTextColor() {

        return messageTextColor;
    }

    public void setMessageTextColor(int messageTextColor) {
        this.messageTextColor = messageTextColor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOkayButtonText() {
        return okayButtonText;
    }

    public void setOkayButtonText(String okayButtonText) {
        this.okayButtonText = okayButtonText;
    }

    public String getCancelButtonText() {
        return cancelButtonText;
    }

    public void setCancelButtonText(String cancelButtonText) {
        this.cancelButtonText = cancelButtonText;
    }


    public View.OnClickListener getOkayButtonListener() {
        return okayButtonListener;
    }

    public void setOkayButtonListener(View.OnClickListener okayButtonListener) {
        this.okayButtonListener = okayButtonListener;
    }

    public View.OnClickListener getCancelButtonListener() {
        return cancelButtonListener;
    }

    public void setCancelButtonListener(View.OnClickListener cancelButtonListener) {
        this.cancelButtonListener = cancelButtonListener;
    }
}
