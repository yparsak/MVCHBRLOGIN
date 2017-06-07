package com.sample.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sample.model.User;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();

		// name must be unique.
		String hql = "from User where name='"+u.getName()+"'";
		List<User> UserList = session.createQuery(hql).list();
		if (UserList.isEmpty()) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(u.getPassword());
			u.setPassword(hashedPassword);
			session.persist(u);
		}
	}

	@Override
	public void updateUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(u.getPassword());
		u.setPassword(hashedPassword);
		session.update(u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> UserList = session.createQuery("from User").list();
		return UserList;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User u = (User) session.load(User.class, new Integer(id));
		return u;
	}

	@Override
	public void removeUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Integer(id));
		if(null != u){
			session.delete(u);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String name, String password){
		
		Session session = this.sessionFactory.getCurrentSession();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		String hql = "from User where name='"+name+"'";
		List<User> UserList = session.createQuery(hql).list();
		if (UserList.isEmpty()) {
			return null;
		}
		else {
			for (int j=0; j < UserList.size(); j++ ) {
				User user = UserList.get(j);
				if (encoder.matches(password, user.getPassword())) {
					return user;
				}
			}
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean isUser(String name, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		String hql = "from User where name='"+name+"'";
		List<User> UserList = session.createQuery(hql).list();
		if (UserList.isEmpty()) {
			return false;
		}
		else {
			for (int j=0; j < UserList.size(); j++ ) {
				User user = UserList.get(j);
				if (encoder.matches(password, user.getPassword())) {
					return true;
				}
			}
			return false;
		}
	}
}
