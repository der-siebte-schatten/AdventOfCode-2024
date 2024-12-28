import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
	public static void main(String[] args) {
		List<List<String>> crossword = new ArrayList<List<String>>();

		try (BufferedReader bin = new BufferedReader(new FileReader("data/day4.txt"))) {
			while (bin.ready()) {
				String line = bin.readLine();
				List<String> aline = new ArrayList<String>();
				for (int j = 0; j < line.length(); j++) {
					aline.add(line.substring(j, j + 1));
				}
				crossword.add(aline);
			}
		} catch (Exception IOException) {
			System.err.println("IO error while reading");
		}

		int score = 0;
		for (int i = 0; i < crossword.size() - 3; i++) {
			for (int j = 0; j < crossword.get(0).size() - 3; j++) {
				String value = crossword.get(i).get(j)
						+ crossword.get(i).get(j + 1)
						+ crossword.get(i).get(j + 2)
						+ crossword.get(i).get(j + 3);
				if (value.equals("XMAS") || value.equals("SAMX"))
					score++;
				value = crossword.get(i).get(j)
						+ crossword.get(i + 1).get(j)
						+ crossword.get(i + 2).get(j)
						+ crossword.get(i + 3).get(j);
				if (value.equals("XMAS") || value.equals("SAMX"))
					score++;
				value = crossword.get(i).get(j)
						+ crossword.get(i + 1).get(j + 1)
						+ crossword.get(i + 2).get(j + 2)
						+ crossword.get(i + 3).get(j + 3);
				if (value.equals("XMAS") || value.equals("SAMX"))
					score++;
				value = crossword.get(i + 3).get(j)
						+ crossword.get(i + 2).get(j + 1)
						+ crossword.get(i + 1).get(j + 2)
						+ crossword.get(i).get(j + 3);
				if (value.equals("XMAS") || value.equals("SAMX"))
					score++;
			}
		}

		for (int i = crossword.size() - 3; i < crossword.size(); i++) {
			for (int j = 0; j < crossword.get(0).size() - 3; j++) {
				String value = crossword.get(i).get(j)
						+ crossword.get(i).get(j + 1)
						+ crossword.get(i).get(j + 2)
						+ crossword.get(i).get(j + 3);
				if (value.equals("XMAS") || value.equals("SAMX"))
					score++;
			}
		}

		for (int j = crossword.get(0).size() - 3; j < crossword.size(); j++) {
			for (int i = 0; i < crossword.size() - 3; i++) {
				String value = crossword.get(i).get(j)
						+ crossword.get(i + 1).get(j)
						+ crossword.get(i + 2).get(j)
						+ crossword.get(i + 3).get(j);
				if (value.equals("XMAS") || value.equals("SAMX"))
					score++;
			}
		}
		System.out.println(score);

		int score2 = 0;
		for (int i = 1; i < crossword.size() - 1; i++) {
			for (int j = 1; j < crossword.get(0).size() - 1; j++) {
				String value1 = crossword.get(i - 1).get(j - 1)
						+ crossword.get(i).get(j)
						+ crossword.get(i + 1).get(j + 1);
				String value2 = crossword.get(i + 1).get(j - 1)
						+ crossword.get(i).get(j)
						+ crossword.get(i - 1).get(j + 1);
				if ((value1.equals("MAS") || value1.equals("SAM"))
						&& (value2.equals("MAS") || value2.equals("SAM")))
					score2++;
			}
		}
		System.out.println(score2);
	}
}
