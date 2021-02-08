function showBattleSeaGamePage() {
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("seaBattleGamePage").style.display = "block";
}

function showDotsAndBoxesGamePage() {
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("dotsAndBoxesGamePage").style.display = "block";
}

function showSeaBattleGamesDiv() {
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-GamesRibbon").style.backgroundColor = "#bb2d44"

    document.getElementById("SB-HowToPlayDiv").style.display = "none"
    document.getElementById("SB-leaderboardDiv").style.display = "none";
    document.getElementById("SB-historyDiv").style.display = "none";
    document.getElementById("SB-GamesDiv").style.display = "flex";
}

function showSeaBattleHistoryDiv() {
    document.getElementById("SB-GamesRibbon").style.backgroundColor = "#122948"
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("SB-GamesDiv").style.display = "none";
    document.getElementById("SB-HowToPlayDiv").style.display = "none"
    document.getElementById("SB-leaderboardDiv").style.display = "none";
    document.getElementById("SB-historyDiv").style.display = "flex";
}

function showSeaBattleLeaderboardDiv() {
    document.getElementById("SB-GamesRibbon").style.backgroundColor = "#122948"
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#bb2d44";

    document.getElementById("SB-GamesDiv").style.display = "none";
    document.getElementById("SB-HowToPlayDiv").style.display = "none"
    document.getElementById("SB-historyDiv").style.display = "none";
    document.getElementById("SB-leaderboardDiv").style.display = "flex";
}

function showSeaBattleHowToPlayDiv() {
    document.getElementById("SB-GamesRibbon").style.backgroundColor = "#122948"
    document.getElementById("SB-HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("SB-HowToPlayRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("SB-LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("SB-GamesDiv").style.display = "none";
    document.getElementById("SB-historyDiv").style.display = "none";
    document.getElementById("SB-leaderboardDiv").style.display = "none";
    document.getElementById("SB-HowToPlayDiv").style.display = "flex"
}

function showDotsAndBoxesGamesDiv() {
    document.getElementById("GamesRibbon").style.backgroundColor = "#bb2d44"
    document.getElementById("HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("DB-HowToPlayDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "none";
    document.getElementById("DB-GamesDiv").style.display = "flex"
}

function showDotsAndBoxesHistoryDiv() {
    document.getElementById("GamesRibbon").style.backgroundColor = "#122948"
    document.getElementById("HistoryRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("DB-GamesDiv").style.display = "none"
    document.getElementById("DB-HowToPlayDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "flex";
}

function showDotsAndBoxesLeaderboardDiv() {
    document.getElementById("GamesRibbon").style.backgroundColor = "#122948"
    document.getElementById("HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#122948";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#bb2d44";

    document.getElementById("DB-GamesDiv").style.display = "none"
    document.getElementById("DB-HowToPlayDiv").style.display = "none";
    document.getElementById("DB-historyDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "flex";
}

function showDotsAndBoxesHowToPlay() {
    document.getElementById("GamesRibbon").style.backgroundColor = "#122948"
    document.getElementById("HistoryRibbon").style.backgroundColor = "#122948";
    document.getElementById("HowToPlayRibbon").style.backgroundColor = "#bb2d44";
    document.getElementById("LeaderboardRibbon").style.backgroundColor = "#122948";

    document.getElementById("DB-GamesDiv").style.display = "none"
    document.getElementById("DB-historyDiv").style.display = "none";
    document.getElementById("DB-leaderboardDiv").style.display = "none";
    document.getElementById("DB-HowToPlayDiv").style.display = "flex";
}