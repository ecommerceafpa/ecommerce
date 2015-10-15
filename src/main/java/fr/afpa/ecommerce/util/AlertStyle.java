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
public enum AlertStyle {

    SUCCESS("alert-success"),
    INFO("alert-info"),
    WARNING("alert-warning"),
    DANGER("alert-danger");

    private final String style;

    private AlertStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    @Override
    public String toString() {
        return style;
    }

}
