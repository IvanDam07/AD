--------------------------------------------------------
-- Archivo creado  - lunes-noviembre-11-2024   
--------------------------------------------------------
DROP TABLE "DEPARTAMENTOS";
DROP TABLE "DEPARTAMENTOS2";
DROP TABLE "EMPLEADOS";
DROP PROCEDURE "P_NOMBRE_DEPART";
DROP FUNCTION "F_NOMBRE_DEPART";
--------------------------------------------------------
--  DDL for Table DEPARTAMENTOS
--------------------------------------------------------

  CREATE TABLE "DEPARTAMENTOS" 
   (	"DEPT_NO" NUMBER(2,0), 
	"DNOMBRE" VARCHAR2(15), 
	"LOC" VARCHAR2(15)
   ) ;
--------------------------------------------------------
--  DDL for Table DEPARTAMENTOS2
--------------------------------------------------------

  CREATE TABLE "DEPARTAMENTOS2" 
   (	"DEPT_NO" NUMBER(2,0), 
	"DNOMBRE" VARCHAR2(15), 
	"LOC" VARCHAR2(15)
   ) ;
--------------------------------------------------------
--  DDL for Table EMPLEADOS
--------------------------------------------------------

  CREATE TABLE "EMPLEADOS" 
   (	"EMP_NO" NUMBER(4,0), 
	"APELLIDO" VARCHAR2(10), 
	"OFICIO" VARCHAR2(10), 
	"DIR" NUMBER(4,0), 
	"FECHA_ALT" DATE, 
	"SALARIO" NUMBER(6,2), 
	"COMISION" NUMBER(6,2), 
	"DEPT_NO" NUMBER(2,0)
   ) ;
REM INSERTING into DEPARTAMENTOS
SET DEFINE OFF;
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('10','CONTABILIDAD','SEVILLA');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('20','INVESTIGACIÓN','MADRID');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('30','VENTAS','BARCELONA');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('40','PRODUCCIÓN','BILBAO');
REM INSERTING into DEPARTAMENTOS2
SET DEFINE OFF;
REM INSERTING into EMPLEADOS
SET DEFINE OFF;
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7369','SANCHEZ','EMPLEADO','7902',to_date('17/12/90','DD/MM/RR'),'1040',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7499','ARROYO','VENDEDOR','7698',to_date('20/02/90','DD/MM/RR'),'1500','390','30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7521','SALA','VENDEDOR','7698',to_date('22/02/91','DD/MM/RR'),'1625','650','30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7566','JIMENEZ','DIRECTOR','7839',to_date('02/04/91','DD/MM/RR'),'2900',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7654','MARTIN','VENDEDOR','7698',to_date('29/09/91','DD/MM/RR'),'1600','1020','30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7698','NEGRO','DIRECTOR','7839',to_date('01/05/91','DD/MM/RR'),'3005',null,'30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7782','CEREZO','DIRECTOR','7839',to_date('09/06/91','DD/MM/RR'),'2885',null,'10');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7788','GIL','ANALISTA','7566',to_date('09/11/91','DD/MM/RR'),'3000',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7839','REY','PRESIDENTE',null,to_date('17/11/91','DD/MM/RR'),'4100',null,'10');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7844','TOVAR','VENDEDOR','7698',to_date('08/09/91','DD/MM/RR'),'1350','0','30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7876','ALONSO','EMPLEADO','7788',to_date('23/09/91','DD/MM/RR'),'1430',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7900','JIMENO','EMPLEADO','7698',to_date('03/12/91','DD/MM/RR'),'1335',null,'30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7902','FERNANDEZ','ANALISTA','7566',to_date('03/12/91','DD/MM/RR'),'3000',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7934','MUÑOZ','EMPLEADO','7782',to_date('23/01/92','DD/MM/RR'),'1690',null,'10');
--------------------------------------------------------
--  DDL for Procedure P_NOMBRE_DEPART
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "P_NOMBRE_DEPART" (
ndepart IN NUMBER,
nombre_depart OUT VARCHAR2)
AS
BEGIN

SELECT dnombre INTO nombre_depart
FROM departamentos
WHERE dept_no = ndepart;

END;

/
--------------------------------------------------------
--  DDL for Function F_NOMBRE_DEPART
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "F_NOMBRE_DEPART" (
ndepart NUMBER) RETURN VARCHAR2
IS
nombre_depart departamentos.dnombre%TYPE;
BEGIN
SELECT dnombre INTO nombre_depart FROM
departamentos WHERE dept_no = ndepart;
RETURN (nombre_depart);
END;

/
--------------------------------------------------------
--  Constraints for Table DEPARTAMENTOS
--------------------------------------------------------

  ALTER TABLE "DEPARTAMENTOS" MODIFY ("DEPT_NO" NOT NULL ENABLE);
  ALTER TABLE "DEPARTAMENTOS" ADD PRIMARY KEY ("DEPT_NO")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table EMPLEADOS
--------------------------------------------------------

  ALTER TABLE "EMPLEADOS" MODIFY ("EMP_NO" NOT NULL ENABLE);
  ALTER TABLE "EMPLEADOS" ADD PRIMARY KEY ("EMP_NO")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table DEPARTAMENTOS2
--------------------------------------------------------

  ALTER TABLE "DEPARTAMENTOS2" MODIFY ("DEPT_NO" NOT NULL ENABLE);
  ALTER TABLE "DEPARTAMENTOS2" ADD PRIMARY KEY ("DEPT_NO")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLEADOS
--------------------------------------------------------

  ALTER TABLE "EMPLEADOS" ADD CONSTRAINT "FK_DEP" FOREIGN KEY ("DEPT_NO")
	  REFERENCES "DEPARTAMENTOS" ("DEPT_NO") ENABLE;
