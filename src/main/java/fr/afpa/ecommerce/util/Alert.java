/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.ecommerce.util;

/**
 *
 * @author Riad YOUSFI
 */
public class Alert {

    private AlertStyle alertStyle = AlertStyle.SUCCESS;
    private String message;
    private boolean showButton = true;

    public Alert() {
    }

    public Alert(String message) {
        this.message = message;
    }

    public Alert(AlertStyle alertStyle, String message) {
        this.alertStyle = alertStyle;
        this.message = message;
    }

    public Alert(AlertStyle alertStyle, String message, boolean showButton) {
        this.alertStyle = alertStyle;
        this.message = message;
        this.showButton = showButton;
    }

    public AlertStyle getAlertStyle() {
        return alertStyle;
    }

    public void setAlertStyle(AlertStyle alertStyle) {
        this.alertStyle = alertStyle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isShowButton() {
        return showButton;
    }

    public void setShowButton(boolean showButton) {
        this.showButton = showButton;
    }

}
