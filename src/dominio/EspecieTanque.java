package dominio;

public class EspecieTanque {
	private String nomeEspecie;
	private int idTanque;
	private String registroCriadouro;
	
	public EspecieTanque ( String e, int t, String r) {
		this.nomeEspecie = e;
		this.idTanque = t;
		this.registroCriadouro = r;
	}
	
	public EspecieTanque ( String e, int t ) {
		this.nomeEspecie = e;
		this.idTanque = t;
	}

	public String getNomeEspecie() {
		return nomeEspecie;
	}

	public void setNomeEspecie(String e) {
		this.nomeEspecie = e;
	}

	public int getIdTanque() {
		return idTanque;
	}

	public void setIdTanque(int t) {
		this.idTanque = t;
	}

	public String getRegistroCriadouro() {
		return registroCriadouro;
	}

	public void setRegistroCriadouro(String registroCriadouro) {
		this.registroCriadouro = registroCriadouro;
	}
	
	
}
