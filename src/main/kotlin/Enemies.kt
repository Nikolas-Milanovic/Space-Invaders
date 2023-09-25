import LevelManager.POINTS_PER_SHIP
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.layout.Pane
import kotlin.random.Random

object Enemies: Pane() {
    val COLUMNS = 10
    val ROWS = 5
    val FRAME_PER_SEC = 60
    var frameCount = 0


    const val PADDING = 10.0

    init {

    }
     fun buildEnemies() {
        for (row in 0 until ROWS) {
            for (column in 0 until COLUMNS) {
                var imageName = "enemy3.png"
                var bulletName = "bullet3.png"
                if (row == 0) {
                    imageName = "enemy3.png"
                    bulletName = "bullet3.png"
                } else if (row == 1 || row == 2) {
                    imageName = "enemy2.png"
                    bulletName = "bullet2.png"
                } else {
                    imageName = "enemy1.png"
                    bulletName = "bullet1.png"
                }
                val enemy = Enemy(imageName, row, column, bulletName)
               // enemies.add(enemy)
                children.add(enemy)
            }
        }
    }

    fun animate(){
        frameCount+=1
        fireMissiles()
        checkForCollisions()
        if(frameCount == FRAME_PER_SEC){
            frameCount = 0
        }

        if(children.size == 0 && LevelManager.level < 4){
            LevelManager.level+=1
        }
    }


    private fun fireMissiles() {
        children.forEach { child ->
            if (child is Enemy) {
                child.animate()
                if (frameCount == FRAME_PER_SEC) {
                    child.maybeFireBack()
                }
            }
        }
    }

    fun deleteEnemies(){
        children.clear()
    }

    fun checkForCollisions(){
        children.forEach { child ->
            if(child is Enemy) {
                Rockets.playerRockets.forEach { rocket: Rocket ->
                    if(child.checkShapeIntersection(rocket.hitBox)){
                        LevelManager.playAudio("invaderkilled.wav")
                        children.remove(child)

                        LevelManager.score += POINTS_PER_SHIP
                        Rockets.playerRockets.remove(rocket)
                        Rockets.children.remove(rocket)

                    }
                }
                if(child.checkShapeIntersection(Player.hitBox)){
                    LevelManager.lives = 0
//                    Enemies.deleteEnemies()
//                    Rockets.delRockets()
//                    LevelManager.gameOver()
                }
            }
        }
    }



}