package pl.my.library.utils;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Admin on 2017-06-03.
 */
public class Utils {

    public static Date convertToDate (LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    public static LocalDate convertToLocalDate(java.util.Date dateRelease) {
        return dateRelease.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
