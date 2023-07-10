/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao.impl;

import db.DB;
import db.DbException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.dao.MultasDao;
import modelo.entities.Cliente;
import modelo.entities.Emprestimo;

import modelo.entities.Multas;

/**
 *
 * @author pc2
 */
public class MultasDaoJDBC implements MultasDao {

    private final Connection conn;

    public MultasDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Multas obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO multas(multas, m_fk_cliente) VALUES (?,?)");
            pst.setDouble(1, obj.getMulta());
            pst.setInt(2, obj.getCliente().getCli_id());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("DELETE FROM multas WHERE m_id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Apagado com sucesso");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public List<Multas> filter(String text) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM multas join clientes on m_fk_cliente=cli_id where cli_nome like ?");
            pst.setString(1, text + "%");
            rs = pst.executeQuery();
            List<Multas> list = new ArrayList<>();
            while (rs.next()) {
                Multas m = new Multas();
                m.setM_id(rs.getInt("m_id"));
                m.setMulta(rs.getDouble("multas"));
                Cliente ep = new Cliente();
                ep.setCli_nome(rs.getString("cli_nome"));
                m.setCliente(ep);
                list.add(m);
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
    public List<Multas> findAllMultas() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM multas join clientes where m_fk_cliente=cli_id  ");
            rs = pst.executeQuery();
            List<Multas> list = new ArrayList<>();
            while (rs.next()) {
                Multas m = new Multas();
                m.setM_id(rs.getInt("m_id"));
                m.setMulta(rs.getDouble("multas"));
                Cliente ep = new Cliente();
                ep.setCli_id(rs.getInt("m_fk_cliente"));
                ep.setCli_nome(rs.getString("cli_nome"));
                m.setCliente(ep);
                list.add(m);
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
    public void pagarMulta(Multas obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE multas SET multas_pagas=? WHERE m_id=?");
            pst.setDouble(1, obj.getPagarMulta());
            pst.setInt(2, obj.getM_id());
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

}
