/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.dao;

import java.util.List;
import modelo.entities.User;

/**
 *
 * @author Tomas
 */
public interface UserDao {
    void insert(User obj);
    void update(User obj);
    void delete(User obj);
    User login(User obj);
    List<User> findAllUser();
    List<User> searchByName(String nome);
    User findUserById(Integer id);
}
