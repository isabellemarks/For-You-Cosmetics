
package br.com.integrador.modelos;

public class Telefone {
    private int idTelefone;
    private int Telefone;
    
    public Telefone() {
    }

    public Telefone(int Telefone) {
        this.Telefone = Telefone;
    }   

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public int getTelefone() {
        return Telefone;
    }

    public void setTelefone(int Telefone) {
        this.Telefone = Telefone;
    }
    @Override
    public String toString() {
        return +this.idTelefone+ " " +this.Telefone;
    }
    
}
