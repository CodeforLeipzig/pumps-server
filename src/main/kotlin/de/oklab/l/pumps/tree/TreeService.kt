package de.oklab.l.pumps.tree

import de.oklab.l.pumps.tree.to.TreeTO
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.stereotype.Service

@Service
class TreeService (internal val treeHistoryRepository: TreeHistoryRepository,
                   internal val geometryFactory: GeometryFactory) {

    fun importTrees(trees: List<TreeTO>) {
        treeHistoryRepository.saveAll(trees.map { it.toEntity(geometryFactory) })
    }
}