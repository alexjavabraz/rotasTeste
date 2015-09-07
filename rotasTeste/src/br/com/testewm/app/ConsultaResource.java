package br.com.testewm.app;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.testewm.RotaCalculada;
import br.com.testewm.service.BuscaRotaService;
import br.com.testewm.util.UtilFunction;

@Path("/buscaRota")
public class ConsultaResource {

	@GET
	@Path("testar")
	public String get() {
		return "TESTE_OK";
	}
	
	@POST
	@Path("buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public String searchRoute(
			@QueryParam("pontoA") String pontoA,
			@QueryParam("pontoB") String pontoB, 
			@QueryParam("autonomia") double autonomia, 
			@QueryParam("valorDoLitro") double valorDoLitro) {

		
		if(UtilFunction.isBlankOrNull(pontoA)){
			return "Parametros invalidos => verificar Ponto Inicial ";
		}
		
		if(UtilFunction.isBlankOrNull(pontoB)){
			return "Parametros invalidos => verificar Ponto Final ";
		}
		
		if(UtilFunction.isBlankZeroOrNull(autonomia)){
			return "Parametros invalidos => verificar Autonomia ";
		}
		
		if(UtilFunction.isBlankZeroOrNull(valorDoLitro)){
			return "Parametros invalidos => verificar Valor Litro ";
		}		
		
		BuscaRotaService service = new BuscaRotaService();
		String rota = service.obterDescricaoMelhorRota(pontoA, pontoB, new BigDecimal(autonomia), new BigDecimal(valorDoLitro));
		
		return rota;
	}

}