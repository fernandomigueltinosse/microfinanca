/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entities;

/**
 *
 * @author fernando
 */
public class Multas {
    
    private Integer m_id;
    private Double multa;
    private Cliente cliente;
    private Double pagarMulta;
    private String mdata;

    /**
     * @return the m_id
     */
    public Integer getM_id() {
        return m_id;
    }

    /**
     * @param m_id the m_id to set
     */
    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    /**
     * @return the multa
     */
    public Double getMulta() {
        return multa;
    }

    /**
     * @param multa the multa to set
     */
    public void setMulta(Double multa) {
        this.multa = multa;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the pagarMulta
     */
    public Double getPagarMulta() {
        return pagarMulta;
    }

    /**
     * @param pagarMulta the pagarMulta to set
     */
    public void setPagarMulta(Double pagarMulta) {
        this.pagarMulta = pagarMulta;
    }

    /**
     * @return the mdata
     */
    public String getMdata() {
        return mdata;
    }

    /**
     * @param mdata the mdata to set
     */
    public void setMdata(String mdata) {
        this.mdata = mdata;
    }
}
