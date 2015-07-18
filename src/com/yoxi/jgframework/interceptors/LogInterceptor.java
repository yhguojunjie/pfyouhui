package com.yoxi.jgframework.interceptors;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.entity.IdUserDefinedEntity;
import com.yoxi.jgframework.common.entity.IdIntegerIdentityAutoUserEntity;
import com.yoxi.jgframework.common.entity.IdIntegerIdentityEntity;
import com.yoxi.jgframework.common.entity.IdUUIDEntity;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.system.controller.BaseServiceController;
import com.yoxi.jgframework.util.DateUtils;
import com.yoxi.jgframework.util.oConvertUtils;


//@Component
//@Aspect
public class LogInterceptor {
	protected static final Logger logger = Logger.getLogger(BaseServiceController.class);
	//@Around("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..)) and " +
			//"@annotation(org.springframework.web.bind.annotation.RequestMapping) and " +
	//@Around("@annotation(com.yoxi.jgframework.LoginAnnotation)")	
	/**
	 * 登录日志跟踪
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object aroundLoginMethod(ProceedingJoinPoint pjp) throws Throwable {
		try {
			long dtbegin = DateUtils.getMillis();
			Object retVal = pjp.proceed();
			
			if (pjp.getTarget() instanceof BaseController){
			
				String objType = null;
				String objId = null;
				for(Object obj : pjp.getArgs()){
					if (obj instanceof IdIntegerIdentityAutoUserEntity){
						objType = obj.getClass().getName();
						objId =oConvertUtils.getString(((IdIntegerIdentityAutoUserEntity)obj).getId());
						continue;
					}					
				   else if (obj instanceof IdIntegerIdentityEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdIntegerIdentityEntity)obj).getId());
					   continue;
				   }
				   else  if (obj instanceof IdUserDefinedEntity){
					   objType = obj.getClass().getName();
					   //objId =oConvertUtils.getString(((IdUserDefinedEntity)obj).getId()); 
					   continue;
				   }else  if (obj instanceof IdUUIDEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdUUIDEntity)obj).getId()); 
					   continue;
				   }
				}
				long takeTime = DateUtils.getMillis() - dtbegin; 
				BaseController bsc = (BaseController)pjp.getTarget();
				bsc.getSystemService().addLog(bsc.getMessage(), 
						 Globals.Log_Leavel_INFO,Globals.Log_Type_LOGIN,
						objType,objId,(int)takeTime);
			}
			return retVal;
		} catch (Exception e) {		
			logger.error("-------------日志注入错误-----------------" + e);			
			return null;
		}
	}
	/**
	 * 退出日志跟踪
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object aroundLogoutMethod(ProceedingJoinPoint pjp) throws Throwable {
		try {
			long dtbegin = DateUtils.getMillis();
			Object retVal = pjp.proceed();
			
			if (pjp.getTarget() instanceof BaseController){
			
				String objType = null;
				String objId = null;
				for(Object obj : pjp.getArgs()){
					if (obj instanceof IdIntegerIdentityAutoUserEntity){
						objType = obj.getClass().getName();
						objId =oConvertUtils.getString(((IdIntegerIdentityAutoUserEntity)obj).getId());
						continue;
					}					
				   else if (obj instanceof IdIntegerIdentityEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdIntegerIdentityEntity)obj).getId());
					   continue;
				   }
				   else  if (obj instanceof IdUserDefinedEntity){
					   objType = obj.getClass().getName();
					   //objId =oConvertUtils.getString(((IdUserDefinedEntity)obj).getId()); 
					   continue;
				   }else  if (obj instanceof IdUUIDEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdUUIDEntity)obj).getId()); 
					   continue;
				   }
				}
				long takeTime = DateUtils.getMillis() - dtbegin; 
				BaseController bsc = (BaseController)pjp.getTarget();
				bsc.getSystemService().addLog(bsc.getMessage(), 
						 Globals.Log_Leavel_INFO,Globals.Log_Type_EXIT,
						objType,objId,(int)takeTime);
			}
			return retVal;
		} catch (Exception e) {
			logger.error("-------------日志注入错误-----------------" + e);		
			return null;
		}
	}
	
	/**
	 * 删除日志跟踪
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object aroundDeleteMethod(ProceedingJoinPoint pjp) throws Throwable {
		try {
			long dtbegin = DateUtils.getMillis();
			Object retVal = pjp.proceed();
			
			if (pjp.getTarget() instanceof BaseController){
			
				String objType = null;
				String objId = null;
				for(Object obj : pjp.getArgs()){
					if (obj instanceof IdIntegerIdentityAutoUserEntity){
						objType = obj.getClass().getName();
						objId =oConvertUtils.getString(((IdIntegerIdentityAutoUserEntity)obj).getId());
						continue;
					}					
				   else if (obj instanceof IdIntegerIdentityEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdIntegerIdentityEntity)obj).getId());
					   continue;
				   }
				   else  if (obj instanceof IdUserDefinedEntity){
					   objType = obj.getClass().getName();
					   //objId =oConvertUtils.getString(((IdUserDefinedEntity)obj).getId()); 
					   continue;
				   }else  if (obj instanceof IdUUIDEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdUUIDEntity)obj).getId()); 
					   continue;
				   }
				}
				long takeTime = DateUtils.getMillis() - dtbegin; 
				BaseController bsc = (BaseController)pjp.getTarget();
				bsc.getSystemService().addLog(bsc.getMessage(), 
						Globals.Log_Leavel_INFO,Globals.Log_Type_DEL, 
						objType,objId,(int)takeTime);
			}
			return retVal;
		} catch (Exception e) {
			logger.error("-------------日志注入错误-----------------" + e);		
			return null;
		}
	}
	
	/**
	 * 新建、修改操作日志跟踪
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object aroundAddOrUpdateMethod(ProceedingJoinPoint pjp) throws Throwable {
		try {
			long dtbegin = DateUtils.getMillis();
			Object retVal = pjp.proceed();
			
			if (pjp.getTarget() instanceof BaseController){
				BaseController bsc = (BaseController)pjp.getTarget();
				if (bsc.isNew() == null) //不记载日志
					return retVal;
				String objType = null;
				String objId = null;
				for(Object obj : pjp.getArgs()){
					if (obj instanceof IdIntegerIdentityAutoUserEntity){
						objType = obj.getClass().getName();
						objId =oConvertUtils.getString(((IdIntegerIdentityAutoUserEntity)obj).getId());
						continue;
					}					
				   else if (obj instanceof IdIntegerIdentityEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdIntegerIdentityEntity)obj).getId());
					   continue;
				   }
				   else  if (obj instanceof IdUserDefinedEntity){
					   objType = obj.getClass().getName();
					   //objId =oConvertUtils.getString(((IdUserDefinedEntity)obj).getId()); 
					   continue;
				   }else  if (obj instanceof IdUUIDEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdUUIDEntity)obj).getId()); 
					   continue;
				   }
				}
				long takeTime = DateUtils.getMillis() - dtbegin; 
				if (bsc.isNew())
					bsc.getSystemService().addLog(bsc.getMessage(), 
						 Globals.Log_Leavel_INFO,Globals.Log_Type_INSERT,
						objType,objId,(int)takeTime);
				else 
					bsc.getSystemService().addLog(bsc.getMessage(), 
							 Globals.Log_Leavel_INFO,Globals.Log_Type_UPDATE,
							objType,objId,(int)takeTime);
			}
			return retVal;
		} catch (Exception e) {
			logger.error("-------------日志注入错误-----------------" + e);		
			return null;
		}
	}
	
	/**
	 * 上传日志跟踪
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object aroundUploadMethod(ProceedingJoinPoint pjp) throws Throwable {
		try {
			long dtbegin = DateUtils.getMillis();
			Object retVal = pjp.proceed();
			
			if (pjp.getTarget() instanceof BaseController){
			
				String objType = null;
				String objId = null;
				for(Object obj : pjp.getArgs()){
					if (obj instanceof IdIntegerIdentityAutoUserEntity){
						objType = obj.getClass().getName();
						objId =oConvertUtils.getString(((IdIntegerIdentityAutoUserEntity)obj).getId());
						continue;
					}					
				   else if (obj instanceof IdIntegerIdentityEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdIntegerIdentityEntity)obj).getId());
					   continue;
				   }
				   else  if (obj instanceof IdUserDefinedEntity){
					   objType = obj.getClass().getName();
					   //objId =oConvertUtils.getString(((IdUserDefinedEntity)obj).getId()); 
					   continue;
				   }else  if (obj instanceof IdUUIDEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdUUIDEntity)obj).getId()); 
					   continue;
				   }
				}
				long takeTime = DateUtils.getMillis() - dtbegin; 
				BaseController bsc = (BaseController)pjp.getTarget();
				bsc.getSystemService().addLog(bsc.getMessage(), 
						Globals.Log_Leavel_INFO,Globals.Log_Type_UPLOAD, 
						objType,objId,(int)takeTime);
			}
			return retVal;
		} catch (Exception e) {
			logger.error("-------------日志注入错误-----------------" + e);		
			return null;
		}
	}
	
	/**
	 * 其他操作日志跟踪
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	public Object aroundOtherMethod(ProceedingJoinPoint pjp) throws Throwable {
		try {
			long dtbegin = DateUtils.getMillis();
			Object retVal = pjp.proceed();
			
			if (pjp.getTarget() instanceof BaseController){				
				String objType = null;
				String objId = null;
				for(Object obj : pjp.getArgs()){
					if (obj instanceof IdIntegerIdentityAutoUserEntity){
						objType = obj.getClass().getName();
						objId =oConvertUtils.getString(((IdIntegerIdentityAutoUserEntity)obj).getId());
						continue;
					}	
					
				   else if (obj instanceof IdIntegerIdentityEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdIntegerIdentityEntity)obj).getId());
					   continue;
				   }
				   else  if (obj instanceof IdUserDefinedEntity){
					   objType = obj.getClass().getName();
					   //objId =oConvertUtils.getString(((IdUserDefinedEntity)obj).getId()); 
					   continue;
				   }else  if (obj instanceof IdUUIDEntity){
					   objType = obj.getClass().getName();
					   objId =oConvertUtils.getString(((IdUUIDEntity)obj).getId()); 
					   continue;
				   }
				}
				long takeTime = DateUtils.getMillis() - dtbegin; 
				BaseController bsc = (BaseController)pjp.getTarget();
				bsc.getSystemService().addLog(bsc.getMessage(), 
						 Globals.Log_Leavel_INFO,Globals.Log_Type_OTHER,
						objType,objId,(int)takeTime);
			}
			return retVal;
		} catch (Exception e) {
			logger.error("-------------日志注入错误-----------------" + e);		
			return null;
		}
	}
//
//    //@Before("execution(* org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..)) && " +
//			//"@annotation(org.springframework.web.bind.annotation.RequestMapping) && " +
//    @Before("@annotation(com.yoxi.jgframework.LogAnnotation)")	
//	public void beforeMethod(JoinPoint joinPoint) throws Exception {
//    	System.out.println("执行了beforeMethod Advice");
//		String temp = joinPoint.getStaticPart().toShortString();
//		String longTemp = joinPoint.getStaticPart().toLongString();
//		joinPoint.getStaticPart().toString();
//		String classType = joinPoint.getTarget().getClass().getName();
//		String methodName = temp.substring(10, temp.length() - 1);
//		Class<?> className = Class.forName(classType);
//		Class[] args = new Class[joinPoint.getArgs().length];
//		String[] sArgs = (longTemp.substring(longTemp.lastIndexOf("(") + 1,
//				longTemp.length() - 2)).split(",");
//		for (int i = 0; i < args.length; i++) {
//			if (sArgs[i].endsWith("String[]")) {
//				args[i] = Array.newInstance(Class.forName("java.lang.String"),
//						1).getClass();
//			} else if (sArgs[i].endsWith("Long[]")) {
//				args[i] = Array.newInstance(Class.forName("java.lang.Long"), 1)
//						.getClass();
//			} else if (sArgs[i].indexOf(".") == -1) {
//				if (sArgs[i].equals("int")) {
//					args[i] = int.class;
//				} else if (sArgs[i].equals("char")) {
//					args[i] = char.class;
//				} else if (sArgs[i].equals("float")) {
//					args[i] = float.class;
//				} else if (sArgs[i].equals("long")) {
//					args[i] = long.class;
//				}
//			} else {
//				args[i] = Class.forName(sArgs[i]);
//			}
//		}
//		Method method = className.getMethod(
//				methodName.substring(methodName.indexOf(".") + 1,
//						methodName.indexOf("(")), args);
//		if (method.isAnnotationPresent(LogAnnotation.class)) {
//			LogAnnotation logAnnotation = method
//					.getAnnotation(LogAnnotation.class);
////			String operateModelNm = logAnnotation.operateModelNm();
////			String operateDescribe = logAnnotation.operateDescribe();
////			String operateFuncNm = logAnnotation.operateFuncNm();
//
//		}
//
//	}

//	public void writeLogInfo(JoinPoint joinPoint) throws Exception,
//			IllegalAccessException {
//		String temp = joinPoint.getStaticPart().toShortString();
//		String longTemp = joinPoint.getStaticPart().toLongString();
//		joinPoint.getStaticPart().toString();
//		String classType = joinPoint.getTarget().getClass().getName();
//		String methodName = temp.substring(10, temp.length() - 1);
//		Class<?> className = Class.forName(classType);
//		// 日志动作
//		@SuppressWarnings("rawtypes")
//		Class[] args = new Class[joinPoint.getArgs().length];
//		String[] sArgs = (longTemp.substring(longTemp.lastIndexOf("(") + 1,
//				longTemp.length() - 2)).split(",");
//		for (int i = 0; i < args.length; i++) {
//			if (sArgs[i].endsWith("String[]")) {
//				args[i] = Array.newInstance(Class.forName("java.lang.String"),
//						1).getClass();
//			} else if (sArgs[i].endsWith("Long[]")) {
//				args[i] = Array.newInstance(Class.forName("java.lang.Long"), 1)
//						.getClass();
//			} else if (sArgs[i].indexOf(".") == -1) {
//				if (sArgs[i].equals("int")) {
//					args[i] = int.class;
//				} else if (sArgs[i].equals("char")) {
//					args[i] = char.class;
//				} else if (sArgs[i].equals("float")) {
//					args[i] = float.class;
//				} else if (sArgs[i].equals("long")) {
//					args[i] = long.class;
//				}
//			} else {
//				args[i] = Class.forName(sArgs[i]);
//			}
//		}
//		Method method = className.getMethod(
//				methodName.substring(methodName.indexOf(".") + 1,
//						methodName.indexOf("(")), args);
//		// BaseSpringController thisController = (BaseSpringController)
//		// joinPoint
//		// .getTarget();
//		// // 如果该方法写了注解才做操作
//		// if (method.isAnnotationPresent(LogAnnotation.class)) {
//		// LogAnnotation logAnnotation = method
//		// .getAnnotation(LogAnnotation.class);
//		// String operateModelNm = logAnnotation.operateModelNm();
//		// String operateFuncNm = logAnnotation.operateFuncNm();
//		// String operateDescribe = logAnnotation.operateDescribe();
//		// List<String> logArgs = null;
//		// if (operateDescribe.indexOf("#") != -1) {
//		// logArgs = new ArrayList<String>();
//		// int startIndex = operateDescribe.indexOf("#", 0);
//		// int endIndex = operateDescribe.indexOf("#", startIndex + 1);
//		// while (startIndex != -1) {
//		// String tempArg = operateDescribe.substring(startIndex + 1,
//		// endIndex);
//		// startIndex = operateDescribe.indexOf("#", endIndex + 1);
//		// endIndex = operateDescribe.indexOf("#", startIndex + 1);
//		// logArgs.add(tempArg);
//		// }
//		// }
//		//
//		// // 获取被注解方法的参数，实现动态注解
//		// String logArg = null;
//		// // 被注解方法只有一个参数的情况可用%替代要传入的参数
//		// if (args.length == 1) {
//		// if (args[0].getName().equals("java.lang.Long")
//		// || args[0].getName().equals("java.lang.Integer")
//		// || args[0].getName().equals("java.lang.String")) {
//		// logArg = String.valueOf((joinPoint.getArgs())[0]);
//		// } else if (args[0] == String[].class) {
//		// String[] arrayArg = (String[]) (joinPoint.getArgs())[0];
//		// logArg = Arrays.toString(arrayArg);
//		// } else if (args[0].getName().startsWith(
//		// "com.apabi.leopard.model")) {
//		// Method m = args[0].getMethod("getId");
//		// logArg = String.valueOf(m.invoke(joinPoint.getArgs()[0]));
//		// // 包含了两个操作的日志内容如save方法中包含了增加和修改操作，根据是否存在参数来判断是什么操作
//		// if (operateDescribe.indexOf("|") != -1) {
//		// if (!logArg.equals("null")) {
//		// operateDescribe = operateDescribe
//		// .substring(operateDescribe.indexOf("|") + 1);
//		// } else {
//		// operateDescribe = operateDescribe.substring(0,
//		// operateDescribe.indexOf("|"));
//		// }
//		// }
//		// }
//		// // 将注解中%转换为被拦截方法参数中的id
//		// if (!logArg.equals("null")) {
//		// operateDescribe = operateDescribe.indexOf("%") != -1 ?
//		// operateDescribe
//		// .replace("%", logArg) : operateDescribe;
//		// }
//		// } else {
//		// Object obj[] = joinPoint.getArgs();
//		// for (int k = 0; k < logArgs.size(); k++) {
//		// for (int j = k; j < obj.length; j++) {
//		// // 如果是实体
//		// if (logArgs.get(k).startsWith("@")) {
//		// if (args[j].getName().startsWith(
//		// "com.apabi.leopard.model")) {
//		// Method m = args[j].getMethod("getId");
//		// logArg = String.valueOf(m.invoke(joinPoint
//		// .getArgs()[j]));
//		// // 包含了两个操作的日志内容如save方法中包含了增加和修改操作，根据是否存在参数来判断是什么操作
//		// if (!logArg.equals("null")) {
//		// operateDescribe = operateDescribe
//		// .substring(operateDescribe
//		// .indexOf("|") + 1);
//		// } else {
//		// operateDescribe = operateDescribe
//		// .substring(0, operateDescribe
//		// .indexOf("|"));
//		// }
//		// } else {
//		// continue;
//		// }
//		// // 数组
//		// } else if (logArgs.get(k).startsWith("{1}")) {
//		// String[] arrayArg = thisController.request
//		// .getParameterValues(logArgs.get(k)
//		// .substring(1));
//		// logArg = Arrays.toString(arrayArg);
//		// // String
//		// } else {
//		// logArg = thisController.request
//		// .getParameter(logArgs.get(k));
//		//
//		// if (logArgs.get(k).equals("bsId") && logArg == null) {
//		// logArg = SpringSecurityUtils.getCurrentAdmin()
//		// .getNwOfficeId().toString();
//		// }
//		// if (operateDescribe.indexOf("|") != -1) {
//		// if (!logArg.equals("null")) {
//		// operateDescribe = operateDescribe
//		// .substring(operateDescribe
//		// .indexOf("|") + 1);
//		// } else {
//		// operateDescribe = operateDescribe
//		// .substring(0, operateDescribe
//		// .indexOf("|"));
//		// }
//		// }
//		// }
//		// if (logArg == null || logArg.equals("null")) {
//		// logArg = "";
//		// }
//		// operateDescribe = operateDescribe.replace(
//		// "#" + logArgs.get(k) + "#", logArg);
//		// break;
//		// }
//		// }
//		// }
//		// OperateLog log = null;
//		// Admin admin = SpringSecurityUtils.getCurrentAdmin();
//		// if (admin != null) {
//		// log = new OperateLog(admin.getNwOfficeId(),
//		// admin.getLoginName(), operateModelNm, operateFuncNm,
//		// operateDescribe, new Date(),
//		// getIpAddr(thisController.request));
//		// } else {
//		// final User user = (User) thisController.request.getSession()
//		// .getAttribute(SsoService.SESSION_USER);
//		// log = new OperateLog(user.getNwOfficeId(), user.getUsername(),
//		// operateModelNm, operateFuncNm, operateDescribe,
//		// new Date(), user.getIpAddress());
//		// }
//		//
//		// operateLogManager.saveOrUpdate(log);
//
//	}
}