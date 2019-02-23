<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<title>Cadastro de Novo Filme</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<?php
error_reporting(E_ALL ^ E_NOTICE);
$ws = new SoapClient('http://localhost:8080/FilmeService/FilmeService?WSDL');

if (isset($_POST['submit_data'])) {
    $titulo = $_POST['titulo'];
    $diretor = $_POST['diretor'];
    $estudio = $_POST['estudio'];
    $genero = $_POST['genero'];
    $ano = $_POST['ano'];
    
    $ws->__soapCall('salvarFilme', array(
        'parameters' => array(
            'titulo' => $titulo,
            'diretor' => $diretor,
            'estudio' => $estudio,
            'genero' => $genero,
            'ano' => $ano
        )
    ));
    
    header('Location: http://localhost:8090/FilmeCliente/index.php');
}
?>
<body>
	<div class="row">
		<div class="col-6 col-md-4"></div>
		<div class="col-6 col-md-4">
			<table class="table">
				<h1 class="display-4">Cadastro de novo Filme</h1>
				<form action="criar.php" method="post">
					<td>Título:</td>
					<td><input name="titulo" type="text" required="required"
						style="width: 100%";></td>
					</tr>
					<tr>
						<td>Gênero:</td>
						<td><input name="genero" type="text" required="required"
							style="width: 100%";></td>
					</tr>
					<tr>
						<td>Diretor:</td>
						<td><input name="diretor" required="required" style="width: 100%";></td>
					</tr>
					<tr>
						<td>Estúdio:</td>
						<td><input name="estudio" required="required" style="width: 100%";></td>
					</tr>
					<tr>
						<td>Ano:</td>
						<td><input name="ano" required="required" style="width: 100%";></td>
					</tr>
					<tr>
					</tr>
			
			</table>
			<input name="submit_data" type="submit" value="Salvar" type="button"
				class="btn btn-primary" style="margin-left: 45%";> <a
				href="index.php" class="btn btn-primary">Listar Filmes</a>
			</form>
		</div>
		<div class="col-6 col-md-4"></div>
	</div>
</body>
</html>