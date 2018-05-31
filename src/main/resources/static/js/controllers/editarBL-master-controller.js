app.controller('editarBLMasterController', ['$scope', '$location', '$http','$window','$routeParams', function($scope, $location, $http,$window, $routeParams) {
    

    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    $scope.bl = $routeParams.bl;


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

}]);