package entities;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class CarrinhoCompras {
	private Map<Produto, Integer> itens;

	public CarrinhoCompras() {
		this.itens = new HashMap<>();
	}
	
	public void pegarItemNoEstoque (Produto produto, int quantidade) {
		produto.removerQuantidadeEstoque(quantidade);
	}

	public void adicionarItem(Produto produto, int quantidade) {
		int quantidadeAtual = itens.getOrDefault(produto, 0);
		if (quantidade <= produto.getQuantidadeEmEstoque()) {
			itens.put(produto, quantidadeAtual + quantidade);
		} else {
			JOptionPane.showMessageDialog(null, "Quantidade selecionada maior do que a quantidade em estoque!");
		}
	}

	public void removerItem(Produto produto, int quantidade) {
		int quantidadeAtual = itens.getOrDefault(produto, 0);
		if (quantidade >= quantidadeAtual) {
			itens.remove(produto);
		} else {
			itens.put(produto, quantidadeAtual - quantidade);
		}
		produto.adicionarQuantidadeEstoque(quantidade);
	}

	public double calcularTotal() {
		double total = 0;
		for (Map.Entry<Produto, Integer> entry : itens.entrySet()) {
			total += entry.getKey().getPreco() * entry.getValue();
		}
		return total;
	}

	public Map<Produto, Integer> getItens() {
		return itens;
	}

	public void combinarCarrinhos(CarrinhoCompras outroCarrinho) {
		for (Map.Entry<Produto, Integer> entry : outroCarrinho.getItens().entrySet()) {
			Produto produto = entry.getKey();
			int quantidade = entry.getValue();
			adicionarItem(produto, quantidade);
			pegarItemNoEstoque(produto, quantidade);
		}
	}

	public boolean contemProduto(Produto produto) {
		return itens.containsKey(produto);
	}
}
