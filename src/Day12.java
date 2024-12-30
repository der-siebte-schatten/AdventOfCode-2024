import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

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

    public static boolean NeighborContainsFence(HashMap<ArrayList<Integer>, ArrayList<String>> fences,
            ArrayList<Integer> plant, String neighbor, String border) {
        try {
            if (neighbor == "left")
                return fences.get(new ArrayList<Integer>(Arrays.asList(plant.get(0), plant.get(1) - 1)))
                        .contains(border);
            if (neighbor == "right")
                return fences.get(new ArrayList<Integer>(Arrays.asList(plant.get(0), plant.get(1) + 1)))
                        .contains(border);
            if (neighbor == "up")
                return fences.get(new ArrayList<Integer>(Arrays.asList(plant.get(0) - 1, plant.get(1))))
                        .contains(border);
            return fences.get(new ArrayList<Integer>(Arrays.asList(plant.get(0) + 1, plant.get(1)))).contains(border);

        } catch (NullPointerException e) {
            return false;
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

        int price = 0, discount = 0;
        for (HashSet<ArrayList<Integer>> region : regions) {
            int area = region.size();
            int perimeter = 0, sides = 0;
            HashMap<ArrayList<Integer>, ArrayList<String>> fences = new HashMap<ArrayList<Integer>, ArrayList<String>>();
            for (ArrayList<Integer> plant : region) {
                fences.put(plant, new ArrayList<String>());
                if (!region.contains(new ArrayList<Integer>(Arrays.asList(plant.get(0) - 1, plant.get(1)))))
                    fences.get(plant).add("up");
                if (!region.contains(new ArrayList<Integer>(Arrays.asList(plant.get(0) + 1, plant.get(1)))))
                    fences.get(plant).add("down");
                if (!region.contains(new ArrayList<Integer>(Arrays.asList(plant.get(0), plant.get(1) - 1))))
                    fences.get(plant).add("left");
                if (!region.contains(new ArrayList<Integer>(Arrays.asList(plant.get(0), plant.get(1) + 1))))
                    fences.get(plant).add("right");
                perimeter += fences.get(plant).size();
            }
            price += area * perimeter;

            for (Map.Entry<ArrayList<Integer>, ArrayList<String>> entry : fences.entrySet()) {
                ArrayList<Integer> plant = entry.getKey();
                ArrayList<String> borders = entry.getValue();
                for (int i = 0; i < borders.size(); i++) {
                    String border = borders.get(i);
                    if ((border.equals("up") || border.equals("down"))
                            && (NeighborContainsFence(fences, plant, "left", border)
                                    || NeighborContainsFence(fences, plant, "right", border))) {
                        borders.remove(i);
                        i--;
                        // Failsafe, if check was performed between two existing fences, also remove the
                        // left one arbitrary
                        if (NeighborContainsFence(fences, plant, "left", border)
                                && NeighborContainsFence(fences, plant, "right", border))
                            fences.get(new ArrayList<Integer>(Arrays.asList(plant.get(0), plant.get(1) - 1)))
                                    .remove(border);
                    }
                    if ((border.equals("left") || border.equals("right"))
                            && (NeighborContainsFence(fences, plant, "up", border)
                                    || NeighborContainsFence(fences, plant, "down", border))) {
                        borders.remove(i);
                        i--;
                        // Same failsafe, on the upper one
                        if (NeighborContainsFence(fences, plant, "up", border)
                                && NeighborContainsFence(fences, plant, "down", border))
                            fences.get(new ArrayList<Integer>(Arrays.asList(plant.get(0) - 1, plant.get(1))))
                                    .remove(border);
                    }
                }
                // This solution does not work unfortunately, failsafe is not enough as there can be a "hole" of 2 in the fence, where it does not work 
                sides += borders.size();
            }
            discount += area * sides;
        }

        System.out.println(price);
        System.out.println(discount);
    }
}
