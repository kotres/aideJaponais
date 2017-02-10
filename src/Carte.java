import java.util.ArrayList;
import java.util.List;


public class Carte {
	private int numero;
	private String question;
	private String reponse;
	public Carte(){
		numero=0;
		question=null;
		reponse=null;
	}
	
	private String miseEnFormeString(String str){
		String[] tokens=str.split("\n");
		str="<html>";
		for(String token:tokens){
			str+=token+"<br><br>";
		}
		str+="</html>";
		return str;
	}
	
	public Carte(int numero, String question,String reponse){
		this.numero=numero;
		this.question=miseEnFormeString(question);
		this.reponse=miseEnFormeString(reponse);
	}
	
	public String toString(){
		String retour="question: "+question+" \n";
		retour+="numero: "+numero+" \n";
		retour+="reponse: "+reponse+" \n";
		return retour;
	}
	
	public int getNumero(){
		return numero;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getReponse(){
		return reponse;
	}

}
