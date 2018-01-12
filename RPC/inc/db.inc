<?php   
    /************************************************/
    /* 1. On se connecte à la DB                    */
    /************************************************/
	$pdo_options[PDO::ATTR_ERRMODE] = PDO::ERRMODE_EXCEPTION;
	$dbh = new PDO('mysql:host=localhost;dbname=id2569727_android2017', 'id2569727_martin', 'password1234', $pdo_options);
	
	/************************************************/
    /* 2. On force l'UTF-8                          */
    /************************************************/
	$sql="SET NAMES 'UTF8';";
    $dbh->exec($sql); 
?>