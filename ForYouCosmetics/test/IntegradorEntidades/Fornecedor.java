
package IntegradorEntidades;

public class Fornecedor {
    public int idFornecedor;
    public String Fornecedornome;
    public int telefone;
    public Produto idProduto;

    public Fornecedor(int idFornecedor, String Fornecedornome, int telefone, Produto idProduto) {
        this.idFornecedor = idFornecedor;
        this.Fornecedornome = Fornecedornome;
        this.telefone = telefone;
        this.idProduto = idProduto;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getFornecedornome() {
        return Fornecedornome;
    }

    public void setFornecedornome(String Fornecedornome) {
        this.Fornecedornome = Fornecedornome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }
    
    
}
