app.controller('agregarCobroController', ['$scope', '$location','$http','$window', function($scope, $location, $http,$window) {



    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newAgregarCobro = {
        "nombreCobro": ""
    };


    $scope.send = function(){
        $scope.newAgregarCobro.nombreCobro = $scope.newAgregarCobro.nombreCobro.toUpperCase();
        $http.post("https://argus-webapp.herokuapp.com/cobros",$scope.newAgregarCobro);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'Cobro añadido con exito!';
        $window.alert($scope.mensaje);

    }
}]);