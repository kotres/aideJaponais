//import java.io.BufferedReader;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LecteurBase {
	private List<MotJaponais> listeMots;
	
	public List<MotJaponais> getListeMots()
	{
		return listeMots;
	}
	
	public LecteurBase() throws IOException
	{
		listeMots= new ArrayList<MotJaponais>();
		try {
			String bufferFichier = new String(Files.readAllBytes(Paths.get("listeMots.txt")));
			bufferFichier=bufferFichier.substring(bufferFichier.indexOf("{")+1);//pour éviter le string vide
			String[] strMots=bufferFichier.split("\\{");
			for(String strMot:strMots){
				if(strMot!=""&&strMot!=null){
					MotJaponais mj=new MotJaponais(strMot);
			    	//System.out.println(mj);
			    	listeMots.add(mj);
				}
			}
			Collections.sort(listeMots);
			for(MotJaponais mot:listeMots)
				System.out.println(mot);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
