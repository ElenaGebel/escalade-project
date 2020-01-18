package com.company.business.impl.managers;

import java.util.List;

import com.company.business.contract.managers.SectorManager;
import com.company.business.impl.AbstractManager;
import com.company.model.bean.Sector;

public class SectorManagerImpl  extends AbstractManager implements SectorManager{
	public int registerSector(Sector sector){
		return getDaoFactory().getSectorDao().registerSector(sector);
	}
	
	
	public void deleteSector(Sector sector){
		getDaoFactory().getSectorDao().deleteSector(sector);
	}
	
	public void updateSector(Sector sector){
		getDaoFactory().getSectorDao().updateSector(sector);
	}
	
	public List<Sector> getListSectors(Sector sector){
		return getDaoFactory().getSectorDao().getListSectors(sector);
	}
	public List<Sector> listForSearch(Sector sector){
		return getDaoFactory().getSectorDao().listForSearch(sector);
	}
	
}
