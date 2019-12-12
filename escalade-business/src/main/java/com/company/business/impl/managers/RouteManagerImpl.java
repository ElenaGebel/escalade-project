package com.company.business.impl.managers;

import java.util.List;

import com.company.business.contract.managers.RouteManager;
import com.company.business.impl.AbstractManager;
import com.company.model.bean.Quotation;
import com.company.model.bean.Route;

public class RouteManagerImpl  extends AbstractManager implements RouteManager{
	
	public int registerRoute(Route route){
		return getDaoFactory().getRouteDao().registerRoute(route);
	}
	
	public void deleteRoute(Route route){
		getDaoFactory().getRouteDao().deleteRoute(route);
	}
	
	public void updateRoute(Route route){
		getDaoFactory().getRouteDao().updateRoute(route);
	}
	
	public List<Route> listRoutesFromParent(Route route){
		return getDaoFactory().getRouteDao().listRoutesFromParent(route);
	}
	
	public List<Route> listRoutsForSector(Route route){
		return getDaoFactory().getRouteDao().listRoutsForSector(route);
	}
	
	public List<Route> listLengthsFromRoute(Route route){
		return getDaoFactory().getRouteDao().listLengthsFromRoute(route);
	}
	
	public List<Route> listForSearch(Route route){
		return getDaoFactory().getRouteDao().listForSearch(route);
	}
	
	public List<Route> listFreeRouts(Route route){
		return getDaoFactory().getRouteDao().listForSearch(route);
	}
	
	public Route getRoute(Route route){
		return getDaoFactory().getRouteDao().getRoute(route);
	}
	
	public List<Quotation> listQuotation(){
		return getDaoFactory().getRouteDao().listQuotation();
	}
	
	
	
}

