import java.util.Scanner;
import java.util.Random;

public class Driver {
    
    public static void main(String args[]) {
        
        String[] movies = {
            "My Neighbor Totoro", "Spirited Away", "Ice Age", "Cars", "The Iron Giant",
            "Good Will Hunting", "Lilo & Stitch", "Home Alone", "It", "The Truman Show",
            "The Godfather", "The Dark Knight", "Pulp Fiction", "Fight Club", "Inception",
            "Interstellar", "Parasite", "Oldboy", "Django Unchained", "WALL-E",
            "Toy Story", "Requiem for a Dream", "Up", "The Sixth Sense"
        };
        Category cat1 = new Category("Movies", movies);
        
        String[] musicalArtists = {
            "AC/DC", "Blackpink", "Twice", "Bon Jovi", "Aerosmith", "Adele",
            "Beyonce", "Amy Winehouse", "Ariana Grande", "MGMT", "Miley Cyrus",
            "Dolly Parton", "Nujabes", "Olivia Rodrigo", "Paramore", "Phum Viphurit", 
            "Radiohead", "Rihanna", "Selena Gomez", "Soulja Boy", "Tyler, The Creator",
            "Usher", "Weezer", "Whitney Houston", "Kelly Clarkson"
        };
        Category cat2 = new Category("Musical Artist", musicalArtists);
        
        Category[] categories = {cat1, cat2};
        
        // choosing a word
        Random rand = new Random();
        int randomInt = rand.nextInt(categories.length);
        Category chosen = categories[randomInt];
        String word = chosen.getRandomWord();
        
        // setup
        Scanner input = new Scanner(System.in);
        boolean gameInProgress = true;
        String nextChar = "";
        
        // starting game
        System.out.println("WELCOME TO THE HANGMAN GAME");
        System.out.println("Category is " + chosen.getName());
        
        String gameString = "";
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (currChar == ' ' || currChar == '-' || currChar == '/' || currChar == ',') {
                gameString += currChar;
                gameString += " ";
            }
            else gameString += "_ ";
                
        }
        StringBuilder updatedString =  new StringBuilder(gameString);

        System.out.println(updatedString);
        
        while(gameInProgress) {
            System.out.println("Guess a letter:");
            System.out.println("To guess the word, enter 1.");
            nextChar = input.nextLine();
            
            // input validation
            if ((nextChar.length() > 1) || (nextChar.length() < 1)) {
                System.out.println("enter exactly one character...");
                continue;
            }
            else if (nextChar.equals("1")) {
                System.out.println("Enter guess:");
                String guess = input.nextLine();
                if (guess.equalsIgnoreCase(word)) {
                    System.out.println("YOU WIN!");
                    break;
                }
                else {
                    System.out.println("That is not correct");
                }
            }
            else if (!nextChar.equals("1") && !Character.isAlphabetic(nextChar.charAt(0))) {
                System.out.println("symbols and numbers not allowed...");
                continue;
            }
            
            //if the character is not in the word
            else if (!word.contains(nextChar.toLowerCase()) || !word.contains(nextChar.toUpperCase())) {
                System.out.println("That letter is not in the word...");
            }
            
            // place the found letters
            for (int i = 0; i < word.length(); i++) {
                if (word.toUpperCase().charAt(i) == nextChar.charAt(0) || word.toLowerCase().charAt(i) == nextChar.charAt(0)) {
                    updatedString.setCharAt(i*2, nextChar.charAt(0));
                }
            }
            

            System.out.println(updatedString);
        }
        
        input.close();
    }

    // Object to hold the categories of words
    static class Category {
        
        String name;
        String[] words;
        Random rand = new Random();
        
        private Category(String name, String[] words) {
            this.name = name;
            this.words = words;
        }
        
        private String getName() {
            return name;
        }
        
        private String getRandomWord() {
            int randomInt = rand.nextInt(words.length);
            return words[randomInt];
        }
        
    }

}