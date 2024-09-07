package de.oklab.l.pumps

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.catalina.util.URLEncoder
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository
import org.junit.jupiter.api.Test
import org.wikidata.wdtk.datamodel.interfaces.*
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher
import java.io.File
import java.io.FileWriter

val objectMapper = jacksonObjectMapper()

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
        "Bestandsfläche",
        "Betula",
        "Caragana",
        "Carpinus",
        "Carya",
        "Castanea",
        "Catalpa",
        "Cedrus",
        "Celtis",
        "Cercidiphyllum",
        "Cercis",
        "Chamaecyparis",
        "Chionanthus",
        "Cladrastis",
        "Cornus",
        "Corylus",
        "Cotinus",
        "Crataegus",
        "Crataemespilus",
        "Cuppressus",
        "Cupressocyparis",
        "Cydonia",
        "Davidia",
        "Diospyros",
        "Elaeagnus",
        "Eriolobus",
        "Eucommia",
        "Euonymus",
        "Fagus",
        "Fraxinus",
        "Ginkgo",
        "Gleditsia",
        "Gymnocladus",
        "Hamamelis",
        "Hibiscus",
        "Hippophae",
        "Ilex",
        "Juglans",
        "Juniperus",
        "Koelreuteria",
        "Laburnum",
        "Larix",
        "Laurus",
        "Liquidambar",
        "Liriodendron",
        "Lonicera",
        "Magnolia",
        "Malus",
        "Mespilus",
        "Metasequoia",
        "Morus",
        "Nothofagus",
        "Nyssa",
        "Ostrya",
        "Parrotia",
        "Paulownia",
        "Pflanzstelle",
        "Phellodendron",
        "Picea",
        "Pinus",
        "Platanus",
        "Populus",
        "Prunus",
        "Pseudolarix",
        "Pseudotsuga",
        "Ptelea",
        "Pterocarya",
        "Pyrus",
        "Quercus",
        "Rhamnus",
        "Rhododendron",
        "Rhus",
        "Robinia",
        "Salix",
        "Sambucus",
        "Sequoia",
        "Sequoiadendron",
        "Sophora",
        "Sorbus",
        "Syringa",
        "Tamarix",
        "Taxodium",
        "Taxus",
        "Tetradium",
        "Thuja",
        "Thujopsis",
        "Tilia",
        "Tsuga",
        "Ulmus",
        "Unklar",
        "Viburnum",
        "Zelkova"
    )

    val species = listOf<String>(
        "Abies alba",
        "Abies koreana",
        "Abies nordmanniana",
        "Abies species",
        "Acer buergerianum",
        "Acer campestre",
        "Acer campestre 'Elsrijk'",
        "Acer campestre 'Huibers Elegant'",
        "Acer capillipes",
        "Acer cappadocicum",
        "Acer cappadocicum 'Rubrum'",
        "Acer griseum",
        "Acer monspessulanum",
        "Acer negundo",
        "Acer negundo 'Discolor'",
        "Acer negundo 'Variegatum'",
        "Acer opalus",
        "Acer palmatum",
        "Acer platanoides",
        "Acer platanoides 'Allershausen'",
        "Acer platanoides 'Apollo'",
        "Acer platanoides 'Cleveland'",
        "Acer platanoides 'Columnare'",
        "Acer platanoides 'Deborah'",
        "Acer platanoides 'Drummondii'",
        "Acer platanoides 'Emerald Queen'",
        "Acer platanoides 'Eurostar'",
        "Acer platanoides 'Faassen's Black'",
        "Acer platanoides 'Faassen´s Black'",
        "Acer platanoides 'Fairview'",
        "Acer platanoides 'Farlake's Green'",
        "Acer platanoides 'Farlake´s Green'",
        "Acer platanoides 'Globosum'",
        "Acer platanoides 'Laciniatum'",
        "Acer platanoides 'Olmstedt'",
        "Acer platanoides 'Reitenbachii'",
        "Acer platanoides 'Royal Red'",
        "Acer platanoides 'Schwedleri'",
        "Acer platanoides 'Summershade'",
        "Acer pseudoplatanus",
        "Acer pseudoplatanus 'Atropurpureum'",
        "Acer pseudoplatanus 'Flavovariegatum'",
        "Acer pseudoplatanus 'Purpurascens'",
        "Acer pseudoplatanus 'Rotterdam'",
        "Acer rubrum",
        "Acer rubrum 'Armstrong'",
        "Acer rubrum 'October Glory'",
        "Acer rubrum 'Scanlon'",
        "Acer saccharinum",
        "Acer saccharinum 'Laciniatum Wieri'",
        "Acer saccharinum 'Pyramidale'",
        "Acer saccharum",
        "Acer spec.",
        "Acer species",
        "Acer tataricum",
        "Acer tataricum ssp. ginnala",
        "Acer triflorum",
        "Acer x  freemanii 'Autumn Blaze'",
        "Acer x  zoeschense 'Annae'",
        "Aesculus flava",
        "Aesculus flava 'Vestita'",
        "Aesculus hippocastanum",
        "Aesculus hippocastanum 'Baumannii'",
        "Aesculus parviflora",
        "Aesculus pavia",
        "Aesculus x carnea",
        "Aesculus x carnea 'Briotii'",
        "Aesculus x hybrida",
        "Ailanthus altissima",
        "Alnus cordata",
        "Alnus glutinosa",
        "Alnus glutinosa 'Imperialis'",
        "Alnus glutinosa 'Incisa'",
        "Alnus incana",
        "Alnus species",
        "Alnus x koehnei",
        "Alnus x spaethii",
        "Amelanchier arborea 'Robin Hill'",
        "Amelanchier laevis",
        "Amelanchier lamarckii",
        "Amelanchier lamarckii 'Ballerina'",
        "Amelanchier ovalis",
        "Bestandsfläche",
        "Betula albosinensis",
        "Betula alleghaniensis",
        "Betula ermanii",
        "Betula maximowicziana",
        "Betula pendula",
        "Betula pendula 'Dalecarlica'",
        "Betula pendula 'Tristis'",
        "Betula pendula 'Youngii'",
        "Betula pubescens",
        "Betula species",
        "Betula utilis",
        "Betula utilis 'Doorenbos'",
        "Caragana arborescens",
        "Carpinus",
        "Carpinus betulus",
        "Carpinus betulus 'Beekmann'",
        "Carpinus betulus 'Fastigiata'",
        "Carpinus betulus 'Frans Fontaine'",
        "Carpinus betulus 'Lucas'",
        "Carya cordiformis",
        "Carya illinoensis",
        "Carya ovata",
        "Castanea sativa",
        "Castanea sativa 'Doree de Lyon'",
        "Castanea sativa 'Marlhac'",
        "Catalpa bignonioides",
        "Cedrus atlantica 'Glauca'",
        "Cedrus deodara",
        "Cedrus species",
        "Celtis australis",
        "Celtis occidentalis",
        "Cercidiphyllum japonicum",
        "Cercis siliquastrum",
        "Chamaecyparis lawsoniana",
        "Chamaecyparis pisifera",
        "Chamaecyparis species",
        "Chionanthus virginicus",
        "Cladrastis lutea",
        "Cornus",
        "Cornus controversa",
        "Cornus florida",
        "Cornus kousa",
        "Cornus mas",
        "Cornus sanguinea",
        "Cornus species",
        "Corylus",
        "Corylus avellana",
        "Corylus colurna",
        "Corylus maxima 'Purpurea'",
        "Cotinus coggygria",
        "Crataegus azarolus",
        "Crataegus chrysocarpa",
        "Crataegus crus-galli",
        "Crataegus laevigata",
        "Crataegus laevigata 'Candidoplena'",
        "Crataegus laevigata 'Paul's Scarlet'",
        "Crataegus laevigata 'Plena'",
        "Crataegus laevigata 'Rosea'",
        "Crataegus monogyna",
        "Crataegus monogyna 'Bicolor'",
        "Crataegus monogyna 'Stricta'",
        "Crataegus pedicellata",
        "Crataegus persimilis",
        "Crataegus persimilis 'Splendens'",
        "Crataegus pinnatifida",
        "Crataegus rhipidophylla",
        "Crataegus spec.",
        "Crataegus species",
        "Crataegus x lavallei",
        "Crataegus x lavallei 'Carrierei'",
        "Crataegus x macrocarpa",
        "Crataegus x media 'Punicea'",
        "Crataemespilus x grandiflora",
        "Cuppressus species",
        "Cupressocyparis x leylandii",
        "Cydonia oblonga",
        "Cydonia oblonga 'Bereczki'",
        "Cydonia oblonga 'Konstantinopeler Apfelquitte'",
        "Cydonia oblonga 'Portugiesische Birnenquitte'",
        "Davidia involucrata",
        "Diospyros lotus",
        "Elaeagnus angustifolia",
        "Eriolobus trilobatus",
        "Eucommia ulmoides",
        "Euonymus europaeus",
        "Euonymus latifolius",
        "Fagus sylvatica",
        "Fagus sylvatica 'Asplenifolia'",
        "Fagus sylvatica 'Atropunicea'",
        "Fagus sylvatica 'Pendula'",
        "Fagus sylvatica 'Purpurea Pendula'",
        "Fagus sylvatica 'Purpurea Tricolor'",
        "Fagus sylvatica purpurea",
        "Fraxinus americana",
        "Fraxinus americana 'Autumn Purple'",
        "Fraxinus americana 'Skyline'",
        "Fraxinus angustifolia",
        "Fraxinus angustifolia 'Raywood'",
        "Fraxinus bungeana",
        "Fraxinus chinensis var. rhynchophylla",
        "Fraxinus excelsior",
        "Fraxinus excelsior 'Altena'",
        "Fraxinus excelsior 'Atlas'",
        "Fraxinus excelsior 'Aurea'",
        "Fraxinus excelsior 'Diversifolia'",
        "Fraxinus excelsior 'Pendula'",
        "Fraxinus excelsior 'Westhof´s Glorie'",
        "Fraxinus ornus",
        "Fraxinus ornus 'Arie Peters'",
        "Fraxinus ornus 'Nijmegen'",
        "Fraxinus ornus 'Rotterdam'",
        "Fraxinus paxiana",
        "Fraxinus pennsylvanica",
        "Fraxinus pennsylvanica 'Summit'",
        "Fraxinus species",
        "Ginkgo biloba",
        "Ginkgo biloba 'Fastigiata Blagon'",
        "Ginkgo biloba 'Lakeview'",
        "Ginkgo biloba 'Princeton Sentry'",
        "Gleditsia triacanthos",
        "Gleditsia triacanthos 'Inermis'",
        "Gleditsia triacanthos 'Shademaster'",
        "Gleditsia triacanthos 'Skyline'",
        "Gleditsia triacanthos 'Sunburst'",
        "Gymnocladus dioicus",
        "Hamamelis virginiana",
        "Hamamelis x intermedia",
        "Hibiscus rosa-sinensis",
        "Hippophae rhamnoides",
        "Ilex aquifolium",
        "Juglans",
        "Juglans ailantifolia var. cordiformis",
        "Juglans mandshurica",
        "Juglans nigra",
        "Juglans regia",
        "Juglans regia 'Wunder von Monrepos'",
        "Juniperus communis",
        "Juniperus species",
        "Juniperus virginiana",
        "Koelreuteria paniculata",
        "Laburnum alpinum",
        "Laburnum anagyroides",
        "Laburnum x watereri 'Vossii'",
        "Larix decidua",
        "Larix kaempferi",
        "Larix species",
        "Laurus nobilis",
        "Liquidambar styraciflua",
        "Liquidambar styraciflua 'Moraine'",
        "Liquidambar styraciflua 'Paarl'",
        "Liquidambar styraciflua 'Worplesdon'",
        "Liriodendron",
        "Liriodendron tulipifera",
        "Liriodendron tulipifera 'Fastigiatum'",
        "Lonicera maackii",
        "Lonicera species",
        "Magnolia 'Galaxy'",
        "Magnolia acuminata",
        "Magnolia hypoleuca",
        "Magnolia kobus",
        "Magnolia liliiflora 'Nigra'",
        "Magnolia loebneri 'Merill'",
        "Magnolia sieboldii",
        "Magnolia species",
        "Magnolia stellata",
        "Magnolia x soulangeana",
        "Malus 'Adams'",
        "Malus 'Brandy Magic'",
        "Malus 'Butterball'",
        "Malus 'Coccinella'",
        "Malus 'Dark Rosaleen'",
        "Malus 'Eleyi'",
        "Malus 'Evereste'",
        "Malus 'Golden Hornet'",
        "Malus 'Hillieri'",
        "Malus 'Hopa'",
        "Malus 'John Downie'",
        "Malus 'Liset'",
        "Malus 'Makamik'",
        "Malus 'Profusion'",
        "Malus 'Red Sentinel'",
        "Malus 'Royalty'",
        "Malus 'Rudolph'",
        "Malus 'Striped Beauty'",
        "Malus 'Wintergold'",
        "Malus baccata",
        "Malus baccata mandschurica",
        "Malus coronaria 'Charlottae'",
        "Malus domestica",
        "Malus domestica 'Carola'",
        "Malus domestica 'Cox Orange'",
        "Malus domestica 'Danziger Kantapfel'",
        "Malus domestica 'Doberaner Renette'",
        "Malus domestica 'Edelborsdorfer'",
        "Malus domestica 'Gascoynes Scharlachroter'",
        "Malus domestica 'Geflammter Kardinal'",
        "Malus domestica 'Goldparmäne'",
        "Malus domestica 'Goldrenette'",
        "Malus domestica 'Gravensteiner'",
        "Malus domestica 'Großer Brünnerling'",
        "Malus domestica 'Idared'",
        "Malus domestica 'Jacob Lebel'",
        "Malus domestica 'Jakob Fischer'",
        "Malus domestica 'Jakob Lebel'",
        "Malus domestica 'Kaiser Wilhelm'",
        "Malus domestica 'Kanadarenette'",
        "Malus domestica 'Klarapfel'",
        "Malus domestica 'Ontario'",
        "Malus domestica 'Pfannkuchenapfel'",
        "Malus domestica 'Professor Sprenger'",
        "Malus domestica 'Rheinischer Bohnapfel'",
        "Malus domestica 'Rote Sternrenette'",
        "Malus domestica 'Roter Berlepsch Mutation'",
        "Malus domestica 'Roter Boskoop'",
        "Malus domestica 'Roter Eiserapfel'",
        "Malus domestica 'Roter Fuchs'",
        "Malus domestica 'Schöner aus Boskoop'",
        "Malus domestica 'Spartan'",
        "Malus domestica 'Topaz'",
        "Malus domestica 'Weißer Klarapfel'",
        "Malus domestica 'Weißer Winter-Calville'",
        "Malus floribunda",
        "Malus prunifolia",
        "Malus pumila",
        "Malus sargentii",
        "Malus spec.",
        "Malus species",
        "Malus spectabilis",
        "Malus sylvestris",
        "Malus toringo",
        "Malus toringoides",
        "Malus tschonoskii",
        "Malus x arnoldiana",
        "Malus x purpurea",
        "Malus x robusta",
        "Malus x robusta var. persicifolia",
        "Mespilus germanica",
        "Metasequoia glyptostroboides",
        "Morus alba",
        "Morus nigra",
        "Morus species",
        "Nothofagus antarctica",
        "Nyssa sylvatica",
        "Ostrya carpinifolia",
        "Parrotia persica",
        "Paulownia tomentosa",
        "Phellodendron amurense",
        "Phellodendron lavallei",
        "Picea abies",
        "Picea omorika",
        "Picea orientalis",
        "Picea pungens",
        "Picea pungens 'Glauca'",
        "Picea species",
        "Pinus cembra",
        "Pinus mugo",
        "Pinus nigra",
        "Pinus peuce",
        "Pinus ponderosa",
        "Pinus species",
        "Pinus strobus",
        "Pinus sylvestris",
        "Platanus orientalis",
        "Platanus x acerifolia",
        "Platanus x acerifolia 'Pyramidalis'",
        "Platanus x acerifolia 'Tremonia'",
        "Populus  x canadensis",
        "Populus alba",
        "Populus balsamifera",
        "Populus canadensis",
        "Populus lasiocarpa",
        "Populus nigra",
        "Populus nigra 'Italica'",
        "Populus simonii",
        "Populus spec.",
        "Populus species",
        "Populus tremula",
        "Populus tremula 'Erecta'",
        "Populus trichocarpa",
        "Populus x  canadensis",
        "Populus x berolinensis",
        "Populus x canadensis",
        "Populus x canescens",
        "Prunus 'Accolade'",
        "Prunus 'Pandora'",
        "Prunus avium",
        "Prunus avium 'Burlat'",
        "Prunus avium 'Büttners Rote Knorpelkirsche'",
        "Prunus avium 'Gr. Prinz.Kirsche'",
        "Prunus avium 'Große Schwarze Knorpel'",
        "Prunus avium 'Hedelfinger Riesenkirsche'",
        "Prunus avium 'Kassins Frühe Herzkirsche",
        "Prunus avium 'Kordia'",
        "Prunus avium 'Plena'",
        "Prunus avium 'Querfurter Königsk.'",
        "Prunus avium 'Regina'",
        "Prunus avium 'Schneiders Späte Knorpelkirsche'",
        "Prunus avium 'Van'",
        "Prunus cerasifera",
        "Prunus cerasifera 'Nigra'",
        "Prunus cerasifera 'Pleniflora'",
        "Prunus cerasus 'Morellenfeuer'",
        "Prunus cerasus 'Plena'",
        "Prunus domestica",
        "Prunus domestica 'Bühler Frühzwetschge'",
        "Prunus domestica 'Ebersweiler Zwetschge'",
        "Prunus domestica 'Große Grüne Reneklode'",
        "Prunus domestica 'Hanita'",
        "Prunus domestica 'Hauszwetschge Wolff'",
        "Prunus domestica 'Hauszwetschge'",
        "Prunus domestica 'Ontariopflaume'",
        "Prunus domestica 'Oullins Reneklode'",
        "Prunus domestica syriaca 'Nancymirabelle'",
        "Prunus dulcis",
        "Prunus maackii 'Amber Beauty'",
        "Prunus mahaleb",
        "Prunus padus",
        "Prunus padus 'Albertii'",
        "Prunus padus 'Schloss Tiefurt'",
        "Prunus padus 'Watereri'",
        "Prunus persica",
        "Prunus sargentii",
        "Prunus sargentii 'Rancho'",
        "Prunus serotina",
        "Prunus serrulata",
        "Prunus serrulata 'Amanogawa'",
        "Prunus serrulata 'Kanzan'",
        "Prunus serrulata 'Pink Perfection'",
        "Prunus serrulata 'Shirofugen'",
        "Prunus serrulata 'Sunset Boulevard'",
        "Prunus serrulata 'Taihaku'",
        "Prunus spec.",
        "Prunus species",
        "Prunus spinosa",
        "Prunus subhirtella 'Autumnalis Rosea'",
        "Prunus subhirtella 'Autumnalis'",
        "Prunus subhirtella 'Fukubana'",
        "Prunus x hillieri 'Spire'",
        "Prunus x schmittii",
        "Prunus x yedoensis",
        "Pseudolarix amabilis",
        "Pseudotsuga menziesii",
        "Ptelea trifoliata",
        "Pterocarya fraxinifolia",
        "Pyrus calleryana 'Chanticleer'",
        "Pyrus caucasica",
        "Pyrus communis",
        "Pyrus communis 'Alexander Lukas'",
        "Pyrus communis 'Beech Hill'",
        "Pyrus communis 'Boscs Flaschenbirne'",
        "Pyrus communis 'Bunte Julibirne'",
        "Pyrus communis 'Clapps Liebling'",
        "Pyrus communis 'Conference'",
        "Pyrus communis 'Gelbmöstler'",
        "Pyrus communis 'Gellerts Butterbirne'",
        "Pyrus communis 'Gute Graue'",
        "Pyrus communis 'Gute Luise'",
        "Pyrus communis 'Mostbirne'",
        "Pyrus communis 'Petersbirne'",
        "Pyrus communis 'Regelii'",
        "Pyrus pyraster",
        "Pyrus salicifolia",
        "Pyrus species",
        "Quercus alba",
        "Quercus canariensis",
        "Quercus cerris",
        "Quercus coccinea",
        "Quercus dentata",
        "Quercus frainetto",
        "Quercus imbricaria",
        "Quercus libani",
        "Quercus macranthera",
        "Quercus macrocarpa",
        "Quercus palustris",
        "Quercus petraea",
        "Quercus petraea 'Mespilifolia'",
        "Quercus pontica",
        "Quercus pubescens",
        "Quercus robur",
        "Quercus robur 'Fastigiata Koster'",
        "Quercus robur 'Fastigiata'",
        "Quercus robur 'Pendula'",
        "Quercus rubra",
        "Quercus species",
        "Quercus velutina",
        "Quercus x turneri",
        "Rhamnus catharticus",
        "Rhododendron 'Album'",
        "Rhododendron 'Boursault'",
        "Rhododendron 'Casalp'",
        "Rhododendron 'Catawbiense Grandiflorum'",
        "Rhododendron 'Christiane Herzog'",
        "Rhododendron 'Constanze'",
        "Rhododendron 'Cunninghams White'",
        "Rhododendron 'Dufthecke'",
        "Rhododendron 'Ehrengold'",
        "Rhododendron 'Furnival's Daughter'",
        "Rhododendron 'Goldbukett'",
        "Rhododendron 'Graf Lennart'",
        "Rhododendron 'Humboldt'",
        "Rhododendron 'Kokardia'",
        "Rhododendron 'Nova Zembla'",
        "Rhododendron 'Old Port'",
        "Rhododendron 'Pink Pearl'",
        "Rhododendron 'Purpureum Grandiflorum'",
        "Rhododendron 'Silvia'",
        "Rhododendron 'Simona'",
        "Rhododendron 'Viscy'",
        "Rhododendron arboreum",
        "Rhododendron species",
        "Rhus typhina",
        "Rhus verniciflua",
        "Robinia pseudoacacia",
        "Robinia pseudoacacia 'Bessoniana'",
        "Robinia pseudoacacia 'Frisia'",
        "Robinia pseudoacacia 'Monophylla'",
        "Robinia pseudoacacia 'Sandraudiga'",
        "Robinia pseudoacacia 'Semperflorens'",
        "Robinia pseudoacacia 'Umbraculifera'",
        "Robinia pseudoacacia 'Unifoliola'",
        "Robinia pseudoaccacia",
        "Robinia species",
        "Robinia x margaretta 'Casque Rouge'",
        "Salix alba",
        "Salix alba 'Belders'",
        "Salix alba 'Liempde'",
        "Salix alba 'Tristis'",
        "Salix aurita",
        "Salix babylonica",
        "Salix caprea",
        "Salix caprea mas",
        "Salix cinerea",
        "Salix fragilis",
        "Salix matsudana 'Tortuosa'",
        "Salix pentandra",
        "Salix spec.",
        "Salix species",
        "Salix viminalis",
        "Salix x rubens",
        "Salix x smithiana",
        "Sambucus nigra",
        "Sambucus spec.",
        "Sequoia sempervirens",
        "Sequoiadendron giganteum",
        "Sophora japonica",
        "Sophora japonica 'Regent'",
        "Sorbus 'Joseph Rock'",
        "Sorbus alnifolia",
        "Sorbus aria",
        "Sorbus aria 'Lutescens'",
        "Sorbus aria 'Magnifica'",
        "Sorbus aria 'Majestica'",
        "Sorbus aucuparia",
        "Sorbus aucuparia 'Edulis'",
        "Sorbus commixta 'Dodong'",
        "Sorbus decora",
        "Sorbus domestica",
        "Sorbus intermedia",
        "Sorbus intermedia 'Brouwers'",
        "Sorbus latifolia 'Henk Vink'",
        "Sorbus serotina",
        "Sorbus species",
        "Sorbus torminalis",
        "Sorbus vilmorinii",
        "Sorbus x thuringiaca 'Fastigiata'",
        "Syringa reticulata",
        "Syringa vulgaris",
        "Syringa vulgaris 'Mme Florentine Stepman'",
        "Tamarix species",
        "Taxodium distichum",
        "Taxus baccata",
        "Taxus baccata 'Lutea'",
        "Tetradium daniellii",
        "Thuja occidentalis",
        "Thuja occidentalis 'Smaragd'",
        "Thuja plicata",
        "Thuja species",
        "Thujopsis dolabrata",
        "Tilia americana",
        "Tilia americana 'Carolinensis'",
        "Tilia americana 'Moltkei'",
        "Tilia americana 'Nova'",
        "Tilia cordata",
        "Tilia cordata 'Erecta'",
        "Tilia cordata 'Greenspire'",
        "Tilia cordata 'Rancho'",
        "Tilia cordata 'Roelvo'",
        "Tilia flavescens 'Glenleven'",
        "Tilia japonica",
        "Tilia platyphyllos",
        "Tilia platyphyllos 'Laciniata'",
        "Tilia platyphyllos 'Rathaus'",
        "Tilia spec.",
        "Tilia species",
        "Tilia tomentosa",
        "Tilia tomentosa 'Brabant'",
        "Tilia tomentosa 'Petiolaris'",
        "Tilia tomentosa 'Szeleste'",
        "Tilia x euchlora",
        "Tilia x flaccida",
        "Tilia x heinaldeana",
        "Tilia x moltkei 'Blechiana'",
        "Tilia x vulgaris",
        "Tilia x vulgaris 'Pallida'",
        "Tsuga canadensis",
        "Tsuga canadensis 'Pendula'",
        "Ulmus 'Columella'",
        "Ulmus 'Dodoens'",
        "Ulmus 'Lobel'",
        "Ulmus 'New Horizon'",
        "Ulmus 'Plantijn'",
        "Ulmus 'Rebona'",
        "Ulmus 'Regal'",
        "Ulmus 'Sapporo Autumn Gold'",
        "Ulmus glabra",
        "Ulmus glabra 'Camperdownii'",
        "Ulmus glabra 'Pendula'",
        "Ulmus laevis",
        "Ulmus minor",
        "Ulmus procera",
        "Ulmus spec.",
        "Ulmus species",
        "Ulmus x hollandica",
        "Ulmus x hollandica 'Groeneveld'",
        "Ulmus x hollandica 'Wredei'",
        "Unklar",
        "Viburnum species",
        "Zelkova serrata",
        "Zelkova serrata 'Green Vase'",
        "Zelkova serrata 'Village Green'"
    )

    @Test
    fun testGenus() {
        val category = "genus"
        val file = File("D://found-$category.json")
        val existing = file.exists() && file.isFile
        val foundEntries = if (existing) {
            objectMapper.readValue(File("D://found-$category.json"), WikiEntries::class.java)
        } else {
            findEntries(gattungen)
        }
        writeFiles(foundEntries, category, !existing)
    }

    private fun writeSqlInserts(foundEntries: WikiEntries, category: String, file: String) {
        writeFile(file, """
            INSERT INTO public.trees_metadata(latin_name, category_type, wikipedia, wikicommons, wikidata, german_label)
            VALUES
            ${foundEntries.data.joinToString(",\n") { toInsertStatement(it, category) }}
            ON CONFLICT (latin_name) DO NOTHING;
        """.trimIndent())
    }

    private fun writeFile(file: String, content: String) =
        FileWriter(File(file), Charsets.UTF_8).use { fw -> fw.write(content) }

    private fun toInsertStatement(entry: WikiEntry, category: String): String {
        val values = mutableListOf(entry.name, category, entry.wikipedia, entry.image, entry.wikidata, entry.labelGer)
        return "(${values.joinToString(", ") { if (it == null) "null" else "'${it.replace("'", "''")}'" }})"
    }

    private fun findEntries(names: List<String>): WikiEntries {
        val foundEntries = mutableListOf<WikiEntry>()
        for (name in names) {
            val wikiEntry = getWikiEntry(name)
            val wikiEntryToUse = if (wikiEntry.wikidata == null) {
                val index = name.indexOf(" '")
                if (index > 0) {
                    val alternativeName = name.substring(0, index)
                    val alternativeWikiEntry = getWikiEntry(alternativeName)
                    if (alternativeWikiEntry.wikidata != null) alternativeWikiEntry else wikiEntry
                } else wikiEntry
            } else wikiEntry
            foundEntries.add(wikiEntryToUse)
        }
        return WikiEntries(foundEntries)
    }

    private fun getWikiEntry(name: String): WikiEntry {
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
                        val taxonStatements = wikiDataEntry.statementGroups.filter { group ->
                            group.find { it.mainSnak.propertyId.id == "P105" } != null
                        }
                        if (taxonStatements.isEmpty()) continue
                        wikiEntry.labelGer = wikiDataEntry.labels["de"]?.text
                        wikiEntry.wikipedia = getWikipediaPageTitle(wikiDataEntry)
                        wikiEntry.image = getImage(wikiDataEntry)
                    }
                    if (wikiEntry.wikipedia != null) break
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return wikiEntry
    }

    private fun getImage(wikiDataEntry: ItemDocument): String? {
        // P18 == image
        val statements = wikiDataEntry.statementGroups.filter { group ->
            group.find { it.mainSnak.propertyId.id == "P18" } != null
        }
        val image = getImage(statements)
        return if (image != null) {
            "https://commons.wikimedia.org/wiki/File:${
                urlEncoder.encode(
                    image,
                    Charsets.UTF_8
                )
            }"
        } else null
    }

    private fun getWikipediaPageTitle(wikiDataEntry: ItemDocument): String? {
        val wikipediaPageTitle = wikiDataEntry.siteLinks["dewiki"]?.pageTitle
        return if (wikipediaPageTitle != null) {
            "https://de.wikipedia.org/wiki/${
                urlEncoder.encode(
                    wikipediaPageTitle,
                    Charsets.UTF_8
                )
            }"
        } else {
            val wikipediaPageEnTitle = wikiDataEntry.siteLinks["enwiki"]?.pageTitle
            if (wikipediaPageEnTitle != null) {
                "https://en.wikipedia.org/wiki/${
                    urlEncoder.encode(
                        wikipediaPageEnTitle,
                        Charsets.UTF_8
                    )
                }"
            } else null
        }
    }

    private fun getImage(statements: List<StatementGroup>) =
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

    @Test
    fun testSpecies() {
        val category = "species"
        val file = File("D://found-$category.json")
        val existing = file.exists() && file.isFile
        val foundEntries = if (existing) {
            objectMapper.readValue(file, WikiEntries::class.java)
        } else {
            findEntries(species)
        }
        writeFiles(foundEntries, category, !existing)
    }

    private fun writeFiles(
        foundEntries: WikiEntries,
        category: String,
        writeJson: Boolean = false
    ) {
        if (writeJson) {
            writeFile("D://found-$category.json", foundEntries.toJson())
        }
        writeSqlInserts(foundEntries, category, "D://found-$category.sql")
    }
}

data class WikiEntry(
    val name: String,
    var labelGer: String? = null,
    var wikidata: String? = null,
    var wikipedia: String? = null,
    var image: String? = null
)

data class WikiEntries(val data: List<WikiEntry>) {
    fun toJson(): String = objectMapper.writeValueAsString(this)!!
}