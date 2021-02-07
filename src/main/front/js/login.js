var login_question = 1;
var login_items = document.querySelectorAll('section.login form ol .item');
var login_inputs = document.querySelectorAll('section.login form ol .item input');
var login_next = document.querySelector('section.login form .next');
var login_progress = document.querySelector('section.login form .progress');
var login_message = document.querySelector('section.login form .message');

document.querySelectorAll('aside nav')[2].addEventListener('click', function () {
    document.querySelectorAll('aside nav')[2].classList.remove('show');
    document.querySelectorAll('aside nav')[1].classList.add('show');
    next_page('login', 'register');

    login_clear_fields();
    login_items[login_question - 1].classList.remove('show');
    login_items[0].classList.add('show');
    login_progress.style.transform = 'scaleX(' + 0 + ')';
    login_next.classList.remove('show');
    login_question = 1;
});

login_inputs[0].addEventListener('focus', function () {
    login_next.classList.add('show');
});

login_inputs[0].addEventListener('keydown', function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        login_username();
    }
});

login_inputs[1].addEventListener('keydown', function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        login_password();
    }
});

login_next.addEventListener('click', function () {
    switch (login_question) {
        case 1:
            login_username();
            break;
        case 2:
            login_password();
            break;
    }
});

function login_username() {
    if (login_inputs[0].value.length > 0) {
        login_toggle(1, 2);
    } else {
        login_message.innerHTML = "Enter username!";
        login_message.style.opacity = 1;
    }
}

function login_password() {
    if (login_inputs[1].value.length > 0) {
        const connection = new WebSocket('ws://127.0.0.1:4444');

        connection.onopen = function () {
            connection.send('user login ' + login_inputs[0].value + ' ' + login_inputs[1].value);
        };

        connection.onmessage = function (e) {
            let data = e.data.split(' ');
            if (data.length != 2) {
                login_progress.style.transform = 'scaleX(' + 2 / 2 + ')';
                document.querySelector('section.login form ol .item.show label').classList.add('tmp');
                login_items[1].style.opacity = 0;
                login_message.style.opacity = 0;
                login_next.classList.remove('show');

                document.querySelectorAll('aside nav')[2].classList.remove('show');
                document.querySelectorAll('aside nav')[3].classList.add('show');
                document.querySelectorAll('aside nav')[4].classList.add('show');
                document.querySelectorAll('aside nav')[5].classList.add('show');
                setTimeout(function () {
                    document.querySelectorAll('aside nav')[2].classList.remove('show');
                    primary(data[0].localeCompare('admin') != 0);
                    next_page('login', 'primary');
                    login_items[1].classList.remove('show');
                }, 300);
                login_clear_fields();

                player = data[0].localeCompare('admin') != 0;
                username = data[1];
                token = data[2];
                first_name = data[3];
                last_name = data[4];
                phone = data[5];
                email = data[6];
                year = parseInt(data[7]);
                month = parseInt(data[8]);
                day = parseInt(data[9]);
                level = player ? 0 : parseInt(data[10]);
                money = player ? 0.0 : parseFloat(data[11]);
            } else {
                if (data[1].localeCompare('username') == 0) {
                    login_message.innerHTML = "Username doesn't exist!";
                    login_message.style.opacity = 1;
                } else if (data[1].localeCompare('password') == 0) {
                    login_message.innerHTML = "Wrong password!";
                    login_message.style.opacity = 1;
                } else {
                    login_message.innerHTML = "You've logged in already!";
                    login_message.style.opacity = 1;
                }
                setTimeout(function () {
                    login_clear_fields();
                    login_toggle(2, 1);
                }, 1000);
            }
            connection.close();
        };
    } else {
        login_message.innerHTML = "Enter password!";
        login_message.style.opacity = 1;
    }
}

function login_toggle(num1, num2) {
    login_progress.style.transform = 'scaleX(' + (num1 % 2) / 2 + ')';
    login_items[num1 - 1].classList.remove('show');
    login_items[num2 - 1].classList.add('show');
    login_items[num2 - 1].querySelector('input').focus();
    login_message.style.opacity = 0;
    login_question += num2 - num1;
}

function login_clear_fields() {
    for (let i = 0; i < 2; i++) {
        login_inputs[i].value = '';
    }
}