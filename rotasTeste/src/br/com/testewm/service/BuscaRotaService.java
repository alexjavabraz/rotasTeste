package br.com.testewm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.testewm.Rota;
import br.com.testewm.RotaCalculada;
import br.com.testewm.dao.HSQLDao;

/**
 * Classe que cont�m as regras de neg�cio.<br/>
 * @author asimas
 *
 */
public class BuscaRotaService {
	protected static final Logger LOGGER = Logger.getLogger(BuscaRotaService.class);
	public List<Rota> rotas = null;
	private HSQLDao dao = new HSQLDao();

	public BuscaRotaService() {
		super();
		dao.criarTabela();
		rotas = dao.listarRotas();
	}

	public BuscaRotaService(List<Rota> rotas) {
		super();
		dao.criarTabela();
		this.rotas = rotas;
	}

	/**
	 * M�todo main para teste
	 * @param args
	 */
	public static void main(String[] args) {
		BuscaRotaService rf = new BuscaRotaService();
		rf.calcularRota("A", "E", new BigDecimal(10), new BigDecimal(2.5));
	}

	/**
	 * Este m�todo efetua a chamada de busca das rotas
	 * Utiliza o algoritmo a-*
	 * 
	 * http://www.redblobgames.com/pathfinding/a-star/introduction.html
	 * @param pontoInicio
	 * @param pontoFim
	 * @param autonomia
	 * @param valorDoLitro
	 * @return RotaCalculada
	 * @see br.com.testewm.RotaCalculada
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
		LOGGER.error(rotaMaisCurta.getDescricaoTotal() + " - Dist�ncia Total = " + distanciaTotal + " KM, Autonomia = " + autonomia + ", Custo do Litro = " + valorDoLitro + ",Custo (R$) = "
				+ rotaMaisCurta.getValorTotalCombustivel());
		
		return rotaMaisCurta;
	}
	
	/**
	 * Este m�todo efetua a chamada de busca das rotas
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
		return (rotaMaisCurta.getDescricaoTotal() + " - Dist�ncia Total = " + distanciaTotal + " KM, Autonomia = " + autonomia + ", Custo do Litro = " + valorDoLitro + ",Custo (R$) = "
				+ rotaMaisCurta.getValorTotalCombustivel());
		
	}

	/**
	 * Este m�todo efetua a busca das rotas que chegam at� o ponto final, a
	 * partir deste destino, � chamado<br/>
	 * recursivamente at� o ponto de origem.
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
	 * Chamamos de linha, a ida de ponto A at� ponto B, utilizando o Este m�todo
	 * busca o caminho do ponto A at� o ponto B O m�todo � chamado
	 * recursivamente at� que se alcance o ponto B, ou se esgotem as rotas
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

	public List<Rota> listarRotas() {
		HSQLDao dao = new HSQLDao();
		return dao.listarRotas();
	}

	/**
	 * 
	 * @param r
	 * @return
	 */
	public Rota inserir(Rota r) {
		try{
			HSQLDao dao = new HSQLDao();
			
			/**
			 * Verifica se j� existe esta rota, caso j� exista, exclui para que a altera��o funcione sempre
			 */
			if(dao.pesquisar(r)){
				dao.excluir(r);
			}
			
			dao.adicionaRota(r);
			return r;
		}catch(Exception e){
			return null;
		}
	}

	public void excluirTodos() {
		HSQLDao dao = new HSQLDao();
		dao.limparBancoDeDados();
		
	}

	public void insereRotasPadrao() {
		HSQLDao dao = new HSQLDao();
		dao.insereRotasPadrao();
		
	}

	private List<Rota> inicializarRotas() {
		dao.criarTabela();
		List<Rota> rotas = dao.listarRotas();
		return rotas;
	}

	public List<Rota> adicionaRota(String pontoInicial, String pontoFinal, double distanciaKm, List<Rota> rotas) {
		Rota r = new Rota();
		r.setPontoInicio(pontoInicial);
		r.setPontoFim(pontoFinal);
		r.setDistancia(distanciaKm);
		rotas.add(r);
		return rotas;
	}
}
