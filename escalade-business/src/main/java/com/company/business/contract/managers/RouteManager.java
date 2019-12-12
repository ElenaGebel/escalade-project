package com.company.business.contract.managers;

import java.util.List;

import com.company.model.bean.Quotation;
import com.company.model.bean.Route;

public interface RouteManager {
    List<Route> listRoutesFromParent(Route route);

    int registerRoute(Route route);

    List<Route> listRoutsForSector(Route route);

    void updateRoute(Route route);

    void deleteRoute(Route route);

    List<Route> listLengthsFromRoute(Route route);
    
    List<Route> listForSearch(Route route);
    
    List<Route> listFreeRouts(Route route);
    
    Route getRoute(Route route);
    
    List<Quotation> listQuotation();
}
