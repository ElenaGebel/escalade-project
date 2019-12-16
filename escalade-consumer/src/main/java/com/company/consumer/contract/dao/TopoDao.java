package com.company.consumer.contract.dao;
import java.util.List;

import com.company.model.bean.Spot;
import com.company.model.bean.Topo;

public interface TopoDao {
	int registerTopo(Topo topo);
	void deleteTopo(Topo topo);
	void updateTopo(Topo topo);
	List<Topo> getListTopos();
	List<Topo> listForSearch(Topo topo);
	List<Spot> getNotRelatedSpots(Topo topo);
	List<Spot> getRelatedSpots(Topo topo);
	Topo getTopo(Topo topo);
}
