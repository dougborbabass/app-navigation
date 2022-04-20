package br.com.douglas.navigationcomponentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.douglas.navigationcomponentapp.ui.profile.ProfileFragment
import br.com.douglas.navigationcomponentapp.ui.start.StartFragment

class MainActivity : AppCompatActivity(), StartFragment.OnButtonClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.myContainer, StartFragment.newInstance())
            .commit()
    }

    override fun buttonCliecked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.myContainer, ProfileFragment.newInstance())
            .commit()
    }
}