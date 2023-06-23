/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.dao;

import java.util.List;
import modelo.entities.Cliente;

/**
 *
 * @author Tomas
 */
public interface ClienteDao {
    void insert(Cliente obj);
    void update (Cliente obj);
    void update2 (Cliente obj);
    void delete(Cliente obj);
    boolean ifClientExist(String text);
    Cliente findById(Integer id);
    List<Cliente> findAllCliente();
    List<Cliente> findByName(String nome);
}
