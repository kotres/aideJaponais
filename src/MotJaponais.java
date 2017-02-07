
public class MotJaponais implements Comparable<MotJaponais>{
	public String motOriginal;
	//public String kana;
	public String signification;
	public String exemple;
	public String exempleTraduit;
	int rangOccurence;
	private void parseString(String str){
		if(str.indexOf('=')!=-1)
			//System.out.println("erreur base de mots");
		{
			String[] tokens=str.split("=");
			if(tokens[1].contains(">"))
				tokens[1]=tokens[1].substring(0,tokens[1].indexOf(">"));
			switch(tokens[0]){
			case "m":
				motOriginal=tokens[1];
			break;
			case "s":
				signification=tokens[1];
			break;
			case "e":
				exemple=tokens[1];
			break;
			case "et":
				exempleTraduit=tokens[1];
			break;
			case "n":
				rangOccurence=Integer.parseInt(tokens[1]);
			}
		}
	}
	public MotJaponais()
	{
		motOriginal="NA";
		signification="NA";
		exemple="NA";
		exempleTraduit="NA";
		rangOccurence=-1;
	}
	
	public MotJaponais(String str)
	{
		this();
		String[] tokens=str.split("<");
		for(String token:tokens){
			parseString(token);
		}
	}
	
	public String toString()
	{
		String retour="mot: "+motOriginal+"\n";
		retour+="rang occurence: "+rangOccurence+"\n";
		retour+="signification: "+signification+"\n";
		retour+="exemple: "+exemple+"\n";
		retour+="exemple traduit: "+exempleTraduit+"\n";
		return retour;
	}
	@Override
	public int compareTo(MotJaponais arg0) {
		return this.rangOccurence-arg0.rangOccurence;
	}
	
}
