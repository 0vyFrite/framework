package ovy.framework.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class FrontControllerServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        String url = req.getRequestURI();
        String method = req.getMethod();
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().println("{" +
                "\"url\":\"" + escapeJson(url) + "\"," +
                "\"method\":\"" + escapeJson(method) + "\"" +
                "}");
    }

    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
    
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        processRequest(req,resp);
    }
}
