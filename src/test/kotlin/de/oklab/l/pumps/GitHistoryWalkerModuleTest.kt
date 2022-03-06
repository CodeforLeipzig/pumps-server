package de.oklab.l.pumps

import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.oklab.l.pumps.tree.TreeService
import de.oklab.l.pumps.tree.to.Geometry
import de.oklab.l.pumps.tree.to.GeometryDeserializer
import de.oklab.l.pumps.tree.to.PointGeojsonFeatureCollection
import de.oklab.l.pumps.tree.to.TreeTO
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.treewalk.TreeWalk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import java.text.SimpleDateFormat


class GitHistoryWalkerModuleTest: BaseTest() {

    @Autowired
    lateinit var treeService: TreeService

    @Test
    fun testImport() {
        val objectMapper = jacksonObjectMapper()
        val module = SimpleModule()
        module.addDeserializer(Geometry::class.java, GeometryDeserializer())
        objectMapper.registerModule(module)
        val builder = FileRepositoryBuilder()
        //val dir = "/media/daten/git/giessdeibohm/.git"
        val dir = "D:/git/giessdeibohm/.git"
        val repository = builder.setGitDir(File(dir))
                .readEnvironment()
                .findGitDir()
                .build()
        val git = Git(repository)
        val revisionId = repository.findRef("refs/heads/master").objectId
        val relativeFilePath = "docs/geojsons/trees/alle.geojson"
        val targetFileLogs = git.log().add(revisionId).addPath(relativeFilePath).call()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        //val collectedTrees = mutableListOf<TreeTO>()
        val collectedTreeRows = mutableListOf<String>()
        for (targetFileLog in targetFileLogs.reversed()) {
            val reader = repository.newObjectReader()
            val treeWalk = TreeWalk.forPath(git.repository, relativeFilePath, targetFileLog.tree)
            val blobId = treeWalk.getObjectId(0)
            val objectLoader = reader.open(blobId)
            val content = String(objectLoader.bytes)
            val trees: PointGeojsonFeatureCollection<TreeTO> = objectMapper.readValue(content, objectMapper.typeFactory.constructParametricType(
                PointGeojsonFeatureCollection::class.java, TreeTO::class.java
            ))
            treeService.importTrees(trees.features.map { it.properties }.map { it.timestamp = targetFileLog.commitTime.toLong().times(1000); it }.filter { it.timestamp != null && it.timestamp!! > 1646165310000 })
            //collectedTrees.addAll(trees.features.map { it.properties })
            //collectedTreeRows.add("${sdf.format(Date(targetFileLog.commitTime.toLong().times(1000)))};${(trees.features.size)}")
            //println("${sdf.format(Date(targetFileLog.commitTime.toLong().times(1000)))}: ${(trees.features.size)} trees")
        }
        /*FileWriter(File("/home/joerg/Schreibtisch/trees_statistics.csv")).use {
            it.write(collectedTreeRows.joinToString("\n"))
        }*/
        //treeService.importTrees(collectedTrees)
    }
}




