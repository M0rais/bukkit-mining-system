package pt.morais.mining.process

import org.bukkit.Material
import pt.morais.mining.Mining
import pt.morais.mining.model.BlockModel

class BlockProcess(plugin: Mining) {

    init {
        val config = plugin.config
        for (item in config.getStringList("blocks")) {
            val split = item.split(";")

            val material = Material.getMaterial(split[0])

            plugin.blockDao.add(BlockModel(material, split[1].replace("&", "§"), split[2].toDouble()))
        }
    }

}