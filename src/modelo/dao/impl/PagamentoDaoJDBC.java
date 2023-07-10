/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.dao.PagamentoDao;
import modelo.entities.Cliente;
import modelo.entities.Emprestimo;
import modelo.entities.Pagamentos;

/**
 *
 * @author Tomas
 */
public class PagamentoDaoJDBC implements PagamentoDao {

    private final Connection conn;

    public PagamentoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pagamentos obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO pagamentos ( pag_valor_pago, pag_fk_emprestimo, pag_data_registro) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            InstaciatePST(pst, obj);
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setPg_id(id);
                    DB.closeResultSet(rs);
                }
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
    public List<Pagamentos> finfAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM pagamentos join emprestimo on ep_id=pag_fk_emprestimo JOIN clientes on clientes.cli_id=ep_fk_clientes");
            
            rs = pst.executeQuery();
            List<Pagamentos> list = new ArrayList<>();
            while (rs.next()) {
               Pagamentos  pg = fetchData(rs);
                list.add(pg);
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
    public List<Pagamentos> findByEpId(String id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM pagamentos join emprestimo on ep_id=pag_fk_emprestimo JOIN clientes on clientes.cli_id=ep_fk_clientes where ep_id like ?");
            pst.setString(1, id +"%");
            rs = pst.executeQuery();
            List<Pagamentos> list = new ArrayList<>();
            while (rs.next()) {
               Pagamentos  pg = fetchData(rs);
                list.add(pg);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    private void InstaciatePST(PreparedStatement pst, Pagamentos obj) throws SQLException {
        pst.setDouble(1, obj.getPg_valor_pago());
        pst.setDouble(2, obj.getEmprestimo().getEp_id());
        pst.setString(3 ,obj.getData_pagamento());
        if (obj.getPg_id() != null) {
            pst.setInt(4, obj.getPg_id());
        }
    }

    @Override
    public Pagamentos findById(Pagamentos obj) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM  emprestimo JOIN pagamentos ON ep_id = pag_fk_emprestimo  WHERE pag_id=?");
            pst.setInt(1, obj.getPg_id());
            rs = pst.executeQuery();
            while (rs.next()) {
                Pagamentos ep = fetchData(rs);
                return ep;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    private Pagamentos fetchData(ResultSet rs) throws SQLException {
        Pagamentos pagamento = new Pagamentos();
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setEp_prazo(rs.getString("ep_prazo"));
        emprestimo.setEp_prestacoes(rs.getInt("ep_prestacoes"));
        emprestimo.setEp_id(rs.getInt("ep_id"));
        Cliente cli = new Cliente();
        cli.setCli_nome(rs.getString("cli_nome"));
        emprestimo.setCliente(cli);
        pagamento.setEmprestimo(emprestimo);
        pagamento.setPg_id(rs.getInt("pag_id"));
        pagamento.setPg_valor_pago(rs.getDouble("pag_valor_pago"));
        pagamento.setNumero_prestacao(rs.getInt("numero_prestacao"));
        pagamento.setData_pagamento(rs.getString("pag_data_registro"));
        
        
        return pagamento;

    }

    @Override
    public Integer count(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT COUNT(*) as count  FROM  pagamentos JOIN emprestimo ON ep_id = pag_fk_emprestimo  WHERE pag_fk_emprestimo=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                return count;
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
    public void updatePrestcoes(Pagamentos obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE pagamentos SET numero_prestacao=? WHERE pag_id=?");
            pst.setInt(1, obj.getNumero_prestacao());
            pst.setInt(2, obj.getPg_id());
            pst.executeUpdate();
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }
}
