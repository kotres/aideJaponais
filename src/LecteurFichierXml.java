import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class LecteurFichierXml {
	private List<Carte> paquet;
	public LecteurFichierXml(){
		paquet=new ArrayList<Carte>();
	}
	public LecteurFichierXml(String filePath) throws IOException{
		this();
		String stringFichierXml = new String(Files.readAllBytes(Paths.get(filePath)));
		XmlParseur parseur=new XmlParseur(stringFichierXml);
		if(parseur.isElementPresent("packet"));
		List<String> listeStrCartes=new ArrayList<String>();
		parseur.parseElement("carte", listeStrCartes);
		for(String strCarte:listeStrCartes)
			paquet.add(new Carte(strCarte));
	}
	
	public String toString(){
		String retour="packet:\n";
		for(Carte carte:paquet)
			retour+=carte;
		return retour;
	}
	public static void main(String[] args) throws IOException{
		LecteurFichierXml lecteur=new LecteurFichierXml("listeMots.xml");
		System.out.println(lecteur);
	}
}
