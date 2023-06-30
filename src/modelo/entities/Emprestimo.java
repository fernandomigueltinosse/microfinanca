/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entities;

import java.time.LocalDate;


/**
 *
 * @author Tomas
 */
public class Emprestimo  extends Cliente{
    private Integer ep_id;
    private Double ep_montante ;
    private String ep_data_emprestimo;
    private Double ep_juros;
    private Double ep_total;
    private Integer ep_prestacoes;
    private String ep_prazo;
    private Integer ep_frequenciaPagamento;
    private Cliente cliente;

    public Integer getEp_id() {
        return ep_id;
    }

    public void setEp_id(Integer ep_id) {
        this.ep_id = ep_id;
    }

    public Double getEp_montante() {
        return ep_montante;
    }

    public void setEp_montante(Double ep_montante) {
        this.ep_montante = ep_montante;
    }

    public String getEp_data_emprestimo() {
        return ep_data_emprestimo;
    }

    public void setEp_data_emprestimo(String ep_data_emprestimo) {
        this.ep_data_emprestimo = ep_data_emprestimo;
    }

    public Double getEp_juros() {
        return ep_juros;
    }

    public void setEp_juros(Double ep_juros) {
        this.ep_juros = ep_juros;
    }

    public Double getEp_total() {
        return ep_total;
    }

    public void setEp_total(Double ep_total) {
        this.ep_total = ep_total;
    }

    public Integer getEp_prestacoes() {
        return ep_prestacoes;
    }

    public void setEp_prestacoes(Integer ep_prestacoes) {
        this.ep_prestacoes = ep_prestacoes;
    }

    public String getEp_prazo() {
        return ep_prazo;
    }

    public void setEp_prazo(String ep_prazo) {
        this.ep_prazo = ep_prazo;
    }

    public Integer getEp_frequenciaPagamento() {
        return ep_frequenciaPagamento;
    }

    public void setEp_frequenciaPagamento(Integer ep_frequenciaPagamento) {
        this.ep_frequenciaPagamento = ep_frequenciaPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Emprestimo{" + "ep_id=" + ep_id + ", ep_montante=" + ep_montante + ", ep_data_emprestimo=" + ep_data_emprestimo + ", ep_juros=" + ep_juros + ", ep_total=" + ep_total + ", ep_prestacoes=" + ep_prestacoes + ", ep_prazo=" + ep_prazo + ", ep_frequenciaPagamento=" + ep_frequenciaPagamento + ", cliente=" + cliente + '}';
    }

    
   
    }
