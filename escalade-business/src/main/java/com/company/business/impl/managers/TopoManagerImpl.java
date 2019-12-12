package com.company.business.impl.managers;


import com.company.business.contract.managers.TopoManager;
import com.company.business.impl.AbstractManager;
import com.company.model.bean.Topo;

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
}