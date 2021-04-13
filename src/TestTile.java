
/**
 * Author: Akram Hannoufa
 * Revised: Apr 8th, 2021
 * 
 * Description: TileT Unit Tests
 */
package src;
import static org.junit.Assert.*;

import org.junit.*;
import java.awt.Color;

public class TestTile
{
   private TileT t1;
   private TileT t2;

   private TileT t4;
    @Before
    public void setUp(){
        t1 = new TileT(1, Color.BLUE);
        t2 = new TileT(3, Color.RED);
        t4 = new TileT(3, Color.ORANGE);
    }

    @After
    public void tearDown(){
      t1 = null;
      t2 = null;
      t4 = null;
    }

    @Test (expected=IllegalArgumentException.class)
    public void testInvalidTile()
    {
       TileT t3 = new TileT(-6, Color.PINK);
    }
    
    @Test 
    public void testGetNum1()
    {
       assertEquals(t1.getTileNum(),1);
    }

    @Test 
    public void testGetNum2()
    {
       assertEquals(t2.getTileNum(),3);
    }

    @Test 
    public void testGetNum3()
    {
       assertFalse(t1.getTileNum()==3);
    }

    @Test 
    public void testGetNum4()
    {
       assertFalse(t2.getTileNum()==1);
    }

    @Test 
    public void testSetNum()
    {
        t1.setTileNum(5);
       assertTrue(t1.getTileNum()==5);
    }

    @Test 
    public void testGetCol1()
    {
       assertTrue(t1.getTileColor()==Color.BLUE);
    }

    @Test 
    public void testGetCol2()
    {
       assertTrue(t4.getTileColor()==Color.ORANGE);
    }

    @Test 
    public void testSetCol()
    {
        t4.setTileColor(Color.PINK);
       assertTrue(t4.getTileColor()==Color.PINK);
    }

    @Test 
    public void testEquals1()
    {
        
       assertTrue(t4.equals(t2) && t2.equals(t4));
    }

    @Test 
    public void testEquals2()
    {
        
       assertTrue(!t1.equals(t2));
    }


    
}
