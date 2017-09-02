package mvc.servlet;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.controlador.entidades.sm.Especialidad;
import mvc.modelo.smDao.EspecialidadDao;
import mvc.modelo.smDaoImp.EspecialidadDaoImp;
import org.codehaus.jackson.map.ObjectMapper;
import java.util.List;
import test.list_count;

/**
 *
 * @author Byron
 */
public class sEspecialidad extends HttpServlet {

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
        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        String opcion = request.getParameter("opcion");
        String buscar;
        // KS: Variable q uso
        list_count l;
        List<String> resultList = new ArrayList<>();
        // KS: Variable q uso
        JsonObject object = new JsonObject();
        Especialidad especialidad = new Especialidad();

        switch (opcion) {
            case "1":
                Integer bandera = Integer.valueOf(request.getParameter("bandera"));
                buscar = request.getParameter("buscar");
                //EspecialidadDao espe = new EspecialidadDaoImp();
                out.println(new EspecialidadDaoImp().totalRegistros(bandera, buscar));
                break;
            case "2":
                buscar = request.getParameter("buscar");
                //EspecialidadDao espe = new EspecialidadDaoImp();
                Integer totalMostrar = Integer.valueOf(request.getParameter("totalMostrar"));
                Integer pagina = Integer.valueOf(request.getParameter("pagina"));
                l = new EspecialidadDaoImp().list(pagina, totalMostrar, buscar);
                for (Object object1 : l.getList()) {
                    especialidad = (Especialidad) object1;
                    resultList.add("{"
                            + "\"id\": \"" + especialidad.getId() + "\","
                            + "\"descripcion\": \"" + especialidad.getDescripcion() + "\","
                            + "\"accion\": \"<button name='editEspecialidad' class='btn btn-info'><span class='glyphicon glyphicon-pencil'></span></button> <button name='deleteEspecialidad' class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span></button>\""
                            + "}");
                }

                out.print("{\"count\":\"" + Math.ceil((float) l.getTotal() / totalMostrar) + "\", \"list\":[" + String.join(",", resultList) + "] }");
                out.flush();
                out.close();
                break;
            case "delete":
                int idEspecialidad = Integer.parseInt(request.getParameter("id"));
                new EspecialidadDaoImp().delete(idEspecialidad);
                out.println("ok");
                out.flush();
                out.close();
                break;
            case "list":
                List<Especialidad> list = new EspecialidadDaoImp().list();
                String result = "";
                for (Especialidad especialidad_1 : list) {
                    result += "<option value='" + especialidad_1.getId() + "'>" + especialidad_1.getDescripcion() + "</option>";
                }
                out.print(result);
                break;
            case "save": 
                especialidad.setDescripcion(request.getParameter("descripcion"));
                especialidad.setId(Integer.parseInt(request.getParameter("id")));
                new EspecialidadDaoImp().save(especialidad);
                result = "{\"status\" : true}";
                out.print(result);
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
