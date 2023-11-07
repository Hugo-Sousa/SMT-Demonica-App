package dam_a46304.shinmegamitenseidemonica.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.adapter.SMTAdapter
import dam_a46304.shinmegamitenseidemonica.entities.Demon
import dam_a46304.shinmegamitenseidemonica.entities.Skill
import dam_a46304.shinmegamitenseidemonica.enums.RaceEnum
import dam_a46304.shinmegamitenseidemonica.ui.CompendiumViewModel
import dam_a46304.shinmegamitenseidemonica.ui.DemonDetailViewModel
import dam_a46304.shinmegamitenseidemonica.ui.FusionChartViewModel


class FusionMain : AppCompatActivity() {

    lateinit var demon1: Demon
    lateinit var demon2: Demon
    var resultDemon: Demon? = null

    var ogSkills: List<String> = listOf()

    var resultList: MutableLiveData<List<Demon>> = MutableLiveData()

    var slotSelected: Int = 1

    private val viewModelChart : FusionChartViewModel by viewModels ()
    val viewModelDetail : DemonDetailViewModel by viewModels ()

    private lateinit var user: FirebaseUser
    private lateinit var ref: DatabaseReference

    private lateinit var userID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fusion_main)
    }

    fun setDemonToFuse(demon: Demon){
        when(slotSelected) {
            1 -> demon1 = demon
            2 -> demon2 = demon
        }
    }

    fun checkIfInitialized(slot: Int): Boolean{
        when(slot){
            1 -> {
                if (this::demon1.isInitialized) {
                    return true
                }
            }
            2 -> {
                if (this::demon2.isInitialized) {
                    return true
                }
            }
            3 -> {
                if (resultDemon != null) {
                    return true
                }
            }
        }
        return false
    }

    fun back(view: View){
        Navigation.findNavController(view).navigate(R.id.action_stock_to_fusion2)
    }

    fun getResult(){
        if(checkIfInitialized(1) && checkIfInitialized(2)){
            runOnUiThread {
                viewModelChart.getRace(application, demon1.race)
                var demonRace1 = viewModelChart.race

                val resultRace = demonRace1.results[RaceEnum.valueOf(demon2.race).ordinal]
                val resultLvl = ((demon1.lvl + demon2.lvl) / 2) + 1
                resultDemon = if(resultRace != "-") {
                    viewModelChart.getDemonToFuse(application, resultRace, resultLvl)
                } else{ null }

            }
        }
    }

    fun transferSkill(skillName: String): Boolean{

        var wastrans = false

        if(skillName !in resultDemon!!.skills) {
            resultDemon!!.skills += skillName
            Log.d("TEST","$skillName Was transfered !")
            wastrans = true

            if(skillName in demon1.skills){
                demon1.skills -= skillName
            }

            if(skillName in demon2.skills){
                demon2.skills -= skillName
            }

            viewModelDetail.demon = resultDemon!!

            viewModelDetail.getDemon(application)
            viewModelDetail.getSkills(application)
            viewModelDetail.getSkillsToTrans(application,demon1, demon2)
        }

        return wastrans
    }

    fun detransferSkill(skillName: String){

        resultDemon!!.skills -= skillName

        if(skillName !in demon1.skills){
            demon1.skills += skillName
        }

        if(skillName !in demon2.skills){
            demon2.skills += skillName
        }

        viewModelDetail.getDemon(application)
        viewModelDetail.getSkills(application)
        viewModelDetail.getSkillsToTrans(application,demon1, demon2)

        Log.d("TEST","$skillName Was returned !")
    }

    fun getResultList(){
        var stock : ArrayList<Demon> = ArrayList()
        user = FirebaseAuth.getInstance().currentUser!!
        ref = FirebaseDatabase.getInstance().getReference("Users")

        userID = user.uid

        ref.child(userID).child("demonStock").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach{
                    stock.add(it.getValue(Demon::class.java)!!)
                }
                getPossibleFromStock(stock)
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun getPossibleFromStock(stock: ArrayList<Demon>){
        var list : ArrayList<Demon> = ArrayList()
        stock.forEach { demon1 ->
            stock.forEach { demon2 ->
                if(demon1 != demon2){
                    viewModelChart.getRace(application, demon1.race)
                    var demonRace1 = viewModelChart.race

                    val resultRace = demonRace1.results[RaceEnum.valueOf(demon2.race).ordinal]
                    val resultLvl = ((demon1.lvl + demon2.lvl) / 2) + 1
                    val resultDemon = if(resultRace != "-") {
                        viewModelChart.getDemonToFuse(application, resultRace, resultLvl)
                    } else{ null }

                    if (resultDemon != null) {
                        list.add(resultDemon)
                    }
                }
            }
        }
        Log.d("TEST",list.toString())
        resultList.postValue(list)
        viewModelChart.possibleResults = resultList
    }

}