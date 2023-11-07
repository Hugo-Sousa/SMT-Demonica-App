package dam_a46304.shinmegamitenseidemonica.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.ui.CompendiumViewModel

class StartScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        findViewById<Button>(R.id.Startbutton).setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
    }
}