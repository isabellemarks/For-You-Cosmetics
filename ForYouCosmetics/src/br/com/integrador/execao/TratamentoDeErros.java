/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.execao;

import javax.swing.JOptionPane;

/**
 *
 * @author 20121164010317
 */
public class TratamentoDeErros {

    public TratamentoDeErros() {

    }

    public void TestarExecao(int id) {
        if (id == -1) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao Cadastrar!");
        }
    }
}
