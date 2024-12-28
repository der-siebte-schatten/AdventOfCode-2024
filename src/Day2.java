import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
	public static boolean isSafe(List<Integer> levels, boolean dampening) {
		if (levels.get(0) == levels.get(1)) {
			if (dampening) {
				System.err.println("Unsafe!");
				return false;
			} else {
				levels.remove(0);
				return isSafe(levels, true);
			}
		}
		if (levels.get(0) < levels.get(1)) {
			System.out.print("Increasing... ");
			for (int i = 0; i < levels.size() - 1; i++) {
				if (levels.get(i + 1) - levels.get(i) > 3 || levels.get(i + 1) - levels.get(i) < 1) {
					if (dampening) {
						System.err.println("unsafe!");
						return false;
					}
					System.out.println("Dampening... ");
					for (int j = 0; j < levels.size(); j++) {
						System.out.print("   ");
						List<Integer> levels2 = new ArrayList<>(levels);
						levels2.remove(j);
						if (isSafe(levels2, true))
							return true;
					}
					return false;
				}
			}
		} else {
			System.out.print("Decreasing... ");
			for (int i = 0; i < levels.size() - 1; i++) {
				if (levels.get(i) - levels.get(i + 1) > 3 || levels.get(i) - levels.get(i + 1) < 1) {
					if (dampening) {
						System.err.println("unsafe!");
						return false;
					}
					System.out.println("Dampening... ");
					for (int j = 0; j < levels.size(); j++) {
						System.out.print("   ");
						List<Integer> levels2 = new ArrayList<>(levels);
						levels2.remove(j);
						if (isSafe(levels2, true))
							return true;
					}
					return false;
				}
			}
		}
		System.out.println("Safe.");
		return true;
	}

	public static void main(String[] args) {
		List<Integer> levels = new ArrayList<Integer>();
		int score = 0;
		try (BufferedReader bin = new BufferedReader(new FileReader("data/day2.txt"))) {
			while (bin.ready()) {
				levels.clear();
				String line = bin.readLine();
				String[] tokens = line.split(" ");
				for (String s : tokens) {
					levels.add(Integer.valueOf(s));
				}
				if (isSafe(levels, false))
					score++;
			}
		} catch (Exception IOException) {
			System.err.println("IO error while reading");
		}

		System.out.println(score);
	}
}
