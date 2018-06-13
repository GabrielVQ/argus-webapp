app.controller('agregarEmpresaController', ['$scope', '$location','$http','$window', function($scope, $location,$http,$window) {



    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newAgregarEmpresa = {
        "cod_agente": "",
        "contacto": "",
        "direccion": "",
        "fonoContacto": "",
        "nacionalidad": "",
        "nombre_abrev": "",
        "razon_social": ""
    };

    $scope.send = function(){

        $http.post("http://localhost:8080/empresas",$scope.newAgregarEmpresa);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'empresa añadida con exito!';
        $window.alert($scope.mensaje);


    }
}]);