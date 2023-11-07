package dam_a46304.shinmegamitenseidemonica.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.work.ListenableWorker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.SMTDatabase
import dam_a46304.shinmegamitenseidemonica.adapter.SMTAdapter
import dam_a46304.shinmegamitenseidemonica.ui.CompendiumViewModel
import dam_a46304.shinmegamitenseidemonica.databinding.ActivityCompendiumBinding
import dam_a46304.shinmegamitenseidemonica.entities.Demon

class Compendium : AppCompatActivity() {

    private lateinit var binding : ActivityCompendiumBinding
    private val viewModel : CompendiumViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompendiumBinding.inflate( layoutInflater )
        viewModel.GetAllDemons(application)
        viewModel.InsertData(application);
        val view = binding.root
        setContentView(view)
        var adapter = SMTAdapter(applicationContext)
        binding.demonList.adapter = adapter
        subscribeUi(adapter)
    }

    private fun subscribeUi ( adapter : SMTAdapter) {
        viewModel.demonList.observe( this ) {demons ->
            adapter.submitList( demons )
        }
    }

    fun ToFusion(view: View){
        val intent = Intent(this,FusionMain::class.java)
        startActivity(intent)
    }
    
}