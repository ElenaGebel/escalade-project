package com.company.business.impl.managers;

import com.company.business.contract.managers.SpotManager;
import com.company.business.impl.AbstractManager;
import com.company.model.bean.Spot;
import com.company.model.bean.Topo;

import java.util.List;

public class SpotManagerImpl extends AbstractManager implements SpotManager{
	
	public int registerSpot(Spot spot){
		return getDaoFactory().getSpotDao().registerSpot(spot);
	}
	
	public void deleteSpot(Spot spot){
		getDaoFactory().getSpotDao().deleteSpot(spot);
	}
	
	public void updateSpot(Spot spot){
		getDaoFactory().getSpotDao().updateSpot(spot);
	}
	
	public List<Spot> getListSpots(){
		return getDaoFactory().getSpotDao().getListSpots();
	}
	public List<Spot> listForSearch(Spot spot){
		return getDaoFactory().getSpotDao().listForSearch(spot);
	}
	
	public void addTopoToSpot(Spot spot){
		getDaoFactory().getSpotDao().addTopoToSpot(spot);
	}
	
	public Spot getSpot(Spot spot){
		return getDaoFactory().getSpotDao().getSpot(spot);
	}
	
	public Topo checkForTopo(Spot spot){
		return getDaoFactory().getSpotDao().checkForTopo(spot);
	}
	

}




