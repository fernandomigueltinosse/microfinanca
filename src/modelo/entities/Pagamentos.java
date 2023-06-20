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
    private Date data_pagamento;
    private Integer numero_prestacao;
    private Emprestimo emprestimo;

    /**
     * @return the pg_id
     */
    public Integer getPg_id() {
        return pg_id;
    }

    /**
     * @param pg_id the pg_id to set
     */
    public void setPg_id(Integer pg_id) {
        this.pg_id = pg_id;
    }

    /**
     * @return the pg_valor_pago
     */
    public Double getPg_valor_pago() {
        return pg_valor_pago;
    }

    /**
     * @param pg_valor_pago the pg_valor_pago to set
     */
    public void setPg_valor_pago(Double pg_valor_pago) {
        this.pg_valor_pago = pg_valor_pago;
    }

    /**
     * @return the data_pagamento
     */
    public Date getData_pagamento() {
        return data_pagamento;
    }

    /**
     * @param data_pagamento the data_pagamento to set
     */
    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    /**
     * @return the numero_prestacao
     */
    public Integer getNumero_prestacao() {
        return numero_prestacao;
    }

    /**
     * @param numero_prestacao the numero_prestacao to set
     */
    public void setNumero_prestacao(Integer numero_prestacao) {
        this.numero_prestacao = numero_prestacao;
    }

    /**
     * @return the emprestimo
     */
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    /**
     * @param emprestimo the emprestimo to set
     */
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    
}
