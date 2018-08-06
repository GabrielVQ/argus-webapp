app.controller('ingresosController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL','servicioNumeroBLHouse', function($scope, $location, $http,$window, servicioNumeroBL,servicioNumeroBLHouse) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    $scope.numeroBLHouse = servicioNumeroBLHouse.numeroBLHouse;


    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $http.get('http://localhost:8080/containers').then(function(response){
        $scope.containers = response.data;
        console.log($scope.containers);
    })

    $http.get('http://localhost:8080/empresas').then(function(response){ // campo: agente
        $scope.empresas = response.data;
        //console.log($scope.puertos);
    })
}]);