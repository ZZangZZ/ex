package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.DBConnection;
import model.Member;

public class DaoImpl implements MemberDao {
	public Member selectOne(String id) throws Exception {
			Connection conn = DBConnection.getConnection(); //List<Member> templet(){} 
			Statement stmt = null;
			ResultSet rs = null;
			Member member = null;
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT ID, PASSWD, NAME FROM MEMBER WHERE ID ='" 
				     + id + "'");
				if (rs.next()) {
					member = new Member()
							.setId(rs.getString("ID"))
							.setPw(rs.getString("PASSWD"))
							.setName(rs.getString("NAME"));
				}
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (stmt != null)
						rs.close();
					if (conn != null)
						rs.close();
				} catch (Exception e) {
				}
			}
			return member;
		}

	public Member exist(String id, String password) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			pstmt = conn.prepareStatement("SELECT ID, PASSWD, NAME FROM MEMBER WHERE ID =? AND PASSWD=?");
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new Member().setId(rs.getString(1))
						             .setPw(rs.getString(2))
						             .setName(rs.getString(3));

			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					rs.close();
				if (conn != null)
					rs.close();
			} catch (Exception e) {
			}
		}
		return member;
	}

	public int insert(Member Member) throws Exception {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("INSERT INTO MEMBER(ID, PASSWD, NAME, EMAIL, PHONE)" + "VALUES(?,?,?,?,?)");
			pstmt.setString(1, Member.getId());
			pstmt.setString(2, Member.getPw());
			pstmt.setString(3, Member.getName());
			pstmt.setString(4, Member.getEmail());
			pstmt.setString(5, Member.getPhone());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}

		}
	} // int

	public Member update(Member Member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("UPDATE MEMBER SET PASSWD=?, NAME=? WHERE ID=?");
			pstmt.setString(1, Member.getPw());
			pstmt.setString(2, Member.getName());
			pstmt.setString(3, Member.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();// throw e;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return Member; // throw e ; 이면 생략
	}

	public int delete(Member Member) throws Exception {
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			return stmt.executeUpdate("DELETE FROM MEMBER WHERE ID='" + Member.getId() + "'");
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}

	public List<Member> selectList() throws Exception {
		Connection conn = DBConnection.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Member> Members = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT ID, PASSWD, NAME FROM " + " MEMBER ORDER BY NAME ASC");
			Members = new ArrayList<Member>();
			while (rs.next()) {
				Members.add(new Member().setId(rs.getString("ID")).setPw(rs.getString("PASSWD"))
						.setName(rs.getString("NAME")));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
			return Members;
		}
	}
}


