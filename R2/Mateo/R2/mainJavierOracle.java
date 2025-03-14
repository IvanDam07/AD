public static void main(String[] args) {
            
        bbdd.abrirConexion();
        
        /******************************
         * PRUEBAS CON DEPARTAMENTO
         ******************************/
              
        /**
         * CONSULTA DE TODOS LOS REGISTROS
         */
        //Departamento.mostrarResultSet(Departamento.selectAll(bbdd));
              
        /**
         * CONSULTA POR CLAVE PRIMARIA
         */
        //Departamento departamento = new Departamento();        
        //departamento.selectById(bbdd,10);
        //System.out.println(departamento);
        
        /**
         * INSERCIÓN
         */
        //Departamento departamento = new Departamento(60,"TRANSPORTE","MADRID");
        //departamento.insertar(bbdd);
        
        /**
         * MODIFICACIÓN
         */
        //Departamento departamento = new Departamento(60,"TRANSPORTE","ZARAGOZA");
        //departamento.update(bbdd);
        
        /**
         * BORRADO
         */
        //Departamento.delete(bbdd, 60);
         
        
        /******************************
         * PRUEBAS CON EMPLEADO
         ******************************/
              
        /**
         * CONSULTA DE TODOS LOS REGISTROS
         */
        //Empleado.mostrarResultSet(Empleado.selectAll(bbdd));
              
        /**
         * CONSULTA POR CLAVE PRIMARIA
         */
        //Empleado empleado = new Empleado();        
        //empleado.selectById(bbdd,7369);
        //System.out.println(empleado);
        
        /**
         * INSERCIÓN
         */
        //Empleado empleado = new Empleado(7888, "GARCIA", "ANALISTA", 7566, "08/11/2024", 2000, 200.50, 10);
        //empleado.insertar(bbdd);
        
        /**
         * MODIFICACIÓN
         */
        //Empleado empleado = new Empleado(7888, "GARCIA", "VENDEDOR", 7566, "08/11/2024", 2000, 200.50, 10);
        //empleado.update(bbdd);
        
        /**
         * BORRADO
         */
        //Empleado.delete(bbdd, 7888);
        
        
        /************************************************
         * PRUEBAS LLAMADAS A PROCEDIMIENTOS Y FUNCIONES
         ************************************************/
        //System.out.println("El nombre del departamento es:" + Departamento.pNombreDepart(bbdd, 10));      
        //System.out.println("El nombre del departamento es:" + Departamento.fNombreDepart(bbdd, 10));
        
        
        /************************************************
         * PRUEBAS MOVIMIENTO POR UN RESULTSET
         ************************************************/
        // En OperacionesBBDD.executeQuery con: preparedStatement = conexion.prepareStatement(querySQL); en el método executeQuery
        
        //Optional<ResultSet> rs = Departamento.selectAll(bbdd);      
        //Departamento.mostrarResultSet(rs);
        //try {
        //    rs.get().beforeFirst();   //Al intentar mover el cursor al principio para volver a mostrar el ResultSet daría error
        //} catch (SQLException ex) {
        //    Logger.getLogger(BbddjdbcOracle.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //Departamento.mostrarResultSet(rs);
        
        // Cambiando en OperacionesBBDD.executeQuery: 
        // preparedStatement = conexion.prepareStatement(querySQL); en el método executeQuery
        // por
        //preparedStatement = conexion.prepareStatement(querySQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        
        //Optional<ResultSet> rs = Departamento.selectAll(bbdd);      
        //Departamento.mostrarResultSet(rs);
        //try {
        //    rs.get().beforeFirst(); //Al intentar mover el cursor al principio para volver a mostrar el ResultSet ya NO daría error
        //} catch (SQLException ex) {
        //    Logger.getLogger(BbddjdbcOracle.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //Departamento.mostrarResultSet(rs);
               
        
        /************************************************
         * PRUEBAS SENTENCIAS DE DESCRIPCIÓN
         ************************************************/
        //bbdd.obtenerInformacionDeConexion();
        //bbdd.obtenerInformacionDeLasTablas();
        //bbdd.obtenerInformacionDeLasColumnas("DEPARTAMENTOS");
        //bbdd.obtenerInformacionDelResultSet(Departamento.selectAll(bbdd));
        //bbdd.obtenerNumeroFilasDevueltas(Departamento.selectAll(bbdd));

        
        /************************************************
         * PRUEBAS MODIFICACIÓN UTILIZANDO EL RESUSLTSET
         ************************************************/
        
        //if (bbdd.obtenerInformacionOperacionesResultSet()){
        //     rs = Departamento.selectAll(bbdd);
        
        //    try {
        //        rs.get().beforeFirst();
        //        while(rs.get().next()) {
        //            rs.get().updateString("loc", "SEVILLA");
        //            rs.get().updateRow();
        //        } 
        //    } catch (SQLException ex) {
        //        Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        //    }
        //}
                   
        bbdd.cerrarConexion();       
    }   