package main.java.controller.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Segment;
import main.java.model.util.Page;

@WebServlet("/segmentServlet")
public class SegmentServlet extends BaseBackServlet {

	@Override
	public String add(HttpServletRequest request, HttpServletResponse response, Page page) {

		String name = request.getParameter("name");
		Segment c = new Segment();
		c.setName(name);
		segmentDAO.add(c);

		return "@admin_segment_list";
	}

	@Override
	public String delete(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		segmentDAO.delete(id);
		return "@admin_segment_list";
	}

	@Override
	public String edit(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		Segment s = segmentDAO.get(id);
		
		request.setAttribute("s", s);
		return "admin/editSegment.jsp";
	}

	@Override
	public String update(HttpServletRequest request, HttpServletResponse response, Page page) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Segment c = new Segment();
		c.setId(id);
		c.setName(name);
		segmentDAO.update(c);

		return "@admin_segment_list";
	}

	@Override
	public String list(HttpServletRequest request, HttpServletResponse response, Page page) {
		List<Segment> cs = segmentDAO.list(page.getStart(), page.getCount());
		int total = segmentDAO.getTotal();
		page.setTotal(total);

		request.setAttribute("thecs", cs);
		request.setAttribute("page", page);

		return "admin/listSegment.jsp";
	}

}
