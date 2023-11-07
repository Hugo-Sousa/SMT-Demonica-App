package dam_a46304.shinmegamitenseidemonica.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.databinding.FragmentFusionBinding
import dam_a46304.shinmegamitenseidemonica.databinding.FragmentStockBinding
import dam_a46304.shinmegamitenseidemonica.enums.RaceEnum
import dam_a46304.shinmegamitenseidemonica.ui.FusionChartViewModel
import kotlinx.coroutines.*

class fusion : Fragment() {

    private lateinit var binding :FragmentFusionBinding

    private val viewModel : FusionChartViewModel by viewModels ()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentFusionBinding.inflate( layoutInflater )

        if((activity as FusionMain).checkIfInitialized(1)) {
            binding.demon1 = (activity as FusionMain).demon1
        }
        if((activity as FusionMain).checkIfInitialized(2)) {
            binding.demon2 = (activity as FusionMain).demon2
        }
        if((activity as FusionMain).checkIfInitialized(3)){
            binding.resultDemon = (activity as FusionMain).resultDemon
        }
        else{binding.resultDemon = null}

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                startActivity(Intent(activity,Compendium::class.java))
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getView()?.findViewById<Button>(R.id.fusionChartbtn)?.setOnClickListener {
            startActivity(Intent(activity,FusionChart::class.java))
        }
        getView()?.findViewById<Button>(R.id.searchResultbtn)?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fusion2_to_fusionResultChoose)
        }
        getView()?.findViewById<Button>(R.id.stockbtn)?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fusion2_to_stock)
        }
        getView()?.findViewById<ImageView>(R.id.imagefuse1)?.setOnClickListener {
            (activity as FusionMain).slotSelected = 1
            Navigation.findNavController(view).navigate(R.id.action_fusion2_to_stock)
        }
        getView()?.findViewById<ImageView>(R.id.imagefuse2)?.setOnClickListener {
            (activity as FusionMain).slotSelected = 2
            Navigation.findNavController(view).navigate(R.id.action_fusion2_to_stock)
        }
        getView()?.findViewById<ImageView>(R.id.result)?.setOnClickListener {
            if((activity as FusionMain).checkIfInitialized(3)) {
                Navigation.findNavController(view).navigate(R.id.action_fusion2_to_fusionResultChoose)
            }
        }
    }


}