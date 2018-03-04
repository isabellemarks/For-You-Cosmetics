/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.contato;

import br.com.integrador.dao.ContatoDao;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteConsultarContatos {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ContatoDao contatoDao = new ContatoDao();
        
        contatoDao.consultarTodosOsContatos();
        
        System.out.println("Digite um nome a ser pesquisado:");
        String nome = s.nextLine();
        contatoDao.consultarContatosPorNome(nome);
    }
}
