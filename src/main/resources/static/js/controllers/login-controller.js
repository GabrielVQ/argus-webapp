app.controller('loginController', ['$scope', '$location', '$http','$window','$interval', '$routeParams',function($scope, $location, $http,$window, $routeParams, ConsultaService,digitalClock, MisDatos) {

    $scope.date = new Date();

	$scope.usuario = {
		email : "",
		password :""
	};

    $scope.send = function () {
        var correo = document.getElementById("usuario").value;
        var pass = document.getElementById("pass").value;
        $scope.usuario.email = (correo);
        $scope.usuario.password = (pass);

        $http.post("http://localhost:8080/users/validate",$scope.usuario).then(function(response){
            //console.log(response.data);
            if(response.data!=""){
                //if
                console.log("mail del weon ",response.data.email);
                if(response.data.email=="costarica@argus.cl") {
                    $location.url('/partnerHome');
                }
                else $location.url('/home');
            } 
            else
                alert("Correo o contraseña incorrectas")
        });

        
    };


}]);