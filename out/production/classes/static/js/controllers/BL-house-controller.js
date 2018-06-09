app.controller('BLHouseController', ['$scope', '$location', function($scope, $location) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = 1452;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();




    $scope.isActive = function(route) {
        return route === $location.path();
    }
}]);