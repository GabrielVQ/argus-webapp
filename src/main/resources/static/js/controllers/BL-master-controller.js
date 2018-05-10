app.controller('BLController', ['$scope', '$location', '$http', function($scope, $location, $http) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = 1452;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();
    //$scope.ciudades;
   // var list = ciuda.map(x => x.id);


    $scope.isActive = function(route) {
        return route === $location.path();
    }
    $scope.newBLMaster= {
        blocked:false,
        servicio:'maritimo2jhgg',
        n_reserva:12345,
        nViaje:'55',
        agenteCreador:'ALUSA',
        tipoNegocio:'algo',
        destino:'STGO',
        naviera:1,
        container:1

    };

    $scope.send = function(){
        $http.post("http://localhost:8080/blmasters",$scope.newBLMaster);
        console.log($scope.newBLMaster);

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