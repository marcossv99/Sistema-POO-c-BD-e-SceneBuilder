package dominio;

public class Criadouro {
	private String cpfProprietario;
	private String endereco;
	private String senha;
	private String cel;
	private String numeroRegistro;
	
	public Criadouro ( String cpf, String endereco, String senha, String cel, String numero ) {
		this.cpfProprietario = cpf;
		this.endereco = endereco;
		this.senha = senha;
		this.cel = cel;
		this.numeroRegistro = numero;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpfProprietario() {
		return cpfProprietario;
	}

	public void setCpfProprietario(String cpfProprietario) {
		this.cpfProprietario = cpfProprietario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String n) {
		this.numeroRegistro = n;
	}	
	
}
