package de.oklab.l.pumps.districts

import de.oklab.l.pumps.districts.bo.District
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DistrictRepository : JpaRepository<District, Long> {

}