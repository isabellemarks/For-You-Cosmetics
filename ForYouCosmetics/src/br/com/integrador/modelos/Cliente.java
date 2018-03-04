
package br.com.integrador.modelos;

import java.sql.Date;
import java.util.List;

public class Cliente {
    private int idCliente;
    private String nome;
    private Date dataNascimento;
    private String email;
    private int status;
    private Endereco endereco;
    private List<Telefone> telefones;
    

    public Cliente() {
    }
    
    public Cliente(int idCliente, int status) {
        this.idCliente = idCliente;
        this.status = status;
    }
    public Cliente(String nomeCliente){
        this.nome = nomeCliente;
    }
    
    
    public Cliente(String nome, Date dataNascimento, String email, Endereco umEndereco,List<Telefone> telefones) {
        
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.endereco = umEndereco;
        this.telefones = telefones;
        
              
    }
    
    public Cliente(String nome, Date dataNascimento, String email, int status, Endereco umEndereco, List<Telefone> telefones) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.status = status;
        this.endereco = umEndereco;
        this.telefones = telefones;
              
    }
    
    
    public Cliente(int umId, String nome, Date dataNascimento, String email, int status, Endereco umEndereco,List<Telefone> telefones) {
        this(nome, dataNascimento, email, status, umEndereco, telefones);
        this.idCliente = umId;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco umEndereco) {
        this.endereco = umEndereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefone) {
        this.telefones = telefone;
    }

    @Override
    public String toString() {
        return this.idCliente+ " " +this.nome+ " "+this.email+ " "+this.dataNascimento;
    }
    
}
