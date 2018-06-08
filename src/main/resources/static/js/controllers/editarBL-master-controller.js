app.controller('editarBLMasterController', ['$scope', '$location', '$q', '$http','$window','$routeParams', 'BLMaster1', function($scope, $location, $q, $http,$window, $routeParams,BLMaster1) {
    

    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    $scope.bl = $routeParams.bl;
    $scope.BLMaster = [];

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

  
    var urlBase = 'http://localhost:8080/blmasters/numerooperacion/'+$scope.bl;

    $http.get(urlBase)
    .then(function(response) {
        $scope.BLMaster = response.data[0];
    });

    

    BLMaster1.get().then(function(data){
        //$scope.BLMaster = data[0];
        console.log($scope.BLMaster);
     })
     .catch(function(response){
        console.log(response.status);
     }); 

     
    
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