package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnect;

/**
 * ���Ա��
 * @author RollingZ
 *
 */
public class user {
	public dataset dataset_item;// ���ݼ�
	public int user_no;// ���Ա���
	public int user_location;// ���Աλ��
	public int user_speed;//���Ա�ٶ�
	public int user_tasknum;//���Ա��������

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
	 * ����һ�����ݼ��е����п��Ա����
	 * 
	 * @param dataset_item ���ݼ�����
	 */
	public static void addUsers(dataset dataset_item) {
		// ��ȡ���Ա����usernum
		int usernum = dataset_item.getConfig_item().getUser_num();

		// ����usernum�����Ա
		System.out.println("...���ӿ��Ա,����=" + usernum);
		for (int i = 0; i < usernum; i++) {
			// ������Ա����
			user user_item = new user(dataset_item, i);
			// ����һ�����Ա����
			System.out.print("......���Ա" + i + ": ");
			addUser(user_item);
		}
	}

	/**
	 * ����һ�����Ա����
	 * 
	 * @param user_item ���Ա����
	 */
	public static void addUser(user user_item) {
		String sql_user;
		// ��ȡ���ݼ�������
		int dataset_id = user_item.dataset_item.getDataset_id();
		// ��ȡ���ö����ʶ
		int config_id = user_item.dataset_item.getConfig_item().getConfig_id();
		// ��ȡ���Ա�ٶ�
		int finalspeed = user_item.dataset_item.getConfig_item().getSpeed();
		//int finalspeed = (int) (initspeed * Math.random() + 1);
		// ��ȡ���Ա��������
		int finaltasknum = user_item.dataset_item.getConfig_item().getNumber();
		//int finaltasknum = (int) (inittasknum * Math.random() + 1);
		// ��ȡ���Ա����ֵ
		int initcredit = user_item.dataset_item.getConfig_item().getCredit();
		int finalcredit = (int) (initcredit * Math.random() + 1);
		// ��ȡ���Աλ�� (ȡ�������һ����)
		int placenum = user_item.dataset_item.getConfig_item().getPlace_num();
		int useraddress = (int) (placenum * Math.random());
		// ����һ�����Ա�������ݿ��user��
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
	 * ��ȡ���Ա
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
