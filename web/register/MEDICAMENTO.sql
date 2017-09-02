/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Deivi
 * Created: 21-jun-2017
 */
DROP TABLE medicamentos
create table medicamentos (id int primary key IDENTITY(1,1), fecha date, hora time, notasEvolucion text,
prescripcionMedica text, estado int, idIngresos INT FOREIGN KEY REFERENCES INGRESOS(ID))

