package com.insa.fr;

import com.insa.fr.entity.Alive;
import com.insa.fr.entity.Logs;
import com.insa.fr.services.logsServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/***************
 *
 * @author herve
 *************/

@RestController
@Api(value = "Application Logs : gestion des services logs, concentre tous les logs des différents services")
public class LogsController {
    
    String VERSION="0.1";
    
    String WHOAMI="ws_services_logs";
    
    @Autowired
    logsServices logserviceLocal;
       
    /*************************************
     * Service de vie des service logs
     * @return 
     **************************************/    
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Tester la vie du service")
    public Alive IAmAlive()
    {
        return new Alive("42", VERSION, WHOAMI);
    }

    /*************************************
     * Service de vie des service logs
     * @return 
     **************************************/        
    @GetMapping("/logs")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Tester la vie du service")
    public Alive IAmAlive2()
    {
        return new Alive("42", VERSION, WHOAMI);
    }
    
    /*************************************
     * Service de vie des service logs
     * @return 
     **************************************/        
    @GetMapping("/logs/isalive")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Tester la vie du service")
    public Alive IAmAlive3()
    {
        return new Alive("42", VERSION, WHOAMI);
    }
    
    
    /****************************************
     * Envoyer des données SYSTEM
     * @param log
     * @return 
     ****************************************/
    @PostMapping(value="/logs/logSYSTEM",consumes={"application/json"})
    @ApiOperation(value = "Inserer les logs de niveau SYSTEM")
    public ResponseEntity<Object> InsertLogSYSTEM(@RequestBody Logs log) 
    {
      //insert log System
      logserviceLocal.putLogs(log,"SYSTEM");
      return new ResponseEntity<>("{\"reponse\":\"Log SYSTEM inserré\"}", HttpStatus.CREATED);
    }
    
    /****************************************
     * Envoyer des données TRANSACTION
     * @param log
     * @return 
     ****************************************/
    @PostMapping(value="/logs/logTRANSACTION",consumes={"application/json"})
    @ApiOperation(value = "Inserer les logs de niveau TRANSACTION")
    public ResponseEntity<Object> InsertLogTRANSACTION(@RequestBody Logs log) {
      
      //insert log Transaction
      logserviceLocal.putLogs(log,"TRANSACTION");      
      return new ResponseEntity<>("{\"reponse\":\"Log TRANSACTION inserré\"}", HttpStatus.CREATED);
   }
    
    /****************************************
     * Envoyer des données ACTION
     * @param log
     * @return 
     ****************************************/
    @PostMapping(value="/logs/logACTION",consumes={"application/json"})
    @ApiOperation(value = "Inserer les logs de niveau ACTION")
    public ResponseEntity<String> InsertLogACTION(@RequestBody Logs log) {
        
      //insert log ACTION
      logserviceLocal.putLogs(log,"ACTION");
      return new ResponseEntity<>("{\"reponse\":\"Log ACTION inserré\"}", HttpStatus.CREATED);
   }
    
    /****************************************
     * Envoyer des données ACTION
     * @param dateDeb
     * @param dateFin
     * @return 
     ****************************************/
    @GetMapping(value="/logs/getACTION/{dateDeb}/{dateFin}", produces ={"application/json"} )
    @ApiOperation(value =" Récuperer les log de niveau ACTION entre deux dates date au format AAAA-MM-DD")
    public ResponseEntity<Object> getACTION(@PathVariable("dateDeb") String dateDeb, @PathVariable("dateFin") String dateFin) {       
      return new ResponseEntity<>(logserviceLocal.getLOGS(dateDeb,dateFin,"ACTION"), HttpStatus.CREATED);
   }
    
    /****************************************
     * Envoyer des données TRANSACTION
     * @param dateDeb
     * @param dateFin
     * @return 
     ****************************************/
    @GetMapping(value="/logs/getTRANSACTION/{dateDeb}/{dateFin}", produces ={"application/json"} )
    @ApiOperation(value =" Récuperer les log de niveau TRANSACTION entre deux dates date au format AAAA-MM-DD")
    public ResponseEntity<Object> getTRANSACTION(@PathVariable("dateDeb") String dateDeb, @PathVariable("dateFin") String dateFin) {
      return new ResponseEntity<>(logserviceLocal.getLOGS(dateDeb,dateFin,"TRANSACTION"), HttpStatus.CREATED);
   }
    
     /****************************************
     * Envoyer des données SYSTEM
     * @param dateDeb
     * @param dateFin
     * @return 
     ****************************************/
    @GetMapping(value="/logs/getSYSTEM/{dateDeb}/{dateFin}", produces ={"application/json"} )
    @ApiOperation(value =" Récuperer les log de niveau SYSTEM entre deux dates date au format AAAA-MM-DD")
    public ResponseEntity<Object> getSYSTEM(@PathVariable("dateDeb") String dateDeb, @PathVariable("dateFin") String dateFin) {
      return new ResponseEntity<>(logserviceLocal.getLOGS(dateDeb,dateFin,"SYSTEM"), HttpStatus.CREATED);
   }
}
