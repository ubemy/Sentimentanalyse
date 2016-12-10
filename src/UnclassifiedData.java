import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class UnclassifiedData {
	ArrayList<String> listEntry;
	String unclassifiedFile = "Testdaten/TwitterUnklassifiziert/twitter_fhb.txt";
	
	public ArrayList<String> getListEnries()
	{
		return this.listEntry;
	}
	
	/*
	 * Datei einlesen
	 */
	public void readFiles()
	{
		File f = new File(unclassifiedFile);
		listEntry = new ArrayList<String>();
		String line;
		
		try
		{
			if(f != null)
			{
				int j=0;
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f.getAbsolutePath()), "UTF-8"));
			    while ((line = br.readLine()) != null) {
			    	if(j>0) {
			    		int i=0;
				    	for(String s : line.split("\t")) {
				    		i++;
				    		if(i==5) {
				    			listEntry.add(s);
				    		}
				    	}
			    	}
			    	else {
			    		j++;
			    	}
			    }
			    br.close();
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
