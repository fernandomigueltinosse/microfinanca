/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.DaoFactory;
import modelo.dao.UserDao;
import modelo.entities.User;

/**
 *
 * @author Tomas
 */
public class frmUser extends javax.swing.JFrame {

    UserDao userdao = DaoFactory.createUserDao();

    public frmUser() {
        initComponents();
        fillTable();
        ActivateButtons(false, false, false);
    }

    private void ActivateButtons(boolean btnAdicionar, boolean btnActualizar, boolean btnApagar) {
        this.btnAdicionar.setEnabled(btnAdicionar);
        this.btnActualizar.setEnabled(btnActualizar);
        this.btnApagar.setEnabled(btnApagar);
    }

    private void cleanField() {
        txtEmail.setText("");
        txtFistName.setText("");
        txtId.setText("");
        txtLastname.setText("");
        txtsenha.setText("");
        txtUserName.setText("");
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        model.setNumRows(0);
        List<User> list = userdao.findAllUser();
        User users = new User();
        users.UserModel(list, model);
    }

    private void ClickedTable() {
        int linha = tblUser.getSelectedRow();
        int id = Integer.parseInt(tblUser.getModel().getValueAt(linha, 0).toString());
        User user = userdao.findUserById(id);
        txtId.setText(String.valueOf(user.getUser_id()));
        txtFistName.setText(user.getUser_fistNome());
        txtLastname.setText(user.getUser_lastNome());
        txtEmail.setText(user.getUser_email());
        comoCargo.setSelectedItem(user.getUser_function());
        txtsenha.setText(user.getUser_password());
        txtUserName.setText(user.getUserName());
    }

    private void searchByName() {
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        model.setNumRows(0);
        List<User> list = userdao.searchByName(txtSearch.getText());
        User users = new User();
        users.UserModel(list, model);

    }

    private void insert() {
        User user = instatiateUser();
        if (!txtUserName.getText().isEmpty() && !txtsenha.getText().isEmpty()) {
            userdao.insert(user);
        } else {
            JOptionPane.showMessageDialog(null, "preecnha os campos obrigatorios*");
        }

    }

    private void update() {
        User user = instatiateUser();
        userdao.update(user);

    }

    private void delete() {
        User user = new User();
        user.setUser_id(Integer.valueOf(txtId.getText()));
        userdao.delete(user);
    }

    private User instatiateUser() {
        User user = new User();
        user.setUser_fistNome(txtFistName.getText());
        user.setUser_lastNome(txtLastname.getText());
        user.setUser_email(txtEmail.getText());
        user.setUser_function((String) comoCargo.getSelectedItem());
        user.setUser_password(txtsenha.getText());
        user.setUserName(txtUserName.getText());
        if (!txtId.getText().isEmpty()) {
            user.setUser_id(Integer.valueOf(txtId.getText()));
        }
        return user;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFistName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtLastname = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtsenha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comoCargo = new javax.swing.JComboBox<>();
        btnNovo = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("usuario");
        setResizable(false);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº. Usuario", "Username", "Primeiro nome", "Ultimo nome", "Email", "Função"
            }
        ));
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUser);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtId.setEditable(false);

        jLabel2.setText("Nº. Usuario");

        jLabel3.setText("Primeiro nome");

        jLabel4.setText("Ultimo nome");

        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserNameFocusLost(evt);
            }
        });

        jLabel8.setText("Username*");

        jLabel5.setText("Email");

        jLabel6.setText("Senha*");

        jLabel7.setText("Cargo*");

        comoCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Geral" }));

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-new-copy-24.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-add-24.png"))); // NOI18N
        btnAdicionar.setText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-pencil-24.png"))); // NOI18N
        btnActualizar.setText("Actualzar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-delete-24.png"))); // NOI18N
        btnApagar.setText("Apagar");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(132, 132, 132)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtUserName))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtFistName)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(comoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo)
                .addGap(34, 34, 34)
                .addComponent(btnAdicionar)
                .addGap(50, 50, 50)
                .addComponent(btnActualizar)
                .addGap(52, 52, 52)
                .addComponent(btnApagar)
                .addGap(70, 70, 70))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtFistName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(comoCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnAdicionar)
                    .addComponent(btnActualizar)
                    .addComponent(btnApagar))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
       searchByName();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        ClickedTable();
         ActivateButtons(false, true, true);
    }//GEN-LAST:event_tblUserMouseClicked

    private void txtUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserNameFocusLost

    }//GEN-LAST:event_txtUserNameFocusLost

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        delete();
        cleanField();
        fillTable();
        ActivateButtons(false, false, false);
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        update();
        cleanField();
        fillTable();
        ActivateButtons(false, false, false);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        if (userdao.ifUserExist(txtUserName.getText())) {
            JOptionPane.showMessageDialog(null, "Usuario ja existe");
        } else {
            insert();
            cleanField();
            fillTable();
            ActivateButtons(false, false, false);
        }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        ActivateButtons(true, false, false);
    }//GEN-LAST:event_btnNovoActionPerformed

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
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> comoCargo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFistName;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtsenha;
    // End of variables declaration//GEN-END:variables
}
