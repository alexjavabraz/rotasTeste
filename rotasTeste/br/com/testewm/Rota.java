package br.com.testewm;

public class Rota {

	private String pontoInicio;
	private String pontoFim;
	private double distancia;
	private String descricao;

	
	public String getPontoInicio() {
		return pontoInicio;
	}

	public void setPontoInicio(String pontoInicio) {
		this.pontoInicio = pontoInicio;
	}

	public String getPontoFim() {
		return pontoFim;
	}

	public void setPontoFim(String pontoFim) {
		this.pontoFim = pontoFim;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
