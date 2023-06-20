/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.dao;

import java.util.List;
import modelo.entities.Pagamentos;

/**
 *
 * @author Tomas
 */
public interface PagamentoDao {
    void insert(Pagamentos obj);
    void updatePrestcoes(Pagamentos obj);
    List<Pagamentos> finfAll();
    List<Pagamentos> findByEpId(Integer id);
    Pagamentos findById(Pagamentos obj);
    Integer count(Integer id);
}
