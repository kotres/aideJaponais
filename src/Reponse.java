import java.util.ArrayList;
import java.util.List;


public class Reponse {
	private String signification;
	private List<String> listeExemples;
	
	public Reponse(){
		signification=null;
		listeExemples=new ArrayList<String>();
	}
	public Reponse(String strReponse){
		this();
		XmlParseur parseur=new XmlParseur(strReponse);
		signification=parseur.parseSingleElement("signification");
		parseur.parseElement("exemple", listeExemples);
	}
	
	public String toString(){
		String retour="signification: "+signification+" \n";
		for(String str:listeExemples)
			retour+="exemple: "+str+" \n";
		return retour;
	}
	
	/*public static void main(String[] args){
		Reponse rep=new Reponse("<reponse><signification>particule de lieu/direction/temps; à</signification><exemple>学校「がっこう」にいるje suis à l’école</exemple><exemple>７時「しちじ」にねc’est à sept heures</exemple></reponse>");
		System.out.println(rep);
	}*/
}
