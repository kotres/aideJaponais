import java.util.ArrayList;
import java.util.List;


public class Carte {
	private int numero;
	private String question;
	private List<Reponse> ListeReponses;
	public Carte(){
		numero=0;
		question=null;
		ListeReponses=new ArrayList<Reponse>();
	}
	public Carte(String strCarte){
		this();
		XmlParseur parseur=new XmlParseur(strCarte);
		numero=Integer.parseInt(parseur.parseSingleElement("numero"));
		question=parseur.parseSingleElement("question");
		List<String> listeStrReponses=new ArrayList<String>();
		parseur.parseElement("reponse", listeStrReponses);
		for(String strRep:listeStrReponses)
			ListeReponses.add(new Reponse(strRep));
	}
	
	public String toString(){
		String retour="question: "+question+" \n";
		retour+="numero: "+numero+" \n";
		for(Reponse rep:ListeReponses)
			retour+="reponse: "+rep+" \n";
		return retour;
	}

}
