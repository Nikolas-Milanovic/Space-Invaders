import LevelManager.gameOver
import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.stage.Stage

class MainKt(): Application() {
    companion object {
        const val width = 1600.00 - 100.0
        const val height = 1080.00 - 200.0
    }

    enum class SCENES {
        HOME,
        LEVEL,
    }
    val home = Scene(HomeScreen,width, height)
    val levels = Scene(LevelManager,width, height)
    //val gameOver = Scene(GameOver,width, height)

    override fun start(primaryStage: Stage?) {
        primaryStage?.isResizable = false

        primaryStage?.apply{
            setScene(primaryStage,SCENES.HOME)
            title = "Space Invaders - Nikolas Milanovic (n2milano)"
            show()
        }


        // pressing '2' or pressing the button will switch to scene 2
        levels.setOnKeyPressed(EventHandler<KeyEvent> { event: KeyEvent ->
            if (event.code == KeyCode.Q) {
                setScene(primaryStage, SCENES.HOME)
            }
            if(event.code == KeyCode.ENTER) {
                LevelManager.restart(1)
            }

            if (event.code == KeyCode.A || event.code == KeyCode.LEFT) {
                Player.moveLeft()
            }
            if (event.code == KeyCode.D || event.code == KeyCode.RIGHT) {
                Player.moveRight()
            }
            if (event.code == KeyCode.SPACE) {
                Player.fire()
            }

            if(event.code == KeyCode.DIGIT1) {
                LevelManager.restart(1)
            }
            if(event.code == KeyCode.DIGIT2) {
                LevelManager.restart(2)
            }
            if(event.code == KeyCode.DIGIT3) {
                LevelManager.restart(3)
            }

        })

        home.setOnKeyPressed(EventHandler<KeyEvent> { event: KeyEvent ->
            if(event.code == KeyCode.DIGIT1 ) {
                LevelManager.restart(1)
                setScene(primaryStage, SCENES.LEVEL)
            }
            if(event.code == KeyCode.ENTER) {
                LevelManager.restart(1)
                setScene(primaryStage, SCENES.LEVEL)
            }
            if(event.code == KeyCode.DIGIT2) {
                LevelManager.restart(2)
                setScene(primaryStage, SCENES.LEVEL)
            }
            if(event.code == KeyCode.DIGIT3) {
                LevelManager.restart(3)
                setScene(primaryStage, SCENES.LEVEL)
            }
        })


    }

    fun setScene(stage: Stage?, scene: SCENES) {
        when (scene) {
            SCENES.HOME -> {
                stage?.scene = home
            }

            SCENES.LEVEL -> {
                stage?.scene = levels
            }
        }
    }
}