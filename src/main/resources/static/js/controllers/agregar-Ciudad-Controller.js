app.controller('agregarCiudadController', ['$scope', '$location','$http','$window', function($scope, $location, $http,$window) {


    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newAgregarCiudad = {
        "nombre": ""
    };

    $scope.send = function(){
        

        $http.post("http://localhost:8080/ciudades",$scope.newAgregarCiudad);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'ciudad añadida con exito!';
        $window.alert($scope.mensaje);



        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }


}]);