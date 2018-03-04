package br.com.integrador.modelos;

import java.util.List;



public class Fornecedor {
    private int idFornecedor;
    private String nome;
    private String email;
    private int status;
    private Produto Produto;
    private Endereco Endereco;
    private List<Telefone> telefones;
    private Telefone telefonee;
    
    

    public Fornecedor() {
    }

    public Fornecedor(int idFornecedor, String nome, String email, Endereco Endereco, Telefone telefonee) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.email = email;
        this.Endereco = Endereco;
        this.telefonee = telefonee;
    }

    

    public Fornecedor(int idFornecedor, String nome, String email, int status, Endereco Endereco, List<Telefone> telefones) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.email = email;
        this.status = status;
        this.Endereco = Endereco;
        this.telefones = telefones;
        
    }

    public Fornecedor(String nome, String email, Endereco Endereco, List<Telefone> telefones) {
        this.nome = nome;
        this.email = email;
        this.Endereco = Endereco;
        this.telefones = telefones;
        
    }
    
    public Fornecedor(int idFornecedor, int status) {
        this.idFornecedor = idFornecedor;
        this.status = status;
    }
    public Fornecedor(String nomeFornecedor){
        this.nome = nomeFornecedor;
        
    }
    public Fornecedor(String nomeFornecedor, String email){
        this.nome = nomeFornecedor;
        this.email = email;
    }

    
    public Fornecedor (String nome, String email, int status, Endereco idEndereco, List<Telefone> telefones) {
        this.nome = nome;
        this.email = email;
        this.status = status;
        this.Endereco = idEndereco;
        this.telefones = telefones;
    }
    
    public int getIdFornecedor() {
        return idFornecedor;
    }
    
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Produto getProduto() {
        return Produto;
    }

    public void setProduto(Produto idProduto) {
        this.Produto = idProduto;
    }
    
    public Endereco getEndereco() {
        return Endereco;
    }

    public void setEndereco(Endereco idEndereco) {
        this.Endereco = idEndereco;
    }
    
    public List<Telefone> getTelefones() {
        return telefones;
    }
    public Telefone getTelefone(){
        return telefonee;
        
    }
    public void setTelefones(List<Telefone> telefone) {
        this.telefones = telefone;
    }
    @Override
    public String toString() {
        return +this.idFornecedor+ " " +this.nome+ " "+this.email+ " "+this.Produto+ " "+this.Endereco+" "+this.telefones;
    }
}
