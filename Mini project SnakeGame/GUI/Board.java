package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener{
   // panel dimension
    int B_width=400;
    int B_Height=400;

    // dimension of  n snake

    int mDots=1600;
    int dotSize=10;
    int dots=3;
   
    int appleX,appleY;

    //Arrays for sstoring snake dots location
    int x[]=new int[mDots];
    int y[]=new int[mDots];

    Image body,head,apple;
   
    //timer 
    Timer timer;
    int delay=350;

    boolean left=true;
    boolean right=false;
    boolean up=false;
    boolean down=false;

    //help to game over
    boolean inGame=true;

    //Score
    int score=0;

    
    Board()
    {
        Tadapter tadapter=new Tadapter();
        this.addKeyListener(tadapter);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(B_Height,B_width));
        this.setBackground(Color.BLUE);
        initGame();
        loadImage();
    }

    public void initGame()
    {

        //Initialize Snake location
        dots=3;
        x[0]=50;
        y[0]=50;
        for(int i=1;i<dots;i++)
        {
            x[i]=x[0]+(dotSize*i);
            y[i]=y[0];
        }

        //randomize food locaton
        randomizeApple();
       

        // time helps to take actionEvent  
        timer=new Timer(delay,this);
        timer.start();
       
    }
 
    // load images form folder to image obj
    public void loadImage()
    {
        ImageIcon bodyIcon=new ImageIcon("GUI/resources/dot.png");
        body=bodyIcon.getImage();

        ImageIcon headIcon=new ImageIcon("GUI/resources/head.png");
        head=headIcon.getImage();

        ImageIcon appleIcon=new ImageIcon("GUI/resources/apple.png");
        apple=appleIcon.getImage();
    }

    // to draw images in place of snake and appple location
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        doDrawing(g);

    }


    // drawing loaded images to its position
    public void doDrawing(Graphics g)
    {

        if(inGame)
        {
            g.drawImage(apple, appleX, appleY, this);
            displayScore(g);
    
            for(int i=0;i<dots;i++)
            {
                if(i==0)
                {
                    g.drawImage(head, x[0], y[0], this);
                }
                else
                {
                    g.drawImage(body, x[i], y[i], this);
                }
            }
           

        }
        else
        {
            gameOver(g);
            timer.stop();
        }
    }

    public void displayScore(Graphics g)
    {
        String s="Score:"+Integer.toString(score);
        Font f=new Font("Helvitica", Font.BOLD, 14);
        FontMetrics fm=getFontMetrics(f);
        g.setColor(Color.white);
        g.setFont(f);
        g.drawString(s,B_width-fm.stringWidth(s)-50,20);
    }

    //game Over msg display

    public void gameOver(Graphics g)
    {
        String msg="Game Over";
        // int score=(dots-3)*10;
        String smsg= "Score :"+Integer.toString(score);
        Font small=new Font("Helvitica",Font.BOLD,14);
        FontMetrics fontMatrics=getFontMetrics(small); //get dimensions of font

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg,(B_width-fontMatrics.stringWidth(msg))/2,(B_Height/2)-10);
        g.drawString(smsg,(B_width-fontMatrics.stringWidth(smsg))/2,(B_Height/2)+10);

    }


    //making apple to apper random position
    public void randomizeApple()
    {
        appleX=((int)(Math.random()*39))*dotSize;
        appleY=((int)(Math.random()*39))*dotSize;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(inGame)
        {
            move();
            checkCollision();
            eatApple();
        }
            repaint();
    }


    //moving or chaniging  Snake direction
    public void move()
    {
        for(int i=dots-1;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }

        if(left)
        {
            x[0]=x[0]-dotSize;
        }
        else if(right)
        { 
            x[0]=x[0]+dotSize;
        }
        else if(up)
        {
            y[0]-=dotSize;
        }
        else
        {
            y[0]+=dotSize;
        }


    }

    public void eatApple()
    { 
        if(appleX==x[0] && appleY==y[0])
        {
            dots++;
            score=(dots-3)*10;
            randomizeApple();
        }
    }

    public void checkCollision()
    {
        for(int i=1;i<dots;i++)
        {
            if(i>=4 && x[0]==x[i] && y[0]==y[i])
            {
                inGame=false;
            }
        }

        if(x[0]<0 || y[0]<0 || x[0]>B_width || y[0]>B_Height)
        {
            inGame=false;
        }
    }


    // class for  controlling snake
    class Tadapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent keyEvent)
        {
            int key=keyEvent.getKeyCode();

            if(key==KeyEvent.VK_LEFT && !right)
            {
                left=true;
                up=false;
                down=false;
            }

            if(key==KeyEvent.VK_RIGHT && !left)
            {
                right=true;
                up=false;
                down=false;
            }

            if(key==KeyEvent.VK_UP && !down)
            {
                up=true;
                left=false;
                right=false;
            }
            if(key== KeyEvent.VK_DOWN && !up)
            {
                down=true;
                left=false;
                right=false;
            }

            if(key==KeyEvent.VK_SPACE && !inGame) // to reset game 
            {
               inGame=true;
               initGame();
            }
        }
    }

    
}
