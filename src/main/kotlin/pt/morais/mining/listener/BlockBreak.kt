package pt.morais.mining.listener

import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit.getServer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import pt.morais.mining.Mining
import pt.morais.mining.dao.BlockDao


class BlockBreak(plugin: Mining) : Listener {

    private val blockDao: BlockDao
    private val vaultHook: VaultHook

    init {
        plugin.server.pluginManager.registerEvents(this, plugin);
        vaultHook = VaultHook(plugin)
        blockDao = BlockDao(plugin)
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val block = event.block

        val blockModel = blockDao.get(block.type) ?: return
        val money = blockModel.money
        val player = event.player

        player.sendMessage("§aYou won $${money} because you breaked ${blockModel.display}")
        vaultHook.economy.depositPlayer(player, money)
    }

}

class VaultHook() {

    lateinit var economy: Economy

    constructor(plugin: Mining) : this() {

        if (plugin.server.pluginManager.getPlugin("Vault") == null) {
            println("Vault not found!")
            plugin.server.pluginManager.disablePlugin(plugin)
            return
        }

        val rsp = getServer().servicesManager.getRegistration(Economy::class.java)
        if (rsp == null) {
            println("Money system not found!")
            plugin.server.pluginManager.disablePlugin(plugin)
            return
        }

        economy = rsp.provider

    }

}