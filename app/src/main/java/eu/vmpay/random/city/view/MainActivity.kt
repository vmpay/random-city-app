package eu.vmpay.random.city.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.vmpay.random.city.R
import eu.vmpay.random.city.repository.Producer
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val producer: Producer by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        producer.start()
    }

    override fun onPause() {
        super.onPause()
        producer.stop()
    }
}
