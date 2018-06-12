app.controller('editarBLMasterController', ['$scope', '$location', '$http','$window','$routeParams', function($scope, $location, $http,$window, $routeParams) {
    

    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    $scope.bl = $routeParams.bl;
    $scope.BLMaster = [];

    function parseFecha(date){
        var aux = date.split(' ')[0].split('/');
        var fecha =  aux[2]+"-"+aux[1]+"-"+aux[0];
        return fecha;
    }
    
    var urlBase = 'http://localhost:8080/blmasters/numerooperacion/'+$scope.bl;

    $http.get(urlBase)
    .then(function(response) {
        $scope.BLMaster = response.data[0];
        console.log('fecha from api',$scope.BLMaster.fechaZarpe);

        $scope.fechaZarpeValue = parseFecha($scope.BLMaster.fechaZarpe);
        $scope.fechaLlegadaValue = parseFecha($scope.BLMaster.fechaLlegada);
    });


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




    
    $http.get('http://localhost:8080/ciudades').then(function(response){  // campo: destino
        $scope.ciudades = response.data;
        //console.log($scope.ciudades);
    })
    //console.log($scope.ciudades);

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