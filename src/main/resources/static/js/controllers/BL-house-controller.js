app.controller('BLHouseController', ['$scope', '$location', '$http','$window', function($scope, $location, $http,$window) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = 1452;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();




    $scope.isActive = function(route) {
        return route === $location.path();
    }


}]);