package br.com.testewm.app;

import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.testewm.util.UtilFunction;

@Path("/login")
public class LoginResource {
	
	private String NOME_APLICACAO_IOS_CLOUD  = "hipprint_ios";
	private String SENHA_APLICACAO_IOS_CLOUD = "hp781421";
	
	@POST
	@Path("getKey")
	@Produces(MediaType.APPLICATION_JSON)
	public String getKey(
				@QueryParam("nome") String nome,  
				@QueryParam("pass") String pass) {
		
		if(UtilFunction.isBlankOrNull(nome) || UtilFunction.isBlankOrNull(pass)){
			return null;
		}
		
		if(!nome.equals(NOME_APLICACAO_IOS_CLOUD)){
			return "usuario ou senha invalida";
		}
		
		if(!pass.equals(SENHA_APLICACAO_IOS_CLOUD)){
			return "usuario ou senha invalida";
		}		
		
		Date data  = UtilFunction.calcularHora(1);
		String key = UtilFunction.dateToString(data, "dd/MM/yyyy HH:mm:ss");
		key        = UtilFunction.encriptBase64(key);
		
		return key;
	}

}
