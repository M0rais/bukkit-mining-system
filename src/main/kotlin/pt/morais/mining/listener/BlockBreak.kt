package pt.morais.mining.listener

import net.milkbowl.vault.economy.Economy
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import pt.morais.mining.Mining

class BlockBreak(plugin: Mining) : Listener {

    private val blockDao = plugin.blockDao
    private val economy = plugin.getProvider<Economy>("Vault")

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val block = event.block

        val blockModel = blockDao.get(block.type) ?: return
        val money = blockModel.money
        val player = event.player

        player.sendMessage("§aYou won $${money} because you breaked ${blockModel.display}")
        economy.depositPlayer(player, money)
    }

}