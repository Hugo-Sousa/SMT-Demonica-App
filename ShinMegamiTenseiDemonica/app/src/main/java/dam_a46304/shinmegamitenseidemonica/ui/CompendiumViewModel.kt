package dam_a46304.shinmegamitenseidemonica.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import dam_a46304.shinmegamitenseidemonica.SMTRep
import dam_a46304.shinmegamitenseidemonica.entities.Demon

class CompendiumViewModel constructor (
        private val savedStateHandle : SavedStateHandle,
        ) : ViewModel() {

        var repository: SMTRep? = null
        lateinit var demonList : LiveData<List<Demon>>
        var stock : MutableLiveData<List<Demon>> = MutableLiveData()

        private lateinit var user: FirebaseUser
        private lateinit var ref: DatabaseReference

        private lateinit var userID: String

    fun GetAllDemons (application: Application)
    {
        repository = SMTRep(application)
        demonList = repository!!.getAllDemons()
    }

    fun getStockDemons(): ArrayList<Demon>{
        var list : ArrayList<Demon> = ArrayList()

        user = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().getReference("Users")

        userID = user.uid

        ref.child(userID).child("demonStock").addListenerForSingleValueEvent(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    list.add(it.getValue(Demon::class.java)!!)
                }
                stock.postValue(list)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        return list
    }

    fun InsertData (application: Application){
        repository = SMTRep(application)
        repository!!.InsertData(application)
    }
}