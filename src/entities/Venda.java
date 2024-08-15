package entities;

import javax.swing.JOptionPane;

public class Venda {
	private Cliente novoCliente;
	private Funcionario funcionario;
	private CarrinhoCompras carrinho;

	public Venda(Cliente cliente, Funcionario funcionario, CarrinhoCompras carrinho) {
		this.novoCliente = cliente;
		this.carrinho = carrinho;
	}

	public void realizarVenda() {
		JOptionPane.showMessageDialog(null, "Venda realizada para o cliente: " + novoCliente.getNome()
				+ "\nTotal da venda: R$" + carrinho.calcularTotal());
	}

	public Cliente getNovoCliente() {
		return novoCliente;
	}

	public void setNovoCliente(Cliente novoCliente) {
		this.novoCliente = novoCliente;
	}

	public CarrinhoCompras getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoCompras carrinho) {
		this.carrinho = carrinho;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

}
