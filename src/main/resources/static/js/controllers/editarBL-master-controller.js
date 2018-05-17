app.controller('editarBLMasterController', ['$scope', '$location', '$http','$window', function($scope, $location, $http,$window) {
    
   
    //$scope.ciudades;
   // var list = ciuda.map(x => x.id);


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
        "destino": "Conce",
        "fechaIngreso": "10/05/2018 a las 10:46",
        "fechaLlegada": "12/07/2018 a las 04:00",
        "fechaZarpe": "25/07/2018 a las 04:00",
        "nave":0,
        "naviera":0
    };

    $scope.numeroOperacion = $scope.newBLMaster.numeroOperacion;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();


    $scope.send = function(){
        
        //$scope.numeroOperacion += 1;
        $scope.newBLMaster.nave= {"id":parseInt($scope.newBLMaster.nave)};
        $scope.newBLMaster.naviera= {"id":parseInt($scope.newBLMaster.naviera)};
        $scope.newBLMaster.puertoOrigen= {"id":parseInt($scope.newBLMaster.puertoOrigen)};
        $scope.newBLMaster.puertoDescarga= {"id":parseInt($scope.newBLMaster.puertoDescarga)};

        



        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    /*$scope.ciudades = function() {
        $http.get('http://localhost:8080/ciudades').then(function (response) {
            $scope.ciudades = response.data;});
            //console.log($scope.ciudades[0]);
    }*/

    $http.get('http://localhost:8080/blmasters/numerooperacion/ARG0003').then(function(response){
        $scope.BLMaster = response.data;
        console.log('BL get testing',$scope.BLMaster);
    })




}]);