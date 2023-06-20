/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao.impl;

import db.DB;
import db.DbException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.entities.Documentos;
import modelo.dao.DocumentosDao;
import modelo.entities.Cliente;

/**
 *
 * @author pc2
 */
public class DocumentosDaoJDBC implements DocumentosDao {

    private final Connection conn;

    public DocumentosDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Documentos doc) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("INSERT INTO documentos (titulo,idEmprestimo,path) VALUES (?,?,?)");
            pst.setString(1, doc.getTitulo());
            pst.setInt(2, doc.getIdEmprestimo());
            pst.setBytes(3, doc.getPath());

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
    public void update(Documentos doc) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE documentos SET titulo=?,path=?,idEmprestimo=? WHERE idDocumentos=?");
            pst.setString(1, doc.getTitulo());
            pst.setBytes(2, doc.getPath());
            pst.setInt(3, doc.getIdEmprestimo());
            pst.setInt(4, doc.getIdDocumentos());

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
    public void update1(Documentos doc) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("UPDATE documentos SET titulo=?,idEmprestimo=? WHERE idDocumentos=?");
            pst.setString(1, doc.getTitulo());
            pst.setInt(2, doc.getIdEmprestimo());
            pst.setInt(3, doc.getIdDocumentos());

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
    public void delete(Integer id) {
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("DELETE FROM documentos  WHERE idDocumentos=?");
            pst.setInt(1, id);
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
    public ArrayList<Documentos> findAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM documentos");
            rs = pst.executeQuery();
            ArrayList<Documentos> list = new ArrayList<>();
            while (rs.next()) {
                Documentos doc = new Documentos();
                doc.setIdDocumentos(rs.getInt("idDocumentos"));
                doc.setTitulo(rs.getString("titulo"));
                doc.setPath(rs.getBytes("path"));
                doc.setIdEmprestimo(rs.getInt("idEmprestimo"));
                list.add(doc);
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
    public ArrayList<Documentos> filtrar(String id) {
       PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement("SELECT * FROM documentos where idEmprestimo like ?");
            pst.setString(1, id + "%");
            rs = pst.executeQuery();
            ArrayList<Documentos> list = new ArrayList<>();
            while (rs.next()) {
                Documentos doc = new Documentos();
                doc.setIdDocumentos(rs.getInt("idDocumentos"));
                doc.setTitulo(rs.getString("titulo"));
                doc.setPath(rs.getBytes("path"));
                doc.setIdEmprestimo(rs.getInt("idEmprestimo"));
                list.add(doc);
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
    public void findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        byte[] b = null;
        try {
            pst = conn.prepareStatement("SELECT path FROM documentos where idDocumentos=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                b = rs.getBytes(1);
            }
            try ( InputStream bos = new ByteArrayInputStream(b)) {
                int tamanoInput = bos.available();
                byte[] datosPDF = new byte[tamanoInput];
                bos.read(datosPDF, 0, tamanoInput);

                OutputStream out = new FileOutputStream("new.pdf");
                out.write(datosPDF);

                //abrir archivo
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(DocumentosDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Documentos findById2(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement("SELECT *FROM documentos where idDocumentos=?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                Documentos doc = new Documentos();
                doc.setIdDocumentos(rs.getInt("idDocumentos"));
                doc.setTitulo(rs.getString("titulo"));
                doc.setIdEmprestimo(rs.getInt("idEmprestimo"));
                return doc;
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
