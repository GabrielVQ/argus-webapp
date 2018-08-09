app.controller('rentaVerController', ['$scope', '$location', '$http','$routeParams', function($scope, $location, $http, $routeParams) {
    
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = 1452;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    $http.get('http://localhost:8080/blhouses').then(function(response){
        $scope.BLHouse = response.data;
        //console.log($scope.BLMaster[0].numeroOperacion);
        //console.log($scope.BLMaster);
    })


    $scope.isActive = function(route) {
        return route === $location.path();
    }
}]);