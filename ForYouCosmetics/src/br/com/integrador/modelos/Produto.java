package br.com.integrador.modelos;

public class Produto {

    public int idProduto;
    public String nomeProduto;
    public String descricao;
    public float precoUnitario;
    private int status;
    public Fornecedor idFornecedor;

    public Produto() {
    }

    public Produto(int idProduto, String nomeProduto, String descricao, float PrecoUnitario, int status, Fornecedor idFornecedor) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.precoUnitario = PrecoUnitario;
        this.status = status;
        this.idFornecedor = idFornecedor;
    }

    /*public Produto(String nomeProduto, String descricao, float PrecoUnitario, Fornecedor idFornecedor) {
     this.nomeProduto = nomeProduto;
     this.descricao = descricao;
     this.PrecoUnitario = PrecoUnitario;
     this.idFornecedor = idFornecedor;
     }*/
    public Produto(String nomeDoProduto, String descricaoDoProduto, float precoUnitarioDoProduto, int status, Fornecedor idFornecedor) {
        this.nomeProduto = nomeDoProduto;
        this.descricao = descricaoDoProduto;
        this.precoUnitario = precoUnitarioDoProduto;
        this.status = status;
        this.idFornecedor = idFornecedor;
    }

    public Produto(String nomeDoProduto, String descricaoDoProduto, float precoUnitarioDoProduto) {
        this.nomeProduto = nomeDoProduto;
        this.descricao = descricaoDoProduto;
        this.precoUnitario = precoUnitarioDoProduto;
    }

    public Produto( int idProduto, String nomeProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
    }

    public Produto(int idProduto) {
        this.idProduto = idProduto;
    }
    

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idEndereco) {
        this.idProduto = idEndereco;
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

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(long PrecoUnitario) {
        this.precoUnitario = PrecoUnitario;
    }

    public Fornecedor getFornecedor() {
        return idFornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.idFornecedor = fornecedor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return +this.idProduto + " " + this.nomeProduto + " " + this.descricao + " " + this.precoUnitario + "  " + this.status + " " + this.idFornecedor;
    }

}
