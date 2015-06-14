
import java.util.Scanner;

/**
 * License: MIT
 * Copyright (c) 2015 Spas Kyuchukov
 * 
 * Tasks description:
 * https://github.com/HackBulgaria/Haskell-1/blob/master/Application/03-NameMatching/README.md
 */

/*
 * Let's do the math first, because that is the important part
 * 
 * Probability of correctly guessing the names of N people if you know their
 *      N different names (with task's input there is not enough info to
 *      correctly calculate probability in the case of possibly equal names) is 
 *      1/N * 1/N-1 * ... 1/1 or 1/factorial(N).
 * 
 * Because the probability of correctly guessing the first person's name
 * out of N possible names is 1/N, for the second one we have one less possible
 * names and so on.
 * 
 * We have 2 distinctive groups of people that can't be mistaken - male and female
 *      names and persons are distinct.
 * 
 * Total probability for not mistaking even a single name is
 *      1/(factorial(UM)*factorial(UF))
 *      where UM is the number of distinct male names that we do not know
 *      the person for, and UF the same for females.
 * 
 */

public class NameMatching {
    
    public static final String MALE_NAME_SUFFIX = "ss";
    public static final String FEMALE_NAME_SUFFIX = "tta";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int knownMalesCount = in.nextInt();
        int knownFemalesCount = in.nextInt();
        
        int allMalesCount = 0;
        int allFemalesCount = 0;
        
        String allNames = in.nextLine();
        // consume one empty line
        allNames = in.nextLine();
        String[] names = allNames.split(" ");
        
        for (String name : names) {
            if(name.endsWith(MALE_NAME_SUFFIX)) {
                ++allMalesCount;
            } else if(name.endsWith(FEMALE_NAME_SUFFIX)) {
                ++allFemalesCount;
            }
        }
        
        int unknownMalesCount = allMalesCount - knownMalesCount;
        int unknownFemalesCount = allFemalesCount - knownFemalesCount;
        
        double probabilityOfSuccess = 1.0 / (factorial(unknownMalesCount) * factorial(unknownFemalesCount));
        // probability to percent
        probabilityOfSuccess *= 100;
        System.out.println(String.format("%.0f%%", probabilityOfSuccess));
    }
    
    public static long factorial(int n) {
        // <0 is undefined, >20 results in something that can not fit inside long
        if (n < 0 || n > 20){
            throw new IllegalArgumentException("factorila() method can calculate n factorial for n = 0 ... 20 only!");
        }
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

}
