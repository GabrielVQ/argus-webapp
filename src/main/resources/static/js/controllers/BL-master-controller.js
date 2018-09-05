app.controller('BLMasterController', ['$scope', '$location', '$http','$window','servicioNumeroBL','$routeParams', function($scope, $location, $http,$window, servicioNumeroBL,$routeParams) {

    console.log("testing scope main controller: ",$scope.title);
    $http.get('http://localhost:8080/users/'+ $scope.user_id).then(function(response){ 
        $scope.nombre_usuario = response.data.name;
        $scope.creador = $scope.nombre_usuario;
    })
    $scope.nombre_usuario = localStorage.getItem("token5");
    console.log("nombre de usuario", $scope.nombre_usuario)
    $scope.numeroOperacion = "";
    //parse fecha formato: dd/mm/yyyy a las hh:mm
    var d = new Date();
    var fechaIngreso = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear() + " a las " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);
    
    function parseFecha(date){
        var fecha = date.getDate()  + "/" + (date.getMonth()+1) + "/" + date.getFullYear() + " a las " + ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2);
        return fecha;
    }
    $scope.isActive = function(route) {
        return route === $location.path();

    }
    $scope.newBLMaster= {

        "numeroOperacion": $scope.numeroOperacion,
        "blocked": false,
        "servicio": "",
        "nReserva": "",
        "nViaje": "",
        "agenteCreador": "eavendano", // $scope.creador
        "tipoNegocio": "",
        "destino": "",
        "fechaIngreso": fechaIngreso,
        "fechaLlegada": "",
        "fechaZarpe": "",
        "nave":0,
        "naviera":0,
        "blmasterNumero":"",
        "agenteAduana": ""
    };

    $scope.send = function(){
        console.log('fechaaaaa original:',$scope.newBLMaster.fechaZarpe);
        //$scope.numeroOperacion += 1;
        $scope.newBLMaster.nave= {"id":parseInt($scope.newBLMaster.nave)};
        $scope.newBLMaster.naviera= {"id":parseInt($scope.newBLMaster.naviera)};
        $scope.newBLMaster.puertoOrigen= {"id":parseInt($scope.newBLMaster.puertoOrigen)};
        $scope.newBLMaster.puertoDescarga= {"id":parseInt($scope.newBLMaster.puertoDescarga)};
        $scope.newBLMaster.destino= {"id":parseInt($scope.newBLMaster.destino)};
        //$scope.newBLMaster.agenteAduana = {"id":parseInt($scope.newBLMaster.agenteAduana)};
        $scope.newBLMaster.fechaZarpe =  parseFecha($scope.newBLMaster.fechaZarpe);
        $scope.newBLMaster.fechaLlegada =  parseFecha($scope.newBLMaster.fechaLlegada);
        //console.log(' fecha de hoy: ',$scope.fecha);
        //console.log('fecha zarpeeee: ',$scope.fechaZarpeAux);

        //$scope.newBLMaster.fechaZarpe = $scope.fechaZarpeAux;
        console.log('fecha zarpeeee final : ',$scope.newBLMaster.fechaZarpe);
        console.log('BL : ',$scope.newBLMaster);
        $http.post("http://localhost:8080/blmasters",$scope.newBLMaster);
        //console.log($scope.newBLMaster);
        $scope.blnum = $scope.newBLMaster.numeroOperacion; 
        $scope.mensaje = 'BL Generada con exito!';
        $window.alert($scope.mensaje);
        //setNumeroBLHouse();


        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    /*$scope.ciudades = function() {
        $http.get('http://localhost:8080/ciudades').then(function (response) {
            $scope.ciudades = response.data;});
            //console.log($scope.ciudades[0]);
    }*/


    $scope.newContenedor={
        "tipo":"",
        "sigla":"" ,
        "numeroContenedor":"",
        "selloEmpresa":"",
        "selloCliente":"",
        "selloAduana":"",
        "descripcionLarga":"",
        "digito":""
    }

   // var token = "xxx";

   // localStorage.setItem("token", token);
    //console.log('data scope4:', localStorage.getItem("token"));
    $scope.numeroOperacion = localStorage.getItem("token");
    //console.log("data numer", $scope.numeroOperacion)
    function  setnumeroOperacion(data){
        /*
        console.log("data length", data.length)
        if (data.length === 0) {
            var token = "1"
            localStorage.setItem("token", token);
            console.log("data length", data.length)
            return
        }*/
        servicioNumeroBL.setNumeroBLMaster(data[data.length - 1].numeroOperacion + 1);
        //console.log('data funcion:',servicioNumeroBL.numeroBL);
        //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
        $scope.newBLMaster.numeroOperacion = servicioNumeroBL.numeroBL;
        var token = servicioNumeroBL.numeroBL;
        localStorage.setItem("token", token);
        //console.log('data scope2:', $scope.newBLMaster.numeroOperacion);
    }
    //console.log('data scope3:', $scope.newBLMaster.numeroOperacion);

        var urlBase2 = 'http://localhost:8080/blhouses/numerooperacion/'+$scope.numeroOperacion;

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

    $scope.selectedItemChanged = function(){
        var urlBase5 = 'http://localhost:8080/empresas/nombreabrev/'+ $scope.newBLMaster.agenteAduana;
        $http.get(urlBase5).then(function(response){  // campo: destino
        $scope.razon_social = response.data[0].razon_social;
    })
    }

    $scope.agregarContenedor = function(){
        $scope.newContenedor.sigla = $scope.newContenedor.sigla.toUpperCase();
        $scope.newContenedor.selloEmpresa = $scope.newContenedor.selloEmpresa.toUpperCase();
        $scope.newContenedor.selloAduana = $scope.newContenedor.selloEmpresa.toUpperCase();
        $scope.newContenedor.selloCliente = $scope.newContenedor.selloEmpresa.toUpperCase();
        $scope.newContenedor.descripcionLarga = $scope.newContenedor.sigla+'-'+$scope.newContenedor.numeroContenedor+$scope.newContenedor.digito+"\n"+$scope.newContenedor.selloEmpresa+'\n'+$scope.newContenedor.selloCliente+'\n'+$scope.newContenedor.selloAduana;

        $http.post("http://localhost:8080/containers",$scope.newContenedor);
        $scope.mensaje = 'Conetenedor agregado con exito!';
        $window.alert($scope.mensaje);
    }



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

    $http.get('http://localhost:8080/blmasters').then(function(response){
        $scope.BLMaster = response.data;
        //console.log($scope.BLMaster[0].numeroOperacion);
        //console.log($scope.BLMaster.pop().numeroOperacion);
        //console.log($scope.BLMaster);
        //servicioNumeroBL.setNumeroBLMaster($scope.BLMaster.pop().numeroOperacion + 1);
        //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
        //console.log('numero opera1:', servicioNumeroBL.numeroBL2());
        setnumeroOperacion($scope.BLMaster)
    })


    //console.log('data servicio:', servicioNumeroBL.numeroBL);
    //console.log('data scope:', $scope.BLMaster);
        }]);