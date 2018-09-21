app.controller('agregarNavieraController', ['$scope', '$location', '$http','$window', function($scope, $location, $http,$window) {


    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newAgregarNaviera = {
        "nombre": ""

    };

    $scope.send = function(){

        $scope.newAgregarNaviera.nombre = $scope.newAgregarNaviera.nombre.toUpperCase();
        console.log("naviera",$scope.newAgregarNaviera.nombre );
        $http.post("https://argus-webapp.herokuapp.com/navieras",$scope.newAgregarNaviera);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'Naviera añadida con exito!';
        $window.alert($scope.mensaje);

    }

}]);