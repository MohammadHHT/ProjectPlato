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
    document.querySelector('section.primary .player').style.display = 'block';
    let list = document.querySelector('section.primary .player header ol');
    let search = document.querySelector('section.primary .player header .search');
    search.addEventListener('keyup', function (event) {
        event.preventDefault();
        list.innerHTML = '';
        list.style.height = 0;
        if (search.value.length > 3) {
            update_list(search.value);
        }
    });

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
}

function primary_admin() {
    document.querySelector('section.primary .admin').style.display = 'block';
}