package de.oklab.l.pumps

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.catalina.util.URLEncoder
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository
import org.junit.jupiter.api.Test
import org.wikidata.wdtk.datamodel.interfaces.*
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher
import java.io.File
import java.io.FileWriter

val objectMapper = ObjectMapper()

class WikiDataTest {
    val urlEncoder = URLEncoder()
    val wbdf = WikibaseDataFetcher.getWikidataDataFetcher()
    val sparqlEndpoint = "https://query.wikidata.org/sparql"
    val userAgent = "Wikidata RDF4J Java Example/0.1 (https://query.wikidata.org/)"
    val repo = SPARQLRepository(sparqlEndpoint).apply {
        additionalHttpHeaders = mapOf("User-Agent" to userAgent)
    }

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
        "Zelkova"
    )

    val species = listOf<String>(
        "Abies alba",
        "Abies species",
        "Acer buergerianum",
        "Acer campestre",
        "Acer campestre 'Elsrijk'",
        "Acer campestre 'Huibers Elegant'",
        "Acer monspessulanum",
        "Acer negundo",
        "Acer platanoides",
        "Acer platanoides 'Allershausen'",
        "Acer platanoides 'Apollo'",
        "Acer platanoides 'Cleveland'",
        "Acer platanoides 'Columnare'",
        "Acer platanoides 'Deborah'",
        "Acer platanoides 'Emerald Queen'",
        "Acer platanoides 'Faassen's Black'",
        "Acer platanoides 'Faassen´s Black'",
        "Acer platanoides 'Fairview'",
        "Acer platanoides 'Farlake's Green'",
        "Acer platanoides 'Farlake´s Green'",
        "Acer platanoides 'Globosum'",
        "Acer platanoides 'Olmstedt'",
        "Acer platanoides 'Royal Red'",
        "Acer platanoides 'Schwedleri'",
        "Acer pseudoplatanus",
        "Acer pseudoplatanus 'Atropurpureum'",
        "Acer rubrum",
        "Acer saccharinum",
        "Acer species",
        "Acer tataricum",
        "Acer x freemanii 'Autumn Blaze'",
        "Aesculus hippocastanum",
        "Aesculus hippocastanum 'Baumannii'",
        "Aesculus x carnea",
        "Aesculus x carnea 'Briotii'",
        "Ailanthus altissima",
        "Alnus cordata",
        "Alnus glutinosa",
        "Alnus incana",
        "Alnus species",
        "Alnus x spaethii",
        "Amelanchier arborea 'Robin Hill'",
        "Amelanchier lamarckii",
        "Betula nigra",
        "Betula pendula",
        "Betula pendula 'Dalecarlica'",
        "Betula pendula 'Youngii'",
        "Betula species",
        "Carpinus betulus",
        "Carpinus betulus 'Fastigiata'",
        "Carpinus betulus 'Frans Fontaine'",
        "Carpinus betulus 'Lucas'",
        "Castanea sativa",
        "Catalpa bignonioides",
        "Celtis australis",
        "Cornus mas",
        "Corylus colurna",
        "Crataegus laevigata",
        "Crataegus laevigata 'Paul's Scarlet'",
        "Crataegus monogyna",
        "Crataegus monogyna 'Stricta'",
        "Crataegus species",
        "Crataegus x lavallei",
        "Crataegus x lavallei 'Carrierei'",
        "Cydonia oblonga",
        "Cydonia oblonga 'Bereczki'",
        "Cydonia oblonga 'Konstantinopeler Apfelquitte'",
        "Elaeagnus angustifolia",
        "Eriolobus trilobatus",
        "Fagus sylvatica",
        "Fagus sylvatica 'Atropunicea'",
        "Fraxinus americana",
        "Fraxinus americana 'Autumn Purple'",
        "Fraxinus americana 'Skyline'",
        "Fraxinus angustifolia 'Raywood'",
        "Fraxinus excelsior",
        "Fraxinus excelsior 'Altena'",
        "Fraxinus excelsior 'Atlas'",
        "Fraxinus excelsior 'Aurea'",
        "Fraxinus excelsior 'Diversifolia'",
        "Fraxinus excelsior 'Westhof's Glorie'",
        "Fraxinus excelsior 'Westhof´s Glorie'",
        "Fraxinus ornus",
        "Fraxinus ornus 'Arie Peters'",
        "Fraxinus ornus 'Rotterdam'",
        "Fraxinus pennsylvanica",
        "Fraxinus pennsylvanica 'Summit'",
        "Fraxinus species",
        "Ginkgo biloba",
        "Ginkgo biloba 'Lakeview'",
        "Ginkgo biloba 'Princeton Sentry'",
        "Gleditsia triacanthos",
        "Gleditsia triacanthos 'Inermis'",
        "Gleditsia triacanthos 'Shademaster'",
        "Gleditsia triacanthos 'Skyline'",
        "Gleditsia triacanthos 'Sunburst'",
        "Hippophae rhamnoides",
        "Ilex aquifolium",
        "Juglans regia",
        "Juglans regia 'Wunder von Monrepos'",
        "Juniperus species",
        "Koelreuteria paniculata",
        "Larix decidua",
        "Larix species",
        "Liquidambar styraciflua",
        "Liquidambar styraciflua 'Moraine'",
        "Liquidambar styraciflua 'Paarl'",
        "Liquidambar styraciflua 'Worplesdon'",
        "Liriodendron tulipifera",
        "Liriodendron tulipifera 'Fastigiatum'",
        "Magnolia species",
        "Malus domestica",
        "Malus domestica 'Carola'",
        "Malus domestica 'Cox Orange'",
        "Malus domestica 'Goldparmäne'",
        "Malus domestica 'Gravensteiner'",
        "Malus domestica 'Großer Brünnerling'",
        "Malus domestica 'Idared'",
        "Malus domestica 'Kaiser Wilhelm'",
        "Malus domestica 'Klarapfel'",
        "Malus domestica 'Ontario'",
        "Malus domestica 'Rheinischer Bohnapfel'",
        "Malus domestica 'Roter Berlepsch Mutation'",
        "Malus domestica 'Roter Boskoop'",
        "Malus domestica 'Roter Eiserapfel'",
        "Malus prunifolia",
        "Malus species",
        "Malus tschonoskii",
        "Mespilus germanica",
        "Metasequoia glyptostroboides",
        "Morus species",
        "nicht bestimmt",
        "Ostrya carpinifolia",
        "Picea abies",
        "Picea pungens 'Glauca'",
        "Picea species",
        "Pinus nigra",
        "Pinus species",
        "Pinus sylvestris",
        "Platanus x acerifolia",
        "Platanus x acerifolia 'Pyramidalis'",
        "Platanus x acerifolia 'Tremonia'",
        "Populus alba",
        "Populus nigra",
        "Populus nigra 'Italica'",
        "Populus species",
        "Populus tremula",
        "Populus x canadensis",
        "Prunus avium",
        "Prunus avium 'Burlat'",
        "Prunus avium 'Große Schwarze Knorpel'",
        "Prunus avium 'Hedelfinger Riesenkirsche'",
        "Prunus avium 'Plena'",
        "Prunus avium 'Regina'",
        "Prunus avium 'Van'",
        "Prunus cerasifera",
        "Prunus cerasifera 'Nigra'",
        "Prunus domestica",
        "Prunus domestica 'Große Grüne Reneklode'",
        "Prunus domestica 'Hanita'",
        "Prunus domestica 'Hauszwetschge Wolff'",
        "Prunus domestica syriaca 'Nancymirabelle'",
        "Prunus maackii 'Amber Beauty'",
        "Prunus mahaleb",
        "Prunus padus",
        "Prunus padus 'Albertii'",
        "Prunus padus 'Schloss Tiefurt'",
        "Prunus 'Pandora'",
        "Prunus sargentii",
        "Prunus sargentii 'Rancho'",
        "Prunus serotina",
        "Prunus serrulata 'Amanogawa'",
        "Prunus serrulata 'Kanzan'",
        "Prunus serrulata 'Sunset Boulevard'",
        "Prunus species",
        "Prunus subhirtella 'Autumnalis'",
        "Prunus x hillieri 'Spire'",
        "Prunus x schmittii",
        "Pseudotsuga menziesii",
        "Pyracantha  species",
        "Pyrus calleryana 'Chanticleer'",
        "Pyrus communis",
        "Pyrus communis 'Beech Hill'",
        "Pyrus communis 'Boscs Flaschenbirne'",
        "Pyrus communis 'Mostbirne'",
        "Pyrus species",
        "Quercus cerris",
        "Quercus coccinea",
        "Quercus frainetto",
        "Quercus libani",
        "Quercus palustris",
        "Quercus petraea",
        "Quercus robur",
        "Quercus robur 'Fastigiata'",
        "Quercus robur 'Fastigiata Koster'",
        "Quercus rubra",
        "Quercus species",
        "Rhus typhina",
        "Robinia pseudoacacia",
        "Robinia pseudoacacia 'Bessoniana'",
        "Robinia pseudoacacia 'Monophylla'",
        "Robinia pseudoacacia 'Sandraudiga'",
        "Robinia pseudoacacia 'Umbraculifera'",
        "Robinia x margaretta 'Casque Rouge'",
        "Salix alba",
        "Salix alba 'Tristis'",
        "Salix caprea",
        "Salix fragilis",
        "Salix matsudana 'Tortuosa'",
        "Salix species",
        "Sambucus nigra",
        "Sophora japonica",
        "Sophora japonica 'Regent'",
        "Sorbus aria",
        "Sorbus aria 'Magnifica'",
        "Sorbus aucuparia",
        "Sorbus aucuparia 'Edulis'",
        "Sorbus intermedia",
        "Sorbus intermedia 'Brouwers'",
        "Sorbus species",
        "Sorbus x thuringiaca 'Fastigiata'",
        "Syringa vulgaris",
        "Tamarix species",
        "Taxus baccata",
        "Tetradium daniellii",
        "Thuja species",
        "Tilia americana 'Nova'",
        "Tilia cordata",
        "Tilia cordata 'Erecta'",
        "Tilia cordata 'Greenspire'",
        "Tilia cordata 'Rancho'",
        "Tilia cordata 'Roelvo'",
        "Tilia flavescens 'Glenleven'",
        "Tilia platyphyllos",
        "Tilia species",
        "Tilia tomentosa",
        "Tilia tomentosa 'Brabant'",
        "Tilia tomentosa 'Petiolaris'",
        "Tilia tomentosa 'Szeleste'",
        "Tilia x euchlora",
        "Tilia x vulgaris",
        "Tilia x vulgaris 'Pallida'",
        "Ulmus 'Columella'",
        "Ulmus 'Dodoens'",
        "Ulmus glabra",
        "Ulmus laevis",
        "Ulmus 'Lobel'",
        "Ulmus minor",
        "Ulmus 'Rebona'",
        "Ulmus 'Regal'",
        "Ulmus 'Sapporo Autumn Gold'",
        "Ulmus species",
        "Ulmus x hollandica",
        "Zelkova serrata",
        "Zelkova serrata 'Green Vase'"
    )

    @Test
    fun testGenus() {
        val foundEntries = findEntries(gattungen)
        FileWriter(File("D://found-genus.json")).use {
            it.write("""
                {
                    "genus": [
                        ${foundEntries.joinToString(",")}                    
                    ]
                }
            """.trimIndent())
        }
    }

    private fun findEntries(names: List<String>): MutableList<String> {
        val foundEntries = mutableListOf<String>()
        for (name in names) {

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
            val wikiEntry = WikiEntry(name)
            try {
                //repo.connection.prepareTupleQuery(queryScientificName).evaluate(SPARQLResultsJSONWriter(System.out))
                val result = repo.connection.prepareTupleQuery(queryScientificName).evaluate()
                if (result.hasNext()) {
                    while (result.hasNext()) {
                        val entry = result.next()
                        val wikiDataLabel = entry.getBinding("itemLabel").value.stringValue()
                        wikiEntry.wikidata = "https://www.wikidata.org/wiki/${wikiDataLabel}"
                        val wikiDataEntry = wbdf.getEntityDocument(wikiDataLabel)
                        if (wikiDataEntry is ItemDocument) {
                            // P105 == taxon rank
                            val taxonStatements =
                                wikiDataEntry.statementGroups.filter { group -> group.find { it.mainSnak.propertyId.id == "P105" } != null }
                            if (taxonStatements.isEmpty()) continue
                            wikiEntry.labelGer = wikiDataEntry.labels["de"]?.text
                            val wikipediaPageTitle = wikiDataEntry.siteLinks["dewiki"]?.pageTitle
                            if (wikipediaPageTitle != null) {
                                wikiEntry.wikipedia = "https://de.wikipedia.org/wiki/${
                                    urlEncoder.encode(
                                        wikipediaPageTitle,
                                        Charsets.UTF_8
                                    )
                                }"
                            } else {
                                val wikipediaPageEnTitle = wikiDataEntry.siteLinks["enwiki"]?.pageTitle
                                if (wikipediaPageEnTitle != null) {
                                    wikiEntry.wikipedia = "https://en.wikipedia.org/wiki/${
                                        urlEncoder.encode(
                                            wikipediaPageEnTitle,
                                            Charsets.UTF_8
                                        )
                                    }"
                                }
                            }
                            // P18 == image
                            val statements =
                                wikiDataEntry.statementGroups.filter { group -> group.find { it.mainSnak.propertyId.id == "P18" } != null }
                            val image =
                                if (statements.isEmpty()) null else statements[0].statements[0].mainSnak.accept(object :
                                    SnakVisitor<String?> {
                                    override fun visit(snak: ValueSnak?): String? {
                                        val value = snak?.value
                                        if (value is StringValue) {
                                            return value.string
                                        }
                                        return snak?.value.toString()
                                    }

                                    override fun visit(snak: SomeValueSnak?): String? {
                                        return null
                                    }

                                    override fun visit(snak: NoValueSnak?): String? {
                                        return null
                                    }
                                })
                            if (image != null) {
                                wikiEntry.image = "https://commons.wikimedia.org/wiki/File:${
                                    urlEncoder.encode(
                                        image,
                                        Charsets.UTF_8
                                    )
                                }"
                            }
                        }
                        if (wikiEntry.wikipedia != null) break
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            foundEntries.add(wikiEntry.toJson())
        }
        return foundEntries
    }

    @Test
    fun testSpecies() {
        val foundEntries = findEntries(species)
        FileWriter(File("D://found-species.json")).use {
            it.write("""
                {
                    "species": [
                        ${foundEntries.joinToString(",")}                    
                    ]
                }
            """.trimIndent())
        }
    }
}

data class WikiEntry(
    val name: String,
    var labelGer: String? = null,
    var wikidata: String? = null,
    var wikipedia: String? = null,
    var image: String? = null
) {
    fun toJson(): String = objectMapper.writeValueAsString(this)!!
}