package mvc.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.controlador.entidades.ip.Paciente;
import mvc.controlador.entidades.sm.Caso;
import mvc.controlador.entidades.sm.Causa;
import mvc.controlador.entidades.sm.Consulta;
import mvc.controlador.entidades.sm.ConsultaEstudiosImagen;
import mvc.controlador.entidades.sm.ConsultaEstudiosLabs;
import mvc.controlador.entidades.sm.DetalleEstudiosLabs;
import mvc.controlador.entidades.sm.DetallesEstudiosImg;
import mvc.controlador.entidades.sm.HistorialClinico;
import mvc.controlador.entidades.sm.MedicoEspecialidad;
import mvc.controlador.entidades.sm.Metodos;
import mvc.controlador.entidades.sm.SignosVitales;
import mvc.controlador.entidades.sm.TipoConsulta;
import mvc.modelo.ipDaoImp.PacienteDaoImp;
import mvc.modelo.smDaoImp.CasoDaoImp;
import mvc.modelo.smDaoImp.CausaDaoImp;
import mvc.modelo.smDaoImp.ConsultaDaoImp;
import mvc.modelo.smDaoImp.ConsultaEstudiosImagenDaoImp;
import mvc.modelo.smDaoImp.ConsultaEstudiosLabsDaoImp;
import mvc.modelo.smDaoImp.EstudiosLaboratorioDaoImp;
import mvc.modelo.smDaoImp.HistorialClinicoDaoImp;
import mvc.modelo.smDaoImp.MetodosDaoImp;
import mvc.modelo.smDaoImp.SignosVitalesDaoImp;
import mvc.modelo.smDaoImp.TipoConsultaDaoImp;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import test.list_count;
import test.test;

public class sConsulta extends HttpServlet {

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
            out.println("<title>Servlet sConsulta</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sConsulta at " + request.getContextPath() + "</h1>");
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

        list_count l = new list_count();
        List<String> resultList = new ArrayList();
        switch (op) {
            case "excel":

                String jsonString = "{\"infile\": [{\"field1\": 11,\"field2\": 12,\"field3\": 13},{\"field1\": 21,\"field2\": 22,\"field3\": 23},{\"field1\": 31,\"field2\": 32,\"field3\": 33}]}";
                System.out.println(jsonString);

                break;
            case "save_edit":
                Consulta save_edit = new Consulta();
                save_edit.setId(Integer.parseInt(request.getParameter("id")));
                save_edit.setMotivo(request.getParameter("descripcion[motivo]"));
                save_edit.setSintoma(request.getParameter("descripcion[sintomas]"));
                save_edit.setDiagnostico(request.getParameter("descripcion[diagnostico]"));
                save_edit.setPrescripcion(request.getParameter("descripcion[prescipcion]"));
                save_edit.setObservacion(request.getParameter("descripcion[observacion]"));

                SignosVitales sv_edit = new SignosVitales(Integer.parseInt(request.getParameter("sv[id]")));
                sv_edit.setPeso(request.getParameter("sv[peso]"));
                sv_edit.setPresion(request.getParameter("sv[presion]"));
                sv_edit.setTalla(request.getParameter("sv[talla]"));
                sv_edit.setTemperatura(request.getParameter("sv[temperatura]"));
                sv_edit.setFrecuenciaC(request.getParameter("sv[frecuenciaC]"));
                sv_edit.setPeriodo(request.getParameter("sv[periodo]"));
                sv_edit.setFum(test.fechaSQL(request.getParameter("sv[fum]")));
                sv_edit.setFuc(test.fechaSQL(request.getParameter("sv[fuc]")));
                new SignosVitalesDaoImp().save(sv_edit);

                new ConsultaDaoImp().save_edit(save_edit);

                break;
            case "adminConsultas":
                int tops = Integer.parseInt(request.getParameter("top")),
                 pag = Integer.parseInt(request.getParameter("pag")),
                 idHC = Integer.parseInt(request.getParameter("idHC")),
                 idTipoConsulta = Integer.parseInt(request.getParameter("idTipoConsulta")),
                 opTiempo = Integer.parseInt(request.getParameter("opTiempo"));

                switch (opTiempo) {
                    case 0:
                        l = new ConsultaDaoImp().listConsultas(idHC, request.getParameter("idsEspecialidad"), idTipoConsulta, tops, pag, request.getParameter("filter"));
                        break;
                    case 1:
                    case 3:
                        Date fechaI = opTiempo == 1 ? test.fechaSQL(request.getParameter("fechaI")) : test.MesSQL(request.getParameter("fechaI"));
                        Date fechaF = opTiempo == 1 ? test.fechaSQL(request.getParameter("fechaF")) : test.MesSQL(request.getParameter("fechaF"));
                        l = new ConsultaDaoImp().listConsultas(fechaI, fechaF, opTiempo, idHC, request.getParameter("idsEspecialidad"), idTipoConsulta, tops, pag, request.getParameter("filter"));
                        break;
                    case 2:
                    case 4:
                        l = new ConsultaDaoImp().listConsultas(opTiempo == 2 ? test.MesSQL(request.getParameter("fecha")) : test.YearSQL(request.getParameter("fecha")), opTiempo, idHC, request.getParameter("idsEspecialidad"), idTipoConsulta, tops, pag, request.getParameter("filter"));
                        break;
                }

                for (Object object : l.getList()) {
                    Consulta consulta = (Consulta) object;
                    resultList.add("{"
                            + "\"id\" : \"" + consulta.getId() + "\","
                            + "\"fecha\" : \"" + consulta.getFecha() + "\","
                            + "\"paciente\" : \"" + new PacienteDaoImp().edit_HC(consulta.getIdCaso().getIdHistorialClinico().getId()).getNombres() + "\","
                            + "\"tipoConsulta\" : \"" + consulta.getSintoma() + "\","
                            + "\"causa_motivo\" : \"" + consulta.getMotivo() + "\","
                            + "\"especialidad\" : \"" + consulta.getIdMedicoEspecialidad().getIdEspecialidad().getDescripcion() + "\""
                            + "}");
                }
                saveExcel("{\"out\": [" + String.join(",", resultList) + "]}", "resultad.xls");
                out.print("{\"count\": \"" + Math.ceil((float) l.getTotal() / tops) + "\" , \"list\": [" + String.join(",", resultList) + "] }");
                out.flush();
                out.close();
                break;
            case "edit":
                Consulta value = new ConsultaDaoImp().edit(Integer.parseInt(request.getParameter("id")));
                Paciente p = new PacienteDaoImp().edit_HC(value.getIdCaso().getIdHistorialClinico().getId());
                String tipoConsulta = new TipoConsultaDaoImp().edit(value.getIdTipoConsulta()).getDescripcion();
                String metodo_causa = (String.valueOf(value.getIdTipoConsulta()).equals("1")) ? new CausaDaoImp().edit(value.getIdMetodo()).getDescripcion() : new MetodosDaoImp().edit_detMetodos(value.getIdMetodo()).getDescripcion();

                SignosVitales s_v = new SignosVitalesDaoImp().editar(value.getId());
                String x = gson.toJson(new ConsultaEstudiosImagenDaoImp().list(value.getId()));
                String y = gson.toJson(new ConsultaEstudiosLabsDaoImp().list(value.getId()));
                String respuesta = ("{ \"esti\": " + x + " ,  \"estl\" : " + y + " , \"sv\": " + gson.toJson(s_v) + ",  \"consulta\": " + gson.toJson(value) + ", \"paciente\" : \"" + p.getNombres() + "\", \"sexoP\" : \"" + (p.getSexo().equals("1") ? "H" : "M") + "\" ,\"tipoConsulta\" : \"" + tipoConsulta + "\",\"metodo_causa\" : \"" + metodo_causa + "\"  }");

                out.print(respuesta);
                out.flush();
                out.close();
                break;
            case "detCaso":
                List<Consulta> list_detCaso = new CasoDaoImp().listDetConsulta(Integer.parseInt(request.getParameter("caso")));
                for (Consulta consulta : list_detCaso) {
                    resultList.add("{"
                            + "\"id\" : \"" + consulta.getId() + "\","
                            + "\"caso\" : \"" + consulta.getIdCaso().getId() + "\","
                            + "\"fecha\" : \"" + consulta.getFecha() + "\","
                            + "\"tipo\" : \"" + consulta.getSintoma() + "\","
                            + "\"motivo\" : \"" + consulta.getMotivo() + "\","
                            + "\"especialidad\" : \"" + consulta.getPrescripcion() + "\""
                            + "}");

                }
                out.print("[" + String.join(",", resultList) + "]");
                out.flush();
                out.close();
                break;
            case "select":
                List<Causa> list_causa = new CausaDaoImp().list_filter(request.getParameter("q"));
                if (list_causa.isEmpty()) {
                    list_causa.add(new Causa(-1, request.getParameter("q").toUpperCase()));
                }
                result = gson.toJson(list_causa);
                out.print(result);
                out.flush();
                out.close();
                break;
            case "list":
                List<Consulta> list = new CasoDaoImp().listConsulta(Integer.parseInt(request.getParameter("idHc")), "", "", request.getParameter("filter"), 0, 20);
                for (Consulta con : list) {
                    resultList.add("{"
                            + "\"caso\": \"" + con.getIdCaso().getId() + "\","
                            + "\"fecha\": \"" + test.SQLSave(con.getFecha()) + "\","
                            + "\"motivo\": \"" + con.getMotivo() + "\""
                            + "}");
                }
                out.print("[" + String.join(",", resultList) + "]");
                out.flush();
                out.close();
                break;
            case "paciente":
                String codPaciente = request.getParameter("cod");
                String opcion = (codPaciente.length() == 10) ? "cedula" : "hc";
                HistorialClinico hc = new HistorialClinicoDaoImp().edit_Paciente(opcion, codPaciente);
                if (hc.getId() != 0) {
                    Paciente pc = new PacienteDaoImp().edit(hc.getIdPaciente());
                    out.print("{\"paciente\":" + gson.toJson(pc) + ",\"hc_id\": " + hc.getId() + "}");
                } else {
                    out.print("null");
                }
                out.flush();
                out.close();
                break;
            case "save":

                // <editor-fold defaultstate="collapsed" desc="Save">
                Consulta consulta = new Consulta();
                //consulta.setMotivo(request.getParameter("dc[motivo]").toUpperCase());
                consulta.setDiagnostico(request.getParameter("dc[diagnostico]").toUpperCase());
                consulta.setPrescripcion(request.getParameter("dc[prescripcion]").toUpperCase());
                //consulta.setSintoma(request.getParameter("dc[sintomas]").toUpperCase());
                consulta.setObservacion(request.getParameter("dc[observacion]").toUpperCase());
                consulta.setFecha(test.fechaSQL(request.getParameter("fecha")));

                // Caso
                Caso cs = new Caso(Integer.parseInt(request.getParameter("idCaso")));
                if (cs.getId() == 0) {
                    cs.setIdHistorialClinico(new HistorialClinico(Integer.parseInt(request.getParameter("idHc"))));
                    new CasoDaoImp().save(cs);
                }
                // Caso

                consulta.setIdCaso(cs);
                consulta.setIdMedicoEspecialidad(new MedicoEspecialidad(Integer.parseInt(request.getParameter("idEspecialidad"))));

                consulta.setIdTipoConsulta(Integer.parseInt(request.getParameter("dc[idTipoConsulta]")));
                if (consulta.getIdTipoConsulta() == 1) {
                    String idCausa = request.getParameter("dc[idMetodo][id]");
                    Causa causa = new Causa(idCausa.equals("-1") ? 0 : Integer.parseInt(idCausa), request.getParameter("dc[idMetodo][descripcion]"));
                    if (causa.getId() == 0) {
                        new CausaDaoImp().save(causa);
                    }
                    consulta.setIdMetodo(causa.getId());
                } else {
                    Metodos metodo = new Metodos(Integer.parseInt(request.getParameter("dc[idMetodo][id]")));
                    consulta.setIdMetodo(metodo.getId());
                }

                new ConsultaDaoImp().save(consulta);

                SignosVitales sv = new SignosVitales(Integer.parseInt(request.getParameter("sv[id]")));
                sv.setPeso(request.getParameter("sv[peso]"));
                sv.setTalla(request.getParameter("sv[talla]"));
                sv.setTemperatura(request.getParameter("sv[temperatura]"));
                sv.setPresion(request.getParameter("sv[presion]"));
                sv.setFrecuenciaC(request.getParameter("sv[frecuenciaC]"));
                sv.setFum(test.fechaSQL(request.getParameter("sv[fum]")));
                sv.setFuc(test.fechaSQL(request.getParameter("sv[fuc]")));
                sv.setPeriodo(request.getParameter("sv[periodo]"));
                sv.setIdConsulta(consulta.getId());
                new SignosVitalesDaoImp().save(sv);

                //Estudios Lab
                String[] estudLabs = request.getParameterValues("estudLab[]");
                if (estudLabs != null) {
                    for (String estudLab : estudLabs) {
                        ConsultaEstudiosLabs cEstLab = new ConsultaEstudiosLabs();
                        cEstLab.setIdConsulta(consulta);
                        cEstLab.setIdDetalleEstudiosLabs(new DetalleEstudiosLabs(Integer.parseInt(estudLab)));
                        new ConsultaEstudiosLabsDaoImp().save(cEstLab);
                    }
                }
                //Estudios Lab
                String estuImgs = request.getParameter("estuImg");
                if (estuImgs != null) {
                    try {
                        /*Gson g = new Gson();
                        Type tipoEstuImg = new TypeToken<List<estImg_ob>>() {
                        }.getType();
                        List<estImg_ob> estuImgs_a = gson.fromJson(estuImgs, tipoEstuImg);*/
                        estImg_ob[] estuImgs_a = gson.fromJson(estuImgs, estImg_ob[].class);
                        for (estImg_ob img_ob : estuImgs_a) {
                            ConsultaEstudiosImagen cEstImg = new ConsultaEstudiosImagen();
                            cEstImg.setIdConsulta(consulta);
                            cEstImg.setIdDetalleEstudiosImagen(new DetallesEstudiosImg(img_ob.getId()));
                            cEstImg.setDetExtremidad(img_ob.getDetExtre());
                            new ConsultaEstudiosImagenDaoImp().save(cEstImg);
                        }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    //g.
                    //estImg_ob[] estuImgs_a = g.fromJson(estuImgs, estImg_ob[].class);
                }
                out.print("hecho");
                out.flush();
                out.close();
// </editor-fold >

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

    public void saveExcel(String json_text, String fileOut) {
        JSONObject output;
        try {
            output = new JSONObject(json_text);

            JSONArray docs = output.getJSONArray("out");
            File file = new File(getServletContext().getRealPath("/xlsx/") + fileOut);
            String csv = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csv);
        } catch (JSONException e) {
            System.out.println("json " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO " + e.getMessage());
        }
    }

}

class estImg_ob {

    private int id;
    private String detExtre;

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getDetExtre() {
        return detExtre;
    }

    public void setDetExtre(String detExtre) {
        this.detExtre = detExtre;
    }

}
