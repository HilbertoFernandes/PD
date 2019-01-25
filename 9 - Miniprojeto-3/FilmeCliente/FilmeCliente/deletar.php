<<<<<<< HEAD
<?php
$titulo = $_GET['id'];
$ws = new SoapClient('http://localhost:8080/FilmeService/FilmeService?WSDL');
$ws->__soapCall('excluirFilme', array('parameters' => array('id' => $titulo)));
header('Location: http://localhost:8090/FilmeCliente/index.php');
?>
=======
<?php
$titulo = $_GET['id'];
$ws = new SoapClient('http://localhost:8080/FilmeService/FilmeService?WSDL');
$ws->__soapCall('excluirFilme', array('parameters' => array('id' => $titulo)));
header('Location: http://localhost:8090/FilmeCliente/index.php');
?>
>>>>>>> ab6ef55d7acc2386a179fe45360418886a0a66a8
