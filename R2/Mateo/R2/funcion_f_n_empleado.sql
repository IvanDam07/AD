CREATE OR REPLACE FUNCTION f_n_empleado (

    p_nDepartamento IN NUMBER
    
) RETURN NUMBER IS
    
    v_num_empleados NUMBER;
    
BEGIN  
    
        --Obtener el numero de empleados del departamento
        SELECT COUNT(*)
        INTO v_num_empleados
        FROM empleados
        WHERE dept_no = p_nDepartamento;
        
        RETURN v_num_empleados;
        
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0; --En caso de error devolver 0
END;
/
        
        