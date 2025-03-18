package Games;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GetWord 
{
	private static ArrayList<String>wordList=new ArrayList<>();
	static Random rand = new Random();
	
	public static String guessWord()
	{
	try
	{
		File file = new File("src/files/dict.txt");
		Scanner scan = new Scanner(file);
		while(scan.hasNextLine())
		{
			wordList.add(scan.nextLine());	
		}
		scan.close();
	}
	catch(FileNotFoundException e)
	{
		e.printStackTrace();
	}
		
	return wordList.get(rand.nextInt(wordList.size()));
	}

}
