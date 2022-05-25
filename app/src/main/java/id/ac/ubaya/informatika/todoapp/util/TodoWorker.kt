package id.ac.ubaya.informatika.todoapp.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TodoWorker(val context: Context, val params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
//<<<<<<< HEAD
        NotificationHelper(context)
            .createNotification(inputData.getString("TITLE").toString(),
                                inputData.getString("MESSAGE").toString())
//=======
        NotificationHelper(context).createNotification(inputData.getString("TITLE").toString(),
            inputData.getString("MESSAGE").toString())
//>>>>>>> origin/master
        return Result.success()
    }
}