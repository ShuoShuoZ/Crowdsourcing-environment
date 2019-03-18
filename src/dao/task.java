package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnect;

/**
 * ������
 * 
 * @author RollingZ
 *
 */
public class task {
	public dataset dataset_item;// ���ݼ�����
	public int task_no;// ������
	public int task_begin;// ����ʼ��
	public int task_end;// ������ɵ�
	public int task_time;//����ʱ��
	public int task_userno;//���Ա���

	static PreparedStatement pst_task = null;
	static ResultSet rs_task = null;

	public task(dataset dataset_item, int task_no) {
		this.dataset_item = dataset_item;
		this.task_no = task_no;
	}
	

	public task(dataset dataset_item, int task_no, int task_begin, int task_end, int task_time, int task_userno) {
		this.dataset_item = dataset_item;
		this.task_no = task_no;
		this.task_begin = task_begin;
		this.task_end = task_end;
		this.task_time = task_time;
		this.task_userno = task_userno;
	}

	/**
	 * ����һ�����ݼ��е������������
	 * 
	 * @param dataset_item ���ݼ�����
	 */
	public static void addTasks(dataset dataset_item) {
		// ��ȡ��������
		int tasknum = dataset_item.getConfig_item().getTask_num();

		// ����tasknum������
		System.out.println("...���������,����=" + tasknum);
		for (int i = 0; i < tasknum; i++) {
			// �����������
			task task_item = new task(dataset_item, i);
			// ����һ���������
			System.out.print("......����" + i + ": ");
			addTask(task_item);
		}
	}

	/**
	 * ����һ���������
	 * 
	 * @param task_item
	 *            �������
	 */
	public static void addTask(task task_item) {
		String sql_task;
		// ��ȡ���ݼ�������
		int dataset_id = task_item.dataset_item.getDataset_id();
		// ��ȡ���ö����ʶ
		int config_id = task_item.dataset_item.getConfig_item().getConfig_id();
		// ��ȡ����ʱ��
		int inittasktime = task_item.dataset_item.getConfig_item().getTime();
		int finaltasktime = (int) (inittasktime * Math.random() + 20);
		// ��ȡ������
		int inittaskmoney = task_item.dataset_item.getConfig_item().getMoney();
		int finaltaskmoney = (int) (inittaskmoney * Math.random() + 1);
		// ��ȡ�������(ȡ�����յ�)
		int placenum = task_item.dataset_item.getConfig_item().getPlace_num();
		int taskbegin = (int) (placenum * Math.random());
		// ��ȡ�����յ�
		int taskend = (int) (placenum * Math.random());
		//��ȡ����-�����
		int usernum = task_item.dataset_item.getConfig_item().getUser_num();
		int finalusernum = (int) (usernum * Math.random());
		// ����һ������������ݿ��task��
		try {
			sql_task = "insert into task(config_id,dataset_id,task_no,task_time,task_money,task_begin,task_end,task_userno) values(?,?,?,?,?,?,?,?)";
			pst_task = DBConnect.conn.prepareStatement(sql_task);
			pst_task.setInt(1, config_id);
			pst_task.setInt(2, dataset_id);
			pst_task.setInt(3, task_item.task_no);
			pst_task.setInt(4, finaltasktime);
			pst_task.setInt(5, finaltaskmoney);
			pst_task.setInt(6, taskbegin);
			pst_task.setInt(7, taskend);
			pst_task.setInt(8, finalusernum);
			pst_task.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�����б�
	 * 
	 * @param dataset_item
	 * @return task_items
	 */

	public static List<task> getTasks(dataset dataset_item) {
		List<task> task_items = new ArrayList<task>();
		String sql_tasktwo;
		try {
			sql_tasktwo = "select task_no,task_begin,task_end,task_time,task_userno from task where config_id='"
					+ dataset_item.getConfig_item().getConfig_id() + "' and dataset_id='" + dataset_item.getDataset_id()
					+ "' order by task_no";
			pst_task = DBConnect.conn.prepareStatement(sql_tasktwo);
			rs_task = pst_task.executeQuery();
			while (rs_task.next()) {
				task task_item = new task(dataset_item, rs_task.getInt(1), rs_task.getInt(2), rs_task.getInt(3), rs_task.getInt(4), rs_task.getInt(5));
				task_items.add(task_item);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task_items;
	}

	public dataset getDataset_item() {
		return dataset_item;
	}

	public void setDataset_item(dataset dataset_item) {
		this.dataset_item = dataset_item;
	}

	public int getTask_no() {
		return task_no;
	}

	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}

	public int getTask_begin() {
		return task_begin;
	}

	public void setTask_begin(int task_begin) {
		this.task_begin = task_begin;
	}

	public int getTask_end() {
		return task_end;
	}

	public void setTask_end(int task_end) {
		this.task_end = task_end;
	}

	public int getTask_time() {
		return task_time;
	}

	public void setTask_time(int task_time) {
		this.task_time = task_time;
	}

	public int getTask_userno() {
		return task_userno;
	}
	
	public void setTask_userno(int task_userno) {
		this.task_userno = task_userno;
	}
	
	
}
