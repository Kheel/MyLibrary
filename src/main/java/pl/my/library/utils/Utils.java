package pl.my.library.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by Admin on 2017-06-03.
 */
public class Utils {

    public static java.util.Date convertToDate (LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
