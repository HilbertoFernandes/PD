<?php
if (isset($_POST["op"])) {
    if ($_POST["op"] == "inserir") {
        $post_data['titulo'] = $_POST["titulo"];
        $post_data['autor'] = $_POST["autor"];
        $post_data['data'] = $_POST["data"];
        $post_data['conteudo'] = $_POST["conteudo"];
        
        foreach ($post_data as $key => $value) {
            $post_items[] = $key . '=' . $value;
        }
        
        $post_string = implode('&', $post_items);
        
        $curl_connection = curl_init('http://localhost:8080/BlogRESTful/webresources/noticias/inserir');
        
        curl_setopt($curl_connection, CURLOPT_CONNECTTIMEOUT, 30);
        curl_setopt($curl_connection, CURLOPT_USERAGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        curl_setopt($curl_connection, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl_connection, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl_connection, CURLOPT_FOLLOWLOCATION, 1);
        curl_setopt($curl_connection, CURLOPT_POSTFIELDS, $post_string);
        
        $result = curl_exec($curl_connection);
        $err = curl_error($curl_connection);
        curl_close($curl_connection);
        
        if ($err) {
            echo "ERRO:" . $err;
        } else {
            include 'cadastrar.php';
            echo '<div align="center" style="font-size:14pt; margin-top:1%;">
                  RESPOSTA HTTP:'.$result.'<br>
                  <form id="dados" action="http://localhost:8080/BlogRESTful/webresources/noticias/inserir?titulo=' . $_POST["titulo"] . '&autor=' . $_POST["autor"] . '&data=' . $_POST["data"] . '&conteudo=' . $_POST["conteudo"] . '" method="post">
                  <button type="submit">json</button>
                  </form>';
            echo '</div>';
        }
    }
}

if (isset($_GET["op"])) {
    if ($_GET["op"] == "listar") {
        
        $curl_connection = curl_init('http://localhost:8080/BlogRESTful/webresources/noticias/listar');
        curl_setopt($curl_connection, CURLOPT_CONNECTTIMEOUT, 30);
        curl_setopt($curl_connection, CURLOPT_USERAGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        curl_setopt($curl_connection, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl_connection, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl_connection, CURLOPT_FOLLOWLOCATION, 1);
        
        $result = curl_exec($curl_connection);
        $err = curl_error($curl_connection);
        curl_close($curl_connection);
        
        if ($err) {
            echo "ERRO:" . $err;
        } else {
            include 'index.php';
            echo '<div align="center" style="margin-top:1%;">
			      <div class="col-sm" style="box-shadow: 0px -1px 35px 2px rgba(0, 0, 0, 0.57); margin-top: 1%; padding: 2%; width:70%">';
            echo '<div align="center"><h1 class="display-4" style="font-size:24pt; margin-top: 1%;">Notícias cadastradas</h1></div>';
            echo $result;
        }
    }
    
    if ($_GET["op"] == "atualizar") {
        
        $post_data['id'] = $_GET["id"];
        $post_data['titulo'] = $_GET["titulo"];
        
        foreach ($post_data as $key => $value) {
            $post_items[] = $key . '=' . $value;
        }
        
        $post_string = implode('&', $post_items);
        
        $curl_connection = curl_init('http://localhost:8080/BlogRESTful/webresources/noticias/atualizar/' . $post_data['id'] . '/' . $post_data['titulo']);
        curl_setopt($curl_connection, CURLOPT_CONNECTTIMEOUT, 30);
        curl_setopt($curl_connection, CURLOPT_USERAGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        curl_setopt($curl_connection, CURLOPT_CUSTOMREQUEST, "PUT");
        curl_setopt($curl_connection, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl_connection, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl_connection, CURLOPT_FOLLOWLOCATION, 1);
        curl_setopt($curl_connection, CURLOPT_PUT, true);
        
        $result = curl_exec($curl_connection);
        $err = curl_error($curl_connection);
        curl_close($curl_connection);
        
        if ($err) {
            echo "ERRO:" . $err;
        } else {
            include 'atualizar.php';
            echo '<div align="center" style="font-size:14pt; margin-top:1%;">';
            echo '<br>';
            echo 'RESPOSTA HTTP:';
            echo $result;
        }
    }
    
    if ($_GET["op"] == "remover") {
        
        $post_data['id'] = $_GET["id"];
        
        foreach ($post_data as $key => $value) {
            $post_items[] = $key . '=' . $value;
        }
        
        $post_string = implode('&', $post_items);
        
        $curl_connection = curl_init('http://localhost:8080/BlogRESTful/webresources/noticias/remover/' . $post_data['id']);
        curl_setopt($curl_connection, CURLOPT_CONNECTTIMEOUT, 30);
        curl_setopt($curl_connection, CURLOPT_USERAGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        curl_setopt($curl_connection, CURLOPT_CUSTOMREQUEST, "DELETE");
        curl_setopt($curl_connection, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl_connection, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl_connection, CURLOPT_FOLLOWLOCATION, 1);
        
        $result = curl_exec($curl_connection);
        $err = curl_error($curl_connection);
        curl_close($curl_connection);
        
        if ($err) {
            echo "ERRO:" . $err;
        } else {
            include 'remover.php';
            echo '<div align="center" style="font-size:14pt; margin-top:1%;">';
            echo '<br>';
            echo 'RESPOSTA HTTP:';
            echo $result;
        }
    }
    
    if ($_GET["op"] == "recuperar") {
        
        $post_data['id'] = $_GET["id"];
        
        foreach ($post_data as $key => $value) {
            $post_items[] = $key . '=' . $value;
        }
        
        $post_string = implode('&', $post_items);
        $curl_connection = curl_init('http://localhost:8080/BlogRESTful/webresources/noticias/recuperar/?id=' . $post_data['id']);
        curl_setopt($curl_connection, CURLOPT_CONNECTTIMEOUT, 30);
        curl_setopt($curl_connection, CURLOPT_USERAGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
        curl_setopt($curl_connection, CURLOPT_CUSTOMREQUEST, "GET");
        curl_setopt($curl_connection, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($curl_connection, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl_connection, CURLOPT_FOLLOWLOCATION, 1);
        
        $result = curl_exec($curl_connection);
        $err = curl_error($curl_connection);
        curl_close($curl_connection);
        
        if ($err) {
            echo "ERRO:" . $err;
        } else {
            include 'recuperar.php';
            echo '<div align="center" style="font-size:14pt; margin-top:1%;">';
            echo '<br>';
            echo 'RESPOSTA HTTP:';
            echo $result;
            $post_data['id'] = null;
        }
    }
}

?>
