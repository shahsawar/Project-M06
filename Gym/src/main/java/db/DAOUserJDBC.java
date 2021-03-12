package db;

import clases.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DAOUserJDBC implements DAOUser{
    @Override
    public void insert(User user) {

        ConnexioJDBC connexioJDBC = new ConnexioJDBC();
        connexioJDBC.start();

        int insercion = 0;
        java.sql.Date sqlDate = new java.sql.Date(user.getBirthDate().getTime()); //Fecha en formato sql

        String sentenciaSQL = "INSERT INTO user (user_code, dni, name, lastname, birthdate) VALUES (?,?,?,?,?)";
        PreparedStatement sentenciaPreparada = null;

        try {
            sentenciaPreparada = connexioJDBC.con.prepareStatement(sentenciaSQL);
            sentenciaPreparada.setString(1,user.getDni() + "");
            sentenciaPreparada.setString(2,user.getName());
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
        return 0;
    }
}
