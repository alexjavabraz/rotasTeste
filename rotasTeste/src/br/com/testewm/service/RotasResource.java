package br.com.testewm.service;

import java.util.Date;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.testewm.util.UtilFunction;

@Path("/cliente")
public class RotasResource {

	Logger log = Logger.getLogger("ClienteResource");

	private static final String MENSAGEM_CHAVE_INVALIDA = "Chave Invalida";

	private static final String THUMB = "thumbs";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getClienteById(@PathParam("key") String key,
			@PathParam("id") Integer id) {

		if (!validarKey(key)) {
			return MENSAGEM_CHAVE_INVALIDA;
		}

		return "Cliente Ok";
	}

	@POST
	@Path("{id}/salvar")
	@Produces(MediaType.APPLICATION_JSON)
	public String salvarCliente(@QueryParam("key") String key,
			@QueryParam("nome") String nome, @PathParam("id") String id,
			@QueryParam("password") String password,
			@QueryParam("email") String email) {

		if (!validarKey(key)) {
			return MENSAGEM_CHAVE_INVALIDA;
		}

		log.info("Acessando o método SalvarCliente");

		return "";
	}
	
	public boolean validarKey(String key) {
		boolean retorno = false;

		try {
			String keyDescriptografada = UtilFunction.descriptoBase64(key);
			Date data = UtilFunction.stringToDate(keyDescriptografada,
					"dd/MM/yyyy HH:mm:ss");

			if (data.after(UtilFunction.hojeDataHoraTimesTamp())) {
				retorno = true;
			}

		} catch (Exception e) {
			retorno = false;
		}

		return retorno;
	}

	
}
