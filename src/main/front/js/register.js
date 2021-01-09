function register() {
    this.items = document.querySelectorAll('section.register form ol .item');
    this.inputs = document.querySelectorAll('section.register form ol .item input');
    this.next = document.querySelector('section.register form .next');
    this.message = document.querySelector('section.register form .message');
    this.progress = document.querySelector('section.register form .progress');
    this.navigation = document.querySelectorAll('aside nav');
    this.question = 1;
    this.init();
}

register.prototype.init = function () {
    const self = this;

    this.inputs[0].addEventListener('focus', function (event) {
        self.next.classList.add('show');
    });

    this.inputs[0].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.firstName();
        }
    });

    this.inputs[1].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.lastName();
        }
    });

    this.inputs[2].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.username();
        }
    });

    this.inputs[3].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.password();
        }
    });

    this.inputs[4].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.phone();
        }
    });

    this.inputs[5].addEventListener('keydown', function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            self.email();
        }
    });

    this.next.addEventListener('click', function (event) {
        switch (self.question) {
            case 1:
                self.firstName();
                break;
            case 2:
                self.lastName();
                break;
            case 3:
                self.username();
                break;
            case 4:
                self.password();
                break;
            case 5:
                self.phone();
                break;
            case 6:
                self.email();
                break;
        }
    });

    this.navigation[1].addEventListener('click', function (event) {
        self.navigation[1].classList.remove('show');
        self.navigation[2].classList.add('show');
        self.nextPage('register', 'login');
    });
}

register.prototype.firstName = function () {
    var e = new RegExp('^[a-zA-Z\\s]+$');
    if (e.test(this.inputs[0].value)) {
        this.progress.style.transform = 'scaleX(' + 1 / 6 + ')';
        this.items[0].classList.remove('show');
        this.items[1].classList.add('show');
        this.items[1].querySelector('input').focus();
        this.message.style.opacity = 0;
        this.question++;
    } else {
        this.message.innerHTML = "Just english letters!";
        this.message.style.opacity = 1;
    }
}

register.prototype.lastName = function () {
    var e = new RegExp('^[a-zA-Z\\s]+$');
    if (e.test(this.inputs[1].value)) {
        this.progress.style.transform = 'scaleX(' + 2 / 6 + ')';
        this.items[1].classList.remove('show');
        this.items[2].classList.add('show');
        this.items[2].querySelector('input').focus();
        this.message.style.opacity = 0;
        this.question++;
    } else {
        this.message.innerHTML = "Just english letters!";
        this.message.style.opacity = 1;
    }
}

register.prototype.username = function () {
    var e = new RegExp('\\w{3}\\w*');
    if (e.test(this.inputs[2].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');
        const self = this;

        connection.onopen = function () {
            connection.send('user register username ' + self.inputs[2].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                self.progress.style.transform = 'scaleX(' + 3 / 6 + ')';
                self.items[2].classList.remove('show');
                self.items[3].classList.add('show');
                self.items[3].querySelector('input').focus();
                self.message.style.opacity = 0;
                self.question++;
            } else {
                self.message.innerHTML = "Username unavailable!";
                self.message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        this.message.innerHTML = "Username must be at least 3 characters!";
        this.message.style.opacity = 1;
    }
}

register.prototype.password = function () {
    var e = new RegExp('\\w{6}\\w*');
    if (e.test(this.inputs[3].value)) {
        this.progress.style.transform = 'scaleX(' + 4 / 6 + ')';
        this.items[3].classList.remove('show');
        this.items[4].classList.add('show');
        this.items[4].querySelector('input').focus();
        this.message.style.opacity = 0;
        this.question++;
    } else {
        this.message.innerHTML = "Password must be at least 6 characters!";
        this.message.style.opacity = 1;
    }
}

register.prototype.phone = function () {
    var e = new RegExp('\\d{12}');
    if (e.test(this.inputs[4].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');
        const self = this;

        connection.onopen = function () {
            connection.send('user register phone ' + self.inputs[4].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                self.progress.style.transform = 'scaleX(' + 5 / 6 + ')';
                self.items[4].classList.remove('show');
                self.items[5].classList.add('show');
                self.items[5].querySelector('input').focus();
                self.message.style.opacity = 0;
                self.question++;
            } else {
                self.message.innerHTML = "Phone number already exists!";
                self.message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        this.message.innerHTML = "Invalid phone!";
        this.message.style.opacity = 1;
    }
}

register.prototype.email = function () {
    var e = new RegExp('^\\w+@\\w+\\.(com|ir)$');
    if (e.test(this.inputs[5].value)) {
        const connection = new WebSocket('ws://127.0.0.1:4444');
        const self = this;

        connection.onopen = function () {
            connection.send('user register email ' + self.inputs[5].value);
        };

        connection.onmessage = function (e) {
            if (e.data.localeCompare('true') == 0) {
                self.progress.style.transform = 'scaleX(' + 6 / 6 + ')';
                self.items[5].style.opacity = 0;
                self.message.style.opacity = 0;
                self.next.classList.remove('show');
                connection.send('user register ' + self.inputs[0].value + ' ' + self.inputs[1].value + ' ' + self.inputs[2].value + ' ' + self.inputs[3].value + ' ' + self.inputs[4].value + ' ' + self.inputs[5].value);
                self.clearFields();
                setTimeout(function() {
                    nextPage('register', 'primary');
                    self.navigation[1].classList.remove('show');
                    self.navigation[3].classList.add('show');
                    self.navigation[4].classList.add('show');
                    self.navigation[5].classList.add('show');
                }, 1000);
            } else if (e.data.localeCompare('false') == 0) {
                self.message.innerHTML = "Email already exists!";
                self.message.style.opacity = 1;
            }
            connection.close();
        };
    } else {
        this.message.innerHTML = "Invalid email!";
        this.message.style.opacity = 1;
    }
}

register.prototype.nextPage = function (previous, next) {
    let pre = document.querySelector('section.' + previous);
    let nxt = document.querySelector('section.' + next);

    if (pre != undefined) {
        pre.classList.remove('page');
    }
    nxt.classList.add('page');
}

register.prototype.clearFields = function () {
    for (let i = 0; i < 6; i++) {
        this.inputs[i].value = '';
    }
}