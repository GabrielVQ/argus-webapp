app.controller('BLHouseController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL','$routeParams','servicioNumeroBLHouse',function($scope, $location, $http,$window, servicioNumeroBL, $routeParams, servicioNumeroBLHouse) {

    $scope.blNumOp =  $routeParams.bl;

    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
   // $scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    //$scope.numeroBLHouse= servicioNumeroBLHouse.numeroBLHouse;

    var d = new Date();
    var fechaIngreso = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear() + " a las " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);

    function parseFecha(date){
        var fecha = date.getDate()  + "/" + (date.getMonth()+1) + "/" + date.getFullYear() + " a las " + ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2);
        return fecha;
    }
    $scope.isActive = function(route) {
        return route === $location.path();

    }

    $scope.numeroOperacion = localStorage.getItem("token");

    $scope.newBLHouse= {
        "numeroOperacion": $scope.numeroOperacion,
        "numeroBLHouse":$scope.numeroBLHouse,
        "blMaster":"",
        "tipoHouse":"",
        "shipper":0,
        "clienteExtranjero":0,
        "notify":0,
        "ppcc":"",
        "moneda":"",
        "bultos":"",
        "carga":"",
        "kilos":"",
        "volumen":"",
        "flete":"",
        "almacenista":0,
        "preCarriage":"",
        "lugarRecepcion":"",
        "ciudadLlegada":0, //destino
        "tipoMovimiento":"",
        "contacto":"",
        "telefono":"",
        "fechaStacking":"",
        "observacion":"",
        "imoClase":"",
        "imoNumero":"",
    };


    var urlBase = 'http://localhost:8080/blmasters/numerooperacion/'+ $scope.blNumOp;

    $http.get(urlBase)
        .then(function(response) {
            $scope.BLMaster = response.data;
            console.log('bl que llega',$scope.BLMaster[0]);
            //$scope.idaux = $scope.BLMaster[0].id;
            //console.log('id: ',$scope.BLMaster[0].id);
            //$scope.newBLHouse.blMaster = $scope.BLMaster[0].id;
            //setea id del blmaster
            var token2 = $scope.BLMaster[0].id;
            localStorage.setItem("token2", token2);
            //setea numero blmaster para ser usado en las vistas
            var token4 = $scope.BLMaster[0].blmasterNumero;
            localStorage.setItem("token4", token4)
            //console.log('id dentro:',localStorage.getItem("token2"));
            console.log('bl numero dentro:',$scope.BLMaster[0].blmasterNumero);
        });
    $scope.idaux = localStorage.getItem("token2");
    $scope.numeroBLMaster = localStorage.getItem("token4");
/*
   function setNumeroBLHouse(){
        var urlBase2 = 'http://localhost:8080/blhouses/numerooperacion/'+$scope.numeroOperacion;
        console.log("ME EJECUTE")
        $http.get(urlBase2)
            .then(function(response) {

                if (response.data.length != 0){
                    servicioNumeroBLHouse.setNumeroBLHouse(response.data[response.data.length - 1].numeroBLHouse + 1);
                    var token = servicioNumeroBLHouse.numeroBLHouse;
                    localStorage.setItem("token3", token);
                    console.log('hay bl houses:', localStorage.getItem("token3"))
                }
                else{
                    var token2 = "1";
                    localStorage.setItem("token3", token2);
                    console.log('no hay bl houses', localStorage.getItem("token3"))
                }
            });
        }*/
        $scope.numeroBLHouse = localStorage.getItem("token3");
        console.log('numero bl house cabecera',localStorage.getItem("token3"))


    $scope.send = function(){

        $scope.newBLHouse.shipper= {"id":parseInt($scope.newBLHouse.shipper)};
        $scope.newBLHouse.clienteExtranjero= {"id":parseInt($scope.newBLHouse.clienteExtranjero)};
        $scope.newBLHouse.notify= {"id":parseInt($scope.newBLHouse.notify)};
        $scope.newBLHouse.almacenista= {"id":parseInt($scope.newBLHouse.almacenista)};
        $scope.newBLHouse.ciudadLlegada= {"id":parseInt($scope.newBLHouse.ciudadLlegada)};
        $scope.newBLHouse.blMaster= {"id":parseInt($scope.idaux)};
        $scope.newBLHouse.fechaStacking =  parseFecha($scope.newBLHouse.fechaStacking);
        $http.post("http://localhost:8080/blhouses",$scope.newBLHouse);
        $scope.mensaje = 'BL House generado con exito!';
        $window.alert($scope.mensaje);

        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    $scope.newDescripcion = {
        "numeroOperacion": $scope.numeroOperacion,
        "numeroBLHouse": $scope.numeroBLHouse,
        "blHouse":0,
        "contenedor":0,
        "markNumbers":"",
        "numerPackages":"",
        "groosWeight":"",
        "measurement":"",
        "descriptionGoods":""
    }

    var urlBaseCargament = 'http://localhost:8080/cargaments/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBaseCargament)
        .then(function(response) {
            $scope.cargamentsBYoperacion = response.data;
        });

    var urlBase1 = 'http://localhost:8080/blhouses/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBase1)
        .then(function(response) {
            $scope.BLHouseId = response.data[0].id;
            // console.log('status: ',response.status);
        });

    $scope.send2 = function(){

        $scope.newDescripcion.blHouse= {"id":parseInt($scope.BLHouseId)};
        $scope.newDescripcion.contenedor= {"id":parseInt($scope.newDescripcion.contenedor)};
        $http.post("http://localhost:8080/cargaments",$scope.newDescripcion);
        $scope.mensaje = 'Descripción agregada con exito!';
        $window.alert($scope.mensaje);

        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    $scope.newIngresos = {
        "facturar": "",
        "impuesto":"",
        "facturara": 0,
        "cobro":0,
        "llevarFormulario": "",
        "prepaid": 0,
        "collect":0,
        "blHouse":0

    }
    $scope.send3 = function(){

        $scope.newIngresos.blHouse= {"id":parseInt($scope.BLHouseId)};
        $scope.newIngresos.cobro= {"id":parseInt($scope.newIngresos.cobro)};
        $http.post("http://localhost:8080/ingresos",$scope.newIngresos);
        $scope.mensaje = 'Ingresos agregado con exito!';
        $window.alert($scope.mensaje);

        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }
   // console.log($scope.idaux);
    //peticion de servicios

    $http.get('http://localhost:8080/ciudades').then(function(response){  // campo: destino
        $scope.ciudades = response.data;
        //console.log($scope.ciudades);
    })

    $http.get('http://localhost:8080/navieras').then(function(response){
        $scope.navieras = response.data;
        //console.log($scope.navieras);
    })

    $http.get('http://localhost:8080/naves').then(function(response){
        $scope.naves = response.data;
        //console.log($scope.naves);
    })

    $http.get('http://localhost:8080/puertos').then(function(response){   //campos: pto origen y descarga
        $scope.puertos = response.data;
        //console.log($scope.puertos);
    })

    $http.get('http://localhost:8080/empresas').then(function(response){ // campo: agente
        $scope.empresas = response.data;
        //console.log($scope.puertos);
    })

    $http.get('http://localhost:8080/cobros').then(function(response){ // campo: agente
        $scope.cobros = response.data;
        //console.log($scope.puertos);
    })
}]);