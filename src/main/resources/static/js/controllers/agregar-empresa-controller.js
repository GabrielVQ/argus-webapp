app.controller('agregarEmpresaController', ['$scope', '$location','$http','$window', function($scope, $location,$http,$window) {



    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newAgregarEmpresa = {
        "codigo": "",
        "contacto": "",
        "direccion": "",
        "fonoContacto": "",
        "nacionalidad": "",
        "nombreAbrev": "",
        "razon_social": "",
        "rut":"",
        "tipoEmpresa":""
    };

    $scope.send = function(){
        $scope.newAgregarEmpresa.codigo = $scope.newAgregarEmpresa.codigo.toUpperCase();
        $scope.newAgregarEmpresa.direccion = $scope.newAgregarEmpresa.direccion.toUpperCase();
        $scope.newAgregarEmpresa.nacionalidad = $scope.newAgregarEmpresa.nacionalidad.toUpperCase();
        $scope.newAgregarEmpresa.nombreAbrev = $scope.newAgregarEmpresa.nombreAbrev.toUpperCase();
        $scope.newAgregarEmpresa.razon_social = $scope.newAgregarEmpresa.razon_social.toUpperCase();
        $http.post("http://localhost:8080/empresas",$scope.newAgregarEmpresa);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'empresa añadida con exito!';
        $window.alert($scope.mensaje);


    }
}]);