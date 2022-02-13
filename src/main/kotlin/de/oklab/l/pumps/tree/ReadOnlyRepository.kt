package de.oklab.l.pumps.tree

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository
import java.util.*

@NoRepositoryBean
interface ReadOnlyRepository<T, ID> : Repository<T, ID> {
    fun findAll(): List<T>?
    fun findAll(sort: Sort?): List<T>?
    fun findAll(pageable: Pageable?): Page<T>?
    fun findById(id: ID): Optional<T>?
    fun count(): Long
}