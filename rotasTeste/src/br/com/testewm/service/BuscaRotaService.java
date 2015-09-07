package br.com.testewm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.testewm.Rota;
import br.com.testewm.RotaCalculada;

public class BuscaRotaService extends CalculaRotaService {
	protected static final Logger LOGGER = Logger.getLogger(BuscaRotaService.class);

	public BuscaRotaService() {
		super();
	}

	public BuscaRotaService(List<Rota> rotas) {
		super(rotas);
	}

	public static void main(String[] args) {
		BuscaRotaService rf = new BuscaRotaService();
		rf.calcularRota("A", "E", new BigDecimal(10), new BigDecimal(2.5));
	}

	/**
	 * Este método efetua a chamada de busca das rotas
	 * 
	 * @param pontoInicio
	 * @param pontoFim
	 * @param autonomia
	 * @param valorDoLitro
	 * @return
	 */
	public RotaCalculada calcularRota(String pontoInicio, String pontoFim, BigDecimal autonomia, BigDecimal valorDoLitro) {

		RotaCalculada rotaCalculada = null;
		Rota r = new Rota();
		r.setPontoFim(pontoFim.toUpperCase());
		r.setPontoInicio(pontoInicio.toUpperCase());

		rotaCalculada = new RotaCalculada(r);
		rotaCalculada = buscarLinhas(rotaCalculada, r.getPontoInicio(), r.getPontoFim());
		List<RotaCalculada> retorno = calculaRota(rotaCalculada, r.getPontoFim());

		/**
		 * Nenhuma Rota foi encontrada
		 */
		if (retorno == null || retorno.size() == 0) {
			return null;
		}

		BigDecimal menorDistancia = null;
		BigDecimal distanciaApurada = BigDecimal.ZERO;
		RotaCalculada rotaMaisCurta = null;
		for (RotaCalculada rotaDestino : retorno) {
			distanciaApurada = rotaDestino.getDistanciaTotal();
			if (menorDistancia == null || distanciaApurada.compareTo(menorDistancia) < 0) {
				rotaMaisCurta = rotaDestino;
				menorDistancia = distanciaApurada;
				rotaMaisCurta.setDistanciaTotal(distanciaApurada);
			}
		}
		BigDecimal distanciaTotal = rotaMaisCurta.getDistanciaTotal();
		rotaMaisCurta.setValorTotalCombustivel(distanciaTotal.divide(autonomia).multiply(valorDoLitro));
		LOGGER.error(rotaMaisCurta.getDescricaoTotal() + " - Distância Total = " + distanciaTotal + " KM, Autonomia = " + autonomia + ", Custo do Litro = " + valorDoLitro + ",Custo (R$) = "
				+ rotaMaisCurta.getValorTotalCombustivel());
		
		return rotaMaisCurta;
	}
	
	/**
	 * Este método efetua a chamada de busca das rotas
	 * 
	 * @param pontoInicio
	 * @param pontoFim
	 * @param autonomia
	 * @param valorDoLitro
	 * @return
	 */
	public String obterDescricaoMelhorRota(String pontoInicio, String pontoFim, BigDecimal autonomia, BigDecimal valorDoLitro) {

		RotaCalculada rotaCalculada = null;
		Rota r = new Rota();
		r.setPontoFim(pontoFim.toUpperCase());
		r.setPontoInicio(pontoInicio.toUpperCase());

		rotaCalculada = new RotaCalculada(r);
		rotaCalculada = buscarLinhas(rotaCalculada, r.getPontoInicio(), r.getPontoFim());
		List<RotaCalculada> retorno = calculaRota(rotaCalculada, r.getPontoFim());

		/**
		 * Nenhuma Rota foi encontrada
		 */
		if (retorno == null || retorno.size() == 0) {
			return null;
		}

		BigDecimal menorDistancia = null;
		BigDecimal distanciaApurada = BigDecimal.ZERO;
		RotaCalculada rotaMaisCurta = null;
		for (RotaCalculada rotaDestino : retorno) {
			distanciaApurada = rotaDestino.getDistanciaTotal();
			if (menorDistancia == null || distanciaApurada.compareTo(menorDistancia) < 0) {
				rotaMaisCurta = rotaDestino;
				menorDistancia = distanciaApurada;
				rotaMaisCurta.setDistanciaTotal(distanciaApurada);
			}
		}
		BigDecimal distanciaTotal = rotaMaisCurta.getDistanciaTotal();
		rotaMaisCurta.setValorTotalCombustivel(distanciaTotal.divide(autonomia).multiply(valorDoLitro));
		return (rotaMaisCurta.getDescricaoTotal() + " - Distância Total = " + distanciaTotal + " KM, Autonomia = " + autonomia + ", Custo do Litro = " + valorDoLitro + ",Custo (R$) = "
				+ rotaMaisCurta.getValorTotalCombustivel());
		
	}

	/**
	 * Este método efetua a busca das rotas que chegam até o ponto final, a
	 * partir deste destino, é chamado<br/>
	 * recursivamente até o ponto de origem.
	 * 
	 * @param rota
	 * @param pontoFinal
	 * @return
	 */
	private List<RotaCalculada> calculaRota(RotaCalculada rota, String pontoFinal) {
		List<RotaCalculada> rotasQueChegamAoDestino = new ArrayList<RotaCalculada>();
		List<RotaCalculada> rotasAlternativas = rota.getRotasAlteranativas();
		for (RotaCalculada rotaAlternativa : rotasAlternativas) {
			if (rotaAlternativa.getPontoFim().equals(pontoFinal)) { // ESTA ROTA
																	// CHEGA AO
																	// DESTINO
				rotaAlternativa.compute();
				rotasQueChegamAoDestino.add(rotaAlternativa);
			} else if (rotaAlternativa.getRotasAlteranativas().size() > 0) {
				List<RotaCalculada> outrasRotas = calculaRota(rotaAlternativa, pontoFinal);

				if (outrasRotas != null && outrasRotas.size() > 0) {
					rotasQueChegamAoDestino.addAll(outrasRotas);
				}
			}
		}
		return rotasQueChegamAoDestino;
	}

	/**
	 * Chamamos de linha, a ida de ponto A até ponto B, utilizando o Este método
	 * busca o caminho do ponto A até o ponto B O método é chamado
	 * recursivamente até que se alcance o ponto B, ou se esgotem as rotas
	 * 
	 * @param rotaCalculada
	 *            => A rota que procuramos
	 * @param pontoInicio
	 * @param pontoFim
	 * @return RotaCalculada2
	 * @see br.com.wm.RotaCalculada2
	 */
	private RotaCalculada buscarLinhas(RotaCalculada rotaCalculada, String pontoInicio, String pontoFim) {
		for (Rota r : rotas) {
			RotaCalculada rota = new RotaCalculada(r);
			if (r.getPontoInicio().equals(pontoInicio) && r.getPontoFim().equals(pontoFim)) {
				rotaCalculada.adicionaRota(rota);
				return rotaCalculada;
			} else if (r.getPontoInicio().equals(pontoInicio)) {
				rotaCalculada.adicionaRota(buscarLinhas(rota, r.getPontoFim(), pontoFim));
			}
		}
		return rotaCalculada;
	}
}
