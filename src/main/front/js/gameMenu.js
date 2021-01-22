function showBattleSeaGamePage() {
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("gamePage").style.display = "block";
}

function showHistoryDiv() {
    document.getElementById("leaderboardDiv").style.display = "none";
    document.getElementById("historyDiv").style.display = "flex";
}

function showLeaderboardDiv() {
    document.getElementById("historyDiv").style.display = "none";
    document.getElementById("leaderboardDiv").style.display = "flex";
}