/**
 * Author: Akram Hannoufa
 * Revised: Apr 8th, 2021
 * 
 * Description: MoveHandler Unit Tests
 */
package src;
import org.junit.*;

import static org.junit.Assert.*;

import java.awt.Color;

public class TestMoveHandler
//too many permutations to test via unit testing ---> mainly tested with the GUI/playing game
//GameBoard also tested via GUI
{
   private TileT[][] fakeBoard;
   private int score;
    @Before
    public void setUp(){
        fakeBoard = new TileT[4][4];
        score =0;
        for(int i =0; i < 4; i ++){
            for(int j =0; j<4;j++){
                fakeBoard[i][j] = new TileT(0, Color.LIGHT_GRAY);
            }
        }
        
    }

    @After
    public void tearDown(){
      fakeBoard = null;
      score =0;
    }

    @Test
    public void testLeftMove1()
    {
        fakeBoard[3][3] = new TileT(2, Color.PINK);
        MoveHandler.processMove(fakeBoard, Directions.LEFT, score);
        assertTrue(fakeBoard[3][0].equals(new TileT(2, Color.PINK))&& fakeBoard[3][3].equals(new TileT(0, Color.LIGHT_GRAY)));
    }

    @Test
    public void testLeftMove2()
    {
        fakeBoard[3][3] = new TileT(2, Color.PINK);
        fakeBoard[3][2] = new TileT(2, Color.PINK);
        MoveHandler.processMove(fakeBoard, Directions.LEFT, score);
        assertTrue(fakeBoard[3][0].equals(new TileT(4, Color.PINK))&& fakeBoard[3][3].equals(fakeBoard[3][2]));
    }

    @Test
    public void testRightMove1()
    {
        fakeBoard[0][0] = new TileT(2, Color.PINK);
        MoveHandler.processMove(fakeBoard, Directions.RIGHT, score);
        assertTrue(fakeBoard[0][3].equals(new TileT(2, Color.PINK))&& fakeBoard[0][0].equals(new TileT(0, Color.LIGHT_GRAY)));
    }

    @Test
    public void testRightMove2()
    {
        fakeBoard[3][3] = new TileT(2, Color.PINK);
        fakeBoard[3][2] = new TileT(2, Color.PINK);
        MoveHandler.processMove(fakeBoard, Directions.RIGHT, score);
        assertTrue(fakeBoard[3][3].equals(new TileT(4, Color.PINK))&& fakeBoard[3][1].equals(fakeBoard[3][2]));
    }

    @Test
    public void testUpMove1()
    {
        fakeBoard[3][1] = new TileT(2, Color.PINK);
        MoveHandler.processMove(fakeBoard, Directions.UP, score);
        assertTrue(fakeBoard[0][1].equals(new TileT(2, Color.PINK))&& fakeBoard[3][1].equals(new TileT(0, Color.LIGHT_GRAY)));
    }

    @Test
    public void testUpMove2()
    {
        fakeBoard[3][3] = new TileT(2, Color.PINK);
        fakeBoard[2][3] = new TileT(2, Color.PINK);
        MoveHandler.processMove(fakeBoard, Directions.UP, score);
        assertTrue(fakeBoard[0][3].equals(new TileT(4, Color.PINK))&& fakeBoard[3][3].equals(fakeBoard[2][3]));
    }

    @Test
    public void testDownMove1()
    {
        fakeBoard[0][1] = new TileT(2, Color.PINK);
        MoveHandler.processMove(fakeBoard, Directions.DOWN, score);
        assertTrue(fakeBoard[3][1].equals(new TileT(2, Color.PINK))&& fakeBoard[0][1].equals(new TileT(0, Color.LIGHT_GRAY)));
    }

    @Test
    public void testDownMove2()
    {
        fakeBoard[1][3] = new TileT(2, Color.PINK);
        fakeBoard[2][3] = new TileT(2, Color.PINK);
        MoveHandler.processMove(fakeBoard, Directions.DOWN, score);
        assertTrue(fakeBoard[3][3].equals(new TileT(4, Color.PINK))&& fakeBoard[1][3].equals(fakeBoard[2][3]));
    }

    
}
