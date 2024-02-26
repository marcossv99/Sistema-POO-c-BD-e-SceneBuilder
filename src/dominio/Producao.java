package dominio;

public class Producao {
	private int idProducao;
	private int quantidade;
	private String nomeEspecie;
	private int idTanque;
	private String registroCriadouro;
	private String dataProducao;
	
	public Producao ( int prod, int quantidade, String p, int t, String dataProducao, String rc ) {
		this.idProducao = prod;
		this.quantidade = quantidade;
		this.nomeEspecie = p;
		this.idTanque = t;
		this.dataProducao = dataProducao;
		this.registroCriadouro = rc;
	}
	
	public Producao ( int id, int quantidade, String p, int t, String dataProducao ) {
		this.idProducao = id;
		this.quantidade = quantidade;
		this.nomeEspecie = p;
		this.idTanque = t;
		this.dataProducao = dataProducao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDataProducao() {
		return dataProducao;
	}

	public void setDataProducao(String dataProducao) {
		this.dataProducao = dataProducao;
	}

	public String getNomeEspecie() {
		return nomeEspecie;
	}
	
	public void setNomeEspecie ( String p ) {
		this.nomeEspecie = p;
	}

	public int getIdTanque() {
		return idTanque;
	}
	
	public void setTanque ( int t ) {
		this.idTanque = t;
	}

	public int getIdProducao() {
		return idProducao;
	}

	public String getRegistroCriadouro() {
		return registroCriadouro;
	}

	public void setRegistroCriadouro(String registroCriadouro) {
		this.registroCriadouro = registroCriadouro;
	}
	
	
}
