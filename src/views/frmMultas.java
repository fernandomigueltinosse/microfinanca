/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDao;
import modelo.dao.DaoFactory;
import modelo.dao.EmprestimoDao;
import modelo.dao.MultasDao;
import modelo.entities.Cliente;
import modelo.entities.Emprestimo;
import modelo.entities.Multas;

/**
 *
 * @author pc2
 */
public class frmMultas extends javax.swing.JFrame {
 Cliente cli = new Cliente();
    ClienteDao clienteDao = DaoFactory.createClienteDao();
    MultasDao multasDao = DaoFactory.createMultas();

    public frmMultas() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        fillTable();
        findAllMultas();
        ActivateButtons(false, false, false);
    }

       private void ActivateButtons(boolean btnAdicionar, boolean btnPagarmulta, boolean btnApagar) {
        this.btnAdicionar.setEnabled(btnAdicionar);
        this.btnpagar.setEnabled(btnPagarmulta);
        this.btnApagar.setEnabled(btnApagar);
    }
    
   private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setNumRows(0);
        List<Cliente> list = clienteDao.findAllCliente();
         for (Cliente client : list) {
            model.addRow(new Object[]{
                client.getCli_id(),
                client.getCli_nome(),
                client.getCli_endereco(),
                client.getCli_telefone(),
                client.getCli_tipo_documento(),
                client.getCli_numero(),
                client.getCli_data_emissao(),
                client.getCli_data_validade()
            });
        }
    }

  

   private void searchByName() {
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setNumRows(0);
        List<Cliente> list = clienteDao.findByName(txtSearchClientes.getText());
        cli.instatiateModel(list, model);
    }

    private void preencherCampo() {
        int linha = tblClientes.getSelectedRow();
        String id = (tblClientes.getModel().getValueAt(linha, 0).toString());
        txtIdClinte.setText(id);
        
    }

    private void insert() {
        Multas m = new Multas();
        Date data = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        m.setMulta(Double.valueOf(txtMultas.getText()));
        m.setMdata(sd.format(data));
        Cliente cliente = new Cliente();
        cliente.setCli_id(Integer.valueOf(txtIdClinte.getText()));
        m.setCliente(cliente);
        multasDao.insert(m);
    }
    
     private void pagarMulta() {
        Multas m = new Multas();
       
        m.setPagarMulta(Double.valueOf(txtPagarMulta.getText()));
        m.setM_id(Integer.valueOf(txtId.getText()));
        multasDao.pagarMulta(m);
    }

    private void delete() {
        multasDao.delete(Integer.valueOf(txtId.getText()));
    }

    private void filter() {

        List<Multas> list = multasDao.filter(txtSearchMultas.getText());
        DefaultTableModel model = (DefaultTableModel) tblMultas.getModel();
        model.setNumRows(0);
        for(Multas m :list){
            model.addRow(new Object[]{
                m.getM_id(),
                m.getCliente().getCli_nome(),
                m.getMulta(),
                m.getMdata()
            });
        }
    }
    
    private void findAllMultas() {

        List<Multas> list = multasDao.findAllMultas();
        DefaultTableModel model = (DefaultTableModel) tblMultas.getModel();
        model.setNumRows(0);
        for(Multas m :list){
            model.addRow(new Object[]{
                m.getM_id(),
                m.getCliente().getCli_nome(),
                m.getMulta(),
                m.getPagarMulta(),
                m.getMdata()
                
            });
        }
    }
    private void preencherCampoMultas() {
        int linha = tblMultas.getSelectedRow();
        String id = (tblMultas.getModel().getValueAt(linha, 0).toString());
        Multas multas = multasDao.findById(id);
        txtId.setText(String.valueOf(multas.getM_id()));
        txtMultas.setText(String.valueOf(multas.getMulta()));
        txtIdClinte.setText(String.valueOf(multas.getCliente().getCli_id()));
        txtPagarMulta.setText(String.valueOf(multas.getPagarMulta()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearchClientes = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMultas = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIdClinte = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMultas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtSearchMultas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPagarMulta = new javax.swing.JTextField();
        btnpagar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Multas");
        setResizable(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nome", "Endereço", "Telefone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N
        jLabel1.setText("Procurar");

        txtSearchClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchClientesKeyReleased(evt);
            }
        });

        jLabel2.setText("Id");

        jLabel3.setText("Multa");

        jLabel4.setText("Cod. cliente");

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-add-24.png"))); // NOI18N
        btnAdicionar.setText("Cadastrar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-delete-24.png"))); // NOI18N
        btnApagar.setText("Apagar");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        tblMultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cod. multas", "Nome", "Multas", "multas pagas", "Data"
            }
        ));
        tblMultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMultasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMultas);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N
        jLabel5.setText("Procurar");

        txtSearchMultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchMultasKeyReleased(evt);
            }
        });

        jLabel6.setText("Pagar Multa");

        btnpagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-card-payment-24.png"))); // NOI18N
        btnpagar.setText("Pagar multa");
        btnpagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearchClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtSearchMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtIdClinte)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtMultas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(53, 53, 53)))
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtPagarMulta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btnAdicionar)
                        .addGap(63, 63, 63)
                        .addComponent(btnpagar)
                        .addGap(75, 75, 75)
                        .addComponent(btnApagar)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAdicionar, btnApagar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtPagarMulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(txtMultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtIdClinte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSearchMultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionar)
                    .addComponent(btnApagar)
                    .addComponent(btnpagar))
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchClientesKeyReleased
        searchByName();
    }//GEN-LAST:event_txtSearchClientesKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        preencherCampo();
        ActivateButtons(true, false, false);
    }//GEN-LAST:event_tblClientesMouseClicked

    private void tblMultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMultasMouseClicked
        preencherCampoMultas();
        ActivateButtons(false, true, true);
    }//GEN-LAST:event_tblMultasMouseClicked

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        insert();
        findAllMultas();
        ActivateButtons(false, false, false);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        delete();
        findAllMultas();
    }//GEN-LAST:event_btnApagarActionPerformed

    private void txtSearchMultasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchMultasKeyReleased
       filter();
    }//GEN-LAST:event_txtSearchMultasKeyReleased

    private void btnpagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpagarActionPerformed
        pagarMulta(); 
        findAllMultas();
    }//GEN-LAST:event_btnpagarActionPerformed

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
            java.util.logging.Logger.getLogger(frmMultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnpagar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblMultas;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdClinte;
    private javax.swing.JTextField txtMultas;
    private javax.swing.JTextField txtPagarMulta;
    private javax.swing.JTextField txtSearchClientes;
    private javax.swing.JTextField txtSearchMultas;
    // End of variables declaration//GEN-END:variables
}
