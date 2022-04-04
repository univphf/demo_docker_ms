package com.insa.fr.services;

import com.insa.fr.entity.LogRep;
import com.insa.fr.entity.Logs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/****************
 *
 * @author herve
 ****************/
@Service
public class logsServicesImpl implements logsServices {
    
     @Autowired
	private MongoTemplate mongotemplate;
    
     //Liste des logs ACTION
    List<LogRep> listLogs=new ArrayList<>();
    Date gdateDeb=null;
    Date gdateFin=null;

    /*********************************
     * Recuperer ACTION
     * @param dtdeb
     * @param dtfin
     * @param collection
     * @return 
     ********************************/
    @Override
    public List<LogRep> getLOGS(String dtdeb, String dtfin,String collection) 
    {
        //corriger date 
        correctDates(dtdeb, dtfin);
        //réaliser la requête 
        Query query = new Query();
          query.addCriteria(Criteria.where("datelog").gte(gdateDeb)
           .andOperator(Criteria.where("datelog").lte(gdateFin)));
          
          System.out.println(query.toString());
          
        //retourne les dates  
        return mongotemplate.find(query, LogRep.class, collection);
    }
       
    /*************************************
     * Tester les dates de debut et fin
     * @param dtdeb
     * @param dtfin 
     *************************************/
    public void correctDates(String dtdeb, String dtfin)
    {
        
        //date doit être au format ISO
        if (dtdeb.isEmpty()) {gdateDeb=current_dateMinuit();}

        //si date de fin vide alors date du jour.
        if (dtfin.isEmpty()) {gdateFin=current_date();}

        
        //essayer de traduire le format de date de debut
        // si pas ok alors date du jour 00:00
        if (!isdate(dtdeb)) {gdateDeb=current_dateMinuit();} else {gdateDeb=translateDateIsoMinuit(dtdeb);}
        if (!isdate(dtfin)) {gdateFin=current_date();} else {gdateFin=translateDateIso(dtfin);}

        //si dateDeb>dateFin ? alors inverser
        checkAndSwapDate(gdateDeb,gdateFin);
    }
        
   
    /**************************************
     * Check et swap date Deb et Fin
     * @param gdtDeb
     * @param gdtFin 
     **************************************/
    private void checkAndSwapDate(Date gdtDeb, Date gdtFin) {
      //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");  
      //LocalDateTime dd=LocalDateTime.parse(gdtDeb, dtf);
      //LocalDateTime df=LocalDateTime.parse(gdtFin, dtf);
      Date gdtTmp=null;
      if (gdtDeb.after(gdtFin)) {gdtTmp=gdateDeb;gdateDeb=gdtFin;gdateFin=gdtTmp;} //Swap dates
      //sinon OK
    }
    
    
    /******************************
    * Date actuelle au format ISO
    * @return 
    ******************************/
    private Date current_date()
    {     
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");  
       LocalDateTime now = LocalDateTime.now().plusHours(2); //correction pour la local Image Docker
       Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
       Date date = Date.from(instant);     
       return date;
    }
    
    
    /******************************
    * Date actuelle au format ISO
    * @return 
    ******************************/
    private Date current_dateMinuit()
    {     
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
       LocalDateTime now = LocalDateTime.now();  //correction pour la locale Image Docker
       Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
       Date date = Date.from(instant);
       return date;
    }
    
    /***************************
     * 
     * @param atester
     * @return 
     ***************************/
    private boolean isdate(String atester)
    {
    try
    {    
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(atester);
    } catch (ParseException e) {return false;}
    return true;
    }

    /******************************
     * Convertir format date en date iso minuit
     * @param dtdeb
     * @return 
     ******************************/
    private Date translateDateIsoMinuit(String dtdeb) 
    {
        Date date = null;
         try {
             date = new SimpleDateFormat("yyyy-MM-dd").parse(dtdeb+"T00:00:00.000Z");
         } catch (ParseException ex) {
             Logger.getLogger(logsServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        //String date = null;
          return date;
    }
    
    /******************************
     * Convertir format date en date iso minuit
     * @param dtdeb
     * @return 
     ******************************/
    private Date translateDateIso(String dtdeb) 
    {
         Date date = null;
         try {
             date = new SimpleDateFormat("yyyy-MM-dd").parse(dtdeb+"T23:59:59.000Z");
         } catch (ParseException ex) {
             Logger.getLogger(logsServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        //String date = null;
          return date;
    }

    /***********************************
    * Inserrer logs dans collection
    * @param log
     * @param collection
    * @return 
    ***********************************/
    @Override
    public boolean putLogs(Logs log,String collection) {
        log.setDatelog(new Date());
        mongotemplate.save(log,collection);
        return true;
    }

}
