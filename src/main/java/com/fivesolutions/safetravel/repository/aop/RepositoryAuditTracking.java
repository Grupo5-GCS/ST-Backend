package com.fivesolutions.safetravel.repository.aop;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fivesolutions.safetravel.entity.UserEntity;
import com.fivesolutions.safetravel.repository.jpa.UserJpaRepository;
import com.fivesolutions.safetravel.util.DateUtil;


@Aspect
@Component
public class RepositoryAuditTracking {
	
	@Autowired
	private UserJpaRepository userRepository;
	
	private static final Logger logger = LogManager.getLogger();
	
	private static final List<String> fieldsIgnore = null;
	
	private static final List<String> classesIgnore = Arrays.asList("com.fivesolutions.safetravel.entity.Catalog", "com.fivesolutions.safetravel.entity.CatalogDetail");
	
	@Pointcut("execution(* com.fivesolutions.safetravel.repository..*.save*(java.lang.Object+,..)) " + "&& args(entity,..)")
	public void saveMethodExecution(Object entity) {
		
	}

	@Around(value = "saveMethodExecution(entity)")
	public Object aroundMethodExeution(ProceedingJoinPoint joinPoint, Object entity) throws Throwable {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object object = null;
		try {
			if(PropertyUtils.getProperty(entity, "createDate") == null) {
				PropertyUtils.setProperty(entity, "createDate", DateUtil.getTime());
				PropertyUtils.setProperty(entity, "deleted", false);
				if(PropertyUtils.getProperty(entity, "status") == null) {
					PropertyUtils.setProperty(entity, "status", "1");
				}
			}
			if(PropertyUtils.getProperty(entity, "userCreateId") == null && !"anonymousUser".equals(principal)) {
				UserEntity userEntity = userRepository.findByUsername(principal.toString());
				//ClientEntity clientEntity = clientRepository.findByUsername(principal.toString());
				PropertyUtils.setProperty(entity, "userCreateId", userEntity.getId());	
			}
			PropertyUtils.setProperty(entity, "updateDate", DateUtil.getTime());
			Class clas = entity.getClass();
			//if(PropertyUtils.getProperty(entity, "userUpdateId") == null && !"anonymousUser".equals(principal))
			
			
			object = joinPoint.proceed();
			
		} catch(NoSuchMethodError e) {
			object = joinPoint.proceed();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch(Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return object;
	}
}
