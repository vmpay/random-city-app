package eu.vmpay.random.city.repository

import android.util.Log
import eu.vmpay.random.city.tools.PRODUCER_DELAY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class Producer(private val channel: Channel<Pair<String, String>>) {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var job: Job

    private val cities = listOf("Gdańsk", "Warszawa", "Poznań", "Białystok", "Wrocław", "Katowice", "Kraków")
    private val colors = listOf("Yellow", "Green", "Blue", "Red", "Black", "White")

    fun start() {
        Log.d("Producer", "Starting job")
        job = applicationScope.launch {
            while (true) {
                channel.send(getPair())
                delay(PRODUCER_DELAY)
            }
        }
    }

    fun stop() {
        Log.d("Producer", "Stopping job")
        if (!job.isCancelled)
            job.cancel()
    }

    private fun getPair(): Pair<String, String> = Pair(
            cities[Random.nextInt(cities.size)],
            colors[Random.nextInt(colors.size)]
    ).also { Log.d("Producer", "Sending $it") }
}
