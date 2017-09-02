/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.servlet;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mvc.modelo.smDao.ExcelDao;
import mvc.modelo.smDaoImp.ExcelDaoImp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Deivi
 */
public class sExcel extends HttpServlet {

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
            out.println("<title>Servlet sExcel</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sExcel at " + request.getContextPath() + "</h1>");
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
        JsonObject object = new JsonObject();
         JsonObject object1 = new JsonObject();
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String result = "", op = request.getParameter("op");
         ObjectMapper OBJECT_MAPPER = new ObjectMapper();
         try
         {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");                                
             Date fechaReporte = sdf.parse(request.getParameter("fechaReporte"));                    
             ExcelDao objExcel= new ExcelDaoImp();
             //String absoluteFilesystemPath = getServletContext().getRealPath("/xlsx/");   
             String absoluteFilesystemPath = "D:/gen_excel/"; 
             //System.out.println(getServletContext().getRealPath("/xlsx/"));
             String resultado=objExcel.generarExcelIngresos(fechaReporte,absoluteFilesystemPath,"");
             object.addProperty("egresos",resultado);
             if("".equals(resultado))
             {
                 object1.addProperty("estado",false);
                 object1.addProperty("formulario","Egresos");
                 object1.addProperty("excepcion",objExcel.getExcepcion());
             }
         }
         catch(Exception ex)
         {
         }
         try
         {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");                                
             Date fechaReporte = sdf.parse(request.getParameter("fechaReporte"));                    
             ExcelDao objExcel= new ExcelDaoImp();
             //String absoluteFilesystemPath = getServletContext().getRealPath("/xlsx/"); 
             String absoluteFilesystemPath = "D:/gen_excel/"; 
             String resultado=objExcel.generarExcelCamas(fechaReporte,absoluteFilesystemPath);
             object.addProperty("camas", resultado);
             if("".equals(resultado))
             {
                 object1.addProperty("estado",false);
                 object1.addProperty("formulario","Camas");
                 object1.addProperty("excepcion",objExcel.getExcepcion());
             }
             resultado=objExcel.generarExcelCamasIndividual(fechaReporte,absoluteFilesystemPath);
             object.addProperty("camasIndividual", resultado);
             if("".equals(resultado))
             {
                 object1.addProperty("estado",false);
                 object1.addProperty("formulario","Camas Individual");
                 object1.addProperty("excepcion",objExcel.getExcepcion());
             }             
         }
         catch(Exception ex)
         {
         }
         
         
         List<JsonObject> list = new ArrayList<JsonObject>();
         list.add(object);
         list.add(object1);
         out.println(list);
         //String json = OBJECT_MAPPER.writeValueAsString(object);
         //response.getWriter().write(json);
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
