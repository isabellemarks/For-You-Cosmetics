
package IntegradorEntidades;

public class ItensdoPedido {
    public int idItenddoPedido;
    public String quantidade;
    public Produto idProduto;
    public Pedido idPedido;

    public ItensdoPedido(int idItenddoPedido, String quantidade, Produto idProduto, Pedido idPedido) {
        this.idItenddoPedido = idItenddoPedido;
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.idPedido = idPedido;
    }

    public int getIdItenddoPedido() {
        return idItenddoPedido;
    }

    public void setIdItenddoPedido(int idItenddoPedido) {
        this.idItenddoPedido = idItenddoPedido;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }
    
    
    
}
