package GUI;

import java.util.ArrayList;

public class Dfs {
    
    public static boolean searchPath(int[][]maze,int x,int y,ArrayList<Integer> list)
    {
       // reach destination
       if(maze[x][y]==9)
       {
        list.add(x);
        list.add(y);
        return true;
       }
       // Valid cell position
       if(maze[x][y]==0)
       {
         //mark
         maze[x][y]=2;

         int dx=0;
         int dy=1;

         if(searchPath(maze,x+dx,y+dy,list))  //moving right
         {
            list.add(x);
            list.add(y);
            return true;
         }

         dx=1;
         dy=0;
         if(searchPath(maze,x+dx,y+dy,list)) // moving down
         {
            list.add(x);
            list.add(y);
            return true;
         }
         dx=0;
         dy=-1;
         if(searchPath(maze,x+dx,y+dy,list)) //moving left
         {
            list.add(x);
            list.add(y);
            return true;
         }
         dx=-1;
         dy=0;
         if(searchPath(maze,x+dx,y+dy,list)) // moving up
         {
            list.add(x);
            list.add(y);
            return true;
         }

       }

       
       return false;
        
    }
}
