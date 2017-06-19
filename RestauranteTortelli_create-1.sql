-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-06-12 20:51:39.968

-- tables
-- Table: empleado
CREATE TABLE "empleado" (
    "tipoempleado" varchar(10)  NOT NULL,
    "idempleado" int  NOT NULL,
    "cargo" varchar(15)  NOT NULL,
    "horario" varchar(15)  NOT NULL,
    "foto" text  NOT NULL,
    "direccion" varchar(20)  NOT NULL,
    "telefono" varchar(20)  NOT NULL,
    "correo" varchar(15)  NOT NULL,
    "pin" varchar(20)  NOT NULL,
    "nombre" varchar(50)  NOT NULL,
    "apellido" varchar(50)  NOT NULL,
    "tipo_documento" varchar(50)  NOT NULL,
    CONSTRAINT "empleado_pk" PRIMARY KEY ("idempleado")
);

COMMENT ON TABLE "empleado" IS 'Tabla Empleado';
COMMENT ON COLUMN "empleado"."tipoempleado" IS 'Tipo de empleado ';
COMMENT ON COLUMN "empleado"."idempleado" IS 'identificador de la tabla empleado(documento de identificación),llave primaria';
COMMENT ON COLUMN "empleado"."cargo" IS 'Cargo que ocupa';
COMMENT ON COLUMN "empleado"."foto" IS 'Fotografía del empleado';
COMMENT ON COLUMN "empleado"."direccion" IS 'Dirección del empleado';
COMMENT ON COLUMN "empleado"."telefono" IS 'Teléfono del empleado';
COMMENT ON COLUMN "empleado"."correo" IS 'correo-electrónico del empleado';
COMMENT ON COLUMN "empleado"."pin" IS 'Contraseña que tiene el usuario';

-- Table: factura
CREATE TABLE "factura" (
    "horapago" time  NOT NULL,
    "facturaid" int  NOT NULL,
    "estadofactura" varchar(10)  NOT NULL,
    "fechafactura" date  NOT NULL,
    "totalfactura" int  NOT NULL,
    "cedulacliente" int  NOT NULL,
    "pedido_idpedido" int  NOT NULL,
    "iva" int  NOT NULL,
    CONSTRAINT "factura_pk" PRIMARY KEY ("facturaid")
);

COMMENT ON COLUMN "factura"."horapago" IS 'Hora que realiza el pago';
COMMENT ON COLUMN "factura"."facturaid" IS 'Identificador de factura ,llave primaria';
COMMENT ON COLUMN "factura"."estadofactura" IS 'Cómo desea pagar el cliente';
COMMENT ON COLUMN "factura"."fechafactura" IS 'fecha de la realización de la factura';
COMMENT ON COLUMN "factura"."totalfactura" IS 'valor a pagar neto';
COMMENT ON COLUMN "factura"."cedulacliente" IS 'cédula del cliente para realizar la factura';
COMMENT ON COLUMN "factura"."pedido_idpedido" IS 'Llave foránea haciendo referencia del identificador de productos que se están facturando ';
COMMENT ON COLUMN "factura"."iva" IS 'valor impuesto';

-- Table: mesa
CREATE TABLE "mesa" (
    "numeromesa" int  NOT NULL,
    "puestos" int  NOT NULL,
    CONSTRAINT "mesa_pk" PRIMARY KEY ("numeromesa")
);

COMMENT ON TABLE "mesa" IS 'Entidad Mesa:Se registra todas las mesas del restaurante.';
COMMENT ON COLUMN "mesa"."numeromesa" IS 'identificador de mesa en el restaurante,llave primaria';
COMMENT ON COLUMN "mesa"."puestos" IS 'Puestos Disponibles';

-- Table: pago
CREATE TABLE "pago" (
    "id_pago" int  NOT NULL,
    "nombre_pago" varchar(50)  NOT NULL,
    "valor_pago" int  NOT NULL,
    CONSTRAINT "pago_pk" PRIMARY KEY ("id_pago")
);

COMMENT ON TABLE "pago" IS 'Entidad Pago:Se registran todos los pagos que se realizan .';
COMMENT ON COLUMN "pago"."id_pago" IS 'identificador del pago ,llave principal ';
COMMENT ON COLUMN "pago"."nombre_pago" IS 'Nombre del que realiza la cancelación ';
COMMENT ON COLUMN "pago"."valor_pago" IS 'total pagado en pesos';

-- Table: pago_factura
CREATE TABLE "pago_factura" (
    "factura_facturaid" int  NOT NULL,
    "pago_id_pago" int  NOT NULL,
    "valor_pago" int  NOT NULL,
    "id" int  NOT NULL,
    "cantidad" int  NOT NULL,
    CONSTRAINT "pago_factura_pk" PRIMARY KEY ("id")
);

COMMENT ON TABLE "pago_factura" IS 'Entidad-relación Pago-factura';
COMMENT ON COLUMN "pago_factura"."factura_facturaid" IS 'Llave foránea hace referencia al atributo facturaid  tabla factura';

-- Table: pedido
CREATE TABLE "pedido" (
    "tipopedido" varchar(10)  NOT NULL,
    "idpedido" int  NOT NULL,
    "horapedido" time  NOT NULL,
    "horaentrega" time  NOT NULL,
    "mesa_numeromesa" int  NOT NULL,
    "empleado_idempleado" int  NOT NULL,
    CONSTRAINT "pedido_pk" PRIMARY KEY ("idpedido")
);

COMMENT ON TABLE "pedido" IS 'Entidad Pedido :Cuenta con 7 atributos y la llave principal es la identificación del producto(idpedido)';
COMMENT ON COLUMN "pedido"."tipopedido" IS 'Tipo de pedido contando con 2 dos alternativas';
COMMENT ON COLUMN "pedido"."idpedido" IS 'identificación del pedido ,llave primaria';
COMMENT ON COLUMN "pedido"."horapedido" IS 'Hora en que se ordenó el pedido';
COMMENT ON COLUMN "pedido"."horaentrega" IS 'Hora que se entregó el pedido';
COMMENT ON COLUMN "pedido"."mesa_numeromesa" IS 'número de mesa que realiza el pedido';
COMMENT ON COLUMN "pedido"."empleado_idempleado" IS 'Llave foránea que hace referencia al empleado que registró el pedido';

-- Table: producto
CREATE TABLE "producto" (
    "nombreproducto" varchar(10)  NOT NULL,
    "foto" varchar  NOT NULL,
    "precio" int  NOT NULL,
    "descripcion" varchar(40)  NOT NULL,
    "categoria" varchar(15)  NOT NULL,
    "productoid" int  NOT NULL,
    CONSTRAINT "producto_pk" PRIMARY KEY ("productoid")
);

COMMENT ON TABLE "producto" IS 'Entidad Producto :aquí se encontrarán todos los  articulos de venta que tiene el restaurante  cuenta con 6 atributos, la llave primaria es el  identificador de producto(Productoid)';
COMMENT ON COLUMN "producto"."nombreproducto" IS 'Nombre del producto';
COMMENT ON COLUMN "producto"."foto" IS 'Imagen del producto';
COMMENT ON COLUMN "producto"."precio" IS 'Valor del producto';
COMMENT ON COLUMN "producto"."descripcion" IS 'Breve descripción del producto';
COMMENT ON COLUMN "producto"."categoria" IS 'Asignación de categoría del producto';
COMMENT ON COLUMN "producto"."productoid" IS 'LLave primaria de la Tabla producto';

-- Table: producto_pedido
CREATE TABLE "producto_pedido" (
    "producto_productoid" int  NOT NULL,
    "pedido_idpedido" int  NOT NULL,
    "cantidad" int  NOT NULL,
    CONSTRAINT "producto_pedido_pk" PRIMARY KEY ("producto_productoid","pedido_idpedido")
);

COMMENT ON TABLE "producto_pedido" IS 'Entidad Relación pedido-producto';
COMMENT ON COLUMN "producto_pedido"."producto_productoid" IS 'Llave producto para hacer referencia de los productos que se realizan en un pedido.';
COMMENT ON COLUMN "producto_pedido"."pedido_idpedido" IS 'Hace referencia al atributo  identificador del pedido de la tabla pedido.';
COMMENT ON COLUMN "producto_pedido"."cantidad" IS 'Cantidad solicitada del producto';

-- Table: productos_factura
CREATE TABLE "productos_factura" (
    "nombre_producto" varchar(30)  NOT NULL,
    "id_producto" int  NOT NULL,
    "precio_producto" int  NOT NULL,
    "factura_facturaid" int  NOT NULL,
    CONSTRAINT "productos_factura_pk" PRIMARY KEY ("id_producto","factura_facturaid")
);

-- foreign keys
-- Reference: Producto_Pedido_pedido (table: producto_pedido)
ALTER TABLE "producto_pedido" ADD CONSTRAINT "Producto_Pedido_pedido"
    FOREIGN KEY ("pedido_idpedido")
    REFERENCES "pedido" ("idpedido")  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Producto_Pedido_producto (table: producto_pedido)
ALTER TABLE "producto_pedido" ADD CONSTRAINT "Producto_Pedido_producto"
    FOREIGN KEY ("producto_productoid")
    REFERENCES "producto" ("productoid")  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

COMMENT ON CONSTRAINT "Producto_Pedido_producto" ON "producto_pedido" IS 'Entidad relación Producto-Pedido';

-- Reference: factura_pedido (table: factura)
ALTER TABLE "factura" ADD CONSTRAINT "factura_pedido"
    FOREIGN KEY ("pedido_idpedido")
    REFERENCES "pedido" ("idpedido")  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: pago_factura_factura (table: pago_factura)
ALTER TABLE "pago_factura" ADD CONSTRAINT "pago_factura_factura"
    FOREIGN KEY ("factura_facturaid")
    REFERENCES "factura" ("facturaid")  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

COMMENT ON CONSTRAINT "pago_factura_factura" ON "pago_factura" IS 'Relación entre la factura y pago ';

-- Reference: pago_factura_pago (table: pago_factura)
ALTER TABLE "pago_factura" ADD CONSTRAINT "pago_factura_pago"
    FOREIGN KEY ("pago_id_pago")
    REFERENCES "pago" ("id_pago")  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: pedido_empleado (table: pedido)
ALTER TABLE "pedido" ADD CONSTRAINT "pedido_empleado"
    FOREIGN KEY ("empleado_idempleado")
    REFERENCES "empleado" ("idempleado")  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

COMMENT ON CONSTRAINT "pedido_empleado" ON "pedido" IS 'Relación entre empleado y pedido,un empleado puede hacer muchos pedidos';

-- Reference: pedido_mesa (table: pedido)
ALTER TABLE "pedido" ADD CONSTRAINT "pedido_mesa"
    FOREIGN KEY ("mesa_numeromesa")
    REFERENCES "mesa" ("numeromesa")  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

COMMENT ON CONSTRAINT "pedido_mesa" ON "pedido" IS 'Cada mesa del restaurante puede realizar un pedido ';

-- Reference: productos_factura_factura (table: productos_factura)
ALTER TABLE "productos_factura" ADD CONSTRAINT "productos_factura_factura"
    FOREIGN KEY ("factura_facturaid")
    REFERENCES "factura" ("facturaid")  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

