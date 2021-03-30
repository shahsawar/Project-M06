package db;

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

    private final String bbdd = "jdbc:mysql://localhost/gym?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String username = "gym_app";
    private final String passwd = "gym_app123";
    private static Connection conn;

    public ConnexioJDBC() {
    }

    @Override
    public Connection start() {
        try {
            conn = DriverManager.getConnection(bbdd, username, passwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    @Override
    public void close() {
        try {
            conn.close();
            System.out.println("Se ha cerrado la conexión con la BBDD");
        } catch (SQLException ex) {
            System.err.println("Error: No se ha podido cerrar la conexón con la BBDD");
        }
    }
}
