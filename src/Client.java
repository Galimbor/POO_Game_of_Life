import java.time.Clock;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class Client {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int gen = Integer.parseInt(sc.nextLine());
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        Board board = new Board(input);
        board.displayGenerations(gen);

    }


}
