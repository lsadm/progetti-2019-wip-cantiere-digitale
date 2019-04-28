import com.example.wipcantieredigitale.datamodel.compito


object DatabaseC {

    private var compiti = ArrayList<compito>()

    init {
        compiti.add(compito("Primo", "AAA"))

    }

    fun getjobs(): ArrayList<compito> {
        return compiti

    }}

