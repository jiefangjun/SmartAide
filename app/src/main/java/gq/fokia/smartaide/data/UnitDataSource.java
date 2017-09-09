package gq.fokia.smartaide.data;

import java.io.IOException;
import java.util.List;

import gq.fokia.smartaide.model.Unit;

/**
 * Created by archie on 8/1/17.
 */

public interface UnitDataSource {

    interface LoadUnitsCallback{

        void onUnitsLoaded(List<Unit> units);

        void onDataNotAvailable();
    }

    void getUnits(LoadUnitsCallback callback);

    void postUnit(Unit unit);

    void putUnit(Unit unit);

    void deleteUnit(Unit unit);
}
