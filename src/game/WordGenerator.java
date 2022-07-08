package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.ArrayList;
public class WordGenerator {
	private String word;
	public String category;
	private ArrayList<String> links;
	private ArrayList<String> categories;
	public int max_gen_index;
	
	public WordGenerator(int max_gen_index) {
		this.setWord("");
		this.max_gen_index = max_gen_index;
		
		this.links = new ArrayList<>();
		this.links.add("https://raw.githubusercontent.com/imsky/wordlists/master/nouns/food.txt");
		this.links.add("https://raw.githubusercontent.com/imsky/wordlists/master/nouns/music_instruments.txt");
		this.links.add("https://raw.githubusercontent.com/imsky/wordlists/master/nouns/coding.txt");
		this.links.add("https://raw.githubusercontent.com/imsky/wordlists/master/nouns/fruit.txt");
		
		this.categories = new ArrayList<>();
		this.categories.add("Foods");
		this.categories.add("Music Instruments");
		this.categories.add("Coding");
		this.categories.add("Fruits");
		
		Random random = new Random();
		int gen_categ_index = random.nextInt(this.categories.size());
		String word = null;
		
		try {
			URL url = new URL(this.links.get(gen_categ_index));
			BufferedReader s = new BufferedReader(new InputStreamReader(url.openStream()));
		   	int word_index = random.nextInt(this.max_gen_index);
		   	
		   	do {
			   	while ((word = s.readLine()) != null) {
			    	if (word_index == 0) {
			    		break;
			    	}
			    	word_index--;
			    }
			   	if (word == null) {
	    			this.max_gen_index /= 2;
	    			break;
	    		}
		   	} while (word == null);
		   	
		    this.category = this.categories.get(gen_categ_index);
			s.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		this.setWord(word);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
