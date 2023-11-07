package dam_a46304.shinmegamitenseidemonica.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dam_a46304.shinmegamitenseidemonica.R

class Login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.EmailLog)
        password = findViewById(R.id.Password)

        mAuth = FirebaseAuth.getInstance();
    }

    fun toRegister(view: View){
        val intent = Intent(this,Register::class.java)
        startActivity(intent)
    }

    fun toCompendium(){
        val intent = Intent(this,Compendium::class.java)
        startActivity(intent)
    }

    fun loginUser(view:View){
        var passwordTxt = password.text.toString().trim()
        var emailTxt = email.text.toString().trim()

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
        if(passwordTxt.isEmpty() || passwordTxt.length < 6){
            password.error = "Input a Password with at least 6 characters"
            password.requestFocus()
            return
        }

        mAuth?.signInWithEmailAndPassword(emailTxt,passwordTxt)?.addOnCompleteListener{
                if(it.isSuccessful){
                    toCompendium()
                }
            if (!it.isSuccessful) {
                Toast.makeText(applicationContext, "Failed Registration: " + it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }


    }
}