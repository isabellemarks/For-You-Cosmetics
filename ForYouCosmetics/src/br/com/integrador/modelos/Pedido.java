
package br.com.integrador.modelos;

import java.util.Date;

public class Pedido {
    public int idPedido;
    public float valorPedidoTotal;
    public Date dataPedido;
    public Cliente cliente;
    

    public Pedido() {
    }
    public Pedido(Date dataPedido, float valorPedidoTotal) {
        this.dataPedido = dataPedido;
        this.valorPedidoTotal = valorPedidoTotal;
    }
    public Pedido(Date dataPedido){
         this.dataPedido = dataPedido;
        
    }

    public Pedido(float valorPedidoTotal, Date dataPedido, Cliente idCliente) {
        this.valorPedidoTotal = valorPedidoTotal;
        this.dataPedido = dataPedido;
        this.cliente = idCliente;
    }

    public Pedido(int idPedido, Cliente cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
    }

    public Pedido(int idPedido) {
        this.idPedido = idPedido;
    }
    

    public Pedido(int idPedido, float valorPedidoTotal, Date dataPedido, Cliente idCliente) {
        this.idPedido = idPedido;
        this.valorPedidoTotal = valorPedidoTotal;
        this.dataPedido = dataPedido;
        this.cliente = idCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public float getValorPedidoTotal() {
        return valorPedidoTotal;
    }

    public void setValorPedidoTotal(float valorPedidoTotal) {
        this.valorPedidoTotal = valorPedidoTotal;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    @Override
    public String toString() {
        return this.idPedido+ " " +this.valorPedidoTotal+ " "+this.dataPedido+ " "+this.cliente;
    }
    
    
    
    
}
