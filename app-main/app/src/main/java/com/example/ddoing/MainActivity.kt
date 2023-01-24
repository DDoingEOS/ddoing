package com.example.ddoing

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ddoing.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    lateinit var homeFragment: HomeFragment
    lateinit var calenderFragment: CalenderFragment
    lateinit var socialFragment: SocialFragment
    lateinit var navController: NavController

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationview.setOnNavigationItemSelectedListener {
            val ap = true
            when (it.itemId){
                R.id.homeFragment->{
                    home
                }
                R.id.calenderFragment->{
                    changeFragment(CalenderFragment())
                }
                R.id.socialFragment->{
                    changeFragment(SocialFragment())
                }
            }
        }

        binding.bottomNavigationview.setOnItemSelectedListener{ item ->
            when (item.itemId){
                R.id.homeFragment->{
                    changeFragment(HomeFragment())
                }
                R.id.calenderFragment->{
                    changeFragment(CalenderFragment())
                }
                R.id.socialFragment->{
                    changeFragment(SocialFragment())
                }
            }
            true
        }
    }
    fun changeFragment(fragment: Fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


}