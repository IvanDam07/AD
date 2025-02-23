-----------------------------------------------------
--NOMBRE Y APELLIDOS: IVÁN SOBRINO CALZADO
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------


-----------------------------------------------------
--EJERCICIO 1:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------

a) /clientes/clien/nombre/text()
-----------------------------------------------------
b)/detallefacturas/factura[@numero=11]
-----------------------------------------------------
c)/detallefacturas/factura[codigo="FACT11"]/(codigo,referencia)
-----------------------------------------------------
d) /detallefacturas/format-number(sum(factura/producto/unidades),'#.00')
-----------------------------------------------------
e)/clientes/clien[contains(tlf,'91')]
-----------------------------------------------------
f)format-number(round(avg(/detallefacturas/factura/producto/unidades)),'#')
-----------------------------------------------------




-----------------------------------------------------
--EJERCICIO 2:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------

a)update insert
    <producto descuento="0.13">
        <codigo>5</codigo>
        <unidades>6</unidades>
    </producto>
into /detallefactura/factura[codigo="FACT10"]
-----------------------------------------------------
b)update value
    /detallefacturas/factura[codigo="FACT10" and codigo="2"]/unidades
with
    "33"
-----------------------------------------------------
c) update value
    /detallefacturas/factura[codigo="FACT10"]/codigo
with
    "FACTMODIF10"
-----------------------------------------------------
d)update delete /detallefacturas/factura[codigo="FACT1"]/producto[codigo="5"]
-----------------------------------------------------
e) update rename
    /detallefacturas/factura/referencia
as "refer"



-----------------------------------------------------
--EJERCICIO 3:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------
private static void mostrarClientes() {
        consulta("string(/clientes)");
}

private static void consulta(String textConsulta) {

        try {
            XQPreparedExpression consulta;
            XQResultSequence resultado;

            consulta = con.prepareExpression(textConsulta);
            resultado = consulta.executeQuery();

            XQResultItem r_item;
            while (resultado.next()) {
                r_item = (XQResultItem) resultado.getItem();
                System.out.println(r_item.getItemAsString(null));
            }
        } catch (XQException ex) {
            Logger.getLogger(ExamenR5.class.getName()).log(Level.SEVERE, null, ex);
        }
}


-----------------------------------------------------
--EJERCICIO 4:
--NOTA:
--OBSERVACIONES:
-----------------------------------------------------
private static void eliminarProductos() {
        modificacion("update delete /productos/product[@categoria='A' and @pvp<200]");
}

private static void modificacion(String textoDML) {
        try {
            XQExpression expresion;

            expresion = con.createExpression();

            expresion.executeCommand(textoDML);
        } catch (XQException ex) {
            Logger.getLogger(ExamenR5.class.getName()).log(Level.SEVERE, null, ex);
        }
}    
-----------------------------------------------------
