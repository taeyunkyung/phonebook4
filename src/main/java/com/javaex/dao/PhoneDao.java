package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhoneDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// @Autowired
	// private DataSource dataSource;

	// 필드
	// 0. import java.sql.*;

	// 메소드 일반
	/*private void getConnection() {
		try {
			// conn = dataSource.getConnection();

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
	}*/
	
	// private void close() {}

	// 리스트 가져오기
	public List<PersonVo> getPersonList() {
		System.out.println("PhoneDao.getPersonList()");
		
		List<PersonVo> personList = sqlSession.selectList("phonebook.selectList");
		System.out.println(personList);
		
		return personList;
	}

	// 한 사람의 데이터만 가져오기
	public PersonVo getPerson(int personId) {
		System.out.println("PhoneDao.getPerson()");
		
		PersonVo personVo = sqlSession.selectOne("phonebook.selectById", personId);
		return personVo;
		
		/*this.getConnection();

		try {
			String query = "";
			query += "select  name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			query += " where person_id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, personId);

			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				vo = new PersonVo(personId, name, hp, company);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();*/
	}
	
	// map 사용
	public PersonVo getPerson2(int index) {
		System.out.println("PhoneDao.getPerson2(): 맵으로 받기");
		Map<String, Object> personMap = sqlSession.selectOne("phonebook.selectById2", index);
		// System.out.println(personMap.keySet());
		
		System.out.println(personMap.get("PERSON_ID"));
		System.out.println(personMap.get("NAME"));
		System.out.println(personMap.get("HP"));
		System.out.println(personMap.get("COMPANY"));
		
		return null;
	}
	
	public int personInsert2(String name, String hp, String company) {
		System.out.println("PhoneDao.personInsert(): 파라미터 여러 개로 받을때 ");
		// String name = "경태윤";
		// String hp = "010-0000-0000";
		// String company = "02-0000-0000";		
		
		Map<String, String> personMap = new HashMap<String, String>();
		personMap.put("name", name);
		personMap.put("hp", hp);
		personMap.put("company", company);
		
		return sqlSession.insert("phonebook.insert2", personMap);
	}
	
	// 등록
	public int personInsert(PersonVo personVo) {
		System.out.println("PhoneDao.personInsert()");
		
		return sqlSession.insert("phonebook.insert", personVo);
		
		/*this.getConnection();

		try {
			String query = "";
			query += "insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?)";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			count = pstmt.executeUpdate();
			System.out.println("[" + count + "건 등록되었습니다.(PhoneDao)]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();*/		
	}

	// 삭제
	public int personDelete(int personId) {
		System.out.println("PhoneDao.personDelete()");
		
		return sqlSession.delete("phonebook.delete", personId);
		
		/*this.getConnection();

		try {
			String query = "";
			query += "delete from person ";
			query += " where person_id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, index);

			count = pstmt.executeUpdate();
			System.out.println("[" + count + "건 삭제되었습니다.(PhoneDao)]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();*/
	}
		
	// 수정
	public int personUpdate(PersonVo personVo) {
		System.out.println("PhoneDao.personUpdate()");

		return sqlSession.update("phonebook.update", personVo);
		
		/*this.getConnection();

		try {
			String query = "";
			query += "update person ";
			query += " set   name = ?, ";
			query += "       hp = ?, ";
			query += "       company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();
			System.out.println("[" + count + "건 수정되었습니다.(PhoneDao)]");

		} catch (SQLException e) {
			System.out.println("error." + e);
		}

		this.close();*/
	}

	// 검색
	/*public List<PersonVo> getPersonList(String keyword) {
		List<PersonVo> pList = new ArrayList<PersonVo>();
		this.getConnection();

		try {
			String query = "";
			query += "select  person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";

			if (keyword != "" || keyword == null) {
				query += " where (name||hp||company) like '%'||?||'%' ";
				query += " order by person_id asc";

				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, keyword);
			} else {
				query += " order by person_id asc";
				pstmt = conn.prepareStatement(query);
			}

			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo vo = new PersonVo(personId, name, hp, company);
				pList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		return pList;
	}*/	
}