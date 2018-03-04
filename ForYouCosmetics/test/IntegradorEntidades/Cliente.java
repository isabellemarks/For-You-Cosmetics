
package IntegradorEntidades;

public class Cliente {
    public int idCliente;
    public String nome;
    public int dataNascimento;
    public String email;
    public Endereco idEndereco;

    public Cliente(int idCliente, String nome, int dataNascimento, String email, Endereco idEndereco) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.idEndereco = idEndereco;
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

    public int getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(int dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Endereco idEndereco) {
        this.idEndereco = idEndereco;
    }
    
    
    
}
