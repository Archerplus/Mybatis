package com.atguigu.mybatis.dao;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

/*
 * ��ɲ����ǩ����
 * 		����MyBatis��ǰ������������Ǹ�������Ǹ�����
 */

@Intercepts({ @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class) })
public class MyFirstPlugin implements Interceptor {

	/*
	 * intercept������ 
	 * 		����Ŀ������Ŀ�귽����ִ�У�
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("MyFirstPlugin......intercept" + invocation.getMethod());
		//��̬�ı�һ�����SQL���еĲ�������ǰ��ѯ1��Ա����ʵ�ʴ����ݿ��в�ѯ3��Ա��
		Object target = invocation.getTarget();
//		System.out.println("��ǰ���ص��Ķ���: " + target);
		//�õ���StatementHandle�����==��ParameterHandle==��parameterObject
		//�õ�target��Ԫ���ݣ�����������󣬾����õ�����������������ֵ
//		MetaObject metaObject = SystemMetaObject.forObject(target);
//		Object value = metaObject.getValue("parameterHandler.parameterObject");
//		System.out.println("sql����õĲ�����:  " + value);
		//�޸�sql����õĲ���
//		metaObject.setValue("parameterHandler.parameterObject", 11); 
		// ִ��Ŀ�귽��
		Object proceed = invocation.proceed();
		// ����ִ�к�ķ���ֵ
		return proceed;
	}

	/*
	 * plugin: ��װĿ����󣺰�װ��ΪĿ����󴴽�һ����������
	 */
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		System.out.println("MyFirstPlugin......plugin:mybatis��Ҫ��װ�Ķ���" + target);
		// ���ǿ��Խ���Plugin��wrap������ʹ�õ�ǰInterceptor��װ����Ŀ�����
		Object wrap = Plugin.wrap(target, this);
		// ����Ϊ��ǰtarget�����Ķ�̬����
		return wrap;
	}

	/*
	 * setProperties�� �����ע��ʱ��property���ý���
	 * 
	 */
	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		System.out.println("������õ���Ϣ" + properties);
	}

}