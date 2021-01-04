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

    inline fun <reified T> getProvider(plugin: String): T = requireNotNull(
        server
            .servicesManager
            .getRegistration(T::class.java)
            ?.provider
    ) {
        server.pluginManager.disablePlugin(this)
        println("Failed to find $plugin provider.")
    }

}