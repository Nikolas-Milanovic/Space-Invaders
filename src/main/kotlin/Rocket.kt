import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Rocket(val imageName: String, startX: Double, startY: Double, rocketVel: Double): Pane() {
    val ICON_WIDTH = 13.0
    val ICON_HEIGHT= 44.0
    val icon: ImageView
    val hitBox: Rectangle
    val vel: Double

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
        val image = Image("file:src/main/resources/images/"+ imageName)
        icon = ImageView(image)
        icon.fitWidth = ICON_WIDTH
        icon.fitHeight = ICON_HEIGHT

        hitBox = Rectangle(ICON_WIDTH, ICON_HEIGHT, Color.TRANSPARENT)

        x = startX
        y = startY
        vel = rocketVel

        children.add(hitBox)
        children.add(icon)
    }

    fun animate(){
        y += vel
    }

}