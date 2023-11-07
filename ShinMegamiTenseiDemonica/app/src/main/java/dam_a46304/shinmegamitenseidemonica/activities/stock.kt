package dam_a46304.shinmegamitenseidemonica.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.adapter.SMTAdapter
import dam_a46304.shinmegamitenseidemonica.databinding.FragmentStockBinding
import dam_a46304.shinmegamitenseidemonica.entities.Demon
import dam_a46304.shinmegamitenseidemonica.ui.CompendiumViewModel


class stock : Fragment() {

    private lateinit var binding : FragmentStockBinding

    private val viewModel : CompendiumViewModel by viewModels ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentStockBinding.inflate( layoutInflater )
        viewModel.getStockDemons()

        var adapter = SMTAdapter(activity as FusionMain)
        binding.demonList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi ( adapter : SMTAdapter) {
        viewModel.stock.observe( viewLifecycleOwner  ) {demons ->
            adapter.submitList( demons )
        }
    }

}