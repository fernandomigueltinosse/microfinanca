/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import db.DB;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDao;
import modelo.dao.DaoFactory;
import modelo.entities.Cliente;
import modelo.entities.Emprestimo;
import modelo.dao.EmprestimoDao;
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
 * @author Tomas
 */
public class frmEmprestimo extends javax.swing.JFrame {

    ClienteDao clienteDao = DaoFactory.createClienteDao();
    EmprestimoDao creditoDao = DaoFactory.createCreditoDao();
    Emprestimo ep = new Emprestimo();
    Cliente cli = new Cliente();
    Connection conn;
    public frmEmprestimo() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        preencherTabelaClientes();
        findAllCredito();
        conn = DB.getConnection();
    }

    private void preencherTabelaClientes() {
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setNumRows(0);
        List<Cliente> list = clienteDao.findAllCliente();
        ep.instatiateModel(list, model);
    }

    private void insert() {
        
         SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        Emprestimo credito = new Emprestimo();
        credito.setEp_montante(Double.valueOf(txtMontante.getText()));
        credito.setEp_juros(Double.valueOf(txtJuros.getText()));
        credito.setEp_total(Double.valueOf(txtTotal.getText()));
        credito.setEp_prestacoes(Integer.valueOf(txtPrestacoes.getText()));
        
        credito.setStatus("nao pago");
        credito.setEp_prazo(sd.format(dataPrazo.getDate()));
        credito.setEp_data_emprestimo(sd.format(data));
        credito.setEp_frequenciaPagamento(Integer.valueOf(txtFrequenciaPagamento.getText()));
        Cliente cliente = new Cliente();
        cliente.setCli_id(Integer.valueOf(txtIdClinte.getText()));
        credito.setCliente(cliente);
        creditoDao.insert(credito);
        imprimirConfissaoDivida(credito.getEp_id());
    }
    
       private void imprimirConfissaoDivida(int id) {
        final frmAguarde spash = new frmAguarde();
        spash.setVisible(true);
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Map<String, Object> parametros = new HashMap<>();
                    parametros.put("emp_id", id);
                    //JasperDesign path = JRXmlLoader.load("src/relatorios/confissao_divida.jrxml");
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

    private void update() {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        Emprestimo credito = new Emprestimo();
        credito.setEp_montante(Double.valueOf(txtMontante.getText()));
        credito.setEp_juros(Double.valueOf(txtJuros.getText()));
        credito.setEp_total(Double.valueOf(txtTotal.getText()));
        credito.setEp_prestacoes(Integer.valueOf(txtPrestacoes.getText()));
        credito.setEp_id(Integer.valueOf(txtIdCredito.getText()));
        
        credito.setEp_prazo(sd.format(dataPrazo.getDate()));
        credito.setEp_data_emprestimo(sd.format(data));
        credito.setEp_frequenciaPagamento(Integer.valueOf(txtFrequenciaPagamento.getText()));
        Cliente cliente = new Cliente();
        cliente.setCli_id(Integer.valueOf(txtIdClinte.getText()));
        credito.setCliente(cliente);
        creditoDao.update(credito);
    }

    private void delete() {
        Emprestimo credito = instatiateCredito();
        creditoDao.delete(credito);
    }

    private void findCreditoById() {
         int linha = tblCredito.getSelectedRow();
         tblCredito.getModel().getValueAt(linha, 0).toString();
        Emprestimo emp = creditoDao.findById(Integer.valueOf(tblCredito.getModel().getValueAt(linha, 0).toString()));
        txtIdCredito.setText(String.valueOf(emp.getEp_id()));
        txtMontante.setText(String.valueOf(emp.getEp_montante()));
        txtJuros.setText(String.valueOf(emp.getEp_juros()));
        txtTotal.setText(String.valueOf(emp.getEp_total()));
        txtPrestacoes.setText(String.valueOf(emp.getEp_prestacoes()));
        txtFrequenciaPagamento.setText(String.valueOf(emp.getEp_frequenciaPagamento()));
        ((JTextField)dataPrazo.getDateEditor().getUiComponent()).setText(emp.getEp_prazo());
        txtIdClinte.setText(String.valueOf(emp.getCliente().getCli_id()));
    }

    private void findCreditoByName() {
        List<Emprestimo> List = creditoDao.findByName(txtsearchCredito.getText());
        DefaultTableModel model = (DefaultTableModel) tblCredito.getModel();
        model.setNumRows(0);
        creditoModel(List, model);
    }

    private void findAllCredito() {

        List<Emprestimo> list = creditoDao.findAllCredito();
        DefaultTableModel model = (DefaultTableModel) tblCredito.getModel();
        model.setNumRows(0);
        creditoModel(list, model);
    }

    private void searchClienteByName() {
        DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
        model.setNumRows(0);
        List<Cliente> list = clienteDao.findByName(txtSearch.getText());
        cli.instatiateModel(list, model);
    }

    private void preencherClienteCampoId() {
        //DefaultTableModel model =  (DefaultTableModel)tblCredito.getModel();
        int linha = tblClientes.getSelectedRow();
        txtIdClinte.setText(tblClientes.getModel().getValueAt(linha, 0).toString());
    }

    private void cancelar() {
        txtIdClinte.setText("");
        txtIdCredito.setText("");
        txtJuros.setText("");
        txtMontante.setText("");
        txtPrestacoes.setText("");
        txtTotal.setText("");
    }

    private void calcular_total() {
        if (!txtMontante.getText().isEmpty() || txtJuros.getText().isEmpty()) {
            double montante, juros, percentual, total;
            montante = Double.parseDouble(txtMontante.getText());
            juros = Double.parseDouble(txtJuros.getText());
            percentual = (montante * juros) / 100;
            total = percentual + montante;
            txtTotal.setText(String.valueOf(total));
            

        } else {
        }
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
                creditos.getEp_prazo(),
                diff
            });
        }
    }

    private Emprestimo instatiateCredito() {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        Emprestimo credito = new Emprestimo();
        credito.setEp_montante(Double.valueOf(txtMontante.getText()));
        credito.setEp_juros(Double.valueOf(txtJuros.getText()));
        credito.setEp_total(Double.valueOf(txtTotal.getText()));
        credito.setEp_prestacoes(Integer.valueOf(txtPrestacoes.getText()));
        credito.setEp_id(Integer.valueOf(txtIdCredito.getText()));
        credito.setStatus("nao pago");
        credito.setEp_prazo(sd.format(dataPrazo.getDate()));
        credito.setEp_data_emprestimo(sd.format(data));
        credito.setEp_frequenciaPagamento(Integer.valueOf(txtFrequenciaPagamento.getText()));
        Cliente cliente = new Cliente();
        cliente.setCli_id(Integer.valueOf(txtIdClinte.getText()));
        credito.setCliente(cliente);
        return credito;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCredito = new javax.swing.JTable();
        txtsearchCredito = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIdClinte = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtIdCredito = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMontante = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtJuros = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dataPrazo = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtPrestacoes = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFrequenciaPagamento = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nº. Cliente", "Nome", "Endereço", "Telefone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N
        jLabel1.setText("Pesquisar pelo nome");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1064, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("Lista de clientes", jPanel5);

        tblCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº. Processo", "Nome", "Montante aprovado", "Juros", "Total", "Prestações", "Frequencia de pagamento", "Prazo de pagamento"
            }
        ));
        tblCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCreditoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCredito);

        txtsearchCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtsearchCreditoKeyReleased(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N
        jLabel11.setText("Pesquisar pelo nome / nº do processo ");
        jLabel11.setToolTipText("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtsearchCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(610, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1087, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtsearchCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lista de pedido de emprestimo", jPanel6);

        jLabel2.setText("Nº. Cliente");

        txtIdClinte.setEnabled(false);

        jLabel3.setText("Nº. Processo");

        txtIdCredito.setEnabled(false);

        jLabel4.setText("Montante ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdClinte)
                    .addComponent(txtIdCredito)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 173, Short.MAX_VALUE))
                    .addComponent(txtMontante))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdClinte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Juros");

        txtJuros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJurosKeyReleased(evt);
            }
        });

        jLabel6.setText("Total");

        jLabel8.setText("Prazo de pagamento");

        dataPrazo.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtJuros, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(dataPrazo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtJuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("Prestações");

        jLabel10.setText("Frequencia de Pagamento(Nº de dias)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrestacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtFrequenciaPagamento))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrestacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFrequenciaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-new-copy-24.png"))); // NOI18N
        jButton1.setText("Novo");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-add-24.png"))); // NOI18N
        jButton2.setText("Adicionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-pencil-24.png"))); // NOI18N
        jButton4.setText("Actualizar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-cancel-24.png"))); // NOI18N
        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        cancelar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        insert();
        findAllCredito();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        preencherClienteCampoId();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchClienteByName();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        update();
        findAllCredito();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCreditoMouseClicked
        findCreditoById();
    }//GEN-LAST:event_tblCreditoMouseClicked

    private void txtsearchCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchCreditoKeyReleased
        findCreditoByName();
    }//GEN-LAST:event_txtsearchCreditoKeyReleased

    private void txtJurosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJurosKeyReleased
        if(!txtJuros.getText().isEmpty()){
        calcular_total();
        }else{
            txtTotal.setText("");
        }
    }//GEN-LAST:event_txtJurosKeyReleased

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
            java.util.logging.Logger.getLogger(frmEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEmprestimo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEmprestimo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dataPrazo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblCredito;
    private javax.swing.JTextField txtFrequenciaPagamento;
    private javax.swing.JTextField txtIdClinte;
    private javax.swing.JTextField txtIdCredito;
    private javax.swing.JTextField txtJuros;
    private javax.swing.JTextField txtMontante;
    private javax.swing.JTextField txtPrestacoes;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtsearchCredito;
    // End of variables declaration//GEN-END:variables
}
