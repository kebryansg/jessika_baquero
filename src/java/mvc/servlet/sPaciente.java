package mvc.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import mvc.controlador.entidades.ip.Enfermedad;
import mvc.controlador.entidades.ip.Obstetricos;
import mvc.controlador.entidades.ip.Paciente;
import mvc.controlador.entidades.ip.ParienteEnfermedadPaciente;
import mvc.controlador.entidades.ip.Parientes;
import mvc.controlador.entidades.ip.Parroquia;
import mvc.controlador.entidades.sm.HistorialClinico;
import mvc.modelo.ipDao.PacienteDao;
import mvc.modelo.ipDaoImp.ObstetricosDaoImp;
import mvc.modelo.ipDaoImp.PacienteDaoImp;
import mvc.modelo.ipDaoImp.ParienteEnfermedadPacienteDaoImp;
import mvc.modelo.ipDaoImp.ParroquiaDaoImp;
import mvc.modelo.smDaoImp.HistorialClinicoDaoImp;
import test.list_count;
import test.test;

/**
 *
 * @author kebryan
 */
public class sPaciente extends HttpServlet {

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
            out.println("<title>Servlet sPaciente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sPaciente at " + request.getContextPath() + "</h1>");
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
        List<Paciente> listP = null;
        list_count l = new list_count();
        Paciente paciente = new Paciente(0);
        PacienteDao pacienteDao = new PacienteDaoImp();
        final String FORMATO_FECHA = "yyyy-MM-dd";
        final DateFormat DF = new SimpleDateFormat(FORMATO_FECHA);
        Gson gson = new GsonBuilder().setDateFormat(FORMATO_FECHA).create();
        switch (op) {
            case "delete":
                new HistorialClinicoDaoImp().delete(Integer.parseInt(request.getParameter("id")));
                break;
            case "list":
                listP = new PacienteDaoImp().list_Filter(request.getParameter("filter"), 0, -1);
                out.print(listP.size());
                out.flush();
                out.close();
                break;
            case "list_filter":
                String filter = request.getParameter("filter");
                String op_filter = request.getParameter("op_filter");
                int topSQL = Integer.parseInt(request.getParameter("top"));
                int inicioSQL = Integer.parseInt(request.getParameter("pag"));
                String listString = "";

                l = new PacienteDaoImp().list_count_Filter(filter,op_filter, inicioSQL, topSQL);
                List<String> resultList = new ArrayList();

                for (Object object : l.getList()) {
                    Paciente paciente1 = (Paciente) object;
                    resultList.add("{"
                            + "\"id\":  \"" + paciente1.getId() + "\""
                            + ",\"hc\":  \"" + paciente1.getHistoriaClinica() + "\""
                            + ",\"cedula\": \"" + (paciente1.getCedula() != null ? paciente1.getCedula() : "-") + "\""
                            + ",\"nombres\": \"" + (paciente1.getNombre1()).toUpperCase() + "\""
                            + ",\"domicilio\": \"" + paciente1.getDomicilio() + "\""
                            + ",\"sexo\": \"" + (paciente1.getSexo()) + "\""
                            + "}");
                }

                result = "{\"count\": \"" + l.getTotal() + "\"  , \"list\": [" + String.join(",", resultList) + "]}";
                out.print(result);
                out.flush();
                out.close();
                break;
            case "det":
                String detParroquia = new ParroquiaDaoImp().detParroquia(Integer.parseInt(request.getParameter("idParroquia")));
                out.print(detParroquia);
                out.close();
                break;
            case "edit":
                paciente = new PacienteDaoImp().edit(Integer.parseInt(request.getParameter("id")));
                Obstetricos obs = new ObstetricosDaoImp().edit_idPaciente(paciente.getId());
                out = response.getWriter();
                result = "{\"paciente\": " + gson.toJson(paciente) + ",\"obs\": " + gson.toJson(obs) + "}";
                out.print(result);
                out.flush();
                out.close();
                break;
            case "save":
                //Paciente paciente = new Paciente(0); //Llama arriba
                paciente.setId(Integer.parseInt(request.getParameter("id")));
                paciente.setCedula(request.getParameter("paciente[cedula]"));
                paciente.setNombre1(test.clear_filter(request.getParameter("paciente[primerNombre]")));
                paciente.setNombre2(test.clear_filter(request.getParameter("paciente[segundoNombre]")));
                paciente.setApellido1(test.clear_filter(request.getParameter("paciente[primerApellido]")));
                paciente.setApellido2(test.clear_filter(request.getParameter("paciente[segundoApellido]")));
                paciente.setFechaNacimiento(test.fechaSQL(request.getParameter("paciente[fechaNac]")));
                paciente.setNacionalidad(request.getParameter("paciente[nacionalidad]"));
                paciente.setTelefonoDomicilio(request.getParameter("paciente[telCasa]"));
                paciente.setEmail(request.getParameter("paciente[email]"));
                paciente.setEtnia(Integer.parseInt(request.getParameter("paciente[etnia]")));
                paciente.setDomicilio(request.getParameter("paciente[domicilio]"));
                paciente.setDiscapacidad(Integer.parseInt(request.getParameter("paciente[discapacidad]")));
                paciente.setEstadoCivil(request.getParameter("paciente[estadoCivil]"));
                paciente.setApp(request.getParameter("paciente[app]"));
                paciente.setApf(request.getParameter("paciente[apf]"));
                paciente.setObservaciones(request.getParameter("paciente[observacion]"));

                paciente.setNombreContacto(test.clear_filter(request.getParameter("paciente[nombreContacto]")));
                paciente.setMovilContacto(request.getParameter("paciente[movilContacto]"));
                paciente.setParentezco(request.getParameter("paciente[parentezco]"));

                paciente.setTelefonoOficina(request.getParameter("paciente[telOficina]"));
                paciente.setSexo(request.getParameter("paciente[genero]"));
                paciente.setPaisNacimiento(request.getParameter("paciente[paisNac]"));
                paciente.setIdParroquia(new Parroquia(Integer.parseInt(request.getParameter("paciente[parroquia]"))));

                new PacienteDaoImp().save(paciente);

                if (paciente.getId() != 0) {
                    if (paciente.getSexo().equals("2")) {
                        Obstetricos obstetricos = new Obstetricos(Integer.parseInt(request.getParameter("paciente[idObs]")));
                        obstetricos.setGestas(Integer.parseInt(request.getParameter("paciente[gestacion]")));
                        obstetricos.setAbortos(Integer.parseInt(request.getParameter("paciente[abortos]")));
                        obstetricos.setCesareas(Integer.parseInt(request.getParameter("paciente[cesareas]")));
                        obstetricos.setFpp(test.fechaSQL(request.getParameter("paciente[fpp]")));
                        obstetricos.setHijosVivos(Integer.parseInt(request.getParameter("paciente[hijosVivos]")));
                        obstetricos.setIdPaciente(paciente);//Agregar Id
                        obstetricos.setMuertos(Integer.parseInt(request.getParameter("paciente[hijosMuertos]")));
                        obstetricos.setNacidosMuertos(Integer.parseInt(request.getParameter("paciente[nacidoMuerto]")));
                        obstetricos.setNacidosVivos(Integer.parseInt(request.getParameter("paciente[nacidoVivo]")));
                        //obstetricos.setObservaciones("");
                        obstetricos.setPartos(Integer.parseInt(request.getParameter("paciente[partos]")));
                        new ObstetricosDaoImp().save(obstetricos);
                    }
                    result = "{\"status\" : \"ok\", \"id\" : \""+ paciente.getCedula()+"\" }";
                } else if (paciente.getId() == -1) {
                    result = "{\"status\" : \"cedula\"}";
                }
                out.print(result);
                break;

        }
    }

    public void saveFoto(String data, int idPaciente) throws FileNotFoundException, IOException {
        String path = getServletContext().getRealPath("/");
        path = path.replace("web", "imagen");
        path = path.replace("build", "web");
        path = path + "paciente\\p_" + idPaciente + ".jpg";
        byte[] imageByteArray = DatatypeConverter.parseBase64Binary(data.substring(data.indexOf(",") + 1));
        try (FileOutputStream fileout = new FileOutputStream(path)) {
            fileout.write(imageByteArray);
            fileout.close();
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
