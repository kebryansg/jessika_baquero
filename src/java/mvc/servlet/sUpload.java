/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.servlet;
import mvc.controlador.entidades.sm.Establecimiento;
import mvc.modelo.smDao.EstablecimientoDao;
import mvc.modelo.smDaoImp.EstablecimientoDaoImp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.*;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Deivi
 */
public class sUpload extends HttpServlet {

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
            out.println("<title>Servlet sUpload</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sUpload at " + request.getContextPath() + "</h1>");
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
        try
        //processRequest(request, response);
        {
            EstablecimientoDao establecimiento= new EstablecimientoDaoImp();
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // req es la HttpServletRequest que recibimos del formulario.
            // Los items obtenidos serán cada uno de los campos del formulario,
            // tanto campos normales como ficheros subidos.
            List items = upload.parseRequest(request);
            // Se recorren todos los items, que son de tipo FileItem
            String[] datos= new String[9];
            int i=0;
            for (Object item : items) 
            {
                FileItem uploaded = (FileItem) item;
                // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero
                // subido donde nos interese
                if (!uploaded.isFormField()) {
                    // No es campo de formulario, guardamos el fichero en algún sitio
                    String absoluteFilesystemPath = getServletContext().getRealPath("/configuracion/img/");
                    
                    File fichero = new File(absoluteFilesystemPath, uploaded.getName());
                    uploaded.write(fichero);
                    datos[8]=uploaded.getName();
                    ObjectMapper OBJECT_MAPPER = new ObjectMapper();
                    String json = OBJECT_MAPPER.writeValueAsString("1");
                    response.getWriter().write(json);
                    
                } else {
                    // es un campo de formulario, podemos obtener clave y valor
                    String key = uploaded.getFieldName();
                    String valor = uploaded.getString();
                    datos[i]=valor;
                    i++;
                    
                }
                
            }
            //Establecimiento value= new Establecimiento(datos[0],datos[1],datos[2],datos[3],datos[4],datos[5],datos[6],datos[7],datos[8]);
            //value.setId(0);
            //establecimiento.save(value);
        }
        catch(Exception ex)
    {
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
