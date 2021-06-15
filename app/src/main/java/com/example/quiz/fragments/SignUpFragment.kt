package com.example.quiz.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.quiz.R
import com.example.quiz.SessionGameController
import com.example.quiz.dao.UserDAO
import com.example.quiz.network.models.login.User
import kotlinx.android.synthetic.main.fragment_sign_up.view.*


class SignUpFragment : Fragment() {

    private var access: UserDAO = UserDAO()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        view.register.setOnClickListener { cadastrar(view) }
        return view
    }

    private fun cadastrar(view: View){
        val user = User("","","","")

        user.name = view.editTextUserName.text.toString()
        user.password = view.editTextPassword.text.toString()
        user.email = view.editTextEmail.text.toString()

        if(user.name.isNotEmpty() && user.password.isNotEmpty() && user.email.isNotEmpty() && user.password == view.editTextPasswordConfirm.text.toString()){

            try {
                access.insert(user){ body ->
                    if(body.status == "success"){
                        val toast: Toast = Toast.makeText(activity, "Register Success!", Toast.LENGTH_LONG)
                        toast.setGravity(Gravity.TOP, 0, 10)
                        toast.show()

                        val destiny = LoginFragment()
                        fragment(destiny)
                    }
                }
            }catch (e : Exception){
                cleanFields(view)
            }
        }else{
            cleanFields(view)
        }
    }

    private fun cleanFields(view: View){
        view.editTextUserName.text = null
        view.editTextEmail.text = null
        view.editTextPassword.text = null
        view.editTextPasswordConfirm.text = null
    }


    private fun fragment(fragment: Fragment, addToBackStack: Boolean = true){
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.ConstraintLayout, fragment)
            if(addToBackStack)
                addToBackStack(null)
            commit()
        }
    }

}
