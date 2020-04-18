import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        BidimensionalMatrix<Integer> game = new BidimensionalMatrix<>(input);
        System.out.print(game.toString());
        System.out.println("----------------------------");
        game.getNextGeneration();
        System.out.println(game.toString());

    }
}
