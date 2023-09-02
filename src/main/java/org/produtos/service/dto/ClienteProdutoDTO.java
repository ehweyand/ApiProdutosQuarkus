package org.produtos.service.dto;

import java.math.BigDecimal;

public class ClienteProdutoDTO {

    private Long clientesId;
    private String clienteNome;
    private String clienteEndereco;
    private Long pedidosId;
    private BigDecimal totalPedido;
    private Long produtoId;
    private String produtoDescricao;
    private BigDecimal valorProduto;
    private Integer rankValor;

    public ClienteProdutoDTO(Object[] columns) {
        this.clientesId = columns[0] != null ? Long.valueOf(columns[0].toString()) : null;
        this.clienteNome = (String) columns[1];
        this.clienteEndereco = (String) columns[2];
        this.pedidosId = columns[3] != null ? Long.valueOf(columns[3].toString()) : null;
        this.totalPedido = (BigDecimal) columns[4];
        this.produtoId = columns[5] != null ? Long.valueOf(columns[5].toString()) : null;
        this.produtoDescricao = (String) columns[6];
        this.valorProduto = (BigDecimal) columns[7];
        this.rankValor = columns[8] != null ? Integer.valueOf(columns[8].toString()) : null;
    }

    public ClienteProdutoDTO(Long clientesId, String clienteNome, String clienteEndereco, Long pedidosId,
                             BigDecimal totalPedido, Long produtoId, String produtoDescricao,
                             BigDecimal valorProduto, Integer rankValor) {
        this.clientesId = clientesId;
        this.clienteNome = clienteNome;
        this.clienteEndereco = clienteEndereco;
        this.pedidosId = pedidosId;
        this.totalPedido = totalPedido;
        this.produtoId = produtoId;
        this.produtoDescricao = produtoDescricao;
        this.valorProduto = valorProduto;
        this.rankValor = rankValor;
    }

    public Long getClientesId() {
        return clientesId;
    }

    public void setClientesId(Long clientesId) {
        this.clientesId = clientesId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteEndereco() {
        return clienteEndereco;
    }

    public void setClienteEndereco(String clienteEndereco) {
        this.clienteEndereco = clienteEndereco;
    }

    public Long getPedidosId() {
        return pedidosId;
    }

    public void setPedidosId(Long pedidosId) {
        this.pedidosId = pedidosId;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(BigDecimal valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Integer getRankValor() {
        return rankValor;
    }

    public void setRankValor(Integer rankValor) {
        this.rankValor = rankValor;
    }
}
