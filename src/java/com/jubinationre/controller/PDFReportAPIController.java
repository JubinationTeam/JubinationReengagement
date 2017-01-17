/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubinationre.controller;

import com.jubinationre.model.pojo.Message;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MumbaiZone
 */

@Controller
public class PDFReportAPIController {
    
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc ;
    private String Text ;
    private final String regex=".*\\d+.*";
    private final String high="High\t";
    private final String low="Low\t";
    private final String normal="Normal\t";
    private final String hba1c="HbA1c H.P.L.C %";
    private final String cholestrol="TOTAL CHOLESTEROL PHOTOMETRY 125  -  200mg/dl";
    private final String triglycerides="TRIGLYCERIDES PHOTOMETRY 25 - 200mg/dl";
    private final String ldl="LDL CHOLESTEROL - DIRECT PHOTOMETRY 85 - 130mg/dl";
    private final String tsh="THYROID STIMULATING HORMONE (TSH) C.L.I.A µIU/ml 0.30 - 5.5";
    private final String sgot="ASPARTATE AMINOTRANSFERASE (SGOT ) PHOTOMETRY M: 0 to 37 - F: 0 to 31U/l";
    private final String sgpt="ALANINE TRANSAMINASE (SGPT) PHOTOMETRY M: 13  to 40 - F: 10 to 28U/l";
    private final String creatinine="CREATININE - SERUM PHOTOMETRY Male: 0.6 - 1.1 Female: 0.5 - 0.8mg/dl";
    private final String um="URINARY MICROALBUMIN PHOTOMETRY µg/ml";
    private final String vd="25-OH VITAMIN D (TOTAL) C.L.I.A ng/ml";
    private final String vb12="VITAMIN B-12 C.L.I.A pg/ml";
    private final String fsh="FOLLICLE STIMULATING HORMONE (FSH) C.L.I.A mIU/ml";
    private final String lh="LUTEINISING HORMONE (LH) C.L.I.A mIU/ml";
    private final String prl="PROLACTIN (PRL) C.L.I.A ng/ml";
    private final String homocysteine="HOMOCYSTEINE C.L.I.A µmol/L";
    private final String hemoglobin="Male   : 13-17 Female : 12-15HEMOGLOBIN";
    
    @RequestMapping(value="/pdf/parser/{clinic}/{test}",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE,headers="Accept=*/*")
    public   @ResponseBody Message adminLoginCheck(@RequestBody Message msg, HttpServletRequest request,@PathVariable("clinic") String clinic,@PathVariable("test") String test,Principal principal) {
              
        if(clinic.equalsIgnoreCase("thyrocare")){   
            if(test.equalsIgnoreCase("blood")){  
                try{
                    System.out.println("Thyrocare Blood");
                     msg.setBody(parsePDFToTextThyrocreBlood(msg.getBody()));
                     return msg;
                }
                catch(Exception e){
                    
                     msg.setBody("Error:"+e);
                     return msg;
                 }
            }
        }
        msg.setBody("NA");
        return msg;
}
    
    
    
    public String parsePDFToTextThyrocreBlood(String url) throws IOException{
        LinkedHashMap<String,String> abnormalReadings = new LinkedHashMap<>();
        String abnormalReadingsText="NA";
        String gender="";
        boolean homocystFlag=false;
        
            String[] lines = ToText(url).split(System.getProperty("line.separator")); 
         
            for(int i=0;i<lines.length;i++){
                if(i>=0&&i<=4&&(lines[i].contains("Y/F")||lines[i].contains("Y/M"))){
                    gender=lines[i].split("\\(")[1].split("\\)")[0].split("/")[1];
                   
                }
               // if(lines[i].matches(regex)&&(!lines[i].startsWith("Alert"))){
                   if(lines[i].contains(hemoglobin)){
                       abnormalReadings.put("Hemoglobin ",(gender.equals("F")?(getValue(lines[i],hemoglobin)<12?low:normal):(getValue(lines[i],hemoglobin)<13?low:normal))+getValue(lines[i],hemoglobin));
                       System.out.println("1");
                   }
                    else if(lines[i].contains(hba1c)){
                           abnormalReadings.put("Hba1c ",(getValue(lines[i],hba1c)>6.5?high:normal)+getValue(lines[i],hba1c));
                      
                    }
                    else if(lines[i].contains(cholestrol)){
                        abnormalReadings.put("Cholestrol ",(getValue(lines[i],cholestrol)>200?high:normal)+getValue(lines[i],cholestrol));
                    }
                    else if(lines[i].contains(triglycerides)){
                        abnormalReadings.put("Triglycerides ",(getValue(lines[i],triglycerides)>200?high:normal)+getValue(lines[i],triglycerides));
                    }
                    else if(lines[i].contains(ldl)){
                       abnormalReadings.put("LDL ",(getValue(lines[i],ldl)>130?high:normal)+getValue(lines[i],ldl));
                    }
                    else if(lines[i].contains(tsh)){
                       abnormalReadings.put("TSH ",(getValue(lines[i],tsh)>5.5?high:(getValue(lines[i],tsh)>=0.3?normal:low))+getValue(lines[i],tsh));
                    }
                    else if(lines[i].contains(sgot)){
                        abnormalReadings.put("SGOT ", (gender.equals("F")?(getValue(lines[i],sgot)>31?high:normal):(getValue(lines[i],sgot)>37?high:normal))+getValue(lines[i],sgot));
                    }
                    else if(lines[i].contains(sgpt)){
                        abnormalReadings.put("SGPT ",(gender.equals("F")?(getValue(lines[i],sgpt)>28?high:normal):(getValue(lines[i],sgpt)>40?high:normal))+getValue(lines[i],sgpt));
                    }
                    else if(lines[i].contains(creatinine)){
                        abnormalReadings.put("Creatinine ",(gender.equals("F")?(getValue(lines[i],creatinine)>0.8?high:normal):(getValue(lines[i],creatinine)>1.1?high:normal))+getValue(lines[i],creatinine));
                    }
                    else if(lines[i].contains(um)){
                        abnormalReadings.put("Urinary Microalbumin ",(getValue(lines[i],um)>25?high:normal)+getValue(lines[i],um));
                    }
                    else if(lines[i].contains(vd)){
                       abnormalReadings.put("Vitamin D",(getValue(lines[i],vd)<30?low:normal)+getValue(lines[i],vd));
                    }
                    else if(lines[i].contains(vb12)){
                        abnormalReadings.put("Vitamin B-12",(getValue(lines[i],vb12)<211?low:normal)+getValue(lines[i],vb12));
                    }
                    else if(lines[i].contains(fsh)){
                        abnormalReadings.put("FSH ",(getValue(lines[i],fsh)>34?high:(getValue(lines[i],fsh)>=1.5?normal:low))+getValue(lines[i],fsh));
                    }
                    else if(lines[i].contains(lh)){
                       abnormalReadings.put("LH ",(getValue(lines[i],lh)>77?high:(getValue(lines[i],lh)>=0.5?normal:low))+getValue(lines[i],lh));
                    }
                    else if(lines[i].contains(prl)){
                       abnormalReadings.put("PRL ",(getValue(lines[i],prl)>30?high:normal)+getValue(lines[i],prl));
                    }
                    else if(homocystFlag&&lines[i]!=null&&Pattern.compile("[\\d+]([\\.\\d]*)").matcher(lines[i].trim()).find()){
                        
                        
                        abnormalReadings.put("Homocysteine",(Float.valueOf(lines[i].trim())>30?high:normal)+lines[i].trim());
                            homocystFlag=false;
                        
                    }
                    else if(lines[i].contains(homocysteine)){
                      homocystFlag=true;
                    }
                //}
            }
           
           Set<String> keys=abnormalReadings.keySet();  
           
             abnormalReadingsText=gender+System.lineSeparator();
                System.out.println(gender);
                    for(String val:keys){
                                abnormalReadingsText+=val+"\t"+abnormalReadings.get(val)+System.lineSeparator();

                               System.out.println(val+"\t"+abnormalReadings.get(val));

                    }
                    return abnormalReadingsText;
           
    }
    
    
    public static Float getValue(String line, String removeLine){
                       Pattern pattern = Pattern.compile("[\\d+]([\\.\\d]*)");
                       Matcher matcher = pattern.matcher(line.replace(removeLine, "").trim());
                       if(matcher.find()){
                 
                                return Float.valueOf(matcher.group());
                         
                       }
                       return null;
    }
    public String ToText(String url) throws IOException
   {
       this.pdfStripper = null;
       this.pdDoc = null;
       pdDoc = PDDocument.load(new URL(url).openStream());
       
       pdDoc.getClass();

       pdfStripper = new PDFTextStripper(){
       
           @Override
           protected void processTextPosition( TextPosition text ){
            //  if(text.getFont().getName().endsWith("Bold")){
                 
                  super.processTextPosition(text);
            //  }
               
               
           }
       };
      // pdDoc.getNumberOfPages();
       pdfStripper.setStartPage(1);
       //pdfStripper.setEndPage(10);
       
       // reading text from page 1 to 10
       // if you want to get text from full pdf file use this code
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());
       
       Text = pdfStripper.getText(pdDoc);
       return Text;
   }

    
    
}
