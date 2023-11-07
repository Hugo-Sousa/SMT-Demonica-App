package dam_a46304.shinmegamitenseidemonica.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dam_a46304.shinmegamitenseidemonica.R
import dam_a46304.shinmegamitenseidemonica.entities.User


class Register : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance();
        Log.d("TEST", mAuth.toString())
        username = findViewById(R.id.Username)
        password = findViewById(R.id.Password)
        email = findViewById(R.id.Email)
    }

    fun Register(view: View){
        var emailTxt = email.text.toString().trim()
        var passwordTxt = password.text.toString().trim()
        var usernameTxt = username.text.toString().trim()

        if(usernameTxt.isEmpty()){
            username.error = "Input a Username"
            username.requestFocus()
            return
        }
        if(passwordTxt.isEmpty() || passwordTxt.length < 6){
            password.error = "Input a Password with at least 6 characters"
            password.requestFocus()
            return
        }

        if(emailTxt.isEmpty()){
            email.error = "Input a Email"
            email.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()){
            email.error = "Input a valid Email"
            email.requestFocus()
            return
        }

        mAuth?.createUserWithEmailAndPassword(emailTxt, passwordTxt)
            ?.addOnCompleteListener { p0 ->
                if (p0.isSuccessful) {
                    val user = User(usernameTxt, passwordTxt, emailTxt, listOf())
                    val ref = FirebaseDatabase.getInstance().getReference("Users")
                    ref.child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .setValue(user).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(applicationContext, "User created", Toast.LENGTH_LONG).show()
                                    val intent = Intent(applicationContext,Login::class.java)
                                    startActivity(intent)
                                }
                                if (!task.isSuccessful) {
                                    Toast.makeText(applicationContext, "Failed Database: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                                }
                        }
                }
                if (!p0.isSuccessful) {
                    Toast.makeText(applicationContext, "Failed Registration: " + p0.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }


}

