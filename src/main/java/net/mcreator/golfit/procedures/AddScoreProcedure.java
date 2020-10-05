package net.mcreator.golfit.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.golfit.GolfItModElements;

import java.util.Map;

@GolfItModElements.ModElement.Tag
public class AddScoreProcedure extends GolfItModElements.ModElement {
	public AddScoreProcedure(GolfItModElements instance) {
		super(instance, 18);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure AddScore!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			Entity _ent = entity;
			if (!_ent.world.isRemote && _ent.world.getServer() != null) {
				_ent.world.getServer().getCommandManager().handleCommand(_ent.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
						"scoreboard players add @s Score 1");
			}
		}
	}
}
