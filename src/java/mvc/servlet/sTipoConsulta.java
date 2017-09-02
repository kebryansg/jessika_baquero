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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.controlador.entidades.sm.DetallesMetodos;
import mvc.controlador.entidades.sm.Metodos;
import mvc.controlador.entidades.sm.TipoConsulta;
import mvc.modelo.smDaoImp.TipoConsultaDaoImp;

/**
 *
 * @author kebryan
 */
public class sTipoConsulta extends HttpServlet {

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
            out.println("<title>Servlet sTipoConsulta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sTipoConsulta at " + request.getContextPath() + "</h1>");
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

        final String FORMATO_FECHA = "yyyy-MM-dd";
        final DateFormat DF = new SimpleDateFormat(FORMATO_FECHA);
        Gson gson = new GsonBuilder().setDateFormat(FORMATO_FECHA).create();
        String result = "";
        String op = request.getParameter("op");
        switch (op) {
            case "list":
                List<TipoConsulta> list_tipo = new TipoConsultaDaoImp().list();
                for (TipoConsulta tipoConsulta : list_tipo) {
                    result += "<option value='" + tipoConsulta.getId() + "'>" + tipoConsulta.getDescripcion() + "</option>";
                }
                break;
            case "list_metodos":
                List<Metodos> list_metodos = new TipoConsultaDaoImp().list_Metodos(Integer.parseInt(request.getParameter("idTipoConsulta")));
                result += "<option value=\"0\">Seleccione</option>";
                for (Metodos metodo : list_metodos) {
                    result += "<optgroup label=\"" + metodo.getDescripcion() + "\">";
                    for (DetallesMetodos det_metodo : metodo.getList()) {
                        String sexo = "";
                        switch (det_metodo.getSexo()) {
                            case "1":
                                sexo = "sMasculino";
                                break;
                            case "2":
                                sexo = "sFemenino";
                                break;
                        }
                        result += "<option class='" + sexo + "' value='" + det_metodo.getId() + "' >" + det_metodo.getDescripcion() + "</option>";
                    }
                    result += "</optgroup>";
                }
                break;
        }
        out.print(result);
        out.flush();
        out.close();
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
