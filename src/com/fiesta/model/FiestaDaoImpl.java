package com.fiesta.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.DataSource;

import com.fiesta.model.vo.Answer;
import com.fiesta.model.vo.Comcategory;
import com.fiesta.model.vo.Company;
import com.fiesta.model.vo.Customer;
import com.fiesta.model.vo.Custorder;
import com.fiesta.model.vo.Orderdetail;
import com.fiesta.model.vo.Question;
import com.fiesta.model.vo.Review;
import com.fiesta.model.vo.Service;
import com.fiesta.model.vo.Wish;
import com.fiesta.util.ServerInfo;

public class FiestaDaoImpl {
	private DataSource ds;
	
	private static FiestaDaoImpl dao = new FiestaDaoImpl();
	private FiestaDaoImpl() {
		//ds = DataSourceManager.getInstance().getConnection();

		//단위테스트
		try {
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			System.out.println("ItemDao :: " + e);
		}
	}
	public static FiestaDaoImpl getInstance() {
		return dao;
	}
	
	public Connection getConnection() throws SQLException {
		//return ds.getConnection();

		//단위테스트
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASS);
	}
	public void closeAll(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();
	}
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if(rs!=null) rs.close();
		closeAll(ps, conn);
	}
	
	
	//작업 영역
	public void registerCustomer(Customer customer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "INSERT INTO customer (cust_id, cust_name, cust_pass, cust_tel, cust_email, cust_group) VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in registerCustomer");
			
			ps.setString(1, customer.getCustId());
			ps.setString(2, customer.getCustName());
			ps.setString(3, customer.getCustPass());
			ps.setString(4, customer.getCustTel());
			ps.setString(5, customer.getCustEmail());
			ps.setString(6, customer.getCustGroup());
			System.out.println(ps.executeUpdate()+" row register success");
			
		} finally {
			closeAll(ps, conn);
		}
	}

	public Customer loginCustomer(String id, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			conn = getConnection();
			String query = "SELECT * FROM customer WHERE cust_id=? AND cust_pass=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in loginCustomer");

			ps.setString(1, id);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer(id,
										pass,
										rs.getString("cust_name"),
										rs.getString("cust_tel"),
										rs.getString("cust_email"),
										rs.getString("cust_group"));
				System.out.println(id+ " login success");
				}
		} finally {
			closeAll(rs, ps, conn);
		}
		return customer; 
	}

	public void updateCustomer(Customer customer) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "UPDATE customer SET cust_pass=?, cust_name=?, cust_tel=? cust_email=? cust_group=? WHERE cust_id=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in updateCustomer");
			
			ps.setString(1, customer.getCustPass()); 
			ps.setString(2, customer.getCustName());
			ps.setString(3, customer.getCustTel());
			ps.setString(4, customer.getCustEmail());
			ps.setString(5, customer.getCustGroup());
			ps.setString(6, customer.getCustId());
			System.out.println(ps.executeUpdate()+" row update success");
		} finally {
			closeAll(ps, conn);
		}
	}

	public void deleteCustomer(String id, String pass) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			String query = "DELETE FROM customer WHERE cust_id=? AND cust_pass=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in deleteCustomer");
			
			ps.setString(1, id);
			ps.setString(2, pass);
			System.out.println(ps.executeUpdate()+" row delete success");
		} finally {
			closeAll(ps, conn);
		}
	}

	public Customer lookupCustomer(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			conn = getConnection();
			String query = "SELECT * FROM customer WHERE cust_id=?";
			ps = conn.prepareStatement(query);
			System.out.println("ps completed in lookupCustomer");
			
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				customer = new Customer(id,
							rs.getString("cust_name"),
							rs.getString("cust_pass"),
							rs.getString("cust_tel"),
							rs.getString("cust_email"),
							rs.getString("cust_group"));
				System.out.println(id+ " lookup success");
			}
		} finally {
			closeAll(rs, ps, conn);
		}
		return customer;
	}

	public void insertCustorder(Custorder custorder) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void insertOrderdetail(Orderdetail orderdetail) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Custorder> showAllCustorder(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Orderdetail> showAllOrderdetail(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void registerCompany(Company company) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Company loginCompany(String id, String pass) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCompany(Company company) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void deleteCompany(String id, String pass) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public Review lookupCompany(String searchBy, String searchContent) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		Review review = new Review();
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_name, c.com_desc, c.com_img, r.review_score, r.review_desc ");
			query.append("FROM company c LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("WHERE c.comCategory_code = ? ");
			query.append("ORDER BY c.com_code DESC ");
			
			
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return review;
	}

	public ArrayList<Review> showAllCompany() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();

		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_name, c.com_desc, c.com_img, r.review_score, r.review_desc ");
			query.append("FROM company c LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("ORDER BY c.com_code DESC");
			ps=conn.prepareStatement(query.toString());
			//System.out.println(query.toString());
			rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new Review(rs.getInt("r.review_score"),
						rs.getString("r.review_desc"),
						new Company(rs.getString("c.com_name"),
								rs.getString("c.com_img"),
								rs.getString("c.com_desc"))));
			}
		}finally {
			closeAll(rs, ps, conn);
		}

		return list;
	}

	public ArrayList<Review> showAllCompanyByCategory(int category) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Review> list = new ArrayList<Review>();
		
		try {
			conn=getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT c.com_name, c.com_desc, c.com_img, r.review_score, r.review_desc ");
			query.append("FROM company c LEFT OUTER JOIN review r ");
			query.append("ON c.com_code = r.com_code ");
			query.append("WHERE c.comCategory_code = ? ");
			query.append("ORDER BY c.com_code DESC ");
			
			ps=conn.prepareStatement(query.toString());
			ps.setInt(1, category);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				list.add(new Review(rs.getInt("r.review_score"),
						rs.getString("r.review_desc"),
						new Company(rs.getString("c.com_name"),
								rs.getString("c.com_img"),
								rs.getString("c.com_desc"))));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		
		return list;
	}

	public void insertService(Service service) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn=  getConnection();
			String query = "INSERT INTO service(service_name, service_desc, service_img, service_tag, com_code) VALUES(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertService");
			
			ps.setString(1, service.getServiceName());
			ps.setString(2, service.getServiceDesc());
			ps.setString(3, service.getServiceImg());
			ps.setString(4, service.getServiceTag());
			ps.setInt(5, service.getComCode());
			
			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	public void deleteService(int code) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn=  getConnection();
			String query = "DELETE FROM service WHERE service_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertService");
			
			ps.setInt(1, code);
			
			System.out.println(ps.executeUpdate()+" row delete OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	public ArrayList<Service> showAllService(int companycode) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Service> list = new ArrayList<>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM service WHERE com_code=?";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement....showAllService..");
					
			ps.setInt(1, companycode);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Service(rs.getString("service_name"), 
									  rs.getString("service_desc"), 
									  rs.getString("service_img"), 
									  rs.getString("service_tag")));
			}
		}finally {
			closeAll(rs, ps, conn);
		}
		return list;
	}

	public void insertQuestion(String desc, String custId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		//현재시간 출력
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String currTime = format.format(time.getTime());
		//System.out.println(currTime); 확인용
		
		try{
			conn=  getConnection();
			String query = "INSERT INTO question(q_date, q_desc, cust_id) VALUES(?,?,?)";
			ps = conn.prepareStatement(query);
			System.out.println("PreparedStatement 생성됨...insertService");
			
			ps.setString(1, currTime);
			ps.setString(2, desc);
			ps.setString(3, custId);	//세션의 고객아이디 값 가져오기
			
			System.out.println(ps.executeUpdate()+" row INSERT OK!!");
		}finally{
			closeAll(ps, conn);
		}
		
	}

	public ArrayList<Question> showAllQuestion(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertAnswer(Answer answer) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Answer> showAllAnswer(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertReview(Review review) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Review> showAllReview(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertWish(Wish wish) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Wish> showAllWish(String id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteWish(Wish wish) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	//단위테스트
	public static void main(String[] args) throws SQLException {
		//하경 - 단위테스트
		FiestaDaoImpl dao=FiestaDaoImpl.getInstance();
		//dao.insertService(new Service("플레이댄스팀","공연계일인자","img","#공연",1));		
		//엄체코드를 자동으로 session에서 받고 돌려야할것...  단위테스트 업체코드에 default값 없어서 못함... ㅠㅅㅠ
		
		//dao.deleteService(3);
		//System.out.println(dao.showAllService(1));
		
		dao.insertQuestion("몇명이서 잘 수 있나요?", "java");
		
		
		//제영 - 단위테스트
		//System.out.println(dao.showAllCompany());
		
		
		//의근 - 단위테스트
		// register
		// customer = new Customer("park", "박의근", "euigeun", "01088048331", "euigeun@gmail.com", "플레이데이터");
		// FiestaDaoImpl.getInstance().registerCustomer(customer);
		// FiestaDaoImpl.getInstance().registerCustomer(new Customer("lee", "이보람", "0107171293", "boram@gmail.com", "concat", "플레이데이터"));
		
		// login
		// FiestaDaoImpl.getInstance().loginCustomer("park", "euigeun");
		
		
		// update
		// Customer customer = new Customer("park", "이보람", "euigeun", "01088048331", "euigeun@gmail.com", "플레이데이터");
		// FiestaDaoImpl.getInstance().updateCustomer(new Customer("park", "이보람", "euigeun", "01088048331", "euigeun@gmail.com", "플레이데이터"));

		// delete
		// FiestaDaoImpl.getInstance().deleteCustomer("park", "euigeun");
		
		// lookup
		// FiestaDaoImpl.getInstance().lookupCustomer("park");
	}
}
