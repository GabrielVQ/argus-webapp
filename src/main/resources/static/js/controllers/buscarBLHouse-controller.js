app.controller('buscarBLHouseController', ['$scope', '$location', '$http','$routeParams', function($scope, $location, $http, $routeParams) {
    
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = 1452;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    $http.get('http://localhost:8080/blhouses').then(function(response){
        $scope.BLHouse = response.data;
        //console.log($scope.BLMaster[0].numeroOperacion);
        //console.log($scope.BLMaster);
        console.log("busqueda master1:",$scope.BLHouse);
    })

    $http.get('http://localhost:8080/blmasters').then(function(response){
        console.log("test 123:",$scope.BLHouse);
        $scope.BLHouse.addElements(response.data);
        //console.log($scope.BLMaster[0].numeroOperacion);
        console.log("test 123:");
        console.log("busqueda master2:",$scope.BLHouse);
    })

    $scope.isActive = function(route) {
        return route === $location.path();
    }
}]);