/* This class should implement the DisplayableMaze interface */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze implements DisplayableMaze {

  //instance variables
  private int height;
  private int width;
  private MazeContents[][] mazeGrid;
  private MazeLocation start;
  private MazeLocation finish; 

  public Maze(){
      //default constructor
  }

  public Maze(String filename) {
    readFromFile(filename);
}

  private void readFromFile(String filename) {
    try {
        Scanner file = new Scanner(new File(filename));
        
        // First, read all lines to get dimensions
        java.util.List<String> lines = new java.util.ArrayList<>();
        while (file.hasNextLine()) {
            lines.add(file.nextLine());
        }
        file.close();
        
        height = lines.size();
        width = lines.get(0).length(); // assume all lines same length
        mazeGrid = new MazeContents[height][width];
        
        for (int i = 0; i < height; i++) {
            String line = lines.get(i);
            for (int j = 0; j < width; j++) {
                char c = line.charAt(j);
                switch (c) {
                    case '#':
                        mazeGrid[i][j] = MazeContents.WALL;
                        break;
                    case '.':
                    case ' ':
                        mazeGrid[i][j] = MazeContents.OPEN;
                        break;
                    case 'S':
                        start = new MazeLocation(i,j);
                        mazeGrid[i][j] = MazeContents.OPEN; // treat start as open
                        break;
                    case 'F':
                        finish = new MazeLocation(i,j);
                        mazeGrid[i][j] = MazeContents.OPEN; // treat finish as open
                        break;
                    default:
                        mazeGrid[i][j] = MazeContents.WALL; // fallback
                }
            }
        }
        
    } catch (FileNotFoundException e) {
        System.err.println("Maze file not found: " + filename);
        System.exit(-1);
    }
}


  //making stubs 
  //fill in stubs
  @Override
  public int getHeight(){
      return height;
  }

  @Override
  public int getWidth(){
      return width;
  }

  @Override
  public MazeContents getContents(int i, int j){
      return mazeGrid[i][j];
  }
   
  @Override
  public MazeLocation getStart(){
      return start;
  }

  @Override
  public MazeLocation getFinish(){
      return finish;
  }

  @Override
  public Boolean checkExplorable(int i, int j) {
        // out of bounds = not explorable
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return false;
        }
        return mazeGrid[i][j].isExplorable;
    }

    /** This DemoMaze method will allow you to generate a simple maze
     * to test your code on as you develop it. Ultimately, you need
     * to accept maze files as command line inputs or standard input.
     * You will need to implement the DisplayableMaze interface before you
     * can run the initDemoMaze method.
     * * @author Tianah Gooden
     * * @version October 17th 2023
     */
    public void initDemoMaze(){ //String fileName, 
        this.height = 10;
        this.width = 8;
        this.mazeGrid = new MazeContents[height][width];
        this.start = new MazeLocation(1,1);
        this.finish = new MazeLocation(8,6);

        this.mazeGrid[0][0] = MazeContents.WALL; this.mazeGrid[0][1] = MazeContents.WALL; this.mazeGrid[0][2] = MazeContents.WALL; this.mazeGrid[0][3] = MazeContents.WALL; this.mazeGrid[0][4] = MazeContents.WALL; this.mazeGrid[0][5] = MazeContents.WALL; this.mazeGrid[0][6] = MazeContents.WALL; this.mazeGrid[0][7] = MazeContents.WALL;
        this.mazeGrid[1][0] = MazeContents.WALL; this.mazeGrid[1][1] = MazeContents.OPEN; this.mazeGrid[1][2] = MazeContents.OPEN; this.mazeGrid[1][3] = MazeContents.OPEN; this.mazeGrid[1][4] = MazeContents.OPEN; this.mazeGrid[1][5] = MazeContents.OPEN; this.mazeGrid[1][6] = MazeContents.WALL; this.mazeGrid[1][7] = MazeContents.WALL;
        this.mazeGrid[2][0] = MazeContents.WALL; this.mazeGrid[2][1] = MazeContents.WALL; this.mazeGrid[2][2] = MazeContents.OPEN; this.mazeGrid[2][3] = MazeContents.WALL; this.mazeGrid[2][4] = MazeContents.WALL; this.mazeGrid[2][5] = MazeContents.OPEN; this.mazeGrid[2][6] = MazeContents.WALL; this.mazeGrid[2][7] = MazeContents.WALL;
        this.mazeGrid[3][0] = MazeContents.WALL; this.mazeGrid[3][1] = MazeContents.OPEN; this.mazeGrid[3][2] = MazeContents.WALL; this.mazeGrid[3][3] = MazeContents.OPEN; this.mazeGrid[3][4] = MazeContents.OPEN; this.mazeGrid[3][5] = MazeContents.OPEN; this.mazeGrid[3][6] = MazeContents.WALL; this.mazeGrid[3][7] = MazeContents.WALL;
        this.mazeGrid[4][0] = MazeContents.WALL; this.mazeGrid[4][1] = MazeContents.OPEN; this.mazeGrid[4][2] = MazeContents.OPEN; this.mazeGrid[4][3] = MazeContents.OPEN; this.mazeGrid[4][4] = MazeContents.WALL; this.mazeGrid[4][5] = MazeContents.WALL; this.mazeGrid[4][6] = MazeContents.OPEN; this.mazeGrid[4][7] = MazeContents.WALL;
        this.mazeGrid[5][0] = MazeContents.WALL; this.mazeGrid[5][1] = MazeContents.OPEN; this.mazeGrid[5][2] = MazeContents.WALL; this.mazeGrid[5][3] = MazeContents.OPEN; this.mazeGrid[5][4] = MazeContents.OPEN; this.mazeGrid[5][5] = MazeContents.WALL; this.mazeGrid[5][6] = MazeContents.WALL; this.mazeGrid[5][7] = MazeContents.WALL;
        this.mazeGrid[6][0] = MazeContents.WALL; this.mazeGrid[6][1] = MazeContents.OPEN; this.mazeGrid[6][2] = MazeContents.WALL; this.mazeGrid[6][3] = MazeContents.WALL; this.mazeGrid[6][4] = MazeContents.OPEN; this.mazeGrid[6][5] = MazeContents.OPEN; this.mazeGrid[6][6] = MazeContents.OPEN; this.mazeGrid[6][7] = MazeContents.WALL;
        this.mazeGrid[7][0] = MazeContents.WALL; this.mazeGrid[7][1] = MazeContents.OPEN; this.mazeGrid[7][2] = MazeContents.WALL; this.mazeGrid[7][3] = MazeContents.OPEN; this.mazeGrid[7][4] = MazeContents.OPEN; this.mazeGrid[7][5] = MazeContents.WALL; this.mazeGrid[7][6] = MazeContents.OPEN; this.mazeGrid[7][7] = MazeContents.WALL;
        this.mazeGrid[8][0] = MazeContents.WALL; this.mazeGrid[8][1] = MazeContents.OPEN; this.mazeGrid[8][2] = MazeContents.OPEN; this.mazeGrid[8][3] = MazeContents.WALL; this.mazeGrid[8][4] = MazeContents.OPEN; this.mazeGrid[8][5] = MazeContents.WALL; this.mazeGrid[8][6] = MazeContents.OPEN; this.mazeGrid[8][7] = MazeContents.WALL;
        this.mazeGrid[9][0] = MazeContents.WALL; this.mazeGrid[9][1] = MazeContents.WALL; this.mazeGrid[9][2] = MazeContents.WALL; this.mazeGrid[9][3] = MazeContents.WALL; this.mazeGrid[9][4] = MazeContents.WALL; this.mazeGrid[9][5] = MazeContents.WALL; this.mazeGrid[9][6] = MazeContents.WALL; this.mazeGrid[9][7] = MazeContents.WALL;
  }

  public void markVisited(MazeLocation loc) {
    mazeGrid[loc.getRow()][loc.getCol()] = MazeContents.VISITED;
}

    public void markDeadEnd(MazeLocation loc) {
        mazeGrid[loc.getRow()][loc.getCol()] = MazeContents.DEAD_END;
    }

    public void markPath(MazeLocation loc) {
        mazeGrid[loc.getRow()][loc.getCol()] = MazeContents.PATH;
    }

  public void loadFromScanner(Scanner s) {
    this.height = s.nextInt();
    this.width = s.nextInt();
    this.start = new MazeLocation(s.nextInt(), s.nextInt());
    this.finish = new MazeLocation(s.nextInt(), s.nextInt());

    mazeGrid = new MazeContents[height][width];

    for (int r = 0; r < height; r++) {
        String line = s.next();
        for (int c = 0; c < width; c++) {
            char ch = line.charAt(c);
            switch (ch) {
                case '#': mazeGrid[r][c] = MazeContents.WALL; break;
                case '.': mazeGrid[r][c] = MazeContents.OPEN; break;
                default: mazeGrid[r][c] = MazeContents.WALL;
            }
        }
    }
}

}
