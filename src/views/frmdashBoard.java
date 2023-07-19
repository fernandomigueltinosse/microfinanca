/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import modelo.entities.UserSession;

/**
 *
 * @author pc2
 */
public class frmdashBoard extends javax.swing.JFrame {

    

    public frmdashBoard() {
        initComponents();
        this.setExtendedState(6);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        lblUser.setText(UserSession.primeiroNome);
    }
    
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblEmpresa = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblClientes = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblUsers = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblPagamentos = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblEmprestimo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblDocumentos = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        clientes = new javax.swing.JMenuItem();
        usuarios = new javax.swing.JMenuItem();
        Emprestimo = new javax.swing.JMenuItem();
        Documentos = new javax.swing.JMenuItem();
        balanco = new javax.swing.JMenuItem();
        pagamentos = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        RelatorioDeVendas = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        GerarDocumentos = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        sair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DashBoard");

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));
        jPanel2.setForeground(new java.awt.Color(153, 153, 153));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lblEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        lblEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/48x48/icons8-company-48.png"))); // NOI18N
        lblEmpresa.setText("Empresa");
        lblEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEmpresaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        lblClientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/48x48/icons8-users-48.png"))); // NOI18N
        lblClientes.setText("Clientes");
        lblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblClientesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        lblUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/48x48/icons8-male-female-user-group-48.png"))); // NOI18N
        lblUsers.setText("Users");
        lblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsersMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsers)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblPagamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/48x48/icons8-bank-cards-48.png"))); // NOI18N
        lblPagamentos.setText("Pagamentos");
        lblPagamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPagamentosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPagamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPagamentos)
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        lblEmprestimo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmprestimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/48x48/icons8-finance-48.png"))); // NOI18N
        lblEmprestimo.setText("Emprestimos");
        lblEmprestimo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEmprestimoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEmprestimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblEmprestimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-detail-24.png"))); // NOI18N
        jLabel2.setText("Emprestimos nao pagos");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setToolTipText("");

        lblDocumentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDocumentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-report-24.png"))); // NOI18N
        lblDocumentos.setText("Documentos");
        lblDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDocumentosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDocumentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(lblDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel3, jPanel4, jPanel5, jPanel6, jPanel7, jPanel8, jPanel9});

        lblUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-account-skin-type-5-24.png"))); // NOI18N
        lblUser.setText("jLabel2");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logofamba.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUser)
                .addGap(137, 137, 137)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(221, 221, 221))
        );

        jMenu1.setText("Empresa");

        jMenuItem1.setText("Dados da empresa");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("janelas");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        clientes.setText("Clientes");
        clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesActionPerformed(evt);
            }
        });
        jMenu2.add(clientes);

        usuarios.setText("Usuarios");
        usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosActionPerformed(evt);
            }
        });
        jMenu2.add(usuarios);

        Emprestimo.setText("Emprestimos");
        Emprestimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmprestimoActionPerformed(evt);
            }
        });
        jMenu2.add(Emprestimo);

        Documentos.setText("Documentos");
        Documentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentosActionPerformed(evt);
            }
        });
        jMenu2.add(Documentos);

        balanco.setText("Balanço");
        balanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balancoActionPerformed(evt);
            }
        });
        jMenu2.add(balanco);

        pagamentos.setText("Pagamentos");
        pagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagamentosActionPerformed(evt);
            }
        });
        jMenu2.add(pagamentos);

        jMenuItem2.setText("Multas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Relatorios");

        RelatorioDeVendas.setText("Relatorio de vendas");
        RelatorioDeVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RelatorioDeVendasActionPerformed(evt);
            }
        });
        jMenu5.add(RelatorioDeVendas);

        jMenuBar1.add(jMenu5);

        jMenu4.setText("Configurações");

        jMenuItem6.setText("Banco de dados");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Gerar documentos");

        GerarDocumentos.setText("Gerar documentos");
        GerarDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GerarDocumentosActionPerformed(evt);
            }
        });
        jMenu6.add(GerarDocumentos);

        jMenuBar1.add(jMenu6);

        jMenu3.setText("Sair");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        sair.setText("terminar sessão");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        jMenu3.add(sair);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

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

    private void clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesActionPerformed
        
            new frmCliente().setVisible(true);
       
    }//GEN-LAST:event_clientesActionPerformed

    private void usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosActionPerformed
        if (UserSession.funcao.equals("Administrador")) {
            new frmUser().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Voce não tem permissão");
        }
    }//GEN-LAST:event_usuariosActionPerformed

    private void EmprestimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmprestimoActionPerformed
        new frmEmprestimo().setVisible(true);
    }//GEN-LAST:event_EmprestimoActionPerformed

    private void pagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagamentosActionPerformed
        new frmPagamentos().setVisible(true);
    }//GEN-LAST:event_pagamentosActionPerformed

    private void DocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentosActionPerformed
        new frmDocumentos().setVisible(true);
    }//GEN-LAST:event_DocumentosActionPerformed

    private void balancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balancoActionPerformed
        if (UserSession.funcao.equals("Administrador")) {
            new frmBalanco().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Voce não tem permissão");
        }
    }//GEN-LAST:event_balancoActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (UserSession.funcao.equals("Administrador")) {
            new frmConectionConfig().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Voce não tem permissão");
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (UserSession.funcao.equals("Administrador")) {
            new frmEmpresa().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Voce não tem permissão");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        dispose();
        new frmLogin().setVisible(true);
    }//GEN-LAST:event_sairActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
       
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new frmMultas().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void RelatorioDeVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RelatorioDeVendasActionPerformed
        new frmRelatorioVendas().setVisible(true);
    }//GEN-LAST:event_RelatorioDeVendasActionPerformed

    private void GerarDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GerarDocumentosActionPerformed
        new frmRelDocumentos().setVisible(true);
    }//GEN-LAST:event_GerarDocumentosActionPerformed

    private void lblEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmpresaMouseClicked
        if (UserSession.funcao.equals("Administrador")) {
            new frmEmpresa().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Voce não tem permissão");
        }
    }//GEN-LAST:event_lblEmpresaMouseClicked

    private void lblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblClientesMouseClicked
        new frmCliente().setVisible(true);
    }//GEN-LAST:event_lblClientesMouseClicked

    private void lblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsersMouseClicked
          if (UserSession.funcao.equals("Administrador")) {
            new frmUser().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Voce não tem permissão");
        }
    }//GEN-LAST:event_lblUsersMouseClicked

    private void lblPagamentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPagamentosMouseClicked
        new frmPagamentos().setVisible(true);
    }//GEN-LAST:event_lblPagamentosMouseClicked

    private void lblEmprestimoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEmprestimoMouseClicked
       new frmEmprestimo().setVisible(true);
    }//GEN-LAST:event_lblEmprestimoMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        new frmListaEmprestimo().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void lblDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDocumentosMouseClicked
        new frmDocumentos().setVisible(true);
    }//GEN-LAST:event_lblDocumentosMouseClicked

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
            java.util.logging.Logger.getLogger(frmdashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmdashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmdashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmdashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmdashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Documentos;
    private javax.swing.JMenuItem Emprestimo;
    private javax.swing.JMenuItem GerarDocumentos;
    private javax.swing.JMenuItem RelatorioDeVendas;
    private javax.swing.JMenuItem balanco;
    private javax.swing.JMenuItem clientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblDocumentos;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblEmprestimo;
    private javax.swing.JLabel lblPagamentos;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblUsers;
    private javax.swing.JMenuItem pagamentos;
    private javax.swing.JMenuItem sair;
    private javax.swing.JMenuItem usuarios;
    // End of variables declaration//GEN-END:variables
}
