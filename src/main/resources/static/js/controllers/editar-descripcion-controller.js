app.controller('editarDescripcionController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL','servicioNumeroBLHouse', '$routeParams' ,function($scope, $location, $http,$window, servicioNumeroBL,servicioNumeroBLHouse, $routeParams) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();
    //$scope.numeroBLHouse = servicioNumeroBLHouse.numeroBLHouse;

    $scope.numeroBLMaster = localStorage.getItem("token4");
    $scope.numeroOperacion = $routeParams.bl;
    $scope.numeroBLHouse = $routeParams.blhouse;
    $scope.bl = $routeParams.bl;
    $scope.blhouse = $routeParams.blhouse;
    $scope.idDescripcion = $routeParams.id;
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
        console.log("datos a guardar",$scope.editarDescripcion.contenedor);
        //$scope.editarDescripcion.blHouse= {"id":parseInt($scope.BLHouseId)};
        //$scope.editarDescripcion.contenedor= {"id":parseInt($scope.editarDescripcion.contenedor.)};

        $http.post("http://localhost:8080/cargaments",$scope.editarDescripcion);
        $scope.mensaje = 'Descripción editada con exito!';
        $window.alert($scope.mensaje);
        $location.url('/descripcion');


        //aca newVotation esta listo para ser utilizado en el método POST, en teoría
        //$window.location.reload();
    }
    var urlBase1 = 'http://localhost:8080/cargaments/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBase1)
        .then(function(response) {
            $scope.descripcion = response.data;
            var i = 0;
            //console.log("descripcion:", $scope.descripcion[i].numeroBLHouse);

            while (i <= $scope.descripcion.length) {
                $scope.numid = parseInt($scope.descripcion[i].id);
                if ($scope.numid === parseInt($scope.idDescripcion)) {
                    $scope.editarDescripcion = $scope.descripcion[i];
                    //console.log("TENGO descri:", $scope.editarDescripcion.numeroBLHouse , "y", $scope.editarDescripcion.numeroOperacion);
                    $scope.editarDescripcion.numerPackages = parseInt($scope.editarDescripcion.numerPackages);
                    $scope.editarDescripcion.groosWeight = parseInt($scope.editarDescripcion.groosWeight);
                    $scope.editarDescripcion.measurement = parseInt($scope.editarDescripcion.measurement);
                    console.log("descipcion",$scope.editarDescripcion.descriptionGoods)
                    break;
                }
                //console.log("no encontre")
                i = i+1;
            }

            // console.log('id: ',$scope.BLMaster);
            //$scope.fechaStacking = parseFecha($scope.editarBLHouse.fechaStacking);
            //console.log("fecha:", $scope.fechaStacking)
        });
    $scope.borrar = function(id){
        var opcion = confirm("¿Seguro que desea eliminar el cargamento?")
        if (opcion == true) {
            $http.delete("http://localhost:8080/cargaments/cargamentBorrar/" + id)
            $window.alert("Cargamento eliminado")
            $window.location.reload();
        }
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