import javafx.animation.AnimationTimer
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javax.sound.sampled.AudioSystem
import java.io.File
import javax.print.attribute.standard.Media
import javax.sound.midi.Sequencer.LOOP_CONTINUOUSLY


object LevelManager: BorderPane(){

    const val PLAYER_SPEED = 25.0
    const val PLAYER_BULLET_SPEED = 6.0
    const val ENEMY_SPEED = 1.0
    var ENEMY_VERTICAL_SPEED = 25.0
    var ENEMY_BULLET_SPEED = 1.0
    const val STARTING_LIVES = 3
    var ENEMY_FIRE_RATE = 0.004
    const val POINTS_PER_SHIP = 10
    const val SPEED_UP_RATE = 1.1

    var levelLabel = makeProgressLabel("Level: 1")
    var scoreLabel = makeProgressLabel("Score: 0")
    var livesLabel = makeProgressLabel("Lives: ${STARTING_LIVES}")
    var gamePlay = Pane().apply {
        children.add(Enemies)
        children.add(Rockets)
    }

    var level = 1
        set(value) {
            field = value
            if(value == 2){
                ENEMY_BULLET_SPEED = 1.5
                ENEMY_VERTICAL_SPEED = 30.0
                ENEMY_FIRE_RATE = 0.005
            }
            if(value == 3){
                ENEMY_BULLET_SPEED = 2.0
                ENEMY_VERTICAL_SPEED = 35.0
                ENEMY_FIRE_RATE = 0.006
            }
            if(value<4){
                Enemies.deleteEnemies()
                Enemies.buildEnemies()
                Rockets.delRockets()
            }
            if(value == 4){
                Enemies.deleteEnemies()
                Rockets.delRockets()
                gameOver()
            }
            levelLabel.text = "Level: ${value}"
        }
    var lives = 3
        set(value) {
            field = value
            if(value == 0){
                Enemies.deleteEnemies()
                Rockets.delRockets()
                gameOver()
            }
            livesLabel.text = "Lives: ${value}"
        }
    var score = 0
        set(value) {
            field = value
            scoreLabel.text = "Score: ${value}"
        }

    init {
        top = BorderPane().apply {
            left = scoreLabel
            right = HBox(20.0,levelLabel,livesLabel)
        }

        center = gamePlay
        bottom = Player
        LevelManager.style = "-fx-background-color: #000000;"

        val animation = MyAnimation()
        animation.start()
    }

    fun restart(level: Int){
        LevelManager.level = level
        LevelManager.lives = 3
        LevelManager.score = 0
        center = gamePlay
    }

    private fun makeProgressLabel(text: String): Label {
        val label = Label(text).apply {
            font = Font("Arial Bold", 20.0)
            textFill = Color.WHITE
            padding = Insets(30.0)
        }
        return label
    }

    class MyAnimation : AnimationTimer() {
        override fun handle(now: Long) {
            Enemies.animate()
            Rockets.animate()
            Player.animate()
        }
    }

    fun playAudio(audioName: String){
        var soundFile = "src/main/resources/sounds/" + audioName


        val audioInputStream = AudioSystem.getAudioInputStream(File(soundFile))
        val clip = AudioSystem.getClip()
        clip.open(audioInputStream)

        println(clip)
        clip.start()

    }

    fun playAudioContinously(audioName: String) {
        val soundFile = "src/main/resources/sounds/" + audioName
        val audioInputStream = AudioSystem.getAudioInputStream(File(soundFile))
        val clip = AudioSystem.getClip()
        clip.open(audioInputStream)

        println(clip)
        clip.loop(LOOP_CONTINUOUSLY)
        clip.start()
    }

    fun gameOver(){
        var didWin = (level == 4) && lives!=0
        if(didWin){
            val vBox = VBox(
                makeInstructionLabel("You Won! Score: ${score}"),
                makeInstructionLabel("ENTER - Start New Game"),
                makeInstructionLabel("Q - Quit Game"),
                makeInstructionLabel("1 or 2 or 3 - Start NEW Game at a specific level")).apply {
                alignment = Pos.CENTER
            }
            center = vBox
        }else{
            val vBox = VBox(
                makeInstructionLabel("You Lost :( Score: ${score}"),
                makeInstructionLabel("ENTER - Start New Game"),
                makeInstructionLabel("Q - Quit Game"),
                makeInstructionLabel("1 or 2 or 3 - Start NEW Game at a specific level")).apply {
                alignment = Pos.CENTER
            }
            center = vBox
        }
    }

    private fun makeInstructionLabel(text: String): Label {
        val label = Label(text).apply {
            font = Font("Arial", 20.0)
            textFill = Color.PURPLE
            padding = Insets(10.0)
        }
        return label
    }



}

