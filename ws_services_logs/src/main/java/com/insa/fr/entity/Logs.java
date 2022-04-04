package com.insa.fr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import lombok.Data;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author herve
 * **/
@Data
public class Logs 
{  
    @JsonIgnore    
    //String datelog;
    //@DateTimeFormat(iso =ISO.DATE_TIME)
    Date datelog; //date do log crer par le service

    
    String service;  //quel service nom de l'application
    String level;  //niveau du log
    String trying;  //ce que j'essaye de faire
    String message;  //le message qui suis l'action
}
