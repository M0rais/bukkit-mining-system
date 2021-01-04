package pt.morais.mining.listener

import net.milkbowl.vault.economy.Economy
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import pt.morais.mining.Mining

class BlockBreak(private val plugin: Mining) : Listener {

    private val blockDao get() = plugin.blockDao
    private val economy = plugin.economy

    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val block = event.block

        val blockModel = blockDao[block.type] ?: return
        val money = blockModel.money
        val player = event.player

        player.sendMessage("§aYou won $${money} because you breaked ${blockModel.display}")
        economy.depositPlayer(player, money)
    }

}