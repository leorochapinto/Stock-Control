
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class Gerenciador {

    private String nomeArquivo;
    private RandomAccessFile arquivo;

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public RandomAccessFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(RandomAccessFile arquivo) {
        this.arquivo = arquivo;
    }

    public Gerenciador() {
        setNomeArquivo("PRODUTO.DAT");
        abrirArquivo();
    }

    public Gerenciador(String nomeArquivo) {
        setNomeArquivo(nomeArquivo);
        abrirArquivo();
    }

    public void abrirArquivo() {
        try {
            File fileArquivo = new File(getNomeArquivo());
            arquivo = new RandomAccessFile(fileArquivo, "rw");
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Problemas ao manipular o arquivo: " + io);
        }
    }

    public Registro leitura(String mensagem) {
        if (!mensagem.equals("")) {
            JOptionPane.showMessageDialog(null, mensagem);
        }
        Registro produto = new Registro();
        produto.setId(Integer.parseInt(JOptionPane.showInputDialog("Digite o número de identificação")));
        produto.setNome(JOptionPane.showInputDialog("Digite o nome"));
        produto.setPreco(Double.parseDouble(JOptionPane.showInputDialog("Digite o preço")));
        produto.setUnidade(Integer.parseInt(JOptionPane.showInputDialog("Digite a unidade")));
        produto.setQuantidadeEstoque(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade")));
        return produto;
    }

    public boolean incluir(Registro registrar) {
        try {
            arquivo.seek(arquivo.length());
            registrar.escrita(arquivo);
            return true;
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    public boolean excluir(int chave) {
        int posicao = -1;
        Registro registro = new Registro();
        try {
            arquivo.seek(0);
            boolean achei = false;
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
                registro.leitura(arquivo);
                if (registro.getId() == chave) {
                    achei = true;
                }
                posicao = posicao + 1;
            }
            if (achei == true) {
                registro.setId(-1);
                registro.setNome(null);
                arquivo.seek(posicao * registro.getTamanhoRegistro());
                registro.escrita(arquivo);
                return true;
            } else {
                return false;
            }
        } catch (EOFException eof) {
            JOptionPane.showMessageDialog(null, "Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    public boolean alterar(int chave, Registro produto) {
        try {
            int posicao = posicao(chave);
            if (posicao != -1) {
                Registro registro = produto;
                arquivo.seek(posicao * registro.getTamanhoRegistro());
                registro.escrita(arquivo);
                return true;
            } else {
                return false;
            }
        } catch (EOFException eof) {
            JOptionPane.showMessageDialog(null, "Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Problemas ao manipular o arquivo: " + io);
        }
        return false;
    }

    public Registro consultar(int chave, String descritivo) {
        Registro retorno = null;
        Registro registro = new Registro();
        try {
            arquivo.seek(0);
            registro.leitura(arquivo);
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (registro.getId() != chave)) {
                registro.leitura(arquivo);
            }
            if (registro.getId() == chave) {
                retorno = registro;
            }
            if (registro.getNome().equals(descritivo)) {
                retorno = registro;
            }
        } catch (EOFException eof) {
            JOptionPane.showMessageDialog(null, "Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Problemas ao manipular o arquivo: " + io);
        }
        return retorno;
    }

    public boolean zeraArquivo() {
        try {
            arquivo.setLength(0);
            return true;
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Problemas ao manipular o arquivo: " + io);
            return false;
        }
    }

    public int posicao(int chave) {
        int posicao = -1;
        Registro registro = new Registro();
        try {
            arquivo.seek(0);
            boolean achei = false;
            while ((getArquivo().getFilePointer() < getArquivo().length()) && (achei == false)) {
                registro.leitura(arquivo);
                if (registro.getId() == chave) {
                    achei = true;
                }
                posicao = posicao + 1;
            }
            if (achei == true) {
                return posicao;
            } else {
                return -1;
            }
        } catch (EOFException eof) {
            JOptionPane.showMessageDialog(null, "Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Problemas ao manipular o arquivo: " + io);
        }
        return posicao;
    }

    public String relatorio() {
        String linha = "";
        Registro registro = new Registro();
        try {
            arquivo.seek(0);
            while (getArquivo().getFilePointer() < getArquivo().length()) {
                registro.leitura(arquivo);
                if (registro.getId() != -1) {
                    linha = linha + registro.toString() + "\n";
                }
            }
        } catch (EOFException eof) {
            JOptionPane.showMessageDialog(null, "Chegou ao final do arquivo: " + eof);
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Problemas ao manipular o arquivo: " + io);
        }
        return linha;
    }
}
