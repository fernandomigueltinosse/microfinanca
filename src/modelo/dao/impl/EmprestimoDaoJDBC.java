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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.entities.Emprestimo;
import modelo.dao.EmprestimoDao;
import modelo.entities.Cliente;

/**
 *
 * @author Tomas
 */
public class EmprestimoDaoJDBC implements EmprestimoDao {

    private final Connection conn;

    public EmprestimoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Emprestimo obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO emprestimo (ep_montante,ep_juros,ep_total, ep_prestacoes, ep_prazo, ep_data_emprestimo, ep_fk_clientes,ep_frequenciaPagamento) VALUES (?,?,?,?,?,?,?,?)");

            InstaciatePST(pst, obj);
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
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
    public void update(Emprestimo obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE emprestimo SET ep_total=?,ep_juros=?,ep_prestacoes=?,ep_prazo=?,ep_data_emprestimo=?,ep_fk_clientes=?, ep_frequenciaPagamento=? WHERE ep_id=?");

            InstaciatePST(pst, obj);
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
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
    public void delete(Emprestimo obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("DELETE from emprestimo where ep_id=?");

            pst.setInt(1, obj.getCd_id());
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
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
    public Emprestimo findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM emprestimo join clientes on cli_id=ep_fk_clientes where ep_id=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Emprestimo ep = fecthData(rs);
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

    @Override
    public List<Emprestimo> findAllCredito() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM emprestimo join clientes where cli_id=ep_fk_clientes");
            
            rs = pst.executeQuery();
            List<Emprestimo> list = new ArrayList<>();
            while (rs.next()) {
               Emprestimo  ep = fecthData(rs);
                list.add(ep);
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
    public List<Emprestimo> findByName(String id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM emprestimo join clientes on cli_id=ep_fk_clientes where ep_id like ?");
            pst.setString(1, id +"%");
            rs = pst.executeQuery();
            List<Emprestimo> list = new ArrayList<>();
            while (rs.next()) {
               Emprestimo  ep = fecthData(rs);
                list.add(ep);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    private Emprestimo fecthData(ResultSet rs) throws SQLException {
        Emprestimo ep = new Emprestimo();
        ep.setCd_id(rs.getInt("ep_id"));
        ep.setValor_emprestimo(rs.getDouble("ep_montante"));
        ep.setTotal_a_pagar(rs.getDouble("ep_total"));
        ep.setTaxa_juros(rs.getDouble("ep_juros"));
        ep.setPrestacoes(rs.getInt("ep_prestacoes"));
        ep.setPrazo_de_pagamento(rs.getDate("ep_prazo").toLocalDate());
        ep.setData_do_emprestimo(rs.getDate("ep_data_emprestimo").toLocalDate());
        
        ep.setFrequenciaPagamento(rs.getInt("ep_frequenciaPagamento"));
        Cliente cli = new Cliente();
        cli.setCli_nome(rs.getString("cli_nome"));
        ep.setCliente(cli);
        return ep;

    }

    private void InstaciatePST(PreparedStatement pst, Emprestimo obj) throws SQLException {
        pst.setDouble(1, obj.getValor_emprestimo());
        pst.setDouble(2, obj.getTaxa_juros());
        pst.setDouble(3, obj.getTotal_a_pagar());
        pst.setInt(4, obj.getPrestacoes());
        pst.setObject(5, obj.getPrazo_de_pagamento());
        pst.setObject(6, obj.getData_do_emprestimo());
        pst.setInt(7, obj.getCliente().getCli_id());
        pst.setInt(8, obj.getFrequenciaPagamento());
        if (obj.getCd_id() != null) {
            pst.setInt(9, obj.getCd_id());
        }
    }

    @Override
    public void updateData(Emprestimo obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE  emprestimo SET ep_prazo=? WHERE ep_id=?");
            pst.setObject(1, obj.getPrazo_de_pagamento());
            pst.setInt(2, obj.getCd_id());
            pst.executeUpdate();
            
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void updatePrestacoes(Integer prestacoes) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE  emprestimo JOIN pagamentos ON ep_id = pag_fk_emprestimo SET ep_prazo=?   WHERE pag_id=1");
            pst.setInt(1, prestacoes);
            
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
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
    public Integer findByidFk(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT COUNT(*) as count  FROM  emprestimo JOIN pagamentos ON ep_id = pag_fk_emprestimo  WHERE pag_fk_emprestimo=?");
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
}
