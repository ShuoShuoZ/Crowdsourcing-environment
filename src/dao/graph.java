package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.DBConnect;

/**
 * 位置点关系图
 * @author RollingZ
 *
 */
public class graph {
	public static PreparedStatement pst_graph=null;
	public static ResultSet rs_graph=null;
	
	/**
	 * 增加位置点关系图
	 * @param dataset_item  数据集
	 */
	public static void addGraph(dataset dataset_item) {
		//获取所有位置点对象
		List<place> place_items=place.getPlaces(dataset_item);
		//获取权值上限
		int value_item=dataset_item.getConfig_item().getValue();
		//获取位置点之间存在边的概率
		double r=dataset_item.getConfig_item().getR();
		//获取快递员速度
		int speed = dataset_item.getConfig_item().getSpeed();
		//定义位置点对象
		place placeone=null;
		place placetwo=null;
		for(int i=0;i<place_items.size()-1;i++) {
			//获取位置点对象1信息
			placeone=place_items.get(i);
			for(int j=i+1;j<place_items.size();j++) {
				//获取位置点对象2信息
				placetwo=place_items.get(j);
				//生成(0,1)之间的一个随机数p
				double p=Math.random();
				if(p>=r) {
					//随机生成一个边权值
					int weight=(int)(value_item*Math.random()+500);
					int time = weight/(speed*60);
					System.out.println("......边("+placeone.getPlace_no()+","+placetwo.getPlace_no()+"),权值="+time);
					//增加位置点之间的边（双向）
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
