package db;

import exceptions.DatabaseNotAvailableExecption;
import utilities.Configuracio;
import utilities.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnexioJDBC implements Connexio<Connection> {

    /*Creación de BBDD User
    CREATE TABLE user (user_code INT NOT NULL , dni VARCHAR(9) NOT NULL, name VARCHAR(20) NOT NULL, lastname VARCHAR(20) NOT NULL, birthdate DATE NOT NULL, PRIMARY KEY (user_code, dni) );


    Creación de BBDD Reservation
    CREATE TABLE reservation (code INT NOT NULL PRIMARY KEY, user_code INT NOT NULL, room_name VARCHAR(20) NOT NULL, has_workout_plane BOOLEAN NOT NULL, date DATE NOT NULL, FOREIGN KEY (user_code) REFERENCES user(user_code));


    //Insert para pruebas
    INSERT INTO user (user_code,dni, name, lastname, birthdate) VALUES (1, "Y26451892", "Ronald", "Intriago", STR_TO_DATE('1-01-2012', '%d-%m-%Y'));
     */
    //Coment de prueba

    private static Connection conn;


    /**
     * Constructs an empty ConnexioJDBC object
     */

    public ConnexioJDBC() {
    }

    @Override
    public Connection start() throws DatabaseNotAvailableExecption {
        try {
            conn = DriverManager.getConnection(Configuracio.URL_DB_JDBC, Configuracio.USER, Configuracio.PASSWORD);
        } catch (SQLException throwables) {
            //Convert StackTraceElement to String
            StringWriter sw = new StringWriter();
            throwables.printStackTrace(new PrintWriter(sw));
            Log.severe("Could not connect to JDBC\n" + sw.toString());
        }
        return conn;
    }

    @Override
    public void close() throws DatabaseNotAvailableExecption {
        try {
            conn.close();
            //System.out.println("Se ha cerrado la conexión con la BBDD");
        } catch (SQLException ex) {
            //Convert StackTraceElement to String
            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            Log.severe("Can't close connection with Mysql database\n" + sw.toString());
        }
    }
}
