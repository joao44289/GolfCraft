package net.mcreator.golfit.procedures;

import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.golfit.item.GolfClubRangedItem;
import net.mcreator.golfit.GolfItModElements;

import java.util.Map;

@GolfItModElements.ModElement.Tag
public class GiceclubGUIProcedure extends GolfItModElements.ModElement {
	public GiceclubGUIProcedure(GolfItModElements instance) {
		super(instance, 23);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GiceclubGUI!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity) {
			ItemStack _setstack = new ItemStack(GolfClubRangedItem.block, (int) (1));
			_setstack.setCount((int) 1);
			((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
			if (entity instanceof ServerPlayerEntity)
				((ServerPlayerEntity) entity).inventory.markDirty();
		}
	}
}
