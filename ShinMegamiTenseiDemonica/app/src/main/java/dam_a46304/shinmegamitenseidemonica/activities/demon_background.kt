package dam_a46304.shinmegamitenseidemonica.activities

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import dam_a46304.shinmegamitenseidemonica.ui.DemonBackgroundViewModel
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.databinding.DemonBackgroundFragmentBinding
import dam_a46304.shinmegamitenseidemonica.ui.DemonDetailViewModel

class demon_background : Fragment() {

    private lateinit var bindingBackground : DemonBackgroundFragmentBinding
    private val viewModel : DemonDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.demon = (activity as DemonDetail).demon

        bindingBackground = DemonBackgroundFragmentBinding.inflate( layoutInflater )
        bindingBackground.lifecycleOwner = this
        bindingBackground.vm = viewModel
        bindingBackground.demon = viewModel.demon

        viewModel.getDemon((activity as DemonDetail).application)

        return bindingBackground.root
    }

}