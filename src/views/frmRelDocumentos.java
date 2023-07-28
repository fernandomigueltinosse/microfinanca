/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import db.DB;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.DaoFactory;
import modelo.dao.EmprestimoDao;
import modelo.entities.Emprestimo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author pc2
 */
public class frmRelDocumentos extends javax.swing.JFrame {

    String id;
    Connection conn;
    EmprestimoDao creditoDao = DaoFactory.createCreditoDao();

    public frmRelDocumentos() {
        initComponents();
        findAllCredito();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        conn = DB.getConnection();
    }

    private void findAllCredito() {

        List<Emprestimo> list = creditoDao.findAllCredito();
        DefaultTableModel model = (DefaultTableModel) tblCredito.getModel();
        model.setNumRows(0);
        creditoModel(list, model);
    }

    private void creditoModel(List<Emprestimo> List, DefaultTableModel model) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datactual = LocalDate.now();
        for (Emprestimo creditos : List) {
            LocalDate dataAnterior = LocalDate.parse(creditos.getEp_prazo(), formato);
            long diff = ChronoUnit.DAYS.between(datactual, dataAnterior);
            model.addRow(new Object[]{
                creditos.getEp_id(),
                creditos.getCliente().getCli_nome(),
                creditos.getEp_montante(),
                creditos.getEp_juros(),
                creditos.getEp_total(),
                creditos.getEp_prestacoes(),
                creditos.getEp_frequenciaPagamento(),
                creditos.getEp_juros(),
                diff
            });
        }
    }

    private void findByName() {
        List<Emprestimo> List = creditoDao.findByName(txtsearchCredito.getText());
        DefaultTableModel model = (DefaultTableModel) tblCredito.getModel();
        model.setNumRows(0);
        creditoModel(List, model);
    }

    private void prencherCampoId() {
        int linha = tblCredito.getSelectedRow();
        id = tblCredito.getModel().getValueAt(linha, 0).toString();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void imprimirRecibo() {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    InputStream jrxmlStream = DB.class.getClassLoader().getResourceAsStream("relatorios/TermoRecibo.jrxml");
                    JasperDesign path = JRXmlLoader.load(jrxmlStream);
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     private void imprimirDeclaracaoCliente() {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    InputStream jrxmlStream = DB.class.getClassLoader().getResourceAsStream("relatorios/declaracao_cliente.jrxml");
                    JasperDesign path = JRXmlLoader.load(jrxmlStream);
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
      private void imprimirDeclaracaoResponsabilidade() {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    InputStream jrxmlStream = DB.class.getClassLoader().getResourceAsStream("relatorios/declaracao_responsabilidade.jrxml");
                    JasperDesign path = JRXmlLoader.load(jrxmlStream);
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
       private void imprimirDeclaracaoDivida() {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    InputStream jrxmlStream = DB.class.getClassLoader().getResourceAsStream("relatorios/declaracao_divida.jrxml");
                    JasperDesign path = JRXmlLoader.load(jrxmlStream);
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
      private void imprimirDeclaracaoGarantia() {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    InputStream jrxmlStream = DB.class.getClassLoader().getResourceAsStream("relatorios/Declaracao_garantia.jrxml");
                    JasperDesign path = JRXmlLoader.load(jrxmlStream);
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
         private void imprimirDeclaracaoDividaPagamento() {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    InputStream jrxmlStream = DB.class.getClassLoader().getResourceAsStream("relatorios/Declaração_De_Divida_De_Pagamento.jrxml");
                    JasperDesign path = JRXmlLoader.load(jrxmlStream);
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

    }
    //////////////////////////////////////////////////////////////////////////////////////////////
         ///////////////////////////////////////////////////////////////////////////////////////////////
         private void imprimirContrato() {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    InputStream jrxmlStream = DB.class.getClassLoader().getResourceAsStream("relatorios/contrato.jrxml");
                    JasperDesign path = JRXmlLoader.load(jrxmlStream);
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

    }
          ///////////////////////////////////////////////////////////////////////////////////////////////
         private void imprimirConfissaoDivida() {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    InputStream jrxmlStream = DB.class.getClassLoader().getResourceAsStream("relatorios/confissao_divida.jrxml");
                    JasperDesign path = JRXmlLoader.load(jrxmlStream);
                    JasperReport report = JasperCompileManager.compileReport(path);
                    JasperPrint print = JasperFillManager.fillReport(report, parametros, conn);
                    JasperViewer.viewReport(print, false);
                } catch (JRException ex) {
                    Logger.getLogger(frmPagamentos.class.getName()).log(Level.SEVERE, null, ex);
                }
                spash.dispose();
            }
        };
        t.start();

    }     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtsearchCredito = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCredito = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        confissaoDivida = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Imprimir documentos");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N
        jLabel1.setText("Pocurar");

        txtsearchCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchCreditoKeyReleased(evt);
            }
        });

        tblCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Nome", "Montante", "Juros"
            }
        ));
        tblCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCreditoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCredito);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Clique nos botões ao lado para imprimir os recpectivos documentos");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton1.setText("termo de recido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Declaracao de cliente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setText("Declaração de  pagamento de divida  Avalista");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setText("Declaração de responsabilidade");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Declaração de penhora");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton8.setText("Declaração da dívida");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        confissaoDivida.setText("confissão da dívida");
        confissaoDivida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confissaoDividaActionPerformed(evt);
            }
        });

        jButton7.setText("contracto de cliente ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(confissaoDivida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(confissaoDivida)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {confissaoDivida, jButton1, jButton2, jButton3, jButton4, jButton6, jButton7, jButton8});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtsearchCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtsearchCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void txtsearchCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchCreditoKeyReleased
        findByName();
    }//GEN-LAST:event_txtsearchCreditoKeyReleased

    private void tblCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCreditoMouseClicked
        prencherCampoId();
    }//GEN-LAST:event_tblCreditoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(id!=null){
            imprimirRecibo();
        }else{
            JOptionPane.showMessageDialog(null, "selecione um cliente na tabela ao lado");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if(id!=null){
            imprimirDeclaracaoCliente();
        }else{
            JOptionPane.showMessageDialog(null, "selecione um cliente na tabela ao lado");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(id!=null){
            imprimirDeclaracaoResponsabilidade();
        }else{
            JOptionPane.showMessageDialog(null, "selecione um cliente na tabela ao lado");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       if(id!=null){
            imprimirDeclaracaoGarantia();
        }else{
            JOptionPane.showMessageDialog(null, "selecione um cliente na tabela ao lado");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(id!=null){
            imprimirDeclaracaoDivida();
        }else{
            JOptionPane.showMessageDialog(null, "selecione um cliente na tabela ao lado");
        } 
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         if(id!=null){
            imprimirDeclaracaoDividaPagamento();
        }else{
            JOptionPane.showMessageDialog(null, "selecione um cliente na tabela ao lado");
        } 
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(id!=null){
            imprimirContrato();
        }else{
            JOptionPane.showMessageDialog(null, "selecione um cliente na tabela ao lado");
        }     
    }//GEN-LAST:event_jButton7ActionPerformed

    private void confissaoDividaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confissaoDividaActionPerformed
         if(id!=null){
            imprimirConfissaoDivida();
        }else{
            JOptionPane.showMessageDialog(null, "selecione um cliente na tabela ao lado");
        }
        
    }//GEN-LAST:event_confissaoDividaActionPerformed

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
            java.util.logging.Logger.getLogger(frmRelDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRelDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRelDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRelDocumentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRelDocumentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confissaoDivida;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCredito;
    private javax.swing.JTextField txtsearchCredito;
    // End of variables declaration//GEN-END:variables
}
