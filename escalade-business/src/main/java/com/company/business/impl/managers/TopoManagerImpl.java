package com.company.business.impl.managers;

import com.company.business.contract.managers.SpotManager;
import com.company.business.contract.managers.TopoManager;
import com.company.business.impl.AbstractManager;
import com.company.model.bean.Spot;
import com.company.model.bean.Topo;

import java.util.List;

public class TopoManagerImpl extends AbstractManager implements TopoManager{
	
	public int registerTopo(Topo topo){
		return getDaoFactory().getTopoDao().registerTopo(topo);
	}
	
	public void deleteTopo(Topo topo){
		getDaoFactory().getTopoDao().registerTopo(topo);
	}
	
	public void updateTopo(Topo topo){
		getDaoFactory().getTopoDao().registerTopo(topo);
	}
	
	public List<Topo> getListTopos(){
		return getDaoFactory().getTopoDao().getListTopos();
	}
}