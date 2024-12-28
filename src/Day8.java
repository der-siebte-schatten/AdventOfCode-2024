import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Day8 {
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();
        try (BufferedReader bin = new BufferedReader(new FileReader("data/day8.txt"))) {
            while (bin.ready()) {
                String s = bin.readLine();
                ArrayList<Character> line = new ArrayList<Character>();
                for (int i = 0; i < s.length(); i++)
                    line.add(s.charAt(i));
                map.add(line);
            }
        } catch (Exception e) {
            throw e;
        }

        int score = 0;
        ArrayList<ArrayList<Integer>> spots = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == '.')
                    continue;
                char c = map.get(i).get(j);
                int i1 = i, j1 = j + 1;
                while (i1 < map.size()) {
                    while (j1 < map.get(i1).size()) {
                        if (map.get(i1).get(j1) != c) {
                            j1++;
                            continue;
                        }
                        int[] d = { i1 - i, j1 - j };
                        // For Part 1, set k to 1 and get rid of the while loop
                        int k = 0;
                        while ((i - k * d[0] >= 0 && j - k * d[1] >= 0)
                                && (i - k * d[0] < map.size() && j - k * d[1] < map.get(i).size())
                                || (i1 + k * d[0] >= 0 && j1 + k * d[1] >= 0)
                                && (i1 + k * d[0] < map.size() && j1 + k * d[1] < map.get(i).size())) {
                            if (i - k * d[0] >= 0 && j - k * d[1] >= 0 && i - k * d[0] < map.size()
                                    && j - k * d[1] < map.get(i).size()
                                    && !spots.contains(
                                            new ArrayList<Integer>(Arrays.asList(i - k * d[0], j - k * d[1])))) {
                                spots.add(new ArrayList<Integer>(Arrays.asList(i - k * d[0], j - k * d[1])));
                                score++;
                            }
                            if (i1 + k * d[0] >= 0 && j1 + k * d[1] >= 0 && i1 + k * d[0] < map.size()
                                    && j1 + k * d[1] < map.get(i).size()
                                    && !spots.contains(
                                            new ArrayList<Integer>(Arrays.asList(i1 + k * d[0], j1 + k * d[1])))) {
                                spots.add(new ArrayList<Integer>(Arrays.asList(i1 + k * d[0], j1 + k * d[1])));
                                score++;
                            }
                            k++;
                        }
                        j1++;
                    }
                    j1 = 0;
                    i1++;
                }
            }
        }
        System.out.println(score);
    }
}
