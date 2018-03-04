
package IntegradorEntidades;

public class Produto {
    public int idProduto;
    public String nomeProduto;
    public String descricao;
    public long PrecoUnitario;

    public Produto(int idProduto, String nomeProduto, String descricao, long PrecoUnitario) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.PrecoUnitario = PrecoUnitario;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getPrecoUnitario() {
        return PrecoUnitario;
    }

    public void setPrecoUnitario(long PrecoUnitario) {
        this.PrecoUnitario = PrecoUnitario;
    }
    
    
}
