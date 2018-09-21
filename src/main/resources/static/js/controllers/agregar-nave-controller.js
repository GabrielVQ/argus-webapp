app.controller('agregarNaveController', ['$scope', '$location','$http','$window', function($scope, $location,$http,$window) {



    $scope.isActive = function(route) {
        return route === $location.path();
    }
    $scope.newAgregarNave = {
        "nombre": ""
    };

    $scope.send = function(){

        $scope.newAgregarNave.nombre = $scope.newAgregarNave.nombre.toUpperCase();
        $http.post("https://argus-webapp.herokuapp.com/naves",$scope.newAgregarNave);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'Nave añadida con exito!';
        $window.alert($scope.mensaje);

    }

}]);