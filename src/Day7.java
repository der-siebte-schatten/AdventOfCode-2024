import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day7 {

    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Long>> equations = new ArrayList<ArrayList<Long>>();
        ArrayList<Long> values = new ArrayList<Long>();
        try (BufferedReader bin = new BufferedReader(new FileReader("data/day7.txt"))) {
            while (bin.ready()) {
                String s = bin.readLine();
                String[] tokens = s.split(":");
                values.add(Long.parseLong(tokens[0]));
                tokens = tokens[1].split(" ");
                ArrayList<Long> equation = new ArrayList<Long>();
                for (String string : tokens) {
                    if(string.equals(""))
                        continue;
                    equation.add(Long.parseLong(string));
                }
                equations.add(equation);
            }
        } catch (Exception e) {
            throw e;
        }

        long score = 0;
        for(int i = 0; i < equations.size(); i++) {
            ArrayList<Long> results = new ArrayList<Long>();
            results.add(equations.get(i).get(0));
            for(int j = 1; j < equations.get(i).size(); j++) {
                ArrayList<Long> inputs = new ArrayList<Long>(results);
                results.clear();
                for (Long input : inputs) {
                    results.add(input + equations.get(i).get(j));
                    results.add(input * equations.get(i).get(j));
                    results.add(Long.parseLong(input.toString() + equations.get(i).get(j).toString()));
                }
            }
            if(results.contains(values.get(i)))
                score+=values.get(i);
        }
        System.out.println(score);
    }
}