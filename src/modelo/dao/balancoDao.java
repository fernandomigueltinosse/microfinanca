/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo.dao;
import java.sql.Date;

import modelo.entities.Balanco;

/**
 *
 * @author pc2
 */
public interface balancoDao {
    //Balanco debitos(Date dataInicio, Date dataFinal);
    Balanco creditos(String dataInicio, String dataFinal);
    Balanco creditos2(String mes, String ano);
    Balanco debitos(String dataInicio, String dataFinal);
}
