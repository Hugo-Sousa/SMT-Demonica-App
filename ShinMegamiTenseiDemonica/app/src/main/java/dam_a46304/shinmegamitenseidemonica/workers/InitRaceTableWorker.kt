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
import dam_a46304.shinmegamitenseidemonica.entities.Race
import kotlinx.coroutines.coroutineScope

class InitRaceTableWorker (var context : Context,
                        var workerParams : WorkerParameters
                        ) : CoroutineWorker(context , workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            context.assets.open("race_data.json").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val raceType = object : TypeToken<List<Race>>() {}.type
                    val raceList: List<Race> = Gson().fromJson(jsonReader, raceType)
                    val database = SMTDatabase.getInstance(context)
                    database.raceDao().insertRaceList(raceList)
                    Log.d("Rep","Inserted")
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e("DemonDatabase", " Error seeding database ", ex)
            Result.failure()
        }
    }
}