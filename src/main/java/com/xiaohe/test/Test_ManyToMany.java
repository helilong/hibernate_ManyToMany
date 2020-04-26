package com.xiaohe.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.xiaohe.entity.Role;
import com.xiaohe.entity.User;
import com.xiaohe.utils.HibernateUtils;

public class Test_ManyToMany {
	
	//��Զ�ļ������
	@Test
	public void Test_insert() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//�õ�sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//�õ�session
			session = sessionFactory.openSession();
			//��������
			tx = session.beginTransaction();
			
			//��������û���Ϊÿ���û����������ɫ
			//1 ��������
			User user1 = new User();
			user1.setUser_name("lucy");
			user1.setUser_password("123");
			
			User user2 = new User();
			user2.setUser_name("mary");
			user2.setUser_password("456");
			
			Role r1 = new Role();
			r1.setRole_name("zong");
			r1.setRole_memo("zong");
			
			Role r2 = new Role();
			r2.setRole_name("mi");
			r2.setRole_memo("mi");
			
			Role r3 = new Role();
			r3.setRole_name("bao");
			r3.setRole_memo("bao");
			
			//2 ������ϵ���ѽ�ɫ�ŵ��û�����
			// user1 -- r1/r2
			user1.getSetRole().add(r1);
			user1.getSetRole().add(r2);
			
			// user2 -- r2/r3
			user2.getSetRole().add(r2);
			user2.getSetRole().add(r3);
			
			//3 �����û�
			session.save(user1);
			session.save(user2);
			
			//�ύ����
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory����Ҫ�ر�
			sessionFactory.close();
		}
	}

	
	//��Զ�ļ���ɾ��
	@Test
	public void Test_delect() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//�õ�sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//�õ�session
			session = sessionFactory.openSession();
			//��������
			tx = session.beginTransaction();
			
			User user = session.get(User.class, 1);
			session.delete(user);
			
			//�ύ����
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory����Ҫ�ر�
			sessionFactory.close();
		}
	}
	
	
	//��Զ�ļ��� ά�������ű�
	@Test
	public void Test_table() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//�õ�sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//�õ�session
			session = sessionFactory.openSession();
			//��������
			tx = session.beginTransaction();
			
			// ��ĳ���û���ĳ����ɫ
			//��lucy�о����˽�ɫ
			//1 ��ѯlucy�;�����
			User lucy = session.get(User.class, 1);
			Role role = session.get(Role.class, 1);
						
			//2 �ѽ�ɫ�ŵ��û���set��������
			lucy.getSetRole().add(role);
			
			//�ύ����
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory����Ҫ�ر�
			sessionFactory.close();
		}
	}
	
	
	//
	@Test
	public void Test_table2() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			//�õ�sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//�õ�session
			session = sessionFactory.openSession();
			//��������
			tx = session.beginTransaction();
			
			// ��ĳ���û�û����ĳ����ɫ
			User user = session.get(User.class, 2);
			Role role = session.get(Role.class, 3);
						
			//2 ���û�����ѽ�ɫȥ��
			user.getSetRole().remove(role);
			
			//�ύ����
			tx.commit();

		}catch(Exception e) {
			tx.rollback();
		}finally {
			session.close();
			//sessionFactory����Ҫ�ر�
			sessionFactory.close();
		}
	}
	
	
}
