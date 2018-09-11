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

        $http.post("https://argus-webapp.herokuapp.com/users/validate",$scope.usuario).then(function(response){
            //console.log(response.data);
            if(response.data!=""){

                //if
                console.log("mail del weon ",response.data);
                if(response.data.email=="costarica@argus.cl") {
                    $location.url('/partnerHome/'+ response.data.id);
                }
                else $location.url('/home/'+ response.data.id);
            } 
            else
                alert("Correo o contraseña incorrectas")
        });

        
    };


}]);