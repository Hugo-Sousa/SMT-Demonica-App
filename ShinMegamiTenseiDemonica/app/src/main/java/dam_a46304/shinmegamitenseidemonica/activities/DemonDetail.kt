package dam_a46304.shinmegamitenseidemonica.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.entities.Demon

class DemonDetail : AppCompatActivity() {


    lateinit var demon: Demon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        demon = intent.getParcelableExtra("demon")!!
        setContentView(R.layout.activity_demon_detail)
    }

}