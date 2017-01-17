package com.jubinationre.controller;



import com.jubinationre.model.pojo.Admin;
import com.jubinationre.model.pojo.CallAPIMessage;
import com.jubinationre.model.pojo.Message;
import com.jubinationre.service.AdminMaintainService;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {
@Autowired
AdminMaintainService adminMaintain;

    public AdminController() {
    }
    
    @RequestMapping(value="/logout")
    public ModelAndView redirectLogOut(HttpSession session,HttpServletRequest request,Principal principal) {
      SecurityContextHolder.getContext().setAuthentication(null);
        session.invalidate();
        
        return new ModelAndView("redirect:/adminlogin");
    }
        @RequestMapping(value = "/adminlogin")
	public  ModelAndView adminLogin(HttpServletRequest request,Principal principal) {
              
            return new ModelAndView("adminlogin");
              
	}
        @RequestMapping(value = "/admin")
	public  ModelAndView adminLoginCheck(HttpServletRequest request,Principal principal) {
              
           
       
            ModelAndView model= new ModelAndView("adminpage");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            model.addObject("callrecords",adminMaintain.getPendingCallOnDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            return model;
	}
      
         @RequestMapping(value = "/admin/mail")
	public  ModelAndView adminMail(HttpServletRequest request,Principal principal) {
              
           
       
            ModelAndView model= new ModelAndView("adminmail");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            List<Message> inbox = adminMaintain.inboxMail(new Admin(principal.getName()));
            model.addObject("mails", inbox);
            return model;
	}
        

         @RequestMapping(value = "/admin/mail/sent")
	public  ModelAndView adminMailSent(HttpServletRequest request,Principal principal) {
              
           
       
            ModelAndView model= new ModelAndView("adminsentmail");
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            List<Message> sent = adminMaintain.sentMail(new Admin(principal.getName()));
            model.addObject("mails", sent);
            return model;
	}
        @RequestMapping(value = "/admin/send/mail", method = RequestMethod.POST)
	public  ModelAndView adminSendMail(HttpServletRequest request,Principal principal) {
             
            
            ModelAndView model= new ModelAndView("adminmail");
            Admin sender =adminMaintain.checkPresence(new Admin(principal.getName()));
              List<Message> inbox = adminMaintain.inboxMail(new Admin(principal.getName()));
            model.addObject("mails", inbox);
            String id=request.getParameter("receiver");
            String subject=request.getParameter("subject");
            String mail=request.getParameter("comment");
            Admin receiver=null;
            if(id!=null){
            receiver = adminMaintain.checkPresence(new Admin(id));
            }
            System.out.println(id+":"+principal.getName()+":"+subject+":"+mail);
            if(sender!=null&&receiver!=null){
            
            
            
            if(adminMaintain.sendMail(sender,receiver,subject,mail)){
                     model.addObject("message","Processed."); 
                } 
                else{
                    model.addObject("message","Not processed. Please try again!"); 
                } 
            
           
           
             
           
         
              model.addObject("admin",sender);
              sender=null;
            receiver=null;
            return model;
            }
            else{
                model.addObject("message","Error. Please try again!"); 
                return model; 

            }
	}
        
        
        
        
       
        
        @RequestMapping(value = "/admin/settings")
	public  ModelAndView adminSettings(HttpServletRequest request,Principal principal) {
              
            ModelAndView model= new ModelAndView("adminsettings");
            if(request.getParameter("msg")!=null){
                if(request.getParameter("msg").toString().equals("true")){
                     model.addObject("message","Changed."); 
                } 
                else if(request.getParameter("msg").toString().equals("false")){
                    model.addObject("message","Not changed. Please try again!"); 
                } 
             }
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            return model;
	}
        
       @RequestMapping(value = "/admin/settings/change_pswrd")
	public  ModelAndView adminChangePassword(HttpServletRequest request,Principal principal) {
                
                 Admin admin=adminMaintain.checkPresence(new Admin(principal.getName()));
                Md5PasswordEncoder encoder = new Md5PasswordEncoder();
                String temPassword=encoder.encodePassword(request.getParameter("oldpassword"), null);
                 encoder=null;
                 if(admin.getPassword().equals(temPassword)&&request.getParameter("newpassword").equals(request.getParameter("rnewpassword"))){
                     
                     if(request.getParameter("newpassword").length()>=6){
                     if(adminMaintain.setPassword(admin,request.getParameter("newpassword"))){
                         admin=null;
                            return new ModelAndView("redirect:/admin/settings?msg=true"); 
                     }
                     }
                 }
                 admin=null;
            return new ModelAndView("redirect:/admin/settings?msg=false"); 
            
          
             
           
	}
       
     
     @RequestMapping(value = "/admin/assign_admin", method = RequestMethod.POST)
    public ModelAndView assignAdmin(Principal principal,HttpServletRequest request) {
 Admin admin =adminMaintain.checkPresence(new Admin(principal.getName()));
                 if(adminMaintain.buildEmployee(request.getParameter("username"),request.getParameter("name"),request.getParameter("work"),principal.getName())){
                  
                     return new ModelAndView("redirect:/admin/hr?msg=true"); 
                     
                 }
                 
            return new ModelAndView("redirect:/admin/hr?msg=false"); 
              
         
    }
    
    
    @RequestMapping(value = "/admin/hr/delete/{username}", method = RequestMethod.GET)
    public ModelAndView deleteAdmin(@PathVariable("username") String username,Principal principal,HttpServletRequest request) {
 Admin destructor =adminMaintain.checkPresence(new Admin(principal.getName()));
     
              if(adminMaintain.checkPresence(new Admin(username.replace("$", "."))).getPower()>destructor.getPower()){
                    destructor=null;
                    
                 if(adminMaintain.deleteEmployee(new Admin(username.replace("$", ".")))){
                  return new ModelAndView("redirect:/admin/hr?msg=true"); 
                     
                 }
                 
                return new ModelAndView("redirect:/admin/hr?msg=false"); 
               }
             else{
                return new ModelAndView("redirect:/admin"); 

            }
         
    }
    @RequestMapping(value = "/admin/hr")
	public  ModelAndView adminHr(HttpServletRequest request,Principal principal) {
              Admin admin =adminMaintain.checkPresence(new Admin(principal.getName()));
                 
            ModelAndView model= new ModelAndView("adminhr");
             if(request.getParameter("msg")!=null){
                if(request.getParameter("msg").equals("true")){
                     model.addObject("message","Processed."); 
                } 
                else if(request.getParameter("msg").equals("false")){
                    model.addObject("message","Not processed. Please try again!"); 
                } 
             }
            model.addObject("admin_list",adminMaintain.getHrList(admin.getPower()));
            model.addObject("admin",admin);
             admin=null; 
            return model;
             
	}
        
        
        @RequestMapping(value="/admin/callinterface/call/beforetest",method = RequestMethod.POST)
    public ModelAndView callDicyCustomers(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallinterface");
        
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            
            if(request.getParameter("numbers")!=null){
                adminMaintain.doCall(request.getParameter("numbers"));
                model.addObject("message", "Keep Calm and attend calls..:P");
                return model;
            }
            model.addObject("message", "Error during call");
            return model;
       
        
    }
    
      @RequestMapping(value="/admin/callinterface/call/aftertest",method = RequestMethod.POST)
    public ModelAndView callDicyCustomers2(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallinterface");
        
            model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
            
            if(request.getParameter("numbers")!=null){
                adminMaintain.doCall2(request.getParameter("numbers"));
                model.addObject("message", "Keep Calm and attend calls..:P");
                return model;
            }
            model.addObject("message", "Error during call");
            return model;
       
        
    }
    
     @RequestMapping(value="/admin/callinterface")
    public ModelAndView callInterface(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallinterface");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
           
            return model;
       
        
    }
    @RequestMapping(value="/admin/callrecords")
    public ModelAndView callRecords(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallrecords");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        model.addObject("message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
                                      
        
           return model;
       
        
    }
    @RequestMapping(value="/admin/callrecords/get")
    public ModelAndView getCallRecords(HttpServletRequest request, Principal principal) throws IOException {
       
        ModelAndView model= new ModelAndView("admincallrecords");
        model.addObject("admin",adminMaintain.checkPresence(new Admin(principal.getName())));
        List<CallAPIMessage> list=adminMaintain.getAllCallRecordsByDate(request.getParameter("date"));
        if(list!=null&&!list.isEmpty()){   
            model.addObject("callrecords",list);
            model.addObject("message", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            model.addObject("excel",adminMaintain.createExcel(list));
            
            
            
            
            
        }
        else{
            model.addObject("message","No such records found");
        }
            return model;
       
        
    }
    
    @RequestMapping(value="/exotel/{value}",method=RequestMethod.GET)
    public ResponseEntity callUpdate(HttpServletRequest request,@PathVariable("value") String status, Principal principal) throws IOException {
                   System.out.println("@ Stage 3"); 
                   CallAPIMessage call= new CallAPIMessage();
                   call.setFrom(request.getParameter("From"));
                   call.setSid(request.getParameter("CallSid"));
                   call.setCallType(request.getParameter("CallType"));
                   call.setDailCallDuration(request.getParameter("DialCallDuration"));
                   call.setDigits(request.getParameter("Digits"));
                   call.setDailWhomNumber(request.getParameter("DialWhomNumber"));
                   call.setRecordingUrl(request.getParameter("RecordingUrl"));
                   call.setDirection(request.getParameter("Direction"));
        boolean flag=true;
        System.out.println("CallUpdate by exotel"+status);
            switch(status){
               case "1": 
                   call.setTrackStatus("Pressed 1. Customer spoke to us");
                   break;
               case "2":
                   call.setTrackStatus("Pressed 2. Customer requested for callback"); 
                   break;
               case "3":
                   call.setTrackStatus("Pressed 3. Customer not registered");
                   break;
               case "4":
                   call.setTrackStatus("Pressed 4. Customer spoke to us");
                   break;
               case "12":
                   call.setTrackStatus("Pressed 1. Customer did not speak to us");
                   break;
               case"32":
                   call.setTrackStatus("Pressed 3. Customer did not speak to us");
                   break;
               case "42":
                   call.setTrackStatus("Pressed 4. Customer did not speak to us");
                   break;
                case "5":
                   call.setTrackStatus("Pressed none. Customer spoke to us");
                   break;
               case "52":
                   call.setTrackStatus("Pressed none. Customer did not speak to us");
                   break;
               case "6":
                 call.setTrackStatus("Pressed invalid number. Customer spoke to us");
                   break;
               case "62":
                   call.setTrackStatus("Pressed invalid number. Customer did not speak to us");
                   break;   
               default:
                   flag=false;
                   System.out.println("Exotel details. Not an option");
                   break;
           }
            if(flag){
                adminMaintain.doStageThreeCall(call);
               return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
       
        
    }
    
}
