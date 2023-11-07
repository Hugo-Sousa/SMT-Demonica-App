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
import kotlinx.coroutines.coroutineScope

class InitDemonTableWorker (var context : Context,
                           var workerParams : WorkerParameters
                           ) : CoroutineWorker(context , workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            context.assets.open("demon_data.json").use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val demonType = object : TypeToken<List<Demon>>(){}.type
                    val demonList: List<Demon> = Gson().fromJson(jsonReader, demonType)
                    val database = SMTDatabase.getInstance(context)
                    database.demonDao().insertDemonList(demonList)
                    Log.e("DemonDatabase", "INSERTED")
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e("DemonDatabase", " Error seeding database ", ex)
            Result.failure()
        }
    }
}