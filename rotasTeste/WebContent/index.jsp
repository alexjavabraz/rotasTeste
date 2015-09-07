
<!DOCTYPE html>

<html>
<head>

<style type="text/css">
  body {
    color: purple;
    background-color: #d8da3d }
  </style>
  
  
<meta charset="UTF-8">
<title>rotasTeste Indice</title>

</head>
<body>

  
<H1>Olá, obrigado por consultar meu projeto!</H1><br/><br/><br/>

Utilize a url a seguir para efetuar os testes JSON: <br/>
<i>http://localhost:8080/rotasTeste/consultas/buscaRota/buscar <b>via POST</b></i><br/><br/>

Para efetuar um teste no serviço, utilize a URL:<br/>
<i><a href='../rotasTeste/consultas/buscaRota/testar' target='_blank'>http://localhost:8080/rotasTeste/consultas/buscaRota/testar</a></i><br/><br/>


Utilize a url a seguir para incluir as rotas default: <br/>
<i><a href='../rotasTeste/consultas/buscaRota/inserirPadrao' target='_blank'>http://localhost:8080/rotasTeste/consultas/buscaRota/inserirPadrao</a></i><br/><br/>

Para incluir uma nova Rota utilize a URL:<br/>
<i><a href='../rotasTeste/consultas/buscaRota/inserir' target='_blank'>http://localhost:8080/rotasTeste/consultas/buscaRota/inserir via POST</a></i><br/>
Com os seguintes parâmetros:<br/>
			@QueryParam("descricao") String descricao<br/>
			@QueryParam("pontoA") String pontoA<br/>
			@QueryParam("pontoB") String pontoB<br/> 
			@QueryParam("distancia") double distancia<br/><br/><br/>


Para consultar as rotas cadastrar na base de dados utilize a URL:<br/>
<i><a href='../rotasTeste/consultas/buscaRota/listar' target='_blank'>http://localhost:8080/rotasTeste/consultas/buscaRota/listar</a></i><br/><br/>

Para excluir todas as rotas utilize a URL:<br/>
<i><a href='../rotasTeste/consultas/buscaRota/limpar' target='_blank'>http://localhost:8080/rotasTeste/consultas/buscaRota/limpar</a></i><br/><br/>


De antemão lamento pelo excesso de HardCoded, faltaram muitas constantes... <br/>
Um style checker repetiria o 'Magic Text', 'Magic Number', etc... Poderia incluir todas essas constantes como Enums ou uma classe de constantes no pacote útil<br/><br/>
Também não fiquei contente com a nomenclatura de alguns métodos, assim como a url dos serviços.<br/>

Clique <a href='http://blogjavabraz.blogspot.com.br/' target='_blank'>aqui</a> para visitar o meu blog.<br/><br/>

</body>

</html>