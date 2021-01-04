package pt.morais.mining.dao

import org.bukkit.Material
import pt.morais.mining.Mining

class BlockDao(plugin: Mining) {

    private val blocks = mutableMapOf<Material?, BlockModel?>()

    operator fun get(material: Material?): BlockModel? = blocks[material]

    init {
        val config = plugin.config
        for (item in config.getStringList("blocks")) {
            val split = item.split(";")

            val material = Material.getMaterial(split[0])

            blocks[material] = BlockModel(material, split[1].replace("&", "§"), split[2].toDouble())
        }
    }

}

data class BlockModel(val material: Material?, val display: String, val money: Double)