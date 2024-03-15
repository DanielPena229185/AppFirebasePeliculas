package daniel.pena.garcia.peliculas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RecuperacionActivity : AppCompatActivity() {

    lateinit var btn_recuperar_cuenta: Button;
    lateinit var btn_iniciar_sesion: Button;
    lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)

        btn_recuperar_cuenta = findViewById(R.id.btn_recuperar_cuenta);
        btn_iniciar_sesion = findViewById(R.id.btn_iniciar_sesion);
        auth = Firebase.auth;

        btn_recuperar_cuenta.setOnClickListener {
            var correo_recuperacion = findViewById<EditText>(R.id.et_correo_recuperacion).text.toString();
            Firebase.auth.sendPasswordResetEmail(correo_recuperacion)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Success", "Email sent.")
                    }else{
                        Toast.makeText(this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                    }
                }
        }

        btn_iniciar_sesion.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java);
            startActivity(intent);
        }


    }
}