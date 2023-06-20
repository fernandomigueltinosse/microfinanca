/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.dao;

import java.util.ArrayList;
import modelo.entities.Documentos;

/**
 *
 * @author pc2
 */
public interface DocumentosDao {
    
    void insert(Documentos doc);
    void update(Documentos doc);
    void update1(Documentos doc);
    void delete(Integer id);
    void findById(Integer id);
    Documentos findById2(Integer id);
    ArrayList<Documentos> findAll();
    ArrayList<Documentos> filtrar(String id);
}
