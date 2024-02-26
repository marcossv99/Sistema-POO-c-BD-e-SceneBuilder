package dominio;

public class EspecieCriadouro {
	private String especie;
	private String criadouro;
	private boolean possui;
	
	public EspecieCriadouro ( String p, String c, boolean possui ) {
		this.especie = p;
		this.criadouro = c;
		this.possui = possui;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie( String p) {
		this.especie = p;
	}

	public String getCriadouro() {
		return criadouro;
	}

	public void setIdCriadouro(String c) {
		this.criadouro = c;
	}

	public boolean isPossui() {
		return possui;
	}

	public void setPossui(boolean possui) {
		this.possui = possui;
	}

	
}
