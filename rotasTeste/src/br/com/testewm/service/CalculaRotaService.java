package br.com.testewm.service;

import java.util.List;

import br.com.testewm.Rota;
import br.com.testewm.dao.HSQLDao;

public class CalculaRotaService {
	public List<Rota> rotas = null;
	private HSQLDao dao = new HSQLDao();

	public CalculaRotaService(List<Rota> rotas) {
		this.rotas = rotas;
	}

	public CalculaRotaService() {
		super();
		rotas = inicializarRotas();
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
