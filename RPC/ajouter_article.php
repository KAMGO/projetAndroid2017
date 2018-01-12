<?php
    /**************************************************/
    /* INCLUDES                                       */
    /**************************************************/
    include('inc/fonction_erreur.inc');
    
    
    /**************************************************/
    /* Etape 1: tests des paramètres POST             */
    /**************************************************/
    if (!isset($_POST['nom']) || empty($_POST['nom']))
    {
        GenererCode(100);
        exit();
    }
    
    if (!isset($_POST['prix']) || !is_numeric($_POST['prix']))
    {
        GenererCode(110);
        exit();
    }
    
    if (!isset($_POST['descriptif']) || empty($_POST['descriptif']))
    {
        GenererCode(120);
        exit();
    }
    
    if (!isset($_POST['ville']) || empty($_POST['ville']))
    {
        GenererCode(135);
        exit();
    }
     if (!isset($_POST['etat']) || empty($_POST['etat']))
    {
        GenererCode(136);
        exit();
    }
    if (!isset($_POST['info']) || empty($_POST['info']))
    {
        GenererCode(130);
        exit();
    }
    $nom        = $_POST['nom']        ;
    $prix       = $_POST['prix']       ;
    $descriptif = $_POST['descriptif'] ;
    $ville      = $_POST['ville']      ;
	$etat      = $_POST['etat']        ;
	$info      = $_POST['info']        ;
    
    
    /**************************************************/
    /* Etape 2: tests supplémentaires                 */
    /**************************************************/
    if ($prix<=0)
    {
        GenererCode(111);
        exit();
    }
    
    
    /**************************************************/
    /* Etape 3: Connection à la DB                    */
    /**************************************************/
	try
    {
        include('inc/db.inc');
    }
    catch (Exception $e)
    {
          GenererCode(1000);
          exit();
    }
	
	/**************************************************/
    /* Etape 4: ajout dans la DB                      */
    /**************************************************/
    try
    {
         $requeteSQL='INSERT INTO article (nom, prix, descriptif, ville, etat, info) VALUES (?, ?, ?, ?, ?, ?)';
		 $stm= $dbh->prepare($requeteSQL);
		 $stm->execute(array($nom, $prix, $descriptif, $ville,$etat,$info));
    }
    catch (Exception $e)
    {
          GenererCode(2000);
          exit();
    }
    GenererCode(1);
?>