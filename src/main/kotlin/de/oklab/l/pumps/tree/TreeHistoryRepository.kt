package de.oklab.l.pumps.tree

import de.oklab.l.pumps.tree.bo.TreeHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TreeHistoryRepository: JpaRepository<TreeHistory, Long> {
}