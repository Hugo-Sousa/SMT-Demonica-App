package dam_a46304.shinmegamitenseidemonica.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.databinding.FragmentFusionResultEndBinding


class FusionResultEnd : Fragment() {

    private lateinit var binding : FragmentFusionResultEndBinding

    private lateinit var user: FirebaseUser
    private lateinit var ref: DatabaseReference

    private lateinit var userID: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFusionResultEndBinding.inflate(layoutInflater)
        binding.resultDemon = (activity as FusionMain).resultDemon

        user = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().getReference("Users")

        userID = user.uid

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Toast.makeText(context,"Add the fused demon to your stock", Toast.LENGTH_LONG).show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getView()?.findViewById<Button>(R.id.stockBtn)?.setOnClickListener {
            addStock()
            Navigation.findNavController(view).navigate(R.id.action_fusionResultEnd_to_fusion2)
            (activity as FusionMain).recreate()
        }
    }

    fun addStock(){
        ref.child(userID).child("demonStock").child((activity as FusionMain).resultDemon!!.name).setValue((activity as FusionMain).resultDemon)
        ref.child(userID).child("demonStock").child((activity as FusionMain).demon1.name).removeValue()
        ref.child(userID).child("demonStock").child((activity as FusionMain).demon2.name).removeValue()
        Toast.makeText(context, (activity as FusionMain).resultDemon!!.name + " was added to your stock !", Toast.LENGTH_LONG).show()
    }
}