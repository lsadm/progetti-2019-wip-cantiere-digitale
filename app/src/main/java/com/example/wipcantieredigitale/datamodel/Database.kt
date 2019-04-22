import com.example.wipcantieredigitale.datamodel.lavoratore


object DataBase {

    private var lavoratori = ArrayList<lavoratore>()

    init {
        lavoratori.add(lavoratore("Pino", "AAA"))

    }

    fun getWorkers(): ArrayList<lavoratore> {
        return lavoratori
    }}

