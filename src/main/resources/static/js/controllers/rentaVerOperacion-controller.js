app.controller('rentaVerController', ['$scope', '$location', '$http','$routeParams', function($scope, $location, $http, $routeParams) {
    
    $scope.bl = $routeParams.bl;
    $scope.numeroOperacion = $routeParams.bl;
    //console.log("falopita virtud",$scope.bl);
    $scope.totalPrepaid = 0;
    $scope.totalCollect = 0;
    $scope.totalCosto = 0;
    $scope.profit = 0;

    var urlBase = 'http://localhost:8080/blhouses/numerooperacion/'+$scope.bl;

    $http.get(urlBase).then(function(response){
        $scope.BLHouse = response.data;
        $scope.moneda = $scope.blHouse[0].moneda;
        //console.log($scope.BLMaster[0].numeroOperacion);
        //console.log("blhouse test: ",$scope.BLHouseOp.numeroOperacion);
    })

    var urlBaseIngreso = 'http://localhost:8080/ingresos/numerooperacion/'+$scope.numeroOperacion;
    $http.get(urlBaseIngreso)
        .then(function(response) {
            $scope.ingresosBYoperacion = response.data;
            var i;
            for (i in $scope.ingresosBYoperacion){
                $scope.totalPrepaid = $scope.totalPrepaid + parseFloat($scope.ingresosBYoperacion[i].prepaid);
                $scope.totalCollect = $scope.totalCollect + parseFloat($scope.ingresosBYoperacion[i].collect);
                $scope.totalCosto = $scope.totalCosto + parseFloat($scope.ingresosBYoperacion[i].costo);
            }
            $scope.profit = parseFloat($scope.totalCollect) + parseFloat($scope.totalPrepaid) - parseFloat($scope.totalCosto);
            //console.log("ingresos por operacion AFUERA ",$scope.ingresosBYoperacion);
        });


    $scope.profit = parseFloat($scope.totalCollect) + parseFloat($scope.totalPrepaid) - parseFloat($scope.totalCosto);

    $scope.isActive = function(route) {
        return route === $location.path();
    }
}]);