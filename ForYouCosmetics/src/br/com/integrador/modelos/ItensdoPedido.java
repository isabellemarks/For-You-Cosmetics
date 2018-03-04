
package br.com.integrador.modelos;

public class ItensdoPedido {
    public int idItensDoPedido;
    public int quantidade;
    public Produto produto;
    public Pedido pedido;

    public ItensdoPedido() {
    }
    
    public ItensdoPedido(Pedido idPedido, Produto idProduto, int quantidade) {
        this.pedido = idPedido;
        this.produto = idProduto;
        this.quantidade = quantidade;
    }
    
    public ItensdoPedido(int idItensDoPedido, int quantidade, Produto idProduto, Pedido idPedido) {
        this.idItensDoPedido = idItensDoPedido;
        this.quantidade = quantidade;
        this.produto = idProduto;
        this.pedido = idPedido;
    }

    public int getIdItensDoPedido() {
        return idItensDoPedido;
    }

    public void setIdItensDoPedido(int idItensDoPedido) {
        this.idItensDoPedido = idItensDoPedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    @Override
    public String toString() {
        return this.idItensDoPedido+ " " +this.quantidade+ " "+this.produto+ " "+this.pedido;
    }
    
    
    
}
