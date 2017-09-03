USE [BD_IP]
GO
/****** Object:  FullTextCatalog [PacienteFTS]    Script Date: 03/09/2017 15:26:34 ******/
CREATE FULLTEXT CATALOG [PacienteFTS]WITH ACCENT_SENSITIVITY = OFF

GO
/****** Object:  StoredProcedure [dbo].[getPacientes]    Script Date: 03/09/2017 15:26:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[getPacientes] 
@trows int,
@inicio int,
@op_filter NVARCHAR(max),
@buscar NVARCHAR(300)
as 
begin
	IF @op_filter = '1'
		BEGIN
			IF @buscar = ''
				set @buscar = N'"a*"';
			select hc.id as historia,paciente.id,cedula,nombres,domicilio,sexo,COUNT(paciente.id) OVER() as full_count
			from
				paciente inner join BD_SM.dbo.historialClinico hc on
				hc.idPaciente = paciente.id and hc.estado = '1'
			where CONTAINS(nombres,@buscar)
			ORDER by nombres OFFSET @inicio ROWS FETCH NEXT @trows ROWS ONLY;
		END 
	else 
		BEGIN
			IF @op_filter = '2'
				BEGIN
					IF @buscar = ''
						set @buscar = N'"1*"';
					ELSE
						set @buscar = N'"' + @buscar  + '*"';
					select hc.id as historia,
						paciente.id,cedula,nombres,domicilio,sexo, COUNT(paciente.id) OVER() as full_count
					from
						paciente inner join BD_SM.dbo.historialClinico hc on
						hc.idPaciente = paciente.id and hc.estado = '1'
					where CONTAINS(cedula,@buscar)
					ORDER by nombres OFFSET @inicio ROWS FETCH NEXT @trows ROWS ONLY;
				END
			ELSE
				BEGIN
					select hc.id as historia,
						paciente.id,cedula,nombres,domicilio,sexo, COUNT(paciente.id) OVER() as full_count
					from
						paciente inner join BD_SM.dbo.historialClinico hc on
						hc.idPaciente = paciente.id and hc.estado = '1'
					where hc.id like @buscar + '%'
					ORDER by nombres OFFSET @inicio ROWS FETCH NEXT @trows ROWS ONLY;
				END
			
		END
END
GO
/****** Object:  StoredProcedure [dbo].[savePaciente]    Script Date: 03/09/2017 15:26:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[savePaciente] 	
@id int,
@cedula NCHAR(10),
@nombre1 NVARCHAR(50),
@nombre2 NVARCHAR(50),
@apellido1 NVARCHAR(50),
@apellido2 NVARCHAR(50),
@nombres NVARCHAR(max),
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
	--DECLARE @val_cedula int;
	--set @val_cedula = (SELECT count(p.id) from dbo.paciente p inner join BD_SM.dbo.historialClinico hc on hc.idPaciente = p.id and hc.estado = 1 where cedula = @cedula and @id = 0 );
	IF EXISTS (SELECT * from dbo.paciente p inner join BD_SM.dbo.historialClinico hc on hc.estado = 1 and hc.idPaciente = p.id where @id = 0 and cedula = @cedula) 
		set @idOut = -1;
	ELSE
		BEGIN
			IF @id = 0
				BEGIN
					INSERT INTO [dbo].[paciente]([cedula],[nombre1],[nombre2],[apellido1],[apellido2],[nombres],[domicilio],[nacionalidad],[estadoCivil],
					[telefonoDomicilio],[telefonoOficina],[email],[sexo],[paisNacimiento],[fechaNacimiento],[etnia],[discapacidad],[idParroquia],[nombreContacto],[movilContacto],[parentezco],[app],[apf],[observacion])
					VALUES(@cedula,@nombre1,@nombre2,@apellido1,@apellido2,@nombres,@domicilio,@nacionalidad,@estadocivil,@teldomicilio,@telOficina,@email,@sexo,@paisNac,@fechaNac,@etnia,@discapacidad,@idParroquia,@nombreContacto,@movilContacto,@parentezco,@app,@apf,@observacion);
					set @idOut = (SELECT top 1 id from dbo.paciente ORDER by id DESC);
					INSERT INTO BD_SM.[dbo].[historialClinico]([idPaciente],[fecha],[menarca],[estado]) VALUES(@idOut,GETDATE(),GETDATE(),'1');
				END;
			ELSE
				BEGIN
					set @idOut = @id;
					UPDATE [dbo].[paciente] SET cedula = @cedula , nombre1 = @nombre1 , nombre2 = @nombre2 , apellido1 = @apellido1 , apellido2 = @apellido2 ,nombres = @nombres, domicilio = @domicilio , nacionalidad = @nacionalidad , estadoCivil = @estadocivil , telefonoDomicilio = @teldomicilio , telefonoOficina = @telOficina , email = @email , sexo = @sexo , paisNacimiento = @paisNac , fechaNacimiento = @fechaNac , etnia = @etnia , discapacidad = @discapacidad ,  idParroquia = @idParroquia ,  nombreContacto = @nombreContacto ,  movilContacto = @movilContacto ,  parentezco = @parentezco , app = @app , apf = @apf, observacion = @observacion where id = @id; 
				END;
		END;
END
GO
/****** Object:  StoredProcedure [dbo].[viewCaso]    Script Date: 03/09/2017 15:26:34 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE   PROCEDURE [dbo].[viewCaso]
@hc INT,
@trows int,
@inicio int
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
	) as r 
	WHERE r.r = 1 
	ORDER by fecha desc
	OFFSET @inicio ROWS FETCH NEXT @trows ROWS ONLY;
END
GO
