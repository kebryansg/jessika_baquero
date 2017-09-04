--Repositorio
https://github.com/kebryansg/jessika_baquero.git

--Catagolo - PacienteFTS
-- Campos
Nombres
Cedula

--Catagolo - ConsultaFTS
-- Campos
Diagnostico

-- 3/Sept/2017
CREATE OR ALTER VIEW getPaciente
AS
	SELECT id,cedula,nombre1,nombre2,apellido1,apellido2,domicilio,discapacidad,
	email,etnia,fechaNacimiento,idParroquia,nacionalidad,paisNacimiento,sexo,app,apf,observacion,
	telefonoDomicilio,telefonoOficina,nombreContacto,movilContacto,parentezco,estadoCivil
	from paciente; 
	
-- Borrar el campo cedula del catalogo de PacienteFTS	
alter table paciente alter COLUMN cedula NVARCHAR(10)
UPDATE paciente set cedula  = TRIM(cedula)
-- Agregar el campo cedula del catalogo de PacienteFTS