package de.oklab.l.pumps

import org.eclipse.rdf4j.repository.sparql.SPARQLRepository
import org.junit.jupiter.api.Test
import org.wikidata.wdtk.datamodel.interfaces.ItemDocument
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher
import java.io.File
import java.io.FileWriter


class WikiDataTest {

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

    @Test
    fun test() {
        val sparqlEndpoint = "https://query.wikidata.org/sparql"
        val repo = SPARQLRepository(sparqlEndpoint)

        val userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)"
        repo.additionalHttpHeaders = mapOf("User-Agent" to userAgent)

        val foundEntries = mutableListOf<String>()
        for (name in gattungen) {

            val queryScientificName = """SELECT DISTINCT ?item ?itemLabel WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "[AUTO_LANGUAGE]". }
  {
    SELECT DISTINCT ?item WHERE {
      ?item p:P225 ?statement0.
      ?statement0 (ps:P225) "$name".
    }
    LIMIT 100
  }
}"""

            try {
                //repo.connection.prepareTupleQuery(queryScientificName).evaluate(SPARQLResultsJSONWriter(System.out))
                val result = repo.connection.prepareTupleQuery(queryScientificName).evaluate()
                if (result.hasNext()) {
                    while (result.hasNext()) {
                        val entry = result.next()
                        val wikiDataLabel = entry.getBinding("itemLabel").value.stringValue()
                        val wbdf = WikibaseDataFetcher.getWikidataDataFetcher()
                        val genus = wbdf.getEntityDocument(wikiDataLabel)
                        if (genus is ItemDocument) {
                            val key = "dewiki"
                            val wikipediaPageTitle = genus.siteLinks[key]?.pageTitle
                            if (wikipediaPageTitle != null) {
                                foundEntries.add("$name: https://de.wikipedia.org/wiki/${wikipediaPageTitle}")
                            } else {
                                foundEntries.add("$name: not found, site keys: ${genus.siteLinks.keys.joinToString(", ")}")
                            }
                        }
                    }
                } else {
                    foundEntries.add("$name: not found in wikidata")
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
        FileWriter(File("D://found-websites.txt")).use { it.write(foundEntries.joinToString("\n")) }
    }
}