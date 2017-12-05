package com.whu.birthday;

import com.whu.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebListener()
public class MyListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public MyListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
      /*服务器 一启动，监听器开始监听，然后发送邮件*/
        Timer timer = new Timer();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        String s = format.format(new Date());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
                String sql = "select * from user where birthday like ? ";
                List<User> userList = null;
                try {
                     userList = runner.query(sql, new BeanListHandler<>(User.class), "%" + s);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (userList != null && userList.size() > 0) {
                    for (User user : userList) {
                        try {
                            MailUtils.sendMail(user.getEmail(), "生日祝福", "亲爱的" + user.getRealname() + "生日快乐");
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },new Date(),1000*10);
        System.out.println("-----");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
