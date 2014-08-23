package webim.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import webim.DBUtil;
import webim.bean.Friends;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/servlet/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection conn=null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		try {
			String sAction = request.getParameter("sAction");
			if("friends".equals(sAction)){
				String uid = request.getParameter("uid");
				List list = new ArrayList();
				conn = DBUtil.getConnection();
				psmt = conn.prepareStatement("SELECT t.uid,t.type,i.* FROM t_friends t,t_userinfo i WHERE t.uid=i.id AND t.puid=?");
				psmt.setString(1, uid);
				rs = psmt.executeQuery();
				while(rs.next()){
					Map m = new HashMap();
					m.put("id",rs.getInt("uid"));
					m.put("type",rs.getInt("type"));
					m.put("UserAvatar",rs.getString("UserAvatar"));
					m.put("UserName",rs.getString("UserName"));
					list.add(m);
				}
				
				JSONArray json = JSONArray.fromObject(list);  
		        PrintWriter out = response.getWriter();
		        out.print(json);
			}else if("userinfo".equals(sAction)){
				String uid= request.getParameter("uid");
				List list = new ArrayList();
				conn = DBUtil.getConnection();
				psmt = conn.prepareStatement("SELECT * FROM t_userinfo t WHERE t.id=?");
				psmt.setString(1, uid);
				rs = psmt.executeQuery();
				while(rs.next()){
					Map m = new HashMap();
					m.put("id",rs.getInt(1));
					m.put("UserConfig","");
					m.put("UserAvatar",rs.getString("UserAvatar"));
					m.put("UserName",rs.getString("UserName"));
					m.put("isOnline",rs.getString("isOnline"));
					m.put("cname",rs.getString("cname"));
					list.add(m);
				}
				
				JSONArray json = JSONArray.fromObject(list);  
		        PrintWriter out = response.getWriter();
		        out.print(json);
			}else if("chats".equals(sAction)){
				String cid= request.getParameter("cid");
				String uid= request.getParameter("uid");
				List list = new ArrayList();
				conn = DBUtil.getConnection();
				psmt = conn.prepareStatement("SELECT"+
				  " t.id,"+ 
				  " u.id     pid,"+
				  " u2.id    uid,"+
				  " u.UserName     pname,"+
				  " u2.UserName    uname,"+ 
				  " u.UserName     pid,"+
				  " u2.UserName    uid,"+
				  " t.d,"+
				  " t.m"+
				  " FROM t_chats t,"+
				  " t_userinfo u,"+
				  " t_userinfo u2"+
				  " WHERE t.pid = u.id"+
				  " AND t.uid = u2.id"+
				  " AND ((t.pid = ? AND t.uid = ?) OR (t.pid = ? AND t.uid = ?))"+
				  " ORDER BY t.id");
				psmt.setString(1, cid);
				psmt.setString(2, uid);
				psmt.setString(3, uid);
				psmt.setString(4, cid);
				rs = psmt.executeQuery();
				while(rs.next()){
					Map m = new HashMap();
					m.put("id",rs.getInt("id"));
					m.put("pid",rs.getString("pid"));
					m.put("uid",rs.getString("uid"));
					m.put("pname",rs.getString("pname"));
					m.put("uname",rs.getString("uname"));
					m.put("D",rs.getString("d"));
					m.put("M",rs.getString("m"));
					list.add(m);
				}
				JSONArray json = JSONArray.fromObject(list);  
		        PrintWriter out = response.getWriter();
		        out.print(json);
	        }else if("sendMsg".equals(sAction)){
				String cid= request.getParameter("cid");
				String uid= request.getParameter("uid");
				String msgContent = request.getParameter("msgContent");
				msgContent=java.net.URLDecoder.decode(msgContent,"utf-8");
				conn = DBUtil.getConnection();
				psmt = conn.prepareStatement("INSERT INTO t_chats(pid,uid,d,m) VALUES(?,?,now(),?)");
				psmt.setString(1, cid);
				psmt.setString(2, uid);
				psmt.setString(3, msgContent);
				psmt.execute();
	        }else if("addFriend".equals(sAction)){
	        	String cid= request.getParameter("cid");
				String uid= request.getParameter("uid");
				conn = DBUtil.getConnection();
				psmt = conn.prepareStatement("UPDATE t_friends SET TYPE = 1 WHERE uid = ?");
				psmt.setString(1, uid);
				psmt.execute();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null)	conn.close();
				if(psmt!=null)	psmt.close();
				if(rs!=null)	rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}

}
