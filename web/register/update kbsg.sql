create or ALTER PROCEDURE login
	@nick   NCHAR(10),
	@pass   NVARCHAR(50),
	@user_name NVARCHAR(100) OUTPUT
AS
BEGIN
	SET NOCOUNT ON;
	declare @total int;
	set @total = (select count(*) from usuario where nick = @nick and cast(DECRYPTBYPASSPHRASE('encriptarClave',clave) As VARCHAR(8000)) = @pass);
	if @total > 0
		BEGIN
			set @user_name = (SELECT UPPER(CONCAT(m.nombre1,' ',m.apellidos1)) from dbo.medico m where m.cedula = @nick);
			select u.*,r.val from usuario u inner join rol r on r.id = u.idRol where u.nick = @nick and cast(DECRYPTBYPASSPHRASE('encriptarClave',u.clave) As VARCHAR(8000)) = @pass;
		END 
END

ALTER TABLE BD_SM.dbo.usuario DROP CONSTRAINT FK_usuario_rol go