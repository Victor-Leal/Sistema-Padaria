package entities;

import java.util.List;
import javax.swing.JOptionPane;

public class Administrador extends Funcionario {

	public Administrador(String nome) {
		super(nome);
	}

	public void adicionarProduto(Produto produto, List<Produto> estoque) {
		estoque.add(produto);
		JOptionPane.showMessageDialog(null, "Produto adicionado ao estoque: " + produto.getNome());
	}

	public void removerProduto(Produto produto, List<Produto> estoque) {
		if (estoque.remove(produto)) {
			JOptionPane.showMessageDialog(null, "Produto removido do estoque: " + produto.getNome());
		} else {
			JOptionPane.showMessageDialog(null, "Produto não encontrado no estoque!");
		}
	}

	public void visualizarRelatorioVendas(List<Venda> vendas) {
		if (vendas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhuma venda realizada ainda!");
			return;
		}

		StringBuilder relatorioVendas = new StringBuilder("Relatório de Vendas:\n");
		for (Venda venda : vendas) {
			relatorioVendas.append("Cliente: ").append(venda.getNovoCliente().getNome()).append(" - Vendedor: ")
					.append(venda.getFuncionario().getNome()).append(" - Total: R$")
					.append(venda.getCarrinho().calcularTotal()).append("\n");
		}
		JOptionPane.showMessageDialog(null, relatorioVendas.toString());
	}
}
