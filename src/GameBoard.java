/**
 * Author: Akram Hannoufa
 * Revised: Apr 8th, 2021
 * 
 * Description: GameBoard abstract object to hold status of game and game board
 */
package src;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;

/**
 * @brief An Abstract Object to represent and hold the state and status of the Game Board
 * @details Inherits JFrame for building the GUI, implements ActionListener for getting user input
 */
public class GameBoard extends JFrame implements ActionListener{
    public final static int size = 4;
    private static TileT[][] gameGrid = new TileT[4][4];
    private static JPanel gameZone = new JPanel();
    private static JPanel bottomZone = new JPanel();
    private static JPanel buttonZone = new JPanel();
    private static JLabel scoreText = new JLabel("Score: 0");
    private static int score = 0;
    private static GridLayout gameLayout = new GridLayout(4,4);
    private static GridLayout bottomLayout = new GridLayout(1,2);

    private static GridLayout buttonLayout = new GridLayout(3,3);
    private static Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
    private static HashMap<Integer,Color> colours = new HashMap<Integer,Color>();
    private static JButton upButton = new JButton("U");
    private static JButton downButton = new JButton("D");

    private static JButton leftButton = new JButton("L");
    private static JButton rightButton = new JButton("R");
    private static boolean gameContinue = true;

   /**
   * @brief Initializes the GameBoard abstract object.
   * @details The main GUI is built within this method, and inital game setup
   */
    public GameBoard(){
        initBoard();
        setTitle("Budget 2048");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        gameZone.setPreferredSize(new Dimension(500, 350));
        gameZone.setBorder(border);

        bottomLayout.setVgap(30);

        bottomZone.setLayout(bottomLayout);
        scoreText.setForeground(Color.BLACK);
        scoreText.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        bottomZone.add(scoreText);
        bottomZone.setBorder(border);

        buttonZone.setLayout(buttonLayout); 
        buttonZone.add(new JLabel());
        buttonZone.add(upButton);
        buttonZone.add(new JLabel());
        buttonZone.add(leftButton);
        buttonZone.add(new JLabel());
        buttonZone.add(rightButton);
        buttonZone.add(new JLabel());
        buttonZone.add(downButton);
        buttonZone.add(new JLabel());
        
        bottomZone.add(buttonZone);

        upButton.addActionListener(this);
        downButton.addActionListener(this);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        add(gameZone, BorderLayout.NORTH);
        add(bottomZone, BorderLayout.CENTER);
        setVisible(true);
        
    }

   /**
   * @brief Sets the game status 
   * @param cont Boolean, representing whether the game should continue or end
   */
    public static void setGameContinue(boolean cont){
        gameContinue = cont;
    }

   /**
   * @brief Sets the current game score 
   * @param newScore Int, representing the new game score after a move
   */
    public static void updateScore(int newScore){
        score = newScore;
        scoreText.setText("Score: "+score);
    } 

    /**
   * @brief Ends the current game, shows score, and closes window
   */
    public void endGame(){
        gameZone.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Game over! Final score: " + score, "Game Over!", JOptionPane.PLAIN_MESSAGE);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
      
    }
    
    /**
   * @brief Gets user input from the button GUI
   * @param e ActionEvent, holds the most recent button clicked (the user's move)
   */
   public void actionPerformed(ActionEvent e){
            String action = e.getActionCommand();

            if(action == "U"){
                gameGrid = MoveHandler.processMove(gameGrid, Directions.UP, score);
                redrawBoard();
                if(!gameContinue){
                    endGame();
                }
            }
            else if(action == "D"){
                gameGrid = MoveHandler.processMove(gameGrid, Directions.DOWN, score);
                redrawBoard();
                if(!gameContinue){
                    endGame();
                }

            }
            else if(action == "R"){
                gameGrid = MoveHandler.processMove(gameGrid, Directions.RIGHT, score);
                redrawBoard();
                if(!gameContinue){
                    endGame();
                }

            }
            else if(action == "L"){
                gameGrid = MoveHandler.processMove(gameGrid, Directions.LEFT, score);
                redrawBoard();
                if(!gameContinue){
                    endGame();
                }
            }
   }

   /**
   * @brief Generates a new TileT with either 2 or 4 value 
   * @returns TileT with either a 2 or 4 value
   * @details 90% chance of generation TileT with 2 value and 10% for TileT with 4 value
   */
    public static TileT generateRandomTile(){
        int random_int = (int)Math.floor(Math.random()*(9-0+1)+0);
        int choice =0;
         if(random_int!=0){
             choice = 2;
             
         }
         else{
             choice = 4;
             
         }
        return new TileT(choice, colours.get(choice));
    }

  /**
   * @brief Generates a new TileT with passed in int value
   * @param num, Int for the new TileT value
   * @returns TileT with new/retrieved colour and number
   * @details generates new colour for TileT number, if number doesn't already have a colour value
   */
    public static TileT makeNewTile(int num){
        if(colours.keySet().contains(num)){
            return new TileT(num, colours.get(num));
        }
        else{
            Color temp = new Color((int)(Math.random() * 0x1000000));
            colours.put(num,temp.brighter());
            return new TileT(num, colours.get(num));
        }
    }

   /**
   * @brief Generates a random position on the gameboard
   * @returns int array with an x and y coord
   * @details Generates 2 ints between 0-3 (inclusive) to represent a position on the 4x4 gameboard
   */
    public static int[] generateRandomPos(){
        int random_int_x = (int)Math.floor(Math.random()*((size-1)-0+1)+0);
        int random_int_y = (int)Math.floor(Math.random()*((size-1)-0+1)+0);

        return new int[]{random_int_x,random_int_y};
    }

   /**
   * @brief Initializes the values of the GameBoard, and adds the 2 initial Tiles
   * @details All Tiles except 2 are set to null (ie. 0 value TileT)
   */
    private static void initBoard(){
        colours.put(0, Color.LIGHT_GRAY);
        for(int i =0; i < size; i ++){
            for(int j =0; j <size; j++){
                gameGrid[i][j] = makeNewTile(0);
                gameZone.add(gameGrid[i][j]);
            }
        }
        colours.put(2,Color.GREEN);
        colours.put(4, Color.ORANGE);
        int[] randPos = generateRandomPos();
        gameGrid[randPos[0]][randPos[1]] = generateRandomTile();
        
        
        int[] randPos2 = generateRandomPos();
        
        while(Arrays.equals(randPos, randPos2)){
            randPos2 = generateRandomPos();
        }
    
        gameGrid[randPos2[0]][randPos2[1]] = generateRandomTile();
        
        redrawBoard();
    }
   /**
   * @brief Redraws the GUI after a move is processed
   * @details Re-adds each TileT (JPanel) to the main GameBoard
   */
    private static void redrawBoard(){
        gameZone.removeAll();
        gameLayout.setHgap(10);
        gameLayout.setVgap(10);
        gameZone.setLayout(gameLayout);
        for(int i =0; i < size; i ++){
            for(int j =0; j <size; j++){
                gameZone.add(gameGrid[i][j]);
            }
        }
    
        gameZone.revalidate();
        gameZone.repaint();
    }
    
}