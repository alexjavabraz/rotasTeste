package br.com.testewm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Esta classe contém o processamento da Rota - Linha.<br/>
 * Contém poucos campos calculados, mas também é um POJO.<br/>
 * @author asimas
 *
 */
public class RotaCalculada {
	private BigDecimal distanciaTotal = BigDecimal.ZERO;
	private BigDecimal distancia = BigDecimal.ZERO;
	private BigDecimal valorTotalCombustivel = BigDecimal.ZERO;
	private String pontoInicio;
	private String pontoFim;
	private RotaCalculada rotaVolta = null;
	private boolean rotaMaisCurta = false;
	private String descricaoTotal = null;

	private List<RotaCalculada> rotasAlteranativas = new ArrayList<RotaCalculada>();

	public RotaCalculada(Rota r) {
		setDistancia(new BigDecimal(r.getDistancia()));
		setPontoFim(r.getPontoFim());
		setPontoInicio(r.getPontoInicio());
	}

	public BigDecimal getDistanciaTotal() {
		return distanciaTotal;
	}

	public void setDistanciaTotal(BigDecimal kilometragemTotal) {
		this.distanciaTotal = kilometragemTotal;
	}

	public BigDecimal getValorTotalCombustivel() {
		return valorTotalCombustivel;
	}

	public void setValorTotalCombustivel(BigDecimal valorTotalCombustivel) {
		this.valorTotalCombustivel = valorTotalCombustivel;
	}

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

	public List<RotaCalculada> getRotasAlteranativas() {
		return rotasAlteranativas;
	}

	public void adicionaRota(RotaCalculada rota) {
		rota.setRotaVolta(this);
		rotasAlteranativas.add(rota);
	}

	public void compute() {
		distanciaTotal = calculaDistanciaTotal(this);
		descricaoTotal = obterDescricaoCompleta(this);
		formatarDescricao();
	}

	private void formatarDescricao() {
		StringBuilder builder = new StringBuilder();

		StringTokenizer tokens = new StringTokenizer(descricaoTotal, ">");

		List<String> caminhos = new ArrayList<String>();

		while (tokens.hasMoreElements()) {
			String token = ((String) tokens.nextElement()).trim();

			if (tokens.hasMoreElements())
				caminhos.add(0, token);
		}

		caminhos.remove(0);

		for (String caminho : caminhos) {
			builder = builder.append(caminho);
		}
		descricaoTotal = builder.toString();
	}

	private BigDecimal calculaDistanciaTotal(RotaCalculada rotaDestino) {
		BigDecimal retorno = BigDecimal.ZERO;

		retorno = retorno.add(rotaDestino.getDistancia());

		if (rotaDestino.getRotaVolta() != null) {
			retorno = retorno.add(calculaDistanciaTotal(rotaDestino.getRotaVolta()));
		}

		return retorno;
	}

	private String obterDescricaoCompleta(RotaCalculada rota) {
		StringBuilder rotaDescricao = new StringBuilder();

		rotaDescricao = rotaDescricao.append(" " + rota.getPontoInicio() + "," + rota.getPontoFim() + " > ");

		if (rota.getRotaVolta() != null) {
			rotaDescricao = rotaDescricao.append(obterDescricaoCompleta(rota.getRotaVolta()));
		}

		return rotaDescricao.toString();
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	private void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	public RotaCalculada getRotaVolta() {
		return rotaVolta;
	}

	public void setRotaVolta(RotaCalculada rotaVolta) {
		this.rotaVolta = rotaVolta;
	}

	public boolean isRotaMaisCurta() {
		return rotaMaisCurta;
	}

	public void setRotaMaisCurta(boolean rotaMaisCurta) {
		this.rotaMaisCurta = rotaMaisCurta;
	}

	public String getDescricaoTotal() {
		return descricaoTotal;
	}

}
