app.controller('agregarNaveController', ['$scope', '$location','$http','$window', function($scope, $location,$http,$window) {



    $scope.isActive = function(route) {
        return route === $location.path();
    }
    $scope.newAgregarNave = {
        "nombre": ""
    };

    $scope.send = function(){


        $http.post("http://localhost:8080/naves",$scope.newAgregarNave);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'nave añadida con exito!';
        $window.alert($scope.mensaje);

    }

}]);