package com.pmdm.ieseljust.comptador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var comptador=0
    lateinit var textViewContador: TextView
    private val TAG = "Proves"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Referencia al TextView
        textViewContador=findViewById<TextView>(R.id.textViewComptador)
        // Referencia al boto d'Open
        val btOpen=findViewById<Button>(R.id.btOpen)

        // Inicialitzem el TextView amb el comptador a 0
        textViewContador.text=comptador.toString() // Estem fent una assignacio directament o accedinta algun metode?

        // Referencia al botón
        val btAdd=findViewById<Button>(R.id.btAdd)
        val btRes=findViewById<Button>(R.id.btRes)
        val btReset=findViewById<Button>(R.id.btReset)

        // Asociaciamos una expresióin lambda como
        // respuesta (callback) al evento Clic sobre
        // el botón
        btAdd.setOnClickListener {
            comptador++
            textViewContador.text=comptador.toString()
        }

        btRes.setOnClickListener {
            comptador--
            textViewContador.text=comptador.toString()
        }

        btReset.setOnClickListener {
            comptador = 0
            textViewContador.text=comptador.toString()
        }

        /*btOpen.setOnClickListener{
            val intent = Intent(baseContext, MostraComptadorActivity::class.java)
            intent.putExtra("comptador", comptador)
            startActivity(intent)
        }*/

        btOpen.setOnClickListener {
            Intent(baseContext, MostraComptadorActivity::class.java).apply {
                putExtra("comptador", comptador)
                startActivity(this)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Estic en onStart")
    }

    override fun onRestoreInstanceState(estat: Bundle) {
        super.onRestoreInstanceState(estat)
        // Codi per a guardar l'estat
        comptador=estat.getInt("comptador")
        textViewContador.text = comptador.toString()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Estic en onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Estic en onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Estic en onStop")
    }
    
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Estic en onRestart")
    }

    override fun onSaveInstanceState(estat: Bundle) {
        super.onSaveInstanceState(estat)
        // Codi per a guardar l'estat
        estat.putInt("comptador", comptador)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Estic en onDestroy")
    }
}