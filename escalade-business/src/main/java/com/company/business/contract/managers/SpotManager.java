package com.company.business.contract.managers;
import java.util.List;
import com.company.model.bean.Spot;


public interface SpotManager {
	
	void registerSpot(Spot user);
	void deleteSpot(Spot user);
	void updateSpot(Spot user);
	List<Spot> getListSpots();

}
