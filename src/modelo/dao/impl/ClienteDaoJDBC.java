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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
            pst = conn.prepareStatement("INSERT INTO clientes(cli_nome, cli_endereco, cli_telefone, cli_tipo_documento, cli_numero, cli_data_emissao, cli_data_validade, cli_foto, cli_data_registro) VALUES (?,?,?,?,?,?,?,?,?)");

            instatiatePst(pst, obj);
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
            pst = conn.prepareStatement("UPDATE clientes SET cli_nome=?,cli_endereco=?,cli_telefone=?,cli_tipo_documento=?,cli_numero=?,cli_data_emissao=?,cli_data_validade=?,cli_foto=?,cli_data_registro=? WHERE cli_id=?");
            pst.setString(1, obj.getCli_nome());
            pst.setString(2, obj.getCli_endereco());
            pst.setInt(3, obj.getCli_telefone());
            pst.setString(4, obj.getCli_tipo_documento());
            pst.setString(5, obj.getCli_numero());
            pst.setDate(6, new Date(obj.getCli_data_emissao().getTime()));
            pst.setDate(7, new Date(obj.getCli_data_validade().getTime()));

            InputStream photo = new FileInputStream(new File(obj.getFoto()));
            pst.setBlob(8, photo);
            pst.setDate(9, new Date(obj.getCli_data_registro().getTime()));

            pst.setInt(10, obj.getCli_id());

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
            pst = conn.prepareStatement("UPDATE clientes SET cli_nome=?,cli_endereco=?,cli_telefone=?,cli_tipo_documento=?,cli_numero=?,cli_data_emissao=?,cli_data_validade=?,cli_data_registro=? WHERE cli_id=?");
             pst.setString(1, obj.getCli_nome());
            pst.setString(2, obj.getCli_endereco());
            pst.setInt(3, obj.getCli_telefone());
            pst.setString(4, obj.getCli_tipo_documento());
            pst.setString(5, obj.getCli_numero());
            pst.setDate(6, new Date(obj.getCli_data_emissao().getTime()));
            pst.setDate(7, new Date(obj.getCli_data_validade().getTime()));
            pst.setDate(8, new Date(obj.getCli_data_registro().getTime()));
            pst.setInt(9, obj.getCli_id());
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
            pst = conn.prepareStatement("SELECT * FROM clientes");
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
            pst = conn.prepareStatement("SELECT * FROM clientes  where cli_nome like ?");
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
        cliente.setCli_data_emissao(rs.getDate("cli_data_emissao"));
        cliente.setCli_data_validade(rs.getDate("cli_data_validade"));
        cliente.setCli_data_registro(rs.getDate("cli_data_registro"));
        if (rs.getBytes("cli_foto") != null) {
            byte[] img = rs.getBytes("cli_foto");
            cliente.setCli_foto(img);
        }
        return cliente;
    }

    private void instatiatePst(PreparedStatement pst, Cliente obj) throws SQLException, IOException {
        pst.setString(1, obj.getCli_nome());
        pst.setString(2, obj.getCli_endereco());
        pst.setInt(3, obj.getCli_telefone());
        pst.setString(4, obj.getCli_tipo_documento());
        pst.setString(5, obj.getCli_numero());
        pst.setDate(6, new Date(obj.getCli_data_emissao().getTime()));
        pst.setDate(7, new Date(obj.getCli_data_validade().getTime()));
        if (obj.getFoto() != null) {
            InputStream photo = new FileInputStream(new File(obj.getFoto()));
            pst.setBlob(8, photo);
        } else {
            pst.setString(8, "");
        }

        pst.setDate(9, new Date(obj.getCli_data_registro().getTime()));
        if (obj.getCli_id() != null) {
            pst.setInt(10, obj.getCli_id());
        }
    }

}
