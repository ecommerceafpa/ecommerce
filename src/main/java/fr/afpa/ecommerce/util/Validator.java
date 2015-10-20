/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afpa.ecommerce.util;

import java.math.BigDecimal;
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

    public static BigDecimal decimal(String value, boolean isRequired) throws Exception {
        if (isRequired) {
            required(value);
        }

        try {
            return BigDecimal.valueOf(Double.parseDouble(value));
        } catch (NumberFormatException e) {
            throw new Exception("Veuillez fournir un numéro valide.");
        }
    }

    public static Integer number(String value, boolean isRequired) throws Exception {
        if (isRequired) {
            required(value);
        }

        try {
            return Integer.valueOf(value);
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

    public static Long isbn(String value, boolean isRequired) throws Exception {
        if (isRequired) {
            required(value);
        }

        if (value.length() == 13) {
            try {
                return Long.valueOf(value);
            } catch (NumberFormatException e) {
                throw new Exception("Veuillez fournir un numéro isbn valide.");
            }
        } else {
            throw new Exception("Veuillez fournir un numéro isbn valide.");
        }
    }

    public static void requiredList(String[] values) throws Exception {

        if (values == null || values.length == 0) {
            throw new Exception("Ce champ est obligatoire.");
        }

    }

    public static Integer[] integerList(String[] values, boolean isRequired) throws Exception {

        if (isRequired) {
            requiredList(values);
        }

        Integer[] intValues = new Integer[values.length];

        try {
            for (int i = 0; i < values.length; i++) {
                intValues[i] = Integer.parseInt(values[i]);
            }
        } catch (NumberFormatException e) {
            throw new Exception("Veuillez fournir une liste valide.");
        }

        return intValues;

    }

}
