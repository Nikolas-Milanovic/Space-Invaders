import LevelManager.ENEMY_BULLET_SPEED
import LevelManager.ENEMY_VERTICAL_SPEED
import LevelManager.PLAYER_BULLET_SPEED
import javafx.scene.layout.Pane

object Rockets: Pane() {

    val enemyRockets = ArrayList<Rocket>()
    val playerRockets = ArrayList<Rocket>()

    fun animate(){
        enemyRockets.forEach { rocket: Rocket ->
            rocket.animate()
        }

        playerRockets.forEach { rocket: Rocket ->
            rocket.animate()
        }
    }

    fun addPlayerRocket(){
        val rocketVel = -1 * PLAYER_BULLET_SPEED
        val rocket = Rocket("player_bullet.png",
            Player.imageView.layoutX + (Player.ICON_WIDTH/2),
            MainKt.height - Player.ICON_HEIGHT*2,
            rocketVel)
        children.add(rocket)
        playerRockets.add(rocket)

        LevelManager.playAudio("shoot.wav")
    }

    fun addEnemyRocket(iconName: String, x: Double, y: Double){
        val rocketVel = ENEMY_BULLET_SPEED
        val rocket = Rocket(iconName,
            x,
            y,
            rocketVel)
        children.add(rocket)
        enemyRockets.add(rocket)
    }

    fun delRockets(){
        enemyRockets.clear()
        playerRockets.clear()
        children.clear()
    }

}