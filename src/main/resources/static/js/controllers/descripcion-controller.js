app.controller('descripcionController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL','servicioNumeroBLHouse','$routeParams',function($scope, $location, $http,$window, servicioNumeroBL,servicioNumeroBLHouse, $routeParams ) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();
    //$scope.numeroBLHouse = servicioNumeroBLHouse.numeroBLHouse;


    $scope.bl = $routeParams.bl;
    $scope.blhouse = $routeParams.blhouse;
    $scope.numeroOperacion =$scope.bl;
    $scope.numeroBLHouse = $scope.blhouse;
    var urlBase1 = 'https://argus-webapp.herokuapp.com/blmasters/numerooperacion/'+$scope.bl;

    $http.get(urlBase1).then(function(response){
        $scope.blMaster = response.data;
        $scope.numeroBLMaster= $scope.blMaster[0].blmasterNumero
        //console.log($scope.ciudades);
    });

    $scope.isActive = function(route) {
        return route === $location.path();
    };

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

    var urlBaseCargament = 'https://argus-webapp.herokuapp.com/cargaments/numerooperacion/'+$scope.numeroOperacion;

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

    var urlBase = 'https://argus-webapp.herokuapp.com/blhouses/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBase)
        .then(function(response) {
            $scope.BLHouseId = response.data[0].id;
           // console.log('status: ',response.status);
        });

    $scope.send2 = function(){

        $scope.newDescripcion.blHouse= {"id":parseInt($scope.BLHouseId)};
        $scope.newDescripcion.contenedor= {"id":parseInt($scope.newDescripcion.contenedor)};
        $scope.newDescripcion.groosWeight = parseFloat($scope.newDescripcion.groosWeight).toFixed(2);
        $scope.newDescripcion.measurement = parseFloat($scope.newDescripcion.measurement).toFixed(2);
        $scope.newDescripcion.markNumbers = $scope.newDescripcion.markNumbers.toUpperCase();
        $http.post("https://argus-webapp.herokuapp.com/cargaments",$scope.newDescripcion);
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
        $window.location.reload();
    }

    $scope.borrar = function(id){
        var opcion = confirm("¿Seguro que desea eliminar el cargamento?")
        if (opcion == true) {
            $http.delete("https://argus-webapp.herokuapp.com/cargaments/cargamentBorrar/" + id)
            $window.alert("Cargamento eliminado")
            $window.location.reload();
        }
    }

    $http.get('https://argus-webapp.herokuapp.com/containers').then(function(response){
        $scope.containers = response.data;
        //console.log($scope.containers);
    })

    $http.get('https://argus-webapp.herokuapp.com/cargaments').then(function(response){
        $scope.cargaments = response.data;
        //console.log($scope.cargaments);
    })

    $http.get('https://argus-webapp.herokuapp.com/blmasters').then(function(response){
        $scope.BLMaster = response.data;
        //console.log('data:',$scope.BLMaster[1].blHouse);
    })
}]);