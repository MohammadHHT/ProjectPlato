navigation[5].addEventListener('click', function () {
    navigation[5].classList.remove('show');
    navigation[3].classList.remove('show');
    navigation[4].classList.add('show');
    navigation[6].classList.add('show');
    if (player) {
        loadSuggestedGames();
    } else {
        loadAllPlayer();
    }
});


function loadAllPlayer() {
    let playerList = document.querySelector('section.suggestion .all-players .players-area');

    playerList.innerHTML = ' ';
    next_page('primary', 'suggestion');

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user allUsers ' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let users = e.data.split('/');

        let usernames = [];
        for (let i = 0; i < users.length; i++) {
            let tmp = users[i].split(' ');
            usernames[i] = tmp[2];
        }

        for (let i = 0; i < users.length; i++) {
            playerList.insertAdjacentHTML('beforeend',
                '                    <div class="player-box">\n' +
                '                        <div onclick=chatWithPlayer(' + '\"' + usernames[i] + '\"' + ') class="message-icon">\n' +
                '                            <img src="jpg/message.png" alt="message icon">\n' +
                '                        </div>\n' +
                '                        <div class="player-avatar">\n' +
                '                            <img src="avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg">\n' +
                '                            <span>' + usernames[i] + '</span>\n' +
                '                        </div>\n' +
                '                        <div class="suggestion-checkbox">\n' +
                '                            <input type="checkbox" class="regular-checkbox" value="' + usernames[i] + '" />\n' +
                '                        </div>\n' +
                '                    </div>');

        }
        connection.close();
    };
}

var isSeaBattleSelected = false;
var isDotsAndBoxesSelected = false;

function selectSeaBattle() {
    if (!isSeaBattleSelected) {
        document.getElementById('sea-battle-select').style.backgroundColor = '#34f16e';
        document.getElementById('SB-select-button').innerHTML = 'Selected';
        isSeaBattleSelected = true;
    } else {
        document.getElementById('sea-battle-select').style.backgroundColor = '#ddddd9';
        document.getElementById('SB-select-button').innerHTML = 'Select';
        isSeaBattleSelected = false;
    }
}

function selectDotsAndBoxes() {
    if (!isDotsAndBoxesSelected) {
        document.getElementById('dots-and-boxes-select').style.backgroundColor = '#34f16e';
        document.getElementById('DB-select-button').innerHTML = 'Selected';
        isDotsAndBoxesSelected = true;
    } else {
        document.getElementById('dots-and-boxes-select').style.backgroundColor = '#ddddd9';
        document.getElementById('DB-select-button').innerHTML = 'Select';
        isDotsAndBoxesSelected = false;
    }
}

function sendSuggestion() {
    let checkedValue;
    let usernameForSendSuggestion = [];

    if (!(isSeaBattleSelected && isDotsAndBoxesSelected)) {
        for (let i = 0; i < document.querySelectorAll('.regular-checkbox:checked').length; i++) {
            checkedValue = document.querySelectorAll('.regular-checkbox:checked')[i].value;
            usernameForSendSuggestion[i] = checkedValue;
        }
    } else {
        alert("Please select one game for suggestion!");
    }


    const connection = new WebSocket('ws://127.0.0.1:4444');

    if (isSeaBattleSelected) {
        connection.onopen = function () {
            for (let i = 0; i < usernameForSendSuggestion.length; i++) {
                connection.send('user sendSuggestion ' + self.username + ' ' + self.token + ' ' + usernameForSendSuggestion[i] + ' ' + 'BattleSea');

            }
        };
    } else if (isDotsAndBoxesSelected) {
        connection.onopen = function () {
            for (let i = 0; i < usernameForSendSuggestion.length; i++) {
                connection.send('user sendSuggestion ' + self.username + ' ' + self.token + ' ' + usernameForSendSuggestion[i] + ' ' + 'DotsAndBoxes');

            }
        };
    } else {
        alert('Please select a game first!');
    }


    connection.onmessage = function (e) {
        let data = e.data.split(' ');

        if (data.length === 2) {
            alert('All suggestions sent :)');
        } else {
            alert('Server can not send suggestion!');
        }
        connection.close();
    }
}

function unSendSuggestion() {
    let selectedPlayer;
    let usernameForUnSendSuggestion = [];

    if (!(isSeaBattleSelected && isDotsAndBoxesSelected)) {
        for (let i = 0; i < document.querySelectorAll('.regular-checkbox:checked').length; i++) {
            selectedPlayer = document.querySelectorAll('.regular-checkbox:checked')[i].value;
            usernameForUnSendSuggestion[i] = selectedPlayer;
        }
    } else {
        alert("Please select one game for suggestion!");
    }

    const connection = new WebSocket('ws://127.0.0.1:4444');

    if (isSeaBattleSelected) {
        connection.onopen = function () {
            for (let i = 0; i < usernameForUnSendSuggestion.length; i++) {
                connection.send('user unSendSuggestion ' + self.username + ' ' + self.token + ' ' + usernameForUnSendSuggestion[i] + ' ' + 'BattleSea');

            }
        };
    } else if (isDotsAndBoxesSelected) {
        connection.onopen = function () {
            for (let i = 0; i < usernameForUnSendSuggestion.length; i++) {
                connection.send('user unSendSuggestion ' + self.username + ' ' + self.token + ' ' + usernameForUnSendSuggestion[i] + ' ' + 'DotsAndBoxes');

            }
        };
    } else {
        alert('Please select a game first!');
    }


    connection.onmessage = function (e) {
        let data = e.data.split(' ');

        if (data.length === 2) {
            alert('All suggestions unsent :)');
        } else {
            alert('Server can not unSend suggestion!');
        }
        connection.close();
    }
}

function loadSuggestedGames() {
    document.getElementById('suggested-game-situation').style.display = 'block';
    document.getElementById('sea-battle-suggestion-pic').style.display = 'none';
    document.getElementById('dots-and-boxes-suggestion-pic').style.display = 'none';

    next_page('primary', 'player-message');

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadSuggestedGame ' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let data = e.data.split(' ');
        if (data.length === 4) {
            document.getElementById('suggested-game-situation').style.display = 'block';
        } else if (data.length === 5) {
            document.getElementById('sea-battle-suggestion-pic').style.display = 'block';
            document.getElementById('dots-and-boxes-suggestion-pic').style.display = 'block';
            document.getElementById('suggested-game-situation').style.display = 'none';
        } else if (data.length === 2) {
            document.getElementById('sea-battle-suggestion-pic').style.display = 'block';
            document.getElementById('suggested-game-situation').style.display = 'none';
        } else if (data.length === 3) {
            document.getElementById('dots-and-boxes-suggestion-pic').style.display = 'block';
            document.getElementById('suggested-game-situation').style.display = 'none';
        }
        connection.close();
    }

    loadFriendsRequest();
}

function loadFriendsRequest() {
    let friendsRequestList = document.querySelector('.section .player-message .request .friend-request-container');
    friendsRequestList.innerHTML = '';

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadFriendsRequest ' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let friendUsername = e.data.split(' ');

        for (let i = 0; i < friendUsername.length; i++) {
            friendsRequestList.insertAdjacentHTML('beforeend',
                '                    <div class="friend-request" id="' + friendUsername[i] + '">\n' +
                '                        <div class="friend-avatar">\n' +
                '                            <img src="avatar/avatar%20(1).svg">\n' +
                '                            <span>' + friendUsername[i] + '</span>\n' +
                '                        </div>\n' +
                '                        <div class="request-buttons">\n' +
                '                            <div onclick="acceptFriend(' + friendUsername[i] + ')" class="accept-button"><span>accept</span></div>\n' +
                '                            <div onclick="declineFriend(' + friendUsername[i] + ')" class="decline-button"><span>decline</span></div>\n' +
                '                        </div>\n' +
                '                    </div>');
        }

        connection.close();
    }
}

function acceptFriend(playerUsername) {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user acceptFriend ' + username + ' ' + token + ' ' + playerUsername);
    };

    connection.onmessage = function (e) {
        let data = e.data.split(' ');

        if (data.length === 2) {
            document.getElementById(playerUsername).style.display = 'none';
            alert(playerUsername + ' now is your friend!');
        }

        connection.close();
    }
}

function declineFriend(playerUsername) {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user declineFriend ' + username + ' ' + token + ' ' + playerUsername);
    };

    connection.onmessage = function (e) {
        let data = e.data.split(' ');

        if (data.length === 2) {
            document.getElementById(playerUsername).style.display = 'none';
            alert(playerUsername + ' removed from your requests.');
        }

        connection.close();
    }
}

function chatWithPlayer(playerUsername) {
    let chatBox = document.querySelector('section .admin-inbox .chatBox');
    chatBox.innerHTML = '';
    chatBox.insertAdjacentHTML('beforeend',
        '                    <div class="inboxHeader">\n' +
        '                        <p>' + playerUsername + '</p>\n' +
        '                    </div>\n' +
        '                    <div class="messageArea">\n' +
        '                    </div>\n' +
        '                    <div class="messageField">\n' +
        '                        <div>\n' +
        '                            <textarea id="text-area" placeholder="Say hello..."></textarea>\n' +
        '                        </div>\n' +
        '                        <div id="send-button" onclick=adminSendMessage(' + '\"' + playerUsername + '\"' + ')>\n' +
        '                            <svg height="512pt" viewBox="0 -18 512 512" width="512pt"\n' +
        '                                xmlns="http://www.w3.org/2000/svg">\n' +
        '                                <path d="m0 475.445312 59.078125-237.714843-59.078125-237.730469 512 237.730469zm0 0"\n' +
        '                                    fill="#ffcb5a" />\n' +
        '                                <path d="m0 475.445312 512-237.714843h-452.921875zm0 0" fill="#fba61f" />\n' +
        '                            </svg>\n' +
        '                        </div>\n' +
        '                    </div>');

    let messageArea = document.querySelector('section .admin-inbox .chatBox .messageArea');
    messageArea.innerHTML = '';

    messageArea.insertAdjacentHTML('beforeend', '<div class="messageHistory">\n' +
        '                            <div id="messageDate">\n' +
        '                                <span id="month">January</span>\n' +
        '                                <span id="day">24</span>\n' +
        '                            </div>\n' +
        '                        </div>');

    document.getElementById('messageDate').style.display = 'none';
    next_page('suggestion', 'admin-inbox');

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadChatHistory ' + username + ' ' + token + ' ' + playerUsername);
    };

    connection.onmessage = function (e) {
        let messages = e.data.split('-');

        if (messages.length > 1) {
            document.getElementById('month').innerHTML = messages[messages.length - 1].split('/')[0];
            document.getElementById('day').innerHTML = messages[messages.length - 1].split('/')[1];
            document.getElementById('messageDate').style.display = 'block';
        }

        for (let i = 0; i < messages.length - 1; i++) {
            messageArea.insertAdjacentHTML('beforeend',
                '                    <div class="admin-avatar-message">\n' +
                '                        <div class="admin-messageBox">\n' +
                '                            <div>\n' +
                '                                <p>' + messages[i].split('/')[0] + '</p>\n' +
                '                            </div>\n' +
                '                            <div>' + messages[i].split('/')[1] + '</div>\n' +
                '                        </div>\n' +
                '                        <div>\n' +
                '                            <svg id="Capa_1" enable-background="new 0 0 512 512" height="512" viewBox="0 0 512 512" width="512" xmlns="http://www.w3.org/2000/svg"><g><g><path d="m41.725 300.851h32.39v-125.824h-32.39c-15.825 0-28.653 12.828-28.653 28.653v68.518c0 15.825 12.828 28.653 28.653 28.653z" fill="#96b4eb"/>\n' +
                '                                <path d="m470.275 300.851h-32.39v-125.824h32.39c15.825 0 28.653 12.828 28.653 28.653v68.518c0 15.825-12.828 28.653-28.653 28.653z" fill="#96b4eb"/><path d="m326.342 79.774h-140.915c-67.851 0-122.855 55.005-122.855 122.855v70.619c0 58.011 40.21 106.624 94.277 119.506-16.003 20.952-25.515 47.127-25.515 75.527v33.672h249.102v-33.672c0-28.4-9.512-54.574-25.515-75.527 54.067-12.882 94.277-61.495 94.277-119.506v-70.618c-.001-67.851-55.005-122.856-122.856-122.856z" fill="#c1e5ef"/><path d="m326.342 79.774h-38.361c57.983 0 104.988 47.005 104.988 104.988v60.349c0 57.983-47.005 104.988-104.988 104.988h-120.421c-57.983 0-104.988-47.004-104.988-104.988v28.138c0 67.851 55.004 122.855 122.855 122.855h92.874c16.527 13.081 27.271 34.218 27.271 58.084v7.208c0 11.22-9.095 20.316-20.315 20.316h-112.512v20.532h166.28v-31.815c0-32.519-18.678-60.662-45.885-74.324h33.202c67.851 0 122.855-55.004 122.855-122.855v-70.62c0-67.852-55.004-122.856-122.855-122.856z" fill="#a6aaed"/><g fill="#fff"><circle cx="161.523" cy="303.245" r="17.657"/><circle cx="350.247" cy="303.245" r="17.657"/><path d="m290.908 332.959h-70.046c-11.535 0-20.885-9.351-20.885-20.885 0-11.535 9.351-20.885 20.885-20.885h70.046c11.535 0 20.885 9.351 20.885 20.885 0 11.534-9.35 20.885-20.885 20.885z"/></g><circle cx="255.885" cy="38.626" fill="#d789b9" r="28.579"/><path d="m450.09 4.559 8.253 23.205c.353.993 1.053 1.781 1.936 2.179l20.617 9.289c2.913 1.312 2.913 5.953 0 7.265l-20.617 9.289c-.883.398-1.582 1.185-1.936 2.178l-8.253 23.206c-1.166 3.278-5.289 3.278-6.455 0l-8.253-23.205c-.353-.993-1.053-1.781-1.936-2.178l-20.617-9.289c-2.913-1.312-2.913-5.953 0-7.265l20.617-9.289c.883-.398 1.582-1.185 1.936-2.179l8.253-23.205c1.166-3.28 5.289-3.28 6.455-.001z" fill="#c1e5ef"/><path d="m34.48 372.731 6.122 17.213c.262.737.781 1.321 1.436 1.616l15.293 6.89c2.161.974 2.161 4.416 0 5.389l-15.293 6.89c-.655.295-1.174.879-1.436 1.616l-6.122 17.215c-.865 2.432-3.923 2.432-4.788 0l-6.122-17.213c-.262-.737-.781-1.321-1.436-1.616l-15.293-6.89c-2.161-.974-2.161-4.416 0-5.389l15.293-6.89c.655-.295 1.174-.879 1.436-1.616l6.122-17.213c.865-2.434 3.924-2.434 4.788-.002z" fill="#c1e5ef"/><ellipse cx="469.08" cy="442.246" fill="#96b4eb" rx="18.02" ry="18.02" transform="matrix(.099 -.995 .995 .099 -17.236 865.465)"/><circle cx="76.929" cy="458.837" fill="#96b4eb" r="7.773"/></g><g><g fill="#3c122c"><path d="m290.908 285.16h-70.047c-14.84 0-26.913 12.074-26.913 26.914s12.073 26.913 26.913 26.913h70.047c14.84 0 26.914-12.074 26.914-26.913-.001-14.84-12.074-26.914-26.914-26.914zm-58.919 12.056h17.867v29.715h-17.867zm29.924 0h17.785v29.715h-17.785zm-55.909 14.858c0-7.879 6.17-14.328 13.93-14.811v29.621c-7.76-.483-13.93-6.931-13.93-14.81zm85.75 14.814v-29.63c7.798.443 14.011 6.909 14.011 14.815 0 7.908-6.213 14.373-14.011 14.815z"/><path d="m129.182 182.747c-10.222 0-18.538-8.316-18.538-18.538 0-10.221 8.316-18.537 18.538-18.537s18.538 8.316 18.538 18.537c0 10.223-8.316 18.538-18.538 18.538zm0-25.019c-3.574 0-6.482 2.907-6.482 6.481s2.908 6.482 6.482 6.482 6.482-2.908 6.482-6.482-2.908-6.481-6.482-6.481z"/><path d="m129.182 236.657c-10.222 0-18.538-8.316-18.538-18.537s8.316-18.537 18.538-18.537 18.538 8.316 18.538 18.537-8.316 18.537-18.538 18.537zm0-25.018c-3.574 0-6.482 2.907-6.482 6.481s2.908 6.481 6.482 6.481 6.482-2.907 6.482-6.481-2.908-6.481-6.482-6.481z"/><path d="m406.25 182.166h-64.086c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h64.086c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m243.252 458.03h-40.999c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h40.999c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m243.252 484.838h-40.999c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h40.999c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m470.275 164.98h-16.471c-16.275-55.003-67.253-95.253-127.462-95.253h-47.586c9.547-7.039 15.754-18.357 15.754-31.102 0-21.297-17.327-38.625-38.625-38.625s-38.626 17.328-38.626 38.626c0 12.744 6.208 24.062 15.754 31.102h-47.586c-60.21 0-111.187 40.25-127.462 95.253h-16.24c-21.339 0-38.699 17.36-38.699 38.699v68.519c0 21.338 17.36 38.699 38.699 38.699h16.241c12.052 40.731 43.133 73.365 82.904 87.561-12.415 20.37-19.582 44.273-19.582 69.823v33.672c0 5.548 4.498 10.047 10.047 10.047s10.047-4.498 10.047-10.047v-33.672c0-23.841 7.341-45.991 19.858-64.343 7.846 1.449 15.93 2.213 24.189 2.213h8.051c-16.504 16.191-26.761 38.728-26.761 63.62v35.995c0 3.329 2.699 6.028 6.028 6.028s6.028-2.699 6.028-6.028v-35.995c0-26.396 13.343-49.716 33.628-63.62h86.968c9.425 6.46 17.347 14.952 23.131 24.844h-26.241c-10.714 0-19.431 8.716-19.431 19.431v14.981c0 10.715 8.717 19.431 19.431 19.431h36.738v20.928c0 3.329 2.699 6.028 6.028 6.028s6.028-2.699 6.028-6.028v-35.995c0-24.892-10.257-47.428-26.761-63.62h8.05c8.258 0 16.339-.764 24.184-2.212 12.518 18.352 19.863 40.501 19.863 64.342v33.672c0 5.548 4.498 10.047 10.047 10.047s10.047-4.498 10.047-10.047v-33.672c0-25.552-7.182-49.447-19.598-69.817 39.778-14.193 70.866-46.83 82.92-87.566h16.471c21.339 0 38.7-17.361 38.7-38.699v-68.52c-.003-21.34-17.364-38.7-38.703-38.7zm-416.589 125.824h-11.961c-10.26 0-18.606-8.346-18.606-18.606v-68.518c0-10.26 8.346-18.606 18.606-18.606h11.962c-.762 5.746-1.161 11.605-1.161 17.556v70.619c-.001 5.951.399 11.809 1.16 17.555zm279.31 178.967v3.011h-36.738c-4.067 0-7.375-3.308-7.375-7.375v-14.981c0-4.067 3.308-7.375 7.375-7.375h31.952c3.089 8.328 4.786 17.328 4.786 26.72zm-6.654-379.95c40.149 0 75.461 21.087 95.458 52.764h-97.292v-52.764zm-70.457-69.728c10.219 0 18.532 8.314 18.532 18.532s-8.314 18.532-18.532 18.532c-10.219 0-18.532-8.314-18.532-18.532-.001-10.218 8.313-18.532 18.532-18.532zm-56.567 69.728h113.134v87.269c0 12.763-10.384 23.147-23.147 23.147h-66.84c-12.763 0-23.147-10.383-23.147-23.147zm-49.424 213.424c0-6.412 5.217-11.628 11.629-11.628s11.629 5.217 11.629 11.628c0 6.413-5.217 11.629-11.629 11.629-6.413 0-11.629-5.216-11.629-11.629zm200.353 11.629c-6.412 0-11.629-5.217-11.629-11.629s5.217-11.628 11.629-11.628 11.629 5.217 11.629 11.628c-.001 6.413-5.217 11.629-11.629 11.629zm88.904-41.625c0 51.843-35.155 95.622-82.876 108.77v-55.869c10.149-2.674 17.657-11.928 17.657-22.905 0-13.06-10.625-23.684-23.685-23.684s-23.685 10.625-23.685 23.684c0 10.977 7.508 20.232 17.657 22.905v58.486c-5.824.931-11.794 1.422-17.876 1.422h-140.916c-6.083 0-12.052-.491-17.876-1.422v-58.486c10.149-2.673 17.657-11.928 17.657-22.905 0-13.06-10.625-23.684-23.685-23.684s-23.685 10.625-23.685 23.684c0 10.977 7.507 20.232 17.657 22.905v55.868c-47.721-13.147-82.876-56.927-82.876-108.769v-70.619c0-62.203 50.606-112.809 112.809-112.809h1.834v87.269c0 19.41 15.792 35.203 35.203 35.203h66.84c19.411 0 35.203-15.792 35.203-35.203v-22.45h103.911c6.878 14.571 10.732 30.838 10.732 47.99zm49.73-1.051c0 10.26-8.347 18.606-18.606 18.606h-12.192c.762-5.746 1.161-11.605 1.161-17.555v-70.619c0-5.951-.4-11.81-1.161-17.556h12.192c10.26 0 18.606 8.346 18.606 18.606z"/></g><path d="m225.291 174.362c-5.549 0-10.047-4.498-10.047-10.047v-15.875c0-5.548 4.498-10.047 10.047-10.047s10.047 4.498 10.047 10.047v15.876c0 5.547-4.498 10.046-10.047 10.046z" fill="#fff"/><path d="m225.291 129.552c-5.549 0-10.047-4.498-10.047-10.047v-4.537c0-5.548 4.498-10.047 10.047-10.047s10.047 4.498 10.047 10.047v4.537c0 5.549-4.498 10.047-10.047 10.047z" fill="#fff"/></g></g>\n' +
                '                            </svg>\n' +
                '                        </div>\n' +
                '                    </div>');
        }

        let test = document.getElementById('test');
        console.log(test);

        connection.close();
    }
}

function adminSendMessage(playerUsername) {
    let messageArea = document.querySelector('section .admin-inbox .chatBox .messageArea');
    let messageFromTextArea = document.getElementById('text-area').value;

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user sendMessage ' + username + ' ' + token + ' ' + playerUsername + ' ' + messageFromTextArea);
    };

    connection.onmessage = function (e) {
        let messageContent = e.data.split('/')[0];
        let messageTime = e.data.split('/')[1];
        let month = e.data.split('/')[2];
        let day = e.data.split('/')[3];

        document.getElementById('month').innerHTML = month;
        document.getElementById('day').innerHTML = day;
        document.getElementById('messageDate').style.display = 'block';

        messageArea.insertAdjacentHTML('beforeend',
            '                    <div class="admin-avatar-message">\n' +
            '                        <div class="admin-messageBox">\n' +
            '                            <div>\n' +
            '                                <p>' + messageContent + '</p>\n' +
            '                            </div>\n' +
            '                            <div>' + messageTime + '</div>\n' +
            '                        </div>\n' +
            '                        <div>\n' +
            '                            <svg id="Capa_1" enable-background="new 0 0 512 512" height="512" viewBox="0 0 512 512" width="512" xmlns="http://www.w3.org/2000/svg"><g><g><path d="m41.725 300.851h32.39v-125.824h-32.39c-15.825 0-28.653 12.828-28.653 28.653v68.518c0 15.825 12.828 28.653 28.653 28.653z" fill="#96b4eb"/>\n' +
            '                                <path d="m470.275 300.851h-32.39v-125.824h32.39c15.825 0 28.653 12.828 28.653 28.653v68.518c0 15.825-12.828 28.653-28.653 28.653z" fill="#96b4eb"/><path d="m326.342 79.774h-140.915c-67.851 0-122.855 55.005-122.855 122.855v70.619c0 58.011 40.21 106.624 94.277 119.506-16.003 20.952-25.515 47.127-25.515 75.527v33.672h249.102v-33.672c0-28.4-9.512-54.574-25.515-75.527 54.067-12.882 94.277-61.495 94.277-119.506v-70.618c-.001-67.851-55.005-122.856-122.856-122.856z" fill="#c1e5ef"/><path d="m326.342 79.774h-38.361c57.983 0 104.988 47.005 104.988 104.988v60.349c0 57.983-47.005 104.988-104.988 104.988h-120.421c-57.983 0-104.988-47.004-104.988-104.988v28.138c0 67.851 55.004 122.855 122.855 122.855h92.874c16.527 13.081 27.271 34.218 27.271 58.084v7.208c0 11.22-9.095 20.316-20.315 20.316h-112.512v20.532h166.28v-31.815c0-32.519-18.678-60.662-45.885-74.324h33.202c67.851 0 122.855-55.004 122.855-122.855v-70.62c0-67.852-55.004-122.856-122.855-122.856z" fill="#a6aaed"/><g fill="#fff"><circle cx="161.523" cy="303.245" r="17.657"/><circle cx="350.247" cy="303.245" r="17.657"/><path d="m290.908 332.959h-70.046c-11.535 0-20.885-9.351-20.885-20.885 0-11.535 9.351-20.885 20.885-20.885h70.046c11.535 0 20.885 9.351 20.885 20.885 0 11.534-9.35 20.885-20.885 20.885z"/></g><circle cx="255.885" cy="38.626" fill="#d789b9" r="28.579"/><path d="m450.09 4.559 8.253 23.205c.353.993 1.053 1.781 1.936 2.179l20.617 9.289c2.913 1.312 2.913 5.953 0 7.265l-20.617 9.289c-.883.398-1.582 1.185-1.936 2.178l-8.253 23.206c-1.166 3.278-5.289 3.278-6.455 0l-8.253-23.205c-.353-.993-1.053-1.781-1.936-2.178l-20.617-9.289c-2.913-1.312-2.913-5.953 0-7.265l20.617-9.289c.883-.398 1.582-1.185 1.936-2.179l8.253-23.205c1.166-3.28 5.289-3.28 6.455-.001z" fill="#c1e5ef"/><path d="m34.48 372.731 6.122 17.213c.262.737.781 1.321 1.436 1.616l15.293 6.89c2.161.974 2.161 4.416 0 5.389l-15.293 6.89c-.655.295-1.174.879-1.436 1.616l-6.122 17.215c-.865 2.432-3.923 2.432-4.788 0l-6.122-17.213c-.262-.737-.781-1.321-1.436-1.616l-15.293-6.89c-2.161-.974-2.161-4.416 0-5.389l15.293-6.89c.655-.295 1.174-.879 1.436-1.616l6.122-17.213c.865-2.434 3.924-2.434 4.788-.002z" fill="#c1e5ef"/><ellipse cx="469.08" cy="442.246" fill="#96b4eb" rx="18.02" ry="18.02" transform="matrix(.099 -.995 .995 .099 -17.236 865.465)"/><circle cx="76.929" cy="458.837" fill="#96b4eb" r="7.773"/></g><g><g fill="#3c122c"><path d="m290.908 285.16h-70.047c-14.84 0-26.913 12.074-26.913 26.914s12.073 26.913 26.913 26.913h70.047c14.84 0 26.914-12.074 26.914-26.913-.001-14.84-12.074-26.914-26.914-26.914zm-58.919 12.056h17.867v29.715h-17.867zm29.924 0h17.785v29.715h-17.785zm-55.909 14.858c0-7.879 6.17-14.328 13.93-14.811v29.621c-7.76-.483-13.93-6.931-13.93-14.81zm85.75 14.814v-29.63c7.798.443 14.011 6.909 14.011 14.815 0 7.908-6.213 14.373-14.011 14.815z"/><path d="m129.182 182.747c-10.222 0-18.538-8.316-18.538-18.538 0-10.221 8.316-18.537 18.538-18.537s18.538 8.316 18.538 18.537c0 10.223-8.316 18.538-18.538 18.538zm0-25.019c-3.574 0-6.482 2.907-6.482 6.481s2.908 6.482 6.482 6.482 6.482-2.908 6.482-6.482-2.908-6.481-6.482-6.481z"/><path d="m129.182 236.657c-10.222 0-18.538-8.316-18.538-18.537s8.316-18.537 18.538-18.537 18.538 8.316 18.538 18.537-8.316 18.537-18.538 18.537zm0-25.018c-3.574 0-6.482 2.907-6.482 6.481s2.908 6.481 6.482 6.481 6.482-2.907 6.482-6.481-2.908-6.481-6.482-6.481z"/><path d="m406.25 182.166h-64.086c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h64.086c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m243.252 458.03h-40.999c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h40.999c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m243.252 484.838h-40.999c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h40.999c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m470.275 164.98h-16.471c-16.275-55.003-67.253-95.253-127.462-95.253h-47.586c9.547-7.039 15.754-18.357 15.754-31.102 0-21.297-17.327-38.625-38.625-38.625s-38.626 17.328-38.626 38.626c0 12.744 6.208 24.062 15.754 31.102h-47.586c-60.21 0-111.187 40.25-127.462 95.253h-16.24c-21.339 0-38.699 17.36-38.699 38.699v68.519c0 21.338 17.36 38.699 38.699 38.699h16.241c12.052 40.731 43.133 73.365 82.904 87.561-12.415 20.37-19.582 44.273-19.582 69.823v33.672c0 5.548 4.498 10.047 10.047 10.047s10.047-4.498 10.047-10.047v-33.672c0-23.841 7.341-45.991 19.858-64.343 7.846 1.449 15.93 2.213 24.189 2.213h8.051c-16.504 16.191-26.761 38.728-26.761 63.62v35.995c0 3.329 2.699 6.028 6.028 6.028s6.028-2.699 6.028-6.028v-35.995c0-26.396 13.343-49.716 33.628-63.62h86.968c9.425 6.46 17.347 14.952 23.131 24.844h-26.241c-10.714 0-19.431 8.716-19.431 19.431v14.981c0 10.715 8.717 19.431 19.431 19.431h36.738v20.928c0 3.329 2.699 6.028 6.028 6.028s6.028-2.699 6.028-6.028v-35.995c0-24.892-10.257-47.428-26.761-63.62h8.05c8.258 0 16.339-.764 24.184-2.212 12.518 18.352 19.863 40.501 19.863 64.342v33.672c0 5.548 4.498 10.047 10.047 10.047s10.047-4.498 10.047-10.047v-33.672c0-25.552-7.182-49.447-19.598-69.817 39.778-14.193 70.866-46.83 82.92-87.566h16.471c21.339 0 38.7-17.361 38.7-38.699v-68.52c-.003-21.34-17.364-38.7-38.703-38.7zm-416.589 125.824h-11.961c-10.26 0-18.606-8.346-18.606-18.606v-68.518c0-10.26 8.346-18.606 18.606-18.606h11.962c-.762 5.746-1.161 11.605-1.161 17.556v70.619c-.001 5.951.399 11.809 1.16 17.555zm279.31 178.967v3.011h-36.738c-4.067 0-7.375-3.308-7.375-7.375v-14.981c0-4.067 3.308-7.375 7.375-7.375h31.952c3.089 8.328 4.786 17.328 4.786 26.72zm-6.654-379.95c40.149 0 75.461 21.087 95.458 52.764h-97.292v-52.764zm-70.457-69.728c10.219 0 18.532 8.314 18.532 18.532s-8.314 18.532-18.532 18.532c-10.219 0-18.532-8.314-18.532-18.532-.001-10.218 8.313-18.532 18.532-18.532zm-56.567 69.728h113.134v87.269c0 12.763-10.384 23.147-23.147 23.147h-66.84c-12.763 0-23.147-10.383-23.147-23.147zm-49.424 213.424c0-6.412 5.217-11.628 11.629-11.628s11.629 5.217 11.629 11.628c0 6.413-5.217 11.629-11.629 11.629-6.413 0-11.629-5.216-11.629-11.629zm200.353 11.629c-6.412 0-11.629-5.217-11.629-11.629s5.217-11.628 11.629-11.628 11.629 5.217 11.629 11.628c-.001 6.413-5.217 11.629-11.629 11.629zm88.904-41.625c0 51.843-35.155 95.622-82.876 108.77v-55.869c10.149-2.674 17.657-11.928 17.657-22.905 0-13.06-10.625-23.684-23.685-23.684s-23.685 10.625-23.685 23.684c0 10.977 7.508 20.232 17.657 22.905v58.486c-5.824.931-11.794 1.422-17.876 1.422h-140.916c-6.083 0-12.052-.491-17.876-1.422v-58.486c10.149-2.673 17.657-11.928 17.657-22.905 0-13.06-10.625-23.684-23.685-23.684s-23.685 10.625-23.685 23.684c0 10.977 7.507 20.232 17.657 22.905v55.868c-47.721-13.147-82.876-56.927-82.876-108.769v-70.619c0-62.203 50.606-112.809 112.809-112.809h1.834v87.269c0 19.41 15.792 35.203 35.203 35.203h66.84c19.411 0 35.203-15.792 35.203-35.203v-22.45h103.911c6.878 14.571 10.732 30.838 10.732 47.99zm49.73-1.051c0 10.26-8.347 18.606-18.606 18.606h-12.192c.762-5.746 1.161-11.605 1.161-17.555v-70.619c0-5.951-.4-11.81-1.161-17.556h12.192c10.26 0 18.606 8.346 18.606 18.606z"/></g><path d="m225.291 174.362c-5.549 0-10.047-4.498-10.047-10.047v-15.875c0-5.548 4.498-10.047 10.047-10.047s10.047 4.498 10.047 10.047v15.876c0 5.547-4.498 10.046-10.047 10.046z" fill="#fff"/><path d="m225.291 129.552c-5.549 0-10.047-4.498-10.047-10.047v-4.537c0-5.548 4.498-10.047 10.047-10.047s10.047 4.498 10.047 10.047v4.537c0 5.549-4.498 10.047-10.047 10.047z" fill="#fff"/></g></g>\n' +
            '                            </svg>\n' +
            '                        </div>\n' +
            '                    </div>');

        document.getElementById('text-area').value = '';
        connection.close();
    }
}

function loadPlatoBotMessage() {
    let messageArea = document.querySelector('section .inbox .chatBox .messageArea');
    messageArea.innerHTML = '';

    next_page('player-message', 'inbox');

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadPlatoBotMessage ' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let allMessages = e.data.split('-');

        // let month = allMessages[1].split('/')[1];
        // let day = allMessages[1].split('/')[2];
        //
        // document.getElementById('p-month').innerHTML = month;
        // document.getElementById('p-day').innerHTML = day;
        // document.getElementById('message-date').style.display = 'block';


        for (let i = 0; i < allMessages.length; i++) {
            let message = allMessages[i].split('/')[0].split('@')[0];
            let time = allMessages[i].split('/')[0].split('@')[1];
            messageArea.insertAdjacentHTML('beforeend',
                '                    <div class="avatar-message">\n' +
                '                        <div>\n' +
                '                            <svg id="Capa_1" enable-background="new 0 0 512 512" height="512" viewBox="0 0 512 512" width="512" xmlns="http://www.w3.org/2000/svg"><g><g><path d="m41.725 300.851h32.39v-125.824h-32.39c-15.825 0-28.653 12.828-28.653 28.653v68.518c0 15.825 12.828 28.653 28.653 28.653z" fill="#96b4eb"/>\n' +
                '                                <path d="m470.275 300.851h-32.39v-125.824h32.39c15.825 0 28.653 12.828 28.653 28.653v68.518c0 15.825-12.828 28.653-28.653 28.653z" fill="#96b4eb"/><path d="m326.342 79.774h-140.915c-67.851 0-122.855 55.005-122.855 122.855v70.619c0 58.011 40.21 106.624 94.277 119.506-16.003 20.952-25.515 47.127-25.515 75.527v33.672h249.102v-33.672c0-28.4-9.512-54.574-25.515-75.527 54.067-12.882 94.277-61.495 94.277-119.506v-70.618c-.001-67.851-55.005-122.856-122.856-122.856z" fill="#c1e5ef"/><path d="m326.342 79.774h-38.361c57.983 0 104.988 47.005 104.988 104.988v60.349c0 57.983-47.005 104.988-104.988 104.988h-120.421c-57.983 0-104.988-47.004-104.988-104.988v28.138c0 67.851 55.004 122.855 122.855 122.855h92.874c16.527 13.081 27.271 34.218 27.271 58.084v7.208c0 11.22-9.095 20.316-20.315 20.316h-112.512v20.532h166.28v-31.815c0-32.519-18.678-60.662-45.885-74.324h33.202c67.851 0 122.855-55.004 122.855-122.855v-70.62c0-67.852-55.004-122.856-122.855-122.856z" fill="#a6aaed"/><g fill="#fff"><circle cx="161.523" cy="303.245" r="17.657"/><circle cx="350.247" cy="303.245" r="17.657"/><path d="m290.908 332.959h-70.046c-11.535 0-20.885-9.351-20.885-20.885 0-11.535 9.351-20.885 20.885-20.885h70.046c11.535 0 20.885 9.351 20.885 20.885 0 11.534-9.35 20.885-20.885 20.885z"/></g><circle cx="255.885" cy="38.626" fill="#d789b9" r="28.579"/><path d="m450.09 4.559 8.253 23.205c.353.993 1.053 1.781 1.936 2.179l20.617 9.289c2.913 1.312 2.913 5.953 0 7.265l-20.617 9.289c-.883.398-1.582 1.185-1.936 2.178l-8.253 23.206c-1.166 3.278-5.289 3.278-6.455 0l-8.253-23.205c-.353-.993-1.053-1.781-1.936-2.178l-20.617-9.289c-2.913-1.312-2.913-5.953 0-7.265l20.617-9.289c.883-.398 1.582-1.185 1.936-2.179l8.253-23.205c1.166-3.28 5.289-3.28 6.455-.001z" fill="#c1e5ef"/><path d="m34.48 372.731 6.122 17.213c.262.737.781 1.321 1.436 1.616l15.293 6.89c2.161.974 2.161 4.416 0 5.389l-15.293 6.89c-.655.295-1.174.879-1.436 1.616l-6.122 17.215c-.865 2.432-3.923 2.432-4.788 0l-6.122-17.213c-.262-.737-.781-1.321-1.436-1.616l-15.293-6.89c-2.161-.974-2.161-4.416 0-5.389l15.293-6.89c.655-.295 1.174-.879 1.436-1.616l6.122-17.213c.865-2.434 3.924-2.434 4.788-.002z" fill="#c1e5ef"/><ellipse cx="469.08" cy="442.246" fill="#96b4eb" rx="18.02" ry="18.02" transform="matrix(.099 -.995 .995 .099 -17.236 865.465)"/><circle cx="76.929" cy="458.837" fill="#96b4eb" r="7.773"/></g><g><g fill="#3c122c"><path d="m290.908 285.16h-70.047c-14.84 0-26.913 12.074-26.913 26.914s12.073 26.913 26.913 26.913h70.047c14.84 0 26.914-12.074 26.914-26.913-.001-14.84-12.074-26.914-26.914-26.914zm-58.919 12.056h17.867v29.715h-17.867zm29.924 0h17.785v29.715h-17.785zm-55.909 14.858c0-7.879 6.17-14.328 13.93-14.811v29.621c-7.76-.483-13.93-6.931-13.93-14.81zm85.75 14.814v-29.63c7.798.443 14.011 6.909 14.011 14.815 0 7.908-6.213 14.373-14.011 14.815z"/><path d="m129.182 182.747c-10.222 0-18.538-8.316-18.538-18.538 0-10.221 8.316-18.537 18.538-18.537s18.538 8.316 18.538 18.537c0 10.223-8.316 18.538-18.538 18.538zm0-25.019c-3.574 0-6.482 2.907-6.482 6.481s2.908 6.482 6.482 6.482 6.482-2.908 6.482-6.482-2.908-6.481-6.482-6.481z"/><path d="m129.182 236.657c-10.222 0-18.538-8.316-18.538-18.537s8.316-18.537 18.538-18.537 18.538 8.316 18.538 18.537-8.316 18.537-18.538 18.537zm0-25.018c-3.574 0-6.482 2.907-6.482 6.481s2.908 6.481 6.482 6.481 6.482-2.907 6.482-6.481-2.908-6.481-6.482-6.481z"/><path d="m406.25 182.166h-64.086c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h64.086c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m243.252 458.03h-40.999c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h40.999c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m243.252 484.838h-40.999c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h40.999c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m470.275 164.98h-16.471c-16.275-55.003-67.253-95.253-127.462-95.253h-47.586c9.547-7.039 15.754-18.357 15.754-31.102 0-21.297-17.327-38.625-38.625-38.625s-38.626 17.328-38.626 38.626c0 12.744 6.208 24.062 15.754 31.102h-47.586c-60.21 0-111.187 40.25-127.462 95.253h-16.24c-21.339 0-38.699 17.36-38.699 38.699v68.519c0 21.338 17.36 38.699 38.699 38.699h16.241c12.052 40.731 43.133 73.365 82.904 87.561-12.415 20.37-19.582 44.273-19.582 69.823v33.672c0 5.548 4.498 10.047 10.047 10.047s10.047-4.498 10.047-10.047v-33.672c0-23.841 7.341-45.991 19.858-64.343 7.846 1.449 15.93 2.213 24.189 2.213h8.051c-16.504 16.191-26.761 38.728-26.761 63.62v35.995c0 3.329 2.699 6.028 6.028 6.028s6.028-2.699 6.028-6.028v-35.995c0-26.396 13.343-49.716 33.628-63.62h86.968c9.425 6.46 17.347 14.952 23.131 24.844h-26.241c-10.714 0-19.431 8.716-19.431 19.431v14.981c0 10.715 8.717 19.431 19.431 19.431h36.738v20.928c0 3.329 2.699 6.028 6.028 6.028s6.028-2.699 6.028-6.028v-35.995c0-24.892-10.257-47.428-26.761-63.62h8.05c8.258 0 16.339-.764 24.184-2.212 12.518 18.352 19.863 40.501 19.863 64.342v33.672c0 5.548 4.498 10.047 10.047 10.047s10.047-4.498 10.047-10.047v-33.672c0-25.552-7.182-49.447-19.598-69.817 39.778-14.193 70.866-46.83 82.92-87.566h16.471c21.339 0 38.7-17.361 38.7-38.699v-68.52c-.003-21.34-17.364-38.7-38.703-38.7zm-416.589 125.824h-11.961c-10.26 0-18.606-8.346-18.606-18.606v-68.518c0-10.26 8.346-18.606 18.606-18.606h11.962c-.762 5.746-1.161 11.605-1.161 17.556v70.619c-.001 5.951.399 11.809 1.16 17.555zm279.31 178.967v3.011h-36.738c-4.067 0-7.375-3.308-7.375-7.375v-14.981c0-4.067 3.308-7.375 7.375-7.375h31.952c3.089 8.328 4.786 17.328 4.786 26.72zm-6.654-379.95c40.149 0 75.461 21.087 95.458 52.764h-97.292v-52.764zm-70.457-69.728c10.219 0 18.532 8.314 18.532 18.532s-8.314 18.532-18.532 18.532c-10.219 0-18.532-8.314-18.532-18.532-.001-10.218 8.313-18.532 18.532-18.532zm-56.567 69.728h113.134v87.269c0 12.763-10.384 23.147-23.147 23.147h-66.84c-12.763 0-23.147-10.383-23.147-23.147zm-49.424 213.424c0-6.412 5.217-11.628 11.629-11.628s11.629 5.217 11.629 11.628c0 6.413-5.217 11.629-11.629 11.629-6.413 0-11.629-5.216-11.629-11.629zm200.353 11.629c-6.412 0-11.629-5.217-11.629-11.629s5.217-11.628 11.629-11.628 11.629 5.217 11.629 11.628c-.001 6.413-5.217 11.629-11.629 11.629zm88.904-41.625c0 51.843-35.155 95.622-82.876 108.77v-55.869c10.149-2.674 17.657-11.928 17.657-22.905 0-13.06-10.625-23.684-23.685-23.684s-23.685 10.625-23.685 23.684c0 10.977 7.508 20.232 17.657 22.905v58.486c-5.824.931-11.794 1.422-17.876 1.422h-140.916c-6.083 0-12.052-.491-17.876-1.422v-58.486c10.149-2.673 17.657-11.928 17.657-22.905 0-13.06-10.625-23.684-23.685-23.684s-23.685 10.625-23.685 23.684c0 10.977 7.507 20.232 17.657 22.905v55.868c-47.721-13.147-82.876-56.927-82.876-108.769v-70.619c0-62.203 50.606-112.809 112.809-112.809h1.834v87.269c0 19.41 15.792 35.203 35.203 35.203h66.84c19.411 0 35.203-15.792 35.203-35.203v-22.45h103.911c6.878 14.571 10.732 30.838 10.732 47.99zm49.73-1.051c0 10.26-8.347 18.606-18.606 18.606h-12.192c.762-5.746 1.161-11.605 1.161-17.555v-70.619c0-5.951-.4-11.81-1.161-17.556h12.192c10.26 0 18.606 8.346 18.606 18.606z"/></g><path d="m225.291 174.362c-5.549 0-10.047-4.498-10.047-10.047v-15.875c0-5.548 4.498-10.047 10.047-10.047s10.047 4.498 10.047 10.047v15.876c0 5.547-4.498 10.046-10.047 10.046z" fill="#fff"/><path d="m225.291 129.552c-5.549 0-10.047-4.498-10.047-10.047v-4.537c0-5.548 4.498-10.047 10.047-10.047s10.047 4.498 10.047 10.047v4.537c0 5.549-4.498 10.047-10.047 10.047z" fill="#fff"/></g></g>\n' +
                '                            </svg>\n' +
                '                        </div>\n' +
                '                        <div class="messageBox">\n' +
                '                            <div>\n' +
                '                                <p>' + message + '</p>\n' +
                '                            </div>\n' +
                '                            <div>' + time + '</div>\n' +
                '                        </div>\n' +
                '                    </div>');
        }
        console.log('insert shod :/');
        connection.close();
    };
}

function playSeaBattleGameFromSuggestion() {
    next_page('player-message', 'games-menu');
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("dotsAndBoxesGamePage").style.display = "none";
    document.getElementById("seaBattleGamePage").style.display = "block";
    gameMenuInit('battle');
}

function playDotsAndBoxesGameFromSuggestion() {
    next_page('player-message', 'games-menu');
    document.getElementById("allGameMenu").style.display = "none";
    document.getElementById("seaBattleGamePage").style.display = "none";
    document.getElementById("dotsAndBoxesGamePage").style.display = "block";
    gameMenuInit('dots');
}

