package br.com.testewm.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.testewm.Rota;
import br.com.testewm.RotaCalculada;
import br.com.testewm.dao.HSQLDao;
import br.com.testewm.service.BuscaRotaService;



public class TestCargaRotas {
	protected static final Logger LOGGER = Logger.getLogger(TestCargaRotas.class);
	public List<Rota> rotas = new ArrayList<Rota>();
	public BuscaRotaService buscaRotas = null;
	private static final String AMBIENTE = "http://localhost:8080";

	@Before
	public void setUp() throws Exception {
		rotas = adicionaRota("A", "B", 10, rotas);
		rotas = adicionaRota("B", "D", 15, rotas);
		rotas = adicionaRota("A", "C", 20, rotas);
		rotas = adicionaRota("C", "D", 30, rotas);
		rotas = adicionaRota("B", "E", 50, rotas);
		rotas = adicionaRota("D", "E", 30, rotas);
	}

	private List<Rota> adicionaRota(String pontoInicial, String pontoFinal, double distanciaKm, List<Rota> rotas) {
		Rota r = new Rota();
		r.setPontoInicio(pontoInicial);
		r.setPontoFim(pontoFinal);
		r.setDistancia(distanciaKm);
		rotas.add(r);
		return rotas;
	}

	@Test
	public void verificarRotasCarregadas() {
		Assert.assertEquals(rotas.size(), 6);
	}

	@Test
	public void inicializarObjeto() {
		buscaRotas = new BuscaRotaService(rotas);
		Assert.assertTrue(buscaRotas.rotas == rotas);
	}

	@Test
	public void computeAE() {
		buscaRotas = new BuscaRotaService(rotas);
		String pontoInicio = "A";
		String pontoFim = "E";
		BigDecimal autonomia = new BigDecimal(10);
		BigDecimal valorDoLitro = new BigDecimal(2.5);
		RotaCalculada melhorRota = buscaRotas.calcularRota(pontoInicio, pontoFim, autonomia, valorDoLitro);
		Assert.assertTrue(buscaRotas.rotas == rotas);
		Assert.assertNotNull(melhorRota);
		Assert.assertNotNull(melhorRota.getValorTotalCombustivel());
		Assert.assertNotNull(melhorRota.getDistanciaTotal());
		Assert.assertNotNull(melhorRota.getPontoInicio());
		Assert.assertNotNull(melhorRota.getPontoFim());
	}

	@Test
	public void computeAC() {
		buscaRotas = new BuscaRotaService(rotas);
		String pontoInicio = "A";
		String pontoFim = "C";
		BigDecimal autonomia = new BigDecimal(10);
		BigDecimal valorDoLitro = new BigDecimal(2.5);
		RotaCalculada melhorRota = buscaRotas.calcularRota(pontoInicio, pontoFim, autonomia, valorDoLitro);
		Assert.assertTrue(buscaRotas.rotas == rotas);
		Assert.assertNotNull(melhorRota);
		Assert.assertNotNull(melhorRota.getValorTotalCombustivel());
		Assert.assertNotNull(melhorRota.getDistanciaTotal());
		Assert.assertNotNull(melhorRota.getPontoInicio());
		Assert.assertNotNull(melhorRota.getPontoFim());
	}

	@Test
	public void computeAD() {
		buscaRotas = new BuscaRotaService(rotas);
		String pontoInicio = "A";
		String pontoFim = "D";
		BigDecimal autonomia = new BigDecimal(10);
		BigDecimal valorDoLitro = new BigDecimal(2.5);
		RotaCalculada melhorRota = buscaRotas.calcularRota(pontoInicio, pontoFim, autonomia, valorDoLitro);
		Assert.assertTrue(buscaRotas.rotas == rotas);
		Assert.assertNotNull(melhorRota);
		Assert.assertNotNull(melhorRota.getValorTotalCombustivel());
		Assert.assertNotNull(melhorRota.getDistanciaTotal());
		Assert.assertNotNull(melhorRota.getPontoInicio());
		Assert.assertNotNull(melhorRota.getPontoFim());
	}

	@Test
	public void computeAB() {
		buscaRotas = new BuscaRotaService(rotas);
		String pontoInicio = "A";
		String pontoFim = "B";
		BigDecimal autonomia = new BigDecimal(10);
		BigDecimal valorDoLitro = new BigDecimal(2.5);
		RotaCalculada melhorRota = buscaRotas.calcularRota(pontoInicio, pontoFim, autonomia, valorDoLitro);
		Assert.assertTrue(buscaRotas.rotas == rotas);
		Assert.assertNotNull(melhorRota);
		Assert.assertNotNull(melhorRota.getValorTotalCombustivel());
		Assert.assertNotNull(melhorRota.getDistanciaTotal());
		Assert.assertNotNull(melhorRota.getPontoInicio());
		Assert.assertNotNull(melhorRota.getPontoFim());
	}

	@Test
	public void computeAX() {
		buscaRotas = new BuscaRotaService(rotas);
		String pontoInicio = "A";
		String pontoFim = "X";
		BigDecimal autonomia = new BigDecimal(10);
		BigDecimal valorDoLitro = new BigDecimal(2.5);
		RotaCalculada melhorRota = buscaRotas.calcularRota(pontoInicio, pontoFim, autonomia, valorDoLitro);
		Assert.assertTrue(buscaRotas.rotas == rotas);
		/**
		 * Não existe esta rota, retorna null
		 */
		Assert.assertNull(melhorRota);
	}

	@Test
	public void computeAEComY() {
		rotas = adicionaRota("A", "Y", 2, rotas);
		rotas = adicionaRota("Y", "Z", 2, rotas);
		rotas = adicionaRota("Z", "E", 2, rotas);
		Assert.assertEquals(rotas.size(), 9);
		BigDecimal resultadoEsperado = new BigDecimal(6);
		buscaRotas = new BuscaRotaService(rotas);
		String pontoInicio = "A";
		String pontoFim = "E";
		BigDecimal autonomia = new BigDecimal(10);
		BigDecimal valorDoLitro = new BigDecimal(2.5);
		RotaCalculada melhorRota = buscaRotas.calcularRota(pontoInicio, pontoFim, autonomia, valorDoLitro);
		Assert.assertTrue(buscaRotas.rotas == rotas);
		Assert.assertNotNull(melhorRota);
		Assert.assertNotNull(melhorRota.getValorTotalCombustivel());
		Assert.assertNotNull(melhorRota.getDistanciaTotal());
		Assert.assertNotNull(melhorRota.getPontoInicio());
		Assert.assertNotNull(melhorRota.getPontoFim());
		Assert.assertEquals(resultadoEsperado.intValue(), melhorRota.getDistanciaTotal().intValue());
		Assert.assertEquals(melhorRota.getValorTotalCombustivel().doubleValue(), 1.5d, 0);
	}

	@Test
	public void testDataBase() {
		LOGGER.debug("Utilizando o banco em memória");
		Rota ab = new Rota();
		ab.setPontoInicio("A");
		ab.setPontoFim("B");
		ab.setDistancia(10d);
		ab.setDescricao("Rota A para B");
		HSQLDao dao = null;
		
		try{
			dao = new HSQLDao();
			dao.criarTabela();
		} catch (Exception e){
			//DO NOTHING
		}
		
		try {
			dao.adicionaRota(ab);
		} catch (Exception e) {
			Assert.fail();
		}
		try {
			dao.adicionaRota("B para D", "B", "D", 15);
		} catch (Exception e) {
			Assert.fail();
		}
		/**
		 * Utiliza o HSQLDB
		 */
		buscaRotas = new BuscaRotaService();
		RotaCalculada melhorRota = buscaRotas.calcularRota("A", "D", new BigDecimal(25), BigDecimal.ONE);
		Assert.assertNotNull(melhorRota);
		Assert.assertNotNull(melhorRota.getValorTotalCombustivel());
		Assert.assertNotNull(melhorRota.getDistanciaTotal());
		Assert.assertNotNull(melhorRota.getPontoInicio());
		Assert.assertNotNull(melhorRota.getPontoFim());
		BigDecimal ONE = BigDecimal.ONE;
		Assert.assertEquals(ONE.multiply(new BigDecimal(25)).intValue(), melhorRota.getDistanciaTotal().intValue());
		Assert.assertEquals(melhorRota.getValorTotalCombustivel().doubleValue(), ONE.intValue(), 0);
	}
	
	@Test
	public void jsonInsereRotasPadrao() {
		Client c = ClientBuilder.newClient();
        
        Configuration newConfiguration = c.getConfiguration();
        WebTarget webTarget = c.target(AMBIENTE+"/rotasTeste/consultas/buscaRota/inserirPadrao");

        Response response = webTarget
                .queryParam("pontoA", "A")
                .queryParam("pontoB", "E")
                .queryParam("autonomia", new Double(1))
                .queryParam("valorDoLitro", new Double(2))
                .request()
                .post(
                    Entity.entity("pontoA", MediaType.TEXT_PLAIN_TYPE).entity("pontoB", MediaType.TEXT_PLAIN_TYPE)
                        .entity("autonomia", MediaType.TEXT_PLAIN_TYPE).entity("valorDoLitro", MediaType.TEXT_PLAIN_TYPE));
        
        Assert.assertTrue("Response Status", response.getStatus() == 200);

        String myBeanXml = response.readEntity(String.class);
        LOGGER.debug(myBeanXml);
	}
	
	/**
	 * Necessário servidor inicializado e servico disponivel, verificar constante AMBIENTE
	 */
	@Test
	public void jsonBuscarRota() {
        Client c = ClientBuilder.newClient();
        
        WebTarget webTarget = c.target(AMBIENTE+"/rotasTeste/consultas/buscaRota/buscar");

        Response response = webTarget
                .queryParam("pontoA", "A")
                .queryParam("pontoB", "E")
                .queryParam("autonomia", new Double(1))
                .queryParam("valorDoLitro", new Double(2))
                .request()
                .post(
                    Entity.entity("pontoA", MediaType.TEXT_PLAIN_TYPE).entity("pontoB", MediaType.TEXT_PLAIN_TYPE)
                        .entity("autonomia", MediaType.TEXT_PLAIN_TYPE).entity("valorDoLitro", MediaType.TEXT_PLAIN_TYPE));
        
        Assert.assertTrue("Response Status", response.getStatus() == 200);

        String myBeanXml = response.readEntity(String.class);
        LOGGER.debug(myBeanXml);
        
    }
	
	/**
	 * Necessário servidor inicializado e servico disponivel, verificar constante AMBIENTE
	 */
	@Test
	public void jsonInsereRotaZD() {
        Client c = ClientBuilder.newClient();
        
        Configuration newConfiguration = c.getConfiguration();
        WebTarget webTarget = c.target(AMBIENTE+"/rotasTeste/consultas/buscaRota/inserir");

        Response response = webTarget
        		 .queryParam("descricao", "Z=>D")
                .queryParam("pontoA", "Z")
                .queryParam("pontoB", "D")
                .queryParam("distancia", new Double(10))
                .request()
                .post(
                    Entity.entity("pontoA", MediaType.TEXT_PLAIN_TYPE).entity("pontoB", MediaType.TEXT_PLAIN_TYPE)
                        .entity("autonomia", MediaType.TEXT_PLAIN_TYPE).entity("valorDoLitro", MediaType.TEXT_PLAIN_TYPE));
        
        Assert.assertTrue("Response Status", response.getStatus() == 200);

        String myBeanXml = response.readEntity(String.class);
        LOGGER.debug(myBeanXml);
        
    }
	
	/**
	 * Necessário servidor inicializado e servico disponivel, verificar constante AMBIENTE
	 */
	@Test
	public void jsonInsereRotaZX() {
        Client c = ClientBuilder.newClient();
        
        Configuration newConfiguration = c.getConfiguration();
        WebTarget webTarget = c.target(AMBIENTE+"/rotasTeste/consultas/buscaRota/inserir");

        Response response = webTarget
        		 .queryParam("descricao", "Z=>X")
                .queryParam("pontoA", "Z")
                .queryParam("pontoB", "X")
                .queryParam("distancia", new Double(10))
                .request()
                .post(
                    Entity.entity("pontoA", MediaType.TEXT_PLAIN_TYPE).entity("pontoB", MediaType.TEXT_PLAIN_TYPE)
                        .entity("autonomia", MediaType.TEXT_PLAIN_TYPE).entity("valorDoLitro", MediaType.TEXT_PLAIN_TYPE));
        
        Assert.assertTrue("Response Status", response.getStatus() == 200);

        String myBeanXml = response.readEntity(String.class);
        LOGGER.debug(myBeanXml);
        
    }
	
	/**
	 * Necessário servidor inicializado e servico disponivel, verificar constante AMBIENTE
	 */
	@Test
	public void jsonLimparRotas() {
        Client c = ClientBuilder.newClient();
        
        Configuration newConfiguration = c.getConfiguration();
        WebTarget webTarget = c.target(AMBIENTE+"/rotasTeste/consultas/buscaRota/limpar");

        Response response = webTarget
       		 .queryParam("descricao", "Z=>X")
               .queryParam("pontoA", "Z")
               .queryParam("pontoB", "X")
               .queryParam("distancia", new Double(10))
               .request()
               .post(
                   Entity.entity("pontoA", MediaType.TEXT_PLAIN_TYPE).entity("pontoB", MediaType.TEXT_PLAIN_TYPE)
                       .entity("autonomia", MediaType.TEXT_PLAIN_TYPE).entity("valorDoLitro", MediaType.TEXT_PLAIN_TYPE));
       
       Assert.assertTrue("Response Status", response.getStatus() == 200);

       String myBeanXml = response.readEntity(String.class);
       LOGGER.debug(myBeanXml);
        
        
    }
	
}
