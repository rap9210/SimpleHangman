import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SimpleHangman {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Random r = new Random();
        int i, j;
        /*
        Initialize ArrayList with words provided.
        Randomly select a word from the list.
        Generate empty string array with as many blank spaces
        as there are letters in the randomly selected word.
        Prompt user to start guessing.
        If letter is in word display letter/s.
        If letter is not in word, display how many attempts are left.
        If the user guesses all the right letters or the whole word
        game ends, display you win!
        If the user runs out of attempts or guesses the wrong word
        game ends, display you lose!
         */

        ArrayList <String> hangman_wordlist = new ArrayList<>();
        hangman_wordlist.add("tree");
        hangman_wordlist.add("rain");
        hangman_wordlist.add("bear");
        hangman_wordlist.add("encourage");
        hangman_wordlist.add("promise");
        hangman_wordlist.add("soup");
        hangman_wordlist.add("chess");
        hangman_wordlist.add("insurance");
        hangman_wordlist.add("pancakes");
        hangman_wordlist.add("stream");
        
        String random_hangman_word = hangman_wordlist.get(r.nextInt(hangman_wordlist.size()));
        String blank_spaces[] = new String[random_hangman_word.length()];
        String random_word_array[] = random_hangman_word.split("");


        int wrong_guess_count = 6;
        boolean match = false;

        System.out.println("The word is ready. \n6 wrong guesses and you lose.You can guess a letter or a word.");
        for (i=0; i<random_hangman_word.length(); i++) {
            blank_spaces[i] = "_";
            System.out.print(blank_spaces[i]+" ");
        }
        //Prompt user for guess
        System.out.println("\nEnter a letter or a word:");
        String user_guess = scn.nextLine();
        //Conditions are victory or fail conditions: guessed word, out of attempts, and guessed all letters.
        while((!user_guess.equalsIgnoreCase(random_hangman_word))&&(wrong_guess_count > 0)&&(!Arrays.equals(random_word_array,blank_spaces))){
            //for loop will compare every letter from the random word to the letter the user guessed
            //boolean is for the machine to know that at least one match was found, so it doesnt count as wrong guess
            for (i=0; i<random_hangman_word.length(); i++){
                if (user_guess.equalsIgnoreCase(random_word_array[i])){
                    blank_spaces[i] = user_guess;
                    System.out.print(blank_spaces[i]+" ");
                    match = true;
                }
                else{
                    System.out.print(blank_spaces[i]+" ");
                }
            }
            //Wrong guess and consequences
            if(match == false){
                wrong_guess_count--;
                if(user_guess.length()==1) {
                    System.out.println("\nThe letter " + user_guess + " is not in the word. You have " + wrong_guess_count + " attempts remaining.");
                }
                else{
                    System.out.println(user_guess + " is not the word. You have " + wrong_guess_count + " attempts remaining.");
                }
            }
            //Victory conditions met, output conditions
            if ((user_guess.equalsIgnoreCase(random_hangman_word))||(Arrays.equals(blank_spaces, random_word_array))){
                System.out.println("\nThat's it! You got it!");
            }
            //Reset boolean before next iteration.
            match = false;
            //Condition prevents a new prompt after the game is done.
            if (wrong_guess_count > 0) {
                System.out.println("\nEnter a letter or a word:");
                user_guess = scn.nextLine();
                if (user_guess.equalsIgnoreCase(random_hangman_word)) {
                    System.out.println("\nThat's it! You got it!");
                }
            }
            //Out of attempts, loss prompt.
            else {
                System.out.println("Sorry! You're out of attempts! The word was "+Arrays.toString(random_word_array)+"Maybe next time.");
            }
        }
    }
}
