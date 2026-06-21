package ovy.framework.servlet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ovy.framework.mapping.ClassInfo;
import ovy.framework.mapping.PackageInfo;
import ovy.framework.test.Ovy;


public class FrontControllerServlet extends HttpServlet {
    private String json;
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String url = req.getRequestURI();
        String method = req.getMethod();
        res.setContentType("application/json;charset=UTF-8");
        res.getWriter().println("{" +
                "\"url\":\"" + escapeJson(url) + "\"," +
                "\"method\":\"" + escapeJson(method) + "\"" +
                "}");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String packageName = "ovy.framework.test";

        try {
            List<Class<?>> classes = ClassInfo.getClassesWithAnnotation(PackageInfo.getClassesInPackage(packageName), Ovy.class);

            List<String> classNames = new ArrayList<>();

            for (Class<?> clazz : classes) {
                classNames.add(clazz.getName());
            }

            this.json = convertirEnJson(classNames);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    private String convertirEnJson(List<String> liste) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < liste.size(); i++) {
            sb.append("  \"").append(liste.get(i)).append("\"");
            if (i < liste.size() - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");
        return sb.toString();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        processRequest(req, res);
    }
}
