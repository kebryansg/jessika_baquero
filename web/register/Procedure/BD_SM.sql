USE [BD_SM]
GO
/****** Object:  FullTextCatalog [ConsultaFTS]    Script Date: 03/09/2017 15:27:38 ******/
CREATE FULLTEXT CATALOG [ConsultaFTS]WITH ACCENT_SENSITIVITY = ON
AS DEFAULT

GO
/****** Object:  UserDefinedFunction [dbo].[GEdad]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[GEdad]
(@fNacimiento DATE, @fConsulta DATE)
RETURNS INT
as BEGIN
	DECLARE @FECHAREF DATETIME
	DECLARE @nAños int
	DECLARE @nMeses int
	DECLARE @dias int
	DECLARE @rs int
	
	SET @FECHAREF = DATEFROMPARTS(YEAR(@fConsulta),MONTH(@fConsulta), DAY(@fNacimiento))
	SET @FECHAREF = DATEADD(DAY,-1,@FECHAREF)
	 
	IF @FECHAREF > @fConsulta
	    SET @FECHAREF = DATEADD(MONTH,-1,@FECHAREF)
	 
	SET @nMeses = DATEDIFF(MONTH,@fNacimiento, @FECHAREF)
	 
	IF @FECHAREF = EOMONTH(@FECHAREF)
	    SET @nMeses = @nMeses + 1
	 
	SET @dias = DATEDIFF(DAY,@FECHAREF, @fConsulta)
	
	SET @nAños = @nMeses / 12 
	
	SET @nMeses = @nMeses % 12
	
	
	IF @nAños = 0
		BEGIN
			set @rs = case 
				WHEN @nMeses < 1 THEN 1
				WHEN @nMeses >= 1 and @nMeses <= 11  THEN 2
			end
		END
	ELSE 
		BEGIN
			set @rs = case 
					WHEN @nAños >= 1 and @nAños <= 4 THEN 3
					WHEN @nAños >= 5 and @nAños <= 9 THEN 4
					WHEN @nAños >= 10 and @nAños <= 14 THEN 5
					WHEN @nAños >= 15 and @nAños <= 19 THEN 6
					WHEN @nAños >= 20 and @nAños <= 35 THEN 7
					WHEN @nAños >= 36 and @nAños <= 49 THEN 8
					WHEN @nAños >= 50 and @nAños <= 64 THEN 9
					WHEN @nAños >= 65  THEN 10
			end
	END
	RETURN @rs;
END
GO
/****** Object:  StoredProcedure [dbo].[getConsulta]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getConsulta]
@id INT
AS BEGIN
	select esp.descripcion especialidad,tc.descripcion tipoConsulta,cs.idHistorialClinico hc, con.* from dbo.consulta con
	inner join dbo.medico_especialidad m_e on m_e.id = con.idMedico_Especialidad
	INNER join dbo.especialidad esp on esp.id = m_e.idEspecialidad
	inner JOIN dbo.tipoConsulta tc on tc.id = con.idTipoConsulta
	inner join dbo.caso cs on cs.id = con.idCaso
	where con.id = @id 
END
GO
/****** Object:  StoredProcedure [dbo].[getConsultas]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getConsultas]
@fechaI date,
@fechaF date,
@fecha date,
@idHC int,
@idTipoConsulta int,
@idsEspecialidad VARCHAR(MAX),
@opFecha int,
@tops int,
@pag int,
@total int OUTPUT
AS BEGIN
	-- 1 Fecha - 2 Mes - 3 Año
	set @total = (SELECT COUNT(*) from dbo.consulta con
	inner join dbo.caso cs on cs.id = con.idCaso
	inner join dbo.historialClinico hc on hc.id = cs.idHistorialClinico and (@idHC = 0 or hc.id = @idHC)
	inner join dbo.medico_especialidad m_e on m_e.id= con.idMedico_Especialidad
	inner join dbo.especialidad esp on esp.id = m_e.idEspecialidad
	inner join dbo.tipoConsulta  tc on tc.id = con.idTipoConsulta
	where 
		((@opFecha = 1 and (con.fecha BETWEEN @fechaI and @fechaF))
		or (@opFecha = 2 and (MONTH(con.fecha) = MONTH(@fecha) and YEAR(con.fecha) = YEAR(@fecha)))
		or (@opFecha = 4 and YEAR(con.fecha) = YEAR(@fecha))
		or (@opFecha = 0))
		and (@idsEspecialidad = '0' or esp.id in (SELECT value FROM STRING_SPLIT(@idsEspecialidad, ',')))
		and (@idTipoConsulta = 0 or tc.id = @idTipoConsulta)
		);
		PRINT @total;
		
	
	SELECT esp.id id_especialidad , esp.descripcion des_especialidad,tc.descripcion tipo, case tc.id
	WHEN 1 THEN (select ca.descripcion from dbo.causa ca where con.idMetodo = ca.id) 
	WHEN 2 THEN (select dmt.descripcion from dbo.detallesMetodos dmt where con.idMetodo = dmt.id)
	END causa_motivo, hc.id idHC, con.* from dbo.consulta con
	inner join dbo.caso cs on cs.id = con.idCaso
	inner join dbo.historialClinico hc on hc.id = cs.idHistorialClinico and (@idHC = 0 or hc.id = @idHC)
	inner join dbo.medico_especialidad m_e on m_e.id= con.idMedico_Especialidad
	inner join dbo.medico me on me.id = m_e.idMedico
	inner join dbo.especialidad esp on esp.id = m_e.idEspecialidad
	inner join dbo.tipoConsulta  tc on tc.id = con.idTipoConsulta
	where 
		((@opFecha = 1 and (con.fecha BETWEEN @fechaI and @fechaF))
		or (@opFecha = 2 and (MONTH(con.fecha) = MONTH(@fecha) and YEAR(con.fecha) = YEAR(@fecha)))
		or (@opFecha = 4 and YEAR(con.fecha) = YEAR(@fecha))
		or (@opFecha = 0))
		and (@idsEspecialidad = '0' or esp.id in (SELECT value FROM STRING_SPLIT(@idsEspecialidad, ',')))
		and (@idTipoConsulta = 0 or tc.id = @idTipoConsulta)
		order by con.fecha 
		OFFSET @pag ROWS FETCH NEXT @tops ROWS ONLY;
END
GO
/****** Object:  StoredProcedure [dbo].[getEstudiosImagen]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getEstudiosImagen]
@id INT
AS BEGIN
	SELECT t_ei.id id_te,t_ei.descripcion descripcion_te, ei.id id_ei ,ei.descripcion descripcion_ei, 
	d_ei.id id_dei, d_ei.descripcion descripcion_dei,c_ei.* from dbo.consultaEstudiosImagen c_ei
	inner join dbo.detallesEstudiosImg d_ei on c_ei.idDetalleEstudiosImagen = d_ei.id
	inner join dbo.estudioImagen ei on ei.id = d_ei.idEstudiosImg
	inner join dbo.tipoEstudioImg t_ei on  t_ei.id = ei.idTipoEstudioImg
	where c_ei.idConsulta = @id
END
GO
/****** Object:  StoredProcedure [dbo].[getPacientes]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[getPacientes] 
@trows int,
@inicio int,
@buscar NVARCHAR(100) 
as 
begin 
	declare @total_registros int
set @total_registros =(
	select
		count(*)
	from
		paciente inner join BD_SM.dbo.historialClinico on
		idPaciente = paciente.id
	where
		estado = '1'
		and(
			nombre1 like '%'+ @buscar +'%'
			or nombre2 like '%'+ @buscar +'%'
			or apellido1 like '%'+ @buscar +'%'
			or apellido2 like '%'+ @buscar +'%'
			or cedula like '%'+ @buscar +'%'
			or historialClinico.id like '%'+ @buscar +'%'
		)
);

select
	@total_registros registros,
	historialClinico.id as historia,
	paciente.*
from
	paciente inner join BD_SM.dbo.historialClinico on
	idPaciente = paciente.id
where
	estado = '1'
	and(
		nombre1 like '%'+ @buscar +'%'
		or nombre2 like '%'+ @buscar +'%'
		or apellido1 like '%'+ @buscar +'%'
		or apellido2 like '%'+ @buscar +'%'
		or cedula like '%'+ @buscar +'%'
		or historialClinico.id like '%'+ @buscar +'%'
	)
order by
	id OFFSET @inicio ROWS FETCH NEXT @trows ROWS ONLY;
end
GO
/****** Object:  StoredProcedure [dbo].[home]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[home]
	-- Add the parameters for the stored procedure here
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	declare @totalPacientes int;
	declare @totalIngresos int;
	declare @totalConsultas int;
	SET @totalPacientes=(select count (BD_IP.dbo.paciente.id) from BD_IP.dbo.paciente)
	SET @totalIngresos=(select count(id) from ingresos where (month(getdate()) between month(fechaEntrada) and month(fechaSalida)) and 
	(year(getdate()) between year(fechaEntrada) and year(fechaSalida)))
	SET @totalConsultas=(select count (id) from consulta  where (month(getdate()) between month(fecha) and month(fecha)) and 
	(year(getdate()) between year(fecha) and year(fecha)))
	select @totalPacientes as pacientes,@totalIngresos as hospitalizaciones, @totalConsultas as consultas
END

GO
/****** Object:  StoredProcedure [dbo].[Insert_IngresosHospital]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[Insert_IngresosHospital]
	-- Add the parameters for the stored procedure here
	@idHistoria as int,
	@fechaIngreso date,
	@idTipoIngreso as int,
	@idEspecialidadEgreso as int,
	@fechaEntrada as date,
	@fechaSalida as date,
	@hora as time(7),
	@sos as bit,
	@condicionEgreso as bit,
	@definitivoEgreso as text,
	@secundarioEgreso as text,
	@secundarioEgreso2 as text,
	@causaExterna as text,
	@codigoDiagnosticoDefinitivo as nvarchar(10)
	
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	INSERT INTO caso (idHistorialClinico) values (@idHistoria);
	declare @idCaso int;
	SELECT Top 1  @idCaso= id from caso order by id desc
	INSERT INTO ingresos(idTipoIngreso, idCaso,idEspecialidadEgreso,fechaEntrada,fechaSalida,hora,sos,condicionEgreso,definitivoEgreso,secundarioEgreso,secundarioEgreso2,causaExterna,codigoDiagnosticoDefinitivo,estado) values 
	(@idTipoIngreso,@idCaso,@idEspecialidadEgreso,@fechaEntrada,@fechaSalida,@hora,@sos,@condicionEgreso,@definitivoEgreso,@secundarioEgreso,@secundarioEgreso2,@causaExterna,@codigoDiagnosticoDefinitivo,1);
	declare @idIngreso int;
	SELECT Top 1  @idIngreso= id from ingresos order by id desc
	INSERT INTO detalleIngresos (idIngreso, fecha,hora) values (@idIngreso,@fechaIngreso,@hora);
	
END
GO
/****** Object:  StoredProcedure [dbo].[insertTipoConsulta]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[insertTipoConsulta]
AS BEGIN
	SET IDENTITY_INSERT [dbo].[tipoConsulta] ON;
	INSERT [dbo].[tipoConsulta](id,descripcion) values
	(1,'AMBULATORIA'),(2,'PREVENCION');
	SET IDENTITY_INSERT [dbo].[tipoConsulta] OFF 
END
GO
/****** Object:  StoredProcedure [dbo].[listConsultaCaso]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[listConsultaCaso]
@idCaso int
AS
BEGIN
	select con.id id, con.idCaso caso,  con.fecha Fecha,tc.descripcion tipo, 
	case tc.id
	WHEN 1 THEN (select ca.descripcion from dbo.causa ca where con.idMetodo = ca.id) 
	WHEN 2 THEN (select dmt.descripcion from dbo.detallesMetodos dmt where con.idMetodo = dmt.id)
	END motivo,esp.descripcion Especialidad from dbo.consulta con
	inner join dbo.medico_especialidad m_e on m_e.id = con.idMedico_Especialidad
	inner join dbo.especialidad esp on esp.id = m_e.idEspecialidad
	inner join dbo.tipoConsulta  tc on tc.id = con.idTipoConsulta
	where con.idCaso = @idCaso ORDER BY con.fecha asc;	
END
GO
/****** Object:  StoredProcedure [dbo].[login]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create    PROCEDURE [dbo].[login]
	@nick   NCHAR(10),
	@pass   NVARCHAR(50),
	@rol   int,
	@user_name NVARCHAR(100) OUTPUT,
	@idOut   int OUTPUT
AS
BEGIN
	SET NOCOUNT ON;
	declare @total int;
	select @total = count(usuario.nick), @idOut = m.id from usuario inner join medico m on m.cedula = usuario.nick where idRol = @rol and nick = @nick and cast(DECRYPTBYPASSPHRASE('encriptarClave',clave) As VARCHAR(8000)) = @pass GROUP by m.id;
	if @total > 0
		BEGIN
			set @user_name = (SELECT UPPER(CONCAT(m.nombre1,' ',m.apellidos1)) from dbo.medico m where m.cedula = @nick);
			select u.*,r.val from usuario u inner join rol r on r.id = u.idRol where u.idRol = @rol and u.nick = @nick and cast(DECRYPTBYPASSPHRASE('encriptarClave',u.clave) As VARCHAR(8000)) = @pass;
		END 
END
GO
/****** Object:  StoredProcedure [dbo].[obtener_medicos]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtener_medicos]
	-- Add the parameters for the stored procedure here
	@NUM_PAGINA   INT,
	@totalRegistros   INT
	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	
	
	declare @totalRegistro int;
	SET @totalRegistro=(SELECT COUNT(id)   from medico where visible=1);	
	
    -- Insert statements for procedure here
	SELECT @totalRegistro as registros, * FROM medico WHERE VISIBLE=1 order by id OFFSET (@NUM_PAGINA*@totalRegistros) ROWS FETCH FIRST @totalRegistros ROWS ONLY;
   
END
GO
/****** Object:  StoredProcedure [dbo].[obtenerCamasMensuales]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerCamasMensuales]
	-- Add the parameters for the stored procedure here
	@fecha as date	
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	declare @diasEstada int
	declare @idParroquia as int 
	select @idParroquia=parroquia from establecimiento
	select @diasEstada=sum(DATEDIFF(day, ingresos.fechaEntrada,ingresos.fechaSalida)) FROM establecimiento,camas,ingresos 
	where (Month(@fecha) between MONTH(ingresos.fechaEntrada)
							 and MONTH(ingresos.fechaSalida)) and YEAR(@fecha) between YEAR(ingresos.fechaEntrada) and YEAR(ingresos.fechaSalida)
	SELECT establecimiento.nombre,BD_IP.dbo.provincia.descripcion as provincia, BD_IP.dbo.canton.descripcion AS canton, BD_IP.dbo.parroquia.descripcion AS parroquia, establecimiento.direccion, establecimiento.encargado,establecimiento.telefono,
	establecimiento.email,camas.*,@diasEstada as DiasDeEstada from camas,establecimiento ,
	BD_IP.dbo.canton INNER JOIN
						BD_IP.dbo.parroquia ON canton.id = BD_IP.dbo.parroquia.idCanton INNER JOIN
						BD_IP.dbo.provincia ON canton.idProvincia = BD_IP.dbo.provincia.id where BD_IP.dbo.parroquia.id=@idParroquia



	
END
GO
/****** Object:  StoredProcedure [dbo].[obtenerEspecialidadBuscar]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerEspecialidadBuscar]
	-- Add the parameters for the stored procedure here
	@NUM_PAGINA   INT,
	@totalRegistros   INT,
	@buscar as nvarchar(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	declare @totalRegistro int;
	SET @totalRegistro=(SELECT COUNT(id) as totalRegistros from especialidad where descripcion COLLATE Latin1_General_CI_AI like '%'+@buscar+'%' COLLATE Latin1_General_CI_AI and visible=1);
	SELECT @totalRegistro as registros, * from especialidad  where descripcion COLLATE Latin1_General_CI_AI like '%'+@buscar+'%' COLLATE Latin1_General_CI_AI and visible=1 order by id OFFSET (@NUM_PAGINA*@totalRegistros) ROWS FETCH FIRST @totalRegistros ROWS ONLY;
    -- Insert statements for procedure here
   /* WITH DRV_TBL AS 
   (
      SELECT top 20
         ROW_NUMBER() OVER (ORDER BY especialidad.id asc) AS rownum, 
         especialidad.* from especialidad where descripcion like '%'+@buscar+'%' and visible=1)

   SELECT @totalRegistro as registros,* FROM DRV_TBL 
   WHERE ROWNUM BETWEEN (@NUM_PAGINA*@totalRegistros)-@totalRegistros+1 AND (@NUM_PAGINA*@totalRegistros) */
	
END
GO
/****** Object:  StoredProcedure [dbo].[obtenerEspecialidades]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerEspecialidades]
	@NUM_PAGINA   INT,
	@totalRegistros   INT,
	@total   INT OUTPUT,
	@buscar as nvarchar(50)
AS
BEGIN
	SET NOCOUNT ON;
	SET @total=(SELECT COUNT(id) as totalRegistros from especialidad where descripcion COLLATE Latin1_General_CI_AI like '%'+@buscar+'%' COLLATE Latin1_General_CI_AI and visible=1);
	SELECT * from especialidad  where descripcion COLLATE Latin1_General_CI_AI like '%'+@buscar+'%' COLLATE Latin1_General_CI_AI and visible=1 order by id OFFSET (@NUM_PAGINA) ROWS FETCH FIRST @totalRegistros ROWS ONLY;	
END
GO
/****** Object:  StoredProcedure [dbo].[obtenerIngresos]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerIngresos]
	-- Add the parameters for the stored procedure here
	@NUM_PAGINA   INT,
	@totalRegistros   INT,
	@fechaInicio DATE,
	@fechaFin date
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    declare @totalIngresos int;
    SET @totalIngresos=(SELECT  COUNT(BD_SM.dbo.ingresos.id) as totalRegistros FROM ingresos
    where (BD_SM.dbo.ingresos.fechaEntrada  BETWEEN @fechaInicio and @fechaFin 
			or BD_SM.dbo.ingresos.fechaSalida BETWEEN @fechaInicio and @fechaFin) and BD_SM.dbo.ingresos.estado=1 );
	SELECT @totalIngresos as registros, BD_SM.dbo.ingresos.id,  BD_IP.dbo.paciente.cedula, BD_IP.dbo.paciente.nombre1, BD_IP.dbo.paciente.nombre2,
			BD_IP.dbo.paciente.apellido1, BD_IP.dbo.paciente.apellido2,  BD_SM.dbo.ingresos.idTipoIngreso,
			BD_SM.dbo.ingresos.idCaso,BD_SM.dbo.ingresos.idEspecialidadEgreso, BD_SM.dbo.especialidadEgreso.descripcion, BD_SM.dbo.ingresos.fechaEntrada, BD_SM.dbo.ingresos.fechaSalida,
			BD_SM.dbo.ingresos.hora,BD_SM.dbo.ingresos.sos, BD_SM.dbo.ingresos.condicionEgreso,BD_SM.dbo.ingresos.definitivoEgreso,BD_SM.dbo.ingresos.secundarioEgreso,
			BD_SM.dbo.ingresos.secundarioEgreso2, BD_SM.dbo.ingresos.causaExterna,BD_SM.dbo.ingresos.codigoDiagnosticoDefinitivo
			from BD_IP.dbo.paciente inner join BD_SM.dbo.historialClinico on BD_IP.dbo.paciente.id= BD_SM.dbo.historialClinico.idPaciente inner join
			BD_SM.dbo.caso on BD_SM.dbo.caso.idHistorialClinico= BD_SM.dbo.historialClinico.id inner join BD_SM.dbo.ingresos on
			BD_SM.dbo.caso.id= BD_SM.dbo.ingresos.idCaso INNER JOIN BD_SM.dbo.especialidadEgreso on BD_SM.dbo.especialidadEgreso.id= BD_SM.dbo.ingresos.idEspecialidadEgreso
			where BD_SM.dbo.historialClinico.estado=1 and (BD_SM.dbo.ingresos.fechaEntrada  BETWEEN @fechaInicio and @fechaFin 
			or BD_SM.dbo.ingresos.fechaSalida BETWEEN @fechaInicio and @fechaFin) and BD_SM.dbo.ingresos.estado=1 order by id OFFSET (@NUM_PAGINA*@totalRegistros) ROWS FETCH FIRST @totalRegistros ROWS ONLY;
	/*WITH DRV_TBL AS 
   (
      SELECT 
         ROW_NUMBER() OVER (ORDER BY BD_SM.dbo.ingresos.id asc) AS rownum
         ,  BD_SM.dbo.ingresos.id,  BD_IP.dbo.paciente.cedula, BD_IP.dbo.paciente.nombre1, BD_IP.dbo.paciente.nombre2,
			BD_IP.dbo.paciente.apellido1, BD_IP.dbo.paciente.apellido2,  BD_SM.dbo.ingresos.idTipoIngreso,
			BD_SM.dbo.ingresos.idCaso,BD_SM.dbo.ingresos.idEspecialidadEgreso, BD_SM.dbo.especialidadEgreso.descripcion, BD_SM.dbo.ingresos.fechaEntrada, BD_SM.dbo.ingresos.fechaSalida,
			BD_SM.dbo.ingresos.hora,BD_SM.dbo.ingresos.sos, BD_SM.dbo.ingresos.condicionEgreso,BD_SM.dbo.ingresos.definitivoEgreso,BD_SM.dbo.ingresos.secundarioEgreso,
			BD_SM.dbo.ingresos.secundarioEgreso2, BD_SM.dbo.ingresos.causaExterna,BD_SM.dbo.ingresos.codigoDiagnosticoDefinitivo
			from BD_IP.dbo.paciente inner join BD_SM.dbo.historialClinico on BD_IP.dbo.paciente.id= BD_SM.dbo.historialClinico.idPaciente inner join
			BD_SM.dbo.caso on BD_SM.dbo.caso.idHistorialClinico= BD_SM.dbo.historialClinico.id inner join BD_SM.dbo.ingresos on
			BD_SM.dbo.caso.id= BD_SM.dbo.ingresos.idCaso INNER JOIN BD_SM.dbo.especialidadEgreso on BD_SM.dbo.especialidadEgreso.id= BD_SM.dbo.ingresos.idEspecialidadEgreso
			where (BD_SM.dbo.ingresos.fechaEntrada  BETWEEN @fechaInicio and @fechaFin 
			or BD_SM.dbo.ingresos.fechaSalida BETWEEN @fechaInicio and @fechaFin) and BD_SM.dbo.ingresos.estado=1)

   SELECT @totalIngresos as registros, * FROM DRV_TBL 
   WHERE ROWNUM BETWEEN (@NUM_PAGINA*@totalRegistros)-@totalRegistros+1 AND (@NUM_PAGINA*@totalRegistros) */
END
GO
/****** Object:  StoredProcedure [dbo].[obtenerIngresosMensuales]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerIngresosMensuales]
	-- Add the parameters for the stored procedure here
	@fecha as date
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
declare @condicion as int 
set @condicion=4
--edad cumplida al ingreso

SELECT      Month(@fecha) as MesRecoleccion,  historialClinico.id as NHistoriaClinica, caso.id AS NoArchivo, BD_IP.dbo.paciente.cedula, BD_IP.dbo.paciente.nombre1,
			BD_IP.dbo.paciente.nombre2,BD_IP.dbo.paciente.apellido1,BD_IP.dbo.paciente.apellido2, Nacionalidad= CASE BD_IP.dbo.paciente.nacionalidad WHEN 1 THEN '1' ELSE '2' end, 
			Pais= CASE BD_IP.dbo.paciente.nacionalidad WHEN 1 THEN '' ELSE BD_IP.dbo.paciente.paisNacimiento end ,BD_IP.dbo.paciente.sexo,  
			 YEAR(BD_IP.dbo.paciente.fechaNacimiento) as AñoNacimiento, MONTH(BD_IP.dbo.paciente.fechaNacimiento) as MesNacimiento, DAY(BD_IP.dbo.paciente.fechaNacimiento) as DiaNacimiento, 
			 @condicion as condicionEdad,DATEDIFF(year, fechaNacimiento,fechaEntrada) as EdadCunplidaAlIngreso,  BD_IP.dbo.paciente.etnia, BD_IP.dbo.paciente.discapacidad,  BD_IP.dbo.provincia.descripcion AS Provincia, BD_IP.dbo.canton.descripcion AS Canton, BD_IP.dbo.parroquia.descripcion AS Parroquia, 
                         BD_IP.dbo.paciente.domicilio as direccion, YEAR(ingresos.fechaEntrada) AñoIngreso,Month(ingresos.fechaEntrada) MesIngreso, DAY(ingresos.fechaEntrada) DiaIngreso,
						  YEAR(ingresos.fechaSalida) as AñoEgreso, MONTH(ingresos.fechaSalida) as MesEgreso,day(ingresos.fechaSalida) AS DiaEgreso,
						  DATEDIFF(day, ingresos.fechaEntrada,ingresos.fechaSalida) as DiasEstada, ingresos.idEspecialidadEgreso, ingresos.condicionEgreso, ingresos.definitivoEgreso, 
                         ingresos.secundarioEgreso, ingresos.secundarioEgreso2, ingresos.causaExterna, ingresos.codigoDiagnosticoDefinitivo
FROM            BD_SM.dbo.historialClinico  inner join BD_IP.dbo.paciente on BD_SM.dbo.historialClinico.idPaciente=paciente.id 
INNER JOIN
                         BD_IP.dbo.parroquia ON BD_IP.dbo.paciente.idParroquia = BD_IP.dbo.parroquia.id INNER JOIN
						 BD_IP.dbo.canton on BD_IP.dbo.parroquia.idCanton= canton.id INNER JOIN
						 BD_IP.dbo.provincia on BD_IP.dbo.canton.idProvincia=BD_IP.dbo.provincia.id
INNER JOIN
                         caso ON BD_SM.dbo.historialClinico.id = BD_SM.dbo.caso.idHistorialClinico INNER JOIN
                         BD_SM.dbo.ingresos ON caso.id = ingresos.idCaso INNER JOIN
                         tipoIngreso ON ingresos.idTipoIngreso = tipoIngreso.id where (Month(@fecha) between MONTH(ingresos.fechaEntrada)
						 and MONTH(ingresos.fechaSalida)) and YEAR(@fecha) between YEAR(ingresos.fechaEntrada) and YEAR(ingresos.fechaSalida)


END
GO
/****** Object:  StoredProcedure [dbo].[obtenerMedicosBuscar]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerMedicosBuscar]
	-- Add the parameters for the stored procedure here
	@NUM_PAGINA   INT,
	@totalRegistros   INT,
	@buscar as nvarchar(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
    declare @totalRegistro int;
    SET @totalRegistro=(SELECT  COUNT(id) as totalRegistros FROM medico WHERE cedula like '%'+@buscar+'%' 
		or nombre1 +' '+ nombre2 +' '+apellidos1  +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ nombre2 +' '+apellidos2  +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos1  +' '+apellidos2 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos1  +' '+nombre2 +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos2  +' '+nombre2 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos2  +' '+nombre2 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos2  +' '+apellidos1 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or nombre2 +' '+ nombre1 +' '+apellidos1  +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or nombre2 +' '+ nombre1 +' '+apellidos2  +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or nombre2 +' '+ apellidos1  +' '+apellidos2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellidos1  +' '+nombre1 +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellidos2  +' '+nombre1 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellidos2  +' '+nombre1 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellidos2  +' '+apellidos1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or apellidos1 +' '+ apellidos2 +' '+nombre1  +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ apellidos2 +' '+nombre2  +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre1  +' '+nombre2 +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre1  +' '+apellidos2 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre2  +' '+apellidos2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre2  +' '+apellidos2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre2  +' '+nombre1 +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or apellidos2 +' '+ apellidos1 +' '+nombre1  +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or apellidos2 +' '+ apellidos1 +' '+nombre2  +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or apellidos2 +' '+ nombre1  +' '+nombre2 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos2 +' '+ nombre1  +' '+apellidos1 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos2 +' '+ nombre2  +' '+apellidos1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos2 +' '+ nombre2  +' '+apellidos1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos2 +' '+ nombre2  +' '+nombre1 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%' and visible=1);
	SELECT @totalRegistro as registros, * from medico
         WHERE cedula like '%'+@buscar+'%' 
		or nombre1 +' '+ nombre2 +' '+apellidos1  +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ nombre2 +' '+apellidos2  +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos1  +' '+apellidos2 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos1  +' '+nombre2 +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos2  +' '+nombre2 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos2  +' '+nombre2 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellidos2  +' '+apellidos1 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or nombre2 +' '+ nombre1 +' '+apellidos1  +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or nombre2 +' '+ nombre1 +' '+apellidos2  +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or nombre2 +' '+ apellidos1  +' '+apellidos2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellidos1  +' '+nombre1 +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellidos2  +' '+nombre1 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellidos2  +' '+nombre1 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellidos2  +' '+apellidos1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or apellidos1 +' '+ apellidos2 +' '+nombre1  +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ apellidos2 +' '+nombre2  +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre1  +' '+nombre2 +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre1  +' '+apellidos2 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre2  +' '+apellidos2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre2  +' '+apellidos2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos1 +' '+ nombre2  +' '+nombre1 +' ' +apellidos2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or apellidos2 +' '+ apellidos1 +' '+nombre1  +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or apellidos2 +' '+ apellidos1 +' '+nombre2  +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or apellidos2 +' '+ nombre1  +' '+nombre2 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos2 +' '+ nombre1  +' '+apellidos1 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos2 +' '+ nombre2  +' '+apellidos1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos2 +' '+ nombre2  +' '+apellidos1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellidos2 +' '+ nombre2  +' '+nombre1 +' ' +apellidos1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%' and visible=1 order by id OFFSET (@NUM_PAGINA*@totalRegistros) ROWS FETCH FIRST @totalRegistros ROWS ONLY;
END
GO
/****** Object:  StoredProcedure [dbo].[obtenerPacientes]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerPacientes]
	-- Add the parameters for the stored procedure here
	@NUM_PAGINA   INT,
	@totalRegistros   INT
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	declare @totalRegistro int;
	SET @totalRegistro=(select count(BD_SM.dbo.historialClinico.idPaciente) as totalRegistros from  BD_IP.dbo.paciente inner join BD_SM.dbo.historialClinico on BD_SM.dbo.historialClinico.idPaciente=paciente.id where BD_SM.dbo.historialClinico.estado=1);
	SELECT @totalRegistro as registros, BD_SM.dbo.historialClinico.id, BD_SM.dbo.historialClinico.idPaciente, BD_IP.dbo.paciente.cedula,BD_IP.dbo.paciente.nombre1,BD_IP.dbo.paciente.nombre2,BD_IP.dbo.paciente.apellido1, BD_IP.dbo.paciente.apellido2 ,BD_IP.dbo.paciente.domicilio, BD_IP.dbo.paciente.email  from BD_IP.dbo.paciente inner join BD_SM.dbo.historialClinico on BD_SM.dbo.historialClinico.idPaciente=paciente.id where BD_SM.dbo.historialClinico.estado=1 order by id OFFSET (@NUM_PAGINA*@totalRegistros) ROWS FETCH FIRST @totalRegistros ROWS ONLY;
    -- Insert statements for procedure here
	/*WITH DRV_TBL AS 
   (
      SELECT 
         ROW_NUMBER() OVER (ORDER BY BD_SM.dbo.historialClinico.id asc) AS rownum
         , BD_SM.dbo.historialClinico.id, BD_SM.dbo.historialClinico.idPaciente, BD_IP.dbo.paciente.cedula,BD_IP.dbo.paciente.nombre1,BD_IP.dbo.paciente.nombre2,BD_IP.dbo.paciente.apellido1, BD_IP.dbo.paciente.apellido2 ,BD_IP.dbo.paciente.domicilio, BD_IP.dbo.paciente.email  from BD_IP.dbo.paciente inner join BD_SM.dbo.historialClinico on BD_SM.dbo.historialClinico.idPaciente=paciente.id)	

   SELECT @totalRegistro as registros, * FROM DRV_TBL 
   WHERE ROWNUM BETWEEN (@NUM_PAGINA*@totalRegistros)-@totalRegistros+1 AND (@NUM_PAGINA*@totalRegistros) */
END
GO
/****** Object:  StoredProcedure [dbo].[obtenerPacientesBuscar]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[obtenerPacientesBuscar]
	-- Add the parameters for the stored procedure here
	@NUM_PAGINA   INT,
	@totalRegistros   INT,
	@buscar as nvarchar(50)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	declare @totalRegistro int;
	SET @totalRegistro=(select count(BD_SM.dbo.historialClinico.idPaciente) as totalRegistros from  BD_IP.dbo.paciente inner join BD_SM.dbo.historialClinico on BD_SM.dbo.historialClinico.idPaciente=paciente.id
	WHERE cedula like '%'+@buscar+'%' 
		or nombre1 +' '+ nombre2 +' '+apellido1  +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ nombre2 +' '+apellido2  +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido1  +' '+apellido2 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido1  +' '+nombre2 +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido2  +' '+nombre2 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido2  +' '+nombre2 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido2  +' '+apellido1 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or nombre2 +' '+ nombre1 +' '+apellido1  +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or nombre2 +' '+ nombre1 +' '+apellido2  +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or nombre2 +' '+ apellido1  +' '+apellido2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellido1  +' '+nombre1 +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellido2  +' '+nombre1 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellido2  +' '+nombre1 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellido2  +' '+apellido1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or apellido1 +' '+ apellido2 +' '+nombre1  +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ apellido2 +' '+nombre2  +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre1  +' '+nombre2 +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre1  +' '+apellido2 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre2  +' '+apellido2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre2  +' '+apellido2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre2  +' '+nombre1 +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or apellido2 +' '+ apellido1 +' '+nombre1  +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or apellido2 +' '+ apellido1 +' '+nombre2  +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or apellido2 +' '+ nombre1  +' '+nombre2 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido2 +' '+ nombre1  +' '+apellido1 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido2 +' '+ nombre2  +' '+apellido1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido2 +' '+ nombre2  +' '+apellido1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido2 +' '+ nombre2  +' '+nombre1 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'

		COLLATE Latin1_General_CI_AI or domicilio like '%'+@buscar+'%');
	SELECT @totalRegistro as registros, BD_SM.dbo.historialClinico.id, BD_SM.dbo.historialClinico.idPaciente, BD_IP.dbo.paciente.cedula,BD_IP.dbo.paciente.nombre1,BD_IP.dbo.paciente.nombre2,BD_IP.dbo.paciente.apellido1, BD_IP.dbo.paciente.apellido2 ,BD_IP.dbo.paciente.domicilio, BD_IP.dbo.paciente.email   from  BD_IP.dbo.paciente inner join
        BD_SM.dbo.historialClinico on BD_SM.dbo.historialClinico.idPaciente=paciente.id WHERE cedula like '%'+@buscar+'%' 
		or nombre1 +' '+ nombre2 +' '+apellido1  +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ nombre2 +' '+apellido2  +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido1  +' '+apellido2 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido1  +' '+nombre2 +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido2  +' '+nombre2 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido2  +' '+nombre2 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre1 +' '+ apellido2  +' '+apellido1 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or nombre2 +' '+ nombre1 +' '+apellido1  +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or nombre2 +' '+ nombre1 +' '+apellido2  +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or nombre2 +' '+ apellido1  +' '+apellido2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellido1  +' '+nombre1 +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellido2  +' '+nombre1 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellido2  +' '+nombre1 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or nombre2 +' '+ apellido2  +' '+apellido1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or apellido1 +' '+ apellido2 +' '+nombre1  +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ apellido2 +' '+nombre2  +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre1  +' '+nombre2 +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre1  +' '+apellido2 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre2  +' '+apellido2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre2  +' '+apellido2 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido1 +' '+ nombre2  +' '+nombre1 +' ' +apellido2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		
		or apellido2 +' '+ apellido1 +' '+nombre1  +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or apellido2 +' '+ apellido1 +' '+nombre2  +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'		
		or apellido2 +' '+ nombre1  +' '+nombre2 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido2 +' '+ nombre1  +' '+apellido1 +' ' +nombre2 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido2 +' '+ nombre2  +' '+apellido1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido2 +' '+ nombre2  +' '+apellido1 +' ' +nombre1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'
		or apellido2 +' '+ nombre2  +' '+nombre1 +' ' +apellido1 COLLATE Latin1_General_CI_AI like '%'+@buscar+'%'

		COLLATE Latin1_General_CI_AI or domicilio like '%'+@buscar+'%'  
		 order by id OFFSET (@NUM_PAGINA*@totalRegistros) ROWS FETCH FIRST @totalRegistros ROWS ONLY;
    
END
GO
/****** Object:  StoredProcedure [dbo].[saveCaso]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[saveCaso]
@hc int,
@id int OUTPUT
AS
BEGIN
	INSERT INTO BD_SM.dbo.caso(idHistorialClinico) VALUES(@hc);
	SELECT Top 1  @id = id from BD_SM.dbo.caso order by id desc
END
GO
/****** Object:  StoredProcedure [dbo].[saveCausa]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[saveCausa]
@descripcion text,
@id int OUTPUT
as
BEGIN
	insert into causa(descripcion,idTipoConsulta) values(@descripcion,1)
	SELECT Top 1  @id = id from causa order by id desc
END
GO
/****** Object:  StoredProcedure [dbo].[saveConsulta]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[saveConsulta]
@idMedico_Especialidad int,
@idCaso int,
@fecha DATE,
@idMetodo int,
@idTipoConsulta int,
@diagnostico text,
@prescripcion TEXT,
@observacion TEXT,
@id int OUTPUT
AS
BEGIN
	INSERT INTO BD_SM.dbo.consulta
	(idMedico_Especialidad, idMetodo, idCaso, fecha, idTipoConsulta, 
	diagnostico, prescripcion, observacion)
	VALUES(@idMedico_Especialidad, @idMetodo, @idCaso, @fecha, @idTipoConsulta, @diagnostico, @prescripcion,@observacion);
	SELECT Top 1  @id = id from BD_SM.dbo.consulta order by id desc

END
GO
/****** Object:  StoredProcedure [dbo].[savePaciente]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE    PROCEDURE [dbo].[savePaciente] 	
@id int,
@cedula NCHAR(10),
@nombre1 NVARCHAR(50),
@nombre2 NVARCHAR(50),
@apellido1 NVARCHAR(50),
@apellido2 NVARCHAR(50),
@domicilio NVARCHAR(50),
@nacionalidad char(1),
@estadocivil CHAR(1),
@telDomicilio NVARCHAR(50),
@telOficina NVARCHAR(50),
@email NVARCHAR(50),
@sexo char(1),
@paisNac NVARCHAR(50),
@fechaNac date,
@etnia int,
@discapacidad int,
@idParroquia int,
@nombreContacto NVARCHAR(300),
@parentezco CHAR(1),
@movilContacto CHAR(10),
@app NVARCHAR(500),
@apf NVARCHAR(500),
@observacion NVARCHAR(500),
@idOut int OUTPUT
AS
BEGIN
	set @idOut = 13;
	IF @id = 0
		BEGIN
			DECLARE @val_cedula int;
			set @val_cedula = (SELECT count(*) from dbo.paciente where cedula = @cedula);
			IF @val_cedula > 0
				set @idOut = -1;
			ELSE
				BEGIN
					INSERT INTO [dbo].[paciente]([cedula],[nombre1],[nombre2],[apellido1],[apellido2],[domicilio],[nacionalidad],[estadoCivil],
					[telefonoDomicilio],[telefonoOficina],[email],[sexo],[paisNacimiento],[fechaNacimiento],[etnia],[discapacidad],[idParroquia],[nombreContacto],[movilContacto],[parentezco],[app],[apf],[observacion])
					VALUES(@cedula,@nombre1,@nombre2,@apellido1,@apellido2,@domicilio,@nacionalidad,@estadocivil,@teldomicilio,@telOficina,@email,@sexo,@paisNac,@fechaNac,@etnia,@discapacidad,@idParroquia,@nombreContacto,@movilContacto,@parentezco,@app,@apf,@observacion);
					set @idOut = (SELECT top 1 id from dbo.paciente ORDER by id DESC);
					INSERT INTO BD_SM.[dbo].[historialClinico]([idPaciente],[fecha],[menarca],[estado]) VALUES(@idOut,GETDATE(),GETDATE(),'1');
				END;				
		END;
	ELSE
		BEGIN
			set @idOut = @id;
			UPDATE [dbo].[paciente] SET cedula = @cedula , nombre1 = @nombre1 , nombre2 = @nombre2 , apellido1 = @apellido1 , apellido2 = @apellido2 , domicilio = @domicilio , nacionalidad = @nacionalidad , estadoCivil = @estadocivil , telefonoDomicilio = @teldomicilio , telefonoOficina = @telOficina , email = @email , sexo = @sexo , paisNacimiento = @paisNac , fechaNacimiento = @fechaNac , etnia = @etnia , discapacidad = @discapacidad ,  idParroquia = @idParroquia ,  nombreContacto = @nombreContacto ,  movilContacto = @movilContacto ,  parentezco = @parentezco , app = @app , apf = @apf, observacion = @observacion where id = @id; 
		END;
END;
GO
/****** Object:  StoredProcedure [dbo].[saveSignosVitales]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[saveSignosVitales]
@id int,
@idConsulta int,
@peso NVARCHAR(50),
@talla NVARCHAR(50),
@temperatura NVARCHAR(50),
@presion NVARCHAR(50),
@fum DATE,
@fuc date,
@frecuencia NVARCHAR(50),
@periodo char(1),
@idOut int OUTPUT
AS
BEGIN
	DECLARE @total int;
	SELECT @total = count(id) from dbo.signosVitales where id = @id;
	IF @total = 0
		BEGIN
			INSERT INTO BD_SM.dbo.signosVitales
			(presion, fum, fuc, frecuenciaC, periodo, peso, talla, temperatura,idConsulta)
			VALUES(@presion, @fum, @fuc, @frecuencia, @periodo, @peso, @talla, @temperatura,@idConsulta);
			SELECT Top 1  @idOut = id from BD_SM.dbo.signosVitales order by id desc
		END 
	ELSE
		BEGIN
			set @idOut = @id;
			UPDATE dbo.signosVitales set presion = @presion,fum = @fum, fuc = @fuc, frecuenciaC = @frecuencia,
			periodo = @periodo, talla=@talla, temperatura = @temperatura, peso = @peso where id = @id;
		END
END;
GO
/****** Object:  StoredProcedure [dbo].[subExcel]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[subExcel]
AS BEGIN
	SELECT * from 
	(
		SELECT dbo.GEdad(pc.fechaNacimiento,cs.fecha) as g_edad2, dbo.GEdad(pc.fechaNacimiento,cs.fecha) as g_edad, esp.id as especialidad from dbo.consulta cs
		INNER JOIN dbo.caso ca on cs.idCaso = ca.id
		INNER JOIN dbo.historialClinico hc on ca.idHistorialClinico = hc.id
		INNER JOIN BD_IP.dbo.paciente pc on hc.idPaciente = pc.id
		INNER JOIN dbo.medico_especialidad m_e on cs.idMedico_Especialidad = m_e.id
		INNER JOIN dbo.especialidad esp on m_e.idEspecialidad = esp.id -- and (esp.id in(38)) -- Especificando (general, obstetricia,psicologia)
	) p
	pivot(COUNT(g_edad) for especialidad in([37],[38])) as eee
END
GO
/****** Object:  StoredProcedure [dbo].[up_consulta]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create PROCEDURE [dbo].[up_consulta]
@id int,
@motivo TEXT,
@sintoma TEXT,
@diagnostico TEXT,
@prescripcion TEXT,
@observacion TEXT
as 
BEGIN
	UPDATE dbo.consulta set motivo = @motivo, sintomas = @sintoma, diagnostico = @diagnostico , prescripcion = @prescripcion ,observacion = @observacion WHERE id = @id;
END;
GO
/****** Object:  StoredProcedure [dbo].[UpdateIngresos]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[UpdateIngresos] 
	-- Add the parameters for the stored procedure here
	@idIngreso as int,
	@fechaIngreso date,
	@idTipoIngreso as int,
	@idEspecialidadEgreso as int,
	@fechaEntrada as date,
	@fechaSalida as date,
	@hora as time(7),
	@sos as bit,
	@condicionEgreso as bit,
	@definitivoEgreso as text,
	@secundarioEgreso as text,
	@secundarioEgreso2 as text,
	@causaExterna as text,
	@codigoDiagnosticoDefinitivo as nvarchar(10),
	@idCaso as int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	
	
	UPDATE ingresos set idEspecialidadEgreso=@idEspecialidadEgreso, fechaEntrada=@fechaEntrada, fechaSalida=@fechaSalida, hora=@hora, condicionEgreso=@condicionEgreso,
	definitivoEgreso=@definitivoEgreso, secundarioEgreso=@secundarioEgreso, secundarioEgreso2=@secundarioEgreso2, causaExterna=@causaExterna,codigoDiagnosticoDefinitivo=@codigoDiagnosticoDefinitivo where id= @idIngreso
	update detalleIngresos set fecha=@fechaIngreso,  hora=@hora where idIngreso=@idIngreso 	
END
GO
/****** Object:  StoredProcedure [dbo].[viewCaso]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[viewCaso]
@hc INT,
@trows int,
@inicio int,
@buscar NVARCHAR(300)
AS
BEGIN
	SELECT caso,fecha,diagnostico,especialidad,tipoConsulta,COUNT(*) over() full_count  from 
	(
		select ROW_NUMBER() OVER(PARTITION BY co.idcaso ORDER BY co.fecha ASC) AS r, 
			co.idcaso as caso,co.fecha fecha,co.diagnostico,esp.descripcion especialidad,tc.descripcion tipoConsulta
		from dbo.consulta co
		inner join dbo.caso ca on co.idCaso = ca.id 
		inner join dbo.historialClinico hc on hc.id = ca.idHistorialClinico and hc.id = @hc
		inner join dbo.medico_especialidad m_e on m_e.id = co.idMedico_Especialidad
		INNER join dbo.especialidad esp on esp.id = m_e.idEspecialidad
		INNER join dbo.tipoConsulta tc on tc.id = co.idTipoConsulta
		where @buscar = '"*"' or (CONTAINS(diagnostico,@buscar))
	) as r 
	WHERE r.r = 1 
	ORDER by fecha desc
	OFFSET @inicio ROWS FETCH NEXT @trows ROWS ONLY;
END
GO
/****** Object:  StoredProcedure [dbo].[viewEstudiosImg]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[viewEstudiosImg]
@filtro NVARCHAR(100),
@tops int,
@pag int,
@tipoEstudio int,
@estImg int,
@registros int OUTPUT
AS
BEGIN
	set @registros = (select count(*) from dbo.detallesEstudiosImg det_est_i inner join dbo.estudioImagen est_i on est_i.id = det_est_i.idEstudiosImg inner join dbo.tipoEstudioImg t_est on est_i.idTipoEstudioImg = t_est.id 
		where (det_est_i.descripcion like '%'+ @filtro +'%')  
		and ((@tipoEstudio = 0) or (t_est.id = @tipoEstudio)) 
		and ((@estImg = 0) or (est_i.id = @estImg)));
	select t_est.descripcion t_descripcion, det_est_i.id, det_est_i.descripcion, det_est_i.extremidades from dbo.detallesEstudiosImg det_est_i inner join dbo.estudioImagen est_i on est_i.id = det_est_i.idEstudiosImg inner join dbo.tipoEstudioImg t_est on est_i.idTipoEstudioImg = t_est.id 
		where (det_est_i.descripcion like '%'+ @filtro +'%')  
		and ((@tipoEstudio = 0) or (t_est.id = @tipoEstudio)) 
		and ((@estImg = 0) or (est_i.id = @estImg)) 
		order by det_est_i.id 
		OFFSET @pag ROWS FETCH NEXT @tops ROWS ONLY;
END
GO
/****** Object:  StoredProcedure [dbo].[viewEstudiosLab]    Script Date: 03/09/2017 15:27:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[viewEstudiosLab]
@filtro NVARCHAR(100),
@tops int,
@pag int,
@categoria int,
@registros int OUTPUT
AS
BEGIN
	set @registros = (select COUNT(*) from dbo.estudiosLaboratorio el inner join dbo.detalleEstudiosLabs del on el.id = del.idEstudiosLab 
	where (del.descripcion like '%'+ @filtro +'%')  and  ((@categoria = 0) or (el.id = @categoria)));
	
	select del.id del_id, del.descripcion del_descripcion, el.id el_id, el.descripcion el_descripcion from dbo.estudiosLaboratorio el inner join dbo.detalleEstudiosLabs del on el.id = del.idEstudiosLab 
	where (del.descripcion like '%'+ @filtro +'%') and ((@categoria = 0) or (el.id = @categoria))
	order by del.id OFFSET @pag ROWS FETCH NEXT @tops ROWS ONLY;
END
GO
