package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnect;

/**
 * 配置类
 * @author RollingZ
 *
 */
public class config {
	public int config_id;//配置id
	public int place_num;//位置点个数
	public int task_num;//任务个数
	public int user_num;//用户个数
	public double r;//位置点之间存在关系的概率  0.5
	public int value;//边的权值上界  5000m
	public int speed;//快递员速度 5m/s
	public int time;//任务时间上界  60min
	public int money;//任务金额上限  10元
	public int number;//快递员任务上限 3个
	public int credit;//快递员信用上限  5
	
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
	 * 根据配置标识获取配置对象
	 * @param config_id  配置标识
	 * @return    配置对象
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
