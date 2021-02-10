var register_question = 1;
var register_items = document.querySelectorAll('section.register form ol .item');
var register_inputs = document.querySelectorAll('section.register form ol .item input');
var register_next = document.querySelector('section.register form .next');
var register_progress = document.querySelector('section.register form .progress');
var register_message = document.querySelector('section.register form .message');

navigation[1].addEventListener('click', function () {
    navigation[1].classList.remove('show');
    navigation[2].classList.add('show');
    next_page('register', 'login');

    register_clear_fields();
    register_items[register_question - 1].classList.remove('show');
    register_items[0].classList.add('show');
    register_progress.style.transform = 'scaleX(' + 0 + ')';
    register_next.classList.remove('show');
    register_question = 1;
});

register_inputs[0].addEventListener('focus', function () {
    register_next.classList.add('show');
});

register_inputs[0].addEventListener('keydown', function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        register_first_name();
    }
});

register_inputs[1].addEventListener('keydown', function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        register_last_name();
    }
});

register_inputs[2].addEventListener('keydown', function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        register_username();
    }
});

register_inputs[3].addEventListener('keydown', function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        register_password();
    }
});

register_inputs[4].addEventListener('keydown', function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        register_phone();
    }
});

register_inputs[5].addEventListener('keydown', function (event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        register_email();
    }
});

register_next.addEventListener('click', function () {
    switch (register_question) {
        case 1:
            register_first_name();
            break;
        case 2:
            register_last_name();
            break;
        case 3:
            register_username();
            break;
        case 4:
            register_password();
            break;
        case 5:
            register_phone();
            break;
        case 6:
            register_email();
            break;
    }
});

function register_first_name() {
    if (/^[a-zA-Z\s]+$/.test(register_inputs[0].value)) {
        register_toggle(1);
    } else {
        register_message.innerHTML = "Just english letters!";
        register_message.style.opacity = 1;
    }
}

function register_last_name() {
    if (/^[a-zA-Z\s]+$/.test(register_inputs[1].value)) {
        register_toggle(2);
    } else {
        register_message.innerHTML = "Just english letters!";
        register_message.style.opacity = 1;
    }
}

function register_username() {
    if (/\w{3}\w*/.test(register_inputs[2].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');

        connection.onopen = function () {
            connection.send('user register username ' + register_inputs[2].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                register_toggle(3);
            } else {
                register_message.innerHTML = "Username unavailable!";
                register_message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        register_message.innerHTML = "Username must be at least 3 characters!";
        register_message.style.opacity = 1;
    }
}

function register_password() {
    if (/\w{6}\w*/.test(register_inputs[3].value)) {
        register_toggle(4);
    } else {
        register_message.innerHTML = "Password must be at least 6 characters!";
        register_message.style.opacity = 1;
    }
}

function register_phone() {
    if (/\d{12}/.test(register_inputs[4].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');

        connection.onopen = function () {
            connection.send('user register phone ' + register_inputs[4].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                register_toggle(5);
            } else {
                register_message.innerHTML = "Phone number already exists!";
                register_message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        register_message.innerHTML = "Invalid phone!";
        register_message.style.opacity = 1;
    }
}

function register_email() {
    if (/^\w+@\w+\.(com|ir)$/.test(register_inputs[5].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');

        connection.onopen = function () {
            connection.send('user register email ' + register_inputs[5].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                register_progress.style.transform = 'scaleX(' + 6 / 6 + ')';
                register_items[5].style.opacity = 0;
                register_message.style.opacity = 0;
                register_next.classList.remove('show');

                navigation[1].classList.remove('show');
                navigation[3].classList.add('show');
                navigation[4].classList.add('show');
                navigation[5].classList.add('show');
                setTimeout(function () {
                    navigation[1].classList.remove('show');
                    primary(true);
                    next_page('register', 'primary');
                    register_items[5].classList.remove('show');
                }, 300);
                submit();
            } else if (e.data.localeCompare('false') == 0) {
                register_message.innerHTML = "Email already exists!";
                register_message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        register_message.innerHTML = "Invalid email!";
        register_message.style.opacity = 1;
    }
}

function register_toggle(num) {
    register_progress.style.transform = 'scaleX(' + num / 6 + ')';
    register_items[num - 1].classList.remove('show');
    register_items[num].classList.add('show');
    register_items[num].querySelector('input').focus();
    register_message.style.opacity = 0;
    register_question++;
}

function register_clear_fields() {
    for (let i = 0; i < 6; i++) {
        register_inputs[i].value = '';
    }
}

function submit() {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user register ' + register_inputs[0].value + ' ' + register_inputs[1].value + ' ' + register_inputs[2].value + ' ' + register_inputs[3].value + ' ' + register_inputs[4].value + ' ' + register_inputs[5].value);
    };

    connection.onmessage = function (e) {
        let date = new Date();
        player = true;
        username = register_inputs[2].value;
        token = e.data;
        first_name = register_inputs[0].value;
        last_name = register_inputs[1].value;
        phone = register_inputs[4].value;
        email = register_inputs[5].value;
        year = date.getFullYear();
        month = date.getMonth() + 1;
        day = date.getDate();
        level = 1;
        money = 0.0;
        account(true);
        register_clear_fields();
        connection.close();
    }
}