package com.itcast.erp.dao;

import java.util.List;

import com.itcast.erp.entity.Menu;
/**
 * 菜单数据访问接口
 * @author Administrator
 *
 */
public interface IMenuDao extends IBaseDao<Menu>{
	/**
	 * 根据员工编号获取菜单
	 */
	List<Menu> getMenusByEmpuuid(Long uuid);
	
	
	
}
