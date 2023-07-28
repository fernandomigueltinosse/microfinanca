/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.dao.ClienteDao;
import modelo.dao.DaoFactory;
import modelo.entities.Cliente;

/**
 *
 * @author Tomas
 */
public class frmCliente extends javax.swing.JFrame {

    ClienteDao clienteDao = DaoFactory.createClienteDao();
    Cliente cli = new Cliente();
    String Imagepath2 = "";

    public frmCliente() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        fillTable();
        ActivateButtons(false, false, false);
    }

    private void ActivateButtons(boolean btnAdicionar, boolean btnActualizar, boolean btnApagar) {
        this.btnAdicionar.setEnabled(btnAdicionar);
        this.btnActualizar.setEnabled(btnActualizar);
        this.btnApagar.setEnabled(btnApagar);
    }

    private void cleanField() {
        txtEndereco.setText("");
        txtId.setText("");
        txtId.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        txtNumero.setText("");
        DataEmissaoConjugue.setDate(null);
        DataValidadeConjugue.setDate(null);
        lblPhoto.setIcon(null);
        txtQuarteirao.setText("");
        txtCasa.setText("");
        txtCliDataEmissao.setDate(null);
        txtCliDataValidade.setDate(null);
        txtArquivoIdentificacao.setText("");
        txtDataNascimento.setDate(null);
        txtEstadoCivil.setText("");
        TxtOcupacao.setText("");
        txtNomeConjugue.setText("");
        OcupacaoConjugue.setText("");
        txtLocalNascimento.setText("");
        txtConNumeroDoc.setText("");
        txtConArquivoIdentificacao.setText("");
    }
    
    private boolean validarCampos(){
        if(txtNome.getText().isEmpty()
                || txtEndereco.getText().isEmpty()
                || txtQuarteirao.getText().isEmpty()
                || txtCasa.getText().isEmpty()
                || txtTelefone.getText().isEmpty()
                || txtNumero.getText().isEmpty()
                || txtCliDataEmissao.getDate()==null
                || txtCliDataValidade.getDate()==null
                || txtArquivoIdentificacao.getText().isEmpty()
                || txtDataNascimento.getDate()==null
                || txtLocalNascimento.getText().isEmpty()
                || txtEstadoCivil.getText().isEmpty()
                || TxtOcupacao.getText().isEmpty()){
        return true;
        }
        return false;
    
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblClients.getModel();
        model.setNumRows(0);
        List<Cliente> list = clienteDao.findAllCliente();
        cli.instatiateModel(list, model);
    }

    private void searchByName() {
        DefaultTableModel model = (DefaultTableModel) tblClients.getModel();
        model.setNumRows(0);
        List<Cliente> list = clienteDao.findByName(txtSearch.getText());
        cli.instatiateModel(list, model);
    }

    private void ClickedTable() {
        int linha = tblClients.getSelectedRow();
        int id = Integer.parseInt(tblClients.getModel().getValueAt(linha, 0).toString());
        Cliente client = clienteDao.findById(id);
        txtId.setText(String.valueOf(client.getCli_id()));
        txtNome.setText(client.getCli_nome());
        txtEndereco.setText(client.getCli_endereco());
        txtTelefone.setText(String.valueOf(client.getCli_telefone()));
        ((JTextField) DataEmissaoConjugue.getDateEditor().getUiComponent()).setText(client.getCon_data_de_emissao());
        ((JTextField) DataValidadeConjugue.getDateEditor().getUiComponent()).setText(client.getCon_data_de_validade());
        txtNumero.setText(client.getCli_numero());
        comoDocumento.setSelectedItem(client.getCli_tipo_documento());
        txtQuarteirao.setText(client.getCli_quarteirao());
        txtCasa.setText(client.getCli_casa_numero());
        txtConArquivoIdentificacao.setText(client.getCom_arquivo_identificacao());
        txtConNumeroDoc.setText(client.getCon_numero());
        ((JTextField) txtCliDataEmissao.getDateEditor().getUiComponent()).setText(client.getCli_data_emissao());
        ((JTextField) txtCliDataValidade.getDateEditor().getUiComponent()).setText(client.getCli_data_validade());
        txtArquivoIdentificacao.setText(client.getCli_arquivo_identificacao());

        ((JTextField) txtDataNascimento.getDateEditor().getUiComponent()).setText(client.getCli_data_de_nascimento());
        txtEstadoCivil.setText(client.getCli_estado_civil());
        TxtOcupacao.setText(client.getCli_ocupacao());
        txtNomeConjugue.setText(client.getNome_conjugue());
        OcupacaoConjugue.setText(client.getCon_Ocupacao());
        comboTipoDocumento.setSelectedItem(client.getCon_tipo_documento());
        txtLocalNascimento.setText(client.getCli_local_nascimento());
        if (client.getCli_foto() != null) {
            ImageIcon image = new ImageIcon(client.getCli_foto());
            Image im = image.getImage();
            Image myimg = im.getScaledInstance(lblPhoto.getWidth(), lblPhoto.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon newImage = new ImageIcon(myimg);
            lblPhoto.setIcon(newImage);
        } else {
            lblPhoto.setIcon(null);
        }

    }

    private void insert() {
        Cliente client = instatiateClient();
        if (Imagepath2.trim().length() != 0) {
            client.setFoto(Imagepath2);
        } else {
            client.setFoto(null);
        }

        clienteDao.insert(client);
    }

    private void update() {
        Cliente client = instatiateClient();
        if (Imagepath2.trim().length() != 0) {
            client.setFoto(Imagepath2);
            clienteDao.update(client);
        } else {
            clienteDao.update2(client);
        }

    }

    private void delete() {

        int confirmar = JOptionPane.showConfirmDialog(null, "tem certeza que deseja "
                + "apagar?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirmar == JOptionPane.YES_OPTION) {
            Cliente client = new Cliente();
            client.setCli_id(Integer.valueOf(txtId.getText()));
            clienteDao.delete(client);

        }
    }

    private Cliente instatiateClient() {
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNow = new Date();
        Cliente client = new Cliente();
        if (!txtId.getText().isEmpty()) {
            client.setCli_id(Integer.valueOf(txtId.getText()));
        }
        client.setCli_nome(txtNome.getText());
        client.setCli_endereco(txtEndereco.getText());
        client.setCli_tipo_documento(comoDocumento.getSelectedItem().toString());
        client.setCli_telefone(Integer.valueOf(txtTelefone.getText()));
        client.setCli_numero((txtNumero.getText()));
        client.setCli_data_emissao(sd.format(txtCliDataEmissao.getDate()));
        client.setCli_data_validade(sd.format(txtCliDataValidade.getDate()));
        client.setCli_data_registro(sd.format(dataNow));
        client.setCli_estado_civil(txtEstadoCivil.getText());
        client.setCli_arquivo_identificacao(txtArquivoIdentificacao.getText());
        client.setCli_quarteirao(txtQuarteirao.getText());
        client.setCli_casa_numero(txtCasa.getText());
        client.setCli_data_de_nascimento(sd.format(txtDataNascimento.getDate()));
        client.setCli_ocupacao(TxtOcupacao.getText());
        client.setCli_local_nascimento(txtLocalNascimento.getText());
        client.setNome_conjugue(txtNomeConjugue.getText());
        client.setCon_tipo_documento(comboTipoDocumento.getSelectedItem().toString());
        if(DataEmissaoConjugue.getDate()==null){
            client.setCon_data_de_validade(null);
        }else{
            client.setCon_data_de_emissao(sd.format(DataEmissaoConjugue.getDate()));
        }
        if(DataValidadeConjugue.getDate()==null){
            client.setCon_data_de_validade(null);
        }else{
            client.setCon_data_de_validade(sd.format(DataValidadeConjugue.getDate()));
        }
        client.setCon_Ocupacao(OcupacaoConjugue.getText());
        client.setCom_arquivo_identificacao(txtConArquivoIdentificacao.getText());
        client.setCon_numero(txtConNumeroDoc.getText());
        return client;
    }

    private void adicionarImagem() throws IOException {
        Cliente client = new Cliente();
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        String Imagepath = f.getAbsolutePath();
        ///this code resize de image 
        BufferedImage bi = ImageIO.read(new File(Imagepath));
        Image img = bi.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        client.setFoto(Imagepath);
        Imagepath2 = Imagepath;
        lblPhoto.setIcon(icon);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblPhoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCasa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JTextField();
        comoDocumento = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtQuarteirao = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtCliDataEmissao = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        txtCliDataValidade = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        txtArquivoIdentificacao = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDataNascimento = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        txtLocalNascimento = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtEstadoCivil = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        TxtOcupacao = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtNomeConjugue = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        comboTipoDocumento = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        txtConNumeroDoc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtConArquivoIdentificacao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        DataEmissaoConjugue = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        DataValidadeConjugue = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        OcupacaoConjugue = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClients = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");
        setExtendedState(6);

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do cliente"));

        lblPhoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Bairro *");

        jLabel3.setText("Nome *");

        txtId.setEnabled(false);
        txtId.setMinimumSize(new java.awt.Dimension(100, 22));
        txtId.setPreferredSize(new java.awt.Dimension(100, 22));

        jLabel5.setText("Telefone*");

        txtCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCasaActionPerformed(evt);
            }
        });

        jLabel10.setText("Quarteirão *");

        jLabel6.setText("Tipo de documento");

        comoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BI", "Passaporte", "Carta de condução", "Outros" }));

        jLabel11.setText("Casa*");

        jLabel7.setText("Numero  do documento*");

        jLabel2.setText("Nº.  Cliente");

        txtQuarteirao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuarteiraoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(71, 71, 71))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(txtEndereco)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(txtQuarteirao, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCasa, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(txtTelefone)
                            .addComponent(comoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumero)
                            .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuarteirao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addGap(4, 4, 4)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addGap(4, 4, 4)
                .addComponent(comoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel7)
                .addGap(4, 4, 4)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {comoDocumento, txtCasa, txtEndereco, txtId, txtNome, txtNumero, txtQuarteirao, txtTelefone});

        jLabel19.setText("Data de emissao*");

        txtCliDataEmissao.setDateFormatString("dd/MM/yyyy");
        txtCliDataEmissao.setMinimumSize(new java.awt.Dimension(100, 22));

        jLabel20.setText("Data de validade*");

        txtCliDataValidade.setDateFormatString("dd/MM/yyyy");

        jLabel15.setText("Arquivo de identificação*");

        jLabel17.setText("Data de nascimento*");

        txtDataNascimento.setDateFormatString("dd/MM/yyyy");

        jLabel21.setText("Local de nascimento");

        jLabel16.setText("Estado Civil*");

        jLabel18.setText("Ocupação*");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtArquivoIdentificacao)
                            .addComponent(txtDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtLocalNascimento)
                            .addComponent(txtEstadoCivil)
                            .addComponent(TxtOcupacao)
                            .addComponent(txtCliDataValidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCliDataEmissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel21)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCliDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCliDataValidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtArquivoIdentificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLocalNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtOcupacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {TxtOcupacao, txtArquivoIdentificacao, txtCliDataEmissao, txtCliDataValidade, txtDataNascimento, txtEstadoCivil, txtLocalNascimento});

        jLabel12.setText("Nome do cônjuge ");

        jLabel13.setText("Tipo de documento");

        comboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BI", "Passaporte", "Carta de condução", "Outros" }));

        jLabel22.setText("Número do documento");

        jLabel23.setText("Arquivo de identificação");

        jLabel8.setText("Data de emissao");

        DataEmissaoConjugue.setDateFormatString("dd/MM/yyyy");

        jLabel9.setText("Data de validade");

        DataValidadeConjugue.setDateFormatString("dd/MM/yyyy");

        jLabel14.setText("Ocupação");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(txtConNumeroDoc)
                    .addComponent(jLabel23)
                    .addComponent(txtConArquivoIdentificacao)
                    .addComponent(jLabel8)
                    .addComponent(DataEmissaoConjugue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(DataValidadeConjugue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14)
                    .addComponent(OcupacaoConjugue)
                    .addComponent(jLabel22)
                    .addComponent(jLabel13)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(comboTipoDocumento, 0, 196, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(txtNomeConjugue))
                .addGap(44, 44, 44))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeConjugue, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConNumeroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConArquivoIdentificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataEmissaoConjugue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataValidadeConjugue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OcupacaoConjugue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {DataEmissaoConjugue, DataValidadeConjugue, comboTipoDocumento, txtConArquivoIdentificacao, txtConNumeroDoc, txtNomeConjugue});

        jButton1.setText("Adicionar Foto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 370, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(lblPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1)))))
        );

        tblClients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nº. Cliente", "Nome", "Endereço", "Telefone", "Documento", "Nº documento", "Data emissão", "Data validade"
            }
        ));
        tblClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClients);

        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSearchMouseClicked(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/24x24/icons8-search-24.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNovo)
                .addGap(34, 34, 34)
                .addComponent(btnAdicionar)
                .addGap(41, 41, 41)
                .addComponent(btnActualizar)
                .addGap(41, 41, 41)
                .addComponent(btnApagar)
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo)
                    .addComponent(btnAdicionar)
                    .addComponent(btnActualizar)
                    .addComponent(btnApagar))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Formulario", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        ActivateButtons(true, false, false);
        cleanField();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
      if(validarCampos()){
          JOptionPane.showMessageDialog(null, "preencha dos campos obrigatorios *");
      }else{
            if (clienteDao.ifClientExist(txtNome.getText())) {
            JOptionPane.showMessageDialog(null, "Cliente ja existe");
        } else {
            insert();
            cleanField();
            fillTable();
            ActivateButtons(false, false, false);
        }
      }
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        update();
        cleanField();
        fillTable();
        ActivateButtons(false, false, false);

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        delete();
        cleanField();
        fillTable();
        ActivateButtons(false, false, false);
    }//GEN-LAST:event_btnApagarActionPerformed

    private void tblClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientsMouseClicked
        ClickedTable();
        ActivateButtons(false, true, true);
    }//GEN-LAST:event_tblClientsMouseClicked

    private void txtSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchByName();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            adicionarImagem();
        } catch (IOException ex) {
            Logger.getLogger(frmCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtQuarteiraoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuarteiraoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuarteiraoActionPerformed

    private void txtCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCasaActionPerformed

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
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser DataEmissaoConjugue;
    private com.toedter.calendar.JDateChooser DataValidadeConjugue;
    private javax.swing.JTextField OcupacaoConjugue;
    private javax.swing.JTextField TxtOcupacao;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> comboTipoDocumento;
    private javax.swing.JComboBox<String> comoDocumento;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JTable tblClients;
    private javax.swing.JTextField txtArquivoIdentificacao;
    private javax.swing.JTextField txtCasa;
    private com.toedter.calendar.JDateChooser txtCliDataEmissao;
    private com.toedter.calendar.JDateChooser txtCliDataValidade;
    private javax.swing.JTextField txtConArquivoIdentificacao;
    private javax.swing.JTextField txtConNumeroDoc;
    private com.toedter.calendar.JDateChooser txtDataNascimento;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtEstadoCivil;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLocalNascimento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeConjugue;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtQuarteirao;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
