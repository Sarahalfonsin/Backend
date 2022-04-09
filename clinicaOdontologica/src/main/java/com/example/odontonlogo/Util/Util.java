package com.example.odontonlogo.Util;

import java.sql.Date;

public class Util {
    //Convierte un Date en un SQL Date
    public static Date utilDateToSqlDate(Date utilDate){
        long timeInMilliSeconds = utilDate.getTime();
        Date sqlDate = new Date(timeInMilliSeconds);
        return sqlDate;
    }
}



