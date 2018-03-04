/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.testes.usuariocliente;

import br.com.integrador.dao.UsuarioDao;
import br.com.integrador.modelos.Usuario;
import java.util.Scanner;

/**
 *
 * @author Isabelle
 */
public class TesteAdicionarUsuarioCliente {
    
    public static void main(String[] args) {
        UsuarioDao usuarioDao = new UsuarioDao();
        Scanner s = new Scanner(System.in);
        
        System.out.println("Digite o nome de usuário");
        String login = s.nextLine();
        
        System.out.println("Digite uma senha:");
        String senha = s.nextLine();
        
        int status = 1;
        
        Usuario meuUsuario = new Usuario(login, senha, status);
        int idUsuario = usuarioDao.cadastrarUmUsuario(meuUsuario);
        meuUsuario.setIdUsuario(idUsuario);
    }
}
