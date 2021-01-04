package pt.morais.mining

import org.bukkit.plugin.java.JavaPlugin
import pt.morais.mining.dao.BlockDao
import pt.morais.mining.listener.BlockBreak

class Mining : JavaPlugin() {

    lateinit var blockDao: BlockDao

    override fun onEnable() {
        saveDefaultConfig()
        blockDao = BlockDao(this)
        BlockBreak(this)
    }

}