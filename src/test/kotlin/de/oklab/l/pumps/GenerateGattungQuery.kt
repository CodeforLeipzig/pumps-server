package de.oklab.l.pumps

import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileWriter
import java.nio.charset.Charset

class GenerateGattungQuery {

    @Test
    fun generate() {
        val gattungen = listOf(
            "Abies",
            "Acer",
            "Aesculus",
            "Ailanthus",
            "Alnus",
            "Amelanchier",
            "Betula",
            "Carpinus",
            "Castanea",
            "Catalpa",
            "Celtis",
            "Cornus",
            "Corylus",
            "Crataegus",
            "Cydonia",
            "Elaeagnus",
            "Eriolobus",
            "Fagus",
            "Fraxinus",
            "Ginkgo",
            "Gleditsia",
            "Hippophae",
            "Ilex",
            "Juglans",
            "Juniperus",
            "Koelreuteria",
            "Larix",
            "Liquidambar",
            "Liriodendron",
            "Magnolia",
            "Malus",
            "Mespilus",
            "Metasequoia",
            "Morus",
            "nicht",
            "Ostrya",
            "Picea",
            "Pinus",
            "Platanus",
            "Populus",
            "Prunus",
            "Pseudotsuga",
            "Pyracantha",
            "Pyrus",
            "Quercus",
            "Rhus",
            "Robinia",
            "Salix",
            "Sambucus",
            "Sophora",
            "Sorbus",
            "Syringa",
            "Tamarix",
            "Taxus",
            "Tetradium",
            "Thuja",
            "Tilia",
            "Ulmus",
            "unbekannt",
            "waldartiger",
            "Zelkova"
        )
        val sb = StringBuilder()
        val step = 8
        for (i in (0..(gattungen.size / step))) {
            sb.append(query(gattungen.subList(i*step,
                if (((i+1)*step) > gattungen.size) gattungen.size else (i+1)*step)
            )).append("\n\n")
        }
        FileWriter(File("D:/query-gattungen.txt"), Charset.forName("UTF-8")).use {
            it.write(sb.toString())
        }
    }

    private fun query(gattungen: List<String>) : String {
        return """
           select gt0.Datum, ${select(gattungen)} from
           ${combine(gattungen)} 
        """.trimIndent()
    }

    private fun select(gattungen: List<String>) : String {
        return gattungen.mapIndexed { index, ot ->
            "gt${index}.Anzahl as \"$ot\""
        }.joinToString(", ")
    }

    private fun combine(gattungen: List<String>) : String {
        return gattungen.mapIndexed  { index, ot ->
            " join " + subquery(ot, index) + " on gt${(index-1)}.Datum = gt$index.Datum "
        }.joinToString("")
    }

    private fun subquery(gattung: String, index: Int): String {
        return """
            (select Datum, Anzahl from gattungen where gattung = '${gattung}') as gt$index
        """.trimIndent()
    }
}