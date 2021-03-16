package db;

import clases.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ronald
 */

public class DAOUserJDBC implements DAOUser{

    private Date stringToDate(String strDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;

        try {
            //Parsing the String
            fecha = formatter.parse(strDate);

        } catch (ParseException e) {

            System.out.println("Error: " + e);
        }


        return fecha;
    }

    /***
     *
     * @param user
     */
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
        user.setUserCode(lastUserCode);
        String sentenciaSQL = "INSERT INTO user (user_code, dni, name, lastname, birthdate) VALUES (?,?,?,?,?)";
        PreparedStatement sentenciaPreparada = null;

        try {
            sentenciaPreparada = connexioJDBC.con.prepareStatement(sentenciaSQL);
            sentenciaPreparada.setInt(1,user.getUserCode());
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

    /**
     * @param user
     */
    @Override
    public void delete(User user) {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        try {

            String sentenciaSQL = "DELETE FROM user WHERE user_code = " + user.getUserCode();
            PreparedStatement preparedStatement = connexioJDBC.con.prepareStatement(sentenciaSQL);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

    }

    @Override
    public void update(User user, Integer code) {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();

        java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime()); //Fecha en formato sql
        try {

            PreparedStatement updateEXP = connexioJDBC.con.prepareStatement("UPDATE user SET dni = ?, name = ?, lastname = ?, birthdate = ? WHERE");
            updateEXP.setString(1, user.getDni());
            updateEXP.setString(2, user.getName());
            updateEXP.setString(3, user.getLastname());
            updateEXP.setString(4, sqlDate + "");
            int result = updateEXP.executeUpdate();
            System.out.println(result + " rows updated");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }
    }

    @Override
    public List<User> getAll() {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connexioJDBC.con.createStatement();
            String sentenciaSQL = "SELECT *  FROM user;";
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                User user = new User();
                user.setUserCode(rs.getInt("user_code"));
                user.setDni(rs.getString("dni"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setBirthDate(stringToDate(rs.getString("birthdate")));

                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }
        return users;
    }

    @Override
    public User getByIdentifier(Integer integer) {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();
        Statement statement = null;
        User user = new User();
        try {
            statement = connexioJDBC.con.createStatement();
            String sentenciaSQL = "SELECT *  FROM user WHERE user_code = " + integer;
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                user.setUserCode(rs.getInt("user_code"));
                user.setDni(rs.getString("dni"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setBirthDate((Date)rs.getDate("birthdate"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();
        }

        return user;
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
