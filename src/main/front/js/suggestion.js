navigation[5].addEventListener('click', function () {
    navigation[5].classList.remove('show');
    navigation[3].classList.remove('show');
    navigation[4].classList.add('show');
    navigation[6].classList.add('show');
    if (player) {
        loadSuggestedGames();
        next_page('primary', 'player-message');
    } else {
        loadAllPlayer();
        next_page('primary', 'suggestion');
    }
});


function loadAllPlayer() {
    let playerList = document.querySelector('section.suggestion .all-players .players-area');

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user allUsers ' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let users = e.data.split('/');

        let usernames = [];
        for (let i = 0; i < users.length; i++) {
            let tmp = users[i].split(' ');
            usernames[i] = tmp[2] ;
        }

        for (let i = 0; i < users.length; i++) {
            playerList.insertAdjacentHTML('beforeend',
                '                    <div class="player-box">\n' +
                '                        <div onclick=chatWithPlayer(' +'\"' + usernames[i] +'\"' + ') class="message-icon">\n' +
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
            for (let i = 0; i< usernameForSendSuggestion.length; i++) {
                connection.send('user sendSuggestion ' + self.username + ' ' + self.token + ' ' + usernameForSendSuggestion[i] + ' ' + 'BattleSea');

            }
        };
    } else if (isDotsAndBoxesSelected) {
        connection.onopen = function () {
            for (let i = 0; i< usernameForSendSuggestion.length; i++) {
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
            for (let i = 0; i< usernameForUnSendSuggestion.length; i++) {
                connection.send('user sendSuggestion ' + self.username + ' ' + self.token + ' ' + usernameForUnSendSuggestion[i] + ' ' + 'BattleSea');

            }
        };
    } else if (isDotsAndBoxesSelected) {
        connection.onopen = function () {
            for (let i = 0; i< usernameForUnSendSuggestion.length; i++) {
                connection.send('user sendSuggestion ' + self.username + ' ' + self.token + ' ' + usernameForUnSendSuggestion[i] + ' ' + 'DotsAndBoxes');

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
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadFriendsRequest ' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let friendUsername = e.data.split(' ');

        for (let i = 0; i < friendUsername.length; i++) {
            friendsRequestList.insertAdjacentHTML('beforeend',
                '                    <div class="friend-request" id="' + friendUsername[i] +'">\n' +
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
    next_page('suggestion', 'admin-inbox');

    document.getElementById('chat-username').innerHTML = playerUsername;
    let messageArea = document.querySelector('section.admin-inbox .chatBox .messageArea');


    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadChatHistory ' + username + ' ' + token + ' ' + playerUsername);
    };

    connection.onmessage = function (e) {
        let message = e.data.split('/')[0].split('@')[0];
        let time = e.data.split('/')[0].split('@')[1];
        let month = e.data.split('/')[1];
        let day = e.data.split('/')[2];

        if (message.length > 0) {
            document.getElementById('month').innerHTML = month;
            document.getElementById('day').innerHTML = day;
            document.getElementById('messageDate').style.display = 'block';
        }

        for (let i = 0; i < message.length; i++) {
            messageArea.insertAdjacentHTML('beforeend',
                '                    <div class="admin-avatar-message">\n' +
                '                        <div class="admin-messageBox">\n' +
                '                            <div>\n' +
                '                                <p>' + message[i] + '</p>\n' +
                '                            </div>\n' +
                '                            <div>' + time[i] + '</div>\n' +
                '                        </div>\n' +
                '                        <div>\n' +
                '                            <svg id="Capa_1" enable-background="new 0 0 512 512" height="512" viewBox="0 0 512 512" width="512" xmlns="http://www.w3.org/2000/svg"><g><g><path d="m41.725 300.851h32.39v-125.824h-32.39c-15.825 0-28.653 12.828-28.653 28.653v68.518c0 15.825 12.828 28.653 28.653 28.653z" fill="#96b4eb"/>\n' +
                '                                <path d="m470.275 300.851h-32.39v-125.824h32.39c15.825 0 28.653 12.828 28.653 28.653v68.518c0 15.825-12.828 28.653-28.653 28.653z" fill="#96b4eb"/><path d="m326.342 79.774h-140.915c-67.851 0-122.855 55.005-122.855 122.855v70.619c0 58.011 40.21 106.624 94.277 119.506-16.003 20.952-25.515 47.127-25.515 75.527v33.672h249.102v-33.672c0-28.4-9.512-54.574-25.515-75.527 54.067-12.882 94.277-61.495 94.277-119.506v-70.618c-.001-67.851-55.005-122.856-122.856-122.856z" fill="#c1e5ef"/><path d="m326.342 79.774h-38.361c57.983 0 104.988 47.005 104.988 104.988v60.349c0 57.983-47.005 104.988-104.988 104.988h-120.421c-57.983 0-104.988-47.004-104.988-104.988v28.138c0 67.851 55.004 122.855 122.855 122.855h92.874c16.527 13.081 27.271 34.218 27.271 58.084v7.208c0 11.22-9.095 20.316-20.315 20.316h-112.512v20.532h166.28v-31.815c0-32.519-18.678-60.662-45.885-74.324h33.202c67.851 0 122.855-55.004 122.855-122.855v-70.62c0-67.852-55.004-122.856-122.855-122.856z" fill="#a6aaed"/><g fill="#fff"><circle cx="161.523" cy="303.245" r="17.657"/><circle cx="350.247" cy="303.245" r="17.657"/><path d="m290.908 332.959h-70.046c-11.535 0-20.885-9.351-20.885-20.885 0-11.535 9.351-20.885 20.885-20.885h70.046c11.535 0 20.885 9.351 20.885 20.885 0 11.534-9.35 20.885-20.885 20.885z"/></g><circle cx="255.885" cy="38.626" fill="#d789b9" r="28.579"/><path d="m450.09 4.559 8.253 23.205c.353.993 1.053 1.781 1.936 2.179l20.617 9.289c2.913 1.312 2.913 5.953 0 7.265l-20.617 9.289c-.883.398-1.582 1.185-1.936 2.178l-8.253 23.206c-1.166 3.278-5.289 3.278-6.455 0l-8.253-23.205c-.353-.993-1.053-1.781-1.936-2.178l-20.617-9.289c-2.913-1.312-2.913-5.953 0-7.265l20.617-9.289c.883-.398 1.582-1.185 1.936-2.179l8.253-23.205c1.166-3.28 5.289-3.28 6.455-.001z" fill="#c1e5ef"/><path d="m34.48 372.731 6.122 17.213c.262.737.781 1.321 1.436 1.616l15.293 6.89c2.161.974 2.161 4.416 0 5.389l-15.293 6.89c-.655.295-1.174.879-1.436 1.616l-6.122 17.215c-.865 2.432-3.923 2.432-4.788 0l-6.122-17.213c-.262-.737-.781-1.321-1.436-1.616l-15.293-6.89c-2.161-.974-2.161-4.416 0-5.389l15.293-6.89c.655-.295 1.174-.879 1.436-1.616l6.122-17.213c.865-2.434 3.924-2.434 4.788-.002z" fill="#c1e5ef"/><ellipse cx="469.08" cy="442.246" fill="#96b4eb" rx="18.02" ry="18.02" transform="matrix(.099 -.995 .995 .099 -17.236 865.465)"/><circle cx="76.929" cy="458.837" fill="#96b4eb" r="7.773"/></g><g><g fill="#3c122c"><path d="m290.908 285.16h-70.047c-14.84 0-26.913 12.074-26.913 26.914s12.073 26.913 26.913 26.913h70.047c14.84 0 26.914-12.074 26.914-26.913-.001-14.84-12.074-26.914-26.914-26.914zm-58.919 12.056h17.867v29.715h-17.867zm29.924 0h17.785v29.715h-17.785zm-55.909 14.858c0-7.879 6.17-14.328 13.93-14.811v29.621c-7.76-.483-13.93-6.931-13.93-14.81zm85.75 14.814v-29.63c7.798.443 14.011 6.909 14.011 14.815 0 7.908-6.213 14.373-14.011 14.815z"/><path d="m129.182 182.747c-10.222 0-18.538-8.316-18.538-18.538 0-10.221 8.316-18.537 18.538-18.537s18.538 8.316 18.538 18.537c0 10.223-8.316 18.538-18.538 18.538zm0-25.019c-3.574 0-6.482 2.907-6.482 6.481s2.908 6.482 6.482 6.482 6.482-2.908 6.482-6.482-2.908-6.481-6.482-6.481z"/><path d="m129.182 236.657c-10.222 0-18.538-8.316-18.538-18.537s8.316-18.537 18.538-18.537 18.538 8.316 18.538 18.537-8.316 18.537-18.538 18.537zm0-25.018c-3.574 0-6.482 2.907-6.482 6.481s2.908 6.481 6.482 6.481 6.482-2.907 6.482-6.481-2.908-6.481-6.482-6.481z"/><path d="m406.25 182.166h-64.086c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h64.086c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m243.252 458.03h-40.999c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h40.999c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m243.252 484.838h-40.999c-3.329 0-6.028-2.699-6.028-6.028s2.699-6.028 6.028-6.028h40.999c3.329 0 6.028 2.699 6.028 6.028s-2.699 6.028-6.028 6.028z"/><path d="m470.275 164.98h-16.471c-16.275-55.003-67.253-95.253-127.462-95.253h-47.586c9.547-7.039 15.754-18.357 15.754-31.102 0-21.297-17.327-38.625-38.625-38.625s-38.626 17.328-38.626 38.626c0 12.744 6.208 24.062 15.754 31.102h-47.586c-60.21 0-111.187 40.25-127.462 95.253h-16.24c-21.339 0-38.699 17.36-38.699 38.699v68.519c0 21.338 17.36 38.699 38.699 38.699h16.241c12.052 40.731 43.133 73.365 82.904 87.561-12.415 20.37-19.582 44.273-19.582 69.823v33.672c0 5.548 4.498 10.047 10.047 10.047s10.047-4.498 10.047-10.047v-33.672c0-23.841 7.341-45.991 19.858-64.343 7.846 1.449 15.93 2.213 24.189 2.213h8.051c-16.504 16.191-26.761 38.728-26.761 63.62v35.995c0 3.329 2.699 6.028 6.028 6.028s6.028-2.699 6.028-6.028v-35.995c0-26.396 13.343-49.716 33.628-63.62h86.968c9.425 6.46 17.347 14.952 23.131 24.844h-26.241c-10.714 0-19.431 8.716-19.431 19.431v14.981c0 10.715 8.717 19.431 19.431 19.431h36.738v20.928c0 3.329 2.699 6.028 6.028 6.028s6.028-2.699 6.028-6.028v-35.995c0-24.892-10.257-47.428-26.761-63.62h8.05c8.258 0 16.339-.764 24.184-2.212 12.518 18.352 19.863 40.501 19.863 64.342v33.672c0 5.548 4.498 10.047 10.047 10.047s10.047-4.498 10.047-10.047v-33.672c0-25.552-7.182-49.447-19.598-69.817 39.778-14.193 70.866-46.83 82.92-87.566h16.471c21.339 0 38.7-17.361 38.7-38.699v-68.52c-.003-21.34-17.364-38.7-38.703-38.7zm-416.589 125.824h-11.961c-10.26 0-18.606-8.346-18.606-18.606v-68.518c0-10.26 8.346-18.606 18.606-18.606h11.962c-.762 5.746-1.161 11.605-1.161 17.556v70.619c-.001 5.951.399 11.809 1.16 17.555zm279.31 178.967v3.011h-36.738c-4.067 0-7.375-3.308-7.375-7.375v-14.981c0-4.067 3.308-7.375 7.375-7.375h31.952c3.089 8.328 4.786 17.328 4.786 26.72zm-6.654-379.95c40.149 0 75.461 21.087 95.458 52.764h-97.292v-52.764zm-70.457-69.728c10.219 0 18.532 8.314 18.532 18.532s-8.314 18.532-18.532 18.532c-10.219 0-18.532-8.314-18.532-18.532-.001-10.218 8.313-18.532 18.532-18.532zm-56.567 69.728h113.134v87.269c0 12.763-10.384 23.147-23.147 23.147h-66.84c-12.763 0-23.147-10.383-23.147-23.147zm-49.424 213.424c0-6.412 5.217-11.628 11.629-11.628s11.629 5.217 11.629 11.628c0 6.413-5.217 11.629-11.629 11.629-6.413 0-11.629-5.216-11.629-11.629zm200.353 11.629c-6.412 0-11.629-5.217-11.629-11.629s5.217-11.628 11.629-11.628 11.629 5.217 11.629 11.628c-.001 6.413-5.217 11.629-11.629 11.629zm88.904-41.625c0 51.843-35.155 95.622-82.876 108.77v-55.869c10.149-2.674 17.657-11.928 17.657-22.905 0-13.06-10.625-23.684-23.685-23.684s-23.685 10.625-23.685 23.684c0 10.977 7.508 20.232 17.657 22.905v58.486c-5.824.931-11.794 1.422-17.876 1.422h-140.916c-6.083 0-12.052-.491-17.876-1.422v-58.486c10.149-2.673 17.657-11.928 17.657-22.905 0-13.06-10.625-23.684-23.685-23.684s-23.685 10.625-23.685 23.684c0 10.977 7.507 20.232 17.657 22.905v55.868c-47.721-13.147-82.876-56.927-82.876-108.769v-70.619c0-62.203 50.606-112.809 112.809-112.809h1.834v87.269c0 19.41 15.792 35.203 35.203 35.203h66.84c19.411 0 35.203-15.792 35.203-35.203v-22.45h103.911c6.878 14.571 10.732 30.838 10.732 47.99zm49.73-1.051c0 10.26-8.347 18.606-18.606 18.606h-12.192c.762-5.746 1.161-11.605 1.161-17.555v-70.619c0-5.951-.4-11.81-1.161-17.556h12.192c10.26 0 18.606 8.346 18.606 18.606z"/></g><path d="m225.291 174.362c-5.549 0-10.047-4.498-10.047-10.047v-15.875c0-5.548 4.498-10.047 10.047-10.047s10.047 4.498 10.047 10.047v15.876c0 5.547-4.498 10.046-10.047 10.046z" fill="#fff"/><path d="m225.291 129.552c-5.549 0-10.047-4.498-10.047-10.047v-4.537c0-5.548 4.498-10.047 10.047-10.047s10.047 4.498 10.047 10.047v4.537c0 5.549-4.498 10.047-10.047 10.047z" fill="#fff"/></g></g>\n' +
                '                            </svg>\n' +
                '                        </div>\n' +
                '                    </div>');
        }

        connection.close();
    }

    let sendButton = document.getElementById('send-button');

    sendButton.addEventListener('click', function (event) {
        event.preventDefault();
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
    });
}

function loadPlatoBotMessage() {
    next_page('player-message', 'inbox');

    let messageArea = document.querySelector('section .inbox .chatBox .messageArea');

    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user loadPlatoBotMessage ' + username + ' ' + token);
    };

    connection.onmessage = function (e) {
        let allMessages = e.data.split('-');

        let month = allMessages[0].split('/')[1];
        let day = allMessages[0].split('/')[2];

        document.getElementById('p-month').innerHTML = month;
        document.getElementById('p-day').innerHTML = day;
        document.getElementById('message-date').style.display = 'block';


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
        connection.close();
    };

    let sendButton = document.getElementById('send');

    sendButton.addEventListener('click', function (event) {
        event.preventDefault();
        let messageFromTextArea = document.getElementById('message-content').value;
        const monthNames = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];
        let date = new Date();

        if (messageFromTextArea != null) {
            document.getElementById('p-month').innerHTML = monthNames[date.getMonth()];
            document.getElementById('p-day').innerHTML = '' + date.getDate();
            document.getElementById('message-date').style.display = 'block';
        }

        messageArea.insertAdjacentHTML('beforeend',
            '                    <div class="player-avatar-message">\n' +
            '                        <div class="messageBox player-messageBox">\n' +
            '                            <div>\n' +
            '                                <p>' + messageFromTextArea + '</p>\n' +
            '                            </div>\n' +
            '                            <div>' + date.getHours() + ':' + date.getMinutes() + '</div>\n' +
            '                        </div>\n' +
            '                        <div>\n' +
            '                            <svg id="Capa_1" enable-background="new 0 0 512 512" height="512" viewBox="0 0 512 512" width="512" xmlns="http://www.w3.org/2000/svg"><g><g>\n' +
            '                                <path d="m451.531 243.523c.577-5.53.879-11.141.879-16.824 0-88.428-71.685-160.113-160.112-160.113h-71.162c-88.427 0-160.112 71.685-160.112 160.113 0 5.683.301 11.294.878 16.824-17.398 5.039-30.122 21.078-30.122 40.1 0 23.06 18.694 41.755 41.755 41.755 6.571 0 12.785-1.523 18.315-4.227 17.826 24.359 42.374 43.479 70.904 54.662-19.972 22.308-32.123 51.766-32.123 84.065v34.087h252.169v-34.087c0-32.299-12.151-61.756-32.123-84.065 28.531-11.182 53.078-30.303 70.904-54.662 5.531 2.704 11.745 4.227 18.316 4.227 23.06 0 41.755-18.694 41.755-41.755.001-19.022-12.724-35.061-30.121-40.1z" fill="#c49a8b"/><path d="m211.567 390.745c-23.448 14.939-39.014 41.15-39.014 71.012v32.207h39.014z" fill="#d789b9"/><path d="m302.533 391.173v102.791h38.347v-32.207c0-29.578-15.272-55.575-38.347-70.584z" fill="#d789b9"/><path d="m265.548 377.968c24.344 10.949 41.468 36.935 41.468 67.261v7.296c0 11.358-9.208 20.566-20.566 20.566h-113.898v20.785h168.328v-32.207c0-43.499-33.002-79.285-75.332-83.701z" fill="#c668b9"/><path d="m286.45 473.091h-76.302v20.785h88.447v-24.78c-3.406 2.5-7.596 3.995-12.145 3.995z" fill="#b2748a"/><path d="m292.297 66.587h-71.162c-88.427 0-160.112 71.685-160.112 160.113 0 88.428 71.685 160.113 160.112 160.113h71.162c88.427 0 160.112-71.685 160.112-160.113 0-88.428-71.685-160.113-160.112-160.113zm59.943 279.164c-5.598 0-10.993-.868-16.057-2.479l-.731-.223c-18.142-4.919-46.545-7.794-78.736-7.794-31.448 0-58.796 2.593-76.988 7.32l-1.174.264c-5.439 1.887-11.281 2.912-17.362 2.912-29.239 0-52.941-23.702-52.941-52.941s23.703-52.942 52.941-52.942c15.427 0 34.556 12.809 34.556 12.809 1.865 1.618 3.073 2.668 5.951 4.62 14.354 9.736 33.638 15.997 55.018 15.997 20.42 0 43.088-6.492 56.064-16.038 2.289-1.684 3.954-3.739 4.954-4.6 9.267-7.971 21.323-12.788 34.505-12.788 29.239 0 52.942 23.703 52.942 52.942s-23.703 52.941-52.942 52.941z" fill="#d789b9"/><g fill="#f6e06e"><path d="m398.584 337.611s12.059-29.717 9.906-57.712c-22.826-63.742-77.093-41.777-96.905-22.396-13.73 13.432-75.627 25.318-104.657 3.015-35.316-27.133-55.128-21.104-66.756-18.95-59.004 21.535-29.717 95.182-29.717 95.182l-37.801-43.94c0-48.82 39.718-88.538 88.538-88.538 24.352 0 47.766 14.412 54.362 18.828 18.491 18.097 61.307 19.624 78.971 2.568 16.039-13.797 36.538-21.396 57.716-21.396 48.82 0 88.537 39.718 88.537 88.538z"/><path d="m264.739 115.312 6.139 12.439c1.303 2.641 3.822 4.471 6.737 4.894l13.727 1.995c7.339 1.066 10.269 10.085 4.959 15.261l-9.933 9.682c-2.109 2.056-3.071 5.017-2.573 7.919l2.345 13.672c1.253 7.309-6.418 12.883-12.982 9.432l-12.278-6.455c-2.606-1.37-5.72-1.37-8.327 0l-12.278 6.455c-6.564 3.451-14.235-2.123-12.982-9.432l2.345-13.672c.498-2.902-.465-5.864-2.573-7.919l-9.933-9.682c-5.31-5.176-2.38-14.195 4.959-15.261l13.727-1.995c2.914-.423 5.433-2.254 6.737-4.894l6.139-12.439c3.281-6.65 12.763-6.65 16.045 0z"/><path d="m189.925 130.661s-18.703 8.808-33.128 7.124-19.81-7.234-19.111-13.223 7.217-10.149 21.643-8.465c14.425 1.685 30.596 14.564 30.596 14.564z"/><path d="m189.879 166.696s-15.986 13.108-30.386 14.998c-14.4 1.889-20.977-2.177-21.761-8.156-.785-5.978 4.521-11.604 18.921-13.494 14.4-1.889 33.226 6.652 33.226 6.652z"/><path d="m323.507 130.661s18.703 8.808 33.128 7.124 19.81-7.234 19.111-13.223-7.217-10.149-21.643-8.465c-14.425 1.685-30.596 14.564-30.596 14.564z"/><path d="m323.553 166.696s15.986 13.108 30.386 14.998c14.4 1.889 20.977-2.177 21.761-8.156.784-5.978-4.521-11.604-18.921-13.494-14.4-1.889-33.226 6.652-33.226 6.652z"/></g><path d="m292.297 66.871h-20.532c71.689 0 129.805 58.116 129.805 129.805v11.024c0 71.689-58.116 129.805-129.805 129.805h-80.937c-68.605 0-124.771-53.224-129.478-120.632-.208 3.344-.327 6.713-.327 10.11v.001c0 88.427 71.685 160.112 160.112 160.112h71.161c88.428 0 160.112-71.685 160.112-160.112v-.001c.001-88.428-71.684-160.112-160.111-160.112z" fill="#b2748a"/><path d="m452.407 226.841c-.077-88.362-71.73-159.97-160.111-159.97h-20.532c71.689 0 129.805 58.116 129.805 129.805v11.024c0 17.642-3.526 34.458-9.901 49.792 8.399 9.37 13.513 21.745 13.513 35.319 0 29.239-23.703 52.941-52.942 52.941-5.598 0-10.993-.869-16.057-2.478l-.73-.223c-11.683-3.168-27.627-5.486-46.093-6.737-5.755.78-11.626 1.191-17.594 1.191h-62.045c-11.564 1.224-21.73 2.925-29.991 5.071l-1.174.264c-5.439 1.887-11.281 2.912-17.362 2.912-23.142 0-42.802-14.853-50.001-35.542-28.265-21.993-47.194-55.421-49.841-93.336-.206 3.303-.322 6.631-.325 9.986.087 88.354 71.736 159.953 160.11 159.953h71.162c88.379-.001 160.033-71.609 160.109-159.972z" fill="#c668b9"/><path d="m401.075 218.996c-1.029 11.928-3.668 23.394-7.703 34.18 5.809 6.447 10.982 15.171 15.118 26.723 2.153 27.995-9.906 57.712-9.906 57.712l37.168-39.465c1.745-3.512 3.365-7.097 4.855-10.749-1.73-28.538-17.036-53.468-39.532-68.401z" fill="#dda86a"/><path d="m103.391 303.622c-11.006-10.039-20.277-21.947-27.313-35.222-1.749 6.09-2.856 12.448-3.253 18.994 1.659 4.069 3.475 8.057 5.451 11.95l32.179 37.405s-5.894-14.831-7.064-33.127z" fill="#dda86a"/><path d="m39.931 31.402 8.354 23.491c.358 1.006 1.066 1.803 1.959 2.205l20.871 9.403c2.949 1.328 2.949 6.027 0 7.355l-20.871 9.403c-.894.402-1.602 1.2-1.959 2.205l-8.354 23.491c-1.18 3.319-5.354 3.319-6.535 0l-8.354-23.491c-.358-1.006-1.066-1.803-1.959-2.205l-20.872-9.403c-2.948-1.328-2.948-6.027 0-7.355l20.871-9.403c.894-.403 1.602-1.2 1.959-2.205l8.354-23.491c1.181-3.319 5.355-3.319 6.536 0z" fill="#e8c2d8"/><path d="m487.227 72.144 6.197 17.425c.265.746.791 1.337 1.453 1.636l15.482 6.975c2.187.986 2.187 4.47 0 5.456l-15.482 6.975c-.663.299-1.188.89-1.453 1.636l-6.197 17.425c-.875 2.462-3.972 2.462-4.847 0l-6.197-17.425c-.265-.746-.791-1.337-1.453-1.636l-15.482-6.975c-2.187-.986-2.187-4.47 0-5.456l15.482-6.975c.663-.299 1.188-.89 1.453-1.636l6.197-17.425c.876-2.462 3.972-2.462 4.847 0z" fill="#e8c2d8"/><ellipse cx="398.717" cy="26.107" fill="#d789b9" rx="18.242" ry="18.242" transform="matrix(.23 -.973 .973 .23 281.702 408.16)"/><ellipse cx="125.837" cy="24.433" fill="#d789b9" rx="7.869" ry="7.869" transform="matrix(.122 -.993 .993 .122 86.211 146.342)"/></g><g fill="#3c122c"><path d="m462.272 236.792c.196-3.341.307-6.704.307-10.093 0-93.894-76.388-170.283-170.283-170.283h-71.161c-93.894 0-170.283 76.389-170.283 170.283 0 3.386.111 6.747.307 10.085-17.459 8.374-29.55 26.217-29.55 46.839 0 28.632 23.294 51.925 51.925 51.925 5.198 0 10.214-.779 14.951-2.21 15.65 19.429 35.468 35.363 58.09 46.427-16.412 22.501-26.113 50.193-26.113 80.112v34.087c0 5.617 4.554 10.17 10.17 10.17 5.617 0 10.17-4.553 10.17-10.17v-34.087c0-27.214 9.43-52.266 25.19-72.065 9.508 3.263 19.392 5.709 29.57 7.249-17.886 16.504-29.111 40.124-29.111 66.323v36.439c0 3.37 2.732 6.102 6.102 6.102s6.102-2.732 6.102-6.102v-36.439c0-23.347 10.309-44.323 26.607-58.64v94.788c0 3.37 2.732 6.102 6.102 6.102s6.102-2.732 6.102-6.102v-100.596c1.221.026 2.443.047 3.67.047h71.161c.758 0 1.511-.019 2.267-.029v100.58c0 3.37 2.732 6.102 6.102 6.102s6.102-2.732 6.102-6.102v-95.997c17.109 14.33 28.01 35.838 28.01 59.849v36.439c0 3.37 2.732 6.102 6.102 6.102s6.102-2.732 6.102-6.102v-36.439c0-26.199-11.225-49.819-29.111-66.323 10.174-1.539 20.054-3.984 29.559-7.245 15.762 19.799 25.201 44.845 25.201 72.061v34.087c0 5.617 4.554 10.17 10.17 10.17 5.617 0 10.17-4.553 10.17-10.17v-34.087c0-29.919-9.697-57.614-26.109-80.115 22.622-11.065 42.441-27.002 58.091-46.433 4.736 1.43 9.747 2.218 14.944 2.218 28.632 0 51.926-23.294 51.926-51.925 0-20.622-12.092-38.459-29.551-46.832zm-241.136-160.035h71.161c82.678 0 149.942 67.264 149.942 149.942 0 9.985-.99 19.742-2.861 29.187-14.416-33.891-48.046-57.716-87.137-57.716-22.638 0-44.548 8.123-61.695 22.872-13.761 13.671-49.181 13.588-65.44 1.747-1.715-1.166-4.145-3.64-6.156-4.758-6.958-4.659-31.685-19.861-57.758-19.861-39.092 0-72.723 23.825-87.138 57.716-1.871-9.444-2.861-19.201-2.861-29.187 0-82.678 67.264-149.942 149.943-149.942zm-147.602 238.451c-17.416 0-31.584-14.169-31.584-31.584 0-10.004 4.691-18.917 11.973-24.706 3.89 20.228 11.36 39.197 21.74 56.21-.705.047-1.413.08-2.129.08zm218.763 61.434h-71.161c-32.447 0-62.516-10.362-87.08-27.947 7.355 3.164 15.45 4.924 23.952 4.924 6.713 0 13.308-1.087 19.607-3.231l.886-.199c.066-.015.131-.031.197-.048 18.286-4.751 45.993-7.368 78.02-7.368 32.316 0 61.389 2.858 79.764 7.841.066.018.132.035.199.05.1.038.202.073.305.106 5.944 1.89 12.149 2.849 18.442 2.849 8.5 0 16.595-1.759 23.95-4.923-24.567 17.584-54.636 27.946-87.081 27.946zm63.129-35.228c-5.036 0-9.997-.765-14.743-2.274-.15-.048-.3-.089-.451-.125-.181-.068-.366-.129-.557-.18-19.371-5.254-49.609-8.266-82.958-8.266-32.975 0-61.73 2.746-80.99 7.734l-1.116.251c-.223.05-.445.113-.662.189-5.11 1.773-10.473 2.672-15.941 2.672-26.801 0-48.604-21.804-48.604-48.604 0-26.801 21.804-48.605 48.604-48.605 12.907 0 29.623 10.469 32.022 12.016 1.85 1.604 3.308 2.839 6.409 4.943 16.716 11.338 38.122 17.582 60.278 17.582 22.376 0 47.112-7.137 61.55-17.76 2.454-1.811 3.857-3.446 5.482-5.042 8.801-7.57 20.051-11.739 31.677-11.739 26.801 0 48.604 21.804 48.604 48.605 0 26.799-21.804 48.603-48.604 48.603zm54.73-22.122c3.891-8.01 6.078-16.995 6.078-26.482 0-33.531-27.278-60.809-60.809-60.809-14.545 0-28.621 5.217-39.636 14.691-1.582 1.393-2.934 3.134-4.756 4.464-12.313 9.059-34.649 15.386-54.317 15.386-19.739 0-38.713-5.496-53.427-15.477-2.665-1.808-3.693-2.699-5.575-4.333-.192-.166-.393-.32-.604-.461-.87-.583-21.509-14.268-39.103-14.268-33.531 0-60.809 27.279-60.809 60.809 0 9.488 2.188 18.474 6.08 26.485-9.775-12.414-17.63-26.405-23.123-41.521 7.091-38.297 40.718-67.4 81.037-67.4 22.631 0 44.732 13.622 50.966 17.796 20.331 19.768 67.349 20.776 86.346 2.124 14.934-12.846 34.017-19.92 53.736-19.92 40.318 0 73.945 29.103 81.036 67.4-5.492 15.112-13.347 29.102-23.12 41.516zm29.741-4.084c-.716 0-1.422-.037-2.126-.085 10.38-17.014 17.849-35.984 21.738-56.213 7.282 5.791 11.972 14.708 11.972 24.714.001 17.415-14.168 31.584-31.584 31.584z"/><ellipse cx="161.192" cy="292.81" rx="17.874" ry="17.874" transform="matrix(.29 -.957 .957 .29 -165.765 362.234)"/><path d="m352.24 274.936c-9.871 0-17.874 8.002-17.874 17.874 0 9.871 8.003 17.874 17.874 17.874 9.872 0 17.874-8.003 17.874-17.874 0-9.872-8.002-17.874-17.874-17.874z"/><path d="m274.59 286.708c-3.37 0-6.102 2.732-6.102 6.102 0 6.491-5.281 11.772-11.771 11.772-6.492 0-11.772-5.281-11.772-11.772 0-3.37-2.732-6.102-6.102-6.102s-6.102 2.732-6.102 6.102c0 13.22 10.756 23.976 23.977 23.976s23.976-10.756 23.976-23.976c-.002-3.37-2.734-6.102-6.104-6.102z"/><path d="m222.804 163.953c.671.654.977 1.596.818 2.518l-2.345 13.672c-.975 5.688 1.319 11.327 5.988 14.719 4.668 3.391 10.74 3.831 15.848 1.145l12.278-6.455c.828-.436 1.819-.436 2.648 0l12.278 6.456c2.222 1.168 4.625 1.745 7.017 1.745 3.108 0 6.195-.974 8.832-2.89 4.669-3.392 6.963-9.032 5.988-14.719l-2.345-13.672c-.159-.924.147-1.865.816-2.519l9.935-9.683c4.132-4.028 5.591-9.939 3.807-15.427-1.783-5.488-6.438-9.413-12.149-10.242l-13.726-1.994c-.928-.135-1.728-.717-2.142-1.556l-6.139-12.439c-2.554-5.175-7.725-8.389-13.496-8.389s-10.942 3.215-13.496 8.389l-6.139 12.439c-.414.84-1.215 1.421-2.141 1.556l-13.728 1.994c-5.71.83-10.365 4.755-12.148 10.242-1.784 5.488-.325 11.399 3.807 15.427zm-2.133-21.338c.165-.506.717-1.707 2.296-1.937l13.728-1.994c4.902-.713 9.138-3.79 11.331-8.233l6.139-12.439c.706-1.431 2.019-1.586 2.551-1.586.531 0 1.844.154 2.551 1.586l6.139 12.439c2.193 4.443 6.429 7.52 11.332 8.233l13.726 1.994c1.58.23 2.132 1.431 2.297 1.937.164.506.423 1.802-.719 2.916l-9.935 9.683c-3.547 3.457-5.164 8.437-4.327 13.32l2.345 13.672c.27 1.573-.701 2.47-1.131 2.782-.431.313-1.587.957-2.996.217l-12.278-6.456c-2.193-1.153-4.598-1.729-7.003-1.729-2.406 0-4.811.576-7.003 1.729l-12.278 6.455c-1.411.742-2.565.097-2.997-.217-.43-.312-1.401-1.209-1.131-2.782l2.345-13.672c.836-4.883-.782-9.862-4.328-13.32l-9.933-9.683c-1.144-1.112-.885-2.409-.721-2.915z"/><path d="m137.858 137.631c4.174 3.199 10.308 5.289 18.232 6.214 1.476.172 2.982.251 4.503.251 14.937 0 31.181-7.561 31.932-7.915 1.908-.898 3.216-2.718 3.461-4.813.244-2.095-.61-4.167-2.26-5.481-.716-.57-17.719-13.987-33.691-15.852-24.518-2.862-27.948 9.853-28.412 13.818-.62 5.329 1.593 10.222 6.235 13.778zm5.889-12.361c.213-1.835 3.285-3.45 9.499-3.45 1.585 0 3.376.105 5.375.339 6.118.715 12.809 3.888 18.059 6.954-6.044 1.82-13.289 3.298-19.177 2.611-7.484-.874-10.871-2.743-12.222-3.78-1.694-1.298-1.586-2.227-1.534-2.674z"/><path d="m192.399 161.139c-.832-.378-20.602-9.233-36.54-7.145-24.487 3.213-24.697 16.378-24.179 20.338.462 3.521 3.356 13.929 21.221 13.929 2.221 0 4.677-.161 7.385-.516 15.944-2.092 32.753-15.749 33.461-16.329 1.631-1.337 2.455-3.422 2.181-5.512-.273-2.093-1.608-3.894-3.529-4.765zm-33.699 14.504c-9.819 1.291-14.617-.599-14.918-2.898-.302-2.3 3.846-5.362 13.664-6.65 1.195-.157 2.431-.228 3.695-.228 5.2 0 10.822 1.202 15.522 2.563-5.207 3.141-11.854 6.411-17.963 7.213z"/><path d="m320.907 136.182c.752.354 16.996 7.915 31.932 7.915 1.521 0 3.028-.079 4.503-.251 7.924-.925 14.058-3.016 18.232-6.214 4.641-3.557 6.855-8.45 6.233-13.777-.463-3.966-3.889-16.681-28.411-13.818-15.973 1.865-32.975 15.281-33.691 15.852-1.65 1.314-2.504 3.386-2.259 5.481.245 2.094 1.553 3.914 3.461 4.812zm33.904-14.024c9.841-1.149 14.606.808 14.874 3.112.053.447.161 1.376-1.534 2.674-1.352 1.037-4.738 2.906-12.222 3.78-6.124.713-13.365-.834-19.182-2.609 5.25-3.066 11.944-6.242 18.064-6.957z"/><path d="m375.715 160.468c-4.128-3.258-10.232-5.436-18.142-6.474-15.943-2.095-35.708 6.767-36.54 7.145-1.921.871-3.256 2.672-3.53 4.763s.55 4.175 2.181 5.512c.708.581 17.517 14.238 33.461 16.329 2.623.344 5.085.515 7.383.515 4.63 0 8.587-.698 11.815-2.09 5.369-2.316 8.71-6.52 9.408-11.837.697-5.317-1.447-10.241-6.036-13.863zm-6.066 12.277c-.059.445-.18 1.373-2.14 2.218-1.563.674-5.305 1.661-12.777.68-6.108-.801-12.752-4.07-17.958-7.21 6.068-1.734 13.332-3.108 19.212-2.337 7.471.98 10.83 2.897 12.168 3.953 1.676 1.322 1.554 2.249 1.495 2.696z"/></g></g>\n' +
            '                            </svg>\n' +
            '                        </div>\n' +
            '                    </div>');

        document.getElementById('message-content').value = '';
    })
}