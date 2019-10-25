package mx.edu.ittepic.tpdm_u3_practica3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    var descripcion : EditText?= null
    var monto : EditText?= null
    var fecha : EditText?= null
    var listaView : ListView?= null
    var insertar : Button?= null
    var actualizar : Button?= null
    var radio1 : CheckBox?= null
    var varPagado = ""
    var baseRemota = FirebaseFirestore.getInstance()

    //declarar objetos tipo arreglo dinamico
    var registrosRemotos = ArrayList<String>()
    var keys = java.util.ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        descripcion = findViewById(R.id.IDescripcion)
        monto = findViewById(R.id.IDMonto)
        fecha = findViewById(R.id.IDFecha)
        listaView = findViewById(R.id.IDLista)
        insertar = findViewById(R.id.btnInsertar)
        radio1 = findViewById(R.id.radio1)

        insertar?.setOnClickListener {
            if(radio1?.isChecked == true){
                Toast.makeText(this,"Elegiste SI", Toast.LENGTH_SHORT).show()
                varPagado = "true"
            }
            else{
                varPagado = "false"
            }

            var insertarDatos = hashMapOf(
                "descripcion" to descripcion?.text.toString(),
                "monto" to monto?.text.toString().toDouble(),
                "fechaVencimiento" to fecha?.text.toString(),
                "pagado" to varPagado
                //"pagado" to pagado?.text.toString()
            )

            baseRemota.collection("recibopagos").add(insertarDatos as Map<String, Any>)
                .addOnSuccessListener {
                    Toast.makeText(this,"Se inserto correctamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this,"No se pudo insertar", Toast.LENGTH_SHORT).show()
                }
            limpiarCampos()
        }

        baseRemota.collection("recibopagos").addSnapshotListener { querySnapshot, e ->
            if(e != null){
                Toast.makeText(this,"Error, No se pudo realizar la consulta", Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            registrosRemotos.clear()
            keys.clear()

            for (document in querySnapshot!!){
                var cadena = document.getString("descripcion")+" -- "+document.getDouble("monto")+" -- "+
                        document.getString("fechaVencimiento")+"\n"
                registrosRemotos.add(cadena)
                keys.add(document.id)
            }

            if(registrosRemotos.size == 0){
                registrosRemotos.add("NO HAY REGISTROS PARA MOSTRAR")
            }

            var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, registrosRemotos)
            listaView?.adapter = adapter
        }
        


    }
    fun limpiarCampos(){
        descripcion?.setText("")
        monto?.setText("")
        fecha?.setText("")
        radio1?.setChecked(false)
        //pagado?.setText("")
    }
}
