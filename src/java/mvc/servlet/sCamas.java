/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.servlet;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.controlador.entidades.sm.Camas;
import mvc.modelo.smDao.CamasDao;
import mvc.modelo.smDaoImp.CamasDaoImp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
/**
 *
 * @author Deivi
 */
public class sCamas extends HttpServlet {

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
            out.println("<title>Servlet sCamas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sCamas at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/plain");
         PrintWriter out = response.getWriter();
         String opcion = request.getParameter("opcion");
         CamasDao camas= new CamasDaoImp();   
         if("1".equals(opcion))
         {
             List<Camas> list= camas.list();             
             ObjectMapper OBJECT_MAPPER = new ObjectMapper();
             String json1 = OBJECT_MAPPER.writeValueAsString(list);
             response.getWriter().write(json1);  
         } 
         //save or update
         else if("2".equals(opcion))         
         {
             int id =  Integer.parseInt(request.getParameter("s"));
             int medicinaInternaIndividual =  Integer.parseInt(request.getParameter("medicinaInternaIndividual"));
             int medicinaInternaDoble =  Integer.parseInt(request.getParameter("medicinaInternaDoble"));
             int cirugiaIndividual =  Integer.parseInt(request.getParameter("cirugiaIndividual"));
             int cirugiaDoble =  Integer.parseInt(request.getParameter("cirugiaDoble"));
             int ginecologiaIndividual =  Integer.parseInt(request.getParameter("ginecologiaIndividual"));
             int ginecologiaDoble =  Integer.parseInt(request.getParameter("ginecologiaDoble"));
             int pediatriaIndividual =  Integer.parseInt(request.getParameter("pediatriaIndividual"));
             int pediatriaDoble =  Integer.parseInt(request.getParameter("pediatriaDoble"));
             int cardiologiaIndividual =  Integer.parseInt(request.getParameter("cardiologiaIndividual"));
             int cardiologiaDoble =  Integer.parseInt(request.getParameter("cardiologiaDoble"));
             int neumologiaIndividual =  Integer.parseInt(request.getParameter("neumologiaIndividual"));
             int neumologiaDoble =  Integer.parseInt(request.getParameter("neumologiaDoble"));
             int psiquiatriaIndividual =  Integer.parseInt(request.getParameter("psiquiatriaIndividual"));
             int psiquiatriaDoble =  Integer.parseInt(request.getParameter("psiquiatriaDoble"));
             int traumatologiaIndividual =  Integer.parseInt(request.getParameter("traumatologiaIndividual"));
             int traumatologiaDoble =  Integer.parseInt(request.getParameter("traumatologiaDoble"));
             int infectologiaIndividual =  Integer.parseInt(request.getParameter("infectologiaIndividual"));
             int infectologiaDoble =  Integer.parseInt(request.getParameter("infectologiaDoble"));
             int oftalmologiaIndividual =  Integer.parseInt(request.getParameter("oftalmologiaIndividual"));
             int oftalmologiaDoble =  Integer.parseInt(request.getParameter("oftalmologiaDoble"));
             int urologiaIndividual =  Integer.parseInt(request.getParameter("urologiaIndividual"));
             int urologiaDoble =  Integer.parseInt(request.getParameter("urologiaDoble"));
             int gastroenterologiaIndividual =  Integer.parseInt(request.getParameter("gastroenterologiaIndividual"));
             int gastroenterologiaDoble =  Integer.parseInt(request.getParameter("gastroenterologiaDoble"));
             int emergencia =  Integer.parseInt(request.getParameter("emergencia"));
             int cuidadosIntensivos =  Integer.parseInt(request.getParameter("cuidadosIntensivos"));
             Camas cama=new Camas(id, medicinaInternaIndividual, medicinaInternaDoble, cirugiaIndividual, cirugiaDoble, ginecologiaIndividual, ginecologiaDoble, pediatriaIndividual, pediatriaDoble, cardiologiaIndividual, cardiologiaDoble, neumologiaIndividual, neumologiaDoble, psiquiatriaIndividual, psiquiatriaDoble, traumatologiaIndividual, traumatologiaDoble, infectologiaIndividual, infectologiaDoble, oftalmologiaIndividual, oftalmologiaDoble, urologiaIndividual, urologiaDoble, gastroenterologiaIndividual, gastroenterologiaDoble, emergencia, cuidadosIntensivos);
              JsonObject object = new JsonObject();
              object.addProperty("estado",camas.save(cama));
              object.addProperty("excepcion",camas.getExcepcion());
              out.println(object);
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
