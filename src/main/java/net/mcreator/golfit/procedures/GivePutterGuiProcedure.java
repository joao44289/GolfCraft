package net.mcreator.golfit.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.golfit.item.PutterClubItem;
import net.mcreator.golfit.GolfItModVariables;
import net.mcreator.golfit.GolfItModElements;

import java.util.Map;

@GolfItModElements.ModElement.Tag
public class GivePutterGuiProcedure extends GolfItModElements.ModElement {
	public GivePutterGuiProcedure(GolfItModElements instance) {
		super(instance, 28);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GivePutterGui!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		{
			final ItemStack _setstack = new ItemStack(PutterClubItem.block, (int) (1));
			final int _sltid = (int) (1);
			_setstack.setCount((int) 1);
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
				if (capability instanceof IItemHandlerModifiable) {
					((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
				}
			});
		}
		GolfItModVariables.isPutter = (boolean) (true);
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();
	}
}
