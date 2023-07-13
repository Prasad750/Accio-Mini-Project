package GUI;

import javax.swing.JFrame;

public class SnakeGame extends JFrame
{
    Board board;
   SnakeGame()
   {
    board=new Board();
    this.setTitle("Sanke_Game");
    this.add(board);
    this.pack();
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

   }

    public static void main(String[] args) {
        SnakeGame sg=new SnakeGame();
    }
}