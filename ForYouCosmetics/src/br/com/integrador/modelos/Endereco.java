
package br.com.integrador.modelos;

public class Endereco {
    public int idEndereco;
    public String rua;
    public String bairro;
    public int cep;
    public String complemento;
    public String numero;
    
    
    public Endereco() {
    }

    public Endereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
        
    public Endereco(String rua, String bairro, int cep, String complemento, String numero) {
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.numero = numero;
    }   

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    @Override
    public String toString() {
        return this.idEndereco+ " " +this.rua+ " "+this.bairro+ " "+this.cep+ " "+this.complemento+ " "+this.numero;
    }
    
}
