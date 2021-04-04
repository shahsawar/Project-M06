package dao;

import clases.User;
import db.ConnexioJDBC;
import execptions.DatabaseNotAvailableExecption;
import execptions.KeyException;
import utilities.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static utilities.Converter.stringToDate;

/**
 * @author ronald
 */

public class DAOUserJDBC implements DAOUser {

    /**
     * Insert an object of type User in the database
     *
     * @param user user to insert into database
     * @throws DatabaseNotAvailableExecption
     * @throws KeyException
     */
    @Override
    public void insert(User user) throws DatabaseNotAvailableExecption, KeyException {

        User userTmp = getUserByDNI(user.getDni());

        if (userTmp != null) {//User with the same dni already exist
            throw new KeyException();
        } else {
            int insercion = 0;
            java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime()); //Fecha en formato sql
            int lastUserCode = getLastUserId();
            if (lastUserCode == -1) {
                lastUserCode = 1;
            } else {
                lastUserCode++;
            }
            user.setUserCode(lastUserCode);
            String sentenciaSQL = "INSERT INTO user (user_code, dni, name, lastname, birthdate) VALUES (?,?,?,?,?)";
            PreparedStatement sentenciaPreparada = null;

            ConnexioJDBC connexioJDBC = new ConnexioJDBC();

            try {
                sentenciaPreparada = connexioJDBC.start().prepareStatement(sentenciaSQL);
                sentenciaPreparada.setInt(1, user.getUserCode());
                sentenciaPreparada.setString(2, user.getDni());
                sentenciaPreparada.setString(3, user.getName());
                sentenciaPreparada.setString(4, user.getLastname());
                sentenciaPreparada.setString(5, sqlDate + "");
                insercion = sentenciaPreparada.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                connexioJDBC.close();
            }

            if (insercion == 1) {
                Log.info("User with dni " + user.getDni() + " added correctly");
            } else {
                Log.info("An error occurred while inserting the user with DNI: " + user.getDni());
            }
        }
    }


    /**
     * Removes an object of type User from the database
     *
     * @param user user to remove from database
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void delete(User user) throws DatabaseNotAvailableExecption {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();

        try {

            String sentenciaSQL = "DELETE FROM user WHERE user_code = " + user.getUserCode();
            PreparedStatement preparedStatement = connexioJDBC.start().prepareStatement(sentenciaSQL);
            preparedStatement.executeUpdate();
            Log.info("User " + user.getUserCode() + " has been deleted");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();//Close connection
        }

    }

    /**
     * Updates the object of type User with identifier integer in the database
     *
     * @param user user to update in database
     * @param code
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public void update(User user, Integer code) throws DatabaseNotAvailableExecption {
        ConnexioJDBC connexioJDBC = new ConnexioJDBC();

        java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime()); //Fecha en formato sql
        try {
            PreparedStatement updateEXP = connexioJDBC.start().prepareStatement("UPDATE user SET dni = ?, name = ?, lastname = ?, birthdate = ? WHERE user_code= " + code);
            updateEXP.setString(1, user.getDni());
            updateEXP.setString(2, user.getName());
            updateEXP.setString(3, user.getLastname());
            updateEXP.setString(4, sqlDate + "");
            updateEXP.executeUpdate();
            Log.info("User " + code + " has been updaed");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();//Close connection
        }
    }


    /**
     * Returns a list of type User
     *
     * @return Return a list of type User
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public List<User> getAll() throws DatabaseNotAvailableExecption {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();

        Statement statement = null;
        List<User> users = new ArrayList<>();
        try {
            statement = connexioJDBC.start().createStatement();
            String sentenciaSQL = "SELECT *  FROM user;";
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                User user = new User();
                user.setUserCode(rs.getInt("user_code"));
                user.setDni(rs.getString("dni"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setBirthDate(stringToDate(rs.getString("birthdate")));

                DAOReservationJDBC daoReservationJDBC = new DAOReservationJDBC();
                user.setReservations(daoReservationJDBC.getUserReservations(user.getUserCode()));

                users.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();//Close connection
        }
        return users;
    }


    /**
     * Returns an object of type User
     *
     * @param code identifier
     * @return return object User
     * @throws DatabaseNotAvailableExecption
     */
    @Override
    public User getByIdentifier(Integer code) throws DatabaseNotAvailableExecption {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();

        Statement statement = null;
        User user = new User();
        try {
            statement = connexioJDBC.start().createStatement();
            String sentenciaSQL = "SELECT *  FROM user WHERE user_code = " + code;
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                user.setUserCode(rs.getInt("user_code"));
                user.setDni(rs.getString("dni"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setBirthDate((Date) rs.getDate("birthdate"));
            }

            DAOReservationJDBC daoReservationJDBC = new DAOReservationJDBC();
            user.setReservations(daoReservationJDBC.getUserReservations(user.getUserCode()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();//Close connection
        }

        return user;
    }


    @Override
    public int getLastUserId() throws DatabaseNotAvailableExecption {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();

        Statement statement = null;
        int lastCode = 0;
        try {
            statement = connexioJDBC.start().createStatement();
            String sentenciaSQL = "SELECT *  FROM user ORDER BY user_code DESC LIMIT 1;";//Recogemos el último registro
            ResultSet rs = statement.executeQuery(sentenciaSQL);

            int contador = 0;

            while (rs.next()) {

                lastCode = rs.getInt("user_code");
                contador++;
            }

            if (contador > 0) {
                System.out.println("Se han recuperado " + contador + " cliente(s)");
            } else {
                lastCode = -1;//La tabla está vacía
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();//Close connection
        }

        return lastCode;
    }


    @Override
    public User getUserByDNI(String dni) throws DatabaseNotAvailableExecption {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();

        Statement statement = null;
        User user = new User();
        try {
            statement = connexioJDBC.start().createStatement();
            String sentenciaSQL = "SELECT *  FROM user WHERE dni = '" + dni + "';";

            ResultSet rs = statement.executeQuery(sentenciaSQL);

            while (rs.next()) {
                user.setUserCode(rs.getInt("user_code"));
                user.setDni(rs.getString("dni"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setBirthDate((Date) rs.getDate("birthdate"));
            }

            DAOReservationJDBC daoReservationJDBC = new DAOReservationJDBC();
            user.setReservations(daoReservationJDBC.getUserReservations(user.getUserCode()));

            if (user.getDni() == null) {
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connexioJDBC.close();//Close connection
        }

        return user;
    }
}
