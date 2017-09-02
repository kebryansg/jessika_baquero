/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.servlet;

import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.controlador.entidades.sm.Especialidad;
import mvc.controlador.entidades.sm.Establecimiento;
import mvc.modelo.smDao.EstablecimientoDao;
import mvc.modelo.smDaoImp.EstablecimientoDaoImp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Deivi
 */
public class sEstablecimiento extends HttpServlet {

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
            out.println("<title>Servlet sEstablecimiento</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sEstablecimiento at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
         String op = request.getParameter("op");
        upload(request,response,"",out);
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
         EstablecimientoDao establecimiento= new EstablecimientoDaoImp(); 
          String opcion = request.getParameter("opcion");
         if("1".equals(opcion))
          {
             List<Establecimiento> list= establecimiento.list();             
             ObjectMapper OBJECT_MAPPER = new ObjectMapper();
             String json = OBJECT_MAPPER.writeValueAsString(list);
             response.getWriter().write(json);
          }
         else if("subirLogo".equals(opcion))
             out.println(upload(request,response,opcion,out));
         else if("eliminar".equals(opcion))
             out.println(upload(request,response,opcion,out));             
         else             
             out.println(upload(request,response,"subir",out));
        
       
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
    public JsonObject upload(HttpServletRequest request,HttpServletResponse response, String op,PrintWriter out)
    {
        JsonObject object = new JsonObject();
        try
        {
            EstablecimientoDao establecimiento= new EstablecimientoDaoImp();
            
            if("eliminar".equals(op))
            {
                
                object.addProperty("estado", establecimiento.updateLogo("sinLogo"));
                object.addProperty("excepcion",establecimiento.getExcepcion());
                ObjectMapper OBJECT_MAPPER = new ObjectMapper();
                String json = OBJECT_MAPPER.writeValueAsString("1");
                response.getWriter().write(json);
                return object;
            }
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            // req es la HttpServletRequest que recibimos del formulario.
            // Los items obtenidos serán cada uno de los campos del formulario,
            // tanto campos normales como ficheros subidos.
            List items = upload.parseRequest(request);
            // Se recorren todos los items, que son de tipo FileItem
            String[] datos= new String[8];
            datos[7]="no";
            int i=0;
            for (Object item : items) 
            {
                FileItem uploaded = (FileItem) item;
                // Hay que comprobar si es un campo de formulario. Si no lo es, se guarda el fichero
                // subido donde nos interese
                if (!uploaded.isFormField()) {
                    // No es campo de formulario, guardamos el fichero en algún sitio                    
                    //String absoluteFilesystemPath = getServletContext().getRealPath("/configuracion/img/");                    
                    String absoluteFilesystemPath = getServletContext().getRealPath("/configuracion/img/");
                    File fichero = new File(absoluteFilesystemPath, uploaded.getName());
                    uploaded.write(fichero);                           
                    datos[7]=uploaded.getName();
                    ObjectMapper OBJECT_MAPPER = new ObjectMapper();
                    String json = OBJECT_MAPPER.writeValueAsString(uploaded.getName());
                    response.getWriter().write(json);
                    
                } else {
                    // es un campo de formulario, podemos obtener clave y valor
                    String key = uploaded.getFieldName();
                    String valor = uploaded.getString();
                    if("sinLogo".equals(valor))
                    {
                        datos[7]=valor;
                        ObjectMapper OBJECT_MAPPER = new ObjectMapper();
                        String json = OBJECT_MAPPER.writeValueAsString("sinLogo");
                        response.getWriter().write(json);
                    }
                    else
                    {
                        datos[i]=valor;
                        i++;                    
                    }
                    
                }
                
            }
            if("subir".equals(op))
            {                         
                Establecimiento value= new Establecimiento(Integer.parseInt(datos[6]),datos[0],datos[1],Integer.parseInt(datos[2]),datos[3],datos[4],datos[5],datos[7]);            
                object.addProperty("estado", establecimiento.save(value));
                object.addProperty("excepcion",establecimiento.getExcepcion());
            }   
            else            
            {
                object.addProperty("estado",establecimiento.updateLogo(datos[7]));                
                object.addProperty("excepcion",establecimiento.getExcepcion());
            }
            return object;
        }
        catch(Exception ex)
        {
            return object;
        }
        
    }

}
