/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.controlador.entidades.sm.DetalleEstudiosLabs;
import mvc.controlador.entidades.sm.EstudiosLaboratorio;
import mvc.modelo.smDaoImp.EstudiosLaboratorioDaoImp;
import test.list_count;

/**
 *
 * @author kebryan
 */
public class sEstudioLab extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sEstudioLab</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sEstudioLab at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String result = "", op = request.getParameter("op");
        List<String> resultList = new ArrayList<>();
        list_count l = new list_count();
        final String FORMATO_FECHA = "yyyy-MM-dd";
        final DateFormat DF = new SimpleDateFormat(FORMATO_FECHA);
        Gson gson = new GsonBuilder().setDateFormat(FORMATO_FECHA).create();
        int categoria = 0;
        switch (op) {
            case "list_cbo":
                List<EstudiosLaboratorio> list = new EstudiosLaboratorioDaoImp().list();
                result += "<option value='0'> Seleccione </option>";
                for (EstudiosLaboratorio estudiosLaboratorio : list) {
                    result += "<option value='" + estudiosLaboratorio.getId() + "'> " + estudiosLaboratorio.getDescripcion() + " </option>";
                }
                out.print(result);
                out.flush();
                out.close();
                break;
            case "detalle":
                categoria = Integer.parseInt(request.getParameter("categoria"));
                l = new EstudiosLaboratorioDaoImp().list_Det(categoria, request.getParameter("filter"), Integer.parseInt(request.getParameter("pag")), Integer.parseInt(request.getParameter("top")));

                for (Object object : l.getList()) {
                    DetalleEstudiosLabs detalleEstudiosLabs = (DetalleEstudiosLabs) object;
                    resultList.add("{ "
                            + "\"ID\" : \"" + detalleEstudiosLabs.getId() + "\","
                            + "\"categoria\" : \"" + detalleEstudiosLabs.getIdEstudiosLab().getDescripcion() + "\","
                            + "\"estudio\" : \"" + detalleEstudiosLabs.getDescripcion() + "\""
                            + "}");
                }
                result = "{\"count\": \"" + l.getTotal() + "\"  , \"list\": [" + String.join(",", resultList) + "]}";
                out.print(result);
                out.flush();
                out.close();
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
