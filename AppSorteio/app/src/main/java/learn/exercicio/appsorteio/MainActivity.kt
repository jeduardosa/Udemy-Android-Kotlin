package learn.exercicio.appsorteio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
  //My first kotlin app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sortearNumero() {
        var texto = findViewById<TextView>(R.id.textoSorteio)
        var numeroSorteado = java.util.Random().nextInt(11)
        texto.setText("O número sorteado é: " + numeroSorteado)
    }
}