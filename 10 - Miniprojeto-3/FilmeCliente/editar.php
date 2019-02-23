<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<?php
error_reporting(E_ALL ^ E_NOTICE);

$id = $_GET['id'];
$ws = new SoapClient('http://localhost:8080/FilmeService/FilmeService?WSDL');
$filme = $ws->__soapCall('buscaFilmeId', array(
    'parameters' => array(
        'id' => $id_f
    )
));

foreach ($filme as $x) {
    $dados['id_filme'] = $x->id;
    $dados['titulo'] = $x->titulo;
    $dados['genero'] = $x->genero;
    $dados['estudio'] = $x->estudio;
    $dados['ano'] = $x->ano;
    $dados['diretor'] = $x->diretor;
}

if (isset($_POST['submit_data'])) {
    $id_filme = $_POST['id_filme'];
    $titulo = $_POST['titulo'];
    $diretor = $_POST['diretor'];
    $estudio = $_POST['estudio'];
    $genero = $_POST['genero'];
    $ano = $_POST['ano'];
    
    $ws->__soapCall('atualizarFilme', array(
        'parameters' => array(
            'id' => $id_filme,
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
<title>Editar Filme</title>
</head>
<body>
	<div class="row">
		<div class="col-6 col-md-4"></div>
		<div class="col-6 col-md-4">
			<table class="table table-sm">
				<h1 class="display-4">Editar Filme</h1>
				<form action="editar.php" method="post">
					<tr>
						<td>ID</td>
						<td><input value="<?php echo $dados['id_filme'];?>"
							name="id_filme" type="text" style="width: 100%" ; disabled></td>
					</tr>
				<tr>
					<td>Título:</td>
					<td><input value="<?php echo $dados['titulo'];?>" name="titulo"
						type="text" required="required" style="width: 100%";></td>
				</tr>
				<tr>
					<td>Gênero:</td>
					<td><input value="<?php echo $dados['genero'];?>" name="genero"
						type="text" required="required" style="width: 100%";></td>
				</tr>
				<tr>
					<td>Diretor:</td>
					<td><input value="<?php echo $dados['diretor'];?>" name="diretor"
						required="required" style="width: 100%";></td>
				</tr>
				<tr>
					<td>Estúdio:</td>
					<td><input value="<?php echo $dados['estudio'];?>" name="estudio"
						required="required" style="width: 100%";></td>
				</tr>
				<tr>
					<td>Ano:</td>
					<td><input value="<?php echo $dados['ano'];?>" name="ano"
						required="required" style="width: 100%";></td>
				</tr>
				<tr>
				</tr>

			</table>
			<a href="index.php" class="btn btn-primary" style="margin-left: 45%";>Cancelar</a>
			<input name="submit_data" type="submit" value="Atualizar Filme"
				class="btn btn-primary">
			</form>
		</div>
		<div class="col-6 col-md-4"></div>
	</div>
</body>
</html>