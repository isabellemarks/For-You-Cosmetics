/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.contato;

import br.com.integrador.dao.ContatoDao;
import br.com.integrador.modelos.Contato;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteAdicionarContato {
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Informe o seu nome:");
        String nome = s.nextLine();
        
        System.out.println("Informe o seu email:");
        String email = s.nextLine();
        
        System.out.println("Informe o seu telefone:");
        int telefone = s.nextInt();
        
        s.nextLine();
        System.out.println("Informe o que você é nosso:");
        String voceENosso = s.nextLine();
        
        System.out.println("Informe o assunto:");
        String assunto = s.nextLine();
        
        System.out.println("Informe a mensagem:");
        String mensagem = s.nextLine();
        
        Contato meuContato = new Contato(nome, email, telefone, voceENosso, assunto, mensagem);
        ContatoDao contatoDao = new ContatoDao();
        int idContato = contatoDao.cadastrarUmContato(meuContato);
        meuContato.setIdContato(idContato);
    }
}
