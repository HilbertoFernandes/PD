<!DOCTYPE html>
<html>
<head>
<title>Inserir Notícia</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
<?php  include 'index.php'; ?>
	<div class="container">
		<div align="center">
			<h1 class="display-4" style="font-size: 24pt; margin-top: 1%;">
				Recuperar uma notícia
				<h1>
		
		</div>
		<div class="row">
			<div class="col-sm"></div>
			<div class="col-sm"
				style="box-shadow: 0px -1px 35px 2px rgba(0, 0, 0, 0.57); margin-top: 1%; padding: 2%;">

				<form id="dados" action="controlador.php" method="GET">

					<div class="form-group">
						<label for="titulo">Id da da notícia desejada</label> <input
							type="text" class="form-control" id="id" name="id"
							required="required">
					</div>
					<input type="hidden" name="op" value="recuperar">
					<div align="center">
						<button type="submit" class="btn btn-primary">Recuperar</button>
						<a href="index.php" class="btn btn-default">Cancelar</a>
					</div>
				</form>
			</div>
			<div class="col-sm"></div>
		</div>
	</div>
</body>
</html>