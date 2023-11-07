package dam_a46304.shinmegamitenseidemonica.activities

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import dam_a46304.shinmegamitenseidemonica.ui.DemonSkillsViewModel
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.adapter.SkillsAdapter
import dam_a46304.shinmegamitenseidemonica.databinding.DemonSkillsFragmentBinding
import dam_a46304.shinmegamitenseidemonica.ui.DemonDetailViewModel

class demon_skills : Fragment() {

    private lateinit var bindingSkills : DemonSkillsFragmentBinding
    private val viewModel : DemonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.demon = (activity as DemonDetail).demon

        bindingSkills = DemonSkillsFragmentBinding.inflate( layoutInflater )
        bindingSkills.lifecycleOwner = this
        bindingSkills.vm = viewModel
        bindingSkills.demon = viewModel.demon

        var adapter = SkillsAdapter((activity as DemonDetail).applicationContext, false)
        bindingSkills.demonskills.adapter = adapter

        viewModel.getDemon((activity as DemonDetail).application)
        viewModel.getSkills((activity as DemonDetail).application)

        subscribeUi(adapter)

        return bindingSkills.root
    }

    private fun subscribeUi ( adapter : SkillsAdapter) {
        viewModel.demonskills.observe( viewLifecycleOwner ) {skills ->
            adapter.submitList( skills )
        }
    }

}