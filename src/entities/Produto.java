package entities;

public class Produto {
	private String nome;
	private double preco;
	private int quantidadeEmEstoque;

	public Produto(String nome, double preco, int quantidadeEmEstoque) {
		this.nome = nome;
		this.preco = preco;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
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

	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public void removerQuantidadeEstoque(int quantidade) {
		if (this.quantidadeEmEstoque >= quantidade) {
			this.quantidadeEmEstoque -= quantidade;
			// this.quantidadeEmEstoque = quantidadeEmEstoque - quantidade;
		} else {
			throw new IllegalArgumentException("Quantidade a ser removida maior do que a quantidade em estoque!");
		}
	}

	public void adicionarQuantidadeEstoque(int quantidade) {
		this.quantidadeEmEstoque += quantidade;
	}
}
