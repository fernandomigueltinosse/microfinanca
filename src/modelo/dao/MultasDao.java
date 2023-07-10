/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.dao;

import java.util.List;
import modelo.entities.Multas;

/**
 *
 * @author pc2
 */
public interface MultasDao {
    void insert(Multas obj);
    void delete(Integer id);
    List<Multas> filter(String text);
    List<Multas> findAllMultas();
    void pagarMulta(Multas obj);
}
