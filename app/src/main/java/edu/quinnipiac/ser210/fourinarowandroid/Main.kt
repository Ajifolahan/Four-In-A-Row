/**
 * Main Class.
 * @author Momore
 * @date 2/2/2023
 */


import java.util.*
import kotlin.concurrent.schedule

val FIR_board = FourInARow()

/** The entry main method (the program starts here)  */
fun main() {
    var currentState: Int = GameConstants.PLAYING
    val userInput = ""

    //game loop
    do {
        print("Enter a cell location between No's 0-35  or 'q' to quit. \n")
        print("Do not put in a number for an already occupied cell. YOU'LL USE YOUR ROUND!! \n")
        FIR_board.printBoard()
        print("No: ")
        val locationInput = readLine()
        if (locationInput != null && locationInput!= "q") {
            try {
                val number = locationInput.toInt()
                if (number < 0 || number > 35) {
                    print("Number Out of Bounds \n")
                } else {
                    FIR_board.setMove(1, number)
                    if (FIR_board.checkForWinner() == 3) {
                        FIR_board.printBoard()
                        println("Yay! You Won")
                        currentState = GameConstants.BLUE_WON
                    }
                }

                val computerMove = FIR_board.computerMove
                FIR_board.setMove(0, computerMove)
                if(FIR_board.checkForWinner() == 2){
                    FIR_board.printBoard()
                    println("Dang! Computer Won. Better Luck Next Time")
                    currentState = GameConstants.RED_WON
                }
            } catch (e: NumberFormatException) {
                println("Invalid input. Not an integer.")

            }
        } else if(locationInput == "q"){
            FIR_board.clearBoard()
            println("You exited the Game")
            break
        }
    } while (currentState == GameConstants.PLAYING && userInput != "q")
// repeat if not game-over
}
