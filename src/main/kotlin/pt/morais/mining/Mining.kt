package pt.morais.mining

import org.bukkit.plugin.java.JavaPlugin
import pt.morais.mining.dao.BlockDao
import pt.morais.mining.listener.BlockBreak

class Mining : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        BlockBreak(this)
    }

}