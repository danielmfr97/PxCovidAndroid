package br.com.daniel.ramos.pacientmvp

import android.R.attr.textColor
import android.R.attr.textStyle
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.daniel.ramos.pacientmvp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import org.koin.android.ext.android.bind


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    init {
        MyApplication.configurarRealm()
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarToolbar()
        configurarNavController()
    }

    private fun configurarToolbar() {
        setSupportActionBar(binding.toolbarMain)
    }

    private fun configurarNavController() {
        navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.splashFragment -> binding.toolbarMain.visibility = View.GONE
                else -> binding.toolbarMain.visibility = View.VISIBLE
            }
        }
        appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment).build()
        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    companion object {
        @JvmStatic
        var instance: MainActivity? = null

        fun showSnackbar(text: String, color: Int) {
            val snackbar =
                instance?.binding?.root?.let {
                    Snackbar.make(
                        it,
                        text, Snackbar.LENGTH_SHORT
                    )
                }
            snackbar?.view?.setBackgroundColor(color)
            val tv =
                snackbar?.view?.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            tv?.apply {
                setTextColor(ContextCompat.getColor(instance?.applicationContext!!, R.color.black))
                setTextSize(16F)
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }
            snackbar?.show()
        }
    }
}