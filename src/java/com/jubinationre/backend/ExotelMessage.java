/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubinationre.backend;

import com.jubinationre.model.pojo.CallAPIMessage;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

/**
 *
 * @author MumbaiZone
 */
@Component
@XmlRootElement(name="TwilioResponse")
public class ExotelMessage{ 
    
    CallAPIMessage successMessage;
    CallAPIMessage failureMessage;

    public CallAPIMessage getSuccessMessage() {
        return successMessage;
    }
    @XmlElement(name="Call")
    public void setSuccessMessage(CallAPIMessage successMessage) {
        this.successMessage = successMessage;
    }

    public CallAPIMessage getFailureMessage() {
        return failureMessage;
    }
    @XmlElement(name="RestException")
    public void setFailureMessage(CallAPIMessage failureMessage) {
        this.failureMessage = failureMessage;
    }

    
    
    
}
