package de.oklab.l.pumps.tree.to

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.DoubleNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode

class GeometryDeserializer : StdDeserializer<Geometry<*>>(Geometry::class.java) {

    override fun deserialize(parser: JsonParser, context: DeserializationContext): Geometry<*> {
        val tn: ObjectNode = parser.readValueAsTree()
        val type: TextNode = tn.get("type") as TextNode
        val coordinates: ArrayNode = tn.get("coordinates") as ArrayNode
        return if (type.textValue().contentEquals("Point")) {
            PointGeometry(
                coordinates = coordinates.filterIsInstance<DoubleNode>().map { it.doubleValue().toFloat() })
        } else if (type.textValue().contentEquals("Polygon")) {
            PolygonGeometry(
                coordinates = coordinates.filterIsInstance<ArrayNode>().map {
                    coord -> coord.filterIsInstance<DoubleNode>().map { it.doubleValue().toFloat() }
                }.toList()
            )
        } else {
            PointGeometry(
                coordinates = coordinates.filterIsInstance<DoubleNode>().map { it.doubleValue().toFloat() })
        }
    }
}