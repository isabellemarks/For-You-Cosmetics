
package IntegradorEntidades;

import java.util.Date;

public class Pedido {
    public int idPedido;
    public long valorPedidoTotal;
    public Date dataPedido;
    public Cliente idCliente;

    public Pedido(int idPedido, long valorPedidoTotal, Date dataPedido, Cliente idCliente) {
        this.idPedido = idPedido;
        this.valorPedidoTotal = valorPedidoTotal;
        this.dataPedido = dataPedido;
        this.idCliente = idCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public long getValorPedidoTotal() {
        return valorPedidoTotal;
    }

    public void setValorPedidoTotal(long valorPedidoTotal) {
        this.valorPedidoTotal = valorPedidoTotal;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
    
    
    
    
}
