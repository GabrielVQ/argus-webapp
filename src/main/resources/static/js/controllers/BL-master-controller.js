app.controller('BLController', ['$scope', '$location', '$http','$window', function($scope, $location, $http,$window) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = 3;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    //$scope.ciudades;
   // var list = ciuda.map(x => x.id);


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
        "fechaIngreso": "10/05/2018 a las 10:46",
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

        $http.post("http://localhost:8080/blmasters",$scope.newBLMaster);
        console.log($scope.newBLMaster);
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
        console.log($scope.ciudades);
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