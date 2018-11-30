package main.java.controller.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.model.bean.User;

import main.java.model.util.Page;

@WebServlet("/userServlet")
public class UserServlet extends BaseBackServlet {

    @Override
    public String add(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String delete(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String update(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String list(HttpServletRequest request, HttpServletResponse response, Page page) throws Exception {
        List<User> userList = null;
        try {
            userList = userDAO.list(page.getStart(), page.getCount());
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int total = userDAO.getTotal();
        page.setTotal(total);
        request.setAttribute("userList", userList);
        request.setAttribute("page", page);
        return "admin/listUser.jsp";
    }

}
