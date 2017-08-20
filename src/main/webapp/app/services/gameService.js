(function () {
    
angular.module('gamesiteApp').service('gameService', ['$http', GameService]);
    
function GameService($http) {
    
    var gameApiPath = "rest/gameAPI"
    var gameApiPathPlusGame = gameApiPath + "/game";
    
    this.getAllGames = function() {
        return $http.get(gameApiPathPlusGame);
    }
    
    this.postGame = function(game) {
        return $http.post(gameApiPathPlusGame, game);
    }
}

}());