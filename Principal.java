
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String Arg[]) {
        Gerenciador gerenciar = new Gerenciador();
        int opcao = -1;
        while (opcao != 0) {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(">>>CONTROLE DE ESTOQUE<<<\n\n"
                    + " 1 - Cadastro de produtos \n"
                    + " 2 - Movimentação \n"
                    + " 3 - Relatórios \n"
                    + " 4 - Propriedades \n"
                    + " 0 - Finalizar \n\n"));

            //CADASTRO DE PRODUTOS
            if (opcao == 1) {
                int escolha = -1;
                while (escolha != 0) {
                    escolha = Integer.parseInt(JOptionPane.showInputDialog("\n 1 - Incluir \n"
                            + " 2 - Alterar \n"
                            + " 3 - Consultar \n"
                            + " 4 - Excluir \n"
                            + " 0 - Voltar \n\n"));
                    //INCLUSÃO
                    if (escolha == 1) {
                        Registro produto = gerenciar.leitura("");
                        if (gerenciar.incluir(produto) == true) {
                            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Registro não foi inserido.");
                        }
                        escolha = 0;
                    }
                    //ALTERAÇÃO
                    if (escolha == 2) {
                        int alterarId = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código a ser atualizado: "));
                        Registro produto = gerenciar.leitura("Digite os novos dados");
                        if (gerenciar.alterar(alterarId, produto) == true) {
                            JOptionPane.showMessageDialog(null, "Registro " + alterarId + " atualizado com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Registro " + alterarId + " não foi atualizado.");
                        }
                        escolha = 0;
                    }
                    //CONSULTA
                    if (escolha == 3) {
                        int escolhaConsultar = -1;
                        while (escolhaConsultar != 0) {
                            escolhaConsultar = Integer.parseInt(JOptionPane.showInputDialog("\n1 - Id \n"
                                    + "2 - Nome \n"
                                    + "0 - Voltar"));
                            if (escolhaConsultar == 1) {
                                int chave = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código a ser perquisado: "));
                                Registro produto = gerenciar.consultar(chave, "");
                                if (produto != null) {
                                    JOptionPane.showMessageDialog(null, produto.toString());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Não Achei");
                                }

                            }
                            if (escolhaConsultar == 2) {
                                String descritivo = JOptionPane.showInputDialog(" Digite o nome a ser pesquisado");
                                Registro produto = gerenciar.consultar(0, descritivo);
                                if (produto != null) {
                                    JOptionPane.showMessageDialog(null, produto.toString());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Não Achei");
                                }
                            }
                            if (escolhaConsultar == 0) {

                            }
                            escolha = 0;
                        }
                    }
                    //EXCLUSÃO
                    if (escolha == 4) {
                        int idExcluir = Integer.parseInt(JOptionPane.showInputDialog(" Digite o codigo a ser excluído: "));
                        gerenciar.excluir(idExcluir);
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso");
                        escolha = 0;
                    }

                    //RETORNAR
                    if (escolha == 0) {

                    }
                }
            }
            //MOVIMENTAçÃO
            if (opcao == 2) {
                int escolhaMovimentacao = -1;
                int estoque;
                while (escolhaMovimentacao != 0) {
                    escolhaMovimentacao = Integer.parseInt(JOptionPane.showInputDialog("\n 1 - Entrada\n"
                            + " 2 - Saída \n"
                            + " 0 - Voltar"));
                    if (escolhaMovimentacao == 1) {
                        int chave = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código a ser perquisado: "));
                        Registro produto = gerenciar.consultar(chave, "");
                        if (produto != null) {
                            JOptionPane.showMessageDialog(null, "Quantidade em estoque: " + produto.getQuantidadeEstoque());
                            estoque = produto.getQuantidadeEstoque();
                            int adicionarQuantidade = Integer.parseInt(JOptionPane.showInputDialog(" Digite a quantidade a ser adicionada: "));
                            estoque = estoque + adicionarQuantidade;
                            produto.setQuantidadeEstoque(estoque);
                            JOptionPane.showMessageDialog(null, "Quantidade em estoque: " + produto.getQuantidadeEstoque());
                            gerenciar.alterar(chave, produto);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não Achei");
                        }
                        escolhaMovimentacao = 0;
                    }
                    if (escolhaMovimentacao == 2) {
                        int chave = Integer.parseInt(JOptionPane.showInputDialog(" Digite o código a ser perquisado: "));
                        Registro produto = gerenciar.consultar(chave, "");
                        if (produto != null) {
                            JOptionPane.showMessageDialog(null, "Quantidade em estoque: " + produto.getQuantidadeEstoque());
                            estoque = produto.getQuantidadeEstoque();
                            int diminuirQuantidade = Integer.parseInt(JOptionPane.showInputDialog(" Digite a quantidade a ser retirada: "));
                            estoque = estoque - diminuirQuantidade;
                            produto.setQuantidadeEstoque(estoque);
                            JOptionPane.showMessageDialog(null, "Quantidade em estoque: " + produto.getQuantidadeEstoque());
                            gerenciar.alterar(chave, produto);
                        } else {
                            JOptionPane.showMessageDialog(null, "Não Achei");
                        }
                        escolhaMovimentacao = 0;

                    }
                    if (escolhaMovimentacao == 0) {

                    }

                }
            }
            //RELATÓRIO
            if (opcao == 3) {
                String saida = gerenciar.relatorio();
                JOptionPane.showMessageDialog(null, saida);
            }
            //PROPRIEDADES
            if (opcao == 4) {
                int escolhaZerar = Integer.parseInt(JOptionPane.showInputDialog(" Zerar Arquivo? \n"
                        + " 1 - Sim \n"
                        + " 2 - Não"));
                //ZERAR ARQUIVO
                if (escolhaZerar == 1) {
                    if (gerenciar.zeraArquivo() == true) {
                        JOptionPane.showMessageDialog(null, "Arquivo zerado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro inesperado");
                    }
                }
                if (escolhaZerar == 2) {

                }

                //FINALIZAR
                if (opcao == 0) {
                    JOptionPane.showMessageDialog(null, "Saindo do Sistema");
                }
            }
        }
    }
}
