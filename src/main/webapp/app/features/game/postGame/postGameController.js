(function() {

    var PostGameController = function($scope, gameService) {

        var vm = this;
        vm.message = "";
        vm.game = {};
        
        vm.postTheGame = function() {
            gameService.postGame(vm.game).then(function(response){
                console.log(response);
                vm.message = response.data.message;
            }, function(error) {
                console.log(error);
            });
        }
        
        vm.printGame = function(){
            console.log(vm.game);
        }
    }
    
    //This needs to be at the bottom so that the controller variable is correctly created before it is added as a controller
    angular.module('gamesiteApp').controller('postGameController', ['$scope', 'gameService', PostGameController]);

}());