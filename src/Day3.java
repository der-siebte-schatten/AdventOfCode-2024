import java.io.BufferedReader;
import java.io.FileReader;

public class Day3 {
	public static void main(String[] args) {
		String memory = "";
		try (BufferedReader bin = new BufferedReader(new FileReader("data/day3.txt"))) {
			while (bin.ready()) {
				String line = bin.readLine();
				memory = memory.concat(line);
			}
		} catch (Exception IOException) {
			System.err.println("IO error while reading");
		}

		int result = 0;
		int enabler = 1;
		while (true) {
			if (memory.indexOf('m') == -1)
				break;
			if (memory.indexOf(')') == -1)
				break;
			if (memory.indexOf('m') < memory.indexOf('d') || memory.indexOf('d') < 0) {
				memory = memory.substring(memory.indexOf('m'));
				String command = memory.substring(0, memory.indexOf(')') + 1);
				if (command.matches("mul(.[0-9]*,.[0-9]*).")) {
					System.out.println("Found command " + command);
					int a = Integer.parseInt(command.substring(command.indexOf('(') + 1, command.indexOf(',')));
					int b = Integer.parseInt(command.substring(command.indexOf(',') + 1, command.indexOf(')')));
					result += enabler * a * b;
				} else
					System.err.println("Corrupt command " + command + ", ignoring");
				memory = memory.substring(1);
			} else {
				memory = memory.substring(memory.indexOf('d'));
				String command = memory.substring(0, memory.indexOf(')') + 1);
				if (command.matches("do(.).")) {
					System.out.println("Found command " + command);
					System.out.println("INFO: Enabling multiplier");
					enabler = 1;
				} else if (command.matches("don't(.).")) {
					System.out.println("Found command " + command);
					System.out.println("INFO: Disabling multiplier");
					enabler = 0;
				} else
					System.err.println("Corrupt command " + command + ", ignoring");
				memory = memory.substring(1);
			}
		}
		System.out.println(result);
	}
}
