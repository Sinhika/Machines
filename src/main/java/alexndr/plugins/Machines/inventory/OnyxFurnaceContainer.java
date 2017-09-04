package alexndr.plugins.Machines.inventory;

import alexndr.plugins.Machines.tiles.OnyxFurnaceTileEntity;
import mcjty.lib.compat.CompatSlot;
import mcjty.lib.tools.InventoryTools;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OnyxFurnaceContainer extends Container
{
    private OnyxFurnaceTileEntity tileFurnace;
    private int lastCookTime = 0;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;
    private int lastTotalCookTime = 0;

    public OnyxFurnaceContainer(InventoryPlayer player, OnyxFurnaceTileEntity tileentity)
    {
        this.tileFurnace = tileentity;
        this.addSlotToContainer(new CompatSlot(tileentity, 0, 56, 17));
        this.addSlotToContainer(new SlotFurnaceFuel(tileentity, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnaceOutput(player.player, tileentity, 2, 116, 35));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(player, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(player, var3, 8 + var3 * 18, 142));
        }
    } // end ctor

    @Override
    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
        listener.sendAllWindowProperties(this, this.tileFurnace);
    }
    
    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
	public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icrafting = (IContainerListener) this.listeners.get(i);

            if (this.lastCookTime != this.tileFurnace.getField(2))
                icrafting.sendProgressBarUpdate(this, 2, this.tileFurnace.getField(2));

            if (this.lastBurnTime != this.tileFurnace.getField(0))
                icrafting.sendProgressBarUpdate(this, 0, this.tileFurnace.getField(0));

            if (this.lastItemBurnTime != this.tileFurnace.getField(1))
                icrafting.sendProgressBarUpdate(this, 1, this.tileFurnace.getField(1));

            if (this.lastTotalCookTime != this.tileFurnace.getField(3))
                icrafting.sendProgressBarUpdate(this, 3, this.tileFurnace.getField(3));
        }

        this.lastCookTime = this.tileFurnace.getField(2);
        this.lastBurnTime = this.tileFurnace.getField(0);
        this.lastItemBurnTime = this.tileFurnace.getField(1);
        this.lastTotalCookTime = this.tileFurnace.getField(3);
    } // end detectAndSendChanges

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileFurnace.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileFurnace.isUsableByPlayer(playerIn);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
    {
        ItemStack itemstack = ItemStackTools.getEmptyStack();
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = ItemStackTools.safeCopy(itemstack1);

            if (index == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                    return ItemStackTools.getEmptyStack();
                slot.onSlotChange(itemstack1, itemstack);
            } 
            else if (index != 1 && index != 0) {
                if (OnyxFurnaceTileEntity.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                        return ItemStackTools.getEmptyStack();
                } 
                else if (FurnaceRecipes.instance().getSmeltingResult(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                        return ItemStackTools.getEmptyStack();
                } 
                else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                        return ItemStackTools.getEmptyStack();
                } 
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                    return ItemStackTools.getEmptyStack();
            } 
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
                return ItemStackTools.getEmptyStack();

            if (ItemStackTools.isEmpty(itemstack1))
                slot.putStack(ItemStackTools.getEmptyStack());
            else
                slot.onSlotChanged();

            if (ItemStackTools.getStackSize(itemstack1) == ItemStackTools.getStackSize(itemstack))
                return ItemStackTools.getEmptyStack();

            InventoryTools.onPickup(slot, playerIn, itemstack1);
        }

        return itemstack;
    } // end transferStackInSlot


} // end class
