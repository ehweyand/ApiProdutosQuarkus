package org.produtos.service.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ResultadoConsultaDTO {
    private String mes;
    private String clienteNome;
    private BigDecimal total;
    private String produtoDescricao;

    public ResultadoConsultaDTO(Object[] columns) {
        this.mes = columns[0] != null ? columns[0].toString() : null;
        this.clienteNome = (String) columns[1];
        this.total = (BigDecimal) columns[2];
        this.produtoDescricao = (String) columns[3];
    }

    public ResultadoConsultaDTO(String mes, String clienteNome, BigDecimal total, String produtoDescricao) {
        this.mes = mes;
        this.clienteNome = clienteNome;
        this.total = total;
        this.produtoDescricao = produtoDescricao;
    }

    public ResultadoConsultaDTO() {
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }
}
