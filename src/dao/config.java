package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnect;

/**
 * ������
 * @author RollingZ
 *
 */
public class config {
	public int config_id;//����id
	public int place_num;//λ�õ����
	public int task_num;//�������
	public int user_num;//�û�����
	public double r;//λ�õ�֮����ڹ�ϵ�ĸ���  0.5
	public int value;//�ߵ�Ȩֵ�Ͻ�  5000m
	public int speed;//���Ա�ٶ� 5m/s
	public int time;//����ʱ���Ͻ�  60min
	public int money;//����������  10Ԫ
	public int number;//���Ա�������� 3��
	public int credit;//���Ա��������  5
	
	public config() {	
	}


	public config(int config_id, int place_num, int task_num, int user_num, double r, int value, int speed, int time,
			int money, int number, int credit) {
		this.config_id = config_id;
		this.place_num = place_num;
		this.task_num = task_num;
		this.user_num = user_num;
		this.r = r;
		this.value = value;
		this.speed = speed;
		this.time = time;
		this.money = money;
		this.number = number;
		this.credit = credit;
	}

	/**
	 * �������ñ�ʶ��ȡ���ö���
	 * @param config_id  ���ñ�ʶ
	 * @return    ���ö���
	 */
	public static config getConfig(int config_id) {
		config config_item=null;
		PreparedStatement pst_config=null;
		ResultSet rs_config=null;
		String sql_config;
		try {
			sql_config="select config_id,place_num,task_num,user_num,r,value,speed,time,money,number,credit from config where config_id='"+config_id+"'";
			pst_config=DBConnect.conn.prepareStatement(sql_config);
			rs_config=pst_config.executeQuery();
			while(rs_config.next()) {
				config_item=new config(rs_config.getInt(1),
						          rs_config.getInt(2),
						          rs_config.getInt(3),
						          rs_config.getInt(4),
						          rs_config.getDouble(5),
						          rs_config.getInt(6),
						          rs_config.getInt(7),
						          rs_config.getInt(8),
						          rs_config.getInt(9),
						          rs_config.getInt(10),
						          rs_config.getInt(11));
			}			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return config_item;
	}
	
	public int getConfig_id() {
		return config_id;
	}
	public void setConfig_id(int config_id) {
		this.config_id = config_id;
	}
	public int getPlace_num() {
		return place_num;
	}
	public void setPlace_num(int place_num) {
		this.place_num = place_num;
	}
	public int getTask_num() {
		return task_num;
	}
	public void setTask_num(int task_num) {
		this.task_num = task_num;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public double getR() {
		return r;
	}
	public void setR(double r) {
		this.r = r;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}

}
