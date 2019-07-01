
public class Produto {

    private int id;
    private String nome;
    private double preco;
    private int unidade;
    private int quantidadeEstoque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Produto(int id, String nome, double preco, int unidade, int quantidadeEstoque) {
        setId(id);
        setNome(nome);
        setPreco(preco);
        setUnidade(unidade);
        setQuantidadeEstoque(quantidadeEstoque);
    }

    public Produto() {
        this(0, "", 0.0, 0, 0);
    }

    @Override
    public String toString() {
        return (" Id: " + getId()
                + " | Nome: " + getNome()
                + " | Preco: R$ " + getPreco()
                + " | Unidade: " + getUnidade()
                + " | Quantidade em estoque: " + getQuantidadeEstoque());
    }
}
