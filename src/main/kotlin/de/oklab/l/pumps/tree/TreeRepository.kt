package de.oklab.l.pumps.tree

import de.oklab.l.pumps.tree.bo.Tree
import de.oklab.l.pumps.tree.bo.TreeHistory
import de.oklab.l.pumps.tree.to.TreeTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Repository

@Repository
interface TreeRepository: ReadOnlyRepository<Tree, Long> {

    @Query("SELECT t FROM trees AS t ORDER BY t.standortnr")
    fun findPaged(pageable: Pageable): Page<Tree>

    @Query("SELECT t FROM trees AS t WHERE t.standortnr = :treeId")
    fun findByTreeId(treeId: String): Tree?

    @Query("SELECT MAX(t.timestamp) FROM trees AS t")
    fun mostRecentTimestamp(): Long

    @Query("SELECT count(t) FROM trees AS t WHERE t.timestamp = :timestamp")
    fun currentCount(timestamp: Long): Long
}