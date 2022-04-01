package com.company.util;

import java.util.Date;

public class Util {
    public static java.sql.Date utilDateToSqlDate(Date utilDate) {
        long timeInMilliSeconds = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
        return sqlDate;
    }
}
