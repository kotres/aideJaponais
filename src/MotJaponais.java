
public class MotJaponais {
	public String motOriginal;
	//public String kana;
	public String signification;
	public String exemple;
	public String exempleTraduit;
	private void parseString(String str){
		if(str.indexOf('=')!=-1)
			//System.out.println("erreur base de mots");
		{
			String[] tokens=str.split("=");
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
			}
		}
	}
	public MotJaponais()
	{
		motOriginal="NA";
		signification="NA";
		exemple="NA";
		exempleTraduit="NA";
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
		retour+="signification: "+signification+"\n";
		retour+="exemple: "+exemple+"\n";
		retour+="exemple traduit: "+exempleTraduit+"\n";
		return retour;
	}
}
