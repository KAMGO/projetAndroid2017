<?php
    /**************************************************/
    /* INCLUDES                                       */
    /**************************************************/
    include('inc/fonction_erreur.inc');
    
	
	/**************************************************/
    /* FONCTIONS                                      */
    /**************************************************/
	function GenererArticle($nom, $prix, $descriptif, $ville, $etat, $info)
	{
		$art='{';
		$art.='"nom": '.'"'.$nom.'"'.',';
		$art.='"prix": '.$prix.',';
		$art.='"descriptif": '.'"'.$descriptif.'"'.',';
		$art.='"ville": '.'"'.$ville.'"'.',';
		$art.='"etat": '.'"'.$etat.'"'.',';
		$art.='"info": '.'"'.$info.'"';
		$art.='}';
		
		return $art;
	}
    
    
    /**************************************************/
    /* Etape 1: tests des paramètres GET              */
    /**************************************************/   
    if (!isset($_GET['id_article']) || !is_numeric($_GET['id_article']))
    {
        GenererCode(100);
        exit();
    }
    
    $id_article = $_GET['id_article'] ;
    
    
    /**************************************************/
    /* Etape 2: tests supplémentaires                 */
    /**************************************************/
    if ($id_article<=0)
    {
        GenererCode(101);
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
    /* Etape 4: recherche de l'article                  */
    /**************************************************/
	$retour='{';
	$retour.='"code": 1,';
	$retour.='"article": ';
	$retour.='[';
    try
    {
        $requeteSQL='SELECT nom, prix, descriptif, ville, etat, info FROM article WHERE id_article=?';
        $stm= $dbh->prepare($requeteSQL);
		$stm->execute(array($id_article));
        if ($row= $stm->fetch()) 
		{
			$retour.=GenererArticle($row["nom"], $row["prix"], $row["descriptif"], $row["ville"], $row["etat"], $row["info"]);
		}
		else
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