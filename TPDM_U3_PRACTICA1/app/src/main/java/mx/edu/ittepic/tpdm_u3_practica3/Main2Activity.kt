package mx.edu.ittepic.tpdm_u3_practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import com.google.firebase.firestore.FirebaseFirestore

class Main2Activity : AppCompatActivity() {
    var descripcion : EditText?= null
    var monto : EditText?= null
    var fecha : EditText?= null
    var listaView : ListView?= null
    var Actualizar : Button?= null
    var radio1 : CheckBox?= null
    var varPagado = ""
    var baseRemota = FirebaseFirestore.getInstance()

    //declarar objetos tipo arreglo dinamico
    var registrosRemotos = ArrayList<String>()
    var keys = java.util.ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        descripcion = findViewById(R.id.IDescripcion)
        monto = findViewById(R.id.IDMonto)
        fecha = findViewById(R.id.IDFecha)
        listaView = findViewById(R.id.IDLista)
        Actualizar = findViewById(R.id.btnActualizar)
        radio1 = findViewById(R.id.radio1)

    }
}
