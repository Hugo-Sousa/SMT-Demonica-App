package dam_a46304.shinmegamitenseidemonica.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.adapter.SMTAdapter
import dam_a46304.shinmegamitenseidemonica.adapter.SkillsAdapter
import dam_a46304.shinmegamitenseidemonica.databinding.FragmentFusionResultChooseBinding
import dam_a46304.shinmegamitenseidemonica.ui.DemonDetailViewModel
import dam_a46304.shinmegamitenseidemonica.ui.FusionChartViewModel

class FusionResultChoose : Fragment() {

    private lateinit var binding : FragmentFusionResultChooseBinding

    private val viewModel : DemonDetailViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as FusionMain).viewModelDetail.demon = (activity as FusionMain).resultDemon!!

        binding = FragmentFusionResultChooseBinding.inflate(layoutInflater)
        binding.lifecycleOwner = (activity as FusionMain)
        binding.vm = (activity as FusionMain).viewModelDetail
        binding.resultDemon = (activity as FusionMain).resultDemon

        var adapterBaseS = SkillsAdapter(activity as FusionMain, true)
        var adapterTransS = SkillsAdapter(activity as FusionMain, false)
        binding.demonskills.adapter = adapterBaseS
        binding.demonskillsTrans.adapter = adapterTransS

        (activity as FusionMain).viewModelDetail.getDemon((activity as FusionMain).application)
        (activity as FusionMain).viewModelDetail.getSkills((activity as FusionMain).application)
        (activity as FusionMain).viewModelDetail.getSkillsToTrans((activity as FusionMain).application, (activity as FusionMain).demon1, (activity as FusionMain).demon2)

        subscribeUiBase(adapterBaseS)
        subscribeUiTrans(adapterTransS)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getView()?.findViewById<Button>(R.id.chooseFusebtn)?.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_fusionResultChoose_to_fusionResultEnd)
        }
    }

    private fun subscribeUiBase ( adapter : SkillsAdapter) {
        (activity as FusionMain).viewModelDetail.demonskills.observe( viewLifecycleOwner ) {skills ->
            adapter.submitList( skills )
            Log.e("TEST", "BASE SKILLS CHANGED")
        }
    }

    private fun subscribeUiTrans ( adapter : SkillsAdapter) {
        (activity as FusionMain).viewModelDetail.demonTransSkills.observe( viewLifecycleOwner ) {skills ->
            adapter.submitList( skills )
            Log.e("TEST", "TRANS SKILLS CHANGED")
        }
    }

}