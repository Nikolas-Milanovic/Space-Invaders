import LevelManager.PLAYER_SPEED
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import java.time.Instant
import java.time.LocalTime
import kotlin.random.Random

object Player: Pane() {
    val ICON_WIDTH = 124.0
    val ICON_HEIGHT = 75.0
    const val PLAYER_WIDTH = 124.0
    const val PLAYER_HEIGHT = 75.0

//    var fireTiming = ArrayDeque<Long>()

    //val imageView = getIcon("images/player.png")
    val imageView: ImageView
    val hitBox = Rectangle(PLAYER_WIDTH, PLAYER_HEIGHT, Color.TRANSPARENT)
    var speed = PLAYER_SPEED

    var prevMissileTime: Long = 0

    init{
        val image = Image("file:src/main/resources/images/player.png")
        imageView = ImageView(image)
        imageView.fitWidth = ICON_WIDTH
        imageView.fitHeight = ICON_HEIGHT

        children.add(hitBox)
        children.add(imageView)
    }




    fun moveLeft(){
        imageView.layoutX -=speed
        hitBox.layoutX -=speed
    }

    fun moveRight(){
        imageView.layoutX +=speed
        hitBox.layoutX +=speed
    }

    fun fire(){
        if( (Instant.now().toEpochMilli() - prevMissileTime) > 500) {
            Rockets.addPlayerRocket()
            prevMissileTime = Instant.now().toEpochMilli()
        }
    }

    fun animate(){
        checkForCollisions()
    }

    fun checkForCollisions(){
        Rockets.enemyRockets.forEach { rocket: Rocket ->
            if(checkShapeIntersection(rocket.hitBox)){
                LevelManager.lives -= 1
                LevelManager.playAudio("explosion.wav")
                val respanPoint = Random.nextDouble() * (MainKt.width - ICON_WIDTH)
                imageView.layoutX = respanPoint
                hitBox.layoutX = respanPoint

                Rockets.enemyRockets.remove(rocket)
                Rockets.children.remove(rocket)
            }
        }
    }

    fun checkShapeIntersection(shape: Shape): Boolean {
        var collisionDetected = false
        val intersect = Shape.intersect(hitBox, shape)
        if (intersect.boundsInLocal.width != -1.0) {
            collisionDetected = true
        } else {
            //collisionDetected = false
        }
        return collisionDetected
    }
}
