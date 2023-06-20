/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entities;

import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc2
 */
public class User {
    private Integer user_id;
    
    private String userName;
    private String user_fistNome;
    private String user_lastNome;
    private String user_function;
    private String user_password;
    private String user_email;

    /**
     * @return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the user_fistNome
     */
    public String getUser_fistNome() {
        return user_fistNome;
    }

    /**
     * @param user_fistNome the user_fistNome to set
     */
    public void setUser_fistNome(String user_fistNome) {
        this.user_fistNome = user_fistNome;
    }

    /**
     * @return the user_lastNome
     */
    public String getUser_lastNome() {
        return user_lastNome;
    }

    /**
     * @param user_lastNome the user_lastNome to set
     */
    public void setUser_lastNome(String user_lastNome) {
        this.user_lastNome = user_lastNome;
    }

    /**
     * @return the user_function
     */
    public String getUser_function() {
        return user_function;
    }

    /**
     * @param user_function the user_function to set
     */
    public void setUser_function(String user_function) {
        this.user_function = user_function;
    }

    /**
     * @return the user_password
     */
    public String getUser_password() {
        return user_password;
    }

    /**
     * @param user_password the user_password to set
     */
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    /**
     * @return the user_email
     */
    public String getUser_email() {
        return user_email;
    }

    /**
     * @param user_email the user_email to set
     */
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

   
       public void UserModel(List<User> list, DefaultTableModel model) {
        for (User users : list) {
            model.addRow(new Object[]{
                users.getUser_id(),
                users.getUserName(),
                users.getUser_fistNome(),
                users.getUser_lastNome(),
                users.getUser_email(),
                users.getUser_function(),
                users.getUser_password()
            });
        }
    }
   
    
 
}
