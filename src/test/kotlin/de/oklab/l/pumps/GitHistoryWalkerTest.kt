package de.oklab.l.pumps

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.oklab.l.pumps.tree.TreeService
import de.oklab.l.pumps.tree.to.GeojsonFeatureCollection
import de.oklab.l.pumps.tree.to.PointGeojsonFeatureCollection
import de.oklab.l.pumps.tree.to.TreeTO
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.treewalk.TreeWalk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

class GitHistoryWalkerTest {

    @Test
    fun testImport() {
        val objectMapper = jacksonObjectMapper()
        val builder = FileRepositoryBuilder()
        val repository = builder.setGitDir(File("/media/daten/git/giessdeibohm/.git"))
                .readEnvironment()
                .findGitDir()
                .build()
        val git = Git(repository)
        val revisionId = repository.findRef("refs/heads/master").objectId
        val relativeFilePath = "docs/geojsons/trees/alle.geojson"
        val targetFileLogs = git.log().add(revisionId).addPath(relativeFilePath).call()
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val collectedTrees = mutableListOf<TreeTO>()
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
            //collectedTrees.addAll(trees.features.map { it.properties })
            collectedTreeRows.add("${sdf.format(Date(targetFileLog.commitTime.toLong().times(1000)))};${(trees.features.size)}")
            //println("${sdf.format(Date(targetFileLog.commitTime.toLong().times(1000)))}: ${(trees.features.size)} trees")
        }
        FileWriter(File("/home/joerg/Schreibtisch/trees_statistics.csv")).use {
            it.write(collectedTreeRows.joinToString("\n"))
        }
    }
}




