// JavaScript Document

//------------------------------------------------------------------------------
app.controller('LancamentosController', ['$scope', function ($scope) {
 
    $scope.tipos = [];
     
    $scope.lancamento = {
        tipo: 0,
        data: "",
        obs: "",
        valor: "", 
        cpf: ""
    };
 
    $scope.init = function () {
        //carregando os tipos
        if (localStorage.getItem("tps") != null) {
            $scope.tipos = JSON.parse(localStorage.getItem("tps"));
        }
    };
 
    $scope.salvar = function () {
        
        //testando se o formulário é valido
        if (!$scope.formDados.$valid) {
             
            alert('Verifique os campos');
        }
        else{
            alert('salvou');
        }
    }
 
    $scope.init();
 
}]);