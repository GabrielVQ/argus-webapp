app.controller('agregarNavieraController', ['$scope', '$location', '$http','$window', function($scope, $location, $http,$window) {


    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newAgregarNaviera = {
        "nombre": ""

    };

    $scope.send = function(){


        $http.post("http://localhost:8080/navieras",$scope.newAgregarNaviera);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'puerto añadido con exito!';
        $window.alert($scope.mensaje);

    }

}]);