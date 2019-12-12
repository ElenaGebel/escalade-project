package com.company.business.contract.managers;
import java.util.List;
import com.company.model.bean.Topo;


public interface TopoManager {
	
	int registerTopo(Topo topo);
	void deleteTopo(Topo topo);
	void updateTopo(Topo topo);
	List<Topo> getListTopos();
	List<Topo> listForSearch(Topo topo);

}