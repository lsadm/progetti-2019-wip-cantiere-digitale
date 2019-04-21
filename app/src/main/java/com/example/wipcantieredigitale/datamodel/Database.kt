import com.example.wipcantieredigitale.lavoratore


object DataBase {

    private var lavoratori = ArrayList<lavoratore>()

    init {
        lavoratori.add(lavoratore("Pino", "AAA"))

    }

    fun getWorkers(): ArrayList<lavoratore> {
        return lavoratori
    }}

