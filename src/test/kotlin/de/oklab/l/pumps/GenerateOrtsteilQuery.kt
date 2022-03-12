package de.oklab.l.pumps

import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileWriter
import java.nio.charset.Charset

class GenerateOrtsteilQuery {

    @Test
    fun generate() {
        val ortsteile = listOf(
            "Althen-Kleinpösna",
            "Altlindenau",
            "Anger-Crottendorf",
            "Baalsdorf",
            "Burghausen-Rückmarsdorf",
            "Böhlitz-Ehrenberg",
            "Connewitz",
            "Dölitz-Dösen",
            "Engelsdorf",
            "Eutritzsch",
            "Gohlis-Mitte",
            "Gohlis-Nord",
            "Gohlis-Süd",
            "Großzschocher",
            "Grünau-Mitte",
            "Grünau-Nord",
            "Grünau-Ost",
            "Grünau-Siedlung",
            "Hartmannsdorf-Knautnaundorf",
            "Heiterblick",
            "Holzhausen",
            "Kleinzschocher",
            "Knautkleeberg-Knauthain",
            "Lausen-Grünau",
            "Leutzsch",
            "Liebertwolkwitz",
            "Lindenau",
            "Lindenthal",
            "Lößnig",
            "Lützschena-Stahmeln",
            "Marienbrunn",
            "Meusdorf",
            "Miltitz",
            "Mockau-Nord",
            "Mockau-Süd",
            "Möckern",
            "Mölkau",
            "Neulindenau",
            "Neustadt-Neuschönefeld",
            "Paunsdorf",
            "Plagwitz",
            "Plaußig-Portitz",
            "Probstheida",
            "Reudnitz-Thonberg",
            "Schleußig",
            "Schönau",
            "Schönefeld-Abtnaundorf",
            "Schönefeld-Ost",
            "Seehausen",
            "Sellerhausen-Stünz",
            "Stötteritz",
            "Südvorstadt",
            "Thekla",
            "Volkmarsdorf",
            "Wahren",
            "Wiederitzsch",
            "Zentrum",
            "Zentrum-Nord",
            "Zentrum-Nordwest",
            "Zentrum-Ost",
            "Zentrum-Süd",
            "Zentrum-Südost",
            "Zentrum-West"
        )
        FileWriter(File("D:/query.txt"), Charset.forName("UTF-8")).use { it.write(query(ortsteile.subList(50, ortsteile.size))) }
    }

    private fun query(ortsteile: List<String>) : String {
        return """
           select ot0.Datum, ${select(ortsteile)} from
           ${combine(ortsteile)} 
        """.trimIndent()
    }

    private fun select(ortsteile: List<String>) : String {
        return ortsteile.mapIndexed { index, ot ->
            "ot${index}.Anzahl as \"$ot\""
        }.joinToString(", ")
    }

    private fun combine(ortsteile: List<String>) : String {
        return ortsteile.mapIndexed  { index, ot ->
            " join " + subquery(ot, index) + " on ot${(index-1)}.Datum = ot$index.Datum "
        }.joinToString("")
    }

    private fun subquery(ortsteil: String, index: Int): String {
        return """
            (select Datum, Anzahl from ortsteile where ortsteil = '${ortsteil}') as ot$index
        """.trimIndent()
    }
}