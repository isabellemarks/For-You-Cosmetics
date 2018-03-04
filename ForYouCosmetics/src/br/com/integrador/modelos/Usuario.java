/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.modelos;

/**
 *
 * @author 20121164010317
 */
public class Usuario {

    private int idUsuario;
    private String login;
    private String senha;
    private int status;

    public Usuario() {
    }

    public Usuario(String login, String senha, int status) {
        this.login = login;
        this.senha = senha;
        this.status = status;
    }
        
    public Usuario(int idUsuario, String login, String senha, int status) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.senha = senha;
        this.status = status;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.idUsuario + " " + this.login + " " + this.senha + " " + this.status;
    }

}
