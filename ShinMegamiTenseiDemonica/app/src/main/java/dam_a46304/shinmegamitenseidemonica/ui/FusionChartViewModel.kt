package dam_a46304.shinmegamitenseidemonica.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dam_a46304.shinmegamitenseidemonica.SMTRep
import dam_a46304.shinmegamitenseidemonica.entities.Demon
import dam_a46304.shinmegamitenseidemonica.entities.Race
import dam_a46304.shinmegamitenseidemonica.entities.Skill

class FusionChartViewModel constructor (
        private val savedStateHandle : SavedStateHandle,
) : ViewModel() {

    var smtRep: SMTRep? = null
    lateinit var race : Race
    lateinit var fusionResults: List<Demon>
    var possibleResults: MutableLiveData<List<Demon>> = MutableLiveData()

    fun getRace(application: Application, name:String){
        smtRep = SMTRep(application)
        race = smtRep!!.getRace(name)
    }

    fun getDemonToFuse(application: Application, race:String, lvl:Int): Demon?{
        smtRep = SMTRep(application)
        fusionResults = smtRep!!.getDemonToFuse(race,lvl)
        if(fusionResults.isEmpty()){
            return null
        }
        return fusionResults[0]
    }
}