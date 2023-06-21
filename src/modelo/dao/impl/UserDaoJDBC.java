/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.dao.UserDao;
import modelo.entities.User;

/**
 *
 * @author Tomas
 */
public class UserDaoJDBC implements UserDao {

    private final Connection conn;

    public UserDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(User obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO user (user_FirstName, user_LastName, user_email, user_password, user_function,username) VALUES (?,?,?,?,?,?)");
            pst.setString(1, obj.getUser_fistNome());
            pst.setString(2, obj.getUser_lastNome());
            pst.setString(3, obj.getUser_email());
            pst.setString(4, obj.getUser_password());
            pst.setString(5, obj.getUser_function());
            pst.setString(6, obj.getUserName());
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            } else {
                throw new DbException("erro inesperado! nenhuma linha foi afectada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void update(User obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE user SET user_FirstName=?,user_LastName=?,user_email=?,user_password=?,user_function=?,username=? WHERE user_id=?");
            pst.setString(1, obj.getUser_fistNome());
            pst.setString(2, obj.getUser_lastNome());
            pst.setString(3, obj.getUser_email());
            pst.setString(4, obj.getUser_password());
            pst.setString(5, obj.getUser_function());
            pst.setString(6, obj.getUserName());
            pst.setInt(7, obj.getUser_id());
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Actualizado com sucesso");
            } else {
                throw new DbException("erro inesperado! nenhuma linha foi afectada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void delete(User obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("DELETE FROM user where user_id=?");
            pst.setInt(1, obj.getUser_id());
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Apagado com sucesso");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public List<User> findAllUser() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM user");
            rs = pst.executeQuery();
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                User user = instatiateUser(rs);
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public User findUserById(Integer id) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM user WHERE user_id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = instatiateUser(rs);
                return user;
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
        return null;
    }

    @Override
    public List<User> searchByName(String nome) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM user where user_firstName like ?");
            pst.setString(1, nome + "%");
            rs = pst.executeQuery();
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                User user = instatiateUser(rs);
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    private User instatiateUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setUser_fistNome(rs.getString("user_firstName"));
        user.setUser_lastNome(rs.getString("user_lastName"));
        user.setUser_email(rs.getString("user_email"));
        user.setUser_function(rs.getString("user_function"));
        user.setUser_password(rs.getString("user_password"));
        user.setUserName(rs.getString("username"));
        return user;
    }

    @Override
    public User login(User obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM user WHERE username=? and user_password=?");
            pst.setString(1, obj.getUserName());
            pst.setString(2, obj.getUser_password());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = instatiateUser(rs);
                return user;
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
        return null;
    }

    @Override
    public boolean ifUserExist(String user) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM user WHERE username=?");
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
        return false;

    }
}
