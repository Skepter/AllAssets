/*******************************************************************************
 * Skepter's licence
 * Copyright 2014 
 *
 * AllAssets was created by Skepter (http://skepter.github.io/).
 *
 * You are able to:
 * * View AllAsset's source code on github
 * * Experiment with the code to your wish
 * * Download it to use on your server
 *
 * You are NOT able to:
 * * Sell AllAssets - it is COMPLETELY free for ALL users
 * * Claim it as your own. AllAssets is created by Skepter
 * * Distribute it on any other website. The ONLY places where it can be downloaded from is github and the Bukkit repository
 * * Decompile the code - it's COMPLETELY unnecessary since the entire code is on Github. I've just made life easier for you :)
 *
 * You can not:
 * * Hold me liable for your actions:
 *     - If there is a bug in the program, you are allowed to notify me.
 *     - If other players abuse AllAssets, you are NOT allowed to hold me liable for that
 *     - If it was setup incorrectly or you used a non-official build, you can NOT hold me liable for that.
 * * Yell at me:
 *     - If something is wrong, please approach the situation calmly.
 * * Modify the code:
 *     - You are NOT allowed to 'steal' the whole code from Github, modify it, compile it yourself and distribute that.
 *
 * If you are to break from these implications, future use of this plugin will be forbidden.
 *******************************************************************************/
/*******************************************************************************
 *******************************************************************************/
package io.github.Skepter.AllAssets.Tasks;

import io.github.Skepter.AllAssets.Utils.FireworkUtils;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class DiscoArmorTask implements Runnable {

	private final Player player;

	public DiscoArmorTask(final Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		ItemStack leatherHelmet = new ItemStack(Material.LEATHER_HELMET, 1);
		LeatherArmorMeta leatherHelmetMeta = (LeatherArmorMeta) leatherHelmet.getItemMeta();
		leatherHelmetMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherHelmet.setItemMeta(leatherHelmetMeta);
		
		ItemStack leatherChestPlate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		LeatherArmorMeta leatherChestPlateMeta = (LeatherArmorMeta) leatherChestPlate.getItemMeta();
		leatherChestPlateMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherChestPlate.setItemMeta(leatherChestPlateMeta);
		
		ItemStack leatherLeggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		LeatherArmorMeta leatherLeggingsMeta = (LeatherArmorMeta) leatherLeggings.getItemMeta();
		leatherLeggingsMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherLeggings.setItemMeta(leatherLeggingsMeta);
		
		ItemStack leatherBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
		LeatherArmorMeta leatherBootsMeta = (LeatherArmorMeta) leatherBoots.getItemMeta();
		leatherBootsMeta.setColor(FireworkUtils.getColor(new Random().nextInt(17)));
		leatherBoots.setItemMeta(leatherBootsMeta);

		player.getInventory().setHelmet(leatherHelmet);
		player.getInventory().setChestplate(leatherChestPlate);
		player.getInventory().setLeggings(leatherLeggings);
		player.getInventory().setBoots(leatherBoots);
	}

}
