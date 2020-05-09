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
        /*
        SparseMatrix<LivingCell> game = new SparseMatrix<>(input);
//        game.showNextGenerations(gen);
        ArrayList<LivingCell> column1 = (ArrayList<LivingCell>)game.getWholeRow(1);
        System.out.println(column1);
        */
        Board GoL = new Board(input);
        GoL.remove(0,1);
        GoL.displayBoard();
        //GoL.displayGeneration(gen);

    }


}
