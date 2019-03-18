package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import util.DBConnect;

/**
 * ����������ݼ�
 * @author RollingZ
 *
 */
public class dataset {
	public int dataset_id;  //���ݼ�id
	public config config_item;  //����
	
	public static List<user> user_item=new ArrayList<user>();  //���Ա����
	public static List<edge> edge_item=new ArrayList<edge>();  //�߼���
	public static List<place> place_item=new ArrayList<place>();//λ�õ㼯��
	public static List<task> task_item=new ArrayList<task>();//���񼯺�
	public static int user_num,place_num,task_num; //���Ա������λ�õ���������������
	
	static PreparedStatement pst_dataset=null;
	static ResultSet rs_dataset=null;
	
	public dataset() {}
	/**
	 * ���������캯��
	 * @param dataset_id   ���ݼ�id
	 * @param config_item  ����
	 */
	public dataset(int dataset_id,config config_item) {
		this.dataset_id=dataset_id;
		this.config_item=config_item;
	}
	/**
	 * ����һ���������ݼ���¼
	 * @param dataset_id
	 * @param config_id
	 */
	public static void addDataset(int dataset_id,int config_id){
		String sql_dataset;
		try{
			sql_dataset="insert into dataset(dataset_id,config_id) values(?,?)";
			pst_dataset=DBConnect.conn.prepareStatement(sql_dataset);
			pst_dataset.setInt(1, dataset_id);
			pst_dataset.setInt(2, config_id);
			pst_dataset.executeUpdate();	
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ݼ�����
	 * @param dataset_id  ���ݼ�id
	 * @param config_id   ����id
	 * @return dataset_item ���ݼ�����
	 */
	public static dataset getDataset(int dataset_id,int config_id) {
		//�������ݼ�����
		dataset dataset_item=new dataset();
		//�������ñ�ʶ��ȡ���ö��󣬲����丳�����ݼ����������config
		dataset_item.config_item=config.getConfig(config_id);
		dataset_item.dataset_id=dataset_id;
		return dataset_item;
	}
	/**
	 * �������ݼ�
	 * @param dataset_id  ���ݼ�id
	 * @param config_id   ����id
	 */
	public static void createDataset(int dataset_id,int config_id) {
		//�������ݼ���ʶ
		addDataset(dataset_id,config_id);
		//��ȡ���ݼ�����
		dataset dataset=getDataset(dataset_id,config_id);
		//����λ�õ�
		place.addPlaces(dataset);
		//���ӿ��Աλ��
		user.addUsers(dataset);
		//��������λ��
		task.addTasks(dataset);
		//����λ�õ�֮��Ĺ�ϵ
		graph.addGraph(dataset);
	}
	/**
	 * ɾ�����ݼ�
	 * @param dataset_id  ���ݼ�id
	 * @param config_id   ����id
	 */
	public static void deleteDataset(int dataset_id,int config_id) {
		String sql_dataset;
		try {
			sql_dataset="delete from dataset where config_id='"+config_id+"' and dataset_id='"+dataset_id+"'";
			pst_dataset=DBConnect.conn.prepareStatement(sql_dataset);
			pst_dataset.executeUpdate();//ɾ��dataset��item��weight
			graph.deleteGraph(dataset_id,config_id);//ɾ��graph
			System.out.println("delete dataset ...");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ�������ݼ�
	 * @param dataset_id  ���ݼ�id
	 * @param config_id   ����id
	 * @return
	 */
	public static void getTestDataset(int dataset_id,int config_id) {
		dataset dataset=getDataset(dataset_id,config_id);
		System.out.println(dataset.getConfig_item());
		user_num=dataset.getConfig_item().getUser_num();
		place_num=dataset.getConfig_item().getPlace_num();
		task_num=dataset.getConfig_item().getTask_num();
		user_item=user.getUsers(dataset);
		place_item=place.getPlaces(dataset);
		task_item=task.getTasks(dataset);
		
		//System.out.println("user_num="+user_num+",place_num="+place_num+",task_num="+task_num);
		//outputTestDataset(dataset_id,config_id);
	}
	/**
	 * ����������ݼ�
	 * @param dataset_id  ���ݼ�id
	 * @param config_id   ����id
	 */
	public static void outputTestDataset(int dataset_id,int config_id) {
		
		for(place place_solo:place_item) {
			System.out.println("place_no="+place_solo.getPlace_no());
			for(edge edge:place_solo.getEdges()) {
				System.out.print("("+edge.getPlaceone().getPlace_no()+"-->"+edge.getPlacetwo().getPlace_no()+","+edge.getValue()+") ");
			}
			System.out.println();
		}
		for(user user_solo:user_item) {
			System.out.println("user_no="+user_solo.getUser_no()+", user_location="+user_solo.getUser_location());
		}
		for(task task_solo:task_item) {
			System.out.println("task_no="+task_solo.getTask_no()+", task_begin="+task_solo.getTask_begin()
			+", task_end="+task_solo.getTask_end());
		}
	}
	public int getDataset_id() {
		return dataset_id;
	}
	public void setDataset_id(int dataset_id) {
		this.dataset_id = dataset_id;
	}
	public config getConfig_item() {
		return config_item;
	}
	public void setConfig_item(config config_item) {
		this.config_item = config_item;
	}
	
}