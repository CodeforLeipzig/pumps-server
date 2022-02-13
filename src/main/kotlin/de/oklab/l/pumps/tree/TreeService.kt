package de.oklab.l.pumps.tree

import de.oklab.l.pumps.tree.to.TreeTO
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class TreeService(
    internal val treeRepository: TreeRepository,
    internal val treeHistoryRepository: TreeHistoryRepository,
    internal val geometryFactory: GeometryFactory
) {

    fun importTrees(trees: List<TreeTO>) {
        treeHistoryRepository.saveAll(trees.map { it.toEntity(geometryFactory) })
    }

    fun getCount(): Long = treeHistoryRepository.count()

    fun getCurrentCount(): Long = treeRepository.currentCount(treeRepository.mostRecentTimestamp())

    fun getHistory(treeId: String): List<TreeTO> =
        treeHistoryRepository.findByTreeId(treeId).map { TreeTO.fromEntity(it) }

    fun getCurrent(treeId: String): TreeTO? = treeRepository.findByTreeId(treeId)?.let { TreeTO.fromEntity(it) }

    fun getHistoryPaged(page: Int, pageSize: Int): List<TreeTO> {
        val results = treeHistoryRepository.findPaged(PageRequest.of(page, pageSize))
        return results.get().map { TreeTO.fromEntity(it) }.toList()
    }

    fun getCurrentPaged(page: Int, pageSize: Int): List<TreeTO> {
        val results = treeRepository.findPaged(PageRequest.of(page, pageSize))
        return results.get().map { TreeTO.fromEntity(it) }.toList()
    }

    fun getTreeAtTimestamp(treeId: String, timestamp: Long): TreeTO? =
        treeHistoryRepository.findByTreeIdAndTimestamp(treeId, timestamp)?.let { TreeTO.fromEntity(it) }

    fun timestamps(): SortedSet<Long> = treeHistoryRepository.timestamps().toSortedSet()
}