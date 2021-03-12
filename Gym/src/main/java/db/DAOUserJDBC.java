package db;

import clases.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DAOUserJDBC implements DAOUser{
    @Override
    public void insert(User user) {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();

        int insercion = 0;
        java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime()); //Fecha en formato sql
        int lastUserCode = getLastUserId();
        if (lastUserCode == -1 ){
            lastUserCode = 1;
        } else {
            lastUserCode++;
        }
        String sentenciaSQL = "INSERT INTO user (user_code, dni, name, lastname, birthdate) VALUES (?,?,?,?,?)";
        PreparedStatement sentenciaPreparada = null;

        try {
            sentenciaPreparada = connexioJDBC.con.prepareStatement(sentenciaSQL);
            sentenciaPreparada.setInt(1,lastUserCode);
            sentenciaPreparada.setString(2,user.getDni());
            sentenciaPreparada.setString(3,user.getName());
            sentenciaPreparada.setString(4,user.getLastname());
            sentenciaPreparada.setString(5,sqlDate + "");
            insercion = sentenciaPreparada.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

        if (insercion == 1) {
            System.out.println("Cliente añadido correctamente");
        } else {
            System.out.println("Ha ocurrido un error al insertar el cliente con DNI: " + user.getDni());
        }

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByIdentifier(Integer integer) {
        return null;
    }

    @Override
    public int getLastUserId() {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        Statement statement = null;
        int lastCode = 0;
        try {
            statement = connexioJDBC.con.createStatement();
            String sentenciaSQL = "SELECT *  FROM user ORDER BY user_code DESC LIMIT 1;";//Recogemos el último registro
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            int contador = 0;

            while (rs.next()) {

                lastCode = rs.getInt("user_code");
                contador++;
            }

            if(contador > 0) {
                System.out.println("Se han recuperado " + contador + " cliente(s)");
            } else {
                lastCode = -1;//La tabla está vacía
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

        return lastCode;
    }

}
