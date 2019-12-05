package com.company.business.impl.managers;

import com.company.business.contract.managers.SpotManager;
import com.company.business.impl.AbstractManager;
import com.company.model.bean.Spot;

import java.util.List;

public class SpotManagerImpl extends AbstractManager implements SpotManager{
	
	public void registerSpot(Spot spot){
		getDaoFactory().getSpotDao().registerSpot(spot);
	}
	
	public void deleteSpot(Spot spot){
		getDaoFactory().getSpotDao().registerSpot(spot);
	}
	
	public void updateSpot(Spot spot){
		getDaoFactory().getSpotDao().registerSpot(spot);
	}
	
	public List<Spot> getListSpots(){
		return getDaoFactory().getSpotDao().getListSpots();
	}
}




