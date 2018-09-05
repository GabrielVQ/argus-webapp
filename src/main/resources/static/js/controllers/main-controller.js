app.controller('MainController', ['$scope', '$location','$routeParams','$http', function($scope, $location,$routeParams,$http) {
	$scope.title = "Inicio";
    $scope.template = "js/views/templates/guest-links.html";
    $scope.loggedUser;
    console.log("routeeee",$routeParams); 
 /*    $http.get('http://localhost:8080/users/'+$routeParams.id).then(function(response){
   		$scope.loggedUser = response.data.name;
    	});
     if($scope.loggedUser!=""){
    	$scope.template="js/views/templates/voter-links.html";
    }
    else{
    	$scope.template="js/views/templates/guest-links.html";
     } */

    $scope.user_id = $routeParams.user_id;
    $http.get('http://localhost:8080/users/'+ $routeParams.user_id).then(function(response){ 
        $scope.nombre_usuario = response.data.name;
        //console.log("nombre usuario home", $scope.nombre_usuario)
        var token = $scope.nombre_usuario;
        localStorage.setItem("token5",token);
        //console.log($scope.ciudades);
    })

	$scope.isActive = function(route) {
        return route === $location.path();
    }
}]);