/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.modelos;

/**
 *
 * @author Estefania
 */
public class Relatorios {

    private int idRelatorio;
    private Pedido idPedido;
    private ItensdoPedido itensdopedido;

    
    
    public ItensdoPedido getItensdopedido() {
        return itensdopedido;
    }

    public void setItensdopedido(ItensdoPedido itensdopedido) {
        this.itensdopedido = itensdopedido;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idP) {
        this.idPedido = idP;
    }
}
