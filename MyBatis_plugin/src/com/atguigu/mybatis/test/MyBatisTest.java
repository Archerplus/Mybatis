package com.atguigu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import com.atguigu.mybatis.dao.EmployeeMapperAnnotation;
import com.atguigu.mybatis.dao.EmployeeMapperDynamicSQL;
import com.atguigu.mybatis.dao.EmployeeMapperPlus;

class MyBatisTest {

	/*
	 * 1. 获取sqlSessionFactory对象：
	 * 		解析文件的每一个信息都保存在Configuration中，返回包含Configuration的DefaultSqlSession
	 * 		注意：MapperStatement：代表一个增删改查的详细信息
	 * 		
	 * 2. 获取sqlSession对象
	 * 		返回一个DefaultSqlSession对象，包含Executor和Configutation;
	 * 		这一步会创建Executor对象
	 * 3. 获取接口的代理对象(Mapper.class)
	 * 4.执行增删改查方法
	 * 
	 */
	
	@Test
	void test() throws IOException {

		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = mapper.getEmpById(28);
			System.out.println(employee);
		} finally {
			openSession.close();
		}

	}

	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}

	/*
	 * 插件原理
	 * 在四大对象创建的时候
	 * 1.每个创建出来的对象不是直接返回的，而是
	 * 		interceptorChain.pluginAll(paramenterHandler);
	 * 2.获取到所有的Interceptor（拦截器）（插件需要实现的接口）
	 * 		调用interceptor.plugin(target);返回target包装后的对象
	 * 3.插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面的编程方式）
	 * 		我们的插件可以为四大对象创建出代理对象
	 * 		代理对象就可以拦截到四大对象的每一个执行方法
	 * 		
	 * public Object pluginAll(Object object){
	 * 	for(Interceptor interceptor : interceptors){
	 * 		target = interceptor.plugin(target);
	 *  }
	 *  return target;
	 * }
	 */
	
	/*
	 * 插件的编写
	 * 1.编写Interceptor的实现类
	 * 2.使用@Intercepts注解完成插件签名
	 * 3.将写好的插件注册在全局配置文件中
	 */
	@Test
	public void testPlugin() {
		
	}
}
