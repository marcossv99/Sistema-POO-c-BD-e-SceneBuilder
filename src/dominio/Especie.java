package dominio;

public class Especie {
	private String nome;
	private int comprimentoIdeal;
	private float pesoIdeal;
	private float phMin, phMax;
	private int tempMin, tempMax;
	private int diasAdulto;
	
	public Especie ( String nome, int comprimento, float peso, float phMin, float phMax, int tempMin, int tempMax, int dias ) {
		this.nome = nome;
		this.comprimentoIdeal = comprimento;
		this.pesoIdeal = peso;
		this.phMin = phMin;
		this.phMax = phMax;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.diasAdulto = dias;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getComprimentoIdeal() {
		return comprimentoIdeal;
	}
	
	public void setComprimentoIdeal(int comprimentoIdeal) {
		this.comprimentoIdeal = comprimentoIdeal;
	}
	
	public float getPesoIdeal() {
		return pesoIdeal;
	}
	
	public void setPesoIdeal(float pesoIdeal) {
		this.pesoIdeal = pesoIdeal;
	}
	
	public float getPhMin() {
		return phMin;
	}
	
	public void setPhMin(float phMin) {
		this.phMin = phMin;
	}
	
	public float getPhMax() {
		return phMax;
	}
	
	public void setPhMax(float phMax) {
		this.phMax = phMax;
	}
	
	public int getTempMin() {
		return tempMin;
	}
	
	public void setTempMin(int tempMin) {
		this.tempMin = tempMin;
	}
	
	public int getTempMax() {
		return tempMax;
	}
	
	public void setTempMax(int tempMax) {
		this.tempMax = tempMax;
	}
	
	public int getDiasAdulto() {
		return diasAdulto;
	}
	
	public void setDiasAdulto(int diasAteAdulto) {
		this.diasAdulto = diasAteAdulto;
	}

}
