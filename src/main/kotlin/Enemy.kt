import LevelManager.ENEMY_SPEED
import LevelManager.ENEMY_VERTICAL_SPEED
import LevelManager.SPEED_UP_RATE
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import kotlin.random.Random

class Enemy(val imageName: String, row: Int, column: Int, bulletName: String): Pane() {
    val ICON_WIDTH = 70.0
    val ICON_HEIGHT= 50.0
    val PADDING = 10.0
    val LEAD_RIGHT: Double
    val LEAD_LEFT: Double
    val icon: ImageView
    val hitBox: Rectangle
    var vel = ENEMY_SPEED
    val bulletName: String

    var x : Double
        get() = icon.layoutX
        set(value) {
            icon.layoutX = value
            hitBox.layoutX = value
        }
    var y : Double
        get() = icon.layoutY
        set(value) {
            icon.layoutY = value
            hitBox.layoutY = value
        }

    init{
        this.bulletName = bulletName
        val image = Image("file:src/main/resources/images/"+ imageName)
        icon = ImageView(image)
        icon.fitWidth = ICON_WIDTH
        icon.fitHeight = ICON_HEIGHT
       // icon.isPreserveRatio = true
       // ICON_HEIGHT = icon.image.height * (ICON_WIDTH / icon.image.width)

        hitBox = Rectangle(ICON_WIDTH, ICON_HEIGHT, Color.TRANSPARENT)

        x = (column * (ICON_WIDTH + PADDING)) + PADDING
        y = (row * (ICON_WIDTH + PADDING)) + PADDING

        LEAD_LEFT = (column*(PADDING+ICON_WIDTH))
        LEAD_RIGHT = (Enemies.COLUMNS - (column))*(PADDING+ICON_WIDTH)-PADDING

        children.add(hitBox)
        children.add(icon)

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

    fun animate(){
        if( x + LEAD_RIGHT > MainKt.width || x < LEAD_LEFT ) {
            vel *= -1
            vel *= SPEED_UP_RATE
            y+=ENEMY_VERTICAL_SPEED
        }
        x += vel
    }

    fun maybeFireBack(){
        val random = Random.nextDouble()
        if (random <= LevelManager.ENEMY_FIRE_RATE){
            Rockets.addEnemyRocket(bulletName,x,y)
        }
    }




}