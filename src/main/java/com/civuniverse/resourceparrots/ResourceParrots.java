package com.civuniverse.resourceparrots;

import com.sun.scenario.effect.Crop;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.material.NetherWarts;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public final class ResourceParrots extends JavaPlugin implements Listener
{
    @Override
    public void onEnable()
    {
        // Plugin startup logic
        System.out.println("###");
        System.out.println("[RP] ResourceParrots Started");
        System.out.println("[RP] by dkf-lab and SwiftFizz");
        System.out.println("###");
        getServer().getPluginManager().registerEvents(this, this);
        // Setup config
        this.saveDefaultConfig();
    }

    public void onDisable() {
        System.out.println("[RP] ResourceParrots has been disabled.");
    }
    public static Integer getRandomInt(Integer max)
    {
        Random ran = new Random();
        return ran.nextInt(max);
    }

    @EventHandler
    public void parrotSpawn(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        String stringState = event.getBlock().getState().getData().toString(); //works
        String stringStateCocoa = stringState.split(" ")[0];
        String stringStateSplit = stringState.split("\\(")[0];
        if (stringStateSplit.equals("RIPE BEETROOT_BLOCK")||(stringStateSplit.equals("RIPE POTATO")||(stringStateSplit.equals("RIPE CARROT")||(stringStateSplit.equals("RIPE CROPS"))||(stringStateSplit.equals("RIPE NETHER_WARTS"))||(stringStateCocoa.equals("COCOA(10)"))||(stringStateCocoa.equals("COCOA(9)"))||(stringStateCocoa.equals("COCOA(8)"))||(stringStateCocoa.equals("COCOA(11)"))))) {
            if (this.getConfig().getBoolean("debug") {
                player.sendMessage("[RP Debug] Ripe crop broken.");
            }
        }
        else {
            return; //nope
        }
        boolean debug = false;
        if (this.getConfig().getBoolean("debug")) {
            debug = true;
            player.sendMessage("[RP Debug] Spawning parrot.");
        }
        int random = getRandomInt(99);
        int chance = this.getConfig().getInt("parrotSpawnPercent");
        int delay = this.getConfig().getInt("delay") * 10;
        if (debug) {
            player.sendMessage("[RP Debug] Random: " + random + ". Chance " + chance);
        }
        World world = event.getBlock().getWorld();
        Location loc = event.getBlock().getLocation();
        if(event.getBlock().getType().equals(Material.CARROT)&&random <= chance&&this.getConfig().getBoolean("carrots"))
        {
            Entity parrot = world.spawnEntity(loc,EntityType.PARROT);
            parrot.setCustomName(ChatColor.GOLD + "Carrot Parrot");
            parrot.setCustomNameVisible(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
            {
                @Override
                public void run()
                {
                    parrot.remove();
                }
            }, delay);
        }
        if(event.getBlock().getType().equals(Material.CROPS)&&random <= chance&&this.getConfig().getBoolean("wheat")) {
            Entity parrot = world.spawnEntity(loc,EntityType.PARROT);
            parrot.setCustomName(ChatColor.DARK_GREEN + "Wheat Parrot");
            parrot.setCustomNameVisible(true);
            if (debug) {
                event.getPlayer().sendMessage("[RP Debug] Parrot spawn success");
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
            {
                @Override
                public void run()
                {
                    parrot.remove();
                }
            }, delay);
        }
        //potato
        if(event.getBlock().getType().equals(Material.POTATO)&&random <= chance&&this.getConfig().getBoolean("potato")) {
            Entity parrot = world.spawnEntity(loc,EntityType.PARROT);
            parrot.setCustomName(ChatColor.YELLOW + "Potato Parrot");
            parrot.setCustomNameVisible(true);
            if (debug) {
                event.getPlayer().sendMessage("[RP Debug] Parrot spawn success");
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
            {
                @Override
                public void run()
                {
                    parrot.remove();
                }
            }, delay);
        }
        //beetroot
        if(event.getBlock().getType().equals(Material.BEETROOT_BLOCK)&&random <= chance&&this.getConfig().getBoolean("beetroot")) {
            Entity parrot = world.spawnEntity(loc,EntityType.PARROT);
            parrot.setCustomName(ChatColor.RED + "Beetroot Parrot");
            parrot.setCustomNameVisible(true);
            if (debug) {
                event.getPlayer().sendMessage("[RP Debug] Parrot spawn success");
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
            {
                @Override
                public void run()
                {
                    parrot.remove();
                }
            }, delay);
        }
        // netherwart
        if(event.getBlock().getType().equals(Material.NETHER_WARTS)&&random <= chance&&this.getConfig().getBoolean("netherwart")) {
            Entity parrot = world.spawnEntity(loc,EntityType.PARROT);
            parrot.setCustomName(ChatColor.DARK_RED + "Nether Wart Parrot");
            parrot.setCustomNameVisible(true);
            if (debug) {
                event.getPlayer().sendMessage("[RP Debug] Parrot spawn success");
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
            {
                @Override
                public void run()
                {
                    parrot.remove();
                }
            }, delay);
        }
        //cocoa
        if(event.getBlock().getType().equals(Material.COCOA)&&random <= chance&&this.getConfig().getBoolean("cocoa")) {
            Entity parrot = world.spawnEntity(loc,EntityType.PARROT);
            parrot.setCustomName(ChatColor.LIGHT_PURPLE + "Cocoa Parrot");
            parrot.setCustomNameVisible(true);
            if (debug) {
                event.getPlayer().sendMessage("[RP Debug] Parrot spawn success");
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
            {
                @Override
                public void run()
                {
                    parrot.remove();
                }
            }, delay);
        }
    }

    @EventHandler
    public void resourceDrop(EntityDeathEvent event)
    {
        int amount = this.getConfig().getInt("amountOfCrop");
        //carrots
        if(event.getEntity().getCustomName().equals(ChatColor.GOLD + "Carrot Parrot"))
        {
            if (this.getConfig().getBoolean("clearParrotDrops")) {
                event.getDrops().clear();
            }
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.CARROT_ITEM, amount));
        }
        //wheat
        if(event.getEntity().getCustomName().equals(ChatColor.DARK_GREEN + "Wheat Parrot"))
        {
            if (this.getConfig().getBoolean("clearParrotDrops")) {
                event.getDrops().clear();
            }
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.WHEAT, amount));
        }
        // potato
        if(event.getEntity().getCustomName().equals(ChatColor.YELLOW + "Potato Parrot"))
        {
            if (this.getConfig().getBoolean("clearParrotDrops")) {
                event.getDrops().clear();
            }
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.POTATO_ITEM, amount));
        }
        // beetroot
        if(event.getEntity().getCustomName().equals(ChatColor.RED + "Beetroot Parrot"))
        {
            if (this.getConfig().getBoolean("clearParrotDrops")) {
                event.getDrops().clear();
            }
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.BEETROOT, amount));
        }
        //netherwart
        if(event.getEntity().getCustomName().equals(ChatColor.DARK_RED + "Nether Wart Parrot"))
        {
            if (this.getConfig().getBoolean("clearParrotDrops")) {
                event.getDrops().clear();
            }
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.NETHER_WARTS, amount));
        }
        //cocoa
        if(event.getEntity().getCustomName().equals(ChatColor.LIGHT_PURPLE + "Cocoa Parrot"))
        {
            if (this.getConfig().getBoolean("clearParrotDrops")) {
                event.getDrops().clear();
            }
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.COCOA, amount));
        }
    }

}
