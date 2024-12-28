import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        try (BufferedReader bin = new BufferedReader(new FileReader("data/day1.txt"))) {
			while(bin.ready()) {
				String line = bin.readLine();
				String[] tokens = line.split("   ");
				l1.add(Integer.parseInt(tokens[0].trim()));
                l2.add(Integer.parseInt(tokens[1].trim()));
			}
		} catch (Exception IOException) {
			System.err.println("IO error while reading");
		}
        // l1.sort(null);
        // l2.sort(null);

        // int d=0;
        // for(int i=0; i< l1.size(); i++)
        //     d+=Math.abs(l2.get(i) - l1.get(i));
        // System.out.println(d);

        int score = 0;
        Map<Integer, Integer> countL2 = new HashMap<Integer, Integer>();
        for (Integer i : l2) {
            countL2.put(i, countL2.getOrDefault(i, 0) + 1);
        }
        for (Integer i : l1) {
            if(countL2.containsKey(i)) score+=i*countL2.get(i);
        }
        System.out.println(score);
    }
}
