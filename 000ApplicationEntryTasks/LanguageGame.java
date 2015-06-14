

import java.util.Scanner;

/**
 * License: MIT
 * Copyright (c) 2015 Spas Kyuchukov
 * 
 * Tasks description:
 * https://github.com/HackBulgaria/Haskell-1/blob/master/Application/02-LanguageGame/README.md
 */

public class LanguageGame {

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        //Text output commented if you are keen on keeping the output clean :)
        //System.out.print("Enter language: ");
        String language = in.nextLine();
        
        int distinctLettersCount = language.length() / 3;
        
        //System.out.print("Enter word to check or 'exit': ");
        String word = in.nextLine();
        
        while (word.compareTo("exit") != 0) {
            int letterCount = 1;
            int prevLetterCount = 0;
            int distinctLettersSoFar = 1;
            boolean inLanguage = true;
            
            for (int index = 1; index < word.length(); index++) {
                if(word.charAt(index) == word.charAt(index - 1)){
                    ++letterCount;
                } else {
                    // init with first letter count
                    if(prevLetterCount == 0){
                        prevLetterCount = letterCount;
                    }
                    
                    if(letterCount != prevLetterCount){
                        inLanguage = false;
                        break;
                    }
                    
                    letterCount = 1;
                    
                    ++distinctLettersSoFar;
                    if(distinctLettersSoFar > distinctLettersCount){
                        inLanguage = false;
                        break;
                    }
                }
            }
            
            if(letterCount != prevLetterCount && prevLetterCount != 0){
                inLanguage = false;
            }
            
            // empty string is in every language?
            if(distinctLettersSoFar < distinctLettersCount && word.length() > 0){
                inLanguage = false;
            }
            
            System.out.println((inLanguage)?"yes":"no");
            
            //System.out.println("Word '" + word + "' is " + ((!inLanguage)?"NOT ":"") + "in language '" + language + "'.");
            //System.out.print("Enter word to check or 'exit': ");
            //word = in.nextLine();
            // change if you want more than one check, made to match examples from task description
            break;
        }
    }

}
