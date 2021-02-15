package eu.vmpay.random.city.view

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import eu.vmpay.random.city.R
import eu.vmpay.random.city.repository.Producer
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val producer: Producer by inject()
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RandomCity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        producer.start()
    }

    override fun onPause() {
        super.onPause()
        producer.stop()
    }

    fun showToolbar() {
        toolbar?.visibility = View.VISIBLE
        setToolbarTitleColor()
    }

    fun setToolbarTitleColor(cityTitle: String = "", @ColorInt color: Int = getColor(R.color.purple_200)) {
        toolbar?.apply {
            title = cityTitle
            setBackgroundColor(color)
        }
    }
}
