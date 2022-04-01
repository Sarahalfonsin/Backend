package com.company;

public class Ejecutable {
    private static final String SQL_TABLE_CREATE = "DROP TABLE IF EXISTS ODONTOLOGO; CREATE TABLE ODONTOLOGO"
            + "("
            + "ID INT PRIMARY KEY,"
            + "NOMBRE varchar(100) NOT NULL,"
            + "APELLIDO varchar(100) NOT NULL,"
            + "MATRICULA  varchar(100) NOT NULL"
            + ")";

    private static final String SQL_INSERT = "INSERT INTO ODONTOLOGO (ID, NOMBRE, APELLIDO, MATRICULA) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE  ODONTOLOGO SET APELLIDO = ? WHERE NOMBRE = ?";
}