import java.io.*;
import java.util.Scanner;

class SolveMaze {

  public static Scanner readMaze(String fname){
    Scanner file = null;
    try {
      file = new Scanner(new File(fname));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);  
    }
    return file;
  }
  
  public static void main(String[] args) {
    if(args.length <= 0){
      System.err.println("Please provide the name of the maze file.");
      System.exit(-1);
    }
    Scanner file = readMaze(args[0]);
    
    Maze maze = new Maze();
    MazeViewer viewer = new MazeViewer(maze);
  }

  //goals
  //Write recursive DFS maze solver
  // Mark VISITED / PATH / DEAD_END
  // Animate it use DFS
   public static boolean dfs(Maze maze, MazeLocation loc) {

        // base cases:
        if (loc.equals(maze.getFinish())) {
            maze.markPath(loc);
            return true;
        }

        // if not explorable, stop
        if (!maze.checkExplorable(loc.getRow(), loc.getCol())) {
            return false;
        }

        // mark visited
        maze.markVisited(loc);

        // try all directions
        for (MazeDirection dir : MazeDirection.values()) {
            MazeLocation next = loc.neighbor(dir);

            if (dfs(maze, next)) {
                maze.markPath(loc);    // part of solution
                return true;
            }
        }

        // dead end
        maze.markDeadEnd(loc);
        return false;
    }




}
