package dam_a46304.shinmegamitenseidemonica

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.work.ListenableWorker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dam_a46304.shinmegamitenseidemonica.entities.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

class SMTRep (application: Application){

    private val demonDao : DemonDao
    private val raceDao : RaceDao
    private val skillDao : SkillDao

    init {
        val smtDatabase = SMTDatabase.getInstance(application);
        demonDao = smtDatabase.demonDao()
        raceDao = smtDatabase.raceDao()
        skillDao = smtDatabase.skillDao()
    }

    fun getAllDemons(): LiveData<List<Demon>>{
        return demonDao.getAllDemons()
    }

    fun getDemon(name: String): LiveData<Demon>{
        return demonDao.getDemon(name)
    }

    fun getSkills(names: List<String>): List<Skill>{
        return skillDao.getSkillsByName(names)
    }

    fun getRace(name:String): Race{
        return raceDao.getRace(name)
    }

    fun getDemonToFuse(race: String, lvl:Int): List<Demon>{
        return demonDao.getDemonToFuse(race,lvl)
    }


    fun InsertData (context:Context){
        runBlocking {
            launch(newSingleThreadContext("demonData")) {
                try {
                    context.assets.open("demon_data.json").use { inputStream ->
                        JsonReader(inputStream.reader()).use { jsonReader ->
                            val demonType = object : TypeToken<List<Demon>>() {}.type
                            val demonList: List<Demon> = Gson().fromJson(jsonReader, demonType)
                            val database = SMTDatabase.getInstance(context)
                            database.demonDao().insertDemonList(demonList)
                            Log.e("DemonDatabaseTEST", "INSERTED")
                        }
                    }
                }catch (ex: Exception) {
                    Log.e("DemonDatabaseTEST", " Error seeding database ", ex)
                }
            }
        }
        runBlocking {
            launch(newSingleThreadContext("raceData")) {
                try {
                    context.assets.open("race_data.json").use { inputStream ->
                        JsonReader(inputStream.reader()).use { jsonReader ->
                            val raceType = object : TypeToken<List<Race>>() {}.type
                            val raceList: List<Race> = Gson().fromJson(jsonReader, raceType)
                            val database = SMTDatabase.getInstance(context)
                            database.raceDao().insertRaceList(raceList)
                            Log.e("DemonDatabaseTEST", "INSERTED")
                        }
                    }
                }catch (ex: Exception) {
                    Log.e("DemonDatabaseTEST", " Error seeding database ", ex)
                }
            }
        }
        runBlocking {
            launch(newSingleThreadContext("skillData")) {
                try {
                    context.assets.open("skill_data.json").use { inputStream ->
                        JsonReader(inputStream.reader()).use { jsonReader ->
                            val skillType = object : TypeToken<List<Skill>>() {}.type
                            val skillList: List<Skill> = Gson().fromJson(jsonReader, skillType)
                            val database = SMTDatabase.getInstance(context)
                            database.skillDao().insertSkillList(skillList)
                            Log.e("DemonDatabaseTEST", "INSERTED")
                        }
                    }
                }catch (ex: Exception) {
                    Log.e("DemonDatabaseTEST", " Error seeding database ", ex)
                }
            }
        }
    }
}