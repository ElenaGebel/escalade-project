package com.company.consumer.contract.dao;
import java.util.List;
import com.company.model.bean.Spot;

public interface SpotDao {
	void registerSpot(Spot spot);
	void deleteSpot(Spot spot);
	void updateSpot(Spot spot);
	List<Spot> getListSpots();
}
