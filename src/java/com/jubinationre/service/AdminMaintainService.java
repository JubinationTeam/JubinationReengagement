/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jubinationre.service;

import com.jubinationre.backend.CallAPIExotel;
import com.jubinationre.backend.EmailService;
import com.jubinationre.backend.LoginInfoService;
import com.jubinationre.model.dao.AdminDAOImpl;
import com.jubinationre.model.dao.CallAPIMessageDAOImpl;
import com.jubinationre.model.dao.MessageDAOImpl;
import com.jubinationre.model.pojo.Admin;
import com.jubinationre.model.pojo.CallAPIMessage;
import com.jubinationre.model.pojo.Message;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Welcome
 */
@Service
@Transactional
public class AdminMaintainService {
    
@Autowired 
AdminDAOImpl adao;
@Autowired 
MessageDAOImpl mdao;
@Autowired
CallAPIMessageDAOImpl callDao;
@Autowired
CallAPIExotel callHandler;

String excelOutputFilePath="C:\\Users\\Administrator\\Documents\\NetBeansProjects\\Reengagement\\Jubination\\web\\admin\\data.xls";

    
    
    public Admin checkPresence(Admin admin){
       admin = (Admin) adao.readProperty(admin.getUsername());
       return admin;
    }

    public boolean buildEmployee(String username, String name, String work, String initiatorName) {
        Admin creator =  checkPresence(new Admin(initiatorName));
        
        
            Integer passcode=null;
            Random r = new Random();
            passcode=r.nextInt(9)*1000+r.nextInt(9)*100+r.nextInt(9)*10+r.nextInt(9);
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            String pass=encoder.encodePassword(passcode.toString(), null).toString();
           
            new LoginInfoService("Welcome to Jubination!!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                    +" We welcome you to Jubination's new Call Monitoring website. Please note your following credentials. You can change your password through settings.<br/>"
                    + "<br/> "
                    + "<br/>"
                    + "Your user id is "+username+" and password is : "+pass
                    + "<br/>"
                    + "Login URL http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support",
                    "admin",username).start();
             System.err.println(pass);
            passcode=null;
            encoder=null;
            
        
        if(creator==null){
            return false;
        }
      
        Admin admin=new Admin(username);
       admin.setPassword(pass);
       admin.setRole("ROLE_ADMINISTRATOR");
       admin.setWork(work);
       admin.setPower(creator.getPower()+1);
       admin.setName(name);
        pass=null;
                
                if(adao.buildEntity(admin)!=null){
                    admin=null;
                    return true;
                }
    return false;
    }
 public void sendEmailUpdate() {
          
            new EmailService("sumitha@jubination.com","Call records updated!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                    +" Reengagement Call records are updated. <br/>"
                    + "<br/> "
                    + "check http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support").start();
            new EmailService("sneha@jubination.com","Call records updated!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                    +" Reengagement Call records are updated. <br/>"
                    + "<br/> "
                    + "check http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support").start();
            new EmailService("souvik@jubination.com","Call records updated!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                   +" Reengagement Call records are updated. <br/>"
                    + "<br/> "
                    + "check http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support").start();
            new EmailService("subhadeep@jubination.com","Call records updated!",
                    "Hi, "
                    + "<br/>"
                    + "<br/>"
                   +" Reengagement Call records are updated. <br/>"
                    + "<br/> "
                    + "check http://162.246.21.98/jubination/admin"
                    + "<br/>"
                    + "<br/>"
                    + "Regards,<br/>Jubination Support").start();
 }

      public List<Admin> getHrList(int power){
          return (List<Admin>) adao.fetchEntities(power);
      }      

    public boolean setPassword(Admin admin, String parameter) {
       return adao.updateProperty(admin, parameter, "Password");
    }

    public boolean deleteEmployee(Admin admin) {
       return adao.deleteEntity(admin);
    }

    public boolean sendMail(Admin sender, Admin receiver, String subject, String mail) {
    Message msg= new Message();
    msg.setReceiver(receiver);
    msg.setSender(sender);
    msg.setSubject(subject);
    msg.setBody(mail);
    msg=(Message) mdao.buildEntity(msg);
    
    return msg!=null;
    }

    public List<Message> inboxMail(Admin admin) {
    return (List<Message>) adao.readPropertyList(admin, "Inbox");
  
    }

    public List<Message> sentMail(Admin admin) {
    return (List<Message>) adao.readPropertyList(admin, "Sent");
    }

   
    public CallAPIMessage addCallAPIMessage(CallAPIMessage call){
        return (CallAPIMessage) callDao.buildEntity(call);
    }
    
    public List<CallAPIMessage> getCallBySid(String sid){
       return (List<CallAPIMessage>) callDao.getByProperty(sid, "Sid");
    }
    
   
    
    
    
    public List<CallAPIMessage> getAllCallRecordsByDate(String date) {
        
        return (List<CallAPIMessage>) callDao.getByProperty(date, "DateCreated");
    }
    public CallAPIMessage getCallRecordBySid(String sid) {
        return ((List<CallAPIMessage>) callDao.getByProperty(sid, "Sid")).get(0);
    }

    public boolean updateCallAPIMessage(CallAPIMessage call) {
            return callDao.updateProperty(call);
    }
public void buildCallAPIMessage(CallAPIMessage call){
            callDao.buildEntity(call);
    }
    public Object getPendingCallOnDate(String date) {
        
      return (List<CallAPIMessage>) callDao.getByProperty(date, "PendingOnDate");
    }

    public void doStageThreeCall(CallAPIMessage call){
        System.out.println("Adding Stage 3 Calling. "+callHandler.getStageThreeUpdates().size()+" updates added");
           if(call!=null){
                                
                                callHandler.getStageThreeUpdates().offer(call);
           }
                  
    }
    
    public void doCall(String numbers){
        for(String number:numbers.trim().split(System.lineSeparator())){
            if(!number.isEmpty()){
            if(!numbers.startsWith("0")){
                                    number="0"+number;
                }
                                  callHandler.setAppId("107784");
                                    callHandler.setCaller("02239698495");
                                           
                                            callHandler.getNumbers().offer(number);

                                            if(!callHandler.isFlag()){
                                                    callHandler.setFlag(true);
                                               }
                           
                        }
        }
                
    }
    
     public void doCall2(String numbers){
        for(String number:numbers.trim().split(System.lineSeparator())){
            if(!number.isEmpty()){
            if(!numbers.startsWith("0")){
                                    number="0"+number;
                }
                                    callHandler.setAppId("121603");
                                    callHandler.setCaller("02239971897");
                                            callHandler.getNumbers().offer(number);

                                            if(!callHandler.isFlag()){
                                                    callHandler.setFlag(true);
                                               }
                           
                        }
        }
     }
    public boolean createExcel(List<CallAPIMessage> list){
        
        
            boolean flag=false;
             HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		
                                      CallAPIMessage[] messageArray = new CallAPIMessage[list.size()];
                                        list.toArray(messageArray);
		Map<String, Object[]> data = new LinkedHashMap<>();
		
                                        Integer index=1;
                                        data.put(index.toString(), new Object[] {
                                                                                "CallFrom","CallTo","Status","TrackStatus",
                                                                                "CallType","DailWhomNumber","DailCallDuration","Message",
                                                                                "DateCreated","AnsweredBy","StartTime","EndTime",
                                                                                "DateUpdated","Duration","Price","Direction",
                                                                                "Digits","Sid","Uri","RecordingUrl","PhoneNumberSid",
                                                                                "AccountSid","ForwardedFrom","CallerName","ParentCallSid"
                                                                                });
                                        index++;
                                        for(CallAPIMessage message:messageArray){
                                                data.put(index.toString(), new Object[] {message.getCallFrom(),message.getCallTo(),message.getStatus(),message.getTrackStatus(),
                                                message.getCallType(),message.getDailWhomNumber(),message.getDailCallDuration(),message.getMessage(),
                                                message.getDateCreated(),message.getAnsweredBy(),message.getStartTime(),message.getEndTime(),
                                                message.getDateUpdated(),message.getDuration(),message.getPrice(),message.getDirection(),
                                                message.getDigits(),message.getSid(),message.getUri(),message.getRecordingUrl(),message.getPhoneNumberSid(),
                                                message.getAccountSid(),message.getForwardedFrom(),message.getCallerName(),message.getParentCallSid()});
                                                index++;
                                        
                                        }
                                        
                                        
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
		
		try {
			FileOutputStream out = new FileOutputStream(new File(excelOutputFilePath));
			workbook.write(out);
			out.close();
                                                           flag=true;
			System.out.println("Excel written successfully..");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
                return flag;
    }

}
