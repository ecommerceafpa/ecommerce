/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.ecommerce.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Riad YOUSFI
 */
public class Validator {

    public static void required(String value) throws Exception {
        if (value == null || value.isEmpty()) {
            throw new Exception("Ce champ est obligatoire.");
        }
    }

    public static void email(String value, boolean isRequired) throws Exception {
        if (isRequired) {
            required(value);
        }
        String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) {
            throw new Exception("Veuillez fournir une adresse électronique valide.");
        }

    }

    public static Double numberDouble(String value, boolean isRequired) throws Exception {
        if (isRequired) {
            required(value);
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new Exception("Veuillez fournir un numéro valide.");
        }

    }

    public static Date date(String value, String pattern, boolean isRequired) throws Exception {

        if (isRequired) {
            required(value);
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new Exception("Veuillez fournir une date valide.");
        }

    }

    public static void dateCompareBefore(Date date1, Date date2) throws Exception {
        if (!date1.equals(date2) && !date1.before(date2)) {
            throw new Exception("Date de début est après la date de fin");
        }
    }

}
