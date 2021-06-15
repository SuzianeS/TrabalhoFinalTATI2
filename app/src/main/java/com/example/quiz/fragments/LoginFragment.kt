package com.example.quiz.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.quiz.ControllerGame
import com.example.quiz.GameActivity
import com.example.quiz.R
import com.example.quiz.SessionController
import com.example.quiz.dao.UserDAO
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    private var dao: UserDAO = UserDAO()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val t = SignUpFragment()
        view.cadastrar.setOnClickListener { fragment(t) }
        view.login.setOnClickListener { login(view) }
        return view
    }

    private fun fragment(fragment: Fragment, addToBackStack: Boolean = true){
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.ConstraintLayout, fragment)
            if(addToBackStack)
                addToBackStack(null)
            commit()
        }
    }

     private fun login(view: View){
         val email = view.editEmail.text.toString()
         val password = view.editTextPass.text.toString()

         dao.logar(email, password, { body ->
             if(body.status == "success"){
                 val user = body.data?.user!!
                 SessionController.setUser(requireActivity(), user)

                 val intent = Intent(activity, GameActivity::class.java)
                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent)

             }else{

             }
         },{

         })
    }

}
