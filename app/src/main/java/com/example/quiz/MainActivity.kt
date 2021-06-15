package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.quiz.fragments.LoginFragment
import com.example.quiz.network.models.login.UserFieldsLogin


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SessionController.init(this)
        SessionGameController.init(this)

        if (SessionController.isAuthenticated(this)) {
            val intent = Intent(this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)
            finish()
        } else {
            val new = LoginFragment()
            fragment(new, false)
        }

    }

     fun fragment(fragment: Fragment, addToBackStack: Boolean = true){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.ConstraintLayout, fragment)
            if(addToBackStack)
                addToBackStack(null)
            commit()
        }
    }
}
