package pt.morais.mining

import net.milkbowl.vault.economy.Economy
import org.bukkit.plugin.java.JavaPlugin
import pt.morais.mining.dao.BlockDao
import pt.morais.mining.listener.BlockBreak
import pt.morais.mining.process.BlockProcess

class Mining : JavaPlugin() {

    lateinit var blockDao: BlockDao
    val economy = getProvider<Economy>("Vault")

    override fun onEnable() {
        saveDefaultConfig()
        blockDao = BlockDao()
        BlockProcess(this)
        server.pluginManager.registerEvents(BlockBreak(this), this)
    }

    private inline fun <reified T> getProvider(plugin: String): T = requireNotNull(
        server
            .servicesManager
            .getRegistration(T::class.java)
            ?.provider
    ) {
        server.pluginManager.disablePlugin(this)
        println("Failed to find $plugin provider.")
    }

}