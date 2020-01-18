package com.company.consumer.contract.dao;

import java.util.List;

import com.company.model.bean.Sector;


public interface SectorDao {
	int registerSector(Sector sector);
	void deleteSector(Sector sector);
	void updateSector(Sector sector);
	List<Sector> getListSectors(Sector sector);
	List<Sector> listForSearch(Sector sector);
}
