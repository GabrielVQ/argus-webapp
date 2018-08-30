app.controller('rentaVerController', ['$scope', '$location', '$http','$routeParams', function($scope, $location, $http, $routeParams) {
    
    $scope.bl = $routeParams.bl;

    console.log("falopita virtud",$scope.bl);

    var urlBase = 'http://localhost:8080/blhouses/numerooperacion/'+$scope.bl;

    $http.get(urlBase).then(function(response){
        $scope.BLHouseOp = response.data[0];
        //console.log($scope.BLMaster[0].numeroOperacion);
        console.log("blhouse test: ",$scope.BLHouseOp.numeroOperacion);
    })


    $scope.isActive = function(route) {
        return route === $location.path();
    }
}]);