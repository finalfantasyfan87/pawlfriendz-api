import string
import random
import names


def passwordGenerator(size=10, chars=string.ascii_lowercase + string.ascii_uppercase + string.digits + string.punctuation):
    return ''.join(random.choice(chars) for _ in range(size))


def generateRandomString(stringLength=8):
    lettersAndDigits = string.ascii_letters + string.digits + string.punctuation
    return ''.join((random.choice(lettersAndDigits) for i in range(stringLength)))


def generateRandomEmail(charNum):
    email = string.ascii_letters + string.digits
    return ''.join(random.choice(email) for x in range(charNum))


data = {
    "firstName": names.get_first_name(),
    "lastName": names.get_last_name(),
    "email": generateRandomEmail(12)+"@gmail.com",
    "password": passwordGenerator(),
    "username": generateRandomString(4)
}

print(str(data).replace("'", '"'))
