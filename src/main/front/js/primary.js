function primary(player) {
    let navigation = document.querySelectorAll('aside nav');
    navigation[3].classList.add('show');
    navigation[4].classList.add('show');
    navigation[5].classList.add('show');
    if (player) {
        primary_player();
    } else {
        primary_admin();
    }
}

function primary_player() {
    let list = document.querySelector('section.primary .player header ol');
    let search = document.querySelector('section.primary .player header .search');
    let events_dots = document.querySelector('section.primary .player .events .dots');
    let events_battle = document.querySelector('section.primary .player .events .battle');

    search.addEventListener('keyup', function (event) {
        event.preventDefault();
        list.innerHTML = '';
        list.style.height = 0;
        if (search.value.length > 3) {
            update_list(search.value);
        }
    });

    update_suggestions();
    update_events();

    document.querySelector('section.primary .player').style.display = 'block';

    function update_list(value) {
        const connection = new WebSocket('ws://127.0.0.1:4444');

        connection.onopen = function () {
            connection.send('user search ' + username + " " + token + " " + value);
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
        }
    }

    function update_suggestions() {
        const connection = new WebSocket('ws://127.0.0.1:4444');

        connection.onopen = function () {
            connection.send('user suggestions ' + username + " " + token);
        };

        connection.onmessage = function (e) {
            let suggestions = e.data;
            console.log(suggestions);
            if (suggestions.includes('dots')) {
                document.querySelector('section.primary .player .favorites .dots').style.visibility = 'visible';
            }
            if (suggestions.includes('battle')) {
                document.querySelector('section.primary .player .favorites .battle').style.visibility = 'visible';
            }
        }
    }

    function update_events(value) {
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
                        events_dots.insertAdjacentHTML('beforeend', '<div class="card" eventID="' + parseInt(event_details[0]) + '"><span>Dots & Boxes</span><div class="info"><span>+' + parseInt(event_details[2]) + '</span><div><span class="start"><span>' + parseInt(event_details[5]) + ' ' + event_details[4] + ' ' + parseInt(event_details[3]) + '</span><span>' + parseInt(event_details[6]) + ':' + parseInt(event_details[7]) + '</span></span><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"viewBox="0 0 447.243 447.243"><g><path d="M406.101,232.603c-0.978-0.982-2.019-1.899-3.116-2.745c-13.381-8.971-31.276-7.013-42.4,4.64l-88.64,88.32c-4.7,4.695-8.641,10.093-11.68,16l-5.12,9.76v-314.4c0.677-16.099-10.332-30.349-26.08-33.76c-17.445-2.829-33.881,9.019-36.71,26.465c-0.297,1.83-0.434,3.682-0.41,5.535v315.52l-3.2-6.88c-3.183-6.725-7.515-12.843-12.8-18.08l-88.64-88.48c-11.124-11.653-29.019-13.611-42.4-4.64c-14.259,10.441-17.355,30.464-6.914,44.724c0.844,1.152,1.764,2.247,2.754,3.276l160,160c12.49,12.504,32.751,12.515,45.255,0.025c0.008-0.008,0.017-0.017,0.025-0.025l160-160C418.542,265.382,418.577,245.121,406.101,232.603z" /></g></svg><span class="finish"><span>' + parseInt(event_details[10]) + ' ' + event_details[9] + ' ' + parseInt(event_details[8]) + '</span><span>' + parseInt(event_details[11]) + ':' + parseInt(event_details[12]) + '</span></span></div></div><div class="cover"><span>JOIN</span></div></div>');
                    } else {
                        events_battle.insertAdjacentHTML('beforeend', '<div class="card" eventID="' + parseInt(event_details[0]) + '"><span>Sea Battle</span><div class="info"><span>+' + parseInt(event_details[2]) + '</span><div><span class="start"><span>' + parseInt(event_details[5]) + ' ' + event_details[4] + ' ' + parseInt(event_details[3]) + '</span><span>' + parseInt(event_details[6]) + ':' + parseInt(event_details[7]) + '</span></span><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"viewBox="0 0 447.243 447.243"><g><path d="M406.101,232.603c-0.978-0.982-2.019-1.899-3.116-2.745c-13.381-8.971-31.276-7.013-42.4,4.64l-88.64,88.32c-4.7,4.695-8.641,10.093-11.68,16l-5.12,9.76v-314.4c0.677-16.099-10.332-30.349-26.08-33.76c-17.445-2.829-33.881,9.019-36.71,26.465c-0.297,1.83-0.434,3.682-0.41,5.535v315.52l-3.2-6.88c-3.183-6.725-7.515-12.843-12.8-18.08l-88.64-88.48c-11.124-11.653-29.019-13.611-42.4-4.64c-14.259,10.441-17.355,30.464-6.914,44.724c0.844,1.152,1.764,2.247,2.754,3.276l160,160c12.49,12.504,32.751,12.515,45.255,0.025c0.008-0.008,0.017-0.017,0.025-0.025l160-160C418.542,265.382,418.577,245.121,406.101,232.603z" /></g></svg><span class="finish"><span>' + parseInt(event_details[10]) + ' ' + event_details[9] + ' ' + parseInt(event_details[8]) + '</span><span>' + parseInt(event_details[11]) + ':' + parseInt(event_details[12]) + '</span></span></div></div><div class="cover"><span>JOIN</span></div></div>');
                    }
                }
            }
        }
    }
}

function primary_admin() {
    document.querySelector('section.primary .admin').style.display = 'block';
}