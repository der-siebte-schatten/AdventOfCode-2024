import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Day9 {
    public static void main(String[] args) {
        ArrayList<String> disk = new ArrayList<String>();
        int id = 0;
        try (BufferedReader bin = new BufferedReader(new FileReader("data/day9.txt"))) {
            String s = bin.readLine();
            boolean file = true;
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < Integer.parseInt(s.substring(i, i + 1)); j++) {
                    if (file)
                        disk.add(Integer.toString(id));
                    else
                        disk.add(".");
                }
                if(file)
                    id++;
                file = !file;
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        // PART 1
        // for(int i = disk.size() - 1; i > 0; i--) {
        //     if(!disk.get(i).equals(".")) {
        //         int free = disk.indexOf(".");
        //         if(free > i)
        //             break;
        //         disk.remove(free);
        //         disk.add(free, disk.get(i - 1));
        //         disk.remove(i);
        //         disk.add(i, ".");
        //     }
        // }

        id--;
        while(id > 0) {
            int pos = disk.indexOf(Integer.toString(id)), size = disk.lastIndexOf(Integer.toString(id)) - pos + 1;
            int free = 0;
            for(int i = 0; i < pos; i++) {
                if(disk.get(i).equals("."))
                    free++;
                else
                    free = 0;
                if(free == size) {
                    for (int k = 0; k < size; k++) {
                        disk.remove(i - size + k + 1);
                        disk.add(i - size + 1, Integer.toString(id));
                        disk.remove(pos);
                        disk.add(pos + size - 1, ".");
                    }
                    break;
                }
            }
            id--;
        }

        long score = 0;
        for(int i = 0; i < disk.size(); i++)
            if (!disk.get(i).equals("."))
                score += i * Long.parseLong(disk.get(i));
        System.out.println(score);
    }
}
