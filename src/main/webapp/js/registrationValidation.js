const form = document.getElementById('form');
const nickname = document.getElementById('nickname');
const email = document.getElementById('email');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');
const phoneNumber = document.getElementById('phoneNumber');
const name = document.getElementById('name');
const surname = document.getElementById('surname');
const login = document.getElementById('login');

form.addEventListener('submit', e => {
    if (validateInputs()) {
        e.preventDefault();
    } else {
        window.location.replace("Controller?command=registration");
    }
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

const isValidPassword = nickname => {
    let re = /^[a-zA-Z0-9_]{5,49}$/;
    return re.test(String(nickname));
}

const isValidNickname = nickname => {
    let re = /^[a-zA-Z0-9_]{2,49}$/;
    return re.test(String(nickname));
}

const isValidEmail = email => {
    let re = /^[\w-.]+@([\w-]+\.)+[\w]+$/;
    return re.test(String(email).toLowerCase());
}

const isValidPhoneNumber = phoneNumber => {
    let re = /^((375(\d{2})|\+375(\d{2}))|\d{2})\d{7}/;
    return re.test(phoneNumber);
}

const isValidName = name => {
    let re = /^[A-Za-zА-Яа-я]\w{2,29}$/;
    return re.test(name);
}

const isValidSurname = surname => {
    let re = /^[A-Za-zА-Яа-я]\w{3,29}$/;
    return re.test(surname);
}

const isValidLogin = login => {
    let re = /^[a-zA-Z0-9_]{5,49}$/;
    return re.test(login);
}

const validateInputs = () => {
    const nicknameValue = nickname.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const password2Value = password2.value.trim();
    const phoneNumberValue = phoneNumber.value.trim();
    const nameValue = name.value.trim();
    const surnameValue = surname.value.trim();
    const loginValue = login.value.trim();

    let counter = 0;

    if(loginValue === '') {
        setError(login, 'Login is required');
        counter++;
    } else if (loginValue.length < 5 ) {
        setError(login, 'Login must be at least 5 character.')
        counter++;
    } else if (!isValidLogin(loginValue)) {
        setError(login, 'Provide a valid login');
        counter++;
    } else {
        setSuccess(login);
    }

    if(nicknameValue === '') {
        setError(nickname, 'Nickname is required');
        counter++;
    } else if (nicknameValue.length < 3 ) {
        setError(nickname, 'Nickname must be at least 3 character.')
        counter++;
    } else if (!isValidNickname(nicknameValue)) {
        setError(nickname, 'Provide a valid nickname');
        counter++;
    } else {
        setSuccess(nickname);
    }

    if(nameValue === '') {
        setError(name, 'Name is required');
        counter++;
    } else if (surnameValue.length < 3 ) {
        setError(name, 'Name must be at least 3 character.')
        counter++;
    }else if (!isValidName(nameValue)) {
        setError(name, 'Provide a valid name');
        counter++;
    } else {
        setSuccess(name);
    }

    if(surnameValue === '') {
        setError(surname, 'Surname is required');
        counter++;
    } else if (surnameValue.length < 4 ) {
        setError(surname, 'Surname must be at least 4 character.')
        counter++;
    } else if (!isValidSurname(surnameValue)) {
        setError(surname, 'Provide a valid surname');
        counter++;
    } else {
        setSuccess(surname);
    }

    if(phoneNumberValue === '') {
        setError(phoneNumber, 'Phone number is required');
        counter++;
    } else if (phoneNumberValue.length < 9 ) {
        setError(phoneNumber, 'Phone number must be at least 9 character.')
        counter++;
    }else if (!isValidPhoneNumber(phoneNumberValue)) {
        setError(phoneNumber, 'Provide a valid phone number');
        counter++;
    } else {
        setSuccess(phoneNumber);
    }

    if(emailValue === '') {
        setError(email, 'Email is required');
        counter++;
    } else if (!isValidEmail(emailValue)) {
        setError(email, 'Provide a valid email address');
        counter++;
    } else {
        setSuccess(email);
    }

    if(passwordValue === '') {
        setError(password, 'Password is required');
        counter++;
    } else if (passwordValue.length < 6 ) {
        setError(password, 'Password must be at least 6 character.')
        counter++;
    } else if (!isValidPassword(passwordValue)) {
        setError(password, 'Provide a valid password');
        counter++;
    } else {
        setSuccess(password);
    }

    if(password2Value === '') {
        setError(password2, 'Please confirm your password');
        counter++;
    } else if (password2Value.length < 6 ) {
        setError(password2, 'Password must be at least 6 character.')
        counter++;
    } else if (!isValidPassword(password2Value)) {
        setError(password2, 'Provide a valid password');
        counter++;
    }else if (password2Value !== passwordValue) {
        setError(password2, "Passwords doesn't match");
        counter++;
    } else {
        setSuccess(password2);
    }

    return counter;
};