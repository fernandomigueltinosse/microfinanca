/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import db.DB;
import modelo.dao.impl.BalancoDaoJDBC;
import modelo.dao.impl.ClienteDaoJDBC;
import modelo.dao.impl.DocumentosDaoJDBC;
import modelo.dao.impl.EmprestimoDaoJDBC;
import modelo.dao.impl.EmpresaDaoJDBC;

import modelo.dao.impl.UserDaoJDBC;
import modelo.dao.impl.PagamentoDaoJDBC;





public class DaoFactory {
 
    
    public static UserDao createUserDao(){
        return new UserDaoJDBC(DB.getConnection());
    }
    
    public static ClienteDao createClienteDao(){
        return new ClienteDaoJDBC(DB.getConnection());
    }
    
    
     public static EmprestimoDao createCreditoDao(){
        return new EmprestimoDaoJDBC(DB.getConnection());
    }
    
 
    public static EmpresaDao createEmpresa(){
        return new EmpresaDaoJDBC(DB.getConnection());
    } 
    
    public static PagamentoDao createPagamento(){
        return new PagamentoDaoJDBC(DB.getConnection());
    }
    
    public static balancoDao createBalanco(){
        return new BalancoDaoJDBC(DB.getConnection());
    }
    
     public static DocumentosDao createDocumentos(){
        return new DocumentosDaoJDBC(DB.getConnection());
    }
}
