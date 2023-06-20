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
    private Integer cd_id;
    private Double valor_emprestimo;
    private LocalDate data_do_emprestimo;
    private Double taxa_juros;
    private Double total_a_pagar;
    private Integer prestacoes;
    private LocalDate prazo_de_pagamento;
    private Integer FrequenciaPagamento;
    private Cliente cliente;

    public Integer getCd_id() {
        return cd_id;
    }

    public void setCd_id(Integer cd_id) {
        this.cd_id = cd_id;
    }

    public Double getValor_emprestimo() {
        return valor_emprestimo;
    }

    public void setValor_emprestimo(Double valor_emprestimo) {
        this.valor_emprestimo = valor_emprestimo;
    }

    public LocalDate getData_do_emprestimo() {
        return data_do_emprestimo;
    }

    public void setData_do_emprestimo(LocalDate data_do_emprestimo) {
        this.data_do_emprestimo = data_do_emprestimo;
    }

    public Double getTaxa_juros() {
        return taxa_juros;
    }

    public void setTaxa_juros(Double taxa_juros) {
        this.taxa_juros = taxa_juros;
    }

    public Double getTotal_a_pagar() {
        return total_a_pagar;
    }

    public void setTotal_a_pagar(Double total_a_pagar) {
        this.total_a_pagar = total_a_pagar;
    }

    public Integer getPrestacoes() {
        return prestacoes;
    }

    public void setPrestacoes(Integer prestacoes) {
        this.prestacoes = prestacoes;
    }

    public LocalDate getPrazo_de_pagamento() {
        return prazo_de_pagamento;
    }

    public void setPrazo_de_pagamento(LocalDate prazo_de_pagamento) {
        this.prazo_de_pagamento = prazo_de_pagamento;
    }

    public Integer getFrequenciaPagamento() {
        return FrequenciaPagamento;
    }

    public void setFrequenciaPagamento(Integer FrequenciaPagamento) {
        this.FrequenciaPagamento = FrequenciaPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    

   
}
