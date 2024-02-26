package dominio;

public class Remedio {
	private String nome;
	private String marca;
	private double dosagem;
	
	public Remedio( String nome, String marca, double dosagem) {
		this.nome = nome;
		this.marca = marca;
		this.dosagem = dosagem;
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

	public double getDosagem() {
		return dosagem;
	}

	public void setDosagem(double dosagem) {
		this.dosagem = dosagem;
	}
	
}
