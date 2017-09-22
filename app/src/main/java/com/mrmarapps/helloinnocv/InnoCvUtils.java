package com.mrmarapps.helloinnocv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Mario on 22/09/2017.
 */

public class InnoCvUtils {


    private static final String FORMAT_LOCAL = "dd/MM/yyyy";
    private static final String FORMAT_API = "yyyy-MM-dd'T'HH:mm:ss";

    public static String formatApiDateToLocal(String dateApi){
        SimpleDateFormat sdfFormatApi = new SimpleDateFormat(FORMAT_API, Locale.US);
        SimpleDateFormat sdfFormatLocal = new SimpleDateFormat(FORMAT_LOCAL,Locale.US);

        sdfFormatApi.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = sdfFormatApi.parse(dateApi);
            return sdfFormatLocal.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateApi;
    }
    public static String formatLocalDateToApi(String dateLocal){
        SimpleDateFormat sdfFormatApi = new SimpleDateFormat(FORMAT_API, Locale.US);
        SimpleDateFormat sdfFormatLocal = new SimpleDateFormat(FORMAT_LOCAL,Locale.US);

        sdfFormatApi.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = sdfFormatLocal.parse(dateLocal);
            return sdfFormatApi.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateLocal;
    }
}
