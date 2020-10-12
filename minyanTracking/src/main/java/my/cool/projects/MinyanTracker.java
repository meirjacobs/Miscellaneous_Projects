package my.cool.projects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class MinyanTracker {

    private static String[] validNames = {"meir", "ezra", "efraim", "ari", "yoni"};
    private static Map<String, MinyanMan> map = new HashMap<>();
    private static MinyanMan[] order = new MinyanMan[5];
    private static String lastUpdated;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\meirj\\MyGit\\JacobsJordan\\My_Projects\\minyanTracking\\src\\main\\java\\my\\cool\\projects\\Statistics.txt"));
        int lineCount = 1;
        while (sc.hasNextLine() && lineCount < 6) {
            String[] line = sc.nextLine().split(" ");
            if(line.length != 8) {
                throw new IllegalArgumentException("Incorrect number of tokens on line " + lineCount + ". Expected 8, received " + line.length + ".");
            }
            String name = line[0].trim().replace(":", "").toLowerCase();
            int minyanimAttended = Integer.parseInt(line[1].trim());
            int shacharisAttended = Integer.parseInt(line[4].trim());
            int minchaAttended = Integer.parseInt(line[6].trim());
            order[lineCount - 1] = new MinyanMan(name, minyanimAttended, shacharisAttended, minchaAttended, lineCount);
            map.put(name, order[lineCount - 1]);
            lineCount++;
        }
        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");
            if(line.length == 0 || line.length == 1) continue;
            lastUpdated = line[2] + " " + line[3];
        }

        sc = new Scanner(System.in);
        System.out.println("\nshacharis or mincha?");
        String minyan = sc.next();
        if(!(minyan.equals("shacharis") || minyan.equals("mincha") || minyan.equals("print"))) {
            throw new IllegalArgumentException("Options are \"shacharis\" and \"mincha\"");
        }
        if(minyan.equals("print")) {
            printStatistics();
            System.exit(0);
        }
        System.out.println("\nWhich day of the week?");
        String dayOfWeek = sc.next().toLowerCase();
        if(!(dayOfWeek.equals("sunday") || dayOfWeek.equals("monday") || dayOfWeek.equals("tuesday") || dayOfWeek.equals("wednesday") || dayOfWeek.equals("thursday") || dayOfWeek.equals("friday"))) {
            throw new IllegalArgumentException("Invalid day of week");
        }
        System.out.println("\nHow many people attended this minyan? ");
        int attendees = sc.nextInt();
        if(attendees > order.length) {
            throw new IllegalArgumentException("Cannot have more minyan attendees than total minyan men");
        }
        if(attendees < 0) {
            throw new IllegalArgumentException("Cannot have less than 0 attendees");
        }
        if(attendees == 0) {
            System.out.println("\nOK. System Exiting.");
            System.exit(0);
        }
        System.out.println("\nEnter the first names of those who attended the minyan, comma separated");
        sc.nextLine();
        String line = sc.nextLine();
        if(line.trim().equals("clear")) {
            clear();
            printStatistics();
            System.exit(0);
        }
        String[] names = line.split(",");
        if(names.length != attendees) {
            throw new IllegalArgumentException("\nIncorrect number of names submitted. Required " + attendees + " but was given " + names.length);
        }
        for(String name : names) {
            name = name.trim().toLowerCase();
            if(!isValidName(name)) {
                throw new IllegalArgumentException("Invalid name: \"" + name + "\"");
            }
            MinyanMan man = map.get(name);
            man.minyanimAttended++;
            if (minyan.equals("shacharis")) {
                man.shacharisAttended++;
            } else {
                man.minchamaarivAttended++;
            }
            reposition(man);
        }

        editStatistics(dayOfWeek + " " + minyan);
        printStatistics();
    }

    private static boolean isValidName(String name) {
        return map.containsKey(name);
    }

    private static void reposition(MinyanMan man) {
        for(int i = man.position; i < order.length; i++) {
            order[i-1] = order[i];
            order[i-1].position--;
        }
        order[order.length - 1] = man;
        man.position = order.length;
    }

    private static void editStatistics(String lastUpdated) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("C:\\Users\\meirj\\MyGit\\JacobsJordan\\My_Projects\\minyanTracking\\src\\main\\java\\my\\cool\\projects\\Statistics.txt");
        pw.print("");
        for(MinyanMan man : order) {
            pw.println(man.name + ": " + man.minyanimAttended + " total - " + man.shacharisAttended + " shacharis, " + man.minchamaarivAttended + " mincha");
        }
        pw.println();
        pw.println("Last Updated: " + lastUpdated);
        pw.close();
    }

    private static void clear() throws FileNotFoundException {
        System.out.println("\nAre you sure you want to clear all data?");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next().trim().toLowerCase();
        if(answer.equals("yes")) {
            clearData();
        }
        else if(answer.equals("no")) {
            System.out.println("\nExiting program without changes. Try again.");
            System.exit(0);
        }
        else {
            System.out.println("\nThis was a yes or no question. Try again.");
            clear();
        }
    }

    private static void clearData() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("C:\\Users\\meirj\\MyGit\\JacobsJordan\\My_Projects\\minyanTracking\\src\\main\\java\\my\\cool\\projects\\Statistics.txt");
        pw.print("");
        for(MinyanMan man : order) {
            pw.println(man.name + ": 0 total - 0 shacharis, 0 mincha");
        }
        pw.close();
    }

    private static void printStatistics() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:\\Users\\meirj\\MyGit\\JacobsJordan\\My_Projects\\minyanTracking\\src\\main\\java\\my\\cool\\projects\\Statistics.txt"));
        System.out.println("\n*************************************\nMINYAN ORDER AND STATISTICS\n");
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }

}