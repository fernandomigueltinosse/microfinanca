/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entities;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc2
 */
public class Cliente {

    private Integer cli_id;
    private String cli_nome;
    private String cli_endereco;
    private Integer cli_telefone;
    private String cli_tipo_documento;
    private String cli_numero;
    private Date cli_data_emissao;
    private Date cli_data_validade;
    private Date cli_data_registro;
    private byte[] cli_foto;
    private String foto;

   
    
    

    public void instatiateModel(List<Cliente> list, DefaultTableModel model) {
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

    public Integer getCli_id() {
        return cli_id;
    }

    public void setCli_id(Integer cli_id) {
        this.cli_id = cli_id;
    }

    public String getCli_nome() {
        return cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    public String getCli_endereco() {
        return cli_endereco;
    }

    public void setCli_endereco(String cli_endereco) {
        this.cli_endereco = cli_endereco;
    }

    public Integer getCli_telefone() {
        return cli_telefone;
    }

    public void setCli_telefone(Integer cli_telefone) {
        this.cli_telefone = cli_telefone;
    }

    public String getCli_tipo_documento() {
        return cli_tipo_documento;
    }

    public void setCli_tipo_documento(String cli_tipo_documento) {
        this.cli_tipo_documento = cli_tipo_documento;
    }

    public String getCli_numero() {
        return cli_numero;
    }

    public void setCli_numero(String cli_numero) {
        this.cli_numero = cli_numero;
    }

    public Date getCli_data_emissao() {
        return cli_data_emissao;
    }

    public void setCli_data_emissao(Date cli_data_emissao) {
        this.cli_data_emissao = cli_data_emissao;
    }

    public Date getCli_data_validade() {
        return cli_data_validade;
    }

    public void setCli_data_validade(Date cli_data_validade) {
        this.cli_data_validade = cli_data_validade;
    }

    public Date getCli_data_registro() {
        return cli_data_registro;
    }

    public void setCli_data_registro(Date cli_data_registro) {
        this.cli_data_registro = cli_data_registro;
    }

    public byte[] getCli_foto() {
        return cli_foto;
    }

    public void setCli_foto(byte[] cli_foto) {
        this.cli_foto = cli_foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
