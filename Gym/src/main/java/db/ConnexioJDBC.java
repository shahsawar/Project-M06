package db;

import utilities.Configuracio;
import utilities.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioJDBC implements Connexio {

    /*Creación de BBDD User
    CREATE TABLE user (user_code INT NOT NULL PRIMARY KEY, dni VARCHAR(9) NOT NULL, name VARCHAR(20) NOT NULL, lastname VARCHAR(20) NOT NULL, birthdate DATE NOT NULL);


    Creación de BBDD Reservation
    CREATE TABLE reservation (code INT NOT NULL PRIMARY KEY, user_code INT NOT NULL, room_name VARCHAR(20) NOT NULL, has_workout_plane BOOLEAN NOT NULL, date DATE NOT NULL, FOREIGN KEY (user_code) REFERENCES user(user_code));


    //Insert para pruebas
    INSERT INTO user (user_code,dni, name, lastname, birthdate) VALUES (1, "Y26451892", "Ronald", "Intriago", STR_TO_DATE('1-01-2012', '%d-%m-%Y'));
     */
    //Coment de prueba

    private static Connection conn;

    public ConnexioJDBC() {
    }

    @Override
    public Connection start() {
        try {

            conn = DriverManager.getConnection(Configuracio.URL_DB_JDBC, Configuracio.USER, Configuracio.PASSWORD);
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            Log.severe("Could not connect to JDBC");
        }
        return conn;
    }

    @Override
    public void close() {
        try {
            conn.close();
            //System.out.println("Se ha cerrado la conexión con la BBDD");
        } catch (SQLException ex) {
            Log.severe("Can't close connection with Mysql database");
        }
    }
}
