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
            pst = conn.prepareStatement("INSERT INTO emprestimo (ep_montante,ep_juros,ep_total, ep_prestacoes, ep_prazo, ep_data_emprestimo, ep_fk_clientes,ep_frequenciaPagamento, status) VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            pst.setDouble(1, obj.getEp_montante());
            pst.setDouble(2, obj.getEp_juros());
            pst.setDouble(3, obj.getEp_total());
            pst.setInt(4, obj.getEp_prestacoes());
            pst.setString(5, obj.getEp_prazo());
            pst.setString(6, obj.getEp_data_emprestimo());
            pst.setInt(7, obj.getCliente().getCli_id());
            pst.setInt(8, obj.getEp_frequenciaPagamento());
            pst.setString(9, obj.getStatus());
          
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                 if(rs.next()){
                    int id = rs.getInt(1); 
                    obj.setEp_id(id);
                    DB.closeResultSet(rs);
                }
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
            pst = conn.prepareStatement("UPDATE emprestimo SET ep_montante=?,ep_juros=?,ep_total=?, ep_prestacoes=?, ep_prazo=?, ep_data_emprestimo=?, ep_fk_clientes=?,ep_frequenciaPagamento=? WHERE ep_id=?");

            pst.setDouble(1, obj.getEp_montante());
            pst.setDouble(2, obj.getEp_juros());
            pst.setDouble(3, obj.getEp_total());
            pst.setInt(4, obj.getEp_prestacoes());
            pst.setString(5, obj.getEp_prazo());
            pst.setString(6, obj.getEp_data_emprestimo());
            pst.setInt(7, obj.getCliente().getCli_id());
            pst.setInt(8, obj.getEp_frequenciaPagamento());
            pst.setInt(9, obj.getEp_id());

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

            pst.setInt(1, obj.getEp_id());
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
            pst = conn.prepareStatement("SELECT * FROM clientes JOIN emprestimo on emprestimo.ep_fk_clientes=cli_id JOIN empresa");

            rs = pst.executeQuery();
            List<Emprestimo> list = new ArrayList<>();
            while (rs.next()) {
                Emprestimo ep = fecthData(rs);
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
    public List<Emprestimo> findAllCreditoById(String id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT ep_id, ep_montante, ep_juros, ep_prestacoes, ep_frequenciaPagamento, ep_total, ep_prazo, ep_data_emprestimo, ep_fk_clientes,cli_id,cli_telefone,cli_nome, cli_endereco, cli_tipo_documento, cli_numero, cli_data_emissao, cli_data_validade, cli_estado_civil, cli_arquivo_identificacao, cli_quarteirao, cli_casa_numero, cli_data_de_nascimento, cli_ocupacao, nome_conjugue, con_tipo_documento, con_data_de_emissao, con_data_de_validade, con_Ocupacao, cli_data_registro, cli_local_nascimento  FROM clientes WHERE cli_id=?");

            pst.setString(1, id);
            rs = pst.executeQuery();
            List<Emprestimo> list = new ArrayList<>();
            while (rs.next()) {
                Emprestimo ep = fecthData(rs);
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
            pst = conn.prepareStatement("SELECT * FROM emprestimo join clientes on cli_id=ep_fk_clientes where ep_id like ? OR cli_nome like ?");
            pst.setString(1, id + "%");
            pst.setString(2, id + "%");
            rs = pst.executeQuery();
            List<Emprestimo> list = new ArrayList<>();
            while (rs.next()) {
                Emprestimo ep = fecthData(rs);
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
        ep.setEp_id(rs.getInt("ep_id"));
        ep.setEp_montante(rs.getDouble("ep_montante"));
        ep.setEp_total(rs.getDouble("ep_total"));
        ep.setEp_juros(rs.getDouble("ep_juros"));
        ep.setEp_prestacoes(rs.getInt("ep_prestacoes"));
        ep.setEp_prazo(rs.getString("ep_prazo"));
        ep.setEp_data_emprestimo(rs.getString("ep_data_emprestimo"));
        ep.setEp_frequenciaPagamento(rs.getInt("ep_frequenciaPagamento"));

        Cliente cliente = new Cliente();
        cliente.setCli_nome(rs.getString("cli_nome"));
        cliente.setCli_id(rs.getInt("cli_id"));
        cliente.setCli_endereco(rs.getString("cli_endereco"));
        cliente.setCli_telefone(rs.getInt("cli_telefone"));
        cliente.setCli_tipo_documento(rs.getString("cli_tipo_documento"));
        cliente.setCli_numero(rs.getString("cli_numero"));
        cliente.setCli_estado_civil(rs.getString("cli_estado_civil"));
        cliente.setCli_arquivo_identificacao(rs.getString("cli_arquivo_identificacao"));
        cliente.setCli_quarteirao(rs.getString("cli_quarteirao"));
        cliente.setCli_casa_numero(rs.getString("cli_casa_numero"));
        cliente.setCli_data_emissao(rs.getString("cli_data_emissao"));
        cliente.setCli_data_validade(rs.getString("cli_data_validade"));
        cliente.setCli_ocupacao(rs.getString("cli_ocupacao"));
        cliente.setCli_local_nascimento(rs.getString("cli_local_nascimento"));

        cliente.setCli_data_de_nascimento(rs.getString("cli_data_de_nascimento"));
        cliente.setCon_data_de_emissao(rs.getString("con_data_de_emissao"));
        cliente.setCon_data_de_validade(rs.getString("con_data_de_validade"));
        cliente.setCon_Ocupacao(rs.getString("con_Ocupacao"));
        cliente.setCon_tipo_documento(rs.getString("con_tipo_documento"));
        cliente.setCli_data_registro(rs.getString("cli_data_registro"));
        cliente.setNome_conjugue(rs.getString("nome_conjugue"));
        ep.setCliente(cliente);
        return ep;

    }

    private void InstaciatePST(PreparedStatement pst, Emprestimo obj) throws SQLException {
        pst.setDouble(1, obj.getEp_montante());
        pst.setDouble(2, obj.getEp_juros());
        pst.setDouble(3, obj.getEp_total());
        pst.setInt(4, obj.getEp_prestacoes());
        pst.setString(5, obj.getEp_prazo());
        pst.setString(6, obj.getEp_data_emprestimo());
        pst.setInt(7, obj.getCliente().getCli_id());
        pst.setInt(8, obj.getEp_frequenciaPagamento());
        pst.setString(9, obj.getStatus());
        if (obj.getEp_id() != null) {
            pst.setInt(10, obj.getEp_id());
        }
    }

    @Override
    public void updateData(Emprestimo obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE  emprestimo SET ep_prazo=? WHERE ep_id=?");
            pst.setString(1, obj.getEp_prazo());
            pst.setInt(2, obj.getEp_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void updateStatus(Emprestimo obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE  emprestimo SET status=? WHERE ep_id=?");
            pst.setString(1, obj.getStatus());
            pst.setInt(2, obj.getEp_id());
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

    @Override
    public List<Emprestimo> findAllCreditoByStatus() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM clientes JOIN emprestimo on emprestimo.ep_fk_clientes=cli_id WHERE status='nao pago'");

            rs = pst.executeQuery();
            List<Emprestimo> list = new ArrayList<>();
            while (rs.next()) {
                Emprestimo ep = fecthData(rs);
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
}
