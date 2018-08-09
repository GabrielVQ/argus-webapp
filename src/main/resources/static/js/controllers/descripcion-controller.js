app.controller('descripcionController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL','servicioNumeroBLHouse' ,function($scope, $location, $http,$window, servicioNumeroBL,servicioNumeroBLHouse) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();
    //$scope.numeroBLHouse = servicioNumeroBLHouse.numeroBLHouse;

    $scope.numeroBLMaster = localStorage.getItem("token4");
    $scope.numeroOperacion = localStorage.getItem("token");
    $scope.numeroBLHouse = localStorage.getItem("token3");

    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newDescripcion = {
        "numeroOperacion": $scope.numeroOperacion,
        "numeroBLHouse": $scope.numeroBLHouse,
        "blHouse":0,
        "contenedor":0,
        "markNumbers":"",
        "numerPackages":"",
        "groosWeight":"",
        "measurement":"",
        "descriptionGoods":""
    }

    var urlBaseCargament = 'http://localhost:8080/cargaments/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBaseCargament)
        .then(function(response) {
            $scope.cargamentsBYoperacion = response.data;
            //var i = 0;
            //console.log("NUMERO BL HOUSE:", response.data[i].numeroBLHouse);
            /*
            while (i <= response.data.length) {
                $scope.numHouse = parseInt(response.data[i].numeroBLHouse);
                if ($scope.numeroBLHouse === parseInt(response.data[i].numeroBLHouse)) {
                    $scope.cargamentsBYoperacion = response.data[i];
                    console.log("TENGO house:", response.data[i].numeroBLHouse)
                    break;
                }
                //console.log("no encontre")
                i = i+1;
            }*/
        });

    var urlBase = 'http://localhost:8080/blhouses/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBase)
        .then(function(response) {
            $scope.BLHouseId = response.data[0].id;
           // console.log('status: ',response.status);
        });

    $scope.send2 = function(){

        $scope.newDescripcion.blHouse= {"id":parseInt($scope.BLHouseId)};
        $scope.newDescripcion.contenedor= {"id":parseInt($scope.newDescripcion.contenedor)};
        $http.post("http://localhost:8080/cargaments",$scope.newDescripcion);
        $scope.mensaje = 'Descripción agregada con exito!';
        $window.alert($scope.mensaje);

        $http.get(urlBaseCargament)
        .then(function(response) {
            $scope.cargamentsBYoperacion = response.data;
            
            console.log("cargamentByoperacion", $scope.cargamentsBYoperacion);
            //var i = 0;
            //console.log("NUMERO BL HOUSE:", response.data[i].numeroBLHouse);
            /*
            while (i <= response.data.length) {
                $scope.numHouse = parseInt(response.data[i].numeroBLHouse);
                if ($scope.numeroBLHouse === parseInt(response.data[i].numeroBLHouse)) {
                    $scope.cargamentsBYoperacion = response.data[i];
                    console.log("TENGO house:", response.data[i].numeroBLHouse)
                    break;
                }
                //console.log("no encontre")
                i = i+1;
            }*/
        });

        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }



    $http.get('http://localhost:8080/containers').then(function(response){
        $scope.containers = response.data;
        //console.log($scope.containers);
    })

    $http.get('http://localhost:8080/cargaments').then(function(response){
        $scope.cargaments = response.data;
        //console.log($scope.cargaments);
    })

    $http.get('http://localhost:8080/blmasters').then(function(response){
        $scope.BLMaster = response.data;
        //console.log('data:',$scope.BLMaster[1].blHouse);
    })
}]);