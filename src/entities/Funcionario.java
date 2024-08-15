package entities;

import java.util.List;

import javax.swing.JOptionPane;

public class Funcionario {

	private String nome;

	public Funcionario(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void consultarVendasRealizadas(List<Venda> vendas) {
		if (vendas.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhuma venda realizada ainda!");
			return;
		}

		StringBuilder relatorioVendas = new StringBuilder("Vendas Realizadas:\n");
		for (Venda venda : vendas) {
			relatorioVendas.append("Cliente: ").append(venda.getNovoCliente().getNome()).append(" - Total: R$")
					.append(venda.getCarrinho().calcularTotal()).append("\n");
		}
		JOptionPane.showMessageDialog(null, relatorioVendas.toString());
	}

	public void visualizarPerfil() {
		JOptionPane.showMessageDialog(null, "Perfil do Funcionário:\nNome: " + nome);
	}
}
