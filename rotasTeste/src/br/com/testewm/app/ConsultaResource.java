package br.com.testewm.app;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.testewm.Rota;
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
		
		List<Rota> rotas = service.listarRotas();
		
		if(rotas == null || rotas.size() == 0){
			return "É necessário cadastrar as rotas!<br/>";
		}
		
		String rota = service.obterDescricaoMelhorRota(pontoA, pontoB, new BigDecimal(autonomia), new BigDecimal(valorDoLitro));
		
		return rota;
	}
	
	
	@POST
	@Path("inserir")
	@Produces(MediaType.APPLICATION_JSON)
	public String inserirRota(
			@QueryParam("descricao") String descricao,
			@QueryParam("pontoA") String pontoA,
			@QueryParam("pontoB") String pontoB, 
			@QueryParam("distancia") double distancia) {

		if(UtilFunction.isBlankZeroOrNull(descricao)){
			return "Parametros invalidos => verificar Descrição ";
		}	
		
		if(UtilFunction.isBlankOrNull(pontoA)){
			return "Parametros invalidos => verificar Ponto Inicial ";
		}
		
		if(UtilFunction.isBlankOrNull(pontoB)){
			return "Parametros invalidos => verificar Ponto Final ";
		}
		
		if(UtilFunction.isBlankZeroOrNull(distancia)){
			return "Parametros invalidos => verificar Distancia ";
		}
		
		BuscaRotaService service = new BuscaRotaService();
		
		Rota r = new Rota();
		r.setDescricao(descricao);
		r.setPontoInicio(pontoA);
		r.setPontoFim(pontoB);
		r.setDistancia(distancia);
		
		r = service.inserir(r);
		
		if(r == null){
			return "Ocorreu um erro";
		}
		
		return "Inserido com sucesso!";
	}
	
	@GET
	@Path("limpar")
	@Produces(MediaType.APPLICATION_JSON)
	public String limpar() {
 
		BuscaRotaService service = new BuscaRotaService();
		try{
			service.excluirTodos();
		}catch(Exception e){
			return "Ocorreu um erro na exclusão";
		}
		
		return "Excluidos com sucesso!";
	}
	
	@GET
	@Path("listar")
	public String listar() {
		BuscaRotaService service = new BuscaRotaService();
		List<Rota> rotasPersistidas = service.listarRotas();
		StringBuilder sb = new StringBuilder();
		
		sb = sb.append("<table widh='100%' border='1' cellspacing='0'>" );
		sb = sb.append("<tr>" );
		sb = sb.append("<td width='50%'>Descri&ccedil;&atilde;o</td>" );
		sb = sb.append("<td width='20%'>Ponto Inicial</td>" );
		sb = sb.append("<td width='20%'>Ponto Final</td>" );
		sb = sb.append("<td width='10%'>Dist&acirc;ncia</td>" );
		sb = sb.append("</tr>" );
		
		if(rotasPersistidas != null && rotasPersistidas.size() > 0){
			for(Rota r : rotasPersistidas){
				sb = sb.append("<tr><td>"+r.getDescricao() + "</td><td>" + r.getPontoInicio() + " </td><td>" + r.getPontoFim() + "</td><td>"+ r.getDistancia()+"</td></tr>");
			}
		}
		
		sb = sb.append("</table>" );
		
		return sb.toString();
	}
	
	@GET
	@Path("inserirPadrao")
	public String inserirPadrao() {
		BuscaRotaService service = new BuscaRotaService();
		try{
			service.excluirTodos();
			service.insereRotasPadrao();
		}catch(Exception e){
			return "Ocorre um erro ao incluir as rotas padrão";
		}
		
		return "Rotas incluidas com sucesso";
	}

}