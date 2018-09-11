app.controller('agregarCiudadController', ['$scope', '$location','$http','$window', function($scope, $location, $http,$window) {


    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newAgregarCiudad = {
        "nombre": ""
    };

    $scope.send = function(){

        $scope.newAgregarCiudad.nombre = $scope.newAgregarCiudad.nombre.toUpperCase();
        $http.post("https://argus-webapp.herokuapp.com/ciudades",$scope.newAgregarCiudad);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'ciudad añadida con exito!';
        $window.alert($scope.mensaje);

    }


}]);