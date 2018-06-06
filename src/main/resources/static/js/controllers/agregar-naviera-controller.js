app.controller('agregarNavieraController', ['$scope', '$location', function($scope, $location) {

    $scope.newAgregarNaviera= {


    };
    $scope.send = function(){

        //$scope.numeroOperacion += 1;
        $scope.newAgregarNaviera.nombre= {"id":parseInt($scope.newAgregarNaviera.nombre)};

        $scope.newBLMaster.fechaZarpe = $scope.fechaZarpeAux;
        $http.post("http://localhost:8080/naviera",$scope.newAgregarNaviera.nombre);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'Naviera Agregada!';
        $window.alert($scope.mensaje);



        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    $scope.isActive = function(route) {
        return route === $location.path();
    }
}]);