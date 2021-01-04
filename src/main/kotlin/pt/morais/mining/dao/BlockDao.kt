package pt.morais.mining.dao

import org.bukkit.Material
import pt.morais.mining.model.BlockModel

class BlockDao() {

    private val blocks = mutableMapOf<Material?, BlockModel>()

    operator fun get(material: Material?): BlockModel? = blocks[material]

    fun add(blockModel: BlockModel) {
        blocks[blockModel.material] = blockModel
    }

}