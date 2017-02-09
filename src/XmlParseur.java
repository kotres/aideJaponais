import java.util.List;

public class XmlParseur {
	private String stringToParse;
	public XmlParseur(){
		stringToParse=null;
	}
	public XmlParseur(String stringToParse){
		this.stringToParse=stringToParse;
	}
	public boolean isElementPresent(String element){
		return(stringToParse.contains("<"+element+">")&&stringToParse.contains("</"+element+">"));
	}
	
	public String parseSingleElement(String element){
		if(isElementPresent(element)){
			int indexDeb=stringToParse.indexOf("<"+element+">");
			int indexFin=stringToParse.indexOf("</"+element+">");
			return stringToParse.substring(indexDeb+element.length()+2,indexFin);
		}
		else
			return null;
	}
	
	public void parseElement(String element,List<String> listToFill){
		while(isElementPresent(element)){
			int indexDeb=stringToParse.indexOf("<"+element+">");
			int indexFin=stringToParse.indexOf("</"+element+">")+1;
			listToFill.add(stringToParse.substring(indexDeb+element.length()+2,indexFin-1));
			stringToParse=stringToParse.substring(indexFin);
		}
	}
}
