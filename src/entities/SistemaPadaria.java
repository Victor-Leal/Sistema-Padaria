package entities;

import java.util.ArrayList;  
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class SistemaPadaria {
	private static List<Produto> estoque = new ArrayList<>();
	private static CarrinhoCompras carrinho = new CarrinhoCompras();

	public static void cadastrarProduto() {
		String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
		double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto:"));
		int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade em estoque:"));

		Produto produto = new Produto(nome, preco, quantidade);
		int index = encontrarProduto(nome);
		if (index != -1) {
			Produto produtoExistente = estoque.get(index);
			produtoExistente.setPreco(produtoExistente.getPreco() + preco);
			produtoExistente.setQuantidadeEmEstoque(produtoExistente.getQuantidadeEmEstoque() + quantidade);
			JOptionPane.showMessageDialog(null, "Produto já cadastrado. Preço e quantidade atualizados!");
		} else {
			estoque.add(produto);
			JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
		}
	}

	public static void realizarVenda() {
		if (estoque.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não há produtos disponíveis no estoque!");
			return;
		}

		Cliente cliente = new Cliente(JOptionPane.showInputDialog("Digite o nome do cliente:"));
		Funcionario funcionario = new Funcionario(JOptionPane.showInputDialog("Digite o nome do vendedor:"));
		CarrinhoCompras carrinhoLocal = new CarrinhoCompras();

		StringBuilder listaDeProdutos = new StringBuilder("Produtos Disponíveis:\n");
		for (int i = 0; i < estoque.size(); i++) {
			Produto produto = estoque.get(i);
			listaDeProdutos.append(i + 1).append(". ").append(produto.getNome()).append(" - Preço: R$")
					.append(produto.getPreco()).append(" - Estoque: ").append(produto.getQuantidadeEmEstoque())
					.append("\n");
		}

		boolean continuarCompra = true;
		while (continuarCompra) {
			int escolha = Integer.parseInt(JOptionPane.showInputDialog(listaDeProdutos.toString()
					+ "\nDigite o número do produto para adicionar ao carrinho (ou digite 0 para finalizar):"));
			if (escolha == 0) {
				int opcao = JOptionPane.showConfirmDialog(null, "Deseja finalizar a compra?", "Finalizar Compra",
						JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.YES_OPTION) {
					continuarCompra = false;
				}
			} else if (escolha < 0 || escolha > estoque.size()) {
				JOptionPane.showMessageDialog(null, "Opção inválida!");
			} else {
				Produto produtoSelecionado = estoque.get(escolha - 1);
				int quantidadeDisponivel = produtoSelecionado.getQuantidadeEmEstoque();
				int quantidadeCompra = Integer
						.parseInt(JOptionPane.showInputDialog("Digite a quantidade do produto a ser adicionada:"));
				if (quantidadeCompra <= quantidadeDisponivel) {
					if (!carrinhoLocal.contemProduto(produtoSelecionado)) {
						carrinhoLocal.adicionarItem(produtoSelecionado, quantidadeCompra);
						JOptionPane.showMessageDialog(null, quantidadeCompra
								+ " unidades do produto adicionadas ao carrinho: " + produtoSelecionado.getNome());
					} else {
						JOptionPane.showMessageDialog(null,
								"Este produto já está no carrinho. Selecione outro produto ou altere a quantidade.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Quantidade selecionada maior do que a quantidade em estoque!");
				}
			}
		}

		double total = carrinhoLocal.calcularTotal();
		JOptionPane.showMessageDialog(null, "Total da venda: R$" + total);

		if (!carrinhoLocal.getItens().isEmpty()) {
			carrinho.combinarCarrinhos(carrinhoLocal);
			Venda venda = new Venda(cliente, funcionario, carrinhoLocal);
			venda.realizarVenda();
		}

		int opcao = JOptionPane.showConfirmDialog(null, "Deseja fazer uma nova venda?", "Nova Venda",
				JOptionPane.YES_NO_OPTION);
		if (opcao == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Encerrando o sistema...");
			System.exit(0);
		}
	}

	public static void verCarrinho() {
		if (carrinho.getItens().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Carrinho vazio!");
			return;
		}

		StringBuilder meuCarrinho = new StringBuilder("Carrinho de Compras:\n");
		double total = 0;
		for (Map.Entry<Produto, Integer> entry : carrinho.getItens().entrySet()) {
			Produto item = entry.getKey();
			int quantidade = entry.getValue();
			meuCarrinho.append(item.getNome()).append(" - R$").append(item.getPreco()).append(" - Quantidade: ")
					.append(quantidade).append("\n");
			total += item.getPreco() * quantidade;
		}
		meuCarrinho.append("\nTotal: R$").append(total);
		JOptionPane.showMessageDialog(null, meuCarrinho.toString());
	}

	public static void removerProdutoCarrinho() {
		if (carrinho.getItens().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Carrinho vazio!");
			return;
		}

		StringBuilder meuCarrinho = new StringBuilder("Carrinho de Compras:\n");
		int index = 1;
		for (Map.Entry<Produto, Integer> entry : carrinho.getItens().entrySet()) {
			Produto item = entry.getKey();
			meuCarrinho.append(index).append(". ").append(item.getNome()).append(" - R$").append(item.getPreco())
					.append(" - Quantidade: ").append(entry.getValue()).append("\n");
			index++;
		}

		int escolha = Integer.parseInt(JOptionPane
				.showInputDialog(meuCarrinho.toString() + "\nDigite o número do produto a ser removido do carrinho:"));
		if (escolha < 1 || escolha > carrinho.getItens().size()) {
			JOptionPane.showMessageDialog(null, "Opção inválida!");
			return;
		}

		Produto produtoRemovido = null;
		int i = 1;
		for (Map.Entry<Produto, Integer> entry : carrinho.getItens().entrySet()) {
			if (i == escolha) {
				produtoRemovido = entry.getKey();
				break;
			}
			i++;
		}

		int quantidadeRemover = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade a ser removida:"));
		int quantidadeAtual = carrinho.getItens().get(produtoRemovido);
		if (quantidadeRemover <= quantidadeAtual) {
			carrinho.removerItem(produtoRemovido, quantidadeRemover);
			JOptionPane.showMessageDialog(null, "Produto removido do carrinho: " + produtoRemovido.getNome());
		} else {
			JOptionPane.showMessageDialog(null, "Quantidade a ser removida maior do que a quantidade no carrinho!");
		}
	}

	private static int encontrarProduto(String nome) {
		for (int i = 0; i < estoque.size(); i++) {
			if (estoque.get(i).getNome().equalsIgnoreCase(nome)) {
				return i;
			}
		}
		return -1;
	}

	public static void visualizarProdutos() {
		if (estoque.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não há produtos cadastrados!");
			return;
		}

		StringBuilder listaProdutos = new StringBuilder("Produtos Disponíveis:\n");
		for (Produto produto : estoque) {
			listaProdutos.append("- Nome: ").append(produto.getNome()).append(" - Preço: R$").append(produto.getPreco())
					.append(" - Quantidade em Estoque: ").append(produto.getQuantidadeEmEstoque()).append("\n");
		}
		JOptionPane.showMessageDialog(null, listaProdutos.toString());
	}

}
