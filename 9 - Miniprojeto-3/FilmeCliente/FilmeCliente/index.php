<<<<<<< HEAD
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<title>Filmes</title>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm"></div>
			<div class="col-6" align="center">
				<h1 class="display-4">Programação Distribuida - Locadora WebService</h1>
				<table class="table table-sm table-bordered">
					<tr>
						<th>Id</th>
						<th>Título</th>
						<th>Gênero</th>
						<th>Diretor</th>
						<th>Estúdio</th>
						<th>Ano</th>
						<th>Açoes</th>
					</tr>

                <?php
                $ws = new SoapClient('http://localhost:8080/FilmeService/FilmeService?WSDL');
                $result = $ws->__soapCall('listarFilmes', array(
                    'parameters' => array()
                ));
                
                // quando há apenas um filma cadastrado o ws retorna um array formado pelos campos desse filme (6 posições),
                // mas quando há mais de um filme cadastrado o ws retorna um array de arrays,
                // fiz isso aqui para alimentar a lista de filmes de formas diferentes, dependendo do que vier do ws
                
                $r = $ws->__soapCall('qtdFilmes', array(
                    'resposta' => array()
                ));
                $qtd_filmes_cadastrados = $r->resposta;
                
                error_reporting(E_ALL ^ E_NOTICE);
                
                if ($qtd_filmes_cadastrados > 1) {
                    $filmes = json_decode(json_encode($result->resposta), true);
                } else {
                    $filmes = [
                        0 => json_decode(json_encode($result->resposta), true)
                    ];
                }
                // se estiver vazio não mostra nada
                if (end($filmes)) {
                    foreach ($filmes as $filme) {
                        $filmeArray = json_decode(json_encode($filme), true);
                        echo "<tr><td style='width:5%';>" . $filmeArray['id'] . "</td>";
                        echo "<td>" . $filmeArray['titulo'] . "</td>";
                        echo "<td>" . $filmeArray['genero'] . "</td>";
                        echo "<td>" . $filmeArray['diretor'] . "</td>";
                        echo "<td>" . $filmeArray['estudio'] . "</td>";
                        echo "<td>" . $filmeArray['ano'] . "</td>";
                        echo "<td style='width:10%';><spam style='margin-left:10%';><a href='editar.php?id=" . $filmeArray['id'] . "'>Editar</a></spam><spam style='margin-left:10%';><a href='deletar.php?id=" . $filmeArray['id'] . "'>Excluir</a></spam></td></tr>";
                    }
                } else {
                    echo "<h4 style='color:red';>No momento não há filmes cadastrados</h4>";
                }
                ?>
            </table>
				<div align="right">
					<br> <a href="http://localhost:8090/FilmeCliente/criar.php"
						class="btn btn-primary">Cadastrar novo filme.</a> <br>
				</div>
			</div>
			<div class="col-sm"></div>
		</div>
	</div>

</body>
=======
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<title>Filmes</title>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm"></div>
			<div class="col-6" align="center">
				<h1 class="display-4">Programação Distribuida - Locadora WebService</h1>
				<table class="table table-sm table-bordered">
					<tr>
						<th>Id</th>
						<th>Título</th>
						<th>Gênero</th>
						<th>Diretor</th>
						<th>Estúdio</th>
						<th>Ano</th>
						<th>Açoes</th>
					</tr>

                <?php
                $ws = new SoapClient('http://localhost:8080/FilmeService/FilmeService?WSDL');
                $result = $ws->__soapCall('listarFilmes', array(
                    'parameters' => array()
                ));
                
                // quando há apenas um filma cadastrado o ws retorna um array formado pelos campos desse filme (6 posições),
                // mas quando há mais de um filme cadastrado o ws retorna um array de arrays,
                // fiz isso aqui para alimentar a lista de filmes de formas diferentes, dependendo do que vier do ws
                
                $r = $ws->__soapCall('qtdFilmes', array(
                    'resposta' => array()
                ));
                $qtd_filmes_cadastrados = $r->resposta;
                
                error_reporting(E_ALL ^ E_NOTICE);
                
                if ($qtd_filmes_cadastrados > 1) {
                    $filmes = json_decode(json_encode($result->resposta), true);
                } else {
                    $filmes = [
                        0 => json_decode(json_encode($result->resposta), true)
                    ];
                }
                // se estiver vazio não mostra nada
                if (end($filmes)) {
                    foreach ($filmes as $filme) {
                        $filmeArray = json_decode(json_encode($filme), true);
                        echo "<tr><td style='width:5%';>" . $filmeArray['id'] . "</td>";
                        echo "<td>" . $filmeArray['titulo'] . "</td>";
                        echo "<td>" . $filmeArray['genero'] . "</td>";
                        echo "<td>" . $filmeArray['diretor'] . "</td>";
                        echo "<td>" . $filmeArray['estudio'] . "</td>";
                        echo "<td>" . $filmeArray['ano'] . "</td>";
                        echo "<td style='width:10%';><spam style='margin-left:10%';><a href='editar.php?id=" . $filmeArray['id'] . "'>Editar</a></spam><spam style='margin-left:10%';><a href='deletar.php?id=" . $filmeArray['id'] . "'>Excluir</a></spam></td></tr>";
                    }
                } else {
                    echo "<h4 style='color:red';>No momento não há filmes cadastrados</h4>";
                }
                ?>
            </table>
				<div align="right">
					<br> <a href="http://localhost:8090/FilmeCliente/criar.php"
						class="btn btn-primary">Cadastrar novo filme.</a> <br>
				</div>
			</div>
			<div class="col-sm"></div>
		</div>
	</div>

</body>
>>>>>>> ab6ef55d7acc2386a179fe45360418886a0a66a8
</html>