package my.cool.projects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WordFind {

    private static class Result {
        boolean found;
        String startCoordinate;
        String endCoordinate;
        static Result NOT_FOUND = new Result(false, null, null);

        public Result(boolean found, String startCoordinate, String endCoordinate) {
            this.found = found;
            this.startCoordinate = startCoordinate;
            this.endCoordinate = endCoordinate;
        }

        @Override
        public String toString() {
            if(!found) return "Not found";
            return startCoordinate + " " + endCoordinate;
        }
    }

    public static void main(String[] args) throws IOException {
        if(args.length != 2 || args[0] == null || args[1] == null) {
            throw new IllegalArgumentException("Usage: WordFind file word\n" +
                    "\tfile: the file containing the word find text\n" +
                    "\tword: the word to be searched for in the word find");
        }
        File wordFindFile = new File(args[0]);
        List<char[]> charList = new ArrayList<>();
        Files.lines(wordFindFile.toPath()).forEach(line -> charList.add(line.toCharArray()));
        char[][] wordFind = new char[charList.size()][charList.get(0).length];
        for(int i = 0; i < charList.size(); i++) wordFind[i] = charList.get(i);
        System.out.println(findWord(wordFind, args[1].toCharArray(), 0, 0, 0, true));
    }

    private static Result findWord(char[][] wordFind, char[] word, int nChar, int sourceX, int sourceY, boolean start) {
        if(start) {
            for (int i = 0; i < wordFind.length; i++) {
                for (int j = 0; j < wordFind[0].length; j++) {
                    if(wordFind[i][j] == word[nChar]) {
                        if(nChar == word.length - 1) {
                            return new Result(true, createCoordinate(i, j), createCoordinate(i, j));
                        }
                        Result result = findWord(wordFind, word, nChar, i, j, false);
                        if(result.found) {
                            result.startCoordinate = createCoordinate(i, j);
                            return result;
                        }
                    }
                }
            }
        }
        else {
            if(sourceX < 0 || sourceX >= wordFind.length || sourceY < 0 || sourceY >= wordFind[0].length) {
                return Result.NOT_FOUND;
            }
            if(wordFind[sourceX][sourceY] == word[nChar]) {
                if(nChar == word.length - 1) {
                    return new Result(true, null, createCoordinate(sourceX, sourceY));
                }
                Result res;
                if((res = findWord(wordFind, word, nChar + 1, sourceX - 1, sourceY - 1, false)).found) return res;
                if((res = findWord(wordFind, word, nChar + 1, sourceX - 1, sourceY, false)).found) return res;
                if((res = findWord(wordFind, word, nChar + 1, sourceX - 1, sourceY + 1, false)).found) return res;
                if((res = findWord(wordFind, word, nChar + 1, sourceX, sourceY - 1, false)).found) return res;
                if((res = findWord(wordFind, word, nChar + 1, sourceX, sourceY + 1, false)).found) return res;
                if((res = findWord(wordFind, word, nChar + 1, sourceX + 1, sourceY - 1, false)).found) return res;
                if((res = findWord(wordFind, word, nChar + 1, sourceX + 1, sourceY, false)).found) return res;
                if((res = findWord(wordFind, word, nChar + 1, sourceX + 1, sourceY + 1, false)).found) return res;
            }
        }
        return Result.NOT_FOUND;
    }

    private static String createCoordinate(int i, int j) {
        return "[" + i + ", " + j + "]";
    }
}
