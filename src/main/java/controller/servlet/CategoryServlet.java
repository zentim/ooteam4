package main.java.controller.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.Segment;
import main.java.model.bean.Category;

import main.java.model.util.Page;

@WebServlet("/categoryServlet")
public class CategoryServlet extends BaseBackServlet {

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int id = Integer.parseInt(request.getParameter("cid"));
        Segment segment = (Segment) segmentDAO.get(id);

        String name = request.getParameter("name");
        Category c = new Category();
        c.setName(name);
        c.setSegment(segment);
        categoryDAO.add(c);

        return "@admin_category_list?cid=" + id;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = (Category) categoryDAO.get(id);

        categoryDAO.delete(id);

        return "@admin_category_list?cid=" + category.getSegment().getId();
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Category c = (Category) categoryDAO.get(id);
        Segment s = (Segment) segmentDAO.get(c.getSegment().getId());

        request.setAttribute("c", c);
        request.setAttribute("s", s);
        return "admin/editCategory.jsp";
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int cid = Integer.parseInt(request.getParameter("cid"));
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Category c = new Category();
        c.setId(id);
        c.setName(name);
        categoryDAO.update(c);

        return "@admin_category_list?cid=" + cid;
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        int id = Integer.parseInt(request.getParameter("cid"));
        Segment segment = (Segment) segmentDAO.get(id);
        List<Category> cs = categoryDAO.list(id, page.getStart(), page.getCount());
        int total = categoryDAO.getTotal();
        page.setTotal(total);

        request.setAttribute("thecs", cs);
        request.setAttribute("page", page);
        request.setAttribute("c", segment);

        return "admin/listCategory.jsp";
    }

}
