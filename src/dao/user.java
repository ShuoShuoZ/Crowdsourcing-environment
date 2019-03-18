package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnect;

/**
 * 快递员类
 * @author RollingZ
 *
 */
public class user {
	public dataset dataset_item;// 数据集
	public int user_no;// 快递员编号
	public int user_location;// 快递员位置
	public int user_speed;//快递员速度
	public int user_tasknum;//快递员任务数量

	static PreparedStatement pst_user = null;
	static ResultSet rs_user = null;

	public user(dataset dataset_item, int user_no) {
		this.dataset_item = dataset_item;
		this.user_no = user_no;
	}

	public user(dataset dataset_item, int user_no, int user_location, int user_speed, int user_tasknum) {
		this.dataset_item = dataset_item;
		this.user_no = user_no;
		this.user_location = user_location;
		this.user_speed = user_speed;
		this.user_tasknum = user_tasknum;
	}

	public user() {
	}

	/**
	 * 增加一个数据集中的所有快递员对象
	 * 
	 * @param dataset_item 数据集对象
	 */
	public static void addUsers(dataset dataset_item) {
		// 获取快递员数量usernum
		int usernum = dataset_item.getConfig_item().getUser_num();

		// 增加usernum个快递员
		System.out.println("...增加快递员,数量=" + usernum);
		for (int i = 0; i < usernum; i++) {
			// 定义快递员对象
			user user_item = new user(dataset_item, i);
			// 增加一个快递员对象
			System.out.print("......快递员" + i + ": ");
			addUser(user_item);
		}
	}

	/**
	 * 增加一个快递员对象
	 * 
	 * @param user_item 快递员对象
	 */
	public static void addUser(user user_item) {
		String sql_user;
		// 获取数据集对象编号
		int dataset_id = user_item.dataset_item.getDataset_id();
		// 获取配置对象标识
		int config_id = user_item.dataset_item.getConfig_item().getConfig_id();
		// 获取快递员速度
		int finalspeed = user_item.dataset_item.getConfig_item().getSpeed();
		//int finalspeed = (int) (initspeed * Math.random() + 1);
		// 获取快递员任务数量
		int finaltasknum = user_item.dataset_item.getConfig_item().getNumber();
		//int finaltasknum = (int) (inittasknum * Math.random() + 1);
		// 获取快递员信用值
		int initcredit = user_item.dataset_item.getConfig_item().getCredit();
		int finalcredit = (int) (initcredit * Math.random() + 1);
		// 获取快递员位置 (取不到最后一个点)
		int placenum = user_item.dataset_item.getConfig_item().getPlace_num();
		int useraddress = (int) (placenum * Math.random());
		// 增加一个快递员对象到数据库表user中
		try {
			sql_user = "insert into user(config_id,dataset_id,user_no,user_speed,user_tasknum,user_credit,user_address) values(?,?,?,?,?,?,?)";
			pst_user = DBConnect.conn.prepareStatement(sql_user);
			pst_user.setInt(1, config_id);
			pst_user.setInt(2, dataset_id);
			pst_user.setInt(3, user_item.user_no);
			pst_user.setInt(4, finalspeed);
			pst_user.setInt(5, finaltasknum);
			pst_user.setInt(6, finalcredit);
			pst_user.setInt(7, useraddress);
			pst_user.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取快递员
	 * @param dataset_item
	 * @return user_items
	 */
	public static List<user> getUsers(dataset dataset_item) {
		List<user> user_items = new ArrayList<user>();
		String sql_usertwo;
		try {
			sql_usertwo = "select user_no,user_address,user_speed,user_tasknum from user where config_id='"
					+ dataset_item.getConfig_item().getConfig_id() + "' and dataset_id='" + dataset_item.getDataset_id()
					+ "' order by user_no";
			// System.out.println(sql_item);
			pst_user = DBConnect.conn.prepareStatement(sql_usertwo);
			rs_user = pst_user.executeQuery();
			while (rs_user.next()) {
				user user_item = new user(dataset_item, rs_user.getInt(1), rs_user.getInt(2),rs_user.getInt(3),rs_user.getInt(4));
				user_items.add(user_item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user_items;
	}

	public dataset getDataset_item() {
		return dataset_item;
	}
	public void setDataset_item(dataset dataset_item) {
		this.dataset_item = dataset_item;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public int getUser_location() {
		return user_location;
	}
	public void setUser_location(int user_location) {
		this.user_location = user_location;
	}
	public int getUser_speed() {
		return user_speed;
	}
	public void setUser_speed(int user_speed) {
		this.user_speed = user_speed;
	}
	public int getUser_tasknum() {
		return user_tasknum;
	}
	public void setUser_tasknum(int user_tasknum) {
		this.user_tasknum = user_tasknum;
	}
}
