package com.company.business.contract.managers;
import java.util.List;
import com.company.model.bean.Spot;


public interface SpotManager {
	
	void registerSpot(Spot spot);
	void deleteSpot(Spot spot);
	void updateSpot(Spot spot);
	List<Spot> getListSpots();

}
