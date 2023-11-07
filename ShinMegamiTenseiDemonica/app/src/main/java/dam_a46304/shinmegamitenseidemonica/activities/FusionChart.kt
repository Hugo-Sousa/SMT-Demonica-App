package dam_a46304.shinmegamitenseidemonica.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.databinding.ActivityCompendiumBinding
import dam_a46304.shinmegamitenseidemonica.enums.RaceEnum
import dam_a46304.shinmegamitenseidemonica.databinding.ActivityFusionChartBinding
import dam_a46304.shinmegamitenseidemonica.ui.FusionChartViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

class FusionChart : AppCompatActivity() {

    private lateinit var binding : ActivityFusionChartBinding
    private val viewModel : FusionChartViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFusionChartBinding.inflate( layoutInflater )
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        val view = binding.root
        setContentView(view)

        val adapter = ArrayAdapter(this, R.layout.spinner_item, RaceEnum.values())

        val spinner = findViewById<Spinner>(R.id.racesDropdown)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                runBlocking {
                    launch(newSingleThreadContext("demonData")) {
                        viewModel.getRace(application, parent?.getItemAtPosition(position).toString())
                        binding.race = viewModel.race
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.race = viewModel.race
            }

        }
    }
}