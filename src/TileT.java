
/**
 * Author: Akram Hannoufa
 * Revised: Apr 8th, 2021
 * 
 * Description: Tile ADT
 */
package src;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @brief An ADT to represent a Tile in the 2048 game. Each Tile has an int and Color
 * @details Inherits JPanel for building the GUI
 */
public class TileT extends JPanel {
    private int num;
    private Color colour;
    private JLabel numLabel = new JLabel();

   /**
   * @brief Initializes a Tile object.
   * @param tileNum Int, representing the number value of the tile
   * @param tileColor Color of the Tile. Shown on GUI
   * @details Tiles with num of zero, are considered "blank" tiles
   * @throws IllegalArgumentException if tile number is negative
   */
    public TileT(int tileNum, Color tileColor){
        if(tileNum < 0){
            throw new IllegalArgumentException("Tile number must be positive");
        }
        this.num = tileNum;
        this.colour = tileColor;
        if(tileNum!=0){
            this.numLabel.setText(Integer.toString(this.num));
        }
        this.setLayout(new GridBagLayout());
        this.add(this.numLabel);
        this.setBackground(this.colour);
        
    }
   /**
   * @brief Gets the Tile number
   * @returns the Tile's number value
   */
    public int getTileNum(){
        return this.num;
    }

   /**
   * @brief Sets the Tile number
   * @param num Number to set Tile's value to
   * @throws IllegalArgumentException if tile number is negative
   */
    public void setTileNum(int num){
        if(num < 0){
            throw new IllegalArgumentException("Tile number must be positive");
        }
         this.num = num;
         this.numLabel.setText(Integer.toString(this.num));

    }

   /**
   * @brief Sets the Tile number
   * @param col Color to set Tile's colour to
   */
    public void setTileColor(Color col){
        this.colour = col;
   }

   /**
   * @brief Gets the Tile number
   * @returns gets the color of the Tile
   */
  public Color getTileColor(){
    return this.colour;
}

   /**
   * @brief Sets the Tile number
   * @param col Color to set Tile's colour to
   */
  public boolean equals(TileT tile){
    return this.num == tile.getTileNum();
}



}