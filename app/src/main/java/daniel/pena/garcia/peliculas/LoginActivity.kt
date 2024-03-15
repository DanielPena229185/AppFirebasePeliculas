package daniel.pena.garcia.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    lateinit var btn_ingresar: Button;
    lateinit var btn_registrar: Button;
    lateinit var tv_recuperar_contrasena: TextView;
    lateinit var auth: FirebaseAuth;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.btn_ingresar = findViewById(R.id.ingresar);
        this.btn_registrar = findViewById(R.id.registrarse);
        this.tv_recuperar_contrasena = findViewById(R.id.recuperar_contrasena);
        this.auth = Firebase.auth;

        btn_registrar.setOnClickListener {
            var intent = Intent(this, RegistroActivity::class.java);
            startActivity(intent);
        }

        tv_recuperar_contrasena.setOnClickListener {
            var intent = Intent(this, RecuperacionActivity::class.java);
            startActivity(intent);
        }

        btn_ingresar.setOnClickListener {
            var correo = findViewById<EditText>(R.id.et_correo).text.toString();
            var contrasena = findViewById<EditText>(R.id.et_contrasena).text.toString();
            auth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Succeful", "signInWithEmail:success")
                        val user = auth.currentUser
                        Toast.makeText(
                            baseContext,
                            "Authentication success.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Error", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        //updateUI(null)
                    }
                }
        }
    }
}