/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.dao;

import java.util.List;
import modelo.entities.Emprestimo;

/**
 *
 * @author Tomas
 */
public interface EmprestimoDao {
    void insert(Emprestimo obj);
    void update (Emprestimo obj);
    void delete(Emprestimo obj);
    void updateData(Emprestimo obj);
    void updatePrestacoes(Integer prestacoes);
    void updateStatus(Emprestimo obj);
    Integer findByidFk(Integer id);
    Emprestimo findById(Integer id);
    List<Emprestimo> findAllCredito();
    List<Emprestimo> findAllCreditoByStatus();
    List<Emprestimo> findAllCreditoById(String id);
    List<Emprestimo> findByName(String nome);
}
