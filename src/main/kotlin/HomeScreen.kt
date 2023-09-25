import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font

object HomeScreen: BorderPane() {
    init{
        top = VBox(getIcon("images/logo.png")).apply {
            alignment = Pos.CENTER
            padding = Insets(30.0)
        }
        var instructionLabel = Label("Instructions").apply {
            font = Font("Arial Bold", 30.0)
            textFill = Color.BLACK
            padding = Insets(20.0)
        }
        center = VBox(instructionLabel,
            makeInstructionLabel("ENTER - Start Game"),
            makeInstructionLabel("A or ←, D or → - Move ship left or right"),
            makeInstructionLabel("SPACE - Fire!"),
            makeInstructionLabel("Q - Quit Game"),
            makeInstructionLabel("1 or 2 or 3 - Start Game at a specific level")).apply {
            alignment = Pos.CENTER
        }
        bottom = HBox(Label("Implemented by Nikolas Milanovic for CS 349, University of Waterloo, S23").apply {
            font = Font("Arial", 12.0)
            textFill = Color.BLACK
            padding = Insets(10.0)
        }).apply {
            alignment = Pos.CENTER
        }
    }

    fun getIcon(fileName: String): ImageView {

        val image = Image("file:src/main/resources/"+ fileName)
        val icon = ImageView(image)
//        icon.fitHeight = iconSize
//        icon.fitWidth = iconSize
        return icon
    }

    private fun makeInstructionLabel(text: String): Label {
        val label = Label(text).apply {
            font = Font("Arial", 20.0)
            textFill = Color.BLACK
            padding = Insets(10.0)
        }
        return label
    }
}