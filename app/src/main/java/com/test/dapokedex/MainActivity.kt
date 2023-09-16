package com.test.dapokedex

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.dapokedex.databinding.ActivityMainBinding
import com.test.dapokedex.ui.favorite.FavoritesFragment
import com.test.dapokedex.ui.home.HomeFragment
import com.test.dapokedex.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() =_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCurrentFragment(HomeFragment.newInstance())

        binding.bottomMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeFragment -> setCurrentFragment(HomeFragment.newInstance())
                R.id.profileFragment -> setCurrentFragment(ProfileFragment.newInstance())
                R.id.favoritesFragment -> setCurrentFragment(FavoritesFragment.newInstance())
            }
            true
        }
    }

    override fun onBackPressed() {
        setCurrentFragment(HomeFragment.newInstance())
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }
}