package com.github.gym;

import clases.Reservation;
import clases.User;
import db.DAOReservation;
import db.DAOReservationJDBC;
import db.DAOUserJDBC;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Prueba {

    public static void main(String[] args){

        Date fecha = new Date();
        String fechaStr = "24/11/2001";
        try {
            fecha = java.sql.Date.valueOf(LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("d/MM/yyyy")));
        }catch (Exception e){
            System.out.println("Fecha incorrecta");
        }
        User user = new User("Y12638888", "Ronald", "Intriago", fecha);
        DAOUserJDBC daoUserJDBC = new DAOUserJDBC();


        DAOReservation daoReservation = new DAOReservationJDBC();
        Reservation reservation = new Reservation(fecha, user.getUserCode(), "Zumba", true);
        //daoReservation.insert(reservation);
        List<Reservation> reservations = daoReservation.getAll();
        for (Reservation r : reservations){
            System.out.println(r.getRoomName());
        }

        //daoReservation.delete(reservations.get(0));
        //daoReservation.update(reservation, reservations.get(0).getReservationCode());
        //User user1 = daoUserJDBC.getUserByDNI("Y2645188");
        User user1 = daoUserJDBC.getUserByDNI("Y2645188");
        System.out.println("User founded: " + user1.getName());

        //daoUserJDBC.insert(user);
        /*List<User> users = daoUserJDBC.getAll();
        for (User usuario : users) {
            System.out.println(usuario.getName());
        }*/

        //daoUserJDBC.delete(users.get(0));
        //daoUserJDBC.update(user, users.get(0).getUserCode());


    }
}
