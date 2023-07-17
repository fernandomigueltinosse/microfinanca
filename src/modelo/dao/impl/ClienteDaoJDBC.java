/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao.impl;

import db.DB;
import db.DbException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.dao.ClienteDao;
import modelo.entities.Cliente;

public class ClienteDaoJDBC implements ClienteDao {

    private final Connection conn;
    
    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO clientes(cli_nome, cli_endereco, cli_telefone, cli_tipo_documento, cli_numero, cli_data_emissao, cli_data_validade, cli_foto, cli_estado_civil, cli_arquivo_identificacao, cli_quarteirao, cli_casa_numero, cli_data_de_nascimento, cli_ocupacao, nome_conjugue, con_tipo_documento, con_data_de_emissao, con_data_de_validade, con_Ocupacao, cli_data_registro,cli_local_nascimento,con_numero,com_arquivo_identificacao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            //instatiatePst(pst, obj);
            pst.setString(1, obj.getCli_nome());
            pst.setString(2, obj.getCli_endereco());
            pst.setInt(3, obj.getCli_telefone());
            pst.setString(4, obj.getCli_tipo_documento());
            pst.setString(5, obj.getCli_numero());
            pst.setString(6, obj.getCli_data_emissao());
            pst.setString(7, obj.getCli_data_validade());

            if (obj.getFoto() != null) {
                InputStream photo = new FileInputStream(new File(obj.getFoto()));
                pst.setBlob(8, photo);
            } else {
                pst.setString(8, "");
            }
            pst.setString(9, obj.getCli_estado_civil());
            pst.setString(10, obj.getCli_arquivo_identificacao());
            pst.setString(11, obj.getCli_quarteirao());
            pst.setString(12, obj.getCli_casa_numero());
            pst.setString(13, obj.getCli_data_de_nascimento());
            pst.setString(14, obj.getCli_ocupacao());
            pst.setString(15, obj.getNome_conjugue());
            pst.setString(16, obj.getCon_tipo_documento());
            pst.setString(17, obj.getCon_data_de_emissao());
            pst.setString(18, obj.getCon_data_de_validade());
            pst.setString(19, obj.getCon_Ocupacao());
            pst.setString(20, obj.getCli_data_registro());
            pst.setString(21, obj.getCli_local_nascimento());
            pst.setString(22, obj.getCon_numero());
            pst.setString(23, obj.getCom_arquivo_identificacao());
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            } else {
                throw new DbException("erro inesperado! nenhuma linha foi afectada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ClienteDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void update(Cliente obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE clientes SET cli_nome=?,cli_endereco=?,cli_telefone=?,cli_tipo_documento=?,cli_numero=?,cli_data_emissao=?,cli_data_validade=?,cli_foto=?,cli_estado_civil=?,cli_arquivo_identificacao=?,cli_quarteirao=?,cli_casa_numero=?,cli_data_de_nascimento=?,cli_ocupacao=?,nome_conjugue=?,con_tipo_documento=?,con_data_de_emissao=?,con_data_de_validade=?,con_Ocupacao=?,cli_data_registro=?,cli_local_nascimento=?,con_numero=?,com_arquivo_identificacao=? WHERE cli_id =?");
            pst.setString(1, obj.getCli_nome());
            pst.setString(2, obj.getCli_endereco());
            pst.setInt(3, obj.getCli_telefone());
            pst.setString(4, obj.getCli_tipo_documento());
            pst.setString(5, obj.getCli_numero());
            pst.setString(6, obj.getCli_data_emissao());
            pst.setString(7, obj.getCli_data_validade());
            InputStream photo = new FileInputStream(new File(obj.getFoto()));
            pst.setBlob(8, photo);
            pst.setString(9, obj.getCli_estado_civil());
            pst.setString(10, obj.getCli_arquivo_identificacao());
            pst.setString(11, obj.getCli_quarteirao());
            pst.setString(12, obj.getCli_casa_numero());
            pst.setString(13, obj.getCli_data_de_nascimento());
            pst.setString(14, obj.getCli_ocupacao());
            pst.setString(15, obj.getNome_conjugue());
            pst.setString(16, obj.getCon_tipo_documento());
            pst.setString(17, obj.getCon_data_de_emissao());
            pst.setString(18, obj.getCon_data_de_validade());
            pst.setString(19, obj.getCon_Ocupacao());
            pst.setString(20, obj.getCli_data_registro());
            pst.setString(21, obj.getCli_local_nascimento());
            pst.setString(22, obj.getCon_numero());
            pst.setString(23, obj.getCom_arquivo_identificacao());
            pst.setInt(24, obj.getCli_id());

            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Actualizado com sucesso");
            } else {
                throw new DbException("erro inesperado! nenhuma linha foi afectada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ClienteDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void update2(Cliente obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE clientes SET cli_nome=?,cli_endereco=?,cli_telefone=?,cli_tipo_documento=?,cli_numero=?,cli_data_emissao=?,cli_data_validade=?,cli_estado_civil=?,cli_arquivo_identificacao=?,cli_quarteirao=?,cli_casa_numero=?,cli_data_de_nascimento=?,cli_ocupacao=?,nome_conjugue=?,con_tipo_documento=?,con_data_de_emissao=?,con_data_de_validade=?,con_Ocupacao=?,cli_data_registro=?,cli_local_nascimento=?,con_numero=?,com_arquivo_identificacao=? WHERE cli_id =?");
            pst.setString(1, obj.getCli_nome());
            pst.setString(2, obj.getCli_endereco());
            pst.setInt(3, obj.getCli_telefone());
            pst.setString(4, obj.getCli_tipo_documento());
            pst.setString(5, obj.getCli_numero());
            pst.setString(6, obj.getCli_data_emissao());
            pst.setString(7, obj.getCli_data_validade());
            pst.setString(8, obj.getCli_estado_civil());
            pst.setString(9, obj.getCli_arquivo_identificacao());
            pst.setString(10, obj.getCli_quarteirao());
            pst.setString(11, obj.getCli_casa_numero());
            pst.setString(12, obj.getCli_data_de_nascimento());
            pst.setString(13, obj.getCli_ocupacao());
            pst.setString(14, obj.getNome_conjugue());
            pst.setString(15, obj.getCon_tipo_documento());
            pst.setString(16, obj.getCon_data_de_emissao());
            pst.setString(17, obj.getCon_data_de_validade());
            pst.setString(18, obj.getCon_Ocupacao());
            pst.setString(19, obj.getCli_data_registro());
            pst.setString(20, obj.getCli_local_nascimento());
            pst.setString(21, obj.getCon_numero());
            pst.setString(22, obj.getCom_arquivo_identificacao());
            pst.setInt(23, obj.getCli_id());
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Actualizado com sucesso");
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
    public void delete(Cliente obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("DELETE from clientes where cli_id=?");
            pst.setInt(1, obj.getCli_id());
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Apagado com sucesso");
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
    public Cliente findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM clientes where cli_id=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Cliente cliente = instatiateClient(rs);
                return cliente;
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
    public List<Cliente> findAllCliente() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM clientes ORDER BY cli_id  DESC LIMIT 50");
            rs = pst.executeQuery();
            List<Cliente> list = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = instatiateClient(rs);
                list.add(cliente);
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
    public List<Cliente> findByName(String nome) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM clientes  where cli_nome like ? ORDER BY cli_id DESC LIMIT 50");
            pst.setString(1, nome + "%");
            rs = pst.executeQuery();
            List<Cliente> list = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = instatiateClient(rs);
                list.add(cliente);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    private Cliente instatiateClient(ResultSet rs) throws SQLException {
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
        cliente.setCon_numero(rs.getString("con_numero"));
        cliente.setCom_arquivo_identificacao(rs.getString("com_arquivo_identificacao"));
        cliente.setCli_data_de_nascimento(rs.getString("cli_data_de_nascimento"));
        cliente.setCon_data_de_emissao(rs.getString("con_data_de_emissao"));
        cliente.setCon_data_de_validade(rs.getString("con_data_de_validade"));
        cliente.setCon_Ocupacao(rs.getString("con_Ocupacao"));
        cliente.setCon_tipo_documento(rs.getString("con_tipo_documento"));
        cliente.setCli_data_registro(rs.getString("cli_data_registro"));
        cliente.setNome_conjugue(rs.getString("nome_conjugue"));
        if (rs.getBytes("cli_foto") != null) {
            byte[] img = rs.getBytes("cli_foto");
            cliente.setCli_foto(img);
        }
        return cliente;
    }



    @Override
    public boolean ifClientExist(String text) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM clientes WHERE cli_nome=?");
            pst.setString(1, text);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
        }
        return false;

    }
}
