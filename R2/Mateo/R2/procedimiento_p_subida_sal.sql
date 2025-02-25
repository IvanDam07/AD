CREATE PROCEDURE p_subida_sal (
    p_nDepartamento IN NUMBER,
    p_subida IN NUMBER
) IS
    v_departamento_existente NUMBER;

BEGIN
    --Verificamos que el departamento existe
    SELECT COUNT(*)
    INTO v_departamento_existente
    FROM departamentos
    WHERE dept_no = p_nDepartamento;
    
    IF v_departamento_existente > 0 THEN
        
        --Actualizo
        UPDATE empleados
        SET salario = salario + p_subida
        WHERE dept_no = p_nDepartamento;
        
        DBMS_OUTPUT.PUT_LINE('Salario actualizado');
    
    ELSE
        
        DBMS_OUTPUT.PUT_LINE('El número de departamento no existe');
    
    END IF;
END;
/
    