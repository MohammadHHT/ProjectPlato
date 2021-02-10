navigation[6].addEventListener('click', function () {
    navigation[6].classList.remove('show');
    navigation[3].classList.add('show');
    navigation[5].classList.add('show');
    navigation[4].classList.add('show');
    next_page('account', 'primary');
});

document.querySelector('section.account .first #avatar').src = 'avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg';

document.querySelector('section.account .first #avatar').addEventListener('click', function () {
    document.querySelector('section.account .first #avatar').classList.remove('show');
    setTimeout(function () {
        document.querySelector('section.account .first #avatar').src = 'avatar/avatar (' + Math.floor(Math.random() * 50 + 1) + ').svg';
        document.querySelector('section.account .first #avatar').classList.add('show');
    }, 300);
});

document.querySelector('section.account header .second .level').style.top = (document.querySelector('.account header').offsetWidth / 50 + document.querySelector('section.account header .second').offsetHeight / 20) + 'px';
document.querySelector('section.account header .second .level').style.right = document.querySelector('.account header').offsetWidth / 10 + 'px';
document.querySelector('section.account header .second .level').style.transform = 'translateX(100%) scale(' + window.innerHeight * 0.03 / document.querySelector('section.account header .second .level').offsetHeight + ')';

document.querySelector('section.account header .edit').addEventListener('click', function () {
    if (document.querySelector('section.account header .second .name').contentEditable.localeCompare('false') == 0) {
        document.querySelector('section.account header .second .name').contentEditable = 'true';
        document.querySelector('section.account header .second .username').contentEditable = 'true';
        document.querySelector('section.account header .second .email').contentEditable = 'true';
        document.querySelector('section.account header .edit').classList.add('hide');
        document.querySelector('section.account header .third').classList.add('show');
    }
});

function account() {

}