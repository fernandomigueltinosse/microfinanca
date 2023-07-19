/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import modelo.dao.DaoFactory;
import modelo.dao.EmpresaDao;
import modelo.entities.Cliente;
import modelo.entities.Empresa;

/**
 *
 * @author pc2
 */
public class frmEmpresa extends javax.swing.JFrame {

    EmpresaDao empresaDao = DaoFactory.createEmpresa();
    Empresa empresa = new Empresa();
    String Imagepath2 = "";

    public frmEmpresa() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        findall();
    }

    private void insert() {

        if (empresaDao.findAll() == null) {
            if (Imagepath2.trim().length() != 0) {
                empresa.setLogotipo(Imagepath2);
            } else {
                empresa.setLogotipo(null);
            }
            empresa.setE_nome(txtEnome.getText());
            empresa.setE_nuit(Integer.parseInt(txtEnuit.getText()));
            empresa.setE_telefone(Integer.parseInt(txtEtelefone.getText()));
            empresa.setE_bairro(txtEbairo.getText());
            empresa.setE_cidade(txtEcidade.getText());
            empresaDao.insert(empresa);
        } else {
            empresa = empresaDao.findAll();
            empresa.getE_id();
            empresa.setE_nome(txtEnome.getText());
            empresa.setE_nuit(Integer.parseInt(txtEnuit.getText()));
            empresa.setE_telefone(Integer.parseInt(txtEtelefone.getText()));
            empresa.setE_bairro(txtEbairo.getText());
            empresa.setE_cidade(txtEcidade.getText());
            if (Imagepath2.trim().length() != 0) {
                empresa.setLogotipo(Imagepath2);
                empresaDao.update(empresa);
            } else {
                empresaDao.update2(empresa);
            }

        }

    }

    private void findall() {
        if (empresaDao.findAll() != null) {
            empresa = empresaDao.findAll();
            txtEnome.setText(empresa.getE_nome());
            txtEbairo.setText(empresa.getE_bairro());
            txtEcidade.setText(empresa.getE_cidade());
            txtEnuit.setText(String.valueOf(empresa.getE_nuit()));
            txtEtelefone.setText(String.valueOf(empresa.getE_telefone()));
              if (empresa.getElogotipo() != null) {
            ImageIcon image = new ImageIcon(empresa.getElogotipo());
            Image im = image.getImage();
            Image myimg = im.getScaledInstance(lblLogotipo.getWidth(), lblLogotipo.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon newImage = new ImageIcon(myimg);
            lblLogotipo.setIcon(newImage);
        } else {
            lblLogotipo.setIcon(null);
        }
        }

    }

    private void adicionarImagem() throws IOException {
        Cliente client = new Cliente();
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String Imagepath = f.getAbsolutePath();
        ///this code resize de image 
        BufferedImage bi = ImageIO.read(new File(Imagepath));
        Image img = bi.getScaledInstance(152, 130, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        client.setFoto(Imagepath);
        Imagepath2 = Imagepath;
        lblLogotipo.setIcon(icon);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEnome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEcidade = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEbairo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEnuit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEtelefone = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblLogotipo = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dados da empresa");
        setResizable(false);

        jLabel1.setText("Nome da empresa");

        jLabel2.setText("Cidade");

        jLabel3.setText("Bairro");

        jLabel4.setText("NUIT");

        jLabel5.setText("Telefone");

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(14, 137, 203));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dados da Empresa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        lblLogotipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-add-24.png"))); // NOI18N
        jButton2.setText("Logotipo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEcidade, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEnuit, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEbairo)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtEtelefone)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(13, 13, 13))
                                        .addComponent(txtEnome, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblLogotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButton2)))
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEtelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEbairo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEcidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLogotipo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insert();
        findall();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            adicionarImagem();
        } catch (IOException ex) {
            Logger.getLogger(frmEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEmpresa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEmpresa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblLogotipo;
    private javax.swing.JTextField txtEbairo;
    private javax.swing.JTextField txtEcidade;
    private javax.swing.JTextField txtEnome;
    private javax.swing.JTextField txtEnuit;
    private javax.swing.JTextField txtEtelefone;
    // End of variables declaration//GEN-END:variables
}
