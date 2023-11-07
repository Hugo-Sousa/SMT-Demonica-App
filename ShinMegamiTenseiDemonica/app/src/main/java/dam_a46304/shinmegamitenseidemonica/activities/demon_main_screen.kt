package dam_a46304.shinmegamitenseidemonica.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.databinding.FragmentDemonMainScreenBinding
import dam_a46304.shinmegamitenseidemonica.ui.DemonDetailViewModel

class demon_main_screen : Fragment(){

    private lateinit var bindingMain : FragmentDemonMainScreenBinding
    private val viewModel : DemonDetailViewModel by viewModels()

    private lateinit var user: FirebaseUser
    private lateinit var ref: DatabaseReference

    private lateinit var userID: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.demon = (activity as DemonDetail).demon

        bindingMain = FragmentDemonMainScreenBinding.inflate( layoutInflater )
        bindingMain.lifecycleOwner = this
        bindingMain.vm = viewModel
        bindingMain.demon = viewModel.demon

        viewModel.getDemon((activity as DemonDetail).application)

        user = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().getReference("Users")

        userID = user.uid

        return bindingMain.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getView()?.findViewById<Button>(R.id.stockBtn)?.setOnClickListener {
            addStock()
        }
    }

    fun addStock(){
        ref.child(userID).child("demonStock").child(viewModel.demon.name).setValue(viewModel.demon)
        Toast.makeText(context, viewModel.demon.name + " was added to your stock !",Toast.LENGTH_LONG).show()
    }
}