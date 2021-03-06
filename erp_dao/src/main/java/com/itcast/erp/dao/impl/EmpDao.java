package com.itcast.erp.dao.impl;

import java.util.List;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.itcast.erp.dao.IEmpDao;
import com.itcast.erp.entity.Emp;
import com.itcast.erp.entity.Menu;

/**
 * 员工数据访问类
 * 
 * @author Administrator
 *
 */
public class EmpDao extends BaseDao<Emp> implements IEmpDao {

	

	/**
	 * 构建查询条件
	 * 
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Emp emp1, Emp emp2, Object param) {
		DetachedCriteria dc = DetachedCriteria.forClass(Emp.class);
		if (emp1 != null) {
			if (null != emp1.getUsername() && emp1.getUsername().trim().length() > 0) {
				dc.add(Restrictions.like("username", emp1.getUsername(), MatchMode.ANYWHERE));
			}
			if (null != emp1.getPwd() && emp1.getPwd().trim().length() > 0) {
				dc.add(Restrictions.like("pwd", emp1.getPwd(), MatchMode.ANYWHERE));
			}
			if (null != emp1.getName() && emp1.getName().trim().length() > 0) {
				dc.add(Restrictions.like("name", emp1.getName(), MatchMode.ANYWHERE));
			}
			if (null != emp1.getEmail() && emp1.getEmail().trim().length() > 0) {
				dc.add(Restrictions.like("email", emp1.getEmail(), MatchMode.ANYWHERE));
			}
			if (null != emp1.getTele() && emp1.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", emp1.getTele(), MatchMode.ANYWHERE));
			}
			if (null != emp1.getAddress() && emp1.getAddress().trim().length() > 0) {
				dc.add(Restrictions.like("address", emp1.getAddress(), MatchMode.ANYWHERE));
			}
			// 根据部门查询
			if (null != emp1.getDep() && null != emp1.getDep().getUuid()) {
				dc.add(Restrictions.eq("dep", emp1.getDep()));
			}
			// 出生年月日查询 起始日期
			if (null != emp1.getBirthday()) {
				dc.add(Restrictions.ge("birthday", emp1.getBirthday()));
			}

		}
		if (null != emp2) {
			// 出生年月日查询 结束日期
			if (null != emp2.getBirthday()) {
				dc.add(Restrictions.le("birthday", emp2.getBirthday()));
			}
		}

		return dc;
	}

	/**
	 * 检测数据库的用户 判断是否查到了 返回一个list 这是因为数据库可能有多个相同的用户名和密码 ，要返回多个员工，但一般设置的时候 如果查到了
	 * return get(0)的数据
	 */
	public Emp findByUsernameAndPwd(String username, String pwd) {
		List<Emp> list = (List<Emp>) this.getHibernateTemplate().find("from Emp where username=? and pwd=?", username, pwd);
		
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	
	public void updatePwd(Long uuid, String newPwd) {
		String hql="update Emp set pwd=? where uuid=?";
		this.getHibernateTemplate().bulkUpdate(hql, newPwd, uuid);
		
	}

}
