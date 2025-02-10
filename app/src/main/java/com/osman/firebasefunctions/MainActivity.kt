package com.osman.firebasefunctions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.osman.firebasefunctions.databinding.ActivityMainBinding
import com.osman.firebasefunctions.ui.theme.FirebaseFunctionsTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var functions: FirebaseFunctions
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Burada inflate et
        setContentView(binding.root)

        functions = Firebase.functions
        db = Firebase.firestore


        /*
                   functions.getHttpsCallable("helloWorld").call().addOnSuccessListener { result->
                     val data=  result.getData()
                       println(data)
                   }.addOnFailureListener{exception->
                       Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
                   }*/
    }


    fun yukle(view: View) {

        val miktar = binding.miktar.text.toString().toDoubleOrNull()
        val fiyat = binding.fiyat.text.toString().toDoubleOrNull()

        val veriMap = hashMapOf<String, Double>()
        if (miktar != null && fiyat != null) {
            veriMap.put("miktar", miktar)
            veriMap.put("fiyat", fiyat)

            db.collection("alisveris")
                .document("sepet")
                .set(veriMap)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        println("kayıt edildi")
                    }

                }.addOnFailureListener { exception ->
                    Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseFunctionsTheme {
        Greeting("Android")
    }
}