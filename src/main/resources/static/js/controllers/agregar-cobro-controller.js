app.controller('agregarCobroController', ['$scope', '$location','$http','$window', function($scope, $location, $http,$window) {



    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newAgregarCobro = {
        "nombreCobro": "asdf"
    };


    $scope.send = function(){

        $http.post("http://localhost:8080/cobros",$scope.newAgregarCobro);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'Cobro añadido con exito!';
        $window.alert($scope.mensaje);

    }
}]);