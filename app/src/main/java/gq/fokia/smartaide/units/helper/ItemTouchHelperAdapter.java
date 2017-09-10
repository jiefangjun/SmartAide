package gq.fokia.smartaide.units.helper;

/**
 * Created by archie on 9/10/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition,int toPosition);
    void onItemDismiss(int position);
}
