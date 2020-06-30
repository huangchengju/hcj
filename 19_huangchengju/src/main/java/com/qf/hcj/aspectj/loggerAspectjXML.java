package com.qf.hcj.aspectj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;



@Component
public class loggerAspectjXML {

   Logger logger= LogManager.getLogger(loggerAspectjXML.class);

   public void errorMsg(JoinPoint joinPoint,Exception ex){
       logger.error(ex);
   }

   public void beforeLogger(JoinPoint joinPoint){
       logger.info("============="+joinPoint.getSignature().getName());
   }
}
