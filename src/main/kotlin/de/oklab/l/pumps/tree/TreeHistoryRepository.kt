package de.oklab.l.pumps.tree

import de.oklab.l.pumps.tree.bo.TreeHistory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface TreeHistoryRepository : JpaRepository<TreeHistory, Long> {

    @Query("SELECT t FROM tree_history AS t ORDER BY t.standortnr, t.timestamp")
    fun findPaged(pageable: Pageable): Page<TreeHistory>

    @Query("SELECT t FROM tree_history AS t WHERE t.standortnr = :treeId")
    fun findByTreeId(treeId: String): List<TreeHistory>

    @Query("SELECT t FROM tree_history AS t WHERE t.standortnr = :treeId AND t.timestamp = :timestamp")
    fun findByTreeIdAndTimestamp(treeId: String, timestamp: Long): TreeHistory?

    @Query("SELECT DISTINCT(t.timestamp) FROM tree_history AS t")
    fun timestamps(): List<Long>
}