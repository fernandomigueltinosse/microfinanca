/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entities;

import java.util.Date;

/**
 *
 * @author Tomas
 */
public class Pagamentos {
    private Integer pg_id;
    private Double pg_valor_pago;
    private String data_pagamento;
    private Integer numero_prestacao;
    private Emprestimo emprestimo;

    public Integer getPg_id() {
        return pg_id;
    }

    public void setPg_id(Integer pg_id) {
        this.pg_id = pg_id;
    }

    public Double getPg_valor_pago() {
        return pg_valor_pago;
    }

    public void setPg_valor_pago(Double pg_valor_pago) {
        this.pg_valor_pago = pg_valor_pago;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public Integer getNumero_prestacao() {
        return numero_prestacao;
    }

    public void setNumero_prestacao(Integer numero_prestacao) {
        this.numero_prestacao = numero_prestacao;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    

    
}
