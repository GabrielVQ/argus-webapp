app.controller('editarIngresosController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL','servicioNumeroBLHouse', '$routeParams' ,function($scope, $location, $http,$window, servicioNumeroBL,servicioNumeroBLHouse, $routeParams) {
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
    $scope.idIngreso = $routeParams.id;

    $scope.isActive = function (route) {
        return route === $location.path();
    }

    var urlBase1 = 'http://localhost:8080/ingresos/numerooperacion/' + $scope.numeroOperacion;

    $http.get(urlBase1)
        .then(function (response) {
            $scope.ingresos = response.data;
            var i = 0;
            console.log("ingreso:", $scope.ingresos[i].numeroBLHouse);

            while (i <= $scope.ingresos.length) {
                $scope.numid = parseInt($scope.ingresos[i].id);
                if ($scope.numid === parseInt($scope.idIngreso)) {
                    $scope.editarIngreso = $scope.ingresos[i];
                    console.log("TENGO prepaidi:", $scope.editarIngreso.prepaid);
                    $scope.editarIngreso.prepaid = parseFloat($scope.editarIngreso.prepaid);
                    $scope.editarIngreso.collect = parseFloat($scope.editarIngreso.collect);
                    $scope.editarIngreso.costo = parseFloat($scope.editarIngreso.costo);
                    //console.log("descipcion", $scope.editarDescripcion.descriptionGoods)
                    break;
                }
                //console.log("no encontre")
                i = i + 1;
            }

            // console.log('id: ',$scope.BLMaster);
            //$scope.fechaStacking = parseFecha($scope.editarBLHouse.fechaStacking);
            //console.log("fecha:", $scope.fechaStacking)
        });

    $scope.send = function(){
        //console.log("datos a guardar",$scope.editarDescripcion.contenedor);
        //$scope.editarDescripcion.blHouse= {"id":parseInt($scope.BLHouseId)};
        //$scope.editarDescripcion.contenedor= {"id":parseInt($scope.editarDescripcion.contenedor.)};
        $scope.editarIngreso.prepaid = parseFloat($scope.editarIngreso.prepaid).toFixed(2);
        $scope.editarIngreso.collect = parseFloat($scope.editarIngreso.collect).toFixed(2);
        $scope.editarIngreso.costo = parseFloat($scope.editarIngreso.costo).toFixed(2);
        $http.post("http://localhost:8080/ingresos",$scope.editarIngreso);
        $scope.mensaje = 'Ingreso editado con exito!';
        $window.alert($scope.mensaje);
        $window.location.reload();
        $location.url('/ingresos/'+$scope.numeroOperacion+'/'+$scope.numeroBLHouse);


        //aca newVotation esta listo para ser utilizado en el método POST, en teoría
        //$window.location.reload();
    }

    $scope.borrar = function(id){
        var opcion = confirm("¿Seguro que desea eliminar el ingreso?");
        if (opcion == true) {
            $http.delete("http://localhost:8080/ingresos/ingresoBorrar/" + id);
            $window.alert("Ingreso eliminado");
            $window.location.reload();
        }
    }
    var urlBaseIngreso = 'http://localhost:8080/ingresos/numerooperacion/'+$scope.numeroOperacion;
    $http.get(urlBaseIngreso)
        .then(function(response) {
            $scope.ingresosBYoperacion = response.data;
            console.log("ingresos por operacion AFUERA ",$scope.ingresosBYoperacion);
        });

    $http.get('http://localhost:8080/cobros').then(function(response){ // campo: agente
        $scope.cobros = response.data;
        //console.log($scope.puertos);
    })

    $http.get('http://localhost:8080/empresas').then(function(response){ // campo: agente
        $scope.empresas = response.data;
        //console.log($scope.puertos);
    })
}]);