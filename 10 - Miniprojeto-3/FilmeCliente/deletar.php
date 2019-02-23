<?php
$titulo = $_GET['id'];
$ws = new SoapClient('http://localhost:8080/FilmeService/FilmeService?WSDL');
$ws->__soapCall('excluirFilme', array('parameters' => array('id' => $titulo)));
header('Location: http://localhost:8090/FilmeCliente/index.php');
?>
