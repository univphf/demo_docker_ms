package com.insa.fr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author herve
 */

@Data
@AllArgsConstructor
public class LogRep {
    
   String datelog;
   String service;
   String level;
   String trying;
   String message;    
}
