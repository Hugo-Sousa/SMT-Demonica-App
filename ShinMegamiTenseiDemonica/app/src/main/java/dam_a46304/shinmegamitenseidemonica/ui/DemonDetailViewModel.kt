package dam_a46304.shinmegamitenseidemonica.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dam_a46304.shinmegamitenseidemonica.SMTRep
import dam_a46304.shinmegamitenseidemonica.entities.Demon
import dam_a46304.shinmegamitenseidemonica.entities.Skill

class DemonDetailViewModel constructor (
    private val savedStateHandle : SavedStateHandle,
) : ViewModel() {

    lateinit var demon: Demon
    var smtRep:SMTRep? = null
    lateinit var demonInfo : LiveData<Demon>
    var demonskills: MutableLiveData<List<Skill>> = MutableLiveData()
    var demonTransSkills: MutableLiveData<List<Skill>> = MutableLiveData()


    fun getDemon(application: Application){
        smtRep = SMTRep(application)
        demonInfo = smtRep!!.getDemon(demon.name)
    }

    fun getSkills(application: Application){
        smtRep = SMTRep(application)
        demonskills.value = smtRep!!.getSkills(demon.skills)
    }

    fun getSkillsToTrans(application: Application, demon1:Demon, demon2:Demon){
        smtRep = SMTRep(application)
        demonTransSkills.value = smtRep!!.getSkills(demon1.skills + demon2.skills)
    }

}