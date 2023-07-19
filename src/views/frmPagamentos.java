/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.DaoFactory;
import modelo.dao.EmpresaDao;
import modelo.dao.EmprestimoDao;
import modelo.dao.PagamentoDao;
import modelo.entities.Empresa;
import modelo.entities.Emprestimo;
import modelo.entities.Pagamentos;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Tomas
 */
public class frmPagamentos extends javax.swing.JFrame {

    EmprestimoDao emprestimoDao = DaoFactory.createCreditoDao();
    PagamentoDao pagamentoDao = DaoFactory.createPagamento();

    public frmPagamentos() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        findAllCredito();
        findAllPagamentos();
    }

    private void findAllPagamentos() {
        List<Pagamentos> list = pagamentoDao.finfAll();
        DefaultTableModel model = (DefaultTableModel) tblPagamentos.getModel();
        model.setNumRows(0);
        for (Pagamentos pg : list) {
            model.addRow(new Object[]{
                pg.getPg_id(),
                pg.getEmprestimo().getCliente().getCli_nome(),
                pg.getPg_valor_pago(),
                pg.getNumero_prestacao(),
                pg.getData_pagamento(),
                pg.getEmprestimo().getEp_id()

            });
        }

    }

    private void limpar() {
        txtId.setText("");
        txtRefEmprestimo.setText("");
        txtValorApagar.setText("");
    }

    private void findAllCredito() {
        List<Emprestimo> list = emprestimoDao.findAllCredito();
        DefaultTableModel model = (DefaultTableModel) tblCredito.getModel();
        model.setNumRows(0);
        creditoModel(list, model);
    }

    private void insert() {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Date dataNow = new Date();
        Integer count0 = pagamentoDao.count(Integer.valueOf(txtRefEmprestimo.getText()));
        Emprestimo emp = emprestimoDao.findById(Integer.valueOf(txtRefEmprestimo.getText()));
        if (emp.getEp_prestacoes() == count0) {
            JOptionPane.showMessageDialog(null, "Todas as prestações foram liquidadas");
        } else {
            Pagamentos pagamento = new Pagamentos();
            pagamento.setPg_valor_pago(Double.valueOf(txtValorApagar.getText()));
            Emprestimo ep = new Emprestimo();
            ep.setEp_id(Integer.valueOf(txtRefEmprestimo.getText()));
            pagamento.setEmprestimo(ep);
            pagamento.setData_pagamento(sd.format(dataNow));
            pagamentoDao.insert(pagamento);
            Integer count = pagamentoDao.count(Integer.valueOf(txtRefEmprestimo.getText()));
            
            pagamento.setNumero_prestacao(count);
            pagamento.setPg_id(pagamento.getPg_id());
            pagamentoDao.updatePrestcoes(pagamento);
            ep = emprestimoDao.findById(Integer.valueOf(txtRefEmprestimo.getText()));
            if(Objects.equals(ep.getEp_prestacoes(), count)){
                ep.setStatus("Pago");
                ep.setEp_id(Integer.valueOf(txtRefEmprestimo.getText()));
                emprestimoDao.updateStatus(ep);
            }
                    
            LocalDate dataAtual = LocalDate.parse(ep.getEp_prazo(), formato);
            
            LocalDate novaData = dataAtual.plusDays(ep.getEp_frequenciaPagamento());
            
            
            ep.setEp_prazo(formato.format(novaData));
            ep.setEp_id(Integer.valueOf(txtRefEmprestimo.getText()));
            emprestimoDao.updateData(ep);
            imprimirRecibo(pagamento, ep);
        }

    }

    private void imprimirRecibo(Pagamentos pg, Emprestimo ep) {

        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    EmpresaDao empresaDao = DaoFactory.createEmpresa();

                    Empresa empresa = empresaDao.findAll();

                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("nomeEmpresa", empresa.getE_nome());
                    parametros.put("nuit", empresa.getE_nuit());
                    parametros.put("telefone", empresa.getE_telefone());

                    parametros.put("pg_id", pg.getPg_id());
                    parametros.put("pg_valor_pago", pg.getPg_valor_pago());
                    parametros.put("numero_prestacao", pg.getNumero_prestacao());
                    parametros.put("data_pagamento", pg.getData_pagamento());
                    parametros.put("cli_nome", ep.getCliente().getCli_nome());
                    parametros.put("idEmprestimo", ep.getEp_id());
                    JasperDesign path = JRXmlLoader.load("src/relatorios/recibo.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, new JREmptyDataSource());
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

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

    private void findByName() {
        List<Emprestimo> List = emprestimoDao.findByName(txtsearchCredito.getText());
        DefaultTableModel model = (DefaultTableModel) tblCredito.getModel();
        model.setNumRows(0);
        creditoModel(List, model);
    }

    private void preencherCampo() {
        int linha = tblCredito.getSelectedRow();
        txtRefEmprestimo.setText(tblCredito.getModel().getValueAt(linha, 0).toString());
        Double montante = (Double) tblCredito.getModel().getValueAt(linha, 4);
        Integer prestacoes = (Integer) tblCredito.getModel().getValueAt(linha, 5);
        txtValorApagar.setText(String.valueOf(montante / prestacoes));
    }

    private void imprimir() throws JRException {
        EmpresaDao empresaDao = DaoFactory.createEmpresa();

        Empresa empresa = empresaDao.findAll();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nomeEmpresa", empresa.getE_nome());
        parametros.put("nuit", empresa.getE_nuit());
        parametros.put("telefone", empresa.getE_telefone());
        List<Pagamentos> list = pagamentoDao.finfAll();

        JasperDesign path = JRXmlLoader.load("src/relatorios/pagamentos.jrxml");
        JasperReport report = JasperCompileManager.compileReport(path);
        JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(list));
        JasperViewer.viewReport(print, false);
    }

    private void searchById() {
        DefaultTableModel model = (DefaultTableModel) tblPagamentos.getModel();
        model.setNumRows(0);
        if (!jTextField1.getText().isEmpty()) {
            List<Pagamentos> list = pagamentoDao.findByEpId(jTextField1.getText());

            for (Pagamentos pg : list) {
                model.addRow(new Object[]{
                    pg.getPg_id(),
                    pg.getEmprestimo().getCliente().getCli_nome(),
                    pg.getPg_valor_pago(),
                    pg.getNumero_prestacao(),
                    pg.getData_pagamento(),
                    pg.getEmprestimo().getEp_id()

                });
            }
        } else {
            findAllPagamentos();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        txtsearchCredito = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCredito = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagamentos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRefEmprestimo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtValorApagar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        txtsearchCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchCreditoKeyReleased(evt);
            }
        });

        tblCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº. Processo", "Nome", "Valor do emprestimo", "Juros", "Total", "Prestacoes", "Prazo de pagamento"
            }
        ));
        tblCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCreditoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCredito);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsearchCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtsearchCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Lista de Pedido de emprestimo", jPanel5);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N
        jLabel1.setText("Filtrar pelo nº do processo");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        tblPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nome", "Valor pago", "Prestacao paga", "Data", "Nº do emprestimo"
            }
        ));
        jScrollPane1.setViewportView(tblPagamentos);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Lista de pagamentos", jPanel6);

        jLabel3.setText("Id");

        txtId.setEnabled(false);

        jLabel4.setText("Nº processo");

        txtRefEmprestimo.setEditable(false);

        jLabel5.setText("Valor a pagar");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRefEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtValorApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtRefEmprestimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtValorApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-add-24.png"))); // NOI18N
        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-cancel-24.png"))); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jButton2)
                        .addGap(71, 71, 71)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtsearchCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchCreditoKeyReleased
        findByName();
    }//GEN-LAST:event_txtsearchCreditoKeyReleased

    private void tblCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCreditoMouseClicked
        preencherCampo();
    }//GEN-LAST:event_tblCreditoMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        insert();
        findAllCredito();
        findAllPagamentos();
        limpar();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        limpar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        searchById();
    }//GEN-LAST:event_jTextField1KeyReleased

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
            java.util.logging.Logger.getLogger(frmPagamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPagamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPagamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPagamentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPagamentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblCredito;
    private javax.swing.JTable tblPagamentos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtRefEmprestimo;
    private javax.swing.JTextField txtValorApagar;
    private javax.swing.JTextField txtsearchCredito;
    // End of variables declaration//GEN-END:variables
}
