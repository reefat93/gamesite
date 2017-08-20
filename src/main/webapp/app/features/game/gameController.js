(function() {

    var GameController = function($scope, gameService) {

        var vm = this;
        
        vm.games = [];
        
        function init() {
            gameService.getAllGames().then(function(response){
                vm.games = response.data;
            }, function(error) {
                console.log(error);
            });
        };
        init();
        
    }
    
    //This needs to be at the bottom so that the controller variable is correctly created before it is added as a controller
    angular.module('gamesiteApp').controller('gameController', ['$scope', 'gameService', GameController]);

}());