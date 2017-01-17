package com.jubinationre.test;





import com.jubinationre.model.dao.AdminDAOImpl;
import com.jubinationre.model.dao.CallAPIMessageDAOImpl;
import com.jubinationre.model.dao.MessageDAOImpl;
import com.jubinationre.model.pojo.Admin;
import com.jubinationre.model.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;


/**
 *
 * @author Welcome
 */
public class AdvancedTest {
   
    public static void main(String[] args) {
       
        MessageDAOImpl mdao = new MessageDAOImpl();
        AdminDAOImpl adao = new AdminDAOImpl();
         CallAPIMessageDAOImpl callDao =new CallAPIMessageDAOImpl();
        
        mdao.setSessionFactory(HibernateUtil.getSessionFactory());
        adao.setSessionFactory(HibernateUtil.getSessionFactory());
        callDao.setSessionFactory(HibernateUtil.getSessionFactory());
        adao.buildEntity(new Admin("support@jubination.com","abcdef","ROLE_ADMINISTRATOR","Support",0,"Administrator"));
//            

        
        
     
        
        
    }
}
