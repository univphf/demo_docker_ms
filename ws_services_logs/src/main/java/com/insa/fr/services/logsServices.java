
package com.insa.fr.services;

import com.insa.fr.entity.LogRep;
import com.insa.fr.entity.Logs;
import java.util.List;

/**
 *
 * @author herve
 */
public interface logsServices {
    
    public List<LogRep> getLOGS(String dtdeb, String dtfin,String collection);
    public boolean putLogs(Logs log,String collection);
}
