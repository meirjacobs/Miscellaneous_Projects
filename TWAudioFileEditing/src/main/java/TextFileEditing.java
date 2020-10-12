import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TextFileEditing {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\meirj\\Downloads\\textMod1 (6).csv"));
        Map<String, Integer> titles = new HashMap<>();
        int counter = 1;
        while(sc.hasNextLine() && counter <= 1150) {
            String[] line = sc.nextLine().split(",");
            if(!titles.containsKey(line[0].trim())) {
                titles.put(line[0].trim(), counter);
            }
            else {
                System.out.println("\nLine " + counter + ": " + line[0].trim() + "\nPreviously appeared on line " + titles.get(line[0].trim()));
            }
            counter++;
        }
    }
}
