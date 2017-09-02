/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.servlet;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.controlador.entidades.sm.Especialidad;
import mvc.controlador.entidades.sm.Medico;
import mvc.controlador.entidades.sm.MedicoEspecialidad;
import mvc.modelo.smDao.EspecialidadDao;
import mvc.modelo.smDao.MedicoDao;
import mvc.modelo.smDao.MedicoEspecialidadDao;
import mvc.modelo.smDaoImp.EspecialidadDaoImp;
import mvc.modelo.smDaoImp.MedicoDaoImp;
import mvc.modelo.smDaoImp.MedicoEspecialidadDaoImp;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Deivi
 */
public class sMedico extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
        JsonObject object = new JsonObject();
        try
        {

        String opcion = request.getParameter("opcion");
        //cargar especialidades de un medico 
        if("1".equals(opcion))
        {
            
            EspecialidadDao mediEspe= new EspecialidadDaoImp();
            String idMedico=request.getParameter("idMedico");
             List<Especialidad> list= mediEspe.list(Integer.valueOf(idMedico));             
             ObjectMapper OBJECT_MAPPER = new ObjectMapper();
             String json = OBJECT_MAPPER.writeValueAsString(list);
             response.getWriter().write(json);
             /*
              for(int i=0;i<especialidades.size();i++)
              {
                  Boolean bandera=false;
                  for(int j=0;j<list.size();j++)
                  {
                      if(Objects.equals(especialidades.get(i).getId(), list.get(j).getId()))
                      {
                          bandera=true;
                          break;
                      } 
                  }
                  if(bandera)
                  {
                      out.println("<option selected='selected' value='"+especialidades.get(i).getId()+"'>"+especialidades.get(i).getDescripcion()+"</option>");
                  }
                  else
                  {
                  out.println("<option value='"+especialidades.get(i).getId()+"'>"+especialidades.get(i).getDescripcion()+"</option>");
                  }
              }*/
        }
        //obtiene los medicos de forma paginada
        else if("2".equals(opcion))
        {
           MedicoDao med= new MedicoDaoImp();
           Integer bandera =Integer.valueOf(request.getParameter("bandera"));
           String buscar =request.getParameter("buscar");
           Integer totalMostrar =Integer.valueOf(request.getParameter("totalMostrar"));
          Integer  pagina =Integer.valueOf( request.getParameter("pagina"));
           List<Medico> list= med.list(pagina,totalMostrar,bandera, buscar);  
           ObjectMapper OBJECT_MAPPER = new ObjectMapper();
           String json = OBJECT_MAPPER.writeValueAsString(list);
           response.getWriter().write(json);
           /*for(Medico elem: list)
           {
            out.println("<tr id='"+elem.getId()+"' class='active'>");
            out.println("<td>"+elem.getId()+"</td>");
            out.println("<td>"+elem.getCedula()+"</td>");
            out.println("<td>"+elem.getApellidos1()+" "+elem.getApellidos2()+"</td>");
            out.println("<td>"+elem.getNombre1()+" "+elem.getNombre2()+"</td>");
            out.println("<td>"+elem.getDomicilio()+"</td>");
            out.println("<td style='display:none;'>"+elem.getCiudad()+"</td>");
            out.println("<td  style='display:none;'>"+elem.getTelefonoDomicilio()+"</td>");
            out.println("<td  style='display:none;'>"+elem.getTelefonoOficina()+"</td>");
            out.println("<td>"+elem.getTelefonoMovil()+"</td>");
            out.println("<td style='display:none;'>"+elem.getEmail()+"</td>");
            out.println("<td style='display:none;'>"+String.valueOf(elem.getVisible())+"</td>  ");                                             
            out.println("<td style='width: 20%' >   ");
            out.println("<button id='botonEditar' class='btn btn-primary'><span class='glyphicon glyphicon-pencil'></span> </button> ");                                           
            out.println("<button id='btnEliminar' class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span></a></button>");
            out.println("</td>");                                       
            out.println("</tr>");
           }*/
        }
        //obtener total de registros
        else if("4".equals(opcion))
        {
            Integer bandera =Integer.valueOf(request.getParameter("bandera"));
            String buscar =request.getParameter("buscar");
            MedicoDao med= new MedicoDaoImp();
            out.println(med.totalRegistros(bandera, buscar));
        }
        //eliminar
        else if("5".equals(opcion))
        {
            MedicoDao med= new MedicoDaoImp();
            Integer idMedico =Integer.valueOf(request.getParameter("idMedico"));
            object.addProperty("estado", med.delete(idMedico));
            object.addProperty("excepcion", med.getExcepcion());
            out.println(object);
        }
        else if("6".equals(opcion))
        {
            String cedula = request.getParameter("cedula");
            MedicoDao med= new MedicoDaoImp();
           out.println( med.validarCedula(cedula));
            
        }
        //guardar
        else
        {
           String cedula = request.getParameter("cedula");
            String primerNombre = request.getParameter("primerNombre");
            String segundoNombre = request.getParameter("segundoNombre");
            String primerApellido = request.getParameter("primerApellido");
            String segundoApellido = request.getParameter("segundoApellido");
            String domicilio = request.getParameter("domicilio");
            String ciudad = request.getParameter("ciudad");
            String telefonoOficina = request.getParameter("telefonoOficina");
            String email = request.getParameter("email");
            String telefonoDomicilio = request.getParameter("telefonoDomicilio");
            String telefonoMovil = request.getParameter("telefonoMovil");        
            Integer visible = Integer.parseInt(request.getParameter("visible"));        
            String[] idEspecialidades = request.getParameterValues("idEspecialidad[]"); 
            MedicoDao medico= new MedicoDaoImp();
            Medico unMedico= new Medico(primerNombre, segundoNombre, primerApellido, segundoApellido,domicilio,ciudad, telefonoDomicilio,telefonoOficina, telefonoMovil, email,  cedula, visible);
            //si es 0 guarda si es 3 edita  
             Integer idMd=0;
             boolean flag=false;
            if("0".equals(opcion))
            {
                unMedico.setId(0);
            }
            else
            {
                idMd = Integer.parseInt(request.getParameter("idMedico"));
                unMedico.setId(idMd);
                flag=true;
            }       
            medico.save(unMedico);
            //insertar
            Medico medi=null;
            MedicoEspecialidadDao mediEspe= new MedicoEspecialidadDaoImp();
            if(!flag)
                medi= new Medico(medico.id());
            else
            {
                medi= new Medico(Integer.parseInt(String.valueOf(idMd)));
                mediEspe.delete(idMd);
                
            }
            
            boolean estado=false;
            String excepcion="";
            for (String idEspecialidad : idEspecialidades) {                
                Especialidad espe;         
                espe = new Especialidad(Integer.parseInt(idEspecialidad));
                MedicoEspecialidad medicoEspe= new MedicoEspecialidad(medi,espe);                
                medicoEspe.setId(idMd);
                estado=mediEspe.save(medicoEspe);
            } 
            object.addProperty("estado", estado);
            object.addProperty("excepcion", excepcion);
            out.println(object);
        }
        
        }
        catch(Exception ex)
        {
            object.addProperty("estado", false);
            object.addProperty("excepcion", ex.getMessage());             
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
