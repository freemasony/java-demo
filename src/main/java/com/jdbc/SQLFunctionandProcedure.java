package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class SQLFunctionandProcedure {

	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	public static void jdbc(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://10.141.105.129:5432/jbmsdb?useUnicode=true&characterEncoding=UTF-8";
			String user = "jbmsuser";
			String password = "jb765321";
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void releaseJdbc(){
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static void function(Connection conn){
		try {
			String sql="{?=call testlengths(?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, "123456");
			cs.execute();
			String result = cs.getString(1);
			System.out.println("function result:"+result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void procedure(Connection conn){
		try {
			String sql="{call pro_add(?,?)}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(1, 10);
			cs.setInt(2, 20);
			rs=cs.executeQuery();
			while(rs.next()){
				int result=rs.getInt(1);
				System.out.println(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 *  delimiter $
		drop procedure if exists mycursor $
		create procedure mycursor(out pout varchar(255))
		begin
		declare tmpname varchar(20) default '';
		declare allname varchar(255) default '';
		declare cur_name cursor for select lecturer_username from cfs.cfs_push_message group by lecturer_username;	--�����α����
		declare continue handler for sqlstate '02000' set tmpname=null;	--�����������tmpname==nullʱ����ѭ��
		open cur_name;	--���α�
		fetch cur_name into tmpname;	--�α�������һ��
		while(tmpname is not null) do
		set tmpname=concat(tmpname,';');
		set allname=concat(allname,tmpname);
		fetch cur_name into tmpname;	--�ٴ�������һ��
		end while;
		close cur_name;	--�ر��α�
		set pout= allname;
		end $
	 */
	private static void cursor(Connection conn){
		try {
			String sql="{call mycursor(?)}";
			CallableStatement	cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, Types.VARCHAR);
			rs=cs.executeQuery();
			System.out.println(cs.getString(1)); //�˴����в�ͬ ȡ����ֵ
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdbc();
		function(conn);
		procedure(conn);
		cursor(conn);
		releaseJdbc();
	}

}
