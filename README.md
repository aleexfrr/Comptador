# COMPTADOR

## Anàlisi de l'estructura del projecte

1. **Tipus de projecte**:
   - Descripció del projecte:
     Aquest projecte és una aplicació Android que actua com a comptador. L’aplicació permet incrementar,
     decrementar i resetejar el valor del comptador mitjançant botons. A més, inclou la funcionalitat per
     obrir una nova activitat que mostra el valor actual del comptador. També es gestionen els canvis
     d’estat de l’activitat per mantenir el valor del comptador en cas de canvis d’orientació o altres interrupcions.
     
   - Estructura del projecte (incloure captures de pantalla si és possible).
     1. Arrel del projecte:
        - build.gradle
        - settings.gradle
     2. Directori app:
        - src/main/java/com/pmdm/ieseljust/comptador/MainActivity.kt
        - src/main/java/com/pmdm/ieseljust/comptador/MostraComptadorActivity.kt
        - src/main/res/layout/activity_main.xml
        - src/main/res/layout/activity_mostra_comptador.xml
        - src/main/AndroidManifest.xml
        
   - Fitxers i carpetes més importants.
     - MainActivity.kt:
     - MostraComptadorActivity.kt:
     - activity_mostra_comptador.xml:
     - AndroidManifest.xml:

2. **Estructura d'una Activitat**:
   - Descripció de l'estructura d'una activitat.
     Una Activitat en Android és una classe que proporciona una pantalla amb la qual els
     usuaris poden interactuar per realitzar alguna acció. Cada activitat es compon de dues
     parts principals: la part lògica i la part gràfica.
   - Fitxers implicats.
     - Fitxer Kotlin (MainActivity.kt)
     - Fitxer XML del layout (activity_main.xml)
     - AndroidManifest.xml

## Modificacions inicials

1. **Reemplaçar el layout de l'activitat principal**:
   - Substituir el layout existent pel layout proporcionat.
   - Exemple de codi XML del nou layout.

2. **Afegir funcionalitat als botons**:
   - Afegir codi a l'activitat principal per dotar de funcionalitat els botons de decrementar i ressetejar.
   - Exemple de codi Kotlin del botó `Reset`:
     ```kotlin
     btRes.setOnClickListener {
         comptador--
         textViewContador.text = comptador.toString()
     }
     ```

## Cicle de vida i la pèrdua d'estat

1. **Implementació dels mètodes del cicle de vida**:
   - Implementar els mètodes `onStart()`, `onResume()`, `onPause()`, `onStop()`, `onRestart()`, `onDestroy()`.
   - Exemple de codi per al mètode `onStart()`:
     ```kotlin
     override fun onStart() {
         super.onStart()
         Log.d(TAG, "Al mètode onStart") // TAG és una etiqueta prèviament definida
     }
     ```

2. **Investigació del mal funcionament per canvis d'orientació**:
   - Solucions per mantenir l'estat de l'aplicació (implementar els mètodes `onRestoreInstanceState()`, `onSaveInstanceState()`).
     ```kotlin
      override fun onRestoreInstanceState(estat: Bundle) {
        super.onRestoreInstanceState(estat)
        comptador = estat.getInt("comptador")
        textViewContador.text = comptador.toString()
      }

     override fun onSaveInstanceState(estat: Bundle) {
        super.onSaveInstanceState(estat)
        estat.putInt("comptador", comptador)
      }
     ```

## Intents entre activitats

1. **Crear una nova activitat**:
   - Crear el fitxer XML del layout per a la nova activitat.
   - Crear el fitxer Kotlin per gestionar la nova activitat.

2. **Afegir botó per obrir la nova activitat**:
   - Afegir un botó amb el text `Open` al disseny de l'activitat principal.
   - Exemple de codi XML per al botó:
     ```xml
     <Button
         android:id="@+id/button_open"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         android:text="Open" />
     ```

3. **Implementar la funcionalitat del botó**:
   - Afegir codi a l'activitat principal per obrir la nova activitat quan es faci clic en el botó.
   - Exemple de codi Kotlin per al botó:
     ```kotlin
     val buttonOpen = findViewById<Button>(R.id.button_open)
     btOpen.setOnClickListener {
         Intent(baseContext, MostraComptadorActivity::class.java).apply {
             putExtra("comptador", comptador)
             startActivity(this)
         }
     }
     ```

4. **Implementar la nova activitat**:
   - Mostrar el valor del comptador en un `TextView`.
   - Afegir un botó amb el text `Close` per tancar la nova activitat.
   - Exemple de codi Kotlin per a la nova activitat:
     ```kotlin
     class MostraComptadorActivity : AppCompatActivity() {
      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          enableEdgeToEdge()
          setContentView(R.layout.activity_mostra_comptador)
          ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
              val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
              v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
              insets
          }
          val textViewContador=findViewById<TextView>(R.id.textViewMostraComptador)
          val btBack=findViewById<Button>(R.id.btBack)
          val comptador:Int? = intent.getIntExtra("comptador", 0)
          textViewContador.text=comptador.toString()
          btBack.setOnClickListener {
              finish()
          }
       }
     }
     ```

## Qüestió

Per crear una nova activitat, seria suficient amb crear el fitxer XML amb el layout i el fitxer Kotlin amb el codi per gestionar-la? 
Sí, aquests són els passos bàsics per crear una nova activitat en una aplicació Android.
- Crear la nova activitat
- Fitxer Kotlin
- Fitxer XML del layout
- Registrar l’activitat en AndroidManifest.xml
- Afegir la lògica per obrir la nova activitat
