package com.company.business.impl.managers;


import com.company.business.contract.managers.TopoManager;
import com.company.business.impl.AbstractManager;
import com.company.model.bean.Spot;
import com.company.model.bean.Topo;
import com.company.model.bean.User;

import java.util.List;


public class TopoManagerImpl extends AbstractManager implements TopoManager{
	
	public int registerTopo(Topo topo){
		return getDaoFactory().getTopoDao().registerTopo(topo);
	}
	
	public void deleteTopo(Topo topo){
		getDaoFactory().getTopoDao().deleteTopo(topo);
	}
	
	public void updateTopo(Topo topo){
		getDaoFactory().getTopoDao().updateTopo(topo);
	}
	
	public List<Topo> getListTopos(){
		return getDaoFactory().getTopoDao().getListTopos();
	}
	
	public List<Topo> listForSearch(Topo topo){
		return getDaoFactory().getTopoDao().listForSearch(topo);
	}
	
	public List<Spot> getNotRelatedSpots(Topo topo){
		return getDaoFactory().getTopoDao().getNotRelatedSpots(topo);
	}
	
	public List<Spot> getRelatedSpots(Topo topo){
		return getDaoFactory().getTopoDao().getRelatedSpots(topo);
	}
	
	public Topo getTopo(Topo topo){
		return getDaoFactory().getTopoDao().getTopo(topo);
	}
	
	public void updateReservation(Topo topo){
		getDaoFactory().getTopoDao().updateReservation(topo);
	}
	
	public List<Topo> getListReservations(User user){
		return getDaoFactory().getTopoDao().getListReservations(user);
	}
	
	public List<Topo> getListToposForUser(User user){
		return getDaoFactory().getTopoDao().getListToposForUser(user);
	}
	
}