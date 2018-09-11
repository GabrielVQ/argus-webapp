app.controller('partnerController', ['$scope', '$location','$routeParams','$http', function($scope, $location,$routeParams, $http) {
    

    $scope.user_id = $routeParams.user_id;
    console.log("testing scope main controller: partner",$scope.user_id);
    $http.get('http://localhost:8080/users/'+ $scope.user_id).then(function(response){ 
        $scope.nombre_usuario = response.data.name;
        //console.log($scope.ciudades);
    })
    
    $scope.op_num;
    $scope.search = function(){
        console.log("input value: ",$scope.op_num);  
        $http.get('https://argus-webapp.herokuapp.com/blmasters/numerooperacion/'+ $scope.op_num).then(function(response){
        console.log("BL: ", response.data);
        $scope.BLMaster = response.data; // deberia ser el bl-house ahi se obtienen todos los datos q van en el pdf
        //console.log($scope.ciudades);
    })

    }

    console.log("bl num from buscarbl in partner",$routeParams.bl)


	$scope.isActive = function(route) {
        return route === $location.path();
    }
}]);