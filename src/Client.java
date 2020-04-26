import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       // int gen = Integer.parseInt(sc.nextLine());
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
//        BidimensionalMatrix<Integer> game = new BidimensionalMatrix<>(input);
//        System.out.print(game.toString());
//        System.out.println("----------------------------");
//        for (int i = 0; i < gen; i++) {
//            game.getNextGeneration();
//            System.out.println(game.toString());
//        }
        SparseMatrix game = new SparseMatrix(input);

        Iterator<SentinelLL.SentinelNode> test = game.getMatrix().iterator();
        while(test.hasNext())
        {

            SentinelLL.SentinelNode currentSentinel = test.next();
            Node token = currentSentinel;
            while(token.east != currentSentinel ) {
                System.out.print(" " + token.east + " ");
                token = token.east;
            }
            System.out.println();
        }

        //game.resize(1,1,0,0,game.rows-2,game.columns-2);
        //game.resize(1,1,0,0,game.rows-2,game.columns-2);
        //resize suprime linha de baixo game.resize(0,0,0,0,game.rows-1,game.columns);
        //resize suprime linha de cima  game.resize(1,0,0,0,game.rows-1,game.columns);
        //resize suprime coluna da direita game.resize(0,0,0,0,game.rows,game.columns-1);
        //resize suprime coluna da esquerda game.resize(0,1,0,0,game.rows,game.columns-1);


    }
}
