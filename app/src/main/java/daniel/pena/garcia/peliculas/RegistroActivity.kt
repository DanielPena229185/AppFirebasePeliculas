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

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;
    lateinit var tv_iniciar_sesion : TextView;
    lateinit var btn_registrarme: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        tv_iniciar_sesion = findViewById(R.id.tv_iniciar_sesion_registrarme);
        btn_registrarme = findViewById(R.id.btn_registrarme_registrarme);
        auth = Firebase.auth;

        btn_registrarme.setOnClickListener {
            var nombre = findViewById<EditText>(R.id.et_nombre_registro).text.toString();
            var genero = findViewById<EditText>(R.id.et_genero_registrarme).text.toString();
            var edad = findViewById<EditText>(R.id.et_edad_registrarme).text.toString();
            var telefono = findViewById<EditText>(R.id.et_telefono_registrarme).text.toString();
            var correo = findViewById<EditText>(R.id.et_correo_registrarme).text.toString();
            var contrasena = findViewById<EditText>(R.id.et_contrasena_registrarme).text.toString();
            var confirmar_contrasena = findViewById<EditText>(R.id.et_confirmar_contrasena_registrarme).text.toString();

            if(!correo.isNullOrEmpty() && !contrasena.isNullOrEmpty() && !confirmar_contrasena.isNullOrEmpty()){
                if(contrasena == confirmar_contrasena){
                    // Sign in success, update UI with the signed-in user's information
                    auth.createUserWithEmailAndPassword(correo, contrasena)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("Exito", "createUserWithEmail:success")
                                val user = auth.currentUser
                                var intent = Intent(this, LoginActivity::class.java);
                                startActivity(intent);
                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Error", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                //updateUI(null)
                            }
                        }
                }else{
                    Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show();
                    // If sign in fails, display a message to the user.
                    Log.w("Error", "signInWithEmail:failure")
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }else{
                Toast.makeText(
                    this,
                    "Debes completar la información",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }

        tv_iniciar_sesion.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java);
            startActivity(intent);
        }

    }
}