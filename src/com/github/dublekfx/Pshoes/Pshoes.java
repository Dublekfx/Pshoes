package com.github.dublekfx.Pshoes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerVelocityEvent;


public final class Pshoes extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable()	{
		this.saveDefaultConfig();
		getLogger().info("Pshoes enabled!");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable()	{
		getLogger().info("Pshoes disabled!");
	}
	
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)	{
		Player ptarget;
		PlayerInventory pinv;
		ItemStack pshoes;
		ItemMeta meta;
		if (cmd.getName().equalsIgnoreCase("pshoooes") && sender.isOp() && !(sender instanceof Player))	{
			ptarget = (Player) sender;
			pinv = ptarget.getInventory();
			pshoes = new ItemStack(Material.LEATHER_BOOTS);
			meta = pshoes.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("PSHOOOES");
			meta.addEnchant(Enchantment.PROTECTION_FALL, 413, true);
			meta.setLore(lore);
			pshoes.setItemMeta(meta);
			pinv.setBoots(pshoes);
			return true;
		}
		return false;		
	}
	
	@EventHandler
	public void onPlayerVelocityEvent (PlayerVelocityEvent event)	{
	Player p = event.getPlayer();
	PotionEffect pot = new PotionEffect(PotionEffectType.JUMP, 30, 20);
		if (p.getInventory().getBoots().getItemMeta().getLore().get(0).equalsIgnoreCase("PSHOOOES"))	{
			p.addPotionEffect(pot, true);
	}
}
}