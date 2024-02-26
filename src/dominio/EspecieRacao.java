package dominio;

public class EspecieRacao {
	private String especie;
	private String nomeRacao;
	private String marcaRacao;

	
	public EspecieRacao ( String p, String nr, String mr ) {
		this.especie = p;
		this.nomeRacao = nr;
		this.marcaRacao = mr;

	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getNomeRacao() {
		return nomeRacao;
	}

	public void setNomeRacao(String nomeRacao) {
		this.nomeRacao = nomeRacao;
	}

	public String getMarcaRacao() {
		return marcaRacao;
	}

	public void setMarcaRacao(String marcaRacao) {
		this.marcaRacao = marcaRacao;
	}

	
	
	
}
