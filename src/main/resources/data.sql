

INSERT INTO public.ciudad(id, nombre) VALUES (1, 'Valparaiso'),(2,'Santiago'),(3,'Concepcion');

INSERT INTO public.moneda VALUES (1,'pesos chilenos',0),(2,'dolar USA',600);

INSERT INTO public.container VALUES (1,'7','123456','asdfghSello','arghasuSigla','20 Reefer'),(2,'6','123456','qwertySello','arguscontainerSigla','40 Reefer');

INSERT INTO public.nave VALUES (1,'SaintQueen'),(2,'Titanic2.0');

INSERT INTO public.navieras VALUES (1,'Naviera 1'),(2,'Naviera 2');

INSERT INTO public.puerto VALUES (1,'direccion 1','puerto 1'),(2,'direccion 2','puerto 2');

INSERT INTO public.empresa VALUES (1,1,'Luis Videla','av. central park 123','almacenista','82596935','chilena','acme','ACME CORPORATION');

INSERT INTO public.bodega VALUES (1,'Bodega_1','puerto Stanley',1),(2,'Bodega_2','puerto ABCD',1);

INSERT INTO public.blmaster VALUES (1,'agencia aduana 1','eavendano','1224660',TRUE,'2010-12-03','2010-12-03','2011-01-04',123456,21,1,'coloader','importacion',1,1,1,2,1,2),(2,'agencia aduana 2','eavendano','1224660',TRUE,'2010-12-03','2010-12-03','2011-01-04',123456,21,2,'coloader','importacion',1,2,1,2,1,2);

INSERT INTO public.blhouse VALUES (1, 1, 1, 2, null, '4', '5', '6', '7', '8', 1, '2', null, null, null, null, null, null, null, null, null, null, null, null, null, null, 1);

INSERT INTO public.confirmacion_reserva VALUES(1,FALSE,'pendiente',1,1);

INSERT INTO public.cobro VALUES (1,'ocean freights');

INSERT INTO public.cargament VALUES (1,'dice contener ... test testtesttes ttesttesttesttesttestt esttesttesttest testtesttestt esttesttestt esttesttestt esttesttet','456 kgs','container STD','30 cbm',2,3,1,1);