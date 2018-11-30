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
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        String name = request.getParameter("name");
        Segment c = new Segment();
        c.setName(name);
        try {
            segmentDAO.add(c);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "@admin_segment_list";
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            segmentDAO.delete(id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "@admin_segment_list";
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Segment s = (Segment) segmentDAO.get(id);

        request.setAttribute("s", s);
        return "admin/editSegment.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Segment c = new Segment();
        c.setId(id);
        c.setName(name);
        try {
            segmentDAO.update(c);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "@admin_segment_list";
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        List<Segment> cs = segmentDAO.list(page.getStart(), page.getCount());
        int total = segmentDAO.getTotal();
        page.setTotal(total);

        request.setAttribute("thecs", cs);
        request.setAttribute("page", page);

        return "admin/listSegment.jsp";
    }

}
