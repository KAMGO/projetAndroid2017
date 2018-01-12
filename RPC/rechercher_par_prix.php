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
    if (!isset($_GET['mini']) || !is_numeric($_GET['mini']))
    {
        GenererCode(100);
        exit();
    }
    
    if (!isset($_GET['maxi']) || !is_numeric($_GET['maxi']))
    {
        GenererCode(110);
        exit();
    }
    
    $mini = $_GET['mini'] ;
    $maxi = $_GET['maxi'] ;
    
    
    /**************************************************/
    /* Etape 2: tests supplémentaires                 */
    /**************************************************/
    if ($mini>$maxi)
    {
        GenererCode(120);
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
    /* Etape 4: Recherche sur base du prix            */
    /**************************************************/
	$retour='{';
	$retour.='"code": 1,';
	$retour.='"article": ';
	$retour.='[';
    try
    {
		$requeteSQL='SELECT id_article, nom FROM article WHERE prix>=? AND prix<=?';
        $stm= $dbh->prepare($requeteSQL);
		$stm->execute(array($mini, $maxi));
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