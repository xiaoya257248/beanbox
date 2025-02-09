package com.beanbox.test.aop;

import com.beanbox.aop.proxy.support.CglibAopProxy;
import com.beanbox.aop.aspect.AdvisedSupport;
import com.beanbox.aop.aspect.AspectJExpressionPointCut;
import com.beanbox.aop.aspect.TargetSource;
import com.beanbox.test.pojo.IUserService;
import com.beanbox.test.pojo.UserService;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author: @zyz
 */
public class AopTest {

	@Test
	public void test_aop_method() throws NoSuchMethodException {

		UserService userService = new UserService ();

		AdvisedSupport advisedSupport=new AdvisedSupport ();
		TargetSource targetSource = new TargetSource (userService);
		advisedSupport.setTargetSource (targetSource);

		// 为啥这里必须声明为接口 否则到代理类里就match为false 在外面就为true
		AspectJExpressionPointCut pointCut=new AspectJExpressionPointCut ("execution( * com.beanbox.test.pojo.UserService.*(..))");
		advisedSupport.setMethodMatcher (pointCut);
		advisedSupport.setMethodInterceptor (new UserServiceInterceptor ());
//		IUserService proxy= (IUserService) new JdkDynamicAopProxy (advisedSupport).getProxy ();
		IUserService proxy= (IUserService) new CglibAopProxy (advisedSupport).getProxy ();
		Class<?> clazz=userService.getClass ();

		Method method = clazz.getDeclaredMethod ("queryUserInfo");
//		System.out.println (pointCut.matches (method,clazz));
//		System.out.println (pointCut.matches (clazz));
//		System.out.println (pointCut.matches (method,targetSource.getTarget ().getClass ()));
//		System.out.println (advisedSupport.getMethodMatcher ().matches (method,null));
		System.out.println (proxy.queryUserInfo ());

		//
	}

	@Test
	public void test_pointcut() throws NoSuchMethodException {
		AspectJExpressionPointCut pointCut=new AspectJExpressionPointCut ("execution(* com.beanbox.test.pojo.UserService.*(..))");
		Class< UserService > clazz= UserService.class;
		Method method = clazz.getDeclaredMethod ("queryUserInfo");
		System.out.println (pointCut.matches (clazz));
		System.out.println (pointCut.matches (method,clazz));
	}


}
