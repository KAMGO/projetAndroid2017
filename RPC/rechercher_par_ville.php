<?php
    /**************************************************/
    /* INCLUDES                                       */
    /**************************************************/
    include('inc/fonction_erreur.inc');
    
	
	/**************************************************/
    /* FONCTIONS                                      */
    /**************************************************/
	function GenererArticle($id_article, $nom)
	{
		$art='{';
		$art.='"id": '.$id_article.',';
		$art.='"nom": '.'"'.$nom.'"';
		$art.='}';
		
		return $art;
	}
	
    
    /**************************************************/
    /* Etape 1: tests des paramètres GET              */
    /**************************************************/    
    if (!isset($_GET['ville']) || empty($_GET['ville']))
    {
        GenererCode(100);
        exit();
    }
    
    $ville = $_GET['ville'] ;
       
    
	/**************************************************/
    /* Etape 2: Connection à la DB                    */
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
    /* Etape 3: Recherche sur base de la ville        */
    /**************************************************/
	$retour='{';
	$retour.='"code": 1,';
	$retour.='"article": ';
	$retour.='[';
    try
    {
		$requeteSQL='SELECT id_article, nom FROM article WHERE ville=?';
        $stm= $dbh->prepare($requeteSQL);
		$stm->execute(array($ville));
		$cpt=0;
        while ($row= $stm->fetch()) 
		{
			if ($cpt>=1)
			{
				$retour.=',';
			}
			$retour.=GenererArticle($row["id_article"], $row["nom"]);
			$cpt++;
		}
		if ($cpt==0)
		{
			GenererCode(1100);
			exit();
		}
    }
    catch (Exception $e)
    {
          GenererCode(2000);
          exit();
    }
	$retour.=']';
	$retour.='}';
    
	echo $retour;
?>