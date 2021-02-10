let first_name_tmp;
let last_name_tmp;
let username_tmp;
let email_tmp;

navigation[6].addEventListener('click', function () {
    navigation[6].classList.remove('show');
    navigation[3].classList.add('show');
    navigation[5].classList.add('show');
    navigation[4].classList.add('show');
    next_page('account', 'primary');
});

document.querySelector('section.account .first #avatar').src = 'avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg';

function avatar_change() {
    document.querySelector('section.account .first #avatar').classList.remove('show');
    setTimeout(function () {
        document.querySelector('section.account .first #avatar').src = 'avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg';
        document.querySelector('section.account .first #avatar').classList.add('show');
    }, 300);
}

document.querySelector('section.account .first #avatar').addEventListener('click', avatar_change);

document.querySelector('section.account header .second .level').style.top = (document.querySelector('.account header').offsetWidth / 50 + document.querySelector('section.account header .second').offsetHeight / 20) + 'px';
document.querySelector('section.account header .second .level').style.right = document.querySelector('.account header').offsetWidth / 10 + 'px';
document.querySelector('section.account header .second .level').style.transform = 'translateX(100%) scale(' + window.innerHeight * 0.03 / document.querySelector('section.account header .second .level').offsetHeight + ')';

document.querySelector('section.account header .edit').addEventListener('click', function () {
    if (document.querySelector('section.account header .second .name').contentEditable.localeCompare('false') == 0) {
        document.querySelector('section.account header .second .name').contentEditable = 'true';
        document.querySelector('section.account header .second .username span').contentEditable = 'true';
        document.querySelector('section.account header .second .email span').contentEditable = 'true';
        document.querySelector('section.account header .edit').classList.add('hide');
        document.querySelector('section.account header .third').classList.add('show');
    }
});

document.querySelector('section.account header .third .apply').addEventListener('click', function () {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user edit ' + username + ' ' + token + ' firstname ' + document.querySelector('section.account header .second .name').textContent.split(' ')[0]);
        connection.send('user edit ' + username + ' ' + token + ' lastname ' + document.querySelector('section.account header .second .name').textContent.split(' ')[1]);
        connection.send('user edit ' + username + ' ' + token + ' username ' + document.querySelector('section.account header .second .username span').textContent);
        connection.send('user edit ' + username + ' ' + token + ' email ' + document.querySelector('section.account header .second .email span').textContent);
    };

    connection.onmessage = function (e) {
        console.log('he1');
        if (e.data.split(' ')[0].localeCompare('false') == 0) {
            console.log('hey');
            if (e.data.split(' ')[1].localeCompare('username') == 0) {
                alert('Username already exists!');
                document.querySelector('section.account header .second .username span').textContent = username_tmp;
            } else {
                alert('Email already exists!');
                document.querySelector('section.account header .second .email span').textContent = email_tmp;
            }
        }
    }

    setTimeout(function () {
        connection.close();
        first_name_tmp = document.querySelector('section.account header .second .name').textContent.split(' ')[0];
        last_name_tmp = document.querySelector('section.account header .second .name').textContent.split(' ')[1];
        username_tmp = document.querySelector('section.account header .second .username span').textContent;
        email_tmp = document.querySelector('section.account header .second .email span').textContent;
    }, 5000);

    document.querySelector('section.account header .second .name').contentEditable = 'false';
    document.querySelector('section.account header .second .username span').contentEditable = 'false';
    document.querySelector('section.account header .second .email span').contentEditable = 'false';
    document.querySelector('section.account header .edit').classList.remove('hide');
    document.querySelector('section.account header .third').classList.remove('show');
});

document.querySelector('section.account header .third .ignore').addEventListener('click', function () {
    document.querySelector('section.account header .second .name').textContent = first_name_tmp + ' ' + last_name_tmp;
    document.querySelector('section.account header .second .username span').textContent = username_tmp;
    document.querySelector('section.account header .second .email span').textContent = email_tmp;

    document.querySelector('section.account header .second .name').contentEditable = 'false';
    document.querySelector('section.account header .second .username span').contentEditable = 'false';
    document.querySelector('section.account header .second .email span').contentEditable = 'false';
    document.querySelector('section.account header .edit').classList.remove('hide');
    document.querySelector('section.account header .third').classList.remove('show');
});

function update_friends() {
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user friends ' + username + " " + token);
    };

    connection.onmessage = function (e) {
        let list = document.querySelector('section.account .friends ol');
        let friends = e.data.split(' ');

        for (let i = 0; i < friends.length; i++) {
            if (friends[i].length > 0) {
                list.insertAdjacentHTML('beforeend', '<li class="item" name="' + friends[i] + '"><img src="avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg"><h2>' + friends[i] + '</h2><div class="delete" onclick="delete_friend(\'' + friends[i] + '\')"><div><span>DELETE</span></div></div></li>')
            }
        }
        document.querySelector('section.account .friends h1').innerHTML = 'FRIENDS (' + friends.length + ')';
        connection.close();
    }
}

function delete_friend(friend) {
    console.log(friend);
    const connection = new WebSocket('ws://127.0.0.1:4444');

    connection.onopen = function () {
        connection.send('user deletefriend ' + username + " " + token + " " + friend);
    };

    let items = document.querySelectorAll('section.account .friends ol li');
    for (let i = 0; i < items.length; i++) {
        if (items[i].getAttribute('name').localeCompare(friend) == 0) {
            items[i].classList.add('hide');
            setTimeout(function () {
                items[i].parentNode.removeChild(items[i]);
            }, 300);
            break;
        }
    }
}

function account(self) {
    if (!self) {
        document.querySelector('section.account header .edit').classList.add('hide');
        document.querySelector('section.account .first #avatar').removeEventListener('click', avatar_change);
        document.querySelector('section.account .first #avatar').style.cursor = 'default';
    }

    first_name_tmp = first_name;
    last_name_tmp = last_name;
    username_tmp = username;
    email_tmp = email;

    document.querySelector('section.account header .first .money span').textContent = money;
    document.querySelector('section.account header .second .name').textContent = first_name + ' ' + last_name;
    document.querySelector('section.account header .second .username span').textContent = username;
    document.querySelector('section.account header .second .email span').textContent = email;
    document.querySelector('section.account header .second .level').innerHTML = level;
    document.querySelector('section.account header #age').textContent = 'since ' + day + ' / ' + month + ' / ' + year;

    update_friends();

    if (!player) {
        document.querySelector('section.account header .second .level').style.display = 'none';
        document.querySelector('section.account header .first .money').style.display = 'none';
        document.querySelector('section.account .friends').style.display = 'none';
    }
}