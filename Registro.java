
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class Registro extends Produto {

    public Registro(int id, String nome, double preco, int unidade, int quantidadeEstoque) {
        super(id, nome, preco, unidade, quantidadeEstoque);
    }

    public Registro() {
        super();
    }
    
    private List<Produto> prod;
    public Registro(List<Produto> prod) {
        super();
        this.prod=prod;
    }

    private String montarPalavra(RandomAccessFile arquivo, int tamanho) throws IOException {
        char palavra[] = new char[tamanho];
        char temp;
        for (int i = 0; i < palavra.length; i++) {
            temp = arquivo.readChar();
            palavra[i] = temp;
        }
        return new String(palavra).replace('\0', ' ');
    }

    private void escreverPalavra(RandomAccessFile arquivo, String palavra, int tamanho) throws IOException {
        StringBuffer buf = null;
        if (palavra != null) {
            buf = new StringBuffer(palavra);
        } else {
            buf = new StringBuffer(tamanho);
        }
        buf.setLength(tamanho);
        arquivo.writeChars(buf.toString());
    }

    public void escrita(RandomAccessFile arquivo) throws IOException {
        arquivo.writeInt(getId());
        escreverPalavra(arquivo, getNome(), 30);
        arquivo.writeDouble(getPreco());
        arquivo.writeInt(getUnidade());
        arquivo.writeInt(getQuantidadeEstoque());
    }

    public void leitura(RandomAccessFile arquivo) throws IOException {
        setId(arquivo.readInt());
        setNome(montarPalavra(arquivo, 30).trim());
        setPreco(arquivo.readDouble());
        setUnidade(arquivo.readInt());
        setQuantidadeEstoque(arquivo.readInt());
    }

    public int getTamanhoRegistro() {
        return ((4 * 3) + (2 * 30) + 8);
    }
}
