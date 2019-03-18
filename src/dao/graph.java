package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.DBConnect;

/**
 * λ�õ��ϵͼ
 * @author RollingZ
 *
 */
public class graph {
	public static PreparedStatement pst_graph=null;
	public static ResultSet rs_graph=null;
	
	/**
	 * ����λ�õ��ϵͼ
	 * @param dataset_item  ���ݼ�
	 */
	public static void addGraph(dataset dataset_item) {
		//��ȡ����λ�õ����
		List<place> place_items=place.getPlaces(dataset_item);
		//��ȡȨֵ����
		int value_item=dataset_item.getConfig_item().getValue();
		//��ȡλ�õ�֮����ڱߵĸ���
		double r=dataset_item.getConfig_item().getR();
		//��ȡ���Ա�ٶ�
		int speed = dataset_item.getConfig_item().getSpeed();
		//����λ�õ����
		place placeone=null;
		place placetwo=null;
		for(int i=0;i<place_items.size()-1;i++) {
			//��ȡλ�õ����1��Ϣ
			placeone=place_items.get(i);
			for(int j=i+1;j<place_items.size();j++) {
				//��ȡλ�õ����2��Ϣ
				placetwo=place_items.get(j);
				//����(0,1)֮���һ�������p
				double p=Math.random();
				if(p>=r) {
					//�������һ����Ȩֵ
					int weight=(int)(value_item*Math.random()+500);
					int time = weight/(speed*60);
					System.out.println("......��("+placeone.getPlace_no()+","+placetwo.getPlace_no()+"),Ȩֵ="+time);
					//����λ�õ�֮��ıߣ�˫��
					edge.addEdge(new edge(placeone,placetwo,time));
					edge.addEdge(new edge(placetwo,placeone,time));
				}
			}
		}
		
	}
	
	/*
	 * delete all edges in dataset
	 */

	public static void deleteGraph(int config_id,int dataset_id) {
		String sql_graph;
		try {
			sql_graph="delete from graph where config_id='"+config_id+"' and dataset_id='"+dataset_id+"'";
			pst_graph=DBConnect.conn.prepareStatement(sql_graph);
			pst_graph.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
