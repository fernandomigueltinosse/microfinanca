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
    private String cli_data_emissao;
    private String cli_data_validade;
    private String cli_data_registro;
    private byte[] cli_foto;
    private String foto;
    private String cli_estado_civil;
    private String cli_arquivo_identificacao;
    private String cli_quarteirao;
    private String cli_casa_numero;
    private String   cli_data_de_nascimento;
    private String cli_ocupacao;
    private String cli_local_nascimento;
    private String nome_conjugue;
    private String con_tipo_documento;
    private String con_data_de_emissao;
    private String con_data_de_validade;
    private String con_Ocupacao;
    

   
    
    

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

    public String getCli_data_emissao() {
        return cli_data_emissao;
    }

    public void setCli_data_emissao(String cli_data_emissao) {
        this.cli_data_emissao = cli_data_emissao;
    }

    public String getCli_data_validade() {
        return cli_data_validade;
    }

    public void setCli_data_validade(String cli_data_validade) {
        this.cli_data_validade = cli_data_validade;
    }

    public String getCli_data_registro() {
        return cli_data_registro;
    }

    public void setCli_data_registro(String cli_data_registro) {
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

    public String getCli_estado_civil() {
        return cli_estado_civil;
    }

    public void setCli_estado_civil(String cli_estado_civil) {
        this.cli_estado_civil = cli_estado_civil;
    }

    public String getCli_arquivo_identificacao() {
        return cli_arquivo_identificacao;
    }

    public void setCli_arquivo_identificacao(String cli_arquivo_identificacao) {
        this.cli_arquivo_identificacao = cli_arquivo_identificacao;
    }

    public String getCli_quarteirao() {
        return cli_quarteirao;
    }

    public void setCli_quarteirao(String cli_quarteirao) {
        this.cli_quarteirao = cli_quarteirao;
    }

    public String getCli_casa_numero() {
        return cli_casa_numero;
    }

    public void setCli_casa_numero(String cli_casa_numero) {
        this.cli_casa_numero = cli_casa_numero;
    }

    public String getCli_data_de_nascimento() {
        return cli_data_de_nascimento;
    }

    public void setCli_data_de_nascimento(String cli_data_de_nascimento) {
        this.cli_data_de_nascimento = cli_data_de_nascimento;
    }

    public String getCli_ocupacao() {
        return cli_ocupacao;
    }

    public void setCli_ocupacao(String cli_ocupacao) {
        this.cli_ocupacao = cli_ocupacao;
    }

    public String getCli_local_nascimento() {
        return cli_local_nascimento;
    }

    public void setCli_local_nascimento(String cli_local_nascimento) {
        this.cli_local_nascimento = cli_local_nascimento;
    }

    public String getNome_conjugue() {
        return nome_conjugue;
    }

    public void setNome_conjugue(String nome_conjugue) {
        this.nome_conjugue = nome_conjugue;
    }

    public String getCon_tipo_documento() {
        return con_tipo_documento;
    }

    public void setCon_tipo_documento(String con_tipo_documento) {
        this.con_tipo_documento = con_tipo_documento;
    }

    public String getCon_data_de_emissao() {
        return con_data_de_emissao;
    }

    public void setCon_data_de_emissao(String con_data_de_emissao) {
        this.con_data_de_emissao = con_data_de_emissao;
    }

    public String getCon_data_de_validade() {
        return con_data_de_validade;
    }

    public void setCon_data_de_validade(String con_data_de_validade) {
        this.con_data_de_validade = con_data_de_validade;
    }

    public String getCon_Ocupacao() {
        return con_Ocupacao;
    }

    public void setCon_Ocupacao(String con_Ocupacao) {
        this.con_Ocupacao = con_Ocupacao;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cli_id=" + cli_id + ", cli_nome=" + cli_nome + ", cli_endereco=" + cli_endereco + ", cli_telefone=" + cli_telefone + ", cli_tipo_documento=" + cli_tipo_documento + ", cli_numero=" + cli_numero + ", cli_data_emissao=" + cli_data_emissao + ", cli_data_validade=" + cli_data_validade + ", cli_data_registro=" + cli_data_registro + ", cli_foto=" + cli_foto + ", foto=" + foto + ", cli_estado_civil=" + cli_estado_civil + ", cli_arquivo_identificacao=" + cli_arquivo_identificacao + ", cli_quarteirao=" + cli_quarteirao + ", cli_casa_numero=" + cli_casa_numero + ", cli_data_de_nascimento=" + cli_data_de_nascimento + ", cli_ocupacao=" + cli_ocupacao + ", cli_local_nascimento=" + cli_local_nascimento + ", nome_conjugue=" + nome_conjugue + ", con_tipo_documento=" + con_tipo_documento + ", con_data_de_emissao=" + con_data_de_emissao + ", con_data_de_validade=" + con_data_de_validade + ", con_Ocupacao=" + con_Ocupacao + '}';
    }

   
   

    

}
