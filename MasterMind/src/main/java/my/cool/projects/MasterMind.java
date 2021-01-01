package my.cool.projects;
import java.util.*;

public class MasterMind {

    public static void main(String[] args) {
        final String[] colors = {"black", "blue", "white", "green", "red", "yellow"};
        final Set<String> legalColors = new HashSet<String>(Arrays.asList(colors));
        Random randomizer = new Random();
        String[] code = new String[4];
        for(int i=0; i < code.length; i++) {
            code[i] = colors[randomizer.nextInt(6)];
        }
        int turn = 0;
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<Integer, Boolean> done = new HashMap<Integer, Boolean>();
        while(turn < 10) {
            turn++;
            map.clear();
            System.out.println("\nGuess 4 colors");
            String[] guess = scanner.nextLine().toLowerCase().split("\\s+");
            if(guess.length != 4) {
                System.err.println("Must guess 4 colors");
                turn--;
                continue;
            }
            boolean invalid = false;
            for(String r : guess) {
                if(!legalColors.contains(r)) {
                    System.err.println("Invalid color: \"" + r + "\"");
                    invalid = true;
                }
            }
            if(invalid) {
                turn--;
                continue;
            }
            for(String key : code) {
                if(map.containsKey(key)) {
                    map.put(key, (map.get(key) + 1));
                }
                else {
                    map.put(key, 1);
                }
            }
            int white = 0;
            int black = 0;
            for(int j=0; j < guess.length; j++) {
                String peg = guess[j];
                if(peg.equals(code[j])) {
                    white++;
                    map.put(peg, (map.get(peg) - 1));
                    done.put(j, true);
                }
            }
            for(int j=0; j < guess.length; j++) {
                String peg = guess[j];
                if(done.containsKey(j) && done.get(j)) {
                    continue;
                }
                if(map.containsKey(peg) && map.get(peg) > 0) {
                    black++;
                    map.put(peg, (map.get(peg) - 1));
                }
            }
            System.out.println(white + " White");
            System.out.println(black + " Black");
            if(white == 4) {
                System.out.println("\nYou Won in " + turn + " Turns!");
                break;
            }
            System.out.println("\nYou have " + (10 - turn) + " turns remaining\n");
        }
        if(turn == 10) {
            System.out.println("You lose! Better luck next time!");
            System.out.print("The code was ");
            for(String s : code) {
                System.out.print(s + " ");
            }
        }
    }

}