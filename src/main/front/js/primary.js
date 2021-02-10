navigation[4].addEventListener('click', function () {
    navigation[4].classList.remove('show');
    navigation[3].classList.add('show');
    navigation[5].classList.add('show');
    navigation[6].classList.add('show');
    next_page('primary', 'account');
});

document.querySelector('section.primary .player header .search').addEventListener('keyup', function (event) {
    event.preventDefault();
    document.querySelector('section.primary .player header ol').innerHTML = '';
    document.querySelector('section.primary .player header ol').style.height = 0;
    if (document.querySelector('section.primary .player header .search').value.length > 3) {
        update_list(document.querySelector('section.primary .player header .search').value);
    }
});

document.querySelector('section.primary .admin .players .search').addEventListener('keyup', function (event) {
    event.preventDefault();
    let items = document.querySelector('section.primary .admin .players ol').querySelectorAll('li');
    if (document.querySelector('section.primary .admin .players .search').value.length > 3) {
        for (let i = 0; i < items.length; i++) {
            if (!items[i].getAttribute('name').includes(document.querySelector('section.primary .admin .players .search').value)) {
                items[i].classList.add('hide');
            } else {
                items[i].classList.remove('hide');
                items[i].style.display = 'flex';
            }
        }
        setTimeout(function () {
            for (let i = 0; i < items.length; i++) {
                if (!items[i].getAttribute('name').includes(document.querySelector('section.primary .admin .players .search').value)) {
                    items[i].style.display = 'none';
                }
            }
        }, 500);
    } else if (document.querySelector('section.primary .admin .players .search').value.length == 0) {
        for (let i = 0; i < items.length; i++) {
            items[i].style.display = 'flex';
        }
        setTimeout(function () {
            for (let i = 0; i < items.length; i++) {
                items[i].classList.remove('hide');
            }
        }, 500)
    }
});

function update_list(value) {
    let list = document.querySelector('section.primary .player header ol');
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user search ' + username + ' ' + token + ' ' + value);
    };

    connection.onmessage = function (e) {
        let users = e.data.split(' ');

        for (let i = 0; i < users.length; i++) {
            if (users[i].length > 0) {
                list.insertAdjacentHTML('beforeend', '<li class="item" name="' + users[i] + '"><img src = "avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg"><h2>' + users[i] + '</h2></li>');
            }
        }
        if (users.length == 1 && users[0].length > 0) {
            list.childNodes[0].style.margin = '0.7vh';
        }
        if (users[0].length > 0) {
            list.style.height = users.length * 4 + 1.4 + 'vh';
        }
        setTimeout(() => {
            for (let i = 0; i < list.childNodes.length; i++) {
            list.childNodes[i].classList.add('show');
        }
    }, 300);
        connection.close();
    }
}

function update_suggestions() {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user suggestions ' + username + " " + token);
    };

    connection.onmessage = function (e) {
        let suggestions = e.data;

        if (suggestions.includes('dots')) {
            document.querySelector('section.primary .player .favorites .dots').style.visibility = 'visible';
        }
        if (suggestions.includes('battle')) {
            document.querySelector('section.primary .player .favorites .battle').style.visibility = 'visible';
        }
        connection.close();
    }
}

function update_player_events() {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user events ' + username + " " + token);
    };

    connection.onmessage = function (e) {
        let events = e.data.split('/');

        for (let i = 0; i < events.length; i++) {
            let event_details = events[i].split(' ');
            if (events[i].length > 0) {
                if (event_details[1].localeCompare('dots') == 0) {
                    document.querySelector('section.primary .player .events .dots').insertAdjacentHTML('beforeend', '<div class="card" eventID="' + event_details[0] + '" onclick="join_event(\'' + event_details[0] + '\')"><span>Dots & Boxes</span><div class="info"><span>+' + parseInt(event_details[2]) + '</span><div><span class="start"><span>' + parseInt(event_details[5]) + ' ' + event_details[4] + ' ' + parseInt(event_details[3]) + '</span><span>' + parseInt(event_details[6]) + ':' + parseInt(event_details[7]) + '</span></span><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"viewBox="0 0 447.243 447.243"><g><path d="M406.101,232.603c-0.978-0.982-2.019-1.899-3.116-2.745c-13.381-8.971-31.276-7.013-42.4,4.64l-88.64,88.32c-4.7,4.695-8.641,10.093-11.68,16l-5.12,9.76v-314.4c0.677-16.099-10.332-30.349-26.08-33.76c-17.445-2.829-33.881,9.019-36.71,26.465c-0.297,1.83-0.434,3.682-0.41,5.535v315.52l-3.2-6.88c-3.183-6.725-7.515-12.843-12.8-18.08l-88.64-88.48c-11.124-11.653-29.019-13.611-42.4-4.64c-14.259,10.441-17.355,30.464-6.914,44.724c0.844,1.152,1.764,2.247,2.754,3.276l160,160c12.49,12.504,32.751,12.515,45.255,0.025c0.008-0.008,0.017-0.017,0.025-0.025l160-160C418.542,265.382,418.577,245.121,406.101,232.603z" /></g></svg><span class="finish"><span>' + parseInt(event_details[10]) + ' ' + event_details[9] + ' ' + parseInt(event_details[8]) + '</span><span>' + parseInt(event_details[11]) + ':' + parseInt(event_details[12]) + '</span></span></div></div><div class="cover"><span>JOIN</span></div></div>');
                } else {
                    document.querySelector('section.primary .player .events .battle').insertAdjacentHTML('beforeend', '<div class="card" eventID="' + event_details[0] + '" onclick="join_event(\'' + event_details[0] + '\')"><span>Sea Battle</span><div class="info"><span>+' + parseInt(event_details[2]) + '</span><div><span class="start"><span>' + parseInt(event_details[5]) + ' ' + event_details[4] + ' ' + parseInt(event_details[3]) + '</span><span>' + parseInt(event_details[6]) + ':' + parseInt(event_details[7]) + '</span></span><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"viewBox="0 0 447.243 447.243"><g><path d="M406.101,232.603c-0.978-0.982-2.019-1.899-3.116-2.745c-13.381-8.971-31.276-7.013-42.4,4.64l-88.64,88.32c-4.7,4.695-8.641,10.093-11.68,16l-5.12,9.76v-314.4c0.677-16.099-10.332-30.349-26.08-33.76c-17.445-2.829-33.881,9.019-36.71,26.465c-0.297,1.83-0.434,3.682-0.41,5.535v315.52l-3.2-6.88c-3.183-6.725-7.515-12.843-12.8-18.08l-88.64-88.48c-11.124-11.653-29.019-13.611-42.4-4.64c-14.259,10.441-17.355,30.464-6.914,44.724c0.844,1.152,1.764,2.247,2.754,3.276l160,160c12.49,12.504,32.751,12.515,45.255,0.025c0.008-0.008,0.017-0.017,0.025-0.025l160-160C418.542,265.382,418.577,245.121,406.101,232.603z" /></g></svg><span class="finish"><span>' + parseInt(event_details[10]) + ' ' + event_details[9] + ' ' + parseInt(event_details[8]) + '</span><span>' + parseInt(event_details[11]) + ':' + parseInt(event_details[12]) + '</span></span></div></div><div class="cover"><span>JOIN</span></div></div>');
                }
            }
        }
        connection.close();
    }
}

function join_event(eventID) {
    let cards = document.querySelectorAll('section.primary .player .events section .card');
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user joinevent ' + username + " " + token + " " + eventID);
        for (let i = 0; i < cards.length; i++) {
            if (cards[i].getAttribute('eventid').localeCompare(eventID) == 0) {
                let cover = cards[i].querySelector('.cover');
                cover.parentNode.removeChild(cover);
                cards[i].classList.add('selected');
                break;
            }
        }
        connection.close();
    };
}

function update_users() {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user users ' + username + " " + token);
    };

    connection.onmessage = function (e) {
        let users = e.data.split(' ');
        for (let i = 0; i < users.length; i++) {
            document.querySelector('section.primary .admin .players ol').insertAdjacentHTML('beforeend', '<li class="item" name="' + users[i] + '"><img src="avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg"><h2>' + users[i] + '</h2></li>')
        }
        connection.close();
    }
}

function update_admin_events() {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user events ' + username + " " + token);
    };

    connection.onmessage = function (e) {
        let events = e.data.split('/');
        for (let i = 0; i < events.length; i++) {
            let event_details = events[i].split(' ');
            if (events[i].length > 0) {
                if (event_details[1].localeCompare('dots') == 0) {
                    document.querySelector('section.primary .admin .events .games').insertAdjacentHTML('beforeend', '<div class="card dots" eventID="' + event_details[0] + '" onclick="delete_event(\'' + event_details[0] + '\')"><span>Dots & Boxes</span><div class="info"><span>+' + parseInt(event_details[2]) + '</span><div><span class="start"><span>' + parseInt(event_details[5]) + ' ' + event_details[4] + ' ' + parseInt(event_details[3]) + '</span><span>' + parseInt(event_details[6]) + ':' + parseInt(event_details[7]) + '</span></span><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"viewBox="0 0 447.243 447.243"><g><path d="M406.101,232.603c-0.978-0.982-2.019-1.899-3.116-2.745c-13.381-8.971-31.276-7.013-42.4,4.64l-88.64,88.32c-4.7,4.695-8.641,10.093-11.68,16l-5.12,9.76v-314.4c0.677-16.099-10.332-30.349-26.08-33.76c-17.445-2.829-33.881,9.019-36.71,26.465c-0.297,1.83-0.434,3.682-0.41,5.535v315.52l-3.2-6.88c-3.183-6.725-7.515-12.843-12.8-18.08l-88.64-88.48c-11.124-11.653-29.019-13.611-42.4-4.64c-14.259,10.441-17.355,30.464-6.914,44.724c0.844,1.152,1.764,2.247,2.754,3.276l160,160c12.49,12.504,32.751,12.515,45.255,0.025c0.008-0.008,0.017-0.017,0.025-0.025l160-160C418.542,265.382,418.577,245.121,406.101,232.603z" /></g></svg><span class="finish"><span>' + parseInt(event_details[10]) + ' ' + event_details[9] + ' ' + parseInt(event_details[8]) + '</span><span>' + parseInt(event_details[11]) + ':' + parseInt(event_details[12]) + '</span></span></div></div><div class="cover"><span>DELETE</span></div></div>');
                } else {
                    document.querySelector('section.primary .admin .events .games').insertAdjacentHTML('beforeend', '<div class="card battle" eventID="' + event_details[0] + '" onclick="delete_event(\'' + event_details[0] + '\')"><span>Sea Battle</span><div class="info"><span>+' + parseInt(event_details[2]) + '</span><div><span class="start"><span>' + parseInt(event_details[5]) + ' ' + event_details[4] + ' ' + parseInt(event_details[3]) + '</span><span>' + parseInt(event_details[6]) + ':' + parseInt(event_details[7]) + '</span></span><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"viewBox="0 0 447.243 447.243"><g><path d="M406.101,232.603c-0.978-0.982-2.019-1.899-3.116-2.745c-13.381-8.971-31.276-7.013-42.4,4.64l-88.64,88.32c-4.7,4.695-8.641,10.093-11.68,16l-5.12,9.76v-314.4c0.677-16.099-10.332-30.349-26.08-33.76c-17.445-2.829-33.881,9.019-36.71,26.465c-0.297,1.83-0.434,3.682-0.41,5.535v315.52l-3.2-6.88c-3.183-6.725-7.515-12.843-12.8-18.08l-88.64-88.48c-11.124-11.653-29.019-13.611-42.4-4.64c-14.259,10.441-17.355,30.464-6.914,44.724c0.844,1.152,1.764,2.247,2.754,3.276l160,160c12.49,12.504,32.751,12.515,45.255,0.025c0.008-0.008,0.017-0.017,0.025-0.025l160-160C418.542,265.382,418.577,245.121,406.101,232.603z" /></g></svg><span class="finish"><span>' + parseInt(event_details[10]) + ' ' + event_details[9] + ' ' + parseInt(event_details[8]) + '</span><span>' + parseInt(event_details[11]) + ':' + parseInt(event_details[12]) + '</span></span></div></div><div class="cover"><span>DELETE</span></div></div>');
                }
            }
        }
        connection.close();
    }
}

document.querySelector('section.primary .admin .events .template').querySelector('button').addEventListener('click', function () {
    let message = document.querySelector('section.primary .admin .events .template').querySelector('.message');
    let prize = document.querySelector('section.primary .admin .events .template').querySelector('.info .prize');
    let sday, smonth, syear, fday, fmonth, fyear;
    sday = document.querySelector('section.primary .admin .events .template').querySelector('.info .start .day');
    smonth = document.querySelector('section.primary .admin .events .template').querySelector('.info .start .month');
    syear = document.querySelector('section.primary .admin .events .template').querySelector('.info .start .year');
    fday = document.querySelector('section.primary .admin .events .template').querySelector('.info .finish .day');
    fmonth = document.querySelector('section.primary .admin .events .template').querySelector('.info .finish .month');
    fyear = document.querySelector('section.primary .admin .events .template').querySelector('.info .finish .year');

    if (document.querySelector('section.primary .admin .events .template').querySelector('.game').value.localeCompare('battle') == 0 || document.querySelector('section.primary .admin .events .template').querySelector('.game').value.localeCompare('dots') == 0) {
        if (prize.value.length > 0 && parseInt(prize.value) > 0) {
            if (sday.value.length > 0 && parseInt(sday.value) > 0 && parseInt(sday.value) < 32 && smonth.value.length > 0 && parseInt(smonth.value) > 0 && parseInt(smonth.value) < 13 && syear.value.length > 0 && parseInt(syear.value) > 2020) {
                if (fday.value.length > 0 && parseInt(fday.value) > 0 && parseInt(fday.value) < 32 && fmonth.value.length > 0 && parseInt(fmonth.value) > 0 && parseInt(fmonth.value) < 13 && fyear.value.length > 0 && parseInt(fyear.value) > 2020) {
                    message.style.opacity = 0;
                    const connection = new WebSocket('ws://127.0.0.1:4444');

                    connection.onopen = function () {
                        connection.send('user addevent ' + username + " " + token + " " + document.querySelector('section.primary .admin .events .template').querySelector('.game').value + " " + prize.value + ' ' + sday.value + ' ' + smonth.value + ' ' + syear.value + ' ' + fday.value + ' ' + fmonth.value + ' ' + fyear.value);
                    };

                    connection.onmessage = function (e) {
                        let eventid = e.data.split(' ')[0];
                        let smonth = e.data.split(' ')[1];
                        let fmonth = e.data.split(' ')[2];
                        let date = new Date();
                        if (document.querySelector('section.primary .admin .events .template').querySelector('.game').value.localeCompare('dots') == 0) {
                            games.insertAdjacentHTML('beforeend', '<div class="card dots" eventID="' + eventid + '" onclick="delete_event(\'' + eventid + '\')"><span>Dots & Boxes</span><div class="info"><span>+' + prize.value + '</span><div><span class="start"><span>' + sday.value + ' ' + smonth + ' ' + syear.value + '</span><span>' + date.getHours() + ':' + date.getMinutes() + '</span></span><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"viewBox="0 0 447.243 447.243"><g><path d="M406.101,232.603c-0.978-0.982-2.019-1.899-3.116-2.745c-13.381-8.971-31.276-7.013-42.4,4.64l-88.64,88.32c-4.7,4.695-8.641,10.093-11.68,16l-5.12,9.76v-314.4c0.677-16.099-10.332-30.349-26.08-33.76c-17.445-2.829-33.881,9.019-36.71,26.465c-0.297,1.83-0.434,3.682-0.41,5.535v315.52l-3.2-6.88c-3.183-6.725-7.515-12.843-12.8-18.08l-88.64-88.48c-11.124-11.653-29.019-13.611-42.4-4.64c-14.259,10.441-17.355,30.464-6.914,44.724c0.844,1.152,1.764,2.247,2.754,3.276l160,160c12.49,12.504,32.751,12.515,45.255,0.025c0.008-0.008,0.017-0.017,0.025-0.025l160-160C418.542,265.382,418.577,245.121,406.101,232.603z" /></g></svg><span class="finish"><span>' + fday.value + ' ' + fmonth + ' ' + fyear.value + '</span><span>' + date.getHours() + ':' + date.getMinutes() + '</span></span></div></div><div class="cover"><span>DELETE</span></div></div>');
                        } else {
                            games.insertAdjacentHTML('beforeend', '<div class="card battle" eventID="' + eventid + '" onclick="delete_event(\'' + eventid + '\')"><span>Sea Battle</span><div class="info"><span>+' + prize.value + '</span><div><span class="start"><span>' + sday.value + ' ' + smonth + ' ' + syear.value + '</span><span>' + date.getHours() + ':' + date.getMinutes() + '</span></span><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"viewBox="0 0 447.243 447.243"><g><path d="M406.101,232.603c-0.978-0.982-2.019-1.899-3.116-2.745c-13.381-8.971-31.276-7.013-42.4,4.64l-88.64,88.32c-4.7,4.695-8.641,10.093-11.68,16l-5.12,9.76v-314.4c0.677-16.099-10.332-30.349-26.08-33.76c-17.445-2.829-33.881,9.019-36.71,26.465c-0.297,1.83-0.434,3.682-0.41,5.535v315.52l-3.2-6.88c-3.183-6.725-7.515-12.843-12.8-18.08l-88.64-88.48c-11.124-11.653-29.019-13.611-42.4-4.64c-14.259,10.441-17.355,30.464-6.914,44.724c0.844,1.152,1.764,2.247,2.754,3.276l160,160c12.49,12.504,32.751,12.515,45.255,0.025c0.008-0.008,0.017-0.017,0.025-0.025l160-160C418.542,265.382,418.577,245.121,406.101,232.603z" /></g></svg><span class="finish"><span>' + fday.value + ' ' + fmonth + ' ' + fyear.value + '</span><span>' + date.getHours() + ':' + date.getMinutes() + '</span></span></div></div><div class="cover"><span>DELETE</span></div></div>');
                        }
                        connection.close();
                    }
                } else {
                    message.innerHTML = 'Incorrect finish date!';
                    message.style.opacity = 1;
                }
            } else {
                message.innerHTML = 'Incorrect start date!';
                message.style.opacity = 1;
            }
        } else {
            message.innerHTML = 'Enter correct prize number!';
            message.style.opacity = 1;
        }
    } else {
        message.innerHTML = 'Game doesn\'t exist (battle or dots)!';
        message.style.opacity = 1;
    }
});

function delete_event(eventID) {
    let cards = document.querySelectorAll('section.primary .admin .events .games .card');
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user deleteevent ' + username + " " + token + " " + eventID);
        for (let i = 0; i < cards.length; i++) {
            if (cards[i].getAttribute('eventid').localeCompare(eventID) == 0) {
                cards[i].parentNode.removeChild(cards[i]);
                break;
            }
        }
        connection.close();
    };
}

function primary(player) {
    navigation[3].classList.add('show');
    navigation[4].classList.add('show');
    navigation[5].classList.add('show');

    if (player) {
        update_suggestions();
        update_player_events();
        document.querySelector('section.primary .player').style.display = 'block';
    } else {
        update_users();
        update_admin_events();
        document.querySelector('section.primary .admin').style.display = 'block';
    }
}