/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.dao.DaoFactory;
import modelo.dao.DocumentosDao;
import modelo.dao.EmprestimoDao;
import modelo.entities.Documentos;
import modelo.entities.Emprestimo;
import util.tableRenderer;

/**
 *
 * @author pc2
 */
public class frmDocumentos extends javax.swing.JFrame {

    EmprestimoDao creditoDao = DaoFactory.createCreditoDao();
    DocumentosDao docDao = DaoFactory.createDocumentos();
    String path="";
    int idDocumentos = -1;

    public frmDocumentos() {
        initComponents();
        findAllCredito();
        findAllDoc(tblDocumentos);
    }

    private void findAllCredito() {

        List<Emprestimo> list = creditoDao.findAllCredito();
        DefaultTableModel model = (DefaultTableModel) tblCredito.getModel();
        model.setNumRows(0);
        creditoModel(list, model);
    }

    private void creditoModel(List<Emprestimo> List, DefaultTableModel model) {
        for (Emprestimo creditos : List) {
            model.addRow(new Object[]{
                 creditos.getEp_id(),
                creditos.getCliente().getCli_nome(),
                creditos.getEp_montante(),
                creditos.getEp_juros(),
                creditos.getEp_total(),
                creditos.getEp_prestacoes(),
                creditos.getEp_prazo(),
                creditos.getEp_data_emprestimo(),});
        }
    }

    private void getIdemprestimo() {
        int linha = tblCredito.getSelectedRow();
        String id = (tblCredito.getModel().getValueAt(linha, 0).toString());
        txtIdEmprestimo.setText(id);
    }

    private void findByName() {
        List<Emprestimo> List = creditoDao.findByName(txtSearch.getText());
        DefaultTableModel model = (DefaultTableModel) tblCredito.getModel();
        model.setNumRows(0);
        creditoModel(List, model);
    }

    private void insert() {
        Documentos doc = new Documentos();
        File ruta = new File(path);
        doc.setIdEmprestimo(Integer.valueOf(txtIdEmprestimo.getText()));
        doc.setTitulo(txtTitulo.getText());
        doc.setIdEmprestimo(Integer.valueOf(txtIdEmprestimo.getText()));
        try {
            byte[] pdf = new byte[(int) ruta.length()];
            InputStream input = new FileInputStream(ruta);
            input.read(pdf);
            doc.setPath(pdf);
        } catch (IOException ex) {
            doc.setPath(null);
        }
        docDao.insert(doc);
    }

    private void update() {

        try {
            Documentos doc = new Documentos();
            if (path.trim().length() != 0) {

                File ruta = new File(path);
                doc.setIdEmprestimo(Integer.valueOf(txtIdEmprestimo.getText()));
                doc.setTitulo(txtTitulo.getText());
                doc.setIdDocumentos(Integer.valueOf(txtId.getText()));
                byte[] pdf = new byte[(int) ruta.length()];
                InputStream input = new FileInputStream(ruta);
                input.read(pdf);
                doc.setPath(pdf);
                docDao.update(doc);
            } else {
                doc.setIdEmprestimo(Integer.valueOf(txtIdEmprestimo.getText()));
                doc.setTitulo(txtTitulo.getText());
                doc.setIdDocumentos(Integer.valueOf(txtId.getText()));
                docDao.update1(doc);
            }

        } catch (IOException ex) {

        }
    }
    
    private void delete(){
        docDao.delete(Integer.valueOf(txtId.getText()));
    }

    private void selecionarDocs() {
        Documentos doc = new Documentos();
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter fi = new FileNameExtensionFilter("pdf", "pdf");
        file.setFileFilter(fi);
        int se = file.showOpenDialog(this);
        if (se == 0) {
            this.btnUpload.setText("" + file.getSelectedFile().getName());
            path = file.getSelectedFile().getAbsolutePath();

        } else {
        }
    }

    public Image get_Image(String ruta) {
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(ruta));
            Image mainIcon = imageIcon.getImage();
            return mainIcon;
        } catch (Exception e) {
        }
        return null;
    }

    private void findAllDoc(JTable tabla) {
        ImageIcon icono = null;
        tabla.setDefaultRenderer(Object.class, new tableRenderer());
        DefaultTableModel model = (DefaultTableModel) tblDocumentos.getModel();
        tabla.setRowHeight(32);
        model.setNumRows(0);
        if (get_Image("/icons/img/pdf.png") != null) {
            icono = new ImageIcon(get_Image("/icons/img/pdf.png"));
        }
        Documentos doc;
        ArrayList<Documentos> list = docDao.findAll();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
               Object fila[] = new Object[4];
                doc = list.get(i);
                fila[0] = doc.getIdDocumentos();
                fila[1] = doc.getTitulo();
                fila[2] = doc.getIdEmprestimo();
                if (doc.getPath() != null) {
                    fila[3] = new JButton(icono);

                } else {
                    fila[3] = new JButton("Vacio");
                }
                model.addRow(fila);
            }

        }
    }

    private void preecherCampos() {
        int linha = tblDocumentos.getSelectedRow();
        int id = Integer.parseInt(tblDocumentos.getModel().getValueAt(linha, 0).toString());
        Documentos doc = docDao.findById2(id);
        txtId.setText(String.valueOf(doc.getIdDocumentos()));
        txtTitulo.setText(doc.getTitulo());
        txtIdEmprestimo.setText(String.valueOf(doc.getIdEmprestimo()));
    }

    private void tblDocumentosMouse(MouseEvent evt) {

        int column = tblDocumentos.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblDocumentos.getRowHeight();

        if (row < tblDocumentos.getRowCount() && row >= 0 && column < tblDocumentos.getColumnCount() && column >= 0) {
            idDocumentos = (int) tblDocumentos.getValueAt(row, 0);
            Object value = tblDocumentos.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getText().equals("Vazio")) {
                    JOptionPane.showMessageDialog(null, "Nenhum arquivo encontrado");
                } else {

                    docDao.findById(idDocumentos);
                    try {
                        Desktop.getDesktop().open(new File("new.pdf"));
                    } catch (Exception ex) {}
                }

            } else {
                preecherCampos();
            }
        }
    }
    
    private void filterByIdEmprestimo(JTable tabla){
         ImageIcon icono = null;
        tabla.setDefaultRenderer(Object.class, new tableRenderer());
        DefaultTableModel model = (DefaultTableModel) tblDocumentos.getModel();
        tabla.setRowHeight(32);
        model.setNumRows(0);
        if (get_Image("/icons/img/pdf.png") != null) {
            icono = new ImageIcon(get_Image("/icons/img/pdf.png"));
        }
        Documentos doc;
        ArrayList<Documentos> list = docDao.filtrar(txtSearchDoc.getText());
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[4];
                doc = list.get(i);
                fila[0] = doc.getIdDocumentos();
                fila[1] = doc.getTitulo();
                fila[2] = doc.getIdEmprestimo();
                if (doc.getPath() != null) {
                    fila[3] = new JButton(icono);

                } else {
                    fila[3] = new JButton("Vacio");
                }
                model.addRow(fila);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCredito = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDocumentos = new javax.swing.JTable();
        txtSearchDoc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtIdEmprestimo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Documentos");

        tblCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº. Processo", "Nome", "Montante aprovado", "Juros", "Total", "Prestações"
            }
        ));
        tblCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCreditoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCredito);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N
        jLabel6.setText("Filtrar");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Lista de pedido  de emprestimo", jPanel1);

        tblDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nº. Documento", "Titulo", "Nº. Processo", "Arquivo"
            }
        ));
        tblDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDocumentos);
        tblDocumentos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblDocumentos.getColumnModel().getColumnCount() > 0) {
            tblDocumentos.getColumnModel().getColumn(3).setMinWidth(200);
            tblDocumentos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblDocumentos.getColumnModel().getColumn(3).setMaxWidth(200);
        }

        txtSearchDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchDocKeyReleased(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N
        jLabel7.setText("Filtrar id do emprestimo");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Lista de documnetos", jPanel5);

        jLabel1.setText("Id");

        jLabel2.setText("Titulo do documento");

        jLabel3.setText("Nº. Processo");

        jLabel4.setText("Carregar documento");

        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-delete-24.png"))); // NOI18N
        btnApagar.setText("Apagar");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-pencil-24.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-add-24.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(btnSalvar)
                        .addGap(37, 37, 37)
                        .addComponent(btnActualizar)
                        .addGap(28, 28, 28)
                        .addComponent(btnApagar))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtIdEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnUpload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnActualizar)
                    .addComponent(btnApagar))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCreditoMouseClicked
        getIdemprestimo();
    }//GEN-LAST:event_tblCreditoMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        findByName();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        insert();
        findAllDoc(tblDocumentos);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        selecionarDocs();
    }//GEN-LAST:event_btnUploadActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        update();
        findAllDoc(tblDocumentos);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        delete();
        findAllDoc(tblDocumentos);
    }//GEN-LAST:event_btnApagarActionPerformed

    private void tblDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDocumentosMouseClicked

    private void txtSearchDocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchDocKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchDocKeyReleased

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
            java.util.logging.Logger.getLogger(frmDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDocumentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnUpload;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tblCredito;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdEmprestimo;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearchDoc;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
