package net.mcreator.golfit.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.golfit.GolfItModVariables;
import net.mcreator.golfit.GolfItModElements;

import java.util.Map;
import java.util.HashMap;

@GolfItModElements.ModElement.Tag
public class MovementSpeedProcedure extends GolfItModElements.ModElement {
	public MovementSpeedProcedure(GolfItModElements instance) {
		super(instance, 18);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MovementSpeed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((GolfItModVariables.movement) == (true))) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.setWalkSpeed((float) 0);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			{
				Entity _ent = entity;
				if (!_ent.world.isRemote && _ent.world.getServer() != null) {
					_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
							"effect give @s jump_boost 99999 128 true");
				}
			}
		} else {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.setWalkSpeed((float) 0.1);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).clearActivePotions();
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
