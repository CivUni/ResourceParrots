package com.civuniverse.resourceparrots;

import com.sun.scenario.effect.Crop;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Parrot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public final class ResourceParrots extends JavaPlugin implements Listener
{



    @Override
    public void onEnable()
    {
        // Plugin startup logic
        System.out.println("ResourceParrots Started");
        getServer().getPluginManager().registerEvents(this, this);

    }


    public static Integer getRandomInt(Integer max)
    {
        Random ran = new Random();
        return ran.nextInt(max);
    }

    @EventHandler
    public void parrotSpawn(BlockBreakEvent event)
    {
        MaterialData data = (Crops) event.getBlock().getState().getData();
        String stringData = data.toString();
        System.out.println(data);
        if (stringData.equals("RIPE CARROT(7)"))
        {
            System.out.println("THIS CARROT IS RIPE");
        }
        else
        {
            System.out.println("THIS CARROT IS NOT RIPE");
        }
        int random = getRandomInt(99);
        System.out.println(random);
        if(event.getBlock().getType().equals(Material.CARROT)&&random == 73)
        {
            World world = event.getBlock().getWorld();
            Location loc = event.getBlock().getLocation();
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
            }, 100);
        }
    }

    @EventHandler
    public void resourceDrop(EntityDeathEvent event)
    {
        if(event.getEntity().getCustomName().equals(ChatColor.GOLD + "Carrot Parrot"))
        {
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.CARROT_ITEM, 64));
        }

    }

}
