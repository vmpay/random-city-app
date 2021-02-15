package eu.vmpay.random.city.view

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import eu.vmpay.random.city.R
import eu.vmpay.random.city.repository.Producer
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val producer: Producer by inject()
    private var toolbar: Toolbar? = null
    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RandomCity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setActionBar(toolbar)
        controller = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    override fun onResume() {
        super.onResume()
        producer.start()
        controller.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        controller.removeOnDestinationChangedListener(listener)
        producer.stop()
        super.onPause()
    }

    private val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
        if (destination.id != R.id.loaderFragment) toolbar?.visibility = View.VISIBLE
    }

    fun setToolbarTitleColor(cityTitle: String = getString(R.string.app_name), @ColorInt color: Int = getColor(R.color.purple_200)) {
        toolbar?.apply {
            title = cityTitle
            setBackgroundColor(color)
        }
    }
}
