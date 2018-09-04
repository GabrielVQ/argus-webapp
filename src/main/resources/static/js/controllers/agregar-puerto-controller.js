app.controller('agregarPuertoController', ['$scope', '$location','$http','$window', function($scope, $location, $http,$window) {



    $scope.isActive = function(route) {
        return route === $location.path();
    }
    $scope.newAgregarPuerto = {
        "nombre": "",
        "direccion": ""
    };

    $scope.send = function(){

        $scope.newAgregarPuerto.nombre = $scope.newAgregarPuerto.nombre.toUpperCase();
        $scope.newAgregarPuerto.direccion = $scope.newAgregarPuerto.direccion.toUpperCase();
        $http.post("http://localhost:8080/puertos",$scope.newAgregarPuerto);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'puerto añadido con exito!';
        $window.alert($scope.mensaje);

    }

}]);