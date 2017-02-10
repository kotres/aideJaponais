import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Paquet {
	
	private NodeList listeNodeCartes;
	public Paquet(){
		listeNodeCartes=null;
	}
	
	public Paquet(String filePath){
		try {
	    	  File fichierXml = new File(filePath);
	    	  DocumentBuilderFactory dbFactory 
	            = DocumentBuilderFactory.newInstance();
	    	  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	  Document docXml = dBuilder.parse(fichierXml);
	    	  docXml.getDocumentElement().normalize();
	    	  //System.out.println(docXml.getDocumentElement().getNodeName());
	    	  listeNodeCartes = docXml.getElementsByTagName("carte");
	    	  for(int i=0;i< listeNodeCartes.getLength();i++){
	    		  Node carte=listeNodeCartes.item(i);
	    		  parseNode("",carte,0);
	    	  }
		}catch (Exception e) {
	          e.printStackTrace();
	    }
	}
	
	public static void parseNode(String nomParent,Node node,int iteration){
		if(iteration<10&&node!=null){
			iteration++;
			nomParent+="/"+node.getNodeName();
			if(!nomParent.contains("#text"))
				System.out.println(nomParent);
			if(node.hasAttributes()){
				NamedNodeMap mapDesNodes = node.getAttributes();
				for(int i=0;i<mapDesNodes.getLength();i++){
					Node nodeFils=mapDesNodes.item(i);
					System.out.println(nodeFils.getNodeName());
				}
			}
			if(node.hasChildNodes()){
				NodeList listeNodes=node.getChildNodes();
				for(int i=0;i<listeNodes.getLength();i++){
					parseNode(nomParent,listeNodes.item(i),iteration);
				}
			}
		}
	}
	
	
	public Carte getCarte(int i){
		if(i<listeNodeCartes.getLength()){
			Element element=(Element)listeNodeCartes.item(i);
			String ques=element.getElementsByTagName("question").item(0).getTextContent();
			int tailleRep=element.getElementsByTagName("reponse").getLength();
			int randRep=ThreadLocalRandom.current().nextInt(0, tailleRep);
			String rep=element.getElementsByTagName("reponse").item(randRep)
					.getTextContent();
			return new Carte(1,ques,rep);
		}
		else
			return null;
	}
	
	public int taillePaquet(){
		return listeNodeCartes.getLength();
	}
	
	/*public static void main(String[] args){
		Paquet paq=new Paquet("listeMots.xml");
	}*/
	
}

//System.out.println(carte.getNodeName());
/*Element element=(Element)carte;
System.out.println(element.getElementsByTagName("reponse")
        .item(0)
        .getTextContent());*/
