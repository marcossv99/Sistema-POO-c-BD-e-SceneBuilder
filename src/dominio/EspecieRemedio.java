package dominio;

public class EspecieRemedio {
	private String especie;
	private String nomeRemedio;
	private String marcaRemedio;
	
	public EspecieRemedio ( String e, String nr, String mr ) {
		this.especie = e;
		this.nomeRemedio = nr;
		this.nomeRemedio = mr;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getNomeRemedio() {
		return nomeRemedio;
	}

	public void setNomeRemedio(String nomeRemedio) {
		this.nomeRemedio = nomeRemedio;
	}

	public String getMarcaRemedio() {
		return marcaRemedio;
	}

	public void setMarcaRemedio(String marcaRemedio) {
		this.marcaRemedio = marcaRemedio;
	}
	
	
}
