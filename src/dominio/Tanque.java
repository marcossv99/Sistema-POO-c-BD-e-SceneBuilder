package dominio;

public class Tanque {
	private int idTanque;
	private int capacidade;
	private String criadouro;
	
	public Tanque ( int id, int capacidade, String criadouro ) {
		this.idTanque = id;
		this.capacidade = capacidade;
		this.criadouro = criadouro;
	}
	
	public Tanque ( int id, int capacidade ) {
		this.idTanque = id;
		this.capacidade = capacidade;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public int getIdTanque() {
		return idTanque;
	}

	public String getCriadouro() {
		return criadouro;
	}

	public void setCriadouro(String criadouro) {
		this.criadouro = criadouro;
	}
	
	
}
