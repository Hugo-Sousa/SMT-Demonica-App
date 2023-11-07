package dam_a46304.shinmegamitenseidemonica.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dam_a46304.shinmegamitenseidemonica.SMTDatabase
import dam_a46304.shinmegamitenseidemonica.entities.Demon
import dam_a46304.shinmegamitenseidemonica.entities.Skill
import kotlinx.coroutines.coroutineScope

class InitSkillTableWorker (var context : Context,
                            var workerParams : WorkerParameters
                           ) : CoroutineWorker(context , workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            context.assets.open("skill_data.json").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val skillType = object : TypeToken<List<Skill>>() {}.type
                    val skillList: List<Skill> = Gson().fromJson(jsonReader, skillType)
                    val database = SMTDatabase.getInstance(context)
                    database.skillDao().insertSkillList(skillList)
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e("DemonDatabase", " Error seeding database ", ex)
            Result.failure()
        }
    }
}