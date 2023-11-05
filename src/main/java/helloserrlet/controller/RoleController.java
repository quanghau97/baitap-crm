package helloserrlet.controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helloservice.RoleService;
import helloservlet.entity.RoleEntity;

@WebServlet(name = "RoleController", urlPatterns = { "/role-add", "/role", "/role-delete", "/role-update" })
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = req.getServletPath(); // dùng để chỉ ra người dùng truy cập vào link nào

		System.out.println("Kiem tra " + path);
		if (path.equals("/role-add")) {
			doGetRoleAdd(req, resp);

		} else if (path.equals("/role")) {
			doGetRoleTable(req, resp);

		} else if (path.equals("/role-delete")) {
			doGetRoleDelete(req, resp);

		} else if (path.equals("/role-update")) {
			doGetEdit(req, resp);

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPostRoleAdd(req, resp);
	}

	// Chia làm từng hàm rồi sử lý code
	private void doGetRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}

	private void doPostRoleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("role-name");
		String description = req.getParameter("role-desc");

		name = setUTF8(name);
		description = setUTF8(description);

		System.out.println("Kiem tra: " + name + " - desc: " + description);

		boolean isSuccess = roleService.insert(name, description);
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);

	}

	private void doGetRoleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> list = roleService.getAllRole();
		req.setAttribute("listRole", list);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);

	}

	private void doGetRoleDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		roleService.deleteRole(id);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
	}

	private String setUTF8(String string) {
		byte[] bytes = string.getBytes(StandardCharsets.ISO_8859_1);
		string = new String(bytes, StandardCharsets.UTF_8);

		return string;
	}

	private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String name = req.getParameter("role-name");
	    String desc = req.getParameter("role-desc");
	    String id = req.getParameter("role-id");
		roleService.edit(name, desc, id);
		req.getRequestDispatcher("role-update.jsp").forward(req, resp);
	    }
//	private void doPostRoleEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String name = req.getParameter("name");
//		String desc = req.getParameter("desc");
//		int id = Integer.parseInt(req.getParameter("id"));
//		roleService.edit(name, desc, id);
//		RoleEntity role = new RoleEntity();
//		req.getSession().setAttribute("role", role);
//		req.getRequestDispatcher("role-update.jsp").forward(req, resp);
//
//	}
//	protected void doPostUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getServletPath();
//        switch (action) {
//            case "/role-update":
//                int id = Integer.parseInt(req.getParameter("id"));
//                String name = req.getParameter("name");
//                String description = req.getParameter("description");
//                RoleEntity role = new RoleEntity();
//                req.getSession().setAttribute("role", role);
//                req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
//                break;
//        }
//}
}
