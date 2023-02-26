/**
 * TicTacToe class implements the interface
 * @author Momoreoluwa Ayinde
 * @date 2/2/2023
 */
class FourInARow
/**
 * clear board and set current player
 */
    : IGame {
    // game board in 2D array initialized to zeros
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS){0} }

    override fun clearBoard() {
        //sets every cell to empty
        for (i in board.indices) {
            for (j in 0 until board[i].size) {
                board[i][j] = GameConstants.EMPTY
            }
        }
    }

    override fun setMove(player: Int, location: Int) {
        //converts location to rows and columns
            val row = location / GameConstants.ROWS
            val column = (location % GameConstants.COLS )
        for (element in board) {
            for (j in element.indices) {
                //Makes sure they're emptied if not it won't work
                if(board[row][column] == GameConstants.EMPTY){
                    if (player == 1) {
                        board[row][column] = GameConstants.BLUE
                    } else {
                        board[row][column] = GameConstants.RED
                    }
                }
                }
            }
    }


    override val computerMove: Int
        get() {
            val cellTotal = GameConstants.ROWS * GameConstants.COLS
            var check = false
            while (!check) {
                val random = (0 until cellTotal).random()
                val row = random / GameConstants.ROWS
                val column = random % GameConstants.COLS

                //AI blocks row wins only if random2 == 0!
                val random2 = (0 until 2).random()
                for (i in 0 until GameConstants.ROWS) {
                    for (j in 0 until GameConstants.COLS - 3) {
                        if(board[i][j] == GameConstants.BLUE && board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j+3] == GameConstants.EMPTY && random2 == 0 ){
                            return (i * (GameConstants.ROWS)) + j + 3
                            }
                    }
                }

                //AI blocks column wins only if random2 == 1!
                val random3 = (0 until 2).random()
                for (i in 0 until GameConstants.ROWS - 3){
                    for(j in 0 until GameConstants.COLS){
                        if(board[i][j] == GameConstants.BLUE && board[i][j] == board[i+ 1][j] && board[i][j] == board[i+ 2][j] && board[i+3][j] == GameConstants.EMPTY && random2 == 1){
                            return (i + 3 * (GameConstants.ROWS)) + j
                        }
                    }
                }


                //AI blocks wins for Diagonal from cell 0 to the left only when random2 == 0.
                for(i in 0 until GameConstants.ROWS-3){
                    for (j in 0 until GameConstants.COLS-3){
                        if(board[i][j] == GameConstants.BLUE && board[i][j] == board[i + 1][j + 1] && board[i][j] == board[i + 2][j + 2] &&  board[i + 3][j+ 3] == GameConstants.EMPTY && random2 == 0){
                            return (i + 3 * (GameConstants.ROWS)) + j + 3
                        }
                    }
                }

                //AI blocks wins for Diagonal from cell 5 to the right only when random2 == 1.
                for(i in 0 until GameConstants.ROWS-3){
                    for(j in 3 until GameConstants.COLS){
                        if(board[i][j] == GameConstants.BLUE && board[i][j] == board[i + 1][j - 1] && board[i][j] == board[i + 2][j - 2] && board[i + 3][j - 3] == GameConstants.EMPTY && random2 == 1){
                            return (i + 3 * (GameConstants.ROWS)) + j - 3
                        }
                    }
                }

                if (board[row][column] == GameConstants.EMPTY) {
                    return random
                    check = true
                }

            }

            return 0
        }


    override fun checkForWinner(): Int {
        // Row check
        for(i in 0 until GameConstants.ROWS){
            for(j in 0 until GameConstants.COLS-3){
                if(board[i][j] != GameConstants.EMPTY && board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3]){
                    return if(board[i][j] == GameConstants.BLUE){
                        GameConstants.BLUE_WON
                    } else {
                        GameConstants.RED_WON
                    }
                }
            }
        }

        // Column check
        for (i in 0 until GameConstants.ROWS - 3){
            for(j in 0 until GameConstants.COLS){
                if(board[i][j] != GameConstants.EMPTY && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]){
                    return if(board[i][j] == GameConstants.BLUE){
                        GameConstants.BLUE_WON
                    } else {
                        GameConstants.RED_WON
                    }
                }
            }
        }

        //Diagonal from cell 0 to the left.
        for(i in 0 until GameConstants.ROWS-3){
            for (j in 0 until GameConstants.COLS-3){
                if(board[i][j] != GameConstants.EMPTY && board[i][j] == board[i + 1][j + 1] && board[i][j] == board[i + 2][j + 2] && board[i][j] == board[i + 3][j+ 3]){
                    return if(board[i][j] == GameConstants.BLUE){
                        GameConstants.BLUE_WON
                    } else {
                        GameConstants.RED_WON
                    }
                }
            }
        }

        //Diagonal from cell 5 to the right.
        //Do GameConstants.ROWS-3 to make sure we're checking just 4 consecutive rows
        for(i in 0 until GameConstants.ROWS-3){
            for(j in 3 until GameConstants.COLS){
                if(board[i][j] != GameConstants.EMPTY && board[i][j] == board[i + 1][j - 1] && board[i][j] == board[i + 2][j - 2] && board[i][j] == board[i + 3][j - 3]){
                    return if(board[i][j] == GameConstants.BLUE){
                        GameConstants.BLUE_WON
                    } else {
                        GameConstants.RED_WON
                    }
                }
            }
        }

        //Tie check
        //Makes sure all cells are occupied before callina a tie
        var checkTie= true
        for(i in 0 until GameConstants.ROWS){
            for(j in 0 until GameConstants.COLS){
                if(board[i][j] == 0){
                    checkTie = false
                }
            }
        }
        if(checkTie){
            return GameConstants.TIE
        }

        return 0
    }

    /**
     * Print the game board
     */
    fun printBoard() {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                printCell(board[row][col]) // print each of the cells
                if (col != GameConstants.COLS - 1) {
                    print("|") // print vertical partition
                }
            }
            println()
            if (row != GameConstants.ROWS - 1) {
                println("-----------") // print horizontal partition
            }
        }
        println()
    }

    /**
     * Print a cell with the specified "content"
     * @param content either BLUE, RED or EMPTY
     */
    fun printCell(content: Int) {
        when (content) {
            GameConstants.EMPTY -> print("   ")
            GameConstants.BLUE -> print(" B ")
            GameConstants.RED -> print(" R ")
        }
    }
}

