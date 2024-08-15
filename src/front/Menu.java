package front;

import entities.SistemaPadaria;

import javax.swing.JOptionPane;


public class Menu {
	public static void exibirMenu() {
		while (true) {
			try {
				int opcao = Integer.parseInt(JOptionPane
						.showInputDialog("Menu de opções:\n" + "1) - Cadastrar Produto\n" + "2) - Visualizar Produtos\n"
								+ "3) - Realizar Venda\n" + "4) - Ver Carrinho\n" + "5) - Remover Produto do Carrinho\n"
								+ "6) - Sair\n" + "Selecione uma das opções disponíveis acima:"));

				switch (opcao) {
				case 1:
					SistemaPadaria.cadastrarProduto();
					break; 
				case 2:
					SistemaPadaria.visualizarProdutos();
					break;
				case 3:
					SistemaPadaria.realizarVenda();
					break;
				case 4:
					SistemaPadaria.verCarrinho();
					break;
				case 5:
					SistemaPadaria.removerProdutoCarrinho();
					break;
				case 6:
					JOptionPane.showMessageDialog(null, "Saindo do sistema...");
					return;
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida!");
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, insira um número válido!");
			}
		}
	}

	public static void main(String[] args) {
		exibirMenu();
	}
}