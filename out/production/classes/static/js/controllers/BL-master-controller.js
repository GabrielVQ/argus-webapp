app.controller('BLMasterController', ['$scope', '$location', '$http','$window', function($scope, $location, $http,$window) {

    $scope.numeroOperacion = 3;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    var d = new Date();

    var datestring = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear() + " a las " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);
    console.log(' fecha de hoy: ',$scope.fecha);

    console.log(' fecha de hoy: ',datestring);

    //$scope.ciudades;
   // var list = ciuda.map(x => x.id);
    $scope.fechaZarpeAux = '';

    $scope.isActive = function(route) {
        return route === $location.path();

    }
    $scope.newBLMaster= {

        "numeroOperacion": "ARG00"+$scope.numeroOperacion,
        "blocked": false,
        "servicio": "",
        "nReserva": "",
        "nViaje": "",
        "agenteCreador": "eavendano", // $scope.creador
        "tipoNegocio": "",
        "destino": "Conce",
        "fechaIngreso": datestring,
        "fechaLlegada": "12/07/2018 a las 04:00",
        "fechaZarpe": "25/07/2018 a las 04:00",
        "nave":0,
        "naviera":0
    };

    $scope.send = function(){
        
        //$scope.numeroOperacion += 1;
        $scope.newBLMaster.nave= {"id":parseInt($scope.newBLMaster.nave)};
        $scope.newBLMaster.naviera= {"id":parseInt($scope.newBLMaster.naviera)};
        $scope.newBLMaster.puertoOrigen= {"id":parseInt($scope.newBLMaster.puertoOrigen)};
        $scope.newBLMaster.puertoDescarga= {"id":parseInt($scope.newBLMaster.puertoDescarga)};

        $scope.fechaZarpeAux =  $scope.fechaZarpeAux.getDate()  + "/" + ($scope.fechaZarpeAux.getMonth()+1) + "/" +
        $scope.fechaZarpeAux.getFullYear() + " a las " + ("0" + $scope.fechaZarpeAux.getHours()).slice(-2) + ":" + ("0" + $scope.fechaZarpeAux.getMinutes()).slice(-2);
        console.log(' fecha de hoy: ',$scope.fecha);
        console.log('fecha zarpeeee: ',$scope.fechaZarpeAux);

        $scope.newBLMaster.fechaZarpe = $scope.fechaZarpeAux;
        $http.post("http://localhost:8080/blmasters",$scope.newBLMaster);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'BL Generada con exito!';
        $window.alert($scope.mensaje);



        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    /*$scope.ciudades = function() {
        $http.get('http://localhost:8080/ciudades').then(function (response) {
            $scope.ciudades = response.data;});
            //console.log($scope.ciudades[0]);
    }*/


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





}]);