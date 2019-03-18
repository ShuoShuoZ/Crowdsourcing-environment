package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnect;

/**
 * 边类
 * @author RollingZ
 *
 */

public class edge {
	public place placeone;//第一个点
	public place placetwo;//第二个点
	public int value;//权值
	
	public static PreparedStatement pst_edge=null;
	public static ResultSet rs_edge=null;
	
	public edge() {
	}
	
	public edge(place placeone, place placetwo, int value) {
		this.placeone = placeone;
		this.placetwo = placetwo;
		this.value = value;
	}
	
	/**
	 * 增加位置点之间的边
	 * @param edge_item   边对象
	 */
	public static void addEdge(edge edge_item) {
		String sql_edge;
		try {
			sql_edge="insert into graph(config_id,dataset_id,place_one,place_two,value) values(?,?,?,?,?)";
			pst_edge=DBConnect.conn.prepareStatement(sql_edge);
			pst_edge.setInt(1, edge_item.placeone.getDataset_item().getConfig_item().getConfig_id());
			pst_edge.setInt(2, edge_item.placeone.getDataset_item().getDataset_id());
			pst_edge.setInt(3, edge_item.placeone.getPlace_no());
			pst_edge.setInt(4, edge_item.placetwo.getPlace_no());
			pst_edge.setInt(5, edge_item.value);
			pst_edge.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public place getPlaceone() {
		return placeone;
	}
	public void setPlaceone(place placeone) {
		this.placeone = placeone;
	}
	public place getPlacetwo() {
		return placetwo;
	}
	public void setPlacetwo(place placetwo) {
		this.placetwo = placetwo;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	

}
