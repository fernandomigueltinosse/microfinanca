/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao.impl;

import db.DB;
import db.DbException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.dao.EmpresaDao;
import modelo.entities.Empresa;

/**
 *
 * @author pc2
 */
public class EmpresaDaoJDBC implements EmpresaDao {

    private final Connection conn;

    public EmpresaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Empresa obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO empresa (E_nome, E_nuit, E_telefone, E_cidade, E_bairro,logotipo) VALUES (?,?,?,?,?,?)");
            pst.setString(1, obj.getE_nome());
            pst.setInt(2, obj.getE_nuit());
            pst.setInt(3, obj.getE_telefone());
            pst.setString(4, obj.getE_cidade());
            pst.setString(5, obj.getE_bairro());
            if (obj.getLogotipo() != null) {
                InputStream photo = new FileInputStream(new File(obj.getLogotipo()));
                pst.setBlob(6, photo);
            } else {
                pst.setString(6, "");
            }
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            } else {
                throw new DbException("erro inesperado! nenhuma linha foi afectada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmpresaDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeStatement(pst);

        }
    }

    @Override
    public void update(Empresa obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE empresa SET E_nome=?,E_nuit=?,E_telefone=?,E_cidade=?,E_bairro=?, logotipo=? WHERE E_id=?");

            pst.setString(1, obj.getE_nome());
            pst.setInt(2, obj.getE_nuit());
            pst.setInt(3, obj.getE_telefone());
            pst.setString(4, obj.getE_cidade());
            pst.setString(5, obj.getE_bairro());
            InputStream photo = new FileInputStream(new File(obj.getLogotipo()));
            pst.setBlob(6, photo);
            pst.setInt(7, obj.getE_id());
            int rowsSffected = pst.executeUpdate();
            if (rowsSffected > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            } else {
                throw new DbException("erro inesperado! nenhuma linha foi afectada");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EmpresaDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DB.closeStatement(pst);

        }

    }
    
    
        @Override
    public void update2(Empresa obj) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE empresa SET E_nome=?,E_nuit=?,E_telefone=?,E_cidade=?,E_bairro=? WHERE E_id=?");

            pst.setString(1, obj.getE_nome());
            pst.setInt(2, obj.getE_nuit());
            pst.setInt(3, obj.getE_telefone());
            pst.setString(4, obj.getE_cidade());
            pst.setString(5, obj.getE_bairro());
            pst.setInt(6, obj.getE_id());
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
    public Empresa findAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM empresa");
            rs = pst.executeQuery();

            if (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setE_id(rs.getInt("E_id"));
                empresa.setE_nome(rs.getString("E_nome"));
                empresa.setE_cidade(rs.getString("E_cidade"));
                empresa.setE_telefone(rs.getInt("E_telefone"));
                empresa.setE_bairro(rs.getString("E_bairro"));
                empresa.setE_nuit(rs.getInt("E_nuit"));
                if (rs.getBytes("logotipo") != null) {
                    byte[] img = rs.getBytes("logotipo");
                    empresa.setElogotipo(img);
                    return empresa;
                }
            }

            }
            catch(SQLException e){
            throw new DbException(e.getMessage());
        }
            finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
            return null;

        }

    }
