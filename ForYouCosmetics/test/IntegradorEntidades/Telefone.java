
package IntegradorEntidades;

public class Telefone {
    public int idTelefone;
    public int Telefone;
    public Cliente idCliente;

    public Telefone(int idTelefone, int Telefone, Cliente idCliente) {
        this.idTelefone = idTelefone;
        this.Telefone = Telefone;
        this.idCliente = idCliente;
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

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }
    
    
    
}
