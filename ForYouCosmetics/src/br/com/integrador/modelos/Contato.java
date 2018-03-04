/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.modelos;

/**
 *
 * @author Isabelle
 */
public class Contato {
    private int idContato;
    private String nome;
    private String email;
    private int telefone;
    private String voceENosso;
    private String assunto;
    private String mensagem;

    public Contato() {
    }

    public Contato(String nome, String email, int telefone, String voceENosso, String assunto, String mensagem) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.voceENosso = voceENosso;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getVoceENosso() {
        return voceENosso;
    }

    public void setVoceENosso(String voceENosso) {
        this.voceENosso = voceENosso;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    @Override
    public String toString() {
        return this.idContato + " " + this.nome + " " + this.email + " " + this.telefone +" " +this.voceENosso +" " + this.assunto + " " + this.mensagem;
    }
}
