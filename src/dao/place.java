package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBConnect;


/**
 * λ�õ���
 * @author RollingZ
 *
 */

public class place {
	public dataset dataset_item;//�������Լ�
	public int place_no;//λ�õ���
	public List<edge> edges=new ArrayList<edge>();//���б�
	
	static PreparedStatement pst_place=null;
	static ResultSet rs_place=null;
	
	public place(dataset dataset_item,int place_no) {
		this.dataset_item = dataset_item;
		this.place_no = place_no;
	}
	
	/**
	 * ����һ�����ݼ��е�����λ�õ����
	 * @param dataset_item  ���ݼ�����
	 */
	public static void addPlaces(dataset dataset_item) {
		//��ȡλ�õ�����placenum
		int placenum=dataset_item.getConfig_item().getPlace_num();
		
		//����placenum��λ�õ�
		System.out.println("...����λ�õ�,����="+placenum);
		for(int i=0;i<placenum;i++) {
			//����λ�õ����
			place place_item=new place(dataset_item,i);
			//����һ��λ�õ����
			System.out.print("......λ�õ�"+i+": ");
			addPlace(place_item);
		}
	}
	/**
	 * ����һ��λ�ö���
	 * @param place_item λ�õ����
	 */
	public static void addPlace(place place_item) {
		String sql_place;
		//��ȡ���ݼ�������
		int dataset_id= place_item.dataset_item.getDataset_id();
		//��ȡ���ö����ʶ
		int config_id= place_item.dataset_item.getConfig_item().getConfig_id();
		//����һ��λ�õ�������ݿ��place��
		try {
			sql_place="insert into place(config_id,dataset_id,place_no) values(?,?,?)";
			pst_place=DBConnect.conn.prepareStatement(sql_place);
			pst_place.setInt(1, config_id);
			pst_place.setInt(2, dataset_id);
			pst_place.setInt(3, place_item.place_no);
			pst_place.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ���б�
	 * @param dataset_item
	 * @return place_items
	 */
	
	public static List<place> getPlaces(dataset dataset_item){
		List<place> place_items=new ArrayList<place>();
		String sql_placetwo;
		try {
			sql_placetwo="select place_no from place where config_id='"+dataset_item.getConfig_item().getConfig_id()
					                               +"' and dataset_id='"+dataset_item.getDataset_id()+"' order by place_no";
			//System.out.println(sql_item);
			pst_place=DBConnect.conn.prepareStatement(sql_placetwo);
			rs_place=pst_place.executeQuery();
			while(rs_place.next()) {
				place place_item=new place(dataset_item,rs_place.getInt(1));
				place_item.getEdgesfromDB();
				place_items.add(place_item);
				
			}			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return place_items;
	}
	/**
	 * ��ȡ���б�
	 * @return
	 */
	
	public List<edge> getEdgesfromDB(){
		PreparedStatement pst_edgeitem=null;
		ResultSet rs_edgeitem=null;
		String sql_edgeitem;
		try {
			sql_edgeitem="select place_two,value from graph where config_id='"+dataset_item.getConfig_item().getConfig_id()
					                               +"' and dataset_id='"+dataset_item.getDataset_id()
					                               +"' and place_one='"+place_no+"'";
			pst_edgeitem=DBConnect.conn.prepareStatement(sql_edgeitem);
			rs_edgeitem=pst_edgeitem.executeQuery();
			while(rs_edgeitem.next()) {
				place placetwo=new place(dataset_item,rs_edgeitem.getInt(1));
				edges.add(new edge(this,placetwo,rs_edgeitem.getInt(2)));
			}			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return edges;
	}
	

	public dataset getDataset_item() {
		return dataset_item;
	}

	public void setDataset_item(dataset dataset_item) {
		this.dataset_item = dataset_item;
	}

	public int getPlace_no() {
		return place_no;
	}

	public void setPlace_no(int place_no) {
		this.place_no = place_no;
	}

	public List<edge> getEdges() {
		return edges;
	}

	public void setEdges(List<edge> edges) {
		this.edges = edges;
	}
	
}
