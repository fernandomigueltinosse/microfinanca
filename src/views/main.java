/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import db.DB;
import javax.swing.JOptionPane;

/**
 *
 * @author pc2
 */
public class main {
    
    public static void main(String args[]) {
         // Verificar a conexão com o banco de dados ao iniciar o formulário de login
        if (DB.getConnection() != null) {
            new frmLogin().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados. Redirecionando para as configurações.");
            frmConectionConfig configuracao = new frmConectionConfig();
            configuracao.setVisible(true);
           
        }
    
    }
    
}
