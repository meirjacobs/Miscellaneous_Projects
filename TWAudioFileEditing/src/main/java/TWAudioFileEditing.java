import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TWAudioFileEditing {
    private static Set<String> authorSet = new HashSet<>(Arrays.asList("Rav Yakov Haber", "Rav Eliakim Koenigsberg", "Rav Aaron Lopiansky", "Rav Yaakov Neuburger", "Rav Michael Rosensweig", "Rav Yonason Sacks", "Rav Hershel Schachter", "Rav Zvi Sobolofsky", "Rav Daniel Stein", "Rav Dr. Abraham J. Twerski", "Rav Mayer Twersky", "Rav Mordechai Willig", "Rav Benjamin Yudin"));

    /*public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\meirj\\Downloads\\newestText.csv"));
        sc.nextLine();
        int counter = 1;
        while(sc.hasNextLine() && counter < 1098) {
            counter++;
            String[] line = sc.nextLine().split(",");
            //line[1].replace("Rav Schachter", "Rav Herschel Schachter").replace("Rav Willig", "Rav Mordechai Willig").replace("Rav Sobolofsky", "Rav Zvi Sobolofsky").replace("Rav Twersky", "Rav Mayer Twersky").replace("Rav Yudin", "Rav Benjamin Yudin").replace("Rav Dr. Twerski", "Rav Dr. Abraham J. Twerski");
            if(line.length < 2) {
                System.out.println("Line " + counter + ": ERROR");
                continue;
            }
            if(!authorSet.contains(line[1].trim())) {
                System.out.println("Line " + counter + ": " + line[1].trim());
            }
        }
    }*/

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\meirj\\Downloads\\newestText.csv"));
        sc.nextLine();
        int counter = 1;
        while(sc.hasNextLine() && counter < 50) {
            counter++;
            System.out.println("\nLine " + counter + ": ");
            String[] line = sc.nextLine().split(",");
            for(int i = 0; i < line.length; i++) {
                System.out.print(line[i] + "; ");
            }
            /*if(line.length <= 2) {
                System.out.println("Reached end at line " + counter + ". Ending program.");
                System.exit(0);
            }
            if(line.length < 5 || line[4] == null || line[4].trim().length() == 0) {
                System.out.println("Line " + counter);
            }*/
        }
    }
}
