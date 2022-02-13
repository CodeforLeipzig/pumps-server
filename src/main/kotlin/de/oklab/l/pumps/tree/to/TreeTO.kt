package de.oklab.l.pumps.tree.to

import com.fasterxml.jackson.annotation.JsonProperty
import de.oklab.l.pumps.tree.bo.Tree
import de.oklab.l.pumps.tree.bo.TreeHistory
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory

data class TreeTO(
    val standortnr: String?,
    val strasse: String?,
    val ortsteil: String?,
    @JsonProperty("baumart_wi")
    val baumartWi: String?,
    @JsonProperty("baumart_de")
    val baumartDe: String?,
    val gattung: String?,
    val pflanzjahr: Int?,
    val alter: Int?,
    @JsonProperty("dat_abgabe")
    val datAbgabe: String?,
    val xcoord: Double?,
    val ycoord: Double?,
    var timestamp: Long? = null,
) {
    fun toEntity(geometryFactory: GeometryFactory): TreeHistory = TreeHistory(
        standortnr = standortnr,
        strasse = strasse,
        ortsteil = ortsteil,
        baumartWi = baumartWi,
        baumartDe = baumartDe,
        gattung = gattung,
        pflanzjahr = pflanzjahr,
        alter = alter,
        datAbgabe = datAbgabe,
        xcoord = xcoord,
        ycoord = ycoord,
        timestamp = timestamp!!,
        geom = geometryFactory.createPoint(Coordinate(xcoord!!, ycoord!!))
    )

    companion object {

        fun fromEntity(tree: Tree): TreeTO = TreeTO(
            standortnr = tree.standortnr,
            strasse = tree.strasse,
            ortsteil = tree.ortsteil,
            baumartWi = tree.baumartWi,
            baumartDe = tree.baumartDe,
            gattung = tree.gattung,
            pflanzjahr = tree.pflanzjahr,
            alter = tree.alter,
            datAbgabe = tree.datAbgabe,
            xcoord = tree.xcoord,
            ycoord = tree.xcoord,
            timestamp = tree.timestamp,
        )

        fun fromEntity(treeHistory: TreeHistory): TreeTO = TreeTO(
            standortnr = treeHistory.standortnr,
            strasse = treeHistory.strasse,
            ortsteil = treeHistory.ortsteil,
            baumartWi = treeHistory.baumartWi,
            baumartDe = treeHistory.baumartDe,
            gattung = treeHistory.gattung,
            pflanzjahr = treeHistory.pflanzjahr,
            alter = treeHistory.alter,
            datAbgabe = treeHistory.datAbgabe,
            xcoord = treeHistory.xcoord,
            ycoord = treeHistory.xcoord,
            timestamp = treeHistory.timestamp,
        )
    }
}