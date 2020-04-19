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
        //game.resize(0,0,1,1,game.rows+2,game.columns+2);
        //game.resize(1,1,0,0,game.rows-2,game.columns-2);
        System.out.println(game.toString());
        //resize suprime linha de baixo game.resize(0,0,0,0,game.rows-1,game.columns);
        //resize suprime linha de cima  game.resize(1,0,0,0,game.rows-1,game.columns);
        //resize suprime coluna da direita game.resize(0,0,0,0,game.rows,game.columns-1);
        //resize suprime coluna da esquerda game.resize(0,1,0,0,game.rows,game.columns-1);


    }
}
