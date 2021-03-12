package com.github.gym;

import clases.User;
import db.DAOUserJDBC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Prueba {

    public static void main(String[] args){
        Date fecha = new Date();
        String fechaStr = "24/11/2001";
        try {
            fecha = java.sql.Date.valueOf(LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("d/MM/yyyy")));
        }catch (Exception e){
            System.out.println("Fecha incorrecta");
        }
        User user = new User("Y12345678", "Jose", "Intri", fecha);
        DAOUserJDBC daoUserJDBC = new DAOUserJDBC();
        daoUserJDBC.insert(user);


    }
}
