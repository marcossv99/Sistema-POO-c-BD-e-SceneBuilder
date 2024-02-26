package dominio;

public class Racao {
	private String nome;
	private String marca;
	private double precoKg;
	
	public Racao(String nome, String marca, double preco ) {
		this.nome = nome;
		this.marca = marca;
		this.precoKg = preco;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public double getPrecoKg() {
		return precoKg;
	}
	
	public void setPreco(double preco) {
		this.precoKg = preco;
	}
}
