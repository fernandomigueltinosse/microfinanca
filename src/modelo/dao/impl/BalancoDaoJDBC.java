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

import modelo.dao.balancoDao;
import modelo.entities.Balanco;
import modelo.entities.Pagamentos;
import java.sql.Date;

/**
 *
 * @author pc2
 */
public class BalancoDaoJDBC implements balancoDao{
    private final Connection conn;

    public BalancoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Balanco creditos(String dataInicio, String dataFinal) {
         
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT SUM(pag_valor_pago) as receita from pagamentos WHERE DATE(pag_data_registro) BETWEEN ? and ?");
            pst.setString(1,  dataInicio );
            pst.setString(2, dataFinal);
            rs = pst.executeQuery();
            while (rs.next()) {
              Balanco bl = new Balanco();
              bl.setTotal_credito(rs.getDouble("receita"));
              
              return bl;
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
        return null;
         
    }

       @Override
    public Balanco creditos2(String mes, String ano) {
         
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT SUM(pag_valor_pago) as receita from pagamentos WHERE  SUBSTRING(pag_data_registro, 4, 2) =? AND  SUBSTRING(pag_data_registro, 7, 4) = ?");
            pst.setString(1,  mes );
            pst.setString(2, ano);
            rs = pst.executeQuery();
            while (rs.next()) {
              Balanco bl = new Balanco();
              bl.setTotal_credito(rs.getDouble("receita"));
              
              return bl;
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
        return null;
         
    }
    
    @Override
    public Balanco debitos(String dataInicio, String dataFinal) {
         PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT SUM(ep_montante), SUM(ep_total-ep_montante),SUM(ep_total) FROM emprestimo WHERE DATE(ep_data_emprestimo) BETWEEN ? and ?");
            pst.setString(1,  dataInicio );
            pst.setString(2, dataFinal);
            rs = pst.executeQuery();
            while (rs.next()) {
              Balanco bl = new Balanco();
              bl.setTotal_debito(rs.getDouble("SUM(ep_montante)"));
              bl.setJuros(rs.getDouble("SUM(ep_total-ep_montante)"));
              bl.setTotal(rs.getDouble("SUM(ep_total)"));
              return bl;
            }
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
        return null;
    }

    
   
    
    
}
