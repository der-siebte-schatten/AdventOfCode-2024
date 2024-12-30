import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Day12 {
    public static void explore(ArrayList<ArrayList<Character>> map, char plant, HashSet<ArrayList<Integer>> region,
            HashSet<ArrayList<Integer>> explored, int i, int j) {
        try {
            if (!map.get(i).get(j).equals(plant) || region.contains(new ArrayList<Integer>(Arrays.asList(i, j))))
                return;
            region.add(new ArrayList<Integer>(Arrays.asList(i, j)));
            explored.add(new ArrayList<Integer>(Arrays.asList(i, j)));

            explore(map, plant, region, explored, i - 1, j);
            explore(map, plant, region, explored, i + 1, j);
            explore(map, plant, region, explored, i, j - 1);
            explore(map, plant, region, explored, i, j + 1);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();
        try (BufferedReader bin = new BufferedReader(new FileReader("data/input"))) {
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

        HashSet<ArrayList<Integer>> explored = new HashSet<ArrayList<Integer>>();
        HashSet<HashSet<ArrayList<Integer>>> regions = new HashSet<HashSet<ArrayList<Integer>>>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (explored.contains(new ArrayList<Integer>(Arrays.asList(i, j))))
                    continue;
                HashSet<ArrayList<Integer>> region = new HashSet<ArrayList<Integer>>();
                explore(map, map.get(i).get(j), region, explored, i, j);
                regions.add(region);
            }
        }

        int price = 0;
        for (HashSet<ArrayList<Integer>> region : regions) {
            int area = region.size();
            int perimeter = 0;
            for (ArrayList<Integer> plant : region) {
                int borders = 0;
                if (!region.contains(new ArrayList<Integer>(Arrays.asList(plant.get(0) - 1, plant.get(1)))))
                    borders++;
                if (!region.contains(new ArrayList<Integer>(Arrays.asList(plant.get(0) + 1, plant.get(1)))))
                    borders++;
                if (!region.contains(new ArrayList<Integer>(Arrays.asList(plant.get(0), plant.get(1) - 1))))
                    borders++;
                if (!region.contains(new ArrayList<Integer>(Arrays.asList(plant.get(0), plant.get(1) + 1))))
                    borders++;
                perimeter += borders;
            }
            price += area * perimeter;
        }

        System.out.println(price);
    }
}
