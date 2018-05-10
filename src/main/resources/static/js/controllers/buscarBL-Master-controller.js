app.controller('buscarBLMasterController', ['$scope', '$location', function($scope, $location) {
    $scope.numeroOperacion = [];
    $scope.fecha = [];
    $scope.creadoPor = [];



    $http.get('http://localhost:8080/').then(function(response){
        $scope.BLMaster = response.data;
        console.log($scope.BLMaster);
    })

    $scope.isActive = function(route) {
        return route === $location.path();
    }
}]);