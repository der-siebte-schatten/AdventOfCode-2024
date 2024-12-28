import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day6 {
    static final int N = 0, E = 1, S = 2, W = 3;

    public static void main(String[] args) throws UnexpectedException {
        ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();
        int x = -1, y = -1, direction = -1;
        try (BufferedReader bin = new BufferedReader(new FileReader("data/day6.txt"))) {
            while (bin.ready()) {
                String s = bin.readLine();
                ArrayList<Character> line = new ArrayList<Character>();
                for (int i = 0; i < s.length(); i++) {
                    line.add(s.charAt(i));
                    if (s.charAt(i) == '^') {
                        x = i;
                        y = map.size();
                        direction = N;
                    }
                    if (s.charAt(i) == '>') {
                        x = i;
                        y = map.size();
                        direction = E;
                    }
                    if (s.charAt(i) == 'v') {
                        x = i;
                        y = map.size();
                        direction = S;
                    }
                    if (s.charAt(i) == '<') {
                        x = i;
                        y = map.size();
                        direction = W;
                    }
                }
                map.add(line);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }

        // Saving this for later (part 2)
        int x0 = x, y0 = y, d0 = direction;

        int score = 1;
        map.get(y).remove(x);
        map.get(y).add(x, 'X');
        while (true) {
            try {
                switch (direction) {
                    case N:
                        if (map.get(y - 1).get(x) == '#') {
                            System.err.println("Bonk");
                            direction = E;
                            continue;
                        }
                        y--;
                        break;
                    case E:
                        if (map.get(y).get(x + 1) == '#') {
                            System.err.println("Bonk");
                            direction = S;
                            continue;
                        }
                        x++;
                        break;
                    case S:
                        if (map.get(y + 1).get(x) == '#') {
                            System.err.println("Bonk");
                            direction = W;
                            continue;
                        }
                        y++;
                        break;
                    case W:
                        if (map.get(y).get(x - 1) == '#') {
                            System.err.println("Bonk");
                            direction = N;
                            continue;
                        }
                        x--;
                        break;
                    default:
                        throw new UnexpectedException("Bad direction!");
                }
            } catch (IndexOutOfBoundsException e) {
                System.err.println("I'm outta here!");
                break;
            }

            if (map.get(y).get(x) == '.') {
                System.out.println("Oh, that's new!");
                score++;
                map.get(y).remove(x);
                map.get(y).add(x, 'X');
            } else if (map.get(y).get(x) == 'X')
                System.out.println("Hmm, I've been there already...");

            else if (map.get(y).get(x) == '#')
                throw new UnexpectedException("You're not supposed to be here!");
            else
                throw new UnexpectedException("Who spilled coffee on my map?!?");
        }
        System.out.println(score);

        System.out.println("\nOkay, let's block this guy");
        int score2 = 0;
        map.get(y0).remove(x0);
        map.get(y0).add(x0, '^');
        ArrayList<int[]> bonks = new ArrayList<int[]>();
        for (ArrayList<Character> line : map) {
            for (int i = 0; i < line.size(); i++) {
                if (line.get(i) != 'X')
                    // He wasn't here, let's not bother trying
                    continue;

                line.remove(i);
                line.add(i, '#');
                x = x0;
                y = y0;
                direction = d0;
                boolean loop = false;
                try {
                    while (!loop) {
                        switch (direction) {
                            case N:
                                if (map.get(y - 1).get(x) == '#') {
                                    System.err.println("Bonk");
                                    for (int[] js : bonks) {
                                        if (Arrays.equals(js, new int[] { x, y, direction }))
                                            loop = true;
                                    }
                                    bonks.add(new int[] { x, y, direction });
                                    direction = E;
                                    continue;
                                }
                                y--;
                                break;
                            case E:
                                if (map.get(y).get(x + 1) == '#') {
                                    System.err.println("Bonk");
                                    for (int[] js : bonks) {
                                        if (Arrays.equals(js, new int[] { x, y, direction }))
                                            loop = true;
                                    }
                                    bonks.add(new int[] { x, y, direction });
                                    direction = S;
                                    continue;
                                }
                                x++;
                                break;
                            case S:
                                if (map.get(y + 1).get(x) == '#') {
                                    System.err.println("Bonk");
                                    for (int[] js : bonks) {
                                        if (Arrays.equals(js, new int[] { x, y, direction }))
                                            loop = true;
                                    }
                                    bonks.add(new int[] { x, y, direction });
                                    direction = W;
                                    continue;
                                }
                                y++;
                                break;
                            case W:
                                if (map.get(y).get(x - 1) == '#') {
                                    System.err.println("Bonk");
                                    for (int[] js : bonks) {
                                        if (Arrays.equals(js, new int[] { x, y, direction }))
                                            loop = true;
                                    }
                                    bonks.add(new int[] { x, y, direction });
                                    direction = N;
                                    continue;
                                }
                                x--;
                                break;
                            default:
                                throw new UnexpectedException("Bad direction!");
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("He's outta here, it didn't work!");
                    continue;
                } finally {
                    line.remove(i);
                    line.add(i, 'X');
                    bonks.clear();
                }
                System.err.println("Hey! I've seen this wall already!");
                score2++;
            }
        }
        System.out.println(score2);
    }
}
