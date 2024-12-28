import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Day10 {

    public static int explore(ArrayList<ArrayList<Integer>> map, int i, int j, ArrayList<ArrayList<Integer>> hikes,
            int level) {
                //System.out.printf("Exploring hike on %d, %d at level %d \n", i, j, level); //Used for debugging
        try {
            if (map.get(i).get(j) == level) {
                if (map.get(i).get(j) == 9 && !hikes.contains(new ArrayList<Integer>(Arrays.asList(i, j)))) {
                    // hikes.add(new ArrayList<Integer>(Arrays.asList(i, j))); //Uncomment for Part 1
                    return 1;
                } else {
                    return explore(map, i - 1, j, hikes, level + 1)
                            + explore(map, i, j - 1, hikes, level + 1)
                            + explore(map, i + 1, j, hikes, level + 1)
                            + explore(map, i, j + 1, hikes, level + 1);
                }
            } else {
                return 0;
            }
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
        try (BufferedReader bin = new BufferedReader(new FileReader("data/day10.txt"))) {
            while (bin.ready()) {
                String s = bin.readLine();
                ArrayList<Integer> line = new ArrayList<Integer>();
                for (int i = 0; i < s.length(); i++)
                    line.add(Integer.parseInt(s.substring(i, i + 1)));
                map.add(line);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        int score = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) != 0)
                    continue;
                ArrayList<ArrayList<Integer>> hikes = new ArrayList<ArrayList<Integer>>();
                score += explore(map, i, j, hikes, 0);
            }
        }
        System.out.println(score);
    }
}
