package GUI;

import java.util.ArrayList;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class View extends JFrame {

  // creating maze
  private int [][] maze={{1,1,1,1,1,1,1,1,1,1,1,1,1},
                         {1,0,1,0,1,0,1,0,0,0,0,0,1},
                         {1,0,1,0,0,0,1,0,1,1,1,0,1},
                         {1,0,1,1,1,1,1,0,0,0,0,0,1},
                         {1,0,0,1,0,0,0,0,1,1,1,0,1},
                         {1,0,1,0,1,1,1,0,1,0,0,0,1},
                         {1,0,1,0,1,0,0,0,1,1,1,0,1},
                         {1,0,1,0,1,1,1,0,1,1,1,0,1},
                         {1,0,0,0,0,0,0,0,0,1,1,9,1},
                         {1,1,1,1,1,1,1,1,1,1,1,1,1}};

  // list for shortest path
  public ArrayList<Integer> list=new ArrayList<>();

Dimension size;
  View()
  {
    // getting screen size

    size=new Dimension(Toolkit.getDefaultToolkit().getScreenSize());

    //creating window
    this.setTitle("Maze_Solver");
    this.setSize(size);
    this.setBackground(Color.yellow);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //finding shortest path
    Dfs.searchPath(maze, 1, 1,list);
    System.out.println(list);
  }

  @Override
  public void paint(Graphics g)
  {
    // setting window background color

    g.setColor(Color.MAGENTA);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.translate(450,150);
    for(int i=0;i<maze.length;i++)
    {
      for(int j=0;j<maze[0].length;j++)
      {
        // setting cell color according to cell value

        Color color;
        switch(maze[i][j])
        {
          case 1:color=Color.black;
                break;
          case 9:color=Color.red;
                break;
          default:color=Color.GRAY
          ;
                break;
        }

        
        //coloring the maze
        g.setColor(color);
        g.fillRect(50*j, 50*i, 50,50);
        
        // making grid over maze
        g.setColor(Color.WHITE);
        g.drawRect(50*j, 50*i, 50, 50);
      }
    }


    // coloring the shortest path
    for(int i=0;i<list.size();i+=2)
    {
      int cx=list.get(i);
      int cy=list.get(i+1);

      g.setColor(Color.green);
      g.fillOval(50*cy,50*cx,50,50);
      
    }

  }

  public static void main(String[] args) {
    View view=new View();
    view.setVisible(true);
  }
    
}
