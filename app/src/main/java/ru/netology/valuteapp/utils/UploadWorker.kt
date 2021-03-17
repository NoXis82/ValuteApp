package ru.netology.valuteapp.utils

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.netology.valuteapp.App

class UploadWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val repository = App.repository
    override suspend fun doWork(): Result {
        return try {
            withContext(Dispatchers.IO) {
                repository.getAll()
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}