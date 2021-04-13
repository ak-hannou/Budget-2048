/**
 * Author: Akram Hannoufa
 * Revised: Apr 8th, 2021
 * 
 * Description: Library that calculates and updates the state of the GameBoard and the status of the game, before and after each move
 */
package src;

 /**
 * @brief A library that processes the user's most recent move; returns a new double array of tiles to the GameBoard
 * @details Checks if a move is valid before calculating the new board. Adds a new 2 orGameBoard.size tile to the board.
 */
public class MoveHandler {
    
   /**
   * @brief Calculates the new Game Board after the user executes a move, checks state of game after move executes
   * @param gameTiles, double array of TileT, contains the current state of the GameBoard
   * @param direction, Direction indicating the direction of the user's most recent move
   * @param currScore, user's current score to update while calculating new Game Board
   * @returns TileT double array, containing the new state of the GameBoard after executing the move
   * @details Checks if a move is valid before executing, adds a new random tile to the board, and checks if the game CAN continue
   */
    public static TileT[][] processMove(TileT[][] gameTiles, Directions direction, int currScore){
        boolean[][] alreadyUpdated = new boolean[GameBoard.size][GameBoard.size]; 

        if(direction==Directions.LEFT){
            if(isValidMove(gameTiles, direction)){
            for(int i = 0; i <GameBoard.size; i++){
                for(int j = 1; j <GameBoard.size; j++){
                    int innerCount = 1;
                    int offset =0;
                    TileT currTile = gameTiles[i][j];
                    while(j-innerCount >= 0 && gameTiles[i][j-innerCount].getTileNum() == 0){
                            gameTiles[i][j-innerCount] = currTile;
                            gameTiles[i][j-offset] = GameBoard.makeNewTile(0);
                            innerCount++;  
                            offset++; 
                        }
                    if(innerCount!=1){
                        innerCount--;
                    }
                    else{
                        innerCount=0;
                    }
                    if(j-innerCount-1 >=0 && !alreadyUpdated[i][j-innerCount-1] && gameTiles[i][j-innerCount].equals(gameTiles[i][j-innerCount-1])){
                        gameTiles[i][j-innerCount-1] = GameBoard.makeNewTile(gameTiles[i][j-innerCount].getTileNum()*2); //pass in int, generate new TileT 
                        currScore+=gameTiles[i][j-innerCount].getTileNum()*2;
                        gameTiles[i][j-innerCount] = GameBoard.makeNewTile(0);
                        alreadyUpdated[i][j-innerCount-1] = true;
                    }
                   
                }
            }
            addNewTile(gameTiles);
            GameBoard.updateScore(currScore);
            if(isGameOver(gameTiles)){
                GameBoard.setGameContinue(false);
            }
        }
        else{
            return gameTiles;
        }
    }
        else if(direction==Directions.RIGHT){
            alreadyUpdated = new boolean[GameBoard.size][GameBoard.size]; 

            if(isValidMove(gameTiles, direction)){
                for(int i = 0; i <GameBoard.size; i++){
                    for(int j = 2; j >=0; j--){
                        int innerCount = 1;
                        int offset =0;
                        TileT currTile = gameTiles[i][j];
                        while(j+innerCount <GameBoard.size && gameTiles[i][j+innerCount].getTileNum() == 0){
                                gameTiles[i][j+innerCount] = currTile;
                                gameTiles[i][j+offset] = GameBoard.makeNewTile(0);
                                innerCount++;  
                                offset++; 
                            }
                        if(innerCount!=1){
                            innerCount--;
                        }
                        else{
                            innerCount=0;
                        }
                        if(j+innerCount+1 <GameBoard.size && !alreadyUpdated[i][j+innerCount+1] && gameTiles[i][j+innerCount].equals(gameTiles[i][j+innerCount+1])){
                            gameTiles[i][j+innerCount+1] = GameBoard.makeNewTile(gameTiles[i][j+innerCount].getTileNum()*2);
                            currScore+=gameTiles[i][j+innerCount].getTileNum()*2;
                            gameTiles[i][j+innerCount] = GameBoard.makeNewTile(0);
                            alreadyUpdated[i][j+innerCount+1] = true;
                        }
                       
                    }
                }
                addNewTile(gameTiles);
                GameBoard.updateScore(currScore);
                if(isGameOver(gameTiles)){
                    GameBoard.setGameContinue(false);
                }
            }
            else{
                return gameTiles;
            }
        }
        else if(direction==Directions.UP){
            alreadyUpdated = new boolean[GameBoard.size][GameBoard.size]; 
            if(isValidMove(gameTiles, direction)){
                for(int i = 1; i <GameBoard.size; i++){
                    for(int j = 0; j <GameBoard.size; j++){
                        int innerCount = 1;
                        int offset =0;
                        TileT currTile = gameTiles[i][j];
                        while(i-innerCount >= 0 && gameTiles[i-innerCount][j].getTileNum() == 0){
                                gameTiles[i-innerCount][j] = currTile;
                                gameTiles[i-offset][j] = GameBoard.makeNewTile(0);
                                innerCount++;  
                                offset++; 
                            }
                        if(innerCount!=1){
                            innerCount--;
                        }
                        else{
                            innerCount=0;
                        }
                        if(i-innerCount-1 >=0 && !alreadyUpdated[i-innerCount-1][j] && gameTiles[i-innerCount][j].equals(gameTiles[i-innerCount-1][j])){
                            gameTiles[i-innerCount-1][j] = GameBoard.makeNewTile(gameTiles[i-innerCount][j].getTileNum()*2);
                            currScore+=gameTiles[i-innerCount][j].getTileNum()*2;
                            gameTiles[i-innerCount][j] = GameBoard.makeNewTile(0);
                            alreadyUpdated[i-innerCount-1][j] = true;
                        }
                       
                    }
                }
                addNewTile(gameTiles);
                GameBoard.updateScore(currScore);
                if(isGameOver(gameTiles)){
                    GameBoard.setGameContinue(false);
                }
            }
            else{
                return gameTiles;
            }
        
        }
        else if(direction == Directions.DOWN){
            alreadyUpdated = new boolean[GameBoard.size][GameBoard.size]; 

            if(isValidMove(gameTiles, direction)){
                for(int i = 2; i >= 0; i--){
                    for(int j = 0; j <GameBoard.size; j++){
                        int innerCount = 1;
                        int offset =0;
                        TileT currTile = gameTiles[i][j];
                        while(i+innerCount <GameBoard.size && gameTiles[i+innerCount][j].getTileNum() == 0){
                                gameTiles[i+innerCount][j] = currTile;
                                gameTiles[i+offset][j] = GameBoard.makeNewTile(0);
                                innerCount++;  
                                offset++; 
                            }
                        if(innerCount!=1){
                            innerCount--;
                        }
                        else{
                            innerCount=0;
                        }
                        if(i+innerCount+1 <GameBoard.size && !alreadyUpdated[i+innerCount+1][j] && gameTiles[i+innerCount][j].equals(gameTiles[i+innerCount+1][j])){
                            gameTiles[i+innerCount+1][j] = GameBoard.makeNewTile(gameTiles[i+innerCount][j].getTileNum()*2);
                            currScore+=gameTiles[i+innerCount][j].getTileNum()*2;
                            gameTiles[i+innerCount][j] = GameBoard.makeNewTile(0);
                            alreadyUpdated[i+innerCount+1][j] = true;
                        }
                       
                    }
                }
                addNewTile(gameTiles);
                GameBoard.updateScore(currScore);
                if(isGameOver(gameTiles)){
                    GameBoard.setGameContinue(false);
                }
            }
            else{
                return gameTiles;
            }
        }
        
        
        return gameTiles;
    }

  /**
   * @brief Determines if a given move (Direction) is valid in the current GameBoard
   * @param tiles, double array of TileT, contains the current state of the GameBoard
   * @param direction, Direction indicating the direction of the user's most recent move
   * @returns A boolean indicating whether the given direction is valid on the given board
   */
    private static boolean isValidMove(TileT[][] tiles, Directions direction){
        if(direction==Directions.LEFT){
            for(int i =0; i <GameBoard.size; i ++){
                for(int j=1; j <GameBoard.size; j++){
                    if(tiles[i][j-1].getTileNum()==0 && tiles[i][j].getTileNum()!=0){
                        return true;
                    }
                }
            }

            for(int i =0; i <GameBoard.size; i ++){
                for(int j=1; j <GameBoard.size; j++){
                    if(tiles[i][j-1].equals(tiles[i][j]) && tiles[i][j].getTileNum()!=0){
                        return true;
                    }
                }
            }
            return false;
        }
        else if(direction==Directions.RIGHT){
            for(int i =0; i <GameBoard.size; i ++){
                for(int j=2; j >=0; j--){
                    if(tiles[i][j+1].getTileNum()==0 && tiles[i][j].getTileNum()!=0){
                        return true;
                    }
                }
            }

            for(int i =0; i <GameBoard.size; i ++){
                for(int j=2; j >=0; j--){
                    if(tiles[i][j+1].equals(tiles[i][j]) && tiles[i][j].getTileNum()!=0){
                        return true;
                    }
                }
            }
            return false;
        }
        else if(direction==Directions.UP){
            for(int i=1; i <GameBoard.size; i ++){
                for(int j=0; j <GameBoard.size; j++){
                    if(tiles[i-1][j].getTileNum()==0 && tiles[i][j].getTileNum()!=0){
                        return true;
                    }
                }
            }

            for(int i =1; i <GameBoard.size; i ++){
                for(int j=0; j <GameBoard.size; j++){
                    if(tiles[i-1][j].equals(tiles[i][j]) && tiles[i][j].getTileNum()!=0){
                        return true;
                    }
                }
            }
            return false;
        }
        else if(direction==Directions.DOWN){
            for(int i =2; i >= 0; i --){
                for(int j=0; j <GameBoard.size; j++){
                    if(tiles[i+1][j].getTileNum()==0 && tiles[i][j].getTileNum()!=0){
                        return true;
                    }
                }
            }

            for(int i =2; i >= 0; i --){
                for(int j=0; j<GameBoard.size; j++){
                    if(tiles[i+1][j].equals(tiles[i][j]) && tiles[i][j].getTileNum()!=0){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    /**
   * @brief Adds a new TileT (2, orGameBoard.size) to the current game board
   * @param tiles, double array of TileT, contains the current state of the GameBoard
   */
    private static void addNewTile(TileT[][] gameTiles){
        int[] newTileCoords = GameBoard.generateRandomPos();
            while(gameTiles[newTileCoords[0]][newTileCoords[1]].getTileNum() != 0){
                newTileCoords = GameBoard.generateRandomPos();
            }
            gameTiles[newTileCoords[0]][newTileCoords[1]] = GameBoard.generateRandomTile();
    }

    /**
   * @brief Determines if after a move is executed, if the game should continue
   * @param tiles, double array of TileT, contains the current state of the GameBoard
   * @returns A boolean indicating whether the game is over or not
   * @details Continuation is based on whether there is at least 1 valid move in any direction; otherwise, end game
   */
    private static boolean isGameOver(TileT[][] tiles){
        return !(isValidMove(tiles, Directions.LEFT) || isValidMove(tiles, Directions.RIGHT) || isValidMove(tiles, Directions.UP) || isValidMove(tiles, Directions.DOWN)) ;
    }
}
