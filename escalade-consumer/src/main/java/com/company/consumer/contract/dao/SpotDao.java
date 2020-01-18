package com.company.consumer.contract.dao;
import java.util.List;
import com.company.model.bean.Spot;
import com.company.model.bean.Topo;


public interface SpotDao {
	int registerSpot(Spot spot);
	void deleteSpot(Spot spot);
	void updateSpot(Spot spot);
	List<Spot> getListSpots();
	List<Spot> listForSearch(Spot spot);
	void addTopoToSpot(Spot spot);
	Topo checkForTopo(Spot spot);
	Spot getSpot(Spot spot);
}
